package org.example.service;

import org.example.model.Question;

import java.sql.SQLException;

public interface QuestionOperations {
    public String addQuestion(Question question) throws SQLException;
}
