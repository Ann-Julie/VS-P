package de.vsys.project.tests;


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
        Assertions.assertEquals(9.868467801400758, robertKochInstitutData.calculateRequiredDaysForLockdown());
    }

    //This method tests the method that returns target number of total infections
    @Test
    public void testCalculateTargetNumberOfTotalInfection(){
        Assertions.assertEquals(77217.87870472744, robertKochInstitutData.calculateTargetNumberOfTotalInfection());
    }

    //This method tests the method that returns the incidence value of the last seven days
    @Test
    public void testCalculateIncidenceValueLastSevenDays(){
        Assertions.assertEquals(57.03724414447506, robertKochInstitutData.calculateIncidenceValueLastSevenDays());
    }
}