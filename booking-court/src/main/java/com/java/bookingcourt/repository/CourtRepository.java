package com.java.bookingcourt.repository;

import com.java.bookingcourt.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Long> {
    List<Court> findByCluster_ClusterId(Long clusterId);

}

