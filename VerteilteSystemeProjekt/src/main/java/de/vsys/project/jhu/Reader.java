package de.vsys.project.jhu;


import com.google.gson.Gson;
import de.vsys.project.general.Request;

public class Reader {
    public static void main(String[] args){
        Gson gson = new Gson();
        Request request = new Request("https://pomber.github.io/covid19/timeseries.json");
        Data data = gson.fromJson(request.sendRequest(),Data.class);
        for(CountryData dataS: data.getData()){
            System.out.println("Datum: " + dataS.getDate());
            System.out.println("Tote: " + dataS.getDeaths());
            System.out.println();

        }





    }
}
