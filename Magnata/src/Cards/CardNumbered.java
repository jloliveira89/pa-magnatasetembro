/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import java.io.Serializable;
import java.util.ArrayList;
import magnata.Token;

/**
 *
 * @author JoãoOliveira
 */
public class CardNumbered extends Card implements Serializable{
    
    private CardSuit m_suit2;
    private boolean m_paidSuit1, m_paidSuit2;
    private int m_CardDevelpStatus; //0- Normal, 1-deed, 2-Developed
    private ArrayList<Token> m_tokensPaid;
    
    public CardNumbered(CardSuit suit1, CardSuit suit2, Integer rank, String name) {
        
        super.Card(CardType.NUMBERED, rank, suit1);
        m_suit2 = suit2;
        m_CardDevelpStatus = 0;
        m_paidSuit1 = false;
        m_paidSuit2 = false;
        m_tokensPaid = new ArrayList<>(); 

        m_name = setName(suit1, suit2, rank);

    }
    
    public void setStatus(int status)
    {
        m_CardDevelpStatus = status;
    }
    
 

    public void paySuit1()
    {
        m_paidSuit1 = true;
    }
    
    public void paySuit2()
    {
        m_paidSuit2 = true;
    }
    
    public boolean getPaidSuit2()
    {
        return m_paidSuit2;
    }
    
    public boolean getPaidSuit1()
    {
        return m_paidSuit1;
    }
    
  
    public int get_DevelopStatus()
    {
        return m_CardDevelpStatus;
    }
      
    public CardSuit getSuit2()
    {
        return m_suit2;
    }
    
    public ArrayList<Token> getTokens()
    {
    	return m_tokensPaid;
    }
    
    public boolean hasOneSuit(CardNumbered card)
    {
        if(card.getSuit() == m_suit1 || card.getSuit() == m_suit2)
            return true;
        
        if(card.getSuit2()== m_suit1 || card.getSuit2() == m_suit2)
            return true;
        
        return false;
    }
        
    public int hasAllSuitsTokens(ArrayList<Token> tokens)
    {
        boolean acceptSuit1 = false, acceptSuit2 = false;
        for(Token tk:tokens)
        {
            if(m_suit1 == tk.getTokenSuit())
                acceptSuit1 = true;
            
            if(m_suit2 == tk.getTokenSuit())
                acceptSuit2 = true;
        }
        
        if(acceptSuit1 && acceptSuit2)
            return 3;
        
        if(acceptSuit1)
            return 1;
        
        if(acceptSuit2)
            return 2;
        
        return 0;
    }
    
    public boolean hasSuit(CardSuit suit)
    {
        if(m_suit1 == suit || m_suit2 == suit)
            return true;
        
        return false;
    }
    

    
    public int countPaidSuitTokens(CardSuit suit)
    {
    	int count = 0;
    	
    	for(Token t:m_tokensPaid)
    	{
    		if(t.getTokenSuit() == suit)
    			count++;
    	}
    	
    	return count;
    }
    
    private String setName(CardSuit suit1, CardSuit suit2, Integer rank)
    {
       switch (rank)
       {
           case 2:
               if(suit1 == CardSuit.MOONS && suit2 == CardSuit.KNOTS)
                   return "AUTHOR";
               else
                   if(suit1 == CardSuit.WAVES && suit2 == CardSuit.LEAVES)
                       return "ORIGIN";
               else
                       return "DESERT";
               
           case 3:
               if(suit1 == CardSuit.LEAVES && suit2 == CardSuit.WYRMS)
                   return "SAVAGE";
               else
                   if(suit1 == CardSuit.SUNS && suit2 == CardSuit.KNOTS)
                       return "PAINTER";
               else
                       return "JOURNEY";
               
           case 4:
               if(suit1 == CardSuit.WYRMS && suit2 == CardSuit.KNOTS)
                   return "BATTLE";
               else
                   if(suit1 == CardSuit.WAVES && suit2 == CardSuit.LEAVES)
                       return "SAILOR";
               else
                   return "MOUNTAIN";    
               
           case 5:
               if(suit1 == CardSuit.WYRMS && suit2 == CardSuit.KNOTS)
                   return "SOLDIER";
               else
                   if(suit1 == CardSuit.SUNS && suit2 == CardSuit.WAVES)
                       return "DISCOVERY";
               else
                       return "FOREST";
               
           case 6:
               if(suit1 == CardSuit.SUNS && suit2 == CardSuit.WYRMS)
                   return "PENITENT";
               else
                   if(suit1 == CardSuit.MOONS && suit2 == CardSuit.WAVES)
                       return "LUNATIC";
               else
                       return "MARKET";
                   
           case 7:
               if(suit1 == CardSuit.SUNS && suit2 == CardSuit.KNOTS)
                   return "CASTLE";
               else
                   if(suit1 == CardSuit.MOONS && suit2 == CardSuit.LEAVES)
                       return "CHANCE MEETING";
               else
                       return "CAVE";
           case 8:
               if(suit1 == CardSuit.WYRMS && suit2 == CardSuit.KNOTS)
                   return "BETRAYAL";
               else
                   if(suit1 == CardSuit.MOONS && suit2 == CardSuit.SUNS)
                       return "DIPLOMAT";
               else
                       return "MILL";
               
           case 9:
               if(suit1 == CardSuit.MOONS && suit2 == CardSuit.SUNS)
                   return "PACT";
               else
                   if(suit1 == CardSuit.LEAVES && suit2 == CardSuit.KNOTS)
                       return "MERCHANT";
               else
                       return "DARKNESS";
       }
       
       return "";
    }

}
