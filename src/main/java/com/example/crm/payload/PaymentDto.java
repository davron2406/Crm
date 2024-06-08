package com.example.crm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private UUID userId;
    private UUID groupId;
    private float paymentAmount;
    private int month;
    private int year;
}
