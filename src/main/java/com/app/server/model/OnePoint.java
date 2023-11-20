package com.app.server.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;


@ManagedBean
@ViewScoped
@Named("onePoint")
public class OnePoint implements Serializable{

    private long id;
    private int shapeType;
    private float size;
    private int x;
    private float y;
    private float r;
    private boolean inside;


    public OnePoint() {}


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getShapeType() {
        return shapeType;
    }
    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    public float getSize() {
        return size;
    }
    public void setSize(float size) {
        this.size = size;
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
}