package com.lateration_app.example.infrastructure.persistence.dao;

import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.domain.port.ISatelliteDBPort;
import com.lateration_app.example.infrastructure.persistence.mapper.ISatelliteMapper;
import com.lateration_app.example.infrastructure.persistence.repository.ISatelliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SatelliteDao implements ISatelliteDBPort {

    private final ISatelliteRepository satelliteRepository;
    private final ISatelliteMapper mapper;

    @Override
    public Satellite findByName(String name) {
        return mapper.toSatellite(satelliteRepository.findByName(name));
    }

    @Override
    public Satellite save(Satellite satellite) {
        return mapper.toSatellite(satelliteRepository.save(mapper.fromSatellite(satellite)));
    }

    @Override
    public List<Satellite> findAll() {
        return mapper.toSatellites(satelliteRepository.findAll());
    }

}
