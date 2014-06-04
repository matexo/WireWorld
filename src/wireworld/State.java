/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Color;

/**
 *
 * @author Matexo
 */
public interface State {

    State nextState(int x, int y, int neighborCounter);

    Color getColor();

    String writeState(int x, int y);
}
