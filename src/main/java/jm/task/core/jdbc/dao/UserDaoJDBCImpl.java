package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.open();
        Statement statement = connection.createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY NOT NULL,
                    name VARCHAR(30) NOT NULL ,
                    last_name VARCHAR(30) NOT NULL,
                    age SMALLINT NOT NULL
)
                    """;
            statement.executeUpdate(sql);
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.open();
        Statement statement = connection.createStatement()) {
            String sql = """
                    DROP TABLE IF EXISTS users;
                    """;
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                    INSERT INTO users (name, last_name, age)
                    VALUES (?, ?, ?)
                    """;
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " сохранен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = """
                    DELETE FROM users WHERE id = ?;
                    """;
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + id + " удален!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = """
                    SELECT id, name, last_name, age FROM users;
                    """;
        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAge(resultSet.getByte("age"));
            users.add(user);
            }
            System.out.println("Лист заполнена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = """
                    TRUNCATE TABLE users;
                    """;
        try (Connection connection = Util.open();
             Statement Statement = connection.createStatement()) {
            Statement.executeUpdate(sql);
            System.out.println("Таблица очищена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}