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
public class CardCrown extends Card implements Serializable{
        
    public CardCrown(String name, CardSuit suit, int cardRank)
    {
        super.Card(CardType.CROWNS, cardRank, suit);
        
        m_name = setName(suit);
    }
    
    private String setName(CardSuit suit)
    {
        
        switch(suit)
        {
            case KNOTS:
                return "WINDFALL";
                
            case LEAVES:
                return "END";
                
            case MOONS:
                return "HUNTRESS";
                
            case SUNS:
                return "BARD";
                
            case WAVES:
                return "SEA";
                
            case WYRMS:
                return "CALAMITTY";
                
         
                             
        }
        
        return "";
        
    }
}
