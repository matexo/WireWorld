/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Color;

/**
 *
 * @author Matexo
 */
public class StateContext implements State {

    private State cellState;

    public StateContext() {

    }

    public StateContext(State state) {
        cellState = state;
    }

    public void setState(State state) {
        cellState = state;
    }

    public State getState() {
        return cellState;
    }

    @Override
    public State nextState(int x, int y, BoardState board) {
        return cellState.nextState(x, y, board);
    }

    @Override
    public Color getColor() {
        return cellState.getColor();
    }

    @Override
    public String writeState(int x, int y) {
        return cellState.writeState(x, y);
    }

}
