package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Алексей", "Кудряшов",(byte) 24);
        us.saveUser("Дмитрий", "Шаповалов",(byte) 27);
        us.saveUser("Дмитрий", "Беляков",(byte) 29);
        us.saveUser("Михаил", "Латыпов",(byte) 39);
        List<User> list = us.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        us.removeUserById(3);
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}