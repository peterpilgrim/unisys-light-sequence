/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.clients.unisys;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * The type LightDisplayJavaFXApplication - visible proof of the algorithm is working as expected
 *
 * TODO: Fix the background thread Task so that it cancelable from the GUI. When the close event happens shutdown the task!
 *
 * @author Peter Pilgrim (peter)
 */
public class LightDisplayJavaFXApplication extends Application {

    private final static int ROWS = 10;
    private final static int COLUMNS = 10;
    private final LightContainer container = new LightContainer(20);
    private LightDisplayStrategy strategy;
    private List<Label> lightBoxList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(2, 2, 2, 2));


        int row = 0;
        int column = 0;
        for (int k = 0; k < container.size(); ++k) {
            final Label lightBox = new Label("X");
            lightBox.setStyle("");
            lightBox.setPrefWidth(60);
            lightBox.setPrefHeight(30);
            lightBoxList.add(lightBox);
            grid.add(lightBox, column, row);
            ++column;
            if (column >= COLUMNS) {
                column = 0;
                ++row;
            }
        }

        strategy = new AlternateLightDisplayStrategy(container, 60, 30);

        primaryStage.setScene(new Scene(grid));

        primaryStage.setTitle("Light Display JavaFX Application");
        primaryStage.show();

        updateColours();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.printf("Strategy: [%s]\n", strategy.getClass().getSimpleName());
                while (strategy != null) {
                    final int sleepTime = strategy.getPulseLength();
                    System.out.printf("SEQ COUNT: %4d\n", strategy.getSequenceCount());
                    strategy.nextSequence();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateColours();
                        }
                    });

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace(System.err);
                    }
                }
                return null;
            }
        };

        new Thread(task).start();
    }

    public void updateColours() {
        for (int j = 0; j < container.size(); ++j) {
            final FairyLight light = container.getLight(j);
            final Label lightBox = lightBoxList.get(j);
            if (light.isLit()) {
                switch (light.getColour()) {
                    case RED:
                        lightBox.setStyle("-fx-background-color: red; -fx-color: white;");
                        break;
                    case GREEN:
                        lightBox.setStyle("-fx-background-color: green; -fx-color: white;");
                        break;
                    case WHITE:
                        lightBox.setStyle("-fx-background-color: white; -fx-color: black;");
                        break;
                }
            } else {
                lightBox.setStyle("-fx-background-color: black; -fx-color: darkgrey;");
            }

        }
    }
}
