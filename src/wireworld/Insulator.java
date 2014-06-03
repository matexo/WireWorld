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
public class Insulator implements State, Element {

    @Override
    public void markElement(int x, int y, Containter board)
    {
        if (!board.isEdge(x, y))
        {
            board.setCell(new Insulator(), x, y);
        }
    }

    @Override
    public State nextState(int x, int y, Containter board)
    {
        return new Insulator();
    }

    @Override
    public String writeState(int x, int y)
    {
        return "";
    }

    @Override
    public Color getColor()
    {
        return Color.BLACK;
    }

}
