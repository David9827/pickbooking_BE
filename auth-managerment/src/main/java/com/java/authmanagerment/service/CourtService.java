package com.java.authmanagerment.service;

import com.java.authmanagerment.entity.Court;
import com.java.authmanagerment.repository.CourtRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourtService {
    private final CourtRepository courtRepo;

    public CourtService(CourtRepository courtRepo) {
        this.courtRepo = courtRepo;
    }

    public List<Court> getAllCourts() {
        return courtRepo.findAll();
    }

    public Court createCourt(Court court) {
        return courtRepo.save(court);
    }

    public void deleteCourt(Long id) {
        courtRepo.deleteById(id);
    }
}
