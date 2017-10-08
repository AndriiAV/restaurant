package com.andrii.restaurant.controller.session;

import com.andrii.restaurant.model.OrderItem;
import com.andrii.restaurant.model.Admin;
import com.andrii.restaurant.model.Cart;
import com.andrii.restaurant.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {

    private static final String CURRENT_CART = "currentCart";
    private static final String CURRENT_USER = "currentUser";
    private static final String CURRENT_ADMIN = "currentAdmin";

    private final HttpSession session;

    public SessionHelper(HttpServletRequest request) {
        this.session = request.getSession();
    }

    public void setCurrentUser(User user) {
        session.setAttribute(CURRENT_USER, user);
    }

    public void setCurrentAdmin(Admin admin) {
        session.setAttribute(CURRENT_ADMIN, admin);
    }

    public User getCurrentUser() {
        return (User) session.getAttribute(CURRENT_USER);
    }

    public Admin getCurrentAdmin() {
        return (Admin) session.getAttribute(CURRENT_ADMIN);
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public boolean isAdminLoggedIn() {
        return getCurrentAdmin() != null;
    }

    public Cart getCurrentCart() {
        Cart currentCart = (Cart) session.getAttribute(CURRENT_CART);
        if (currentCart == null) {
            currentCart = createNewCart();
            setCurrentCart(currentCart);
        }
        return currentCart;
    }

    public void clearCurrentCart() {
        session.setAttribute(CURRENT_CART, createNewCart());
    }

    public void addOrderItem(OrderItem orderItem) {
        Cart cart = getCurrentCart();
        if (cart == null) {
            cart = createNewCart();
        }
        cart.add(orderItem);
        setCurrentCart(cart);
    }

    private Cart createNewCart() {
        Long userId = getCurrentUser().getId();
        return new Cart(userId);
    }

    private void setCurrentCart(Cart cart) {
        session.setAttribute(CURRENT_CART, cart);
    }

    public void invalidate() {
        session.invalidate();

    }
}
