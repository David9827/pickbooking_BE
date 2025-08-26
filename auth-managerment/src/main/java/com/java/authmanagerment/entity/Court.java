package com.java.authmanagerment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courts")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courtId;

    @Column(nullable = false)
    private String name;

    private String location;

    private boolean available = true; // còn trống hay đã được đặt

    private boolean court_number;
}
