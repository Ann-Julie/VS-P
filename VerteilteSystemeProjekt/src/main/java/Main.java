import de.vsys.project.rki.RequestRKI;
import de.vsys.projects.jhu.Request;

public class Main {
    public static void main(String []args) throws Exception {
        Request request = new Request("https://pomber.github.io/covid19/timeseries.json");

        RequestRKI requestRKI = new RequestRKI("https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/Coronaf%C3%A4lle_in_den_Bundesl%C3%A4ndern/FeatureServer/0/query?where=1%3D1&outFields=cases7_bl_per_100k&returnGeometry=false&returnDistinctValues=true&outSR=4326&f=json");
        requestRKI.sendGet();

    }

}
