package de.vsys.project.jhu;

import de.vsys.project.general.MathOperations;

import java.util.ArrayList;
import java.util.List;

public class JohnHopkinsUniversityData {
    private Reader reader;
    MathOperations mathOperations;

    public JohnHopkinsUniversityData() {
        reader = new Reader();
        mathOperations = new MathOperations();
    }

    public int checkNewInfectionsFromLastTwentyFourHours() {
        Data countryData = reader.readData();
        int today = countryData.getData()[countryData.getData().length-1].getConfirmed();
        int yesterday = countryData.getData()[countryData.getData().length-2].getConfirmed();
        int result = today - yesterday;
        return result;
    }

    public int checkTotalInfections() {
        Data countryData = reader.readData();
        int confirmed = countryData.getData()[countryData.getData().length-1].getConfirmed();
        int recovered = countryData.getData()[countryData.getData().length-1].getRecovered();
        int deaths = countryData.getData()[countryData.getData().length-1].getDeaths();
        int result = confirmed - recovered - deaths;
        return result;
    }

    //This method calculates the average increase of the confirmed numbers in the last 24 h
    public double checkIncreaseFromLastTwentyFoursHours() {
        Data countryData = reader.readData();
        double confirmedYesterday = (countryData.getData()[(countryData.getData().length) - 1].getConfirmed());
        double deathsYesterday = (countryData.getData()[(countryData.getData().length) - 1].getDeaths());
        double recoveredYesterday = (countryData.getData()[(countryData.getData().length) - 1].getRecovered());
        double totalYesterday = confirmedYesterday - deathsYesterday - recoveredYesterday;

        double confirmedDayBeforeYesterday = (countryData.getData()[(countryData.getData().length) - 2].getConfirmed());
        double deathsDayBeforeYesterday = (countryData.getData()[(countryData.getData().length) - 2].getDeaths());
        double recoveredDayBeforeYesterday = (countryData.getData()[(countryData.getData().length) - 2].getRecovered());
        double totalDayBeforeYesterday = confirmedDayBeforeYesterday - deathsDayBeforeYesterday - recoveredDayBeforeYesterday;

        double result = totalYesterday - totalDayBeforeYesterday;
        return result;
    }

    //This method calculates the average increase of the confirmed numbers in the time specified time period
    public double checkAverageIncreaseFromLastNDays(int days) {
        Data countryData = reader.readData();
        double result = 0;
        for (int i = 1; i <= days; i++) {
            double confirmedCurrent = countryData.getData()[(countryData.getData().length) - i].getConfirmed();
            double deathsCurrent = countryData.getData()[(countryData.getData().length) - i].getDeaths();
            double recoveredCurrent = countryData.getData()[(countryData.getData().length) - i].getRecovered();
            double totalCurrent = confirmedCurrent - deathsCurrent - recoveredCurrent;

            double confirmedDayBeforeCurrent = countryData.getData()[(countryData.getData().length) - (i+1)].getConfirmed();
            double deathsDayBeforeCurrent = countryData.getData()[(countryData.getData().length) - (i+1)].getDeaths();
            double recoveredDayBeforeCurrent = countryData.getData()[(countryData.getData().length) - (i+1)].getRecovered();
            double totalDayBeforeCurrent = confirmedDayBeforeCurrent - deathsDayBeforeCurrent - recoveredDayBeforeCurrent;

            result += totalCurrent - totalDayBeforeCurrent;
        }

        result = result / days;
        return result;
    }


    public static void main(String[] args) {
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        System.out.println(johnHopkinsUniversityData.checkTotalInfections());
    }




    // Diese Methode bekommt ein Array von CountryData. Jeder Eintrag beinhaltet aktuelle Fallzahlen. Dabei enthält der Letzte Eintrag bei Ausführung
    // immer die Werte vom Vortag. Die Variable "daysSince" gibt dabei an, von welchem Tag die Werte ausgegeben werden. Ist daysSince == 1, werden die
    // Werte vom Vortag ausgegeben. Bei daysSince == 2 werden die Werte von vor zwei Tagen ausgegeben. Es wird eine Exception geworfen, wenn
    // unzulässige Werte für daysSince eingegeben werden. Da Werte vom aktuellen Tag (oder später) nicht verfügbar sind, sind Werte <= 0 verboten.
    // Auch Werte, die auf Daten vor Beginn der Aufzeichnung zugreifen sind verboten.
    private CountryData getCountryDataByDaysSinceUsingLocalData(CountryData[] countryDataArray, int daysSince) throws IndexOutOfBoundsException {
        if (daysSince <= 0 || daysSince > countryDataArray.length) {
            throw new IndexOutOfBoundsException("Value of daysSince must not be <= 0 or > number of entries in countryData.");
        }
        return countryDataArray[countryDataArray.length - daysSince];
    }
    public CountryData getCountryDataByDaysSince(int daysSince) throws IndexOutOfBoundsException {
        CountryData[] countryDataArray = reader.readData().getData();
        return getCountryDataByDaysSinceUsingLocalData(countryDataArray, daysSince);
    }

    // Nimmt eine Liste von daysSince, fragt die passenden Fallzahlen (CountryData) ab und gibt diese in einer identisch sortierten Liste zurück.
    public List<CountryData> getCountryDataForDaysSinceList(List<Integer> daysSinceList) throws IndexOutOfBoundsException {
        CountryData[] countryDataArray = reader.readData().getData();
        for (int daysSince : daysSinceList) {
            if (daysSince <= 0 || daysSince > countryDataArray.length) {
                throw new IndexOutOfBoundsException("Value of every daysSince entry must not be <= 0 or > number of entries in countryData.");
            }
        }

        List<CountryData> countryDataList = new ArrayList<>();
        for (int daysSince : daysSinceList) {
            countryDataList.add(getCountryDataByDaysSinceUsingLocalData(countryDataArray, daysSince));
        }

        return countryDataList;
    }

}



