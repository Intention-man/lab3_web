package com.app.server.model;

import com.app.server.db.DAO;
import com.app.server.db.DAOInterface;
import com.google.gson.Gson;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Named;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@ApplicationScoped
@Named("resultsBean")
public class ResultsBean implements Serializable {

//    @ManagedProperty(value = "#{onePoint}")
//    private OnePoint onePoint;

    private final DAOInterface dao;
    private List<OnePoint> results;
    private String resultsASJSONString;
    private final Gson gson;


    public ResultsBean() {
        dao = new DAO();
        gson = new Gson();
        results = getResults();
    }

    public void addResult(OnePoint onePoint) {
        onePoint.setInside(isInside(onePoint.getX(), onePoint.getY(), onePoint.getR()));

        if (onePoint.getShapeType() == 1){addCircle(onePoint);}
        else {addSquare(onePoint);}

        results.add(onePoint);
    }

    public void addCircle(OnePoint point){
        RoundPointEntity entity = new RoundPointEntity();
        entity.setX(point.getX());
        entity.setY(point.getY());
        entity.setR(point.getR());
        entity.setInside(point.isInside());
        entity.setRadius(point.getSize());
        dao.addCircle(entity);
    }

    public void addSquare(OnePoint point){
        SquarePointEntity entity = new SquarePointEntity();
        entity.setX(point.getX());
        entity.setY(point.getY());
        entity.setR(point.getR());
        entity.setInside(point.isInside());
        entity.setLength(point.getSize());
        dao.addSquare(entity);
    }

    public List<OnePoint> getResults() {
        List<RoundPointEntity> circlesList = dao.getAllCircles();
        List<SquarePointEntity> squaresList = dao.getAllSquares();
        results = Stream.concat(circlesList.stream().map(this::circlesEntityToBean), squaresList.stream().map(this::squaresEntityToBean)).collect(Collectors.toCollection(ArrayList::new));;
        resultsASJSONString = !results.isEmpty() ? gson.toJson(results) : "[]";
        return results;
    }

    public String getResultsASJSONString() {
        return resultsASJSONString;
    }

    public OnePoint circlesEntityToBean(RoundPointEntity result){
        OnePoint onePoint = createNewBeanFromEntity(result.getId(), result.getX(), result.getY(), result.getR(), result.isInside());
        onePoint.setShapeType(1);
        onePoint.setSize(result.getRadius());
        return onePoint;
    }

    public OnePoint squaresEntityToBean(SquarePointEntity result){
        OnePoint onePoint = createNewBeanFromEntity(result.getId(), result.getX(), result.getY(), result.getR(), result.isInside());
        onePoint.setShapeType(2);
        onePoint.setSize(result.getLength());
        return onePoint;
    }

    private OnePoint createNewBeanFromEntity(long id, int x, float y, float r, boolean inside) {
        OnePoint onePoint = new OnePoint();
        onePoint.setId(id);
        onePoint.setX(x);
        onePoint.setY(y);
        onePoint.setR(r);
        onePoint.setInside(inside);
        return onePoint;
    }

    public boolean isInside(int x, float y, float r) {
        return (x >= 0 && y >= 0 && x <= r && y <= r/2 ) || (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) || (x >= 0 && y <= 0 && y >= x - r/2);
    }
}