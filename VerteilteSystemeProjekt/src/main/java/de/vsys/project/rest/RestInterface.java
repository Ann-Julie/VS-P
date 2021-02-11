package de.vsys.project.rest;

import com.google.gson.Gson;
import de.vsys.project.general.data.*;
import de.vsys.project.jhu.JohnHopkinsUniversityData;
import de.vsys.project.rki.RobertKochInstitutData;
import static spark.Spark.*;

public class RestInterface {
    /*
    @author: Mike Witkowski, David Rohrschneider
    This class implements the rest interface with different routes for the different values
     */
    public static void main(String[] args){
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        RobertKochInstitutData robertKochInstitutData = new RobertKochInstitutData();
        Gson gson = new Gson();

        get("alldata", (request, response)->{
            //returns all data as json
            AllData returnData = new AllData();
            //Set John Hopkins data
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            returnData.setAverageIncreaseLastNDays(johnHopkinsUniversityData.checkAverageIncreaseFromLastNDays(7));
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            returnData.setIncreaseLastTwentyFourHours(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFourHours());
            //Set Robert Koch data


           return gson.toJson(returnData);
        });
        get("totalinfections", (request, response)->{
            //returns the totalinfections as json
            TotalInfections returnData = new TotalInfections();
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            return gson.toJson(returnData);
        });
        get("newinfectionsfromlasttwentyfourhours", (request, response)->{
            //returns new infections from last twenty four hours as json
            NewInfectionsLastTwentyFourHours returnData = new NewInfectionsLastTwentyFourHours();
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            return gson.toJson(returnData);
        });

        get("targettotalinfection", (request, response)->{
            //returns target of the total infection number as json
            TargetTotalInfection returnData = new TargetTotalInfection();
            returnData.setTargetTotalInfection(robertKochInstitutData.calculateTargetNumberOfTotalInfection());
            return gson.toJson(returnData);
        });

        get("forecastnecessarylockdowndays", (request, response)->{
            //returns target of the total infection number as json
            ForecastNecessaryLockdownDays returnData = new ForecastNecessaryLockdownDays();
            returnData.setForecastNecessaryLockdownDays(robertKochInstitutData.calculateRequiredDaysForLockdown());
            return gson.toJson(returnData);
        });

        get("incidencevaluelastsevendays", (request, response)->{
            //returns target of the total infection number as json
            IncidenceValueLastSevenDays returnData = new IncidenceValueLastSevenDays();
            returnData.setIncidenceValueLastSevenDays(robertKochInstitutData.calculateIncidenceValueLastSevenDays());
            return gson.toJson(returnData);
        });
    }
}
