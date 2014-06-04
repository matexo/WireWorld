/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Matexo
 */
public interface Game {

    public void gameNextStep();

    public void saveBoardToFile(File file) throws IOException;
    
    public void loadBoardFromFile(File file) throws IOException;

}
