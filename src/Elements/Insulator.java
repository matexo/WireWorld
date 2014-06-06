/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.awt.Color;

import Containter.Board;
/**
 *
 * @author Matexo
 */
public class Insulator implements State, Element {

    @Override
    public void markElement(int x, int y, Board board)
    {
        if (!board.isEdge(x, y))
        {
            board.setCell(new Insulator(), x, y);
        }
    }

    @Override
    public State nextState(int neighborCounter)
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
