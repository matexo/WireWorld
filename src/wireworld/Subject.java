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
public interface Subject {

    public void register(Observer observer);

    public void unregister(Observer observer);

    public void notifyObservers(int x , int y);

}
