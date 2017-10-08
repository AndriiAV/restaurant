package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.User;
import com.andrii.restaurant.persistent.user.JdbcUserDao;
import com.andrii.restaurant.persistent.user.UserDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Test;

import javax.sql.DataSource;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JdbcUserDaoTest {

    @Test
    public void testFindById() throws Exception {
        UserDao userDao = new JdbcUserDao(createDataSource());

        User user = userDao.findById(1L);

        User expectedUser = new User()
            .setId(1L)
            .setLogin("First_user")
            .setUserName("First User")
            .setPhoneNumber("(067)123-45-67")
            .setAddress("Kyiv City, Peremogy str, 45, ap., 13");

        assertThat(user.getId(), is(1L));
        assertThat(user, is(expectedUser));
    }

    @Test(expected = NotFoundPersistenceException.class)
    public void testFindByIdWithNonexistentUser() throws Exception {
        UserDao userDao = new JdbcUserDao(createDataSource());

        userDao.findById(100L);
    }


    @Test
    public void testSave() throws Exception {
        User user = new User();
        Random random = new Random();
        int addIndex = random.nextInt();
            user.setLogin("test"+addIndex)
                .setUserName("Test")
                .setPhoneNumber("123456789")
                .setAddress("London");

        UserDao userDao = new JdbcUserDao(createDataSource());
        User userAfterSave = userDao.save(user);

        User foundedUser = userDao.findById(userAfterSave.getId());

        assertThat(foundedUser, is(user));
    }

    private DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
