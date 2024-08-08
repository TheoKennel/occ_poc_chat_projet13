package com.backend.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @Column(nullable = false, precision = 10, scale = 2)
    private double amount;

    @Column(nullable = false)
    private Date date;

    @Column(length = 50)
    private String status;
}

