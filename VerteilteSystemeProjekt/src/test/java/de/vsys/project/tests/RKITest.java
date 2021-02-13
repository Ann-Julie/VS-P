package de.vsys.project.tests;


import de.vsys.project.jhu.JohnHopkinsUniversityData;
import de.vsys.project.rki.RobertKochInstitutData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RKITest {
    /*
    @author: David Rohrschneider
    This class tests the methods from the JohnHopkinsUniversityData class
    For testing the methods, you have to calcute the values manually
     */
    RobertKochInstitutData robertKochInstitutData = new RobertKochInstitutData();

    //This method tests the method that returns the required days left for lockdown
    @Test
    public void testCalculateRequiredDaysForLockdown(){
        Assertions.assertEquals(20.826848031668494, robertKochInstitutData.calculateRequiredDaysForLockdown(7));
    }

    //This method tests the method that returns target number of total infections
    @Test
    public void testCalculateTargetNumberOfTotalInfection(){
        Assertions.assertEquals(77577.27115768887, robertKochInstitutData.calculateTargetNumberOfTotalInfection());
    }

    //This method tests the method that returns the incidence value of the last seven days
    @Test
    public void testCalculateIncidenceValueLastSevenDays(){
        Assertions.assertEquals(66.54339503005781, robertKochInstitutData.calculateIncidenceValueLastSevenDays());
    }
}