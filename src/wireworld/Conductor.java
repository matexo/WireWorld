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
public class Conductor implements State, Element {

    @Override
    public void markElement(int x, int y, BoardState board)
    {
        if (!board.isEdge(x, y))
        {
            board.setCell(new Conductor(), x, y);
        }
    }

    @Override
    public State nextState(int x, int y, BoardState board)
    {
        int counter = board.neighborsCounter(x, y, new ElectronHead());
        if (counter != 1 && counter != 2)
        {
            return new Conductor();
        }
        else
        {
            return new ElectronHead();
        }
    }

    @Override
    public String writeState(int x, int y)
    {
        return "Conductor " + x + " " + y + "\r\n";
    }

    @Override
    public Color getColor()
    {
        return Color.YELLOW;
    }

}
