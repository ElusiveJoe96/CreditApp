package ru.axiomatika.CreditApp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.axiomatika.CreditApp.model.Enum.MaritalStatus;

import java.math.BigDecimal;

@Data
public class ClientDTO {
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @NotBlank(message = "Отчество не может быть пустым")
    private String middleName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Некорректные паспортные данные")
    private String passportData;

    @NotNull(message = "Выберите семейное положение")
    private MaritalStatus maritalStatus;

    @NotBlank(message = "Адрес не может быть пустым")
    private String address;

    @Pattern(regexp = "^[0-9]{10}$", message = "Некорректный номер телефона")
    private String phoneNumber;

    private EmploymentInfoDTO employmentInfoDTO;

    @DecimalMin(value = "10000.0", inclusive = false, message = "Сумма кредита не может быть меньше 10,000")
    @DecimalMax(value = "100000000.0", message = "Сумма кредита не может превышать 100,000,000")
    @NotNull(message = "Необходимо указать сумму")
    private BigDecimal desiredCreditAmount;

}
