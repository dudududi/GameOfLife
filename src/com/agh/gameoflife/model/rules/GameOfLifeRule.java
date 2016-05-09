package com.agh.gameoflife.model.rules;

import com.agh.gameoflife.model.Cell;
import com.agh.gameoflife.model.Rule;

/**
 * Created by dudek on 4/21/16.
 */
public class GameOfLifeRule implements Rule {

    @Override
    public boolean shouldCellBeAlive(Cell cell) {
        int count = cell.getNeighbourhood().stream()
                .filter(Cell::isAlive)
                .mapToInt(c -> 1)
                .sum();
        if (cell.isAlive() && (count == 2 || count == 3)) {
            return true;
        } else if ((!cell.isAlive()) && count == 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "Gra w życie";
    }
}
