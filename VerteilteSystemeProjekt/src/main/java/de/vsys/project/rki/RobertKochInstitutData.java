package de.vsys.project.rki;


import de.vsys.project.jhu.JohnHopkinsUniversityData;

public class RobertKochInstitutData {
    /*
    @author: Maximilian Meyer, Jona Heinzer
    This class uses information collected from the RKI to calculate the required values.
    */
    private ReaderRKI readerRKI;
    private DataRKI stateData;
    private JohnHopkinsUniversityData jhuData;

    public RobertKochInstitutData() {
        readerRKI = new ReaderRKI();
        jhuData = new JohnHopkinsUniversityData();
    }

    //this method calculates the incidence value for the last seven days
    public double calculateIncidenceValueLastSevenDays(){
        stateData = readerRKI.readData();
        double result = 0;
        double valueOfNewInfections = 0;
        double population = 0;
        for(int i = 0; i <= stateData.getData().length - 1; i++){
            valueOfNewInfections += stateData.getData()[stateData.getData().length - i - 1].getAttributes().getCases7_bl();
            population += stateData.getData()[stateData.getData().length-i-1].getAttributes().getLAN_EW_EWZ();

        }
        result = (valueOfNewInfections / population) * 100000;
        return result;
    }

    //this method calculates the target number of total infections
    public double calculateTargetNumberOfTotalInfection(){
        int totalInfection = jhuData.checkTotalInfections();
        double incidenceValue = calculateIncidenceValueLastSevenDays();
        int targetIncidence = 35;
        return (totalInfection / incidenceValue) * targetIncidence;
    }

    //this method gives a forecast for the necessary lockdown days, uses the average decrease of the last 14 days
    public double calculateRequiredDaysForLockdown(){
        int totalInfection = jhuData.checkTotalInfections();
        double targetNumberOfTotalInfection = calculateTargetNumberOfTotalInfection();
        double averageDecrease = (jhuData.checkAverageIncreaseFromLastNDays(14) * -1 );
        double result;
        if(averageDecrease <= 0){
            result =  9999;
        } else {
            result = (totalInfection - targetNumberOfTotalInfection)/averageDecrease;
        }
        return result;
    }

}