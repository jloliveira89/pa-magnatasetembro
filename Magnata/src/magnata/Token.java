/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;
import java.io.Serializable;

import Cards.*;
/**
 *
 * @author JoãoOliveira
 */
public class Token implements Serializable {
    
    private CardSuit m_cardSuit;
    
    public Token(CardSuit suit)
    {
        
        m_cardSuit = suit;
        
    }
    
    public CardSuit getTokenSuit()
    {
        return m_cardSuit;
    }
}
