package utils;

import java.io.*;

import org.testng.annotations.Test;

public class command {
    public static void main(String[] args) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
     // "cmd.exe", "/c", "cd \"C:\\Program Files\\Microsoft SQL Server\" && dir");
      "cmd.exe", "/c", "cd D:\\Eclipse_Development\\Eclipse_workspace\\AIO && allure serve && allure generate");
        // && allure open
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}