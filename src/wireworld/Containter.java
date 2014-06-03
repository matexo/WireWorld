/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.util.ArrayList;

/**
 *
 * @author uesr
 */
public final class Containter implements Subject , Container<State>{

    private ArrayList<Observer> observers = new ArrayList<>();
    private final StateContext board[][];
    private final int width;
    private final int height;

    public Containter()
    {
        width = 40;
        height = 40;
        board = new StateContext[getWidth()][getHeight()];
        for (int i = 0; i < getWidth(); i++)
        {
            for (int j = 0; j < getHeight(); j++)
            {
                board[i][j] = new StateContext(new Insulator());
            }
        }
    }

    public Containter(int x, int y)
    {
        width = x;
        height = y;
        board = new StateContext[x][y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                board[i][j] = new StateContext(new Insulator());
            }
        }
    }

    @Override
    public void setCell(State state, int x, int y)
    {
        board[x][y].setState(state);
        notifyObservers(x, y);
    }

    @Override
    public State getCell(int x, int y)
    {
        return board[x][y].getState();
    }

    public void updateCell(State state, int x, int y)
    {
        board[x][y].setState(state);
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public void clear()
    {
        for (StateContext[] board1 : board)
        {
            for (StateContext board11 : board1)
            {
                board11.setState(new Insulator());
            }
        }
    }

    @Override
    public void write()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                System.out.print(getCell(i, j).getClass() + " ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public boolean isEdge(int x, int y)
    {
        return x < 0 || y < 0 || x >= getWidth() || y >= getHeight();
    }

    @Override
    public int neighborsCounter(int x, int y, State state)
    {
        int neighbor = 0;
        for (int i = x - 1; i < x + 2; i++)
        {
            for (int j = y - 1; j < y + 2; j++)
            {
                if (i == x && j == y)
                {
                    continue;
                }
                if (isEdge(i, j) == false && state.getClass().equals(getCell(i, j).getClass()))
                {
                    neighbor++;
                }
            }
        }
        return neighbor;
    }

    //observerpattern
    public ArrayList<Observer> getObservers()
    {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers)
    {
        this.observers = observers;
    }

    @Override
    public void register(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int x, int y)
    {
        for (Observer ob : observers)
        {
            ob.update(x, y, getCell(x, y));
        }
    }

}