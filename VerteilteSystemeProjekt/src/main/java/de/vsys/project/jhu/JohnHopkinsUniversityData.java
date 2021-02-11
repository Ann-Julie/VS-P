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
        int result = countryData.getData()[(countryData.getData().length) - 1].getConfirmed() - countryData.getData()[(countryData.getData().length) - 2].getConfirmed();
        return result;
    }

    public int checkTotalInfections() {
        Data countryData = reader.readData();
        int result = countryData.getData()[countryData.getData().length - 1].getConfirmed();
        return result;
    }

    //This method calculates the average increase of the confirmed numbers in the last 24 h
    public double checkIncreaseFromLastTwentyFoursHours() {
        Data countryData = reader.readData();
        double confirmedYesterday = (countryData.getData()[(countryData.getData().length) - 1].getConfirmed());
        double confirmedDayBeforeYesterday = (countryData.getData()[(countryData.getData().length) - 2].getConfirmed());
        double percentIncrease = (confirmedYesterday / confirmedDayBeforeYesterday - 1) * 100;
        return mathOperations.roundNumberToTwoDecimals(percentIncrease);
    }

    //This method calculates the average increase of the confirmed numbers in the time specified time period
    public double checkAverageIncreaseFromLastNDays(int days) {
        Data countryData = reader.readData();
        double result = 0;
        for (int i = 1; i <= days; i++) {
            double confirmedCurrent = (countryData.getData()[(countryData.getData().length) - i].getConfirmed());
            double confirmedDayBeforeCurrent = (countryData.getData()[(countryData.getData().length) - (i + 1)].getConfirmed());
            result += (confirmedCurrent / confirmedDayBeforeCurrent) - 1;
        }

        result = (result / days) * 100;
        result = mathOperations.roundNumberToTwoDecimals(result);
        return result;
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


    public static void main(String[] args) {
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        System.out.println(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFoursHours());
    }


}



