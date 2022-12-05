package com.epammurodil.service;

import com.epammurodil.model.entity.Account;

import java.rmi.UnexpectedException;
import java.util.Optional;

public interface AccountService {
    boolean insertAccount(String first_name, String last_name, String email, String phone, String password) throws UnexpectedException;
    Optional<Account> findById(int userId) throws UnexpectedException;
}
