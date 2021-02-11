package de.vsys.project.general.data;

public class AllData {
    /*
    @author: Mike Witkowski
    This class is the template for the data which we return to the user
     */

    private int newInfectionsLastTwentyFourHours;
    private int totalInfections;
    private double increaseLastTwentyFourHours;
    private double averageIncreaseLastNDays;
    private int targetTotalInfection;
    private int forecastNecessaryLockdownDays;
    private int incidenceValueLastSevenDays;

    public int getIncidenceValueLastSevenDays() {
        return incidenceValueLastSevenDays;
    }

    public void setIncidenceValueLastSevenDays(int incidenceValueLastSevenDays) {
        this.incidenceValueLastSevenDays = incidenceValueLastSevenDays;
    }

    public int getNewInfectionsLastTwentyFourHours() {
        return newInfectionsLastTwentyFourHours;
    }

    public void setNewInfectionsLastTwentyFourHours(int newInfectionsLastTwentyFourHours) {
        this.newInfectionsLastTwentyFourHours = newInfectionsLastTwentyFourHours;
    }

    public int getTotalInfections() {
        return totalInfections;
    }

    public void setTotalInfections(int totalInfections) {
        this.totalInfections = totalInfections;
    }

    public double getIncreaseLastTwentyFourHours() {
        return increaseLastTwentyFourHours;
    }

    public void setIncreaseLastTwentyFourHours(double increaseLastTwentyFourHours) {
        this.increaseLastTwentyFourHours = increaseLastTwentyFourHours;
    }

    public double getAverageIncreaseLastNDays() {
        return averageIncreaseLastNDays;
    }

    public void setAverageIncreaseLastNDays(double averageIncreaseLastNDays) {
        this.averageIncreaseLastNDays = averageIncreaseLastNDays;
    }

    public int getTargetTotalInfection() {
        return targetTotalInfection;
    }

    public void setTargetTotalInfection(int targetTotalInfection) {
        this.targetTotalInfection = targetTotalInfection;
    }

    public int getForecastNecessaryLockdownDays() {
        return forecastNecessaryLockdownDays;
    }

    public void setForecastNecessaryLockdownDays(int forecastNecessaryLockdownDays) {
        this.forecastNecessaryLockdownDays = forecastNecessaryLockdownDays;
    }
}
