/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.CardSuit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JoãoOliveira
 */
public class TokensPile implements Serializable {

    private ArrayList<Token> m_tokens;
    
    public TokensPile()
    {
        m_tokens = new ArrayList<>();
    }
    
    
    public boolean addToken(Token tok) {
        m_tokens.add(tok);
        return true;
    }
    
    public boolean removeToken(Token tok)
    {
        for(Token t:m_tokens)
        {
            if( t.getTokenSuit() == tok.getTokenSuit())
            { 
                m_tokens.remove(t);
                return true;
            }
        }
        
        return false;
     }
    
    public ArrayList<Token> getCards()
    {
    	return m_tokens;
    }
    
    public void removeArrayTokens(ArrayList<Token> tk)
    {
        for(Token token:tk)
        {
            for(Token tk_list:m_tokens)
            {
                if(tk_list.equals(token))
                    removeToken(token);
            }
        }
    }
    
    public void removeNtokens(CardSuit suit, int number)
    {
    	for(Iterator<Token> it = m_tokens.iterator(); it.hasNext();)
    	{
    		Token t = it.next();
    		
    		if(t.getTokenSuit() == suit && number > 0)
    		{
    			it.remove();
    			number--;
    		}
    	}
        
    }
    
    public int countTokensSuit(CardSuit suit)
    {
        int count = 0;
        for(Token t:m_tokens)
        {
            if(t.getTokenSuit() == suit)
            {
                count++;
            }
        }
        
        return count;
    }
    
    public String toString()
    {
    	String response ="";
    	
    	response = "TOKENS ============================================" +
    					"\nMoons -> " + countTokensSuit(CardSuit.MOONS)+ 
    					"\nSuns -> " + countTokensSuit(CardSuit.SUNS)+
    					"\nWaves -> " + countTokensSuit(CardSuit.WAVES)+
    					"\nLeaves -> " + countTokensSuit(CardSuit.LEAVES)+
    					"\nWyrms -> " + countTokensSuit(CardSuit.WYRMS)+
    					"\nKnots -> " + countTokensSuit(CardSuit.KNOTS) + "\n\n";
    				
    	return response;
    
    }
     
    
}
