package com.app.server.db;

import com.app.server.model.OneRes;
import com.app.server.model.ResultEntity;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;

import java.util.List;

public interface DAOInterface {

    List<ResultEntity> getAllResults();

    void addResult(ResultEntity resultEntity);
}
