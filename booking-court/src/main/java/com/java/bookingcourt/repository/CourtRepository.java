package com.java.bookingcourt.repository;

import com.java.bookingcourt.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}

