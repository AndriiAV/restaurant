package com.andrii.restaurant.services.user;

import com.andrii.restaurant.model.User;
import com.andrii.restaurant.persistent.user.UserDao;

public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        User userWithLogin = userDao.findByLogin(user.getLogin());
        if (userWithLogin != null) {
            throw new UserAlreadyRegisteredException(user.getLogin());
        }

        userDao.save(user);
    }

    @Override
    public User findRegisteredUser(String login, String password) {
        User userByLogin = userDao.findByLogin(login);
        boolean isRegistered = userByLogin != null && userByLogin.getPassword().equals(password);
        return isRegistered ? userByLogin : null;
    }

    @Override
    public void delete(int id) {

    }
}
