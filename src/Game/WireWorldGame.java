/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Observer.Observer;
import Elements.State;
import Elements.ElectronHead;
import java.io.File;
import java.io.IOException;
import Containter.Board;
import InputOutput.InOut;

/**
 *
 * @author Matexo
 */
public class WireWorldGame implements Game, Observer {

    private Board board;
    private final Board boardTmp;

    public WireWorldGame(Board board)
    {
        this.board = board;
        this.boardTmp = new Board();
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public Board getBoard()
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
                int counter = board.neighborsCounter(i, j, new ElectronHead());
                boardTmp.setCell(board.getCell(i, j).nextState(i, j, counter), i, j);
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
    public void restartGame()
    {
        this.board.clear();
    }
    
    
    @Override
    public void loadBoardFromFile(File file) throws IOException
    {
        InOut.readFile(file , this.board);
    }

    @Override
    public void saveBoardToFile(File file) throws IOException
    {
        InOut.writeFile(file, this.board);
    }

    @Override
    public void update(int x, int y, State state)
    {
        this.board.updateCell(state, x, y);
    }

}
