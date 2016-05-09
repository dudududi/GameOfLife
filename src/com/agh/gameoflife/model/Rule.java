package com.agh.gameoflife.model;

/**
 * Created by dudek on 4/21/16.
 */
public interface Rule {
    boolean shouldCellBeAlive(Cell cell);
}
