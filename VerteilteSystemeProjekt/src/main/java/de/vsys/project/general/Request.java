package de.vsys.project.general;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;

    public Request(String url){
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder().GET().header("accept","application/json").uri(URI.create(url)).build();
    }
    public String sendRequest(){
        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return response.body();
    }

}