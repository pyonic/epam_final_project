package com.epammurodil.service.impl;

import com.epammurodil.model.dao.impl.RatingsDao;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Rating;
import com.epammurodil.service.RatingService;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RatingServiceImpl implements RatingService {
    private static RatingServiceImpl instance = new RatingServiceImpl();

    private RatingServiceImpl() {};

    public static RatingServiceImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insertRating(int medicine_id, int rating, String body, int author_id) throws UnexpectedException {
        Rating rating1 = new Rating(medicine_id, author_id, rating, body);
        return RatingsDao.getInstance().insert(rating1);
    }

    @Override
    public List<Rating> getRatingsForMedicine(int medicine_id) throws UnexpectedException {
        return RatingsDao.getInstance().getRatingForMedicine(medicine_id);
    }

    public Map<String, Object> getRatingData(int medicine_id) {
        return RatingsDao.getInstance().getRating(medicine_id);
    }

    public boolean userHasRated(int medicine_id, int account_id) {
        return RatingsDao.getInstance().userRated(medicine_id, account_id);
    }
}
