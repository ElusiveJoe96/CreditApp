package ru.axiomatika.CreditApp.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.axiomatika.CreditApp.model.Enum.MaritalStatus;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private String passportData;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private String address;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employment_info_id")
    private EmploymentInfo employmentInfo;

    private BigDecimal desiredCreditAmount;

    @OneToMany(mappedBy = "client")
    private Set<Request> requests;
}
