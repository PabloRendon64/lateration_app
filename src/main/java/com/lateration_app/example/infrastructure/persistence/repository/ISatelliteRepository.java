package com.lateration_app.example.infrastructure.persistence.repository;

import com.lateration_app.example.infrastructure.persistence.entity.SatelliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISatelliteRepository extends JpaRepository<SatelliteEntity, String> {

    SatelliteEntity findByName(String name);

}
