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
public class ElectronTail implements State, Element {

    @Override
    public void markElement(int x, int y, BoardState board)
    {
        if (!board.isEdge(x, y))
        {
            board.setCell(new ElectronTail(), x, y);
        }
    }

    @Override
    public State nextState(int x, int y, BoardState board)
    {
        return new Conductor();
    }

    @Override
    public String writeState(int x, int y)
    {
        return "ElectronTail " + x + " " + y + "\r\n";
    }

    @Override
    public Color getColor()
    {
        return Color.BLUE;
    }

}
