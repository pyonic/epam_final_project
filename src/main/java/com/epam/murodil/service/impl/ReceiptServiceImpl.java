package com.epam.murodil.service.impl;

import com.epam.murodil.constants.ProjectConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.dao.impl.RatingsDao;
import com.epam.murodil.model.dao.impl.ReceiptsDao;
import com.epam.murodil.model.entity.Receipt;

import java.rmi.UnexpectedException;

public class ReceiptServiceImpl {
    public static final ReceiptServiceImpl instance = new ReceiptServiceImpl();
    private ReceiptServiceImpl() {}

    public static ReceiptServiceImpl getInstance() {
        return instance;
    }

    public boolean insert(int account_id, String description) throws DaoException {
        Receipt receipt = new Receipt(account_id, description, ProjectConstants.DEFAULT_STATUS);
        return ReceiptsDao.getInstance().insert(receipt);
    }
    public boolean deleteOne(String id) throws DaoException {
        int rid = Integer.parseInt(id);
        return RatingsDao.getInstance().delete(rid);
    }
}
