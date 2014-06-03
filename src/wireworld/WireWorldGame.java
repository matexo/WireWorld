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
public class WireWorldGame implements Game, Observer {

    private Containter board;
    private Containter boardTmp;
    private final StateContext state;

    public WireWorldGame(Containter board)
    {
        this.board = board;
        this.boardTmp = new Containter();
        state = new StateContext();
    }

    public void setBoard(Containter board)
    {
        this.board = board;
    }

    public Containter getBoard()
    {
        return board;
    }

    @Override
    public void gameNextStep()
    {
        boardTmp.clear();
        for (int i = 0; i < board.getWidth(); i++)
        {
            for (int j = 0; j < board.getHeight(); j++)
            {
                state.setState(board.getCell(i, j));
                boardTmp.setCell(state.nextState(i, j, board), i, j);
            }
        }

        // uaktywnienie wzorca obserwator 
        for (int i = 0; i < board.getWidth(); i++)
        {
            for (int j = 0; j < board.getHeight(); j++)
            {
                if (!board.getCell(i, j).getClass().equals(boardTmp.getCell(i, j).getClass()))
                {
                    board.setCell(boardTmp.getCell(i, j), i, j);
                }
            }
        }

    }

    @Override
    public void update(int x, int y, State state)
    {
        this.board.updateCell(state, x, y);
    }

}
