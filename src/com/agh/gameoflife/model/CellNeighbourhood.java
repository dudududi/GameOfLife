package com.agh.gameoflife.model;

import java.util.List;

/**
 * Created by dudek on 4/21/16.
 */
public abstract class CellNeighbourhood {
    private boolean isPeriodic;

    protected CellNeighbourhood() {
        isPeriodic = true;
    }

    protected CellNeighbourhood(boolean isPeriodic) {
        this.isPeriodic = isPeriodic;
    }

    boolean isPeriodic() {
        return isPeriodic;
    }

    protected abstract List<CoordinatePair> getCellNeighbourhood();
}
