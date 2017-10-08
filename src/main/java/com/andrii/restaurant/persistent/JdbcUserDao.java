package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUserDao implements UserDao {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_SAVE = "INSERT INTO users (login, user_name, phone_number, address, psw) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    private final DataSource dataSource;
    private final static Logger logger = Logger.getLogger(JdbcUserDao.class);

    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(long userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createUser(resultSet);
            } else {
                throw new NotFoundPersistenceException("User with id " + userId + " not found");
            }
        } catch (SQLException e) {
            logger.error("error: " + "user by ib " + userId + " was not found");
            throw new RestaurantPersistenceException("Can not find user by id " + userId, e);
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User()
                .setId(resultSet.getLong("user_id"))
                .setLogin(resultSet.getString("login"))
                .setUserName(resultSet.getString("user_name"))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setAddress(resultSet.getString("address"))
                .setPassword(resultSet.getString("psw"));
    }

    @Override
    public User save(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet generateKey = preparedStatement.getGeneratedKeys();
            if (generateKey.next()) {
                long id = generateKey.getLong(1);
                user.setId(id);
                return user;
            } else {
                throw new RestaurantPersistenceException("Can not generate id during saving user " + user);
            }
        } catch (SQLException e) {
            throw new RestaurantPersistenceException("Can not save user " + user, e);
        }
    }

    @Override
    public User findByLogin(String login) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            logger.error("error: " + "user by login " + login + " was not found");
            throw new RestaurantPersistenceException("Can not find user by login " + login, e);
        }
    }

}
