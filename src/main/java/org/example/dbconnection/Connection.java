package org.example.dbconnection;

import java.sql.*;

public class Connection {

   public static java.sql.Connection getConnection() {
        java.sql.Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?" + "user=root&password=Ajay@123");
        //    System.out.println("Successful Connection to Mysql ");
            statement=connection.createStatement();
            String userTable = "Create table users (email varchar(100) primary key,name varchar(100) not null,password varchar(100) not null , created_date DATE)";
            String questionTable = "Create table questions (qnid INTEGER primary key AUTO_INCREMENT,question varchar(500) not null,opt1 varchar(500) not null ,opt2 varchar(500) not null,opt3 varchar(500) not null,answer varchar(500) not null)";

            statement.executeUpdate(userTable);
            statement.executeUpdate(questionTable);
//            System.out.println("Table created");
        } catch (SQLException e) {
//            System.out.println("SQLException: " + e.getMessage());

        }

        return connection;

    }

}
