package com.paymentapp.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="course_order")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String name;
    private String email;
    private String phone;
    private String course;
    private double amount;
    private String orderStatus;
    private String razorpayOrderId;
}
