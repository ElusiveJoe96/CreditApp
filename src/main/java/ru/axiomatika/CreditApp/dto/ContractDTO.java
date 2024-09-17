package ru.axiomatika.CreditApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ContractDTO {
    private Long id;
    private boolean signingStatus;
    private LocalDate signingDate;
    private RequestDTO requestDTO;
}
