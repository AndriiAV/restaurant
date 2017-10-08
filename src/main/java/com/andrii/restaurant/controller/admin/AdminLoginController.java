package com.andrii.restaurant.controller.admin;

import com.andrii.restaurant.controller.SessionHelper;
import com.andrii.restaurant.model.Admin;
import com.andrii.restaurant.services.AdminUserService;
import com.andrii.restaurant.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/login")
public class AdminLoginController extends HttpServlet{
    private final AdminUserService adminUserService = ServiceFactory.getInstance().getAdminUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/admin-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");

        Admin admin = adminUserService.findRegisteredAdmin(login, password);
        if (admin != null) {
            new SessionHelper(req).setCurrentAdmin(admin);

            resp.sendRedirect("/admin/confirmation");
        } else {
            req.setAttribute("illegalCredentialsError", true);
            req.getRequestDispatcher("/WEB-INF/admin/admin-login.jsp").forward(req, resp);
        }
    }
}
