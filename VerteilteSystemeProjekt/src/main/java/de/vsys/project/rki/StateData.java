package de.vsys.project.rki;

/**
 * authors: Max, AJ, Jona
 *
 * In dieser Klasse werden Variablen festgelegt mit gettern und settern
 * für das Bundesland, die Fälle und die entsprechende ID
 */
public class StateData {
    String LAN_ew_GEN;
    int cases7_bl_per_100k;
    int OBJECTID_1;

    public String getLAN_ew_GEN() {
        return LAN_ew_GEN;
    }

    public int getCases7_bl_per_100k() {
        return cases7_bl_per_100k;
    }

    public int getOBJECTID_1() {
        return OBJECTID_1;
    }
}
