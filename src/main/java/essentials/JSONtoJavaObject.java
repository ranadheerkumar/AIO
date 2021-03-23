package essentials;

// Convert JSON to Java Object
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONtoJavaObject {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        
         ObjectMapper om=new ObjectMapper();
        
         File jsonfile=new File(".//datafiles//custinfo.json");
         CustomerDetails cd=om.readValue(jsonfile, CustomerDetails.class);
        
         System.out.println(cd.getBookname());
         System.out.println(cd.getAmount());
        

    }

}

