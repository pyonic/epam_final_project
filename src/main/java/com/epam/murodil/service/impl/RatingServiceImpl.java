package com.epam.murodil.service.impl;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.dao.impl.RatingsDao;
import com.epam.murodil.model.entity.Rating;
import com.epam.murodil.service.RatingService;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Map;

public class RatingServiceImpl implements RatingService {
    private static RatingServiceImpl instance = new RatingServiceImpl();

    private RatingServiceImpl() {};

    public static RatingServiceImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insertRating(int medicine_id, int rating, String body, int author_id) throws ServiceException {
        try {
            Rating rating1 = new Rating(medicine_id, author_id, rating, body);
            return RatingsDao.getInstance().insert(rating1);
        } catch (Exception e) {
            throw new ServiceException("Failed to insert rating ", e);
        }
    }

    @Override
    public List<Rating> getRatingsForMedicine(int medicine_id) throws DaoException {
        return RatingsDao.getInstance().getRatingForMedicine(medicine_id);
    }

    public Map<String, Object> getRatingData(int medicine_id) {
        return RatingsDao.getInstance().getRating(medicine_id);
    }

    public boolean userHasRated(int medicine_id, int account_id) {
        return RatingsDao.getInstance().userRated(medicine_id, account_id);
    }
}
