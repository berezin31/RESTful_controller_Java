package ru;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;

public class MainInfo {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/controller/scott/main/info"))
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
