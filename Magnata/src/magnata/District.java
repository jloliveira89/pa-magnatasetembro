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
public class District implements Serializable {
    
    private Card m_district;
    private ArrayList<Card> m_player1District;
    private ArrayList<Card> m_player2District;
    
    public District(Card card)
    {
         m_district = card;
         m_player1District = new ArrayList<>();
         m_player2District = new ArrayList<>();
    }
    
    public boolean addCardIntoSide(Card card, int side) {
        if (side == 2){
            m_player1District.add(card);
            return true;
        }
        
        if (side == 1)
        {
            m_player2District.add(card);
            return true;
        }
        
        return false;
    }
     
    public Card getDistrict()
    {
        return m_district;
    }
    
    public ArrayList<Card> getPlayer1Districts()
    {
        return m_player1District;
    }
    
    public ArrayList<Card> getPlayer2Districts()
    {
        return m_player2District;
    }
    
    public ArrayList<Card> getCurrentPlayerDistricts(Player currentPlayer)
    {
        if( currentPlayer.get_PlayerNumber() == 1)
            return m_player1District;
        else
            return m_player2District;
    }
    
    public ArrayList<Card> getCardbyDistrictName(String districtName, String cardName, Player selectPlayer)
    {
    	ArrayList<Card> returnedCards = new ArrayList<>();
    	
    	if(districtName.equalsIgnoreCase(m_district.getName()))
    	{
    		for(Card cp: getCurrentPlayerDistricts(selectPlayer))
    		{
    			if(cp.getName().equals(cardName))
    				returnedCards.add(cp);
    		}
    	}
    	return returnedCards;
    }
    
    @Override
    public String toString()
    {
        String text = "District: " + m_district.getName() + "\n";
        
        text+="Jogador1: ";
        
        if(m_player1District.isEmpty())
        {
            text+="Ainda nao foi adicionado propriedades\n";
        }
        else
        {
            for(Card c:m_player1District)
            {
                text+=c.getName()+"|";
            }
            text+="\n";
        }
        text+="Jogador2: ";
        if(m_player2District.isEmpty())
        {
            text+="Ainda nao foi adicionado propriedades\n\n";
        }
        else
        {
            for(Card c:m_player2District)
            {
                text+=c.getName()+"|";
            }
            text+="\n\n";
        }
        
        return text;
        
    }
    
    
}
