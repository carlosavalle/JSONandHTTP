package avalle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class HTTPServer {
    private int port;

    public HTTPServer(int port) {
        this.port = port;
        try {
            createServer(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void createServer(int port) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port),0);
        httpServer.createContext("/json", new handler());
        httpServer.setExecutor(null);
        httpServer.start();
    }

    private class handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Student _student = new Student();
            _student.setID(0);
            _student.setFirstName("Carlos");
            _student.setLastName("Avalle");
            String response = studentToJSOn(_student);
            exchange.sendResponseHeaders(200, response.length());
          //  exchange.getResponseHeaders().set("Content-Type", "application/json");
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }

    public String studentToJSOn(Student student){

        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;


    }

    }
