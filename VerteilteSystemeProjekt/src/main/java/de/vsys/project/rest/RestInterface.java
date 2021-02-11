package de.vsys.project.rest;

import com.google.gson.Gson;
import de.vsys.project.general.ReturnData;
import de.vsys.project.jhu.JohnHopkinsUniversityData;
import de.vsys.project.rki.RobertKochInstitutData;
import static spark.Spark.*;

public class RestInterface {
    public static void main(String[] args){
        JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
        RobertKochInstitutData robertKochInstitutData = new RobertKochInstitutData();
        Gson gson = new Gson();

        get("allData", (request, response)->{
            ReturnData returnData = new ReturnData();
            //Set John Hopkins data
            returnData.setNewInfectionsLastTwentyFourHours(johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
            returnData.setAverageIncreaseLastNDays(johnHopkinsUniversityData.checkAverageIncreaseFromLastNDays(7));
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            returnData.setIncreaseLastTwentyFourHours(johnHopkinsUniversityData.checkIncreaseFromLastTwentyFoursHours());
            //Set Robert Koch data
           return gson.toJson(returnData);
        });
        get("totalinfections",(request, response) ->{
            ReturnData returnData = new ReturnData();
            returnData.setTotalInfections(johnHopkinsUniversityData.checkTotalInfections());
            return gson.toJson(returnData);
        });
    }
}
