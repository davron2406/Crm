package com.example.crm.service;


import com.example.crm.entity.Attendance;
import com.example.crm.entity.User;
import com.example.crm.payload.ApiResponse;
import com.example.crm.payload.AttendanceDto;
import com.example.crm.payload.TotalAttendance;
import com.example.crm.repository.AttendanceRepository;
import com.example.crm.repository.GroupRepository;
import com.example.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;


    public ApiResponse addAttendance(List<AttendanceDto> attendanceDtos) {
        List<Attendance> attendanceList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        attendanceDtos.forEach(attendanceDto -> {
            if(!checkAttendance(attendanceDto)) {
                Attendance attendance = new Attendance();
                attendance.setDate(now);
                attendance.setPresent(attendanceDto.isPresent());
                attendance.setUserId(attendanceDto.getUserId());
                attendance.setGroupId(attendanceDto.getGroupId());
                attendance.setHomework(attendanceDto.getHomework());
                attendanceList.add(attendance);
            }

            else{
                updateAttendance(attendanceDto);
            }
        });
        attendanceRepository.saveAll(attendanceList);
        return new ApiResponse("Saved", true);
    }

    public boolean checkAttendance(AttendanceDto attendanceDto) {
      return  attendanceRepository.existsAttendanceByUserIdAndGroupIdAndDate(
                attendanceDto.getUserId(),
                attendanceDto.getGroupId(),
                LocalDate.now()
        );
    }

    public void updateAttendance(AttendanceDto attendanceDto){
        Optional<Attendance> attendanceByUserIdAndGroupIdAndDate = attendanceRepository.findAttendanceByUserIdAndGroupIdAndDate(
                attendanceDto.getUserId(),
                attendanceDto.getGroupId(),
                LocalDate.now()
        );
        Attendance attendance = attendanceByUserIdAndGroupIdAndDate.get();
        attendance.setHomework(attendanceDto.getHomework());
        attendance.setPresent(attendanceDto.isPresent());
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getGroupTodayAttendance(UUID groupId) {
       return attendanceRepository.findAttendancesByGroupIdAndDate(groupId,LocalDate.now());
    }

    public List<TotalAttendance> getGroupMonthAttendance(UUID groupId, int month, int year) {
        List<TotalAttendance> totalAttendances = new ArrayList<>();
        List<User> groupUsers = groupRepository.findGroupUsers(groupId);
        groupUsers.forEach((groupUser) -> {
            totalAttendances.add(getUserMonthAttendanceAndHomeworkTotal(groupId,groupUser.getId(),month,year));
        });
    return totalAttendances;
    }

    public TotalAttendance getUserMonthAttendanceAndHomeworkTotal(UUID groupId, UUID userId, int month, int year) {
        List<Attendance> userGroupMonthAttendances = attendanceRepository.getUserGroupMonthAttendance(groupId, userId, month, year);
        AtomicInteger attendanceCount = new AtomicInteger();
        AtomicInteger allLessons = new AtomicInteger();
        AtomicInteger totalHomework = new AtomicInteger();
        System.out.println(userGroupMonthAttendances);
        userGroupMonthAttendances.forEach((attendance) -> {
            System.out.println(attendance);
            if (attendance.isPresent()) {
                attendanceCount.getAndIncrement();
                totalHomework.addAndGet(attendance.getHomework());
            }
            allLessons.getAndIncrement();
        });
        return new TotalAttendance(attendanceCount.get(),allLessons.get(),(float) totalHomework.get() / attendanceCount.get(),userId,userRepository.findById(userId).get().getFullName());
    }

    public List<Attendance> getUserMonthlyAttendanceAndHomework(
            UUID userId,UUID groupId, int month, int year) {
            return attendanceRepository.getUserGroupMonthAttendance(groupId,userId,month,year);
    }



}
