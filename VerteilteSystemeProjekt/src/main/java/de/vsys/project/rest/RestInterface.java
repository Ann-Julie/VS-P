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
            //set John Hopkins data
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            returnData.setAverageIncreaseLastNDays(johnHopkinsUniversityData.checkAverageIncreaseFromLastNDays(7));
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            returnData.setIncreaseLastTwentyFourHours(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFourHours());
            //set Robert Koch data
            returnData.setIncidenceValueLastSevenDays(robertKochInstitutData.calculateIncidenceValueLastSevenDays());
            returnData.setTargetTotalInfection(robertKochInstitutData.calculateTargetNumberOfTotalInfection());
            returnData.setForecastNecessaryLockdownDays(robertKochInstitutData.calculateRequiredDaysForLockdown());
           return gson.toJson(returnData);
        });

        //returns the totalinfections as json
        get("totalinfections", (request, response)->{
            TotalInfections returnData = new TotalInfections();
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            return gson.toJson(returnData);
        });

        //returns new infections from last twenty four hours as json
        get("newinfectionsfromlasttwentyfourhours", (request, response)->{
            NewInfectionsLastTwentyFourHours returnData = new NewInfectionsLastTwentyFourHours();
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            return gson.toJson(returnData);
        });

        //returns target of the total infection number as json
        get("targettotalinfection", (request, response)->{
            TargetTotalInfection returnData = new TargetTotalInfection();
            returnData.setTargetTotalInfection(robertKochInstitutData.calculateTargetNumberOfTotalInfection());
            return gson.toJson(returnData);
        });

        //returns the forecast of necessary lockdown days as json
        get("forecastnecessarylockdowndays", (request, response)->{
            ForecastNecessaryLockdownDays returnData = new ForecastNecessaryLockdownDays();
            returnData.setForecastNecessaryLockdownDays(robertKochInstitutData.calculateRequiredDaysForLockdown());
            return gson.toJson(returnData);
        });

        //returns the incidence value of the last seven days as json
        get("incidencevaluelastsevendays", (request, response)->{
            IncidenceValueLastSevenDays returnData = new IncidenceValueLastSevenDays();
            returnData.setIncidenceValueLastSevenDays(robertKochInstitutData.calculateIncidenceValueLastSevenDays());
            return gson.toJson(returnData);
        });

        //returns the average increase from the last n days as json
        get("averageincreaselastndays/:n", (request, response)->{
            AverageIncreaseLastNDays returnData = new AverageIncreaseLastNDays();
            returnData.setAverageIncreaseLastNDays(johnHopkinsUniversityData.checkAverageIncreaseFromLastNDays(Integer.parseInt(request.params(":n"))));
            return gson.toJson(returnData);
        });

        //returns the increase of the last twenty four hours as json
        get("/increaselasttwentyfourhours", (request, response)->{
            IncreaseLastTwentyFourHours returnData = new IncreaseLastTwentyFourHours();
            returnData.setIncreaseLastTwentyFourHours(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFourHours());
            return gson.toJson(returnData);
        });
    }
}