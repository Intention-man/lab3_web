package com.app.server.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("1")
public class RoundPointEntity extends ResultEntity{
    @Column
    private float radius;

    public RoundPointEntity(){}

    @Column(name = "radius")
    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
