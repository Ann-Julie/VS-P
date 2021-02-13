package de.vsys.project.rki;

import de.vsys.project.jhu.CountryData;
import de.vsys.project.jhu.JohnHopkinsUniversityData;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class RobertKochInstitutData {
    /*
    @author: Maximilian Meyer, Jona Heinzer
    This class uses information collected from the RKI to calculate the required values.
    */
    private ReaderRKI readerRKI;
    private DataRKI data;
    private JohnHopkinsUniversityData jhuData;

    public RobertKochInstitutData() {
        readerRKI = new ReaderRKI();
        jhuData = new JohnHopkinsUniversityData();
    }

    public ReaderRKI getReaderRKI() {
        return readerRKI;
    }

    public DataRKI getData() {
        return data;
    }

    //this method calculates the incidence value for the last seven days
    public double calculateIncidenceValueLastSevenDays(){
        DataRKI stateData = readerRKI.readData();
        double result = 0;
        double value = 0;
        for(int i = 0; i <= stateData.getData().length - 1; i++){
            value = stateData.getData()[stateData.getData().length - i - 1].getAttributes().getCases7_bl_per_100k();
            result += value;
        }
        result = (result/stateData.getData().length);

        return result;
    }

    //this method calculates the target number of total infections
    public double calculateTargetNumberOfTotalInfection(){
        int totalInfection = jhuData.checkTotalInfections();
        double incidenceValue = calculateIncidenceValueLastSevenDays();
        int targetIncidence = 35;
        return (totalInfection / incidenceValue) * targetIncidence;
    }

    //this method gives a forecast for the necessary lockdown days
    public double calculateRequiredDaysForLockdown(int days){
        int totalInfection = jhuData.checkTotalInfections();
        double targetNumberOfTotalInfection = calculateTargetNumberOfTotalInfection();
        double averageDecrease = (jhuData.checkAverageIncreaseFromLastNDays(days) * -1);
        return (totalInfection - targetNumberOfTotalInfection)/averageDecrease;
    }
}
