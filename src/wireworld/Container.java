/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

/**
 *
 * @author Matexo
 */
public interface Container {

    public void setCell(int state, int x, int y);

    public int getCell(int x, int y);

    public int getLengthX();

    public int getLengthY();

    public void clear();

    public void write();

    public boolean isEdge(int x, int y);

    public int neighborsCounter(int x, int y, int state);

}
