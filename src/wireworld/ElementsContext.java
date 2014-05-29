/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Graphics;

/**
 *
 * @author Matexo
 */
public class ElementsContext implements Element {

    Element element;

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public void markState(int x, int y, BoardState board) {
        element.markState(x, y, board);
    }

}
