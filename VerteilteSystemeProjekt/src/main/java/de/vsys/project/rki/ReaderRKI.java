package de.vsys.project.rki;


import com.google.gson.Gson;
import de.vsys.project.general.Request;

public class ReaderRKI {
    public static void main(String []args){
        Gson gson = new Gson();
        Request request = new Request("https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/Coronaf%C3%A4lle_in_den_Bundesl%C3%A4ndern/FeatureServer/0/query?where=1%3D1&outFields=cases7_bl_per_100k,LAN_ew_GEN,OBJECTID_1&returnGeometry=false&outSR=4326&f=json");
        DataRKI dataRKI = gson.fromJson(request.sendRequest(),DataRKI.class);
        for(StateData dataA: dataRKI.getData()){
            System.out.println("Bundesland: " + dataA.getLAN_ew_GEN());
            System.out.println("Neuinfektionen: "+ dataA.getCases7_bl_per_100k());
            System.out.println();
        }
    }
}
