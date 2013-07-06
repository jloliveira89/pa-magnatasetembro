/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;
import Cards.CardNumbered;
import Cards.CardType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Hand extends CardsPile implements Serializable {

    public Hand() {
        m_cards = new ArrayList<>();
    }

    @Override
    public boolean addCard(Card card) {

        m_cards.add(card);
        return true;

    }
    
    public boolean removeCard(Card card)
    {
    	boolean removed = false;
    	
    	for(Iterator<Card> it = m_cards.iterator(); it.hasNext();)
    	{
    		Card cardIter = it.next();
    		if(cardIter.equals(card))
    		{
    			it.remove();
    			removed = true;
    			
    		}
    	}
    	
    	return removed;
    }
    
    public int getCardPosition(Card card)
    {
    	for(int i = 0; i < m_cards.size(); i++)
    	{
    		Card auxCard = m_cards.get(i);
    		if(auxCard.getName().equalsIgnoreCase(card.getName()) && auxCard.getSuit() == card.getSuit())
    			return (i);
    	}
    	
    	return -1;
    }
    
    public String getDetailsCard(String CardName)
    {
    	
    	String details = "";
    	for(Card c: m_cards)
    	{
    		if(c.getName().trim().equalsIgnoreCase(CardName))
    		{
    			//ver se e As ou Numerica
    			if(c.getType().equals(CardType.ACE))
    				details += "ACE ========================= \nNaipe: " + c.getSuit()+ "\n\n";
    			else
    			{
    				CardNumbered cNumbered = (CardNumbered) c;
    				
    				details += cNumbered.getName() + " ========================= \nValor: "+ cNumbered.getRank()+ "\nNaipe 1: " + cNumbered.getSuit() + "\nNaipe 2: " +cNumbered.getSuit2()+ "\n\n";
    			}
    		}
    		
    		
    	}
    	
    	return details;
    }
    
    public ArrayList<Card> getHandCardbyName(String cardName)
    {
    	ArrayList<Card> returnedCards = new ArrayList<>();
    	
    	for(Card c:m_cards)
    	{
    		if(c.getName().equalsIgnoreCase(cardName))
    			returnedCards.add(c);
    			
    	}
    	
    	return returnedCards;
    }
    
    @Override
    public String toString() {
        String text = "Hand: ";

        for (Card d : m_cards) {
            text += d.getName() + "|";
        }

        return text;
    }
}

