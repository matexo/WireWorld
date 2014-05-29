/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author Matexo
 */
public class Painter extends JPanel implements MouseListener, MouseMotionListener {

    private BoardState board;
    private final StateContext state;
    private Element paintelements;
    private boolean isClicked;
    private final int scale;

    public Painter(BoardState board) {
        this.board = board;
        state = new StateContext();
        scale = 800 / board.getHeight();
    }

    public void setBoard(BoardState board) {
        this.board = board;
    }

    public BoardState getBoard() {
        return this.board;
    }

    public void setElement(Element element) {
        paintelements = element;
    }

    public int getScale() {
        return scale;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // plansza
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                state.setState(board.getCell(i, j));
                g.setColor(state.getColor());
                g.fillRect(i * scale, j * scale, scale, scale);
            }
        }

        // kratka
        g.setColor(Color.darkGray);
        for (int i = 0; i < board.getWidth(); i++) {
            g.fillRect(i * scale, 0, 1, 800);
        }
        for (int i = 0; i < board.getWidth(); i++) {
            g.fillRect(0, i * scale, 800, 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
            isClicked = true;
        } else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
            isClicked = false;
        }
        int x = e.getX();
        int y = e.getY();
        paintelements.markState((int) x / scale, (int) y / scale, this.board);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isClicked) {
            int x = e.getX();
            int y = e.getY();
            paintelements.markState((int) x / scale, (int) y / scale, this.board);
            repaint();
        }
    }

}
