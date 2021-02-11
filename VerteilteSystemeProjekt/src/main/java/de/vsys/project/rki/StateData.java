package de.vsys.project.rki;


public class StateData {
    /*
    @author: Maximilian Meyer, Jona Heinzer
    This class is the template for the json data from the Robert Koch Institut
    */
    double cases7_bl_per_100k;
    String LAN_ew_GEN;


    public String getLAN_ew_GEN() {
        return LAN_ew_GEN;
    }

    public double getCases7_bl_per_100k() {
        return cases7_bl_per_100k;
    }

}
