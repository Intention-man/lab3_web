package com.app.server.model;

import com.app.server.db.DAO;
import com.google.gson.Gson;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Named;
import org.primefaces.shaded.json.JSONArray;

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
    private String resultsASJSONString;
    private final Gson gson;


    public ResultsBean() {
        dao = new DAO();
        results = new ArrayList<>();
        gson = new Gson();
        if (!dao.getAllResults().isEmpty()) {results = dao.getAllResults();}
    }

    public void addResult(Integer x, Float y, Float r) {
        ResultEntity point = new ResultEntity();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setInside();
        results.add(point);
        dao.addResult(point);
    }

    public List<ResultEntity> getResults() {
        results = dao.getAllResults();
        resultsASJSONString = gson.toJson(results);
        return results;
    }

    public String getResultsASJSONString() {
        return resultsASJSONString;
    }
}