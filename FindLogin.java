package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindLogin {

        public List<Users> findAll() {
            Connection connection = ConnectionDB.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM UsersChat");

                List<Users> students = new ArrayList<>();
                while (resultSet.next()) {
                    students.add(
                            new Users(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("login"),
                                    resultSet.getString("password")

                            )
                    );
                }

                return students;

            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            } finally {
                close(connection);
            }
        }

        public Optional<Users> findByNick(String login) {
            Connection connection = ConnectionDB.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM UsersChat WHERE login = " + login);

                if (resultSet.next()) {
                    return Optional.of(
                            new Users(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("login"),
                                    resultSet.getString("password")
                            )
                    );
                }

                return Optional.empty();

            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            } finally {
                close(connection);
            }
        }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}

