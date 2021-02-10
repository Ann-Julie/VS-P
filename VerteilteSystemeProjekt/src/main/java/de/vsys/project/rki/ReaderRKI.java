package de.vsys.project.rki;


import com.google.gson.Gson;
import de.vsys.project.general.Request;

/**
 * authors: Max, AJ, Jona, Mike
 *
 * In dieser Klasse werden JSON Objekte erstellt
 * Sie ist dazu da, um Anfragen zu machen und direkt eine Antwort zu erhalten
 * Dazu wird die JSON Datei durchgegangen und Bundesländer sowie Neuinfektionen ausgegeben
 * Der Request selbst wird aus dem General Ordner und der Request.java Klasse geholt
 */
public class ReaderRKI {
    Gson gson;
    Request request;

    public ReaderRKI(){
        gson = new Gson();
        request = new Request("https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/Coronaf%C3%A4lle_in_den_Bundesl%C3%A4ndern/FeatureServer/0/query?where=1%3D1&outFields=cases7_bl_per_100k,LAN_ew_GEN&returnGeometry=false&returnDistinctValues=true&outSR=4326&f=json");
    }

    public DataRKI readData(){
        DataRKI dataRKI = gson.fromJson(request.sendRequest(), DataRKI.class);
        return dataRKI;
    }
}
