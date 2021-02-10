package de.vsys.projects.jhu;

import com.google.gson.Gson;

public class Reader {
    public static void main() throws Exception {
        Gson gson = new Gson();
        Request request = new Request("https://pomber.github.io/covid19/timeseries.json");

        Country[] countries = gson.fromJson(request.sendGet(),Country[].class);
        System.out.println(countries[0].getName());
    }
}
