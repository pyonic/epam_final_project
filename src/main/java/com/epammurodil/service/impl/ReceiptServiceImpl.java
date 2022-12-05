package com.epammurodil.service.impl;

import com.epammurodil.constants.ProjectConstants;
import com.epammurodil.model.dao.impl.RatingsDao;
import com.epammurodil.model.dao.impl.ReceiptsDao;
import com.epammurodil.model.entity.Receipt;

import java.rmi.UnexpectedException;

public class ReceiptServiceImpl {
    public static final ReceiptServiceImpl instance = new ReceiptServiceImpl();
    private ReceiptServiceImpl() {}

    public static ReceiptServiceImpl getInstance() {
        return instance;
    }

    public boolean insert(int account_id, String description) throws UnexpectedException {
        Receipt receipt = new Receipt(account_id, description, ProjectConstants.DEFAULT_STATUS);
        return ReceiptsDao.getInstance().insert(receipt);
    }
    public boolean deleteOne(String id) throws UnexpectedException {
        int rid = Integer.parseInt(id);
        return RatingsDao.getInstance().delete(rid);
    }
}
