package de.vsys.project.jhu;

public class JohnHopkinsUniversityData {
    Reader reader;
    Data countryData;
    public JohnHopkinsUniversityData(){
        reader = new Reader();
    }

    public int checkNewInfectionsFromLastTwentyFourHours(){
        countryData = reader.readData();
        int result;
        result = countryData.getData()[(countryData.getData().length)-1].getConfirmed() - countryData.getData()[(countryData.getData().length)-2].getConfirmed();
        return result;

    }
    public void checkTotalInfections(){

    }
    public void checkIncreseFromLastTwentyFoursHours(){

    }
    public void checkAverageIncreaseFromLastNDays(int days){

    }
    public static void main(String[] args){
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        System.out.println(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
    }



}



