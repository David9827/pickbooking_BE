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
    private Long cluster_id;
    @Column(nullable = false)
    private Integer court_number;

    public enum Status{
        AVAILABLE, UNAVAILABLE
    }
    @Column(nullable = false)
    private boolean available;

    private String location;
    @Column(nullable = false)
    private String name;

}
