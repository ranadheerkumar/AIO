package essentials;
//Multiple Java Objects à Multiple JSON Files:

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDBResults_MultipeJavaObjects_MultipleJsonFiles {

    public static void main(String[] args) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
         // 1) Create a connection
   	 //   	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
   	Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
         // 2) Create statement/Query
         Statement stmt = con.createStatement();

         String s = "select * from customerinfo";

         // 3,4) Execute statement/Query & Store data in resultset
         ResultSet rs = stmt.executeQuery(s);

         ArrayList ar=new ArrayList();
        
         while (rs.next()) {
             String bookname = rs.getString("BookName");
             String purchasedate = rs.getString("PurchasedDate");
             int amount = rs.getInt("Amount");
             String location = rs.getString("Location");
            
             CustomerDetails cd=new CustomerDetails();
            
             cd.setBookname(bookname);
             cd.setPurchasedate(purchasedate);
             cd.setAmount(amount);
             cd.setLocation(location);
            
             ar.add(cd); // Adding all the objects to arraylist
         }

         //Using Jakson API, Converting all Java objects into JSON Files
        
         for(int i=0;i<ar.size();i++)
         {
             File jsonfile=new File(".\\datafiles\\custinfo"+i+".json");
            
             ObjectMapper om=new ObjectMapper();
             om.writeValue(jsonfile,ar.get(i));
         }
                 
         System.out.println("Done!");
   
         con.close();
        
    }

}