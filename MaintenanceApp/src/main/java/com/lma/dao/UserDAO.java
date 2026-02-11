package com.lma.dao;

import com.lma.model.User;

public interface UserDAO {

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    boolean login();
}
