package de.vsys.project.rki;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class RequestRKI {
    String url;

    public RequestRKI(String url) {
        this.url = url;
    }

    public void sendGet() throws Exception{
    HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        try (
    BufferedReader in = new BufferedReader(
            new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            //print result
            System.out.println(response.toString());

        }
    }
}
