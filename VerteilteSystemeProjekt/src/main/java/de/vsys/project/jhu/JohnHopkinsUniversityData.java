package de.vsys.project.jhu;

import de.vsys.project.general.MathOperations;

public class JohnHopkinsUniversityData {
    Reader reader;
    Data countryData;
    MathOperations mathOperations;
    public JohnHopkinsUniversityData(){
        reader = new Reader();
        mathOperations = new MathOperations();
    }

    public int checkNewInfectionsFromLastTwentyFourHours(){
        countryData = reader.readData();
        int result = countryData.getData()[(countryData.getData().length)-1].getConfirmed() - countryData.getData()[(countryData.getData().length)-2].getConfirmed();
        return result;

    }
    public void checkTotalInfections(){

    }
    public double checkIncreaseFromLastTwentyFoursHours(){
        countryData = reader.readData();
        double result = mathOperations.roundNumber(((double)(countryData.getData()[(countryData.getData().length)-1].getConfirmed())/(double)(countryData.getData()[(countryData.getData().length)-2].getConfirmed())-1) * 100);
        return result;
    }
    public double checkAverageIncreaseFromLastNDays(int days){
        countryData = reader.readData();
        double result=0;
        for(int i = 1; i<=days;i++){
            result += (((double)(countryData.getData()[(countryData.getData().length)-i].getConfirmed())/(double)(countryData.getData()[(countryData.getData().length)-(i+1)].getConfirmed()))-1);
        }

        result = (result/days)*100;
        result = mathOperations.roundNumber(result);
        return result;
    }



    public static void main(String[] args){
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        System.out.println(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFoursHours());
    }



}



