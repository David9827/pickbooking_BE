package com.java.bookingcourt.repository;

import com.java.bookingcourt.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClusterCourtRepository extends JpaRepository<Court, Long> {

}
