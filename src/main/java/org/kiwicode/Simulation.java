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
                if (this.board[x][y] == 0) {
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
        this.board[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }

    public int countAliveNeighbors(int x, int y) {
        int count = 0;


        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);


        count += getState(x - 1, y);
        count += getState(x + 1, y);


        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);


        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {

            return 0;
        }

        if (y < 0 || y >= height) {
            return 0;
        }

        return this.board[x][y];

    }


    public void step() {

        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            String line = "|";

            for (int x = 0; x < width; x++) {
                int aliveNeighbors = countAliveNeighbors(x, y);

                if (getState(x, y) == 1) {
                    if (aliveNeighbors < 2) {
                        newBoard[x][y] = 0;
                    } else if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                        newBoard[x][y] = 1;

                    } else if (aliveNeighbors > 3) {
                        newBoard[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        newBoard[x][y] = 1;
                    }


                }


            }
        }
        this.board = newBoard;


    }

    public static void main(String[] args) {
        Simulation sim = new Simulation(8, 5);

        sim.setAlive(2, 2);
        sim.setAlive(3, 2);
        sim.setAlive(4, 2);

        sim.printBoard();

        //System.out.println(sim.countAliveNeighbors(3, 2));
        sim.step();
        sim.printBoard();

        sim.step();
        sim.printBoard();


        sim.step();
        sim.printBoard();


        sim.step();
        sim.printBoard();


        sim.step();
        sim.printBoard();


    }


}
