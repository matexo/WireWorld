/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

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
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Matexo
 */
// sprawdzanie czy .txt
// standardowo niech pokazuje pulpit
// dodac instrukcje
public class GUISwing extends JFrame implements ActionListener {

    Board board;
    WireWorldGame game;
    Painter painter;
    //InOut readWrite;
    boolean isStop;

    //upper menu
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem readFile, writeFile, help;
    JFileChooser fileChooser;

    // rightmenu
    JButton buttonStart;
    JButton buttonNext;
    JButton buttonClear;
    JButton buttonStop;
    JLabel counter;
    JTextField counterField;
    JComboBox elementChooser;
    JSlider slider;
    JLabel sliderLabel;
    JLabel elementsLabel;

    public GUISwing()
    {
        this.board = new Board();
        game = new WireWorldGame(this.board);
        //readWrite = new InOut(this.board);

        fileChooser = new JFileChooser();
        isStop = false;

        iniFrame();
        iniUpperMenu();
        iniRightMenu();

        painter = new Painter(board);
        painter.setBounds(0, 0, 800, 800);
        painter.addMouseListener(painter);
        painter.addMouseMotionListener(painter);
        painter.setElement(new Conductor());

        add(painter);

        board.register(game);
        board.register(painter);
        //board.register(readWrite);

    }

    private void iniFrame()
    {
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("WireWorld");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniUpperMenu()
    {
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

    private void iniRightMenu()
    {
        buttonStart = new JButton("Start");
        buttonStart.setBounds(800, 00, 200, 30);
        buttonStart.addActionListener(this);
        add(buttonStart);

        counter = new JLabel("Ilość generacji");
        counter.setBounds(805, 90, 200, 30);
        add(counter);

        counterField = new JTextField();
        counterField.setBounds(800, 120, 200, 30);
        counterField.setText("1000");
        add(counterField);

        buttonNext = new JButton("Następna generacja");
        buttonNext.setBounds(800, 60, 200, 30);
        buttonNext.addActionListener(this);
        add(buttonNext);

        buttonStop = new JButton("Stop");
        buttonStop.setBounds(800, 30, 200, 30);
        buttonStop.addActionListener(this);
        add(buttonStop);

        buttonClear = new JButton("Wyczyść plansze");
        buttonClear.setBounds(800, 350, 200, 30);
        buttonClear.addActionListener(this);
        add(buttonClear);

        elementChooser = new JComboBox();
        elementChooser.setBounds(805, 180, 190, 30);
        elementChooser.addItem("PRZEWODNIK");
        elementChooser.addItem("GŁOWA ELEKTRONU");
        elementChooser.addItem("OGON ELEKTRONU");
        elementChooser.addItem("IZOLATOR");
        elementChooser.addItem("DIODA");
        elementChooser.addItem("OR");
        elementChooser.addActionListener(this);
        add(elementChooser);

        slider = new JSlider(50, 1000, 100);
        slider.setBounds(810, 240, 190, 100);
        slider.setMajorTickSpacing(200);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);

        sliderLabel = new JLabel("Przerwa [ms]");
        sliderLabel.setBounds(805, 210, 200, 30);
        add(sliderLabel);

        elementsLabel = new JLabel("Wybór elementów");
        elementsLabel.setBounds(805, 150, 200, 30);
        add(elementsLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object selection = e.getSource();
        if (selection == readFile)
        {
            board.clear();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                try
                {
                    game.loadBoardFromFile(file);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(GUISwing.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        else if (selection == writeFile)
        {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                try
                {
                    game.saveBoardToFile(file);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(GUISwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if (selection == help)
        {
            JOptionPane.showMessageDialog(menu, "ISNTRUKCJA");
        }
        else if (selection == buttonStart)
        {
            isStop = false;
            new Thread() {
                @Override
                public void run()
                {
                    int temp = Integer.parseInt(counterField.getText());
                    while (temp > 0 && isStop == false)
                    {
                        game.gameNextStep();
                        temp--;
                        counterField.setText(String.valueOf(temp));
                        try
                        {
                            Thread.sleep(slider.getValue());
                        }
                        catch (InterruptedException ex)
                        {
                            Logger.getLogger(GUISwing.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }.start();
        }
        else if (selection == elementChooser)
        {
            String temp = elementChooser.getSelectedItem().toString();
            painter.setElement(ElementFactory.buildElement(temp));
        }
        else if (selection == buttonClear)
        {
            board.clear();
            painter.repaint();
        }
        else if (selection == buttonNext)
        {
            game.gameNextStep();
        }
        else if (selection == buttonStop)
        {
            isStop = true;
        }
    }

}
