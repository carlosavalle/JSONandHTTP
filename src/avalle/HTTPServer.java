package avalle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class HTTPServer {

    // creates a http server, takes a student object and send it to the http handler
    public HTTPServer(Student student) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(80),0);
        httpServer.createContext("/json", new handler(student));
        httpServer.setExecutor(null);
        httpServer.start();
    }



    private class handler implements HttpHandler {
        private Student _student;

        // takes the student object and save it into a variable.
        public handler(Student student) {
            _student = student;
        }

        // convert student object into JSON and send it to the header so the client can get the JSON created
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = studentToJSOn(_student);
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }

    // convert and student object to JSON
    public String studentToJSOn(Student student) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        s = mapper.writeValueAsString(student);

        return s;


    }

    }
