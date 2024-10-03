package com.lateration_app.example.domain.port;

import com.lateration_app.example.domain.model.Satellite;

import java.util.List;

public interface ISatelliteDBPort {

    Satellite findByName(String name);

    Satellite save(Satellite satellite);

    List<Satellite> findAll();

}
