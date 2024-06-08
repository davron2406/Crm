package com.example.crm.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attendance extends AbstractEntity{

    
    private UUID userId;

    private UUID groupId;

    private LocalDate date;

    private boolean isPresent;

    private int homework;

}
