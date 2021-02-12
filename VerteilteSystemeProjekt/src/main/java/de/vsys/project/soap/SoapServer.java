package de.vsys.project.soap;

import com.google.gson.Gson;
import de.vsys.project.general.data.*;
import de.vsys.project.jhu.JohnHopkinsUniversityData;
import de.vsys.project.rki.RobertKochInstitutData;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class SoapServer {

    JohnHopkinsUniversityData jhiData = new JohnHopkinsUniversityData();
    RobertKochInstitutData rkiData = new RobertKochInstitutData();
    Gson gson = new Gson();

    @WebMethod
    public String getAllData() {
        //returns all data as json
        AllData returnData = new AllData();
        //Set John Hopkins data
        returnData.setNewInfectionsLastTwentyFourHours(jhiData.checkNewInfectionsFromLastTwentyFourHours());
        returnData.setAverageIncreaseLastNDays(jhiData.checkAverageIncreaseFromLastNDays(7));
        returnData.setTotalInfections(jhiData.checkTotalInfections());
        returnData.setIncreaseLastTwentyFourHours(jhiData.checkIncreaseFromLastTwentyFourHours());
        //Set Robert Koch data
        returnData.setIncidenceValueLastSevenDays(rkiData.calculateIncidenceValueLastSevenDays());
        returnData.setTargetTotalInfection(rkiData.calculateTargetNumberOfTotalInfection());
        returnData.setForecastNecessaryLockdownDays(rkiData.calculateRequiredDaysForLockdown());
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getTotalInfections() {
        //returns the totalinfections as json
        TotalInfections returnData = new TotalInfections();
        returnData.setTotalInfections(jhiData.checkTotalInfections());
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getNewInfectionsOfLastTwentyFourHours() {
        //returns new infections from last twenty four hours as json
        NewInfectionsLastTwentyFourHours returnData = new NewInfectionsLastTwentyFourHours();
        returnData.setNewInfectionsLastTwentyFourHours(jhiData.checkNewInfectionsFromLastTwentyFourHours());
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getTargetTotalInfection() {
        //returns target of the total infection number as json
        TargetTotalInfection returnData = new TargetTotalInfection();
        returnData.setTargetTotalInfection(rkiData.calculateTargetNumberOfTotalInfection());
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getForecastForNecessaryLockdownDays() {
        //returns target of the total infection number as json
        ForecastNecessaryLockdownDays returnData = new ForecastNecessaryLockdownDays();
        returnData.setForecastNecessaryLockdownDays(rkiData.calculateRequiredDaysForLockdown());
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getIncidenceValueLastSevenDays() {
        //returns target of the total infection number as json
        IncidenceValueLastSevenDays returnData = new IncidenceValueLastSevenDays();
        returnData.setIncidenceValueLastSevenDays(rkiData.calculateIncidenceValueLastSevenDays());
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getAverageIncreaseInLastNDays(int n) {
        //returns the average increase from the last n days as json
        AverageIncreaseLastNDays returnData = new AverageIncreaseLastNDays();
        returnData.setAverageIncreaseLastNDays(jhiData.checkAverageIncreaseFromLastNDays(n));
        return gson.toJson(returnData);
    }

    @WebMethod
    public String getIncreaseLastTwentyFourHours() {
        //returns the increase of the last twenty four hours as json
        IncreaseLastTwentyFourHours returnData = new IncreaseLastTwentyFourHours();
        returnData.setIncreaseLastTwentyFourHours(jhiData.checkIncreaseFromLastTwentyFourHours());
        return gson.toJson(returnData);
    }


    public static void main(String[] args) {
        Endpoint.publish("http://localhost:4568/coronaData", new SoapServer());
    }
}
