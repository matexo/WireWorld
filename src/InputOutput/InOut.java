/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutput;

import Elements.ElementFactory;
import Containter.Board;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Matexo
 */
public class InOut {

    public static void readFile(File fileName, Board board) throws IOException
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
                    int x = Integer.parseInt(p[1]);
                    int y = Integer.parseInt(p[2]);
                    if (!board.isEdge(x, y))
                    {
                        ElementFactory.buildElement(p[0]).markElement(x, y, board);
                    }
                    else
                    {
                        System.err.println("Zignorowana linia\"" + buffer + "\" - złe pozycje elementów");
                    }

                }
                catch (NullPointerException e)
                {
                    System.err.println("Zigronorwana linia \"" + buffer + "\" - podano zły element");
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

    public static void writeFile(File file, Board board) throws IOException
    {
        try (PrintStream out = new PrintStream(file))
        {
            for (int i = 0; i < board.getWidth(); i++)
            {
                for (int j = 0; j < board.getHeight(); j++)
                {
                    out.print(board.getCell(i, j).writeState(i, j));
                }
            }
        }
    }

}
