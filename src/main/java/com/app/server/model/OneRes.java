package com.app.server.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@ManagedBean
@ApplicationScoped
@Named("oneResBean")
public class OneRes implements Serializable {
    private int x;
    private float y;
    private float r;
    private boolean inside;

    public OneRes() {
    }

    public OneRes(int x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
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

    public void setInside() {
        this.inside = (x >= 0 && y >= 0 && x <= r && y <= r/2 ) || (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) || (x >= 0 && y <= 0 && y >= x - r/2);
    }

    @Override
    public String toString() {
        return "Result{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                '}';
    }
}