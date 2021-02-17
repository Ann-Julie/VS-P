package de.vsys.project.jhu;


public class CountryData {
    /*
    @author: Mike Witkowski
    This class is the template for the json data from the john hopkins university
     */
    private String date;
    private int confirmed;
    private int deaths;
    private int recovered;

    public String getDate() {
        return date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }
}