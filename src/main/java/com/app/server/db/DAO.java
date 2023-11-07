package com.app.server.db;

import com.app.server.model.ResultEntity;
import com.app.server.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;

import java.util.List;

// class for work with database
public class DAO implements DAOInterface{
    private final EntityManager entityManager = JPAUtil.getFactory().createEntityManager();

    public DAO(){}

    @Override
    public List<ResultEntity> getAllResults() {
        var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(ResultEntity.class);
        Root<ResultEntity> root = criteriaQuery.from(ResultEntity.class);
        return entityManager.createQuery(criteriaQuery.select(root)).getResultList();
    }

    @Override
    public void addResult(ResultEntity resultEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(resultEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void clearResults() {
        entityManager.clear();
    }
}