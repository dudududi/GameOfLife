package com.agh.gameoflife.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * Created by dudek on 4/21/16.
 */
public class Cell {
    private BooleanProperty isAlive;
    private Rectangle rectangle;
    private List<Cell> neighbourhood;

    public Cell() {
        isAlive = new SimpleBooleanProperty(false);
        rectangle = new Rectangle(0, 0, 10, 10);
        rectangle.fillProperty().bind(Bindings.when(isAlive).then(Color.BLACK).otherwise(Color.WHITE));
        rectangle.setOnMouseClicked(event -> isAlive.setValue(!isAlive.get()));
    }

    public boolean isAlive() {
        return isAlive.get();
    }

    public void setAlive(boolean alive) {
        isAlive.setValue(alive);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public List<Cell> getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(List<Cell> neighbourhood) {
        this.neighbourhood = neighbourhood;
    }
}
