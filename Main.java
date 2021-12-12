package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        FindLogin findLogin = new FindLogin();
      System.out.println(findLogin.findAll());
     System.out.println(findLogin.findByNick("login1"));
    }

}

