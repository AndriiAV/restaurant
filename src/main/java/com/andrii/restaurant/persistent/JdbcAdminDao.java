package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.Admin;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcAdminDao implements AdminDao {

    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM admin WHERE login = ?";

    private final DataSource dataSource;
    private final static Logger logger = Logger.getLogger(JdbcAdminDao.class);

    public JdbcAdminDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Admin findByLogin(String login) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next() ? createAdmin(resultSet) : null;
        } catch (SQLException e) {
            logger.error("error: " + "admin by login " + login + " was not found");
            throw new RestaurantPersistenceException("Can not find admin by login " + login, e);
        }
    }

    private Admin createAdmin(ResultSet resultSet) throws SQLException {
        return new Admin()
                .setId(resultSet.getLong("admin_id"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("psw"));
    }
}
