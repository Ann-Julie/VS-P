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
    /*
    @author: Jona Heinzer, Maximilian Meyer
    This class implements the soap server with different routes for different data.
    */
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
        //set Robert Koch data
        returnData.setIncidenceValueLastSevenDays(rkiData.calculateIncidenceValueLastSevenDays());
        returnData.setTargetTotalInfection(rkiData.calculateTargetNumberOfTotalInfection());
        returnData.setForecastNecessaryLockdownDays(rkiData.calculateRequiredDaysForLockdown(7));
        return gson.toJson(returnData);
    }

    //returns the totalInfections as json
    @WebMethod
    public String getTotalInfections() {
        TotalInfections returnData = new TotalInfections();
        returnData.setTotalInfections(jhiData.checkTotalInfections());
        return gson.toJson(returnData);
    }

    //returns new infections from last twenty four hours as json
    @WebMethod
    public String getNewInfectionsOfLastTwentyFourHours() {
        NewInfectionsLastTwentyFourHours returnData = new NewInfectionsLastTwentyFourHours();
        returnData.setNewInfectionsLastTwentyFourHours(jhiData.checkNewInfectionsFromLastTwentyFourHours());
        return gson.toJson(returnData);
    }

    //returns target of the total infection number as json
    @WebMethod
    public String getTargetTotalInfection() {
        TargetTotalInfection returnData = new TargetTotalInfection();
        returnData.setTargetTotalInfection(rkiData.calculateTargetNumberOfTotalInfection());
        return gson.toJson(returnData);
    }

    //returns target of the total infection number as json
    @WebMethod
    public String getForecastForNecessaryLockdownDays(int n) {
        ForecastNecessaryLockdownDays returnData = new ForecastNecessaryLockdownDays();
        returnData.setForecastNecessaryLockdownDays(rkiData.calculateRequiredDaysForLockdown(n));
        return gson.toJson(returnData);
    }

    //returns target of the total infection number as json
    @WebMethod
    public String getIncidenceValueLastSevenDays() {
        IncidenceValueLastSevenDays returnData = new IncidenceValueLastSevenDays();
        returnData.setIncidenceValueLastSevenDays(rkiData.calculateIncidenceValueLastSevenDays());
        return gson.toJson(returnData);
    }

    //returns the average increase from the last n days as json
    @WebMethod
    public String getAverageIncreaseInLastNDays(int n) {
        AverageIncreaseLastNDays returnData = new AverageIncreaseLastNDays();
        returnData.setAverageIncreaseLastNDays(jhiData.checkAverageIncreaseFromLastNDays(n));
        return gson.toJson(returnData);
    }

    //returns the increase of the last twenty four hours as json
    @WebMethod
    public String getIncreaseLastTwentyFourHours() {
        IncreaseLastTwentyFourHours returnData = new IncreaseLastTwentyFourHours();
        returnData.setIncreaseLastTwentyFourHours(jhiData.checkIncreaseFromLastTwentyFourHours());
        return gson.toJson(returnData);
    }

    public static void main(String[] args) {
        Endpoint.publish("http://167.99.252.170:4568/coronaData", new SoapServer());
    }
}