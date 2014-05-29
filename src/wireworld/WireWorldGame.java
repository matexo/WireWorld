/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

/**
 *
 * @author Matexo
 */
public class WireWorldGame implements Game {

    private BoardState board;
    private BoardState board2;
    private final StateContext state;

    public WireWorldGame(BoardState board) {
        this.board = board;
        board2 = new BoardState();
        state = new StateContext();
    }

    public void setBoard(BoardState board) {
        this.board = board;
    }

    public BoardState getBoard() {
        return board;
    }

    @Override
    public BoardState gameNextStep() {
        board2.clear();
        for (int j = 0; j < board.getLengthX(); j++) {
            for (int k = 0; k < board.getLengthY(); k++) {
                state.setState(board.getCell(j, k));
                board2.setCell(state.nextState(j, k, board), j, k);
            }
        }

        BoardState boardTmp = board;
        board = board2;
        board2 = boardTmp;

        return board;
    }

}
