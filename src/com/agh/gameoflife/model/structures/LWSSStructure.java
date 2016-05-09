package com.agh.gameoflife.model.structures;

import com.agh.gameoflife.model.CoordinatePair;
import com.agh.gameoflife.model.Structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dudek on 4/28/16.
 */
public class LWSSStructure extends Structure {

    public LWSSStructure(String name) {
        super(name);
    }

    @Override
    protected List<CoordinatePair> getStructureDefinition() {
        List<CoordinatePair> relativeCoordinates = new ArrayList<>();
        relativeCoordinates.add(new CoordinatePair(0,0));
        relativeCoordinates.add(new CoordinatePair(3, 0));
        relativeCoordinates.add(new CoordinatePair(4, -1));
        relativeCoordinates.add(new CoordinatePair(4, -2));
        relativeCoordinates.add(new CoordinatePair(4, -3));
        relativeCoordinates.add(new CoordinatePair(3, -3));
        relativeCoordinates.add(new CoordinatePair(2, -3));
        relativeCoordinates.add(new CoordinatePair(1, -3));
        relativeCoordinates.add(new CoordinatePair(0, -2));

        return relativeCoordinates;
    }
}
