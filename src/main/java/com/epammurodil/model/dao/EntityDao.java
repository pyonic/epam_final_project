package com.epammurodil.model.dao;

import com.epammurodil.model.entity.AbstractEntity;

import java.rmi.UnexpectedException;
import java.util.Optional;

public interface EntityDao<T extends AbstractEntity> {
    boolean insert(T t) throws UnexpectedException;

    boolean delete(int id) throws UnexpectedException;

    Optional<T> findById(int id) throws UnexpectedException;

    boolean update(T t) throws UnexpectedException;
}
