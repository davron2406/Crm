package com.example.crm.payload;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public interface GetGroupDto {

    String getName();
    UUID getId();
    Date getStartDate();
    LocalTime getStartTime();
}
