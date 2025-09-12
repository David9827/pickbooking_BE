package com.java.bookingcourt.controller;

import com.java.bookingcourt.entity.Court;
import com.java.bookingcourt.service.CourtService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/courts")
@CrossOrigin(origins = "*")
public class CourtController {
    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public List<Court> getAllCourts() {
        return courtService.getAllCourts();
    }

    @PostMapping
    public Court createCourt(@RequestBody Court court) {
        return courtService.createCourt(court);
    }

    @DeleteMapping("/{id}")
    public void deleteCourt(@PathVariable Long id) {
        courtService.deleteCourt(id);
    }
}
