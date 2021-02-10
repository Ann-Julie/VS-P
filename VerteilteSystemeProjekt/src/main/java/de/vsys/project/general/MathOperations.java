package de.vsys.project.general;

public class MathOperations {
    public double roundNumber(double number){
        number = number * 100;
        number = (int) number;
        number = (double) number / 100;
        return number;
    }

}
