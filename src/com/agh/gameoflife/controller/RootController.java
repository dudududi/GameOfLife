package com.agh.gameoflife.controller;

import com.agh.gameoflife.Main;
import com.agh.gameoflife.model.*;
import com.agh.gameoflife.model.Cell;
import com.agh.gameoflife.model.neighbourhoods.MooreNeighbourhood;
import com.agh.gameoflife.model.rules.GameOfLifeRule;
import com.agh.gameoflife.model.rules.FredkinGameRule;
import com.agh.gameoflife.model.structures.BeehiveStructure;
import com.agh.gameoflife.model.structures.BlinkerStructure;
import com.agh.gameoflife.model.structures.GliderStructure;
import com.agh.gameoflife.model.structures.LWSSStructure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RootController {
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 40;

    private Main mainApp;

    @FXML
    private GridPane table;
    @FXML
    private ToggleButton startButton;
    @FXML
    private TextField widthField, heightField;
    @FXML
    private Button clearButton, generateButton, addStructure;
    @FXML
    private ComboBox<Structure> selectStructure;
    @FXML
    private ComboBox<Rule> selectRule;
    @FXML
    private CheckBox periodicBC;

    private Timeline animation;
    private Rule automatonRule;
    private CellNeighbourhood neighbourhood;
    private CellAutomaton cellAutomaton;


    @FXML
    private void initialize() {
        neighbourhood = new MooreNeighbourhood();
        generateAutomaton(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        periodicBC.setSelected(true);
        periodicBC.selectedProperty().addListener((observable, oldValue, newValue) -> {
            neighbourhood = new MooreNeighbourhood(newValue);
            cellAutomaton.init(neighbourhood);
        });
        widthField.setText(Integer.toString(DEFAULT_WIDTH));
        heightField.setText(Integer.toString(DEFAULT_HEIGHT));
        generateButton.setOnMouseClicked(event -> {
            animation.stop();
            table.setDisable(false);
            cellAutomaton.clear();
            int width = Integer.valueOf(widthField.getText());
            int height = Integer.valueOf(heightField.getText());
            generateAutomaton(width, height);
        });

        clearButton.setOnMouseClicked(event -> cellAutomaton.clear());

        automatonRule = new GameOfLifeRule();
        animation = new Timeline(new KeyFrame(Duration.millis(100), event -> cellAutomaton.next(automatonRule)));
        animation.setCycleCount(Timeline.INDEFINITE);
        startButton.setToggleGroup(new ToggleGroup());
        startButton.getToggleGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                animation.stop();
                table.setDisable(false);
                clearButton.setDisable(false);
                generateButton.setDisable(false);
                selectRule.setDisable(false);
                periodicBC.setDisable(false);
                startButton.setText("START");
            } else {
                table.setDisable(true);
                clearButton.setDisable(true);
                generateButton.setDisable(true);
                selectRule.setDisable(true);
                periodicBC.setDisable(true);
                animation.play();
                startButton.setText("STOP");
            }
        });

        generateStructures();
        addStructure.setOnMouseClicked(event -> addStructure());

        generateRules();
        selectRule.valueProperty().addListener((observable, oldValue, newValue) -> {
            automatonRule = newValue;
        });

    }

    private void generateAutomaton(int width, int height) {
        cellAutomaton = new CellAutomaton(width, height);
        table.getChildren().clear();
        Cell[][] cells = cellAutomaton.getCells();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table.add(cells[i][j].getRectangle(), i, j);
            }
        }
        cellAutomaton.init(neighbourhood);
    }

    private void generateStructures() {
        ObservableList<Structure> structures = FXCollections.observableArrayList(
                new BlinkerStructure("Blinker"),
                new BeehiveStructure("Beehive"),
                new GliderStructure("Glider"),
                new LWSSStructure("LWSS")
        );
        selectStructure.getItems().addAll(structures);
        selectStructure.getSelectionModel().selectFirst();
    }

    private void generateRules() {
        ObservableList<Rule> rules = FXCollections.observableArrayList(
                new GameOfLifeRule(),
                new FredkinGameRule()
        );
        selectRule.getItems().addAll(rules);
        selectRule.getSelectionModel().selectFirst();
    }

    private void addStructure() {
        Structure toAdd = selectStructure.getValue();
        toAdd.drawStructureOnAutomaton(cellAutomaton);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
