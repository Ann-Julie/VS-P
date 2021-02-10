package de.vsys.project.jhu;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Request {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    String url;
    public Request(String url){
        this.url = url;
    }
    public void sendGet() throws Exception{
        HttpGet request = new HttpGet(url);
        
    }
}
