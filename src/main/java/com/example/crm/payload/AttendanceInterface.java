package com.example.crm.payload;


import java.util.UUID;

public interface AttendanceInterface {
    UUID getUserId();
    UUID getGroupId();
    boolean getPresent();
    int getHomework();

}
