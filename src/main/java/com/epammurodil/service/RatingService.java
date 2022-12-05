package com.epammurodil.service;

import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Rating;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

public interface RatingService {
    boolean insertRating(int medicine_id, int rating, String body, int author_id) throws UnexpectedException;
    List<Rating> getRatingsForMedicine(int medicine_id) throws UnexpectedException;
}
