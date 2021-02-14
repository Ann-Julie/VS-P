package de.vsys.project.tests;


import de.vsys.project.jhu.JohnHopkinsUniversityData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JHUTest {
    /*
    @author: Mike Witkowski
    This class tests the methods from the JohnHopkinsUniversityData class
    For testing the methods, you have to calcute the values manually
     */
    JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();

    //This method tests the method that checks the infections from the last twenty four hours
    @Test
    public void testInfectionsFromLastTwentyFourHours(){
        Assertions.assertEquals(6484, johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
    }

    //This method tests the method that checks the total infections
    @Test
    public void testTotalInfections(){
        Assertions.assertEquals(143914, johnHopkinsUniversityData.checkTotalInfections());

    }

    //This method tests the method that checks the average increase from the last n days, in this case we test for the last 7 days
    @Test
    public void testAverageIncreaseFromLastNDays(){
        Assertions.assertEquals(-5364.285714285715,johnHopkinsUniversityData.checkAverageIncreaseFromLastNDays(7));
    }

    //This method tests the method that checks the increase from the last twenty four hours
    @Test
    public void testIncreaseFromLastTwentyFourHours(){
        Assertions.assertEquals(-1395, johnHopkinsUniversityData.checkIncreaseFromLastTwentyFourHours());
    }
}