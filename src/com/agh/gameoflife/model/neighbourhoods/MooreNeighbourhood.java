package com.agh.gameoflife.model.neighbourhoods;

import com.agh.gameoflife.model.CellNeighbourhood;
import com.agh.gameoflife.model.CoordinatePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dudek on 4/21/16.
 */
public class MooreNeighbourhood extends CellNeighbourhood {
    public MooreNeighbourhood() {
        super();
    }

    public MooreNeighbourhood(boolean isPeriodic) {
        super(isPeriodic);
    }

    @Override
    protected List<CoordinatePair> getCellNeighbourhood() {
        List<CoordinatePair> indices = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) indices.add(new CoordinatePair(i, j));
            }
        }
        return indices;
    }
}
