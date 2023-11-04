package com.app.server.db;

import com.app.server.model.OneRes;

import java.util.List;

public interface DAOInterface {

    boolean openConnection();
    boolean closeConnection();
    List<OneRes> getAllResults();

    boolean addResult();
}
