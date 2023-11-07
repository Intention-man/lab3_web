package com.app.server.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hit_results", schema = "s367044")
@ManagedBean
@ApplicationScoped
@Named("resultEntity")
public class ResultEntity {

    @Id
    @Column
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator"
    )
    private long id;

    @Column
    private int x;
    @Column
    private float y;
    @Column
    private float r;
    @Column
    private boolean inside;

    public ResultEntity(){}


    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "x")
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    @Column(name = "y")
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    @Column(name = "r")
    public float getR() {
        return r;
    }
    public void setR(float r) {
        this.r = r;
    }

    @Column(name = "inside")
    public boolean isInside() {
        return inside;
    }
    public void setInside() {
        this.inside = (x >= 0 && y >= 0 && x <= r && y <= r/2 ) || (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) || (x >= 0 && y <= 0 && y >= x - r/2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultEntity that)) return false;
        return getId() == that.getId() && Double.compare(getX(), that.getX()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getX());
    }

    @Override
    public String toString() {
        return "XBeanEntity{" +
                "id=" + id +
                ", x=" + x +
                '}';
    }
}