package com.epammurodil.model.dao.impl;

import static com.epammurodil.constants.QueryConstants.*;
import com.epammurodil.model.EntityQueries.MedicineQueries;
import com.epammurodil.model.dao.EntityDao;
import com.epammurodil.model.database.DatabaseConnection;
import com.epammurodil.model.entity.Medicine;
import com.epammurodil.libs.TableManager;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.*;

public class MedicineDao implements EntityDao<Medicine> {
    private static MedicineDao instance;

    private MedicineDao() {
    }

    public static MedicineDao getInstance() {
        if (instance == null) {
            instance = new MedicineDao();
        }
        return instance;
    }
    public List<Medicine> parseResult(ResultSet resultSet) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(ID);
            String title = resultSet.getString(TITLE);
            String description = resultSet.getString(DESCRIPTION);
            BigDecimal price = resultSet.getBigDecimal(PRICE);
            String slug = resultSet.getString(SLUG);
            Boolean need_receipt = resultSet.getBoolean(NEED_RECEIPT);
            Timestamp created_at = resultSet.getTimestamp(CREATED_AT);
            Timestamp updated_at = resultSet.getTimestamp(UPDATED_AT);
            Medicine m = new Medicine(id, title, description, price, slug, need_receipt, created_at, updated_at);
            medicines.add(m);
        }
        return medicines;
    }

    public Medicine getBySlug(String slug) throws SQLException {
        Medicine medicine =  null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(MedicineQueries.SELECT_ONE_BY_SLUG);
            preparedStatement.setString(1, slug);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Medicine> medicines = parseResult(resultSet);
            medicine = medicines.size() > 0 ? medicines.get(0) : null;
        }
        return medicine;
    }
    public List<Medicine> getList()  {
        List<Medicine> medicines = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(MedicineQueries.SELECT_ALL_ORDERED);
            medicines = parseResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicines;
    }

    public Medicine getOne(int id) throws SQLException {
        Medicine medicine = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(MedicineQueries.SELECT_ONE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Medicine> medicines = parseResult(resultSet);
            System.out.println(medicines);
            medicine = medicines.get(0);
        }
        return medicine;
    }

    @Override
    public boolean insert(Medicine medicine) throws UnexpectedException {
        if(findById(medicine.getId()).isPresent()) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println(MedicineQueries.INSERT_ONE);
            PreparedStatement preparedStatement = connection.prepareStatement(MedicineQueries.INSERT_ONE);
            preparedStatement.setString(1, medicine.getTitle());
            preparedStatement.setString(2, medicine.getDescription());
            preparedStatement.setBigDecimal(3, medicine.getPrice());
            preparedStatement.setString(4, medicine.getSlug());
            preparedStatement.setBoolean(5, medicine.getNeedReceipt());
            System.out.println("FFF: " + preparedStatement);
            int inserted = preparedStatement.executeUpdate();
            return inserted == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) throws UnexpectedException {
        try(Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(MedicineQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Medicine> findById(int id) throws UnexpectedException {
        Optional<Medicine> medicine = Optional.empty();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(MedicineQueries.SELECT_ONE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                medicine = Optional.ofNullable(parseResult(resultSet).get(0));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicine;
    }

    public List<Medicine> search(String filter) {
        List<Medicine> medicine = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(MedicineQueries.GENERATE_SEARCH_QUERY(filter));
            medicine = parseResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicine;
    }

    @Override
    public boolean update(Medicine medicine) throws UnexpectedException {
        if (findById(medicine.getId()).isPresent() == false) {
            return false;
        }
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(MedicineQueries.UPDATE_ONE);
            preparedStatement.setString(1, medicine.getTitle());
            preparedStatement.setString(2, medicine.getDescription());
            preparedStatement.setBigDecimal(3, medicine.getPrice());
            preparedStatement.setBoolean(4, medicine.getNeedReceipt());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
