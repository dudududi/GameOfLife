package com.agh.gameoflife.model.structures;

import com.agh.gameoflife.model.CoordinatePair;
import com.agh.gameoflife.model.Structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dudek on 4/27/16.
 */
public class BlinkerStructure extends Structure {

    public BlinkerStructure(String name) {
        super(name);
    }

    @Override
    protected List<CoordinatePair> getStructureDefinition() {
        List<CoordinatePair> relativesCoordinates = new ArrayList<>();
        relativesCoordinates.add(new CoordinatePair(-1, 0));
        relativesCoordinates.add(new CoordinatePair(0, 0));
        relativesCoordinates.add(new CoordinatePair(1, 0));
        return relativesCoordinates;
    }
}
