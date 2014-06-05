package WireWorldGame;

import GUI.GUISwing;

/**
 *
 * @author Matexo
 */
public class GameRunner {

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                new GUISwing().setVisible(true);
            }
        });

    }
}
