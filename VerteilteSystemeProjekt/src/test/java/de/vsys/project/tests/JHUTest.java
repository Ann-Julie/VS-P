package de.vsys.project.tests;

import de.vsys.project.jhu.JohnHopkinsUniversityData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JHUTest {
    /*
    @author: Mike Witkowski
     */
    JohnHopkinsUniversityData johnHopkinsUniversityData = new JohnHopkinsUniversityData();
    @Test
    public void test(){
        Assertions.assertEquals(7,3+5);
    }
    @Test
    public void testInfectionsFromLastTwentyFourHours(){
            Assertions.assertEquals(9928, johnHopkinsUniversityData.checkNewInfectionsFromLastTwentyFourHours());
    }
}
