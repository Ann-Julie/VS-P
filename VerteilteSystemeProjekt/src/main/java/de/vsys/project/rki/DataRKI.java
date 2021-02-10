package de.vsys.project.rki;

/**
 * authors: Max, AJ, Jona
 * Diese Klasse ist dazu da, um auf das JSON Objekt zuzugreifen,
 * damit die entsprechenden Features von der Seite
 * https://npgeo-corona-npgeo-de.hub.arcgis.com/datasets/ef4b445a53c1406892257fe63129a8ea_0/geoservice?selectedAttribute=cases7_bl_per_100k
 * erhält
 */
public class DataRKI {
    StateData[] features;

    public StateData[] getData(){
        return features;
    }
}
