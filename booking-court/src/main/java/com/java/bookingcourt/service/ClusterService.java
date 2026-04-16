package com.java.bookingcourt.service;

import com.java.bookingcourt.entity.Cluster;
import com.java.bookingcourt.repository.ClusterRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClusterService {
    private final ClusterRepository clusterRepo;

    public ClusterService(ClusterRepository clusterRepo) {
        this.clusterRepo = clusterRepo;
    }

    public List<Cluster> getAllClusters() {
        return clusterRepo.findAll();
    }

    public Cluster createCluster(Cluster cluster) {
        return clusterRepo.save(cluster);
    }

    public void deleteCluster(Long id) {
        clusterRepo.deleteById(id);
    }
}
