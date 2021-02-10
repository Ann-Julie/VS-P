package de.vsys.project.jhu;


import com.google.gson.Gson;
import de.vsys.project.general.Request;

public class Reader {
    Gson gson;
    Request request;
    public Reader() {
        gson = new Gson();
        request = new Request("https://pomber.github.io/covid19/timeseries.json");
    }
    public Data readData(){
        Data data = gson.fromJson(request.sendRequest(),Data.class);
        return data;
    }





}

