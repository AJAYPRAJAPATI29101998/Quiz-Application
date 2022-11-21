package org.example.service;

import org.example.dbconnection.Connection;
import org.example.model.Question;
import org.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LoginService {
List<User> users = UserCurd.allPresentUser;

Scanner scanner = new Scanner(System.in);

    static List<Question> questions = new ArrayList<>();


    public LoginService() throws SQLException {
        System.out.println(users);
        Statement statement = Connection.getConnection().createStatement();
        String query = "SELECT * FROM questions";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {

            Question question = new Question(resultSet.getInt("qnid"),resultSet.getString("question"),
                    resultSet.getString("opt1"),resultSet.getString("opt2"),resultSet.getString("opt3"),
                    resultSet.getInt("answer"));
            questions.add(question);
        }

    }


public Boolean loginHandler(String email,String password) throws SQLException {
    User user = users.stream().filter(u->u.getEmail().equals(email)).findAny().orElse(null);
    if(user==null){
        System.out.println("Email is incorrect");
        return true;
    }
    if(!user.getPassword().equals(password)){
        System.out.println("Passowrd incorrect");
        return true;
    }

    System.out.println("LOgin Successfully ,Select the options ---- ");
    int runLoop = 8;
    while (runLoop==8) {
        System.out.println("Enter 1 to take the quiz");
        System.out.println("Enter 2 to view the previous score");
        System.out.println("Enter 3 to see your position in topers list");
        System.out.println("Enter 9 to Logout");
        switch (scanner.nextInt()) {
            case 1:
                int count = 0;
                for (int i = 0; i < questions.size(); i++) {
                    System.out.println(questions.get(i).getQuestion());
                    System.out.println(questions.get(i).getOption1());
                    System.out.println(questions.get(i).getOption2());
                    System.out.println(questions.get(i).getOption3());
                    System.out.println("Enter 1or2or3 for correct ans");
                    int ans = scanner.nextInt();
                    if (ans == questions.get(i).getAnswer()) {
                        count++;
                    }
                }
                String insertUserQuery = "UPDATE `quiz`.`users` SET `marks` = ? WHERE (`email` = ?);";
                PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(insertUserQuery);
                preparedStatement.setInt(1, count);
                preparedStatement.setString(2, email);

                preparedStatement.executeUpdate();

                break;
            case 2:
                System.out.println(user.getName() + "Last quiz marks-->" + user.getMarks());
                break;

            case 3:
                Collections.sort(users, (user1, t1) -> user1.getMarks() < t1.getMarks() ? 1 : -1);
                System.out.println("Name--------------------->Marks");
                users.forEach(e -> System.out.println(e.getName() + " ----------> " + e.getMarks()));
                break;
            case 9:
                runLoop = 9;
                break;
        }
    }
    return false;
}

}
