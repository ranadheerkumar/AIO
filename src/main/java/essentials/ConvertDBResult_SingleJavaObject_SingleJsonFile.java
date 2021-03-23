package essentials;

//Single Java Object à Single JSON File:

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDBResult_SingleJavaObject_SingleJsonFile{

    public static void main(String[] args) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
         // 1) Create a connection
    	 //   	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
    	Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
         // 2) Create statement/Query
         Statement stmt = con.createStatement();

         String s = "select * from customerinfo  limit 1";

         // 3,4) Execute statement/Query & Store data in resultset
         ResultSet rs = stmt.executeQuery(s);

         CustomerDetails cd=new CustomerDetails();
        
         while (rs.next()) {
             String bookname = rs.getString("BookName");
             String purchasedate = rs.getString("PurchasedDate");
             int amount = rs.getInt("Amount");
             String location = rs.getString("Location");
            
             cd.setBookname(bookname);
             cd.setPurchasedate(purchasedate);
             cd.setAmount(amount);
             cd.setLocation(location);
         }

         // 5) close connection
         con.close();
                 
         // Using Jakson API, Converting Java object into JSON File
         File jsonfile=new File(".\\datafiles\\custinfo.json");
        
         ObjectMapper om=new ObjectMapper();
         om.writeValue(jsonfile, cd);
        
         System.out.println("Done!");
        
    }

}

