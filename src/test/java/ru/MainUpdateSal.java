package ru;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class MainUpdateSal {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        JsonObject newEmp= Json.createObjectBuilder()
                .add("id",7788)
                .add("sal",6001)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/controller/scott/dml/update"))
                .headers("Content-Type", MediaType.APPLICATION_JSON+";charset=utf-8")
                .PUT(HttpRequest.BodyPublishers.ofByteArray(newEmp.toString()
                        .getBytes(StandardCharsets.UTF_8)))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));
            if(response.statusCode()==200) {
                System.out.println(""+response.body());
            }else {
                System.out.println("Ошибка !!!! "+response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
