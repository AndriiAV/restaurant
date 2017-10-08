package com.andrii.restaurant.controller.user;

import com.andrii.restaurant.controller.session.SessionHelper;
import com.andrii.restaurant.model.User;
import com.andrii.restaurant.services.ServiceFactory;
import com.andrii.restaurant.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");

        User user = userService.findRegisteredUser(login, password);
        if (user != null) {
            new SessionHelper(req).setCurrentUser(user);

            resp.sendRedirect("/menu");
        } else {
            req.setAttribute("illegalCredentialsError", true);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
