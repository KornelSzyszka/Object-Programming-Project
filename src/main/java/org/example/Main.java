package org.example;
public class Main {
    public static void main(String[] args) {
        RunSimulation firstSimulation = new RunSimulation();

        firstSimulation.setParameters();
        firstSimulation.firstPhase();
        firstSimulation.secondPhase();
        firstSimulation.printSim();
    }
}