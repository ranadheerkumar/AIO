package essentials;

//Multiple Java Objects à Single JSON (Along with JSON Formatting):

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ConvertDBResults_MultipleJavaObjects_SingleJsonFile {

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
//Jackson Databind,Jackson Core,Jackson Annotations
         //Using Jakson API, Converting all Java objects into JSON Files
        
         JSONArray jsonr=new JSONArray(); //Required to add JSon string to Json array
        
         for(int i=0;i<ar.size();i++)
         {
             //File jsonfile=new File("C:\\Users\\admin\\eclipse-workspace\\sdet\\custinfo"+i+".json");
             //ObjectMapper om=new ObjectMapper();
             //om.writeValue(jsonfile,ar.get(i));
            
             Gson g=new Gson();
             String JsonString=g.toJson(ar.get(i)); //Converts Java object into JSON String
            
             jsonr.add(JsonString); //Add JSon String to Json Array
         }
        
         JSONObject jo=new JSONObject();
         jo.put("data",jsonr);
        
         System.out.println("Initialformat:  "+jo.toJSONString()); // added escape chars That's not right format
   
         String jsonFormattedString = jo.toJSONString().replace("\\\"", "\""); //removed escape chars
         System.out.println("jsonFormattedString: "+jsonFormattedString); //still doubles quotes there. It's not exactly in Json format
        
         String finalJSONString=jsonFormattedString.replace("\"{","{").replace("}\"","}"); //Removes double quotes both the sides
         System.out.println("finalJSONString: " +finalJSONString);
        
         File jsonfile=new File(".\\datafiles\\singlejson.json");
         ObjectMapper om=new ObjectMapper();
         
         om.writeValue(jsonfile,finalJSONString);
         //om.writeva
         
         
         System.out.println("Done!");
                 
         con.close();
        
    }

}