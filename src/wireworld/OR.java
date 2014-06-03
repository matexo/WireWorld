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
public class OR implements Element {

    @Override
    public void markElement(int x, int y, BoardState board)
    {
        Element conductor = new Conductor();
        conductor.markElement(x, y, board);
        conductor.markElement(x + 1, y, board);
        conductor.markElement(x + 1, y - 1, board);
        conductor.markElement(x + 1, y - 2, board);
        conductor.markElement(x, y + 2, board);
        conductor.markElement(x - 1, y - 2, board);
        conductor.markElement(x - 1, y + 2, board);
        conductor.markElement(x - 2, y + 1, board);
        conductor.markElement(x - 2, y - 1, board);
        conductor.markElement(x - 3, y + 1, board);
        conductor.markElement(x - 3, y - 1, board);
        conductor.markElement(x + 2, y, board);
        conductor.markElement(x + 3, y, board);
    }

}
