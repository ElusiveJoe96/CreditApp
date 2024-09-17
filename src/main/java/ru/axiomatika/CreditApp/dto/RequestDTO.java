package ru.axiomatika.CreditApp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDTO {
    private Long id;
    private ClientDTO clientDTO;
    private BigDecimal approvedAmount;
    private boolean approvalStatus;
    private int termInDays;
}
