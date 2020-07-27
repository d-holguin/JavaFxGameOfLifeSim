/*
 *  Copyright (c) 2020.  Dante
 *
 *     This program GameOfLifeSimulator is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program GameOfLifeSimulator is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program GameOfLifeSimulator .  If not, see <https://www.gnu.org/licenses/>.
 *
 */

/**
 * @ author Dante
 */
package org.kiwicode;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class MainView extends VBox {

    private Button stepButton;
    private Canvas canvas;

    private Simulation sim;

    private Affine affine;

    public MainView() {

        stepButton = new Button("Step");
        this.stepButton.setOnAction(actionEvent -> {
            sim.step();
            draw();
        });
        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);


        this.getChildren().addAll(this.stepButton, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.sim = new Simulation(10, 10);

    }

    private void handleDraw(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();


        System.out.println(mouseX + ", " + mouseY);

    }

    public void draw() {

        GraphicsContext g = this.canvas.getGraphicsContext2D();

        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.sim.width; x++) {
            for (int y = 0; y < this.sim.height; y++) {
                if (this.sim.getState(x, y) == 1) {
                    g.fillRect(x, y, 1, 1);
                }

            }


        }
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.sim.width; x++) {
            g.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y <= this.sim.height; y++) {
            g.strokeLine(0, y, 10, y);


        }
    }
}
