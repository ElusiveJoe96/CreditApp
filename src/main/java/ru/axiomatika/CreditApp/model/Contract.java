package ru.axiomatika.CreditApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean signingStatus;

    private LocalDate signingDate;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Request request;
}
