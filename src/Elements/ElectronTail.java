/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import Elements.Conductor;
import java.awt.Color;

import Containter.Board;
/**
 *
 * @author Matexo
 */
public class ElectronTail implements State, Element {

    @Override
    public void markElement(int x, int y, Board board)
    {
        if (!board.isEdge(x, y))
        {
            board.setCell(new ElectronTail(), x, y);
        }
    }

    @Override
    public State nextState(int x, int y, int neighborCounter)
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
