package de.vsys.project.rki;

import de.vsys.project.jhu.CountryData;
import de.vsys.project.jhu.JohnHopkinsUniversityData;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class RobertKochInstitutData {
    /*
    @author: Mike Witkowski
    description...
     */
    private ReaderRKI readerRKI;
    private DataRKI data;
    private JohnHopkinsUniversityData jhuData;

    public RobertKochInstitutData() {
        readerRKI = new ReaderRKI();
        jhuData = new JohnHopkinsUniversityData();
    }

    public ReaderRKI getReaderRKI() {
        return readerRKI;
    }

    public DataRKI getData() {
        return data;
    }

    private double getAbsoluteCaseIncrease(CountryData older, CountryData newer) {
        int newCases = newer.getConfirmed() - older.getConfirmed();
        return newCases;
    }

    public double getRValueJhu(int daysInTimeframe) {
        List<CountryData> dataForCalculation = jhuData.getCountryDataForDaysSinceList(asList(2 * daysInTimeframe, daysInTimeframe + 1, daysInTimeframe, 1)); // Zwei Zeiträume für r-Wert Berechnung. (Bei daysInTimeframe == 7: 14-8 & 7-1)
        CountryData oldTimeframeEnd = dataForCalculation.get(1);
        CountryData oldTimeframeStart = dataForCalculation.get(0);
        CountryData newTimeframeEnd = dataForCalculation.get(3);
        CountryData newTimeframeStart = dataForCalculation.get(2);
        return getRValue(oldTimeframeEnd, oldTimeframeStart, newTimeframeEnd, newTimeframeStart);
    }

    public double getRValue(CountryData oldTimeframeEnd, CountryData oldTimeframeStart, CountryData newTimeframeEnd, CountryData newTimeframeStart) {
        double caseIncreaseOldTimeframe = getAbsoluteCaseIncrease(oldTimeframeStart, oldTimeframeEnd);
        double caseIncreaseNewTimeframe = getAbsoluteCaseIncrease(newTimeframeStart, newTimeframeEnd);
        return caseIncreaseNewTimeframe / caseIncreaseOldTimeframe;
    }

    public double targetTotalNumberOfInfections(double rTarget) {
        int activeCasesYesterday = jhuData.getCountryDataByDaysSince(1).getActive();
        double rCurrent = getRValueJhu(7);
        return activeCasesYesterday / rCurrent * rTarget;
    }

    // 1. Erstelle Liste mit "daysSince"-Werten, die für die Berechnung relevant sind
    // 2. Frage Case-Daten mit der Liste der "days-Since"-Werte ab
    // 3. Berechne für die gegebenen Tage neue Fälle pro Tag
    // 4. Berechne, um wie viel die neuen Fälle pro Tag im Vergleich zum Vortag steigen. Sinken == negative Werte.
    // 5. Lade akute Fälle (neuste verfügbare Daten)
    // 6. Berechne Tage mit gegebener Formel
    public double daysUntilRTargetIsReached(int daysInTimeframe, double rTarget) {
        List<Integer> requestCasesFor = new ArrayList<>();
        for (int i = daysInTimeframe + 1; i >= 1; i--) { // +1 needed because one more day is needed to calculate new cases
            requestCasesFor.add(i);
        }

        List<CountryData> countryDataList = jhuData.getCountryDataForDaysSinceList(requestCasesFor);

        List<Integer> newCasesInTimeframe = new ArrayList<>();
        for (int i = 1; i < countryDataList.size(); i++) {
            int currentConfirmed = countryDataList.get(i).getConfirmed();
            int previousConfirmed = countryDataList.get(i - 1).getConfirmed();

            newCasesInTimeframe.add(currentConfirmed - previousConfirmed);
        }

        int avgNewCasesIncrease = 0;
        for (int i = 1; i < newCasesInTimeframe.size(); i++) {
            int newCasesCurrent = newCasesInTimeframe.get(i);
            int newCasesPrevious = newCasesInTimeframe.get(i - 1);
            avgNewCasesIncrease += newCasesCurrent - newCasesPrevious;
        }
        avgNewCasesIncrease /= newCasesInTimeframe.size();

        int avgNewCasesDecrease = -avgNewCasesIncrease;


        int activeCasesYesterday = countryDataList.get(countryDataList.size() - 1).getActive();
        return (activeCasesYesterday - targetTotalNumberOfInfections(rTarget)) / avgNewCasesDecrease;
    }

    public double calculateIncidenceValueLastSevenDays(){
        DataRKI stateData = readerRKI.readData();
        double result = 0;
        double value = 0;
        System.out.println(stateData.getData().length);
        for(int i = 0; i <= stateData.getData().length - 1; i++){
            value = stateData.getData()[stateData.getData().length - i - 1].getAttributes().getCases7_bl_per_100k();
            result += value;
        }
        result = (result/stateData.getData().length);
        return result;
    }

    //andere Methoden reinmachen
    public static void main(String[] args) {
        RobertKochInstitutData rkiData = new RobertKochInstitutData();
    //    System.out.println(rkiData.daysUntilRTargetIsReached(7, 0.7));
        System.out.println(rkiData.calculateIncidenceValueLastSevenDays());
    }
}
