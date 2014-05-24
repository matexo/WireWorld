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
public class BoardState {

    private final StateContext board[][];

    public BoardState() {
        board = new StateContext[80][80];
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                board[i][j] = new StateContext(new Insulator());
            }
        }
    }

    public void setCell(State state, int x, int y) {
        board[x][y].setState(state);
    }

    public State getCell(int x, int y) {
        return board[x][y].getState();
    }

    public int getLengthX() {
        return board.length;
    }

    public int getLengthY() {
        return board[0].length;
    }

    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setState(new Insulator());
            }
        }
    }

    public void write() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(getCell(i, j) + " ");
            }
            System.out.print("\n");
        }
    }

    public boolean isEdge(int x, int y) {
        return x < 0 || y < 0 || x >= getLengthX() || y >= getLengthY();
    }

    public int neighborsCounter(int x, int y, State state) {
        int neighbor = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (isEdge(i, j) == false && state.getClass().equals(getCell(i, j).getClass())) {
                    neighbor++;
                }
            }
        }
        return neighbor;
    }

}
