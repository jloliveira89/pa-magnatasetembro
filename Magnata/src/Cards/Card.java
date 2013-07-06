/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import java.io.Serializable;

public abstract class Card implements Serializable {
    
    protected CardType m_type;
    protected String m_name;
    protected Integer m_rank;
    protected CardSuit m_suit1;
    

    public void Card(CardType type, Integer rank, CardSuit suit) {
        
        m_type = type;
        m_rank = rank;
        m_suit1 = suit;

    }

    public CardSuit getSuit()
    {
        return m_suit1;
    }
    
    //Método que retorna o tipo de carta
    public CardType getType() {
        return m_type;
    }

    //Método que retorna o nome da carta
    public String getName() {
        return m_name;
    }
    
    public int getRank()
    {
        return m_rank;
    }
    
}
