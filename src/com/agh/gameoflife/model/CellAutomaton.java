package com.agh.gameoflife.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dudek on 4/21/16.
 */
public class CellAutomaton {
    private Cell[][] cells;
    private int width, height;

    public CellAutomaton(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void init(CellNeighbourhood neighbourhood) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                List<Cell> adjacentCells = new ArrayList<>();
                for (CoordinatePair index : neighbourhood.getCellNeighbourhood()) {
                    int x = i + index.x;
                    int y = j + index.y;
                    if (neighbourhood.isPeriodic()) {
                        x = x >= 0 ? x % width : width + (x % width );
                        y = y >= 0 ? y % height : height + (y % height );
                        adjacentCells.add(cells[x][y]);
                    } else if (x >= 0 && y >= 0 && x < width && y < height) {
                        adjacentCells.add(cells[x][y]);
                    }
                }
                cells[i][j].setNeighbourhood(adjacentCells);
            }
        }
    }

    public void next(Rule rule) {
        boolean[][] nextStep = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                nextStep[i][j] = rule.shouldCellBeAlive(cells[i][j]);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].setAlive(nextStep[i][j]);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void clear() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].setAlive(false);
            }
        }
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

}
