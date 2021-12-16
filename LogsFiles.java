package com.company.logs;

import java.io.*;

public class LogsFiles {
    public LogsFiles() {
    }

    public void setFile(String str) {

    File file;
        file = new File("src/com/company/logs/logs.txt");

        try {
            FileOutputStream bos = new FileOutputStream(file, true);
            bos.write (str.getBytes());
            bos.write("\n".getBytes());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}






