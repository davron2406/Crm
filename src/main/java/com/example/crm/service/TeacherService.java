package com.example.crm.service;


import com.example.crm.entity.Teacher;
import com.example.crm.entity.User;
import com.example.crm.payload.ApiResponse;
import com.example.crm.payload.GetTeacherDto;
import com.example.crm.repository.RoleRepository;
import com.example.crm.repository.TeacherRepository;
import com.example.crm.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addTeacher(UUID id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(user,teacher);
            teacher.setSalary(0);
            teacher.setRole(roleRepository.findByName("teacher").get());
            userRepository.delete(user);
            teacherRepository.save(teacher);
            return new ApiResponse("Teacher added successfully", true);
        }

        return new ApiResponse("Teacher not found", true);
    }

    public Page<Teacher> getTeacherIdsAndNames(Pageable pageable) {
        return teacherRepository.getTeacherIdsAndNames(pageable);

    }

    public Page<GetTeacherDto> getTeachers(Pageable pageable) {
        return teacherRepository.getTeachers(pageable);
    }
}
