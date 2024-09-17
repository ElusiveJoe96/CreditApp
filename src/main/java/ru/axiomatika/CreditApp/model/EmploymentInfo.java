package ru.axiomatika.CreditApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employment_info")
public class EmploymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String organizationName;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
