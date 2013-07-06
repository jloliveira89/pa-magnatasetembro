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
public class CrowsPile extends CardsPile implements Serializable {
        
    public CrowsPile()
    {
        m_cards = new ArrayList<>();
    }

    @Override
    public boolean addCard(Card card) {
        m_cards.add(card);
        return true;
    }
}
