package de.vsys.projects.jhu;


import java.net.http.HttpClient;

public class Request {
    String url;
    HttpClient client;

    public Request(String url){
        this.url = url;
        client = HttpClient.newHttpClient();
        
    }

}