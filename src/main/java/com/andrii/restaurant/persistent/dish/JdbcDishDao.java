package com.andrii.restaurant.persistent.dish;

import com.andrii.restaurant.model.Dish;
import com.andrii.restaurant.persistent.NotFoundPersistenceException;
import com.andrii.restaurant.persistent.RestaurantPersistenceException;
import com.andrii.restaurant.persistent.dish.DishDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishDao implements DishDao {


    private static final String SQL_FIND_BY_ID = "SELECT * FROM dish WHERE product_code = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM dish";
    private static final String SQL_SAVE = "INSERT INTO dish (product_name, price, photo_id) VALUES (?, ?, ?)";
    private final DataSource dataSource;

    public JdbcDishDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Dish findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createDish(resultSet);
            } else {
                throw new NotFoundPersistenceException("Dish with id " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not find dish by id " + id, e);
        }
    }

    private Dish createDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("product_code"));
        dish.setName(resultSet.getString("product_name"));
        dish.setPrice(resultSet.getBigDecimal("price"));
        dish.setPhotoId(getInteger(resultSet, "photo_id"));
        return dish;
    }

    private Integer getInteger(ResultSet resultSet, String column) throws SQLException {
        int val = resultSet.getInt(column);
        return (resultSet.wasNull()) ? null : val;
    }

    @Override
    public Dish save(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setBigDecimal(2, dish.getPrice());
            preparedStatement.setObject(3, dish.getPhotoId());

            preparedStatement.executeUpdate();

            ResultSet generateKey = preparedStatement.getGeneratedKeys();
            if (generateKey.next()) {
                int id = generateKey.getInt(1);
                dish.setId(id);
                return dish;
            } else {
                throw new RestaurantPersistenceException("Can not generate id during saving dish " + dish);
            }
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not save dish " + dish, e);
        }
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> menu = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dish dish = createDish(resultSet);
                menu.add(dish);
            }
            return menu;
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not find dishes", e);
        }
    }

}
