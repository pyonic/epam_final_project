package com.epam.murodil.service;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.entity.Account;

import java.rmi.UnexpectedException;
import java.util.Optional;

public interface AccountService {
    boolean insertAccount(String first_name, String last_name, String email, String phone, String password) throws DaoException;
    Optional<Account> findById(int userId) throws UnexpectedException;
}
