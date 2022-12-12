package com.epam.murodil.service;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.entity.Rating;

import java.rmi.UnexpectedException;
import java.util.List;

public interface RatingService {
    boolean insertRating(int medicine_id, int rating, String body, int author_id) throws DaoException, ServiceException;
    List<Rating> getRatingsForMedicine(int medicine_id) throws UnexpectedException, DaoException;
}
