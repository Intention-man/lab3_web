package com.app.server.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("2")
public class SquarePointEntity extends ResultEntity{
    @Column
    private float length;

    public SquarePointEntity(){}

    @Column(name = "length")
    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
}
