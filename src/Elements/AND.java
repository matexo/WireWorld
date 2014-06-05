/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Elements;


import Containter.Board;
/**
 *
 * @author uesr
 */
public class AND implements Element{

    @Override
    public void markElement(int x, int y, Board board) {
         board.setCell(new Conductor(), x, y);
         board.setCell(new Conductor(), x - 1, y - 1);
         board.setCell(new Conductor(), x - 1, y);
         board.setCell(new Conductor(), x - 1, y + 1);
         board.setCell(new Conductor(), x, y - 1);
         board.setCell(new Conductor(), x, y + 1);
         board.setCell(new Conductor(), x + 1, y - 1);
         board.setCell(new Conductor(), x + 1, y);
         board.setCell(new Conductor(), x + 1, y + 1);
    }
    
}
