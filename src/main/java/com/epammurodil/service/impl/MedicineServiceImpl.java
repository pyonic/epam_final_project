package com.epammurodil.service.impl;

import com.epammurodil.model.dao.impl.MedicineDao;
import com.epammurodil.model.entity.Medicine;
import com.github.slugify.Slugify;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public class MedicineServiceImpl {
    private static MedicineServiceImpl instance = new MedicineServiceImpl();

    private MedicineServiceImpl() {};

    public static MedicineServiceImpl getInstance() {
        return instance;
    }

    public String insertMedicine(String title, String description, BigDecimal price, boolean need_receipt) {
        Slugify slg = Slugify.builder().underscoreSeparator(true).build();
        String slug = slg.slugify(title);
        Medicine medicine = new Medicine(title, description, price, slug, need_receipt);
        MedicineDao medicineDao = MedicineDao.getInstance();
        try {
            medicineDao.insert(medicine);
        } catch (UnexpectedException e) {
            throw new RuntimeException(e);
        }
        return slug;
    }

    public List<Medicine> getList() throws SQLException {
        List<Medicine> medicines = MedicineDao.getInstance().getList();
        return medicines;
    }

    public Medicine getBySlag(String slug) throws SQLException {
        Medicine medicines = MedicineDao.getInstance().getBySlug(slug);
        return medicines;
    }

    public List<Medicine> searchMedicine(String filter) throws SQLException {
        List<Medicine> medicines = MedicineDao.getInstance().search(filter);
        return medicines;
    }

    public boolean deleteOne(int medicine_id) throws UnexpectedException {
        return MedicineDao.getInstance().delete(medicine_id);
    }
}
