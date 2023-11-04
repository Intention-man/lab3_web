package com.app.server.model;

import com.app.server.db.DAO;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Named("resultsBean")
public class ResultsBean implements Serializable {

    @ManagedProperty(value = "#{oneRes}")
    private OneRes oneRes;

    private DAO db;
    private List<OneRes> results;


    public ResultsBean() {
        results = new ArrayList<>();
        db = new DAO();
    }

    public void addResult(Integer x, Float y) {
        oneRes = new OneRes(x, y, 0);
        oneRes.setInside();
        results.add(oneRes);
        System.out.println(oneRes.getX() + " " + oneRes.getY());
    }

    public List<OneRes> getResults() {
        //results = db.getAllResults();
        return results;
    }
}