/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Matexo
 */
// pozycja okien
// sprawdzanie czy .txt
// standardowo niech pokazuje pulpit
// dodac instrukcje
// buttonsy do elementow
public class GUISwing extends JFrame implements ActionListener {

    BoardState board;
    ElementsContext elements = new ElementsContext();
    WireWorldGame game;
    Painter painter;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem readFile, writeFile, help;

    JButton buttonStart;
    JButton buttonNext;
    JButton buttonClear;
    JLabel counter;
    JTextField counterField;

    JFileChooser fileChooser;

    JComboBox elementChooser;

    public GUISwing() {
        board = new BoardState();
        game = new WireWorldGame(board);
        fileChooser = new JFileChooser();

        iniFrame();
        iniUpperMenu();
        iniRightMenu();

        painter = new Painter(board);
        painter.setBounds(0, 0, 800, 800);
        painter.addMouseListener(painter);
        painter.addMouseMotionListener(painter);
        painter.setElement(new Conductor());
        add(painter);

    }

    private void iniFrame() {
        setSize(1000, 800);
        setResizable(false);
        setTitle("WireWorld");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniUpperMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Opcje");
        readFile = new JMenuItem("Wczytaj plik");
        readFile.addActionListener(this);
        writeFile = new JMenuItem("Zapisz plik");
        writeFile.addActionListener(this);
        help = new JMenuItem("Pomoc");
        help.addActionListener(this);

        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(readFile);
        menu.add(writeFile);
        menu.add(help);

    }

    private void iniRightMenu() {
        buttonStart = new JButton("Start");
        buttonStart.setBounds(810, 10, 150, 30);
        buttonStart.addActionListener(this);
        buttonStart.setVisible(true);
        add(buttonStart);

        counter = new JLabel("Ilość generacji");
        counter.setBounds(810, 50, 150, 20);
        counter.setVisible(true);
        add(counter);

        counterField = new JTextField();
        counterField.setBounds(810, 67, 150, 20);
        counterField.setVisible(true);
        add(counterField);

        buttonNext = new JButton("Następna generacja");
        buttonNext.setBounds(810, 90, 150, 30);
        buttonNext.setVisible(true);
        add(buttonNext);

        buttonClear = new JButton("Wyczyść plansze");
        buttonClear.setBounds(810, 700, 150, 30);
        buttonClear.setVisible(true);
        buttonClear.addActionListener(this);
        add(buttonClear);

        elementChooser = new JComboBox();
        elementChooser.setBounds(810, 120, 150, 30);
        elementChooser.addItem("PRZEWODNIK");
        elementChooser.addItem("GŁOWA ELEKTORUN");
        elementChooser.addItem("OGON ELEKTRONU");
        elementChooser.addItem("IZOLATOR");
        elementChooser.addItem("DIODA");
        elementChooser.addActionListener(this);
        add(elementChooser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object selection = e.getSource();

        if (selection == readFile) {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                InOut read = new InOut();
                try {
                    board = read.readFile(file);
                    painter.setBoard(board);
                } catch (IOException ex) {
                    Logger.getLogger(GUISwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            game = new WireWorldGame(board);
            painter.repaint();
        } else if (selection == writeFile) {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                InOut write = new InOut();
                try {
                    write.writeFile(board, file);
                } catch (IOException ex) {
                    Logger.getLogger(GUISwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (selection == help) {
            JOptionPane.showMessageDialog(menu, "ISNTRUKCJA");
        } else if (selection == buttonStart) {
            board = painter.getBoard();
            board = game.gameNextStep();
            painter.setBoard(board);
            painter.repaint();
        } else if (selection == elementChooser) {
            String temp = elementChooser.getSelectedItem().toString();
            switch (temp) {
                case "IZOLATOR":
                    painter.setElement(new Insulator());
                    break;
                case "PRZEWODNIK":
                    painter.setElement(new Conductor());
                    break;
                case "GŁOWA ELEKTORUN":
                    painter.setElement(new ElectronHead());
                    break;
                case "OGON ELEKTRONU":
                    painter.setElement(new ElectronTail());
                    break;
                case "DIODA":
                    painter.setElement(new Diode());
                    break;

            }
        } else if (selection == buttonClear) {
            board.clear();
            painter.setBoard(board);
            painter.repaint();
        }
    }

}
