package com.andrii.restaurant.persistent.order;

import com.andrii.restaurant.model.Order;
import com.andrii.restaurant.persistent.NotFoundPersistenceException;
import com.andrii.restaurant.persistent.RestaurantPersistenceException;
import com.andrii.restaurant.persistent.order.OrderDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderDao implements OrderDao {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
    private static final String SQL_SAVE = "INSERT INTO orders (user_id, date, amount, confirmed) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE orders SET user_id = ?, date = ?, amount = ?, confirmed = ? WHERE order_id = ?";
    private static final String SQL_FIND_BY_CONFIRMED = "SELECT * FROM orders WHERE confirmed = ?";
    private final DataSource dataSource;

    public JdbcOrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createOrder(resultSet);
            } else {
                throw new NotFoundPersistenceException("Order with id " + id + " not found");
            }

        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not find order by " + id + " id", e);
        }
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setOrderTimestamp(resultSet.getDate("date").getTime());
        order.setOrderAmount(resultSet.getBigDecimal("amount"));
        order.setConfirmed(resultSet.getBoolean("confirmed"));
        return order;
    }

    @Override
    public Order save(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(order.getOrderTimestamp()));
            preparedStatement.setBigDecimal(3, order.getOrderAmount());
            preparedStatement.setBoolean(4, order.isConfirmed());

            preparedStatement.executeUpdate();

            ResultSet generateKey = preparedStatement.getGeneratedKeys();
            if (generateKey.next()) {
                int id = generateKey.getInt(1);
                order.setOrderId(id);
                return order;
            } else {
                throw new RestaurantPersistenceException("Can not generate id during saving order " + order);
            }
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not save order " + order, e);
        }
    }

    @Override
    public void update(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(order.getOrderTimestamp()));
            preparedStatement.setBigDecimal(3, order.getOrderAmount());
            preparedStatement.setBoolean(4, order.isConfirmed());
            preparedStatement.setInt(5, order.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not update order " + order, e);
        }
    }

    @Override
    public List<Order> findOrdersWithConfirmation(boolean confirmed) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CONFIRMED);
            preparedStatement.setBoolean(1, confirmed);

            ResultSet resultSet = preparedStatement.executeQuery();

            return createOrders(resultSet);
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not find orders with confirmation " + confirmed, e);
        }
    }

    private List<Order> createOrders(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            orders.add(createOrder(resultSet));
        }
        return orders;
    }

//    private java.sql.Date toSqlDate(LocalDateTime localDateTime) {
//        Date javaDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        return new java.sql.Date(javaDate.getTime());
//    }
}
