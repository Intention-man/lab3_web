package com.app.server.db;

import com.app.server.model.ResultEntity;
import com.app.server.model.RoundPointEntity;
import com.app.server.model.SquarePointEntity;

import java.util.List;

public interface DAOInterface {

    List<ResultEntity> getAllResults();

    void addResult(ResultEntity resultEntity);

    public void addCircle(RoundPointEntity round);

    public void addSquare(SquarePointEntity round);

    void clearResults();
}
