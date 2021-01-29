package avalle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientApplication {


    public static void main(String[] args) {

        HTTPServer server = new HTTPServer(80);
        String json = readJSON("http://localhost/json");
        Student student = new Student();
        student = JSONToStudent(json);
        System.out.println(student);
    }


    public static Student JSONToStudent(String json){
        ObjectMapper mapper = new ObjectMapper();
        Student student = null;
        try {
            student =  mapper.readValue(json, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }



    public static String readJSON(String url) {
        StringBuilder data = new StringBuilder();
        String line = null;
        try {
            HttpURLConnection cnn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(cnn.getInputStream()));


            while ((line = reader.readLine()) != null){

                data.append(line);
            }
        }
        catch (IOException ioe) {
            
            System.out.println("Error reading source: "+ioe);
        }
        return data.toString();
    }

}
