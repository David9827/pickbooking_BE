package com.java.bookingcourt.controller;


import com.java.bookingcourt.entity.Cluster;
import com.java.bookingcourt.service.ClusterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/clusters")
@CrossOrigin(origins = "*")
public class ClustersController {
    private final ClusterService clusterService;

    public ClustersController(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @GetMapping
    public List<Cluster> getAllClusters() {
        return clusterService.getAllClusters();
    }
    @PostMapping
    public Cluster createCluster(@RequestBody Cluster cluster){
        return clusterService.createCluster(cluster);
    }

    @DeleteMapping("/{id}")
    public void deleteCluster(@PathVariable Long id) {
        clusterService.deleteCluster(id);
    }

}
