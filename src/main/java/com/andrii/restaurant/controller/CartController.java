package com.andrii.restaurant.controller;

import com.andrii.restaurant.model.OrderItem;
import com.andrii.restaurant.model.Cart;
import com.andrii.restaurant.model.Dish;
import com.andrii.restaurant.persistent.DaoFactory;
import com.andrii.restaurant.persistent.DishDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/cart")
public class CartController extends HttpServlet {

    private final DishDao dishDao = DaoFactory.getInstance().getDishDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = new SessionHelper(req).getCurrentCart();

        req.setAttribute("orderItems", cart.getOrderItems());
        req.getRequestDispatcher("/WEB-INF/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidationResult validationResult = validate(req);
        if (validationResult.isError()) {
            String errorMessage = URLEncoder.encode(validationResult.getErrorMessage(), "UTF-8");
            resp.sendRedirect("/menu?error=" + errorMessage);
            return;
        }

        OrderItem orderItem = createOrderItemFromReqParams(req);

        new SessionHelper(req).addOrderItem(orderItem);

        resp.sendRedirect("/menu");
    }

    private ValidationResult validate(HttpServletRequest req) {
        String countStr = req.getParameter("count");
        if (!ValidationUtils.isInt(countStr)) {
            return ValidationResult.error("count is not int");
        }

        int count = Integer.parseInt(countStr);
        if (count <= 0) {
            return ValidationResult.error("negative count: " + count);
        }
        if (count > 100) {
            return ValidationResult.error("too big count: " + count);
        }
        return ValidationResult.success();
    }

    private OrderItem createOrderItemFromReqParams(HttpServletRequest req) {
        int dishId = Integer.parseInt(req.getParameter("dishId"));
        int count =  Integer.parseInt(req.getParameter("count"));

        Dish dish = dishDao.findById(dishId);

        return new OrderItem(dish, count);
    }
}
