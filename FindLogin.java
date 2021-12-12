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
//    public Optional<Student> findById(int id) {
//        Connection connection = DBConnection.getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT WHERE id = " + id);
//
//            if (resultSet.next()) {
//                return Optional.of(
//                        new Student(
//                                resultSet.getInt("id"),
//                                resultSet.getString("name"),
//                                resultSet.getString("surname")
//                        )
//                );
//            }
//
//            return Optional.empty();
//
//        } catch (SQLException throwables) {
//            throw new RuntimeException(throwables);
//        } finally {
//            close(connection);
//        }
//    }
//
//    public boolean update(Student student) {
//        Connection connection = DBConnection.getConnection();
//        try {
//            connection.setAutoCommit(false);
//
//            PreparedStatement statement = connection.prepareStatement("UPDATE STUDENT SET name = ?, surname = ? WHERE id = ?");
//            statement.setString(1, student.getName());
//            statement.setString(2, student.getSurname());
//            statement.setInt(3, student.getId());
////            UPDATE table_name
////            SET column1 = value1, column2 = value2, ...
////            WHERE condition;
//
//            boolean result = statement.execute();
//            connection.commit();
//            return result;
//
//        } catch (SQLException throwables) {
//            try {
//                connection.rollback();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            throw new RuntimeException(throwables);
//        } finally {
//            close(connection);
//        }
//    }
//
//    public boolean save(Student student) {
//        Connection connection = DBConnection.getConnection();
//        try {
//            connection.setAutoCommit(false);
//
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO STUDENT (name, surname) VALUES (?, ?)");
//            statement.setString(1, student.getName());
//            statement.setString(2, student.getSurname());
////            INSERT INTO table_name (column1, column2, column3, ...)
////            VALUES (value1, value2, value3, ...);
//
//            boolean result = statement.execute();
//            connection.commit();
//            return result;
//
//        } catch (SQLException throwables) {
//            try {
//                connection.rollback();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            throw new RuntimeException(throwables);
//        } finally {
//            close(connection);
//        }
//    }
