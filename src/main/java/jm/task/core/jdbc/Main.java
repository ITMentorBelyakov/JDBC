package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Алексей", "Кудряшов",(byte) 24);
        user.saveUser("Дмитрий", "Шаповалов",(byte) 27);
        user.saveUser("Дмитрий", "Беляков",(byte) 29);
        user.saveUser("Михаил", "Латыпов",(byte) 39);
        List<User> list = user.getAllUsers();
        for (User users : list) {
            System.out.println(users);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}