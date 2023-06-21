package com.simulation.projekt;

public class Interactions {
    public static void cellHabitability(Cell cell, Environment env){
        double maxTemp = cell.temperatureResistance+10;
        double minTemp = cell.temperatureResistance-10;
        double pollutionChange = cell.speciesAmount * cell.metabolism/10;

        if (env.temperature < minTemp)
            cell.speciesAmount--;

        if (env.temperature > maxTemp)
            cell.speciesAmount--;

        if (env.pollution > cell.pollutionResistance)
            cell.speciesAmount--;

        if (env.pollution == 0 && cell.isSaprobiont)
            cell.speciesAmount--;

        env.pollution = cell.isSaprobiont ? env.pollution - pollutionChange : env.pollution + pollutionChange;

        if (env.pollution < 0)
            env.pollution = 0;
    }
}
