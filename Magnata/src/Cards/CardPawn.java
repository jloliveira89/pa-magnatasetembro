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
public class CardPawn extends Card implements Serializable{
    
    private CardSuit m_suit2;
    private CardSuit m_suit3;
    
    public CardPawn(String name,CardSuit suit1, CardSuit suit2, CardSuit suit3, int rankCard)
    {
        super.Card(CardType.PAWNS, rankCard , suit1);
        
        m_suit2 = suit2;
        m_suit3 = suit3;
        
        m_name = name;
    }
    
    public CardSuit get_Suit2()
    {
        return m_suit2;
    }
    
    public CardSuit get_Suit3()
    {
        return m_suit3;
    }
}
