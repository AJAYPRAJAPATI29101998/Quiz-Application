package org.example.service;

import org.example.model.User;

import java.sql.SQLException;

public interface UserOperations {
    public Boolean createUser(User user) throws SQLException;

}
