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
        Element conductor = new Conductor();
        conductor.markState(x, y, board);
        conductor.markState(x - 1, y, board);
        conductor.markState(x - 2, y, board);
        conductor.markState(x, y - 1, board);
        conductor.markState(x, y + 1, board);
        conductor.markState(x + 1, y - 1, board);
        conductor.markState(x + 2, y, board);
        conductor.markState(x + 1, y + 1, board);
        conductor.markState(x + 3, y, board);
    }
}
