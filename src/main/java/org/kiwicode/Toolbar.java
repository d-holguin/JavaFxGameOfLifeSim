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

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        this.getItems().addAll(draw, erase, step);

    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("Step");
        this.mainView.getSimulation().step();
        this.mainView.draw();

    }
    private void handleErase(ActionEvent actionEvent) {
        System.out.println("Erase");
        this.mainView.setDrawMode(Simulation.DEAD);
        this.mainView.draw();
    }
    private void handleDraw(ActionEvent actionEvent) {
        System.out.println("Draw");
        this.mainView.setDrawMode(Simulation.ALIVE);
        this.mainView.draw();
    }
}
