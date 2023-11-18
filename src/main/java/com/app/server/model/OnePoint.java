package com.app.server.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


@ManagedBean
@SessionScoped
@Named("onePoint")
public class OnePoint implements Serializable{

    private long id;
    private int x;
    private float y;
    private float r;
    private boolean inside;
    private int type;
    private float size;

    public OnePoint() {}


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }


    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }


    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean isInside() {
        return inside;
    }
    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public float getSize() {
        return size;
    }
    public void setSize(float size) {
        this.size = size;
    }
}
