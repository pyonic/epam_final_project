package com.epam.murodil.model.dao;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.entity.AbstractEntity;

import java.rmi.UnexpectedException;
import java.util.Optional;

public interface EntityDao<T extends AbstractEntity> {
    boolean insert(T t) throws DaoException;

    boolean delete(int id) throws DaoException;

    Optional<T> findById(int id) throws DaoException;

    boolean update(T t) throws DaoException;
}
