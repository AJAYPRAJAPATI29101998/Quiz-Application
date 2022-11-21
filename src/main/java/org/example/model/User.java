package org.example.model;

import java.time.LocalDate;

public class User {
    private String email;
    private String name;

    private String password;

    private LocalDate createdAt;

    private int marks;

    public User() {
    }

    public User(String email, String name, String password,int marks) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.marks=marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10s%-10s",email,name,password,createdAt);
    }
}
