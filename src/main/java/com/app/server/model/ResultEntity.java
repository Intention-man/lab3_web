package com.app.server.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "hit_results", schema = "s367044")
@DiscriminatorColumn(name = "type")
public class ResultEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setInside(boolean inside) {
         this.inside = inside;
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