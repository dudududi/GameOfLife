package com.agh.gameoflife.model.rules;

import com.agh.gameoflife.model.Cell;
import com.agh.gameoflife.model.Rule;

/**
 * Created by dudek on 4/28/16.
 */
public class FredkinGameRule implements Rule {
    @Override
    public boolean shouldCellBeAlive(Cell cell) {
        int count = cell.getNeighbourhood().stream()
                .filter(Cell::isAlive)
                .mapToInt(c -> 1)
                .sum();

        return !cell.isAlive() && count%2 != 0;
    }


    @Override
    public String toString(){
        return "Gra Fredkina";
    }
}
