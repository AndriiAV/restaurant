package com.andrii.restaurant.controller.user;

import com.andrii.restaurant.controller.session.SessionHelper;
import com.andrii.restaurant.model.User;
import com.andrii.restaurant.services.ServiceFactory;
import com.andrii.restaurant.services.user.UserAlreadyRegisteredException;
import com.andrii.restaurant.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = createUserFromReqParams(req);
            userService.register(user);
            new SessionHelper(req).setCurrentUser(user);

            resp.sendRedirect("/menu");
        } catch (UserAlreadyRegisteredException e) {
            req.setAttribute("alreadyRegisteredError", true);
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        }
    }

    private User createUserFromReqParams(HttpServletRequest req) {
        return new User()
                .setLogin(req.getParameter("login"))
                .setPassword(req.getParameter("psw"))
                .setUserName(req.getParameter("user_name"))
                .setPhoneNumber(req.getParameter("phone_number"))
                .setAddress(req.getParameter("address"));
    }
}
