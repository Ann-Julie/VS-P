package de.vsys.project.general.data;

public class AllData {
    /*
    @author: Mike Witkowski
    This class is the template for the data which we return to the user
     */
    private int newInfectionsLastTwentyFourHours;
    private int totalInfections;
    private int increaseLastTwentyFourHours;
    private double averageIncreaseLastNDays;
    private double targetTotalInfection;
    private double forecastNecessaryLockdownDays;
    private double incidenceValueLastSevenDays;

    public double getIncidenceValueLastSevenDays() {
        return incidenceValueLastSevenDays;
    }

    public void setIncidenceValueLastSevenDays(double incidenceValueLastSevenDays) {
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

    public void setIncreaseLastTwentyFourHours(int increaseLastTwentyFourHours) {
        this.increaseLastTwentyFourHours = increaseLastTwentyFourHours;
    }

    public double getAverageIncreaseLastNDays() {
        return averageIncreaseLastNDays;
    }

    public void setAverageIncreaseLastNDays(double averageIncreaseLastNDays) {
        this.averageIncreaseLastNDays = averageIncreaseLastNDays;
    }

    public double getTargetTotalInfection() {
        return targetTotalInfection;
    }

    public void setTargetTotalInfection(double targetTotalInfection) {
        this.targetTotalInfection = targetTotalInfection;
    }

    public double getForecastNecessaryLockdownDays() {
        return forecastNecessaryLockdownDays;
    }

    public void setForecastNecessaryLockdownDays(double forecastNecessaryLockdownDays) {
        this.forecastNecessaryLockdownDays = forecastNecessaryLockdownDays;
    }
}
