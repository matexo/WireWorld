/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

/**
 *
 * @author Matexo
 * @param <T>
 */
public interface Container<T> {

    public void setCell(T state, int x, int y);

    public T getCell(int x, int y);

    public int getWidth();
    
    public int getHeight();

    public void clear();

    public void write();

    public boolean isEdge(int x, int y);

    public int neighborsCounter(int x, int y, T state);

}
