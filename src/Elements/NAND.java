/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Elements;

import Containter.Board;

/**
 *
 * @author Matexo
 */
public class NAND implements Element{
 
    @Override
    public void markElement(int x, int y, Board board)
    {
        Element conductor = new Conductor();
        conductor.markElement(x, y,  board);
        conductor.markElement(x+1, y-1,  board);
        conductor.markElement(x+2, y-1,  board);
        conductor.markElement(x+3, y,  board);
        conductor.markElement(x+3, y+1,  board);
        conductor.markElement(x+2, y+1,  board);
        conductor.markElement(x+3, y+2,  board);
        conductor.markElement(x+2, y+3,  board);
        conductor.markElement(x+1, y+3,  board);
        conductor.markElement(x, y+2,  board);
        conductor.markElement(x+4, y+3,  board);
        conductor.markElement(x+5, y+3,  board);
        conductor.markElement(x+4, y+1,  board);    }
}
