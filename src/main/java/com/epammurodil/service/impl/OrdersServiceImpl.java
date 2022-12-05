package com.epammurodil.service.impl;


import com.epammurodil.constants.ProjectConstants;
import com.epammurodil.model.dao.impl.MedicineDao;
import com.epammurodil.model.dao.impl.OrdersDao;
import com.epammurodil.model.entity.Medicine;
import com.epammurodil.model.entity.Order;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public class OrdersServiceImpl {
    private OrdersServiceImpl() {}
    public static final OrdersServiceImpl instance = new OrdersServiceImpl();

    public static OrdersServiceImpl getInstance() {
        return instance;
    }

    public boolean userOrdered(int medicine_id, int account_id) {
        return OrdersDao.getInstance().userOrdered(medicine_id, account_id);
    }

    public boolean insert(int medicine_id, int account_id, BigDecimal dosage, BigDecimal amount, String delivery_address) throws SQLException, UnexpectedException {
        Medicine medicine = MedicineDao.getInstance().getOne(medicine_id);
        BigDecimal total_price = amount.multiply(medicine.getPrice());
        Order order = new Order(medicine_id, account_id, amount, dosage, total_price, delivery_address, ProjectConstants.DEFAULT_STATUS);
        boolean inserted = OrdersDao.getInstance().insert(order);
        return  inserted;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = OrdersDao.getInstance().getAllOrders();
        return orders;
    }

    public List<Order> getUserOrders(int account_id) {
        List<Order> orders = OrdersDao.getInstance().getUserOrders(account_id);
        return orders;
    }

    public boolean updateStatus(String order_id, String status) {
        return OrdersDao.getInstance().updateStatus(Integer.parseInt(order_id), status);
    }

    public boolean deleteById(int id) throws UnexpectedException {
        return OrdersDao.getInstance().delete(id);
    }

}
