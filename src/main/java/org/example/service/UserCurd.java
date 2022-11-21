package org.example.service;

import org.example.dbconnection.Connection;
import org.example.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserCurd implements UserOperations {
   static List<User> allPresentUser = new ArrayList<>();


    public UserCurd() throws SQLException {
        Statement statement = Connection.getConnection().createStatement();
        String query = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            User user = new User(resultSet.getString("email"), resultSet.getString("name"),
                    resultSet.getString("password"),resultSet.getInt("marks"));
            user.setCreatedAt(resultSet.getDate("created_date").toLocalDate());
            allPresentUser.add(user);
        }

    }


    @Override
    public Boolean createUser(User user) throws SQLException {
        Boolean checkIfPresent = false;
        for (User checkUser : allPresentUser) {

            if (user.getEmail().equals(checkUser.getEmail())) {
                checkIfPresent = true;
                break;
            }
        }
        System.out.println(checkIfPresent);
        String insertUserQuery = "INSERT INTO users(email,name,password,created_date) values(?,?,?,?)";
        if (!checkIfPresent) {
            user.setCreatedAt(LocalDate.now());
            PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(insertUserQuery);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, Date.valueOf(user.getCreatedAt()));
            preparedStatement.executeUpdate();
            System.out.println( "User Saved!!!! Enter email and password to login");
            Statement statement = Connection.getConnection().createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user2 = new User(resultSet.getString("email"), resultSet.getString("name"),
                        resultSet.getString("password"),resultSet.getInt("marks"));
                user.setCreatedAt(resultSet.getDate("created_date").toLocalDate());
                allPresentUser.add(user2);
            }
            return false;
        }

        System.out.println( "User already present with email id - " + user.getEmail() +"   Try again");
        return true;
    }
}
