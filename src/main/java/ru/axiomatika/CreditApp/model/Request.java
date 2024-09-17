package ru.axiomatika.CreditApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    private BigDecimal approvedAmount;

    private boolean approvalStatus;

    private int termInDays;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private Contract contract;
}
