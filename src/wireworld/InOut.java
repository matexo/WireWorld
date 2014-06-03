/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Matexo
 */
public class InOut implements Observer{

    Element element;
    StateContext state;
    BoardState board;

    public InOut(BoardState board)
    {
        this.board = board;
        state = new StateContext();
        
    }

    // sprawdzac x i y czy sie mieszcza !!
    public void readFile(File fileName) throws IOException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String buffer;
            while ((buffer = reader.readLine()) != null)
            {
                String[] p = buffer.split("\\s+");
                try
                {
                    element = ElementFactory.buildElement(p[0]);
                    element.markElement(Integer.parseInt(p[1]), Integer.parseInt(p[2]), board);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("Zignorowana linia \"" + buffer + "\" - zbyt mało pól");
                }
                catch (NumberFormatException e)
                {
                    System.err.println("Zignorowana linia \"" + buffer + "\" - \"" + p[2] + "\" nie jest liczbą całkowitą");
                }
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public void writeFile(BoardState board, File file) throws IOException
    {
        try (PrintStream out = new PrintStream(file))
        {
            for (int i = 0; i < board.getWidth(); i++)
            {
                for (int j = 0; j < board.getHeight(); j++)
                {
                    state.setState(board.getCell(i, j));
                    out.print(state.writeState(i, j));

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
