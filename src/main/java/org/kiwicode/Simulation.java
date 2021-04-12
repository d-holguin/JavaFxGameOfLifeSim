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

public class Simulation {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    int width;
    int height;
    int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void printBoard() {
        System.out.println("----");
        for (int y = 0; y < height; y++) {
            String line = "|";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == DEAD) {
                    line += ".";
                } else {
                    line += "*";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("----\n");

    }

    public void setAlive(int x, int y) {
        this.setState(x, y, ALIVE);
    }

    public void setDead(int x, int y) {
        this.setState(x, y, DEAD);
    }

    public void setState(int x, int y, int state) {
        if (x < 0 || x >= width) {
            return;
        }
        if (y < 0 || y >= height) {
            return;
        }
        this.board[x][y] = state;
    }

    public int countAliveNeighbors(int x, int y) {
        int count = 0;
        //tops 3 cells
        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);
        //cells to the left and right of the cell
        count += getState(x - 1, y);
        count += getState(x + 1, y);
        //bottom 3 cells
        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);


        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {

            return DEAD;
        }
        if (y < 0 || y >= height) {
            return DEAD;
        }
        return this.board[x][y];
    }


    public void step() {

        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            String line = "|";

            for (int x = 0; x < width; x++) {

                int aliveNeighbors = countAliveNeighbors(x, y);
                if (getState(x, y) == ALIVE) {

                    if (aliveNeighbors < 2) {
                        newBoard[x][y] = DEAD;
                    } else if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                        newBoard[x][y] = ALIVE;

                    } else if (aliveNeighbors > 3) {
                        newBoard[x][y] = DEAD;
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        newBoard[x][y] = ALIVE;
                    }
                }
            }
        }
        this.board = newBoard;
    }
}
