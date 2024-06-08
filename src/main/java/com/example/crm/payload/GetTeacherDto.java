package com.example.crm.payload;

import java.util.UUID;


public interface GetTeacherDto {
     String getFullName();
     UUID getId();
     double getSalary();
     int getGroupCount();
}
