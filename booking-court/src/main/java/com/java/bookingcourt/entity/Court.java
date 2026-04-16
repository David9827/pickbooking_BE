package com.java.bookingcourt.entity;

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


    @ManyToOne
    @JoinColumn(name = "cluster_id", referencedColumnName = "clusterId")
    private Cluster cluster;

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
