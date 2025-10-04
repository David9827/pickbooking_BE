package com.java.bookingcourt.service;

import com.java.bookingcourt.entity.Court;
import com.java.bookingcourt.repository.CourtRepository;
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
    public List<Court> getCourtsByClusterId(Long clusterId) {
        return courtRepo.findByCluster_ClusterId(clusterId);
    }
    public Court updateCourtStatus(Long courtId, boolean available) {
        // Tìm sân theo courtId
        Court court = courtRepo.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Sân không tồn tại"));

        // Cập nhật trạng thái
        court.setAvailable(available);

        // Lưu lại thông tin thay đổi vào cơ sở dữ liệu
        return courtRepo.save(court);
    }
}
