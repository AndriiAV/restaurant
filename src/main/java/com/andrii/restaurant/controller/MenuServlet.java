package com.andrii.restaurant.controller;

import com.andrii.restaurant.model.OrderItem;
import com.andrii.restaurant.model.Cart;
import com.andrii.restaurant.model.Dish;
import com.andrii.restaurant.persistent.DaoFactory;
import com.andrii.restaurant.persistent.DishDao;
import com.andrii.restaurant.persistent.JdbcDishDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {

    private final DishDao dishDao = DaoFactory.getInstance().getDishDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!new SessionHelper(req).isLoggedIn()) {
            resp.sendRedirect("/login");
            return;
        }

        List<Dish> menu = dishDao.findAll();
        Cart currentCart = new SessionHelper(req).getCurrentCart();

        List<Dish> menuForView = (currentCart != null) ? getNotOrderedDished(menu, currentCart) : menu;

        req.setAttribute("menu", menuForView);
        getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(req, resp);
    }

    private List<Dish> getNotOrderedDished(List<Dish> menu, Cart currentCart) {
        Set<Integer> dishIdToRemove = new HashSet<>();
        for (OrderItem orderItem : currentCart) {
            dishIdToRemove.add(orderItem.getDish().getId());
        }

        List<Dish> menuForView = new ArrayList<>();
        for (Dish dish : menu) {
            if (!dishIdToRemove.contains(dish.getId())) {
                menuForView.add(dish);
            }
        }
        return menuForView;
    }

    private List<Dish> createAllMenu() {
        DishDao dishDao = new JdbcDishDao(createDataSource());
        List<Dish> menu = dishDao.findAll();
        return menu;
    }

    private DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
