package net.javaguides.sms.service;


import net.javaguides.sms.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User newUser);

        User createAdmin(User newAdmin);

        List<User> findAllUsers();

 }

