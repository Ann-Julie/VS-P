package de.vsys.project.general;

public class MathOperations {
    public double roundNumberToTwoDecimals(double number){
        return (double) Math.round(number * 100d) / 100d;
    }
}
