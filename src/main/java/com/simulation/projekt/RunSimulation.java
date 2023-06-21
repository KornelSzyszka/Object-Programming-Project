package com.simulation.projekt;
import java.util.ArrayList;

public class RunSimulation {

    int firstPhaseTimer = 60;
    int secondPhaseTimer = 240;
    OptionsController envParams = new OptionsController();
    public Environment simulation = new Environment(envParams.getTemperature(),envParams.getPollution(),envParams.getDayTimeLength());
    public ArrayList<Cell> SpeciesList = new ArrayList<>();
    public void setParameters() {
        this.SpeciesList.add(this.firstCell);
    }
    Cell firstCell = new Cell(70, 5, 30, 50, 2, false);
    public ArrayList<Cell> getList(){
        return SpeciesList;
    }

    public void firstPhase() {
        System.out.println("\n" + simulation.temperature + "\n" + simulation.pollution + "\n" + simulation.dayTimeLength);
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
    public void startSimulation() {

        SimulationController simulatorController = new SimulationController();
        simulatorController.setSpeciesList(SpeciesList);
    }

}