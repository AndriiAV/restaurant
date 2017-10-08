package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.Dish;
import com.andrii.restaurant.persistent.dish.DishDao;
import com.andrii.restaurant.persistent.dish.JdbcDishDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JdbcDishDaoTest {

    @Test
    public void testFindByIdWithExistentDish() throws Exception {
        DishDao dishDao = new JdbcDishDao(createDataSource());

        Dish dish = dishDao.findById(1);

        Dish expectedDish = new Dish()
                .setName("Hawaii")
                .setPrice(BigDecimal.valueOf(117))
                .setPhotoId(null);

        assertThat(dish.getId(), is(1));
        assertThat(dish, is(expectedDish));
    }

    @Test(expected = NotFoundPersistenceException.class)
    public void testFindByIdWithNonexistentDish() throws Exception {
        DishDao dishDao = new JdbcDishDao(createDataSource());

        dishDao.findById(1000);
    }

    @Test (expected = RestaurantPersistenceException.class)
    public void testFindByIdWithIncorrectDataSource() throws Exception {
        DishDao dishDao = new JdbcDishDao(createIncorrectDataSource());
        dishDao.findById(1);

    }

    private DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    private DataSource createIncorrectDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant!!!!");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Test
    public void testSave() throws Exception {
        Dish dish = new Dish();
        dish.setName("New pizza")
        .setPrice(BigDecimal.valueOf(100))
        .setPhotoId(3);

        DishDao dishDao = new JdbcDishDao(createDataSource());
        Dish dishAfterSave = dishDao.save(dish);

        Dish foundedDish = dishDao.findById(dishAfterSave.getId());

        assertThat(foundedDish, is(dish));
    }

    @Test
    public void testFindAll() throws Exception {
        DishDao dishDao = new JdbcDishDao(createDataSource());

        List<Dish> menu = dishDao.findAll();
    }
}