package com.lateration_app.example.infrastructure.persistence.mapper;

import com.lateration_app.example.domain.model.Satellite;
import com.lateration_app.example.infrastructure.persistence.entity.SatelliteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ISatelliteMapper {

    SatelliteEntity fromSatellite(Satellite satellite);

    @InheritInverseConfiguration
    Satellite toSatellite(SatelliteEntity satelliteEntity);

    List<Satellite> toSatellites(List<SatelliteEntity> satellitesEntities);

}
