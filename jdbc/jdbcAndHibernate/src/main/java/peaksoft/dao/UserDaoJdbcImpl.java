package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.type.UrlType;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.security.auth.login.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar," +
                "lastName varchar," +
                "age int);";
        try (Connection connection = Util.connectToDataBase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("user table is create!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users;";
        try (Connection connection = Util.connectToDataBase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("user table is delete...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name, lastName, age) values (?,?,?)";
        try (Connection connection = Util.connectToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name + " is saved.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (Connection connection = Util.connectToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("user is deleted...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users";
        try (Connection connection = Util.connectToDataBase();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        (byte) resultSet.getInt("age")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;

    }

    public void cleanUsersTable() {
        String sql = "truncate table users";
        try (Connection connection = Util.connectToDataBase();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table is cleaned.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}