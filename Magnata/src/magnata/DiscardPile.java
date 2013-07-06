/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author JoãoOliveira
 */
public class DiscardPile extends CardsPile implements Serializable {
    
    public DiscardPile()
    {
        m_cards = new ArrayList<>();
    }

    @Override
    public boolean addCard(Card card) {
        m_cards.add(card);
        return true;
    }
    
    @Override
    public String toString() {
        String text = "DiscardPile: ";

        if (m_cards.size() > 0) {
            for (Card d : m_cards) {
                text += d.getName() + "|";
            }
        } else {
            text += "No Cards on DiscardPile";
        }

        return text;
    }
}
