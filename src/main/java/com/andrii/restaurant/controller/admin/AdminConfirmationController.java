package com.andrii.restaurant.controller.admin;

import com.andrii.restaurant.controller.SessionHelper;
import com.andrii.restaurant.model.Order;
import com.andrii.restaurant.persistent.DaoFactory;
import com.andrii.restaurant.persistent.OrderDao;
import com.andrii.restaurant.services.AdminService;
import com.andrii.restaurant.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/confirmation")
public class AdminConfirmationController extends HttpServlet {

    private final AdminService adminService = ServiceFactory.getInstance().getAdminService();
    private final OrderDao orderDao = DaoFactory.getInstance().getOrderDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!new SessionHelper(req).isAdminLoggedIn()) {
            resp.sendRedirect("/admin/login");
            return;
        }

        List<Order> ordersWithConfirmation = orderDao.findOrdersWithConfirmation(false);

        req.setAttribute("orders", ordersWithConfirmation);
        req.getRequestDispatcher("/WEB-INF/admin/admin-confirmation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));

        adminService.confirmOrder(orderId);

        resp.sendRedirect("/admin/confirmation");
    }
}
