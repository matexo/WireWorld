/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Matexo
 */
public class ElectronHead implements State, Elements {

    @Override
    public void markState(int x, int y, BoardState board) {
        board.setCell(new ElectronHead(), x, y);
    }

    @Override
    public State nextState(int x, int y, BoardState board) {
        return new ElectronTail();
    }

    @Override
    public String writeState(int x, int y) {
        return "ElectronHead " + x + " " + y + "\r\n";
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

}
