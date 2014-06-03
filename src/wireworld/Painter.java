/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author Matexo
 */
public class Painter extends JPanel implements MouseListener, MouseMotionListener, Observer {

    private Containter board;
    private final StateContext state;
    private Element paintelements;
    private boolean isClicked;
    private final int scale;

    public Painter(Containter board)
    {
        this.board = board;
        state = new StateContext();
        scale = 800 / board.getHeight();
        ///this.board.register(this);
    }

    public void setBoard(Containter board)
    {
        this.board = board;
    }

    public Containter getBoard()
    {
        return this.board;
    }

    public void setElement(Element element)
    {
        paintelements = element;
    }

    public int getScale()
    {
        return scale;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // plansza
        for (int i = 0; i < board.getWidth(); i++)
        {
            for (int j = 0; j < board.getHeight(); j++)
            {
                state.setState(board.getCell(i, j));
                g.setColor(state.getColor());
                g.fillRect(i * scale, j * scale, scale, scale);
            }
        }
        // kratka
        g.setColor(Color.darkGray);
        for (int i = 0; i < board.getWidth(); i++)
        {
            g.fillRect(i * scale, 0, 1, 800);
        }
        for (int i = 0; i < board.getWidth(); i++)
        {
            g.fillRect(0, i * scale, 800, 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        paintelements.markElement((int) x / scale, (int) y / scale, this.board);
        //repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        isClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        isClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        isClicked = false;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (isClicked)
        {
            int x = e.getX();
            int y = e.getY();
            paintelements.markElement((int) x / scale, (int) y / scale, this.board);
            //repaint();
        }
    }

    @Override
    public void update(int x, int y, State state)
    {
        this.board.updateCell(state, x, y);
        repaint();
    }

}
