package com.epam.murodil.service.impl;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.dao.impl.MedicineDao;
import com.epam.murodil.model.entity.Medicine;
import com.github.slugify.Slugify;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public class MedicineServiceImpl {
    private static final Logger logger = LogManager.getLogger();
    private static MedicineServiceImpl instance = new MedicineServiceImpl();

    private MedicineServiceImpl() {};

    public static MedicineServiceImpl getInstance() {
        return instance;
    }

    public String insertMedicine(String title, String description, BigDecimal price, boolean need_receipt) throws ServiceException {
        Slugify slg = Slugify.builder().underscoreSeparator(true).build();
        String slug = slg.slugify(title);
        Medicine medicine = new Medicine(title, description, price, slug, need_receipt);
        MedicineDao medicineDao = MedicineDao.getInstance();
        try {
            medicineDao.insert(medicine);
        } catch (DaoException e) {
            logger.warn("Failed to insert a medicine {}", e.getMessage());
            throw new ServiceException("Failed to insert a medicine ", e);
        }
        return slug;
    }

    public List<Medicine> getList() throws ServiceException {
        try {
            List<Medicine> medicines = MedicineDao.getInstance().getList();
            return medicines;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get list of medicines ", e);
        }
    }

    public Medicine getBySlag(String slug) throws ServiceException {
        try {
            Medicine medicines = MedicineDao.getInstance().getBySlug(slug);
            return medicines;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get slug for medicines ", e);
        }
    }

    public List<Medicine> searchMedicine(String filter) throws DaoException {
        List<Medicine> medicines = MedicineDao.getInstance().search(filter);
        return medicines;
    }

    public boolean deleteOne(int medicine_id) throws DaoException {
        return MedicineDao.getInstance().delete(medicine_id);
    }
}
