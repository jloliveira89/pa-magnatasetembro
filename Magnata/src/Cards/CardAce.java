/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import java.io.Serializable;

/**
 *
 * @author JoãoOliveira
 */
public class CardAce extends Card implements Serializable {
    
    private boolean isConstructed;
    
    public CardAce(CardSuit suit)
    {
        super.Card(CardType.ACE, 1, suit);
        m_name = "ACE";
        isConstructed = false;
    }
    
    public void constructAce()
    {
        isConstructed = true;
    }
    
    public boolean getStatusConstruct()
    {
        return isConstructed;
    }
}
