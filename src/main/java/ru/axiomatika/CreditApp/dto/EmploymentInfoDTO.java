package ru.axiomatika.CreditApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmploymentInfoDTO {
    private Long id;

    @NotBlank(message = "Название организации не может быть пустым")
    @Size(max = 100, message = "Название организации не может превышать 100 символов")
    private String organizationName;

    @NotBlank(message = "Занимаемая должность не может быть пустой")
    @Size(max = 50, message = "Занимаемая должность не может превышать 50 символов")
    private String jobTitle;

    @NotNull(message = "Дата начала работы не может быть пустой")
    private LocalDate startDate;

    private LocalDate endDate;

    public boolean isValid() {
        return endDate == null || endDate.isAfter(startDate);
    }
}
