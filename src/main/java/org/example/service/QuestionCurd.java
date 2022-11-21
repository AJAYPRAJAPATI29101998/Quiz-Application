package org.example.service;

import org.example.dbconnection.Connection;
import org.example.model.Question;
import org.example.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class QuestionCurd implements QuestionOperations {

    @Override
    public String addQuestion(Question question) throws SQLException {

        String insertUserQuery = "INSERT INTO questions(question,opt1,opt2,opt3,answer) values(?,?,?,?)";

            PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(insertUserQuery);
            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setString(2, question.getOption1());
            preparedStatement.setString(3, question.getOption2());
            preparedStatement.setString(4,question.getOption3());
            preparedStatement.executeUpdate();
            return "User Saved!!!!";


    }
}
