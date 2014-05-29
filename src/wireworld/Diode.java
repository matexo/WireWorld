/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

/**
 *
 * @author uesr
 */
public class Diode implements Element {

    @Override
    public void markState(int x, int y, BoardState board) {
        board.setCell(new Conductor(), x, y);
        if (!board.isEdge(x - 1, y)) {
            board.setCell(new Conductor(), x - 1, y);
        }
        if (!board.isEdge(x - 2, y )) {
            board.setCell(new Conductor(), x - 2, y );
        }
        if (!board.isEdge(x, y - 1)) {
            board.setCell(new Conductor(), x, y - 1);
        }
        if (!board.isEdge(x, y + 1)) {
            board.setCell(new Conductor(), x, y + 1);
        }
        if (!board.isEdge(x + 1, y - 1)) {
            board.setCell(new Conductor(), x + 1, y - 1);
        }
        if (!board.isEdge(x + 2, y)) {
            board.setCell(new Conductor(), x + 2, y);
        }
        if (!board.isEdge(x + 1, y + 1)) {
            board.setCell(new Conductor(), x + 1, y + 1);
        }
        if (!board.isEdge(x + 3, y )) {
            board.setCell(new Conductor(), x + 3, y );
        }

    }
}
