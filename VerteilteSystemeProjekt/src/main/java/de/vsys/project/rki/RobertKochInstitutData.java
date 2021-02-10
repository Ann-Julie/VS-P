package de.vsys.project.rki;

import de.vsys.project.jhu.JohnHopkinsUniversityData;

public class RobertKochInstitutData {
    /*
    @author: Mike Witkowski
    description...
     */
    private ReaderRKI readerRKI;
    private DataRKI data;
    private JohnHopkinsUniversityData johnHopkinsUniversityData;

    public RobertKochInstitutData(){
        readerRKI = new ReaderRKI();
        johnHopkinsUniversityData = new JohnHopkinsUniversityData();
    }
    public int getRValueGermany(){
        data = readerRKI.readData();
        return 1;
    }

    public ReaderRKI getReaderRKI() {
        return readerRKI;
    }

    public DataRKI getData() {
        return data;
    }

    public JohnHopkinsUniversityData getJohnHopkinsUniversityData() {
        return johnHopkinsUniversityData;
    }

    //andere Methoden reinmachen
    public static void main(String[] args){
        RobertKochInstitutData robertKochInstitutData = new RobertKochInstitutData();

    }
}
