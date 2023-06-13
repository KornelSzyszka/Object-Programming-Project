package com.simulation.projekt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class RunSimulation {

    int firstPhaseTimer = 60;
    int secondPhaseTimer = 240;
    public Environment simulation = new Environment(30,0,7);
    public ArrayList<Cell> SpeciesList = new ArrayList<>();
    public void setParameters() {
        this.SpeciesList.add(this.firstCell);
    }
    Cell firstCell = new Cell(70, 5, 30, 50, 2, false);
    public ArrayList<Cell> getList(){
        return SpeciesList;
    }


    public void firstPhase() {
        for (int timer = 0; timer < this.firstPhaseTimer; timer++) {
            this.simulation.periodicEvents(timer);
            this.simulation.randomEvents();
            for (int i = 0; i < this.SpeciesList.size(); i++) {
                Interactions.cellHabitability(this.SpeciesList.get(i), this.simulation);
                if (this.SpeciesList.get(i).canDivide() && this.SpeciesList.get(i).speciesAmount != 0)
                    this.SpeciesList.get(i).speciesAmount++;
                if (this.SpeciesList.get(i).canMutate() && this.SpeciesList.get(i).canDivide() && this.SpeciesList.get(i).speciesAmount != 0)
                    this.SpeciesList.add(this.SpeciesList.get(i).division());
            }
        }
    }

    public void secondPhase() {
        for (int timer = 0; timer < this.secondPhaseTimer; timer++) {
            this.simulation.periodicEvents(timer);
            this.simulation.randomEvents();
            for (Cell cell : this.SpeciesList) {
                if (cell.canDivide() && cell.speciesAmount != 0)
                    cell.speciesAmount++;
            }
        }
    }

    public void printSim() {
        for (int i = 0; i < SpeciesList.size(); i++) {
            Cell printcell = SpeciesList.get(i);
            if (SpeciesList.get(i).speciesAmount >= 0) {
                System.out.print("\nID" + i + "\nLiczba: " + printcell.speciesAmount
                        + "\nPlodnosc: " + printcell.fertility + "\nDlugosc zycia: " + printcell.lifeExpectancy
                        + "\nPreferowana temperatura: " + BigDecimal.valueOf(printcell.temperatureResistance).setScale(2, RoundingMode.HALF_UP)
                        + "\nOdpornosc na zanieczyszczenia: " + BigDecimal.valueOf(printcell.pollutionResistance).setScale(2, RoundingMode.HALF_UP)
                        + "\nMetabolizm: " + BigDecimal.valueOf(printcell.metabolism).setScale(2, RoundingMode.HALF_UP) + "\nSaprobiont: " + printcell.isSaprobiont + "\n");
            }
        }

        System.out.print("\nLiczba gatunkow powstalych w symulacji: " + SpeciesList.size()
                + "\nTemperatura: " + BigDecimal.valueOf(simulation.temperature).setScale(2, RoundingMode.HALF_UP)
                + "\nZanieczyszczenia: " + BigDecimal.valueOf(simulation.pollution).setScale(2, RoundingMode.HALF_UP));

    }

    public void startSimulation() {

        SimulationController simulatorController = new SimulationController();
        simulatorController.setSpeciesList(SpeciesList);
    }

}