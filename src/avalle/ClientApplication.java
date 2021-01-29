package avalle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientApplication {


    public static void main(String[] args) {
        // handle exception if something happens, it will be catch
        try {
            // create and set values for a new student.
            Student studentToSend = new Student();
            studentToSend.setID(1);
            studentToSend.setFirstName("Carlos");
            studentToSend.setLastName("Avalle");

            // create a http server and send a student to be converted to JSON format
            HTTPServer server = new HTTPServer(studentToSend);

            // will read the JSON from the server
            String json = readJSON("http://localhost/json");

            // it will convert the JSOn to Student and display it
            System.out.println(JSONToStudent(json));


            // will catch any exception and will display an error if something happens.
        } catch (IllegalArgumentException | IOException e){
            System.out.println(e.toString());
        }

    }

    // converts a JSON string into Student using jackson.
    public static Student JSONToStudent(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = null;
        student =  mapper.readValue(json, Student.class);
        return student;
    }


    // it will take a JSON string from a http server.
    public static String readJSON(String url) throws IOException {
        StringBuilder data = new StringBuilder();
        String line = null;
        // will connect to the server with the url provided.
        HttpURLConnection cnn = (HttpURLConnection) new URL(url).openConnection();

        // will read the information in the server.
        BufferedReader reader = new BufferedReader(new InputStreamReader(cnn.getInputStream()));
        while ((line = reader.readLine()) != null){
            data.append(line);
        }
        // will return the JSON string.
        return data.toString();
    }

}
