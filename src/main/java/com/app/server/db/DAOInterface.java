package com.app.server.db;

import com.app.server.model.ResultEntity;

import java.util.List;

public interface DAOInterface {

    List<ResultEntity> getAllResults();

    void addResult(ResultEntity resultEntity);

    void clearResults();
}
