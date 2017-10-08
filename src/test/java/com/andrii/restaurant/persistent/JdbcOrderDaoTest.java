package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.Order;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JdbcOrderDaoTest {
    @Test
    public void testFindById() throws Exception {
        OrderDao orderDao = new JdbcOrderDao((createDataSource()));

        Order order = orderDao.findById(1);

        Order expectedOrder = new Order()
                .setOrderId(1)
                .setUserId(1)
                .setOrderDate(LocalDateTime.now())
                .setOrderAmount(BigDecimal.valueOf(500));

        assertThat(order.getOrderId(), is(1));
        assertThat(order, is(expectedOrder));
    }

    private DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
