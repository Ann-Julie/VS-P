package de.vsys.project.rest;

import com.google.gson.Gson;
import de.vsys.project.general.data.AllData;
import de.vsys.project.general.data.TotalInfections;
import de.vsys.project.jhu.JohnHopkinsUniversityData;
import de.vsys.project.rki.RobertKochInstitutData;
import static spark.Spark.*;

public class RestInterface {
    /*
    @author: Mike Witkowski
    This class implements the rest interface with different routes for the different values
     */
    public static void main(String[] args){
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        RobertKochInstitutData robertKochInstitutData = new RobertKochInstitutData();
        Gson gson = new Gson();

        get("allData", (request, response)->{
            //returns all data as json
            AllData returnData = new AllData();
            //Set John Hopkins data
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            returnData.setAverageIncreaseLastNDays(johnHopkinsUniversityData.checkAverageIncreaseFromLastNDays(7));
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            returnData.setIncreaseLastTwentyFourHours(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFoursHours());
            //Set Robert Koch data


           return gson.toJson(returnData);
        });
        get("totalinfections", (request, response)->{
            //returns the totalinfections as json
            TotalInfections returnData = new TotalInfections();
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            return gson.toJson(returnData);
        });
        get("infectionsfromlasttwentyfourhours", (request, response)->{
            //returns new infections from last twenty four hours as json
            AllData returnData = new AllData();
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            return gson.toJson(returnData);
        });
    }
}
