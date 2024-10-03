package com.lateration_app.example.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "SATELLITES")
public class SatelliteEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "position_x")
    private Double positionX;

    @Column(name = "position_y")
    private Double positionY;

    @Column(name = "distance")
    private Double distance;

    @ElementCollection
    @OrderColumn
    private String[] message;

}
