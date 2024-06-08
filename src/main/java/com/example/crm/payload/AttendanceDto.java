package com.example.crm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDto {
    private UUID userId;
    private  UUID groupId;
    private boolean isPresent;
    private int homework;
}
