package org.example;

import org.example.dbconnection.Connection;
import org.example.model.Question;
import org.example.model.User;
import org.example.service.LoginService;
import org.example.service.QuestionCurd;
import org.example.service.UserCurd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
try {


    UserCurd userCurd = new UserCurd();
    QuestionCurd questionCurd = new QuestionCurd();
    LoginService loginService = new LoginService();

    Scanner scanner = new Scanner(System.in);
    System.out.println("----------------Welcome to Quiz portal---------------");
    int control = 9;
    while (control == 9) {
        System.out.println("Enter 1 to Create Account");
        System.out.println("Enter 2 to Login");
        System.out.println("Enter 3 to add questions");
        System.out.println("Enter 9 to exit");

        switch (scanner.nextInt()) {
            case 1:


                Boolean check = true;
                while (check) {
                    User user = new User();
                    System.out.println("Enter name - ");
                    user.setName(scanner.next());
                    System.out.println("Enter email -");
                    user.setEmail(scanner.next());
                    System.out.println("Enter password");
                    user.setPassword(scanner.next());
                    check = userCurd.createUser(user);

                }

            case 2:
                Boolean checkLogin = true;
                while (true) {
                    System.out.println("Enter email ---");
                    String email = scanner.next();
                    System.out.println("Enter password ---");
                    String password = scanner.next();
                    checkLogin = loginService.loginHandler(email, password);

                    if (checkLogin == true) {
                        continue;
                    }
                    break;
                }
                break;
            case 3:
                System.out.println("Enter number of question you want to add");
                int value = scanner.nextInt();
                for (int i = 0; i < value; i++) {
                    Question question = new Question();
                    System.out.println("Enter question");
                    scanner.nextLine();
                    String quest = scanner.nextLine();
                    System.out.println("Enter option 1");
                    String o2 = scanner.nextLine();

                    System.out.println("Enter option 2");
                    String o3 = scanner.nextLine();
                    System.out.println("Enter option 3");
                    String o4 = scanner.nextLine();
                    System.out.println("Enter corrent answer");
                    int o5 = scanner.nextInt();
                    question.setQuestion(quest);
                    question.setOption1(o2);
                    question.setOption1(o3);
                    question.setOption1(o4);
                    question.setAnswer(o5);
                    questionCurd.addQuestion(question);

                    break;
                }
            case 9:
                control = 91;
                break;
            default:
                System.out.println("Wrong input try again");
        }
    }
}
catch (InputMismatchException e){
    System.out.println(new InputMismatchException("Only integer values allowed re-run the application"));
}

    }
}