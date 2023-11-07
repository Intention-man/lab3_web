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

    @ManagedProperty(value = "#{resultEntity}")
    private ResultEntity resultEntity;

    private final DAO dao;
    private List<ResultEntity> results;


    public ResultsBean() {
        dao = new DAO();
        results = new ArrayList<>();
        if (!dao.getAllResults().isEmpty()) {results = dao.getAllResults();}
    }

    public void addResult(Integer x, Float y) {
        float r = 0;

        ResultEntity point = new ResultEntity();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setInside();
        results.add(point);

        dao.addResult(point);
//        System.out.println(resultEntity.getX() + " " + resultEntity.getY());
    }

    public List<ResultEntity> getResults() {
        results = dao.getAllResults();
        return results;
    }
}