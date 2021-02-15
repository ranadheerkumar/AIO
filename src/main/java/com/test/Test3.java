package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Test3 {
	
	@Test
	public void main(String[] args)throws Exception {
		
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
	


