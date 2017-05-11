/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonobj;

/**
 *
 * @author I Sidhartha Kumar
 */
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class JsonObj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String s="{\"name\":\"sid\",\"age\":19}";
        JSONObject json=new JSONObject();
        //String to JSONObject 
        try {
            FileReader reader=new FileReader("test.json");
            JSONParser parsefile=new JSONParser();
            Object js1=parsefile.parse(reader);
            JSONObject json1=(JSONObject) js1;
            System.out.println(json1.get("name"));
            System.out.println(json1.get("version"));

        } catch (FileNotFoundException ex) {
           
            System.out.println("file not found");
        } catch (ParseException ex) {
            
        }
        JSONParser parser=new JSONParser();
        try{
            Object js=parser.parse(s);
            json =(JSONObject) js;
            System.out.println(json.get("name"));
            System.out.println(json.get("age"));
        }
        catch (ParseException e) {
            e.printStackTrace();
            System.out.println("exception");
        }
        //JSONObject to JSONString
        String str;
        str=json.toJSONString();
        System.out.println(str);
        
    }
    
}
 