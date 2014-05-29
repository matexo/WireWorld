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
public class ElementFactory {

    public static Element buildElement(String s) {
        switch (s) {
            case "Insulator":
                return new Insulator();
            case "IZOLATOR":
                return new Insulator();
            case "ElectronHead":
                return new ElectronHead();
            case "GŁOWA ELEKTRONU":
                return new ElectronHead();    
            case "ElectronTail":
                return new ElectronTail();
            case "OGON ELEKTRONU":
                return new ElectronTail();
            case "Conductor":
                return new Conductor();
            case "PRZEWODNIK":
                return new Conductor();
            default:
                System.err.println("Zignorowana linia \"" + s + "\" - nie istnieje taki element");
        }
        return null;
    }

}