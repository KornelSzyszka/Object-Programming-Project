package com.simulation.projekt;

import java.util.HashMap;
import java.util.Random;

public class Cell implements IEvents{
    protected int speciesAmount;
    protected int fertility;
    protected int lifeExpectancy;
    protected double temperatureResistance;
    protected double pollutionResistance;
    protected double metabolism;
    protected boolean isSaprobiont;
    private final Random roll = new Random();

    public Cell(int fertility, int lifeExpectancy, double temperatureResistance, double pollutionResistance, double metabolism, boolean isSaprobiont){
        this.speciesAmount = 1;
        this.fertility = fertility;
        this.lifeExpectancy = lifeExpectancy;
        this.temperatureResistance = temperatureResistance;
        this.pollutionResistance = pollutionResistance;
        this.metabolism = metabolism;
        this.isSaprobiont = isSaprobiont;
    }
    public int getFertility() {
        return fertility;
    }
    public int getLifeExpectancy() {
        return lifeExpectancy;
    }
    public double getTemperatureResistance() {
        return temperatureResistance;
    }
    public double getPollutionResistance() {
        return pollutionResistance;
    }
    public double getMetabolism() {
        return metabolism;
    }
    public boolean isSaprobiont() {
        return isSaprobiont;
    }
    public int getSpeciesAmount() { return this.speciesAmount; }

    public Cell division(){

        double[] params;
        params = paramCheck(mutation());
        int eventID = roll.nextInt(99);
        boolean saprobiont = eventID < 10;

        return new Cell(this.fertility + (int) params[0], this.lifeExpectancy + (int) params[1],
                    this.temperatureResistance + params[2], this.pollutionResistance + params[3],
                    this.metabolism + params[4], saprobiont);
    }

    public double[] paramCheck(double[] params){
        params[0] = (this.fertility + (int) params[0]) < 0 ? 0 : params[0];
        params[1] = (this.lifeExpectancy + (int) params[1]) < 0 ? 0 : params[1];
        params[2] = (this.temperatureResistance + params[2]) < 0 ? 0 : params[2];
        params[3] = (this.pollutionResistance + params[3]) < 0 ? 0 : params[3];
        params[4] = (this.metabolism + params[4]) < 0 ? 0 : params[4];
        return params;
    }
    public boolean canDivide() {
        for (int i = 0; i < this.speciesAmount; i++) {
            int divisionChance = roll.nextInt(99);
            if (divisionChance <= this.fertility)
                return true;
        }
        return false;
    }
    public boolean canMutate() {
        for (int i = 0; i < this.speciesAmount; i++) {
            int mutationChance = roll.nextInt((int) (2000/this.metabolism));
            if (mutationChance <= 10)
                return true;
        }
        return false;
    }
    public double[] mutation() {

        double[] params;

        int eventID = roll.nextInt(10);
        int impact = roll.nextInt(100);
        double paramImpact = (double)impact/10;
            HashMap<Integer, double[]> eventParams = new HashMap<>();
            eventParams.put(0, new double[]{paramImpact, 0, 0, 0, 0});
            eventParams.put(1, new double[]{-paramImpact, 0, 0, 0, 0});
            eventParams.put(2, new double[]{0, paramImpact, 0, 0, 0});
            eventParams.put(3, new double[]{0, -paramImpact, 0, 0, 0});
            eventParams.put(4, new double[]{0, 0, paramImpact, 0, 0});
            eventParams.put(5, new double[]{0, 0, -paramImpact, 0, 0});
            eventParams.put(6, new double[]{0, 0, 0, paramImpact, 0});
            eventParams.put(7, new double[]{0, 0, 0, -paramImpact, 0});
            eventParams.put(8, new double[]{paramImpact, 0, 0, 0, paramImpact/10});
            eventParams.put(9, new double[]{0, -paramImpact, 0, 0, paramImpact/10});
            //fertility lifeExpectancy temperatureResistance pollutionResistance metabolism
            params = eventParams.get(eventID);
            return params;
    }

    @Override
    public void randomEvents(){

    }
    @Override
    public void periodicEvents(int turnsAmount) {
            if( ( turnsAmount + lifeExpectancy ) % this.lifeExpectancy == 0)
                this.speciesAmount -= (int)(metabolism);
        }
}





