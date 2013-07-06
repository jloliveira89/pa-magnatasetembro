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
public class CardSpecial extends Card implements Serializable{
    
    public CardSpecial(String name)
    {
        
        super.Card(CardType.SPECIAL, 0, null);
        m_name = name;
    }
}
