package com.app.server.db;

import com.app.server.model.OneRes;

import java.util.List;

// class for work with database
public class DAO implements DAOInterface{

    public DAO(){}

    @Override
    public boolean openConnection() {
        return false;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    @Override
    public List<OneRes> getAllResults() {
        return null;
    }

    @Override
    public boolean addResult() {
        return false;
    }
}
