package de.vsys.project.rki;

/**
 * authors: Max, AJ, Jona
 *
 * In dieser Klasse werden Variablen festgelegt mit gettern und settern
 * für das Bundesland, die Fälle und die entsprechende ID
 */
public class StateData {
    double cases7_bl_per_100k;
    String LAN_ew_GEN;


    public String getLAN_ew_GEN() {
        return LAN_ew_GEN;
    }

    public double getCases7_bl_per_100k() {
        return cases7_bl_per_100k;
    }

}
