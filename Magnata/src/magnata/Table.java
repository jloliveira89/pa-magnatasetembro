/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;
import Cards.CardNumbered;
import Cards.CardPawn;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable{
    
    private ArrayList<District> m_district;
    
    public Table() {
        
        m_district = new ArrayList<>();
    }
    
    public boolean addDistrict(Card card)
    {
        if(m_district.size() > 5)
            return false;
        else
        {
            m_district.add(new District(card));
            return true;
        }
        
    }
    
    public ArrayList<Card> getCardbyDistrictName(String districtName, String cardName, Player selectPlayer)
    {
    	ArrayList<Card> returnedCards = new ArrayList<>();
    	
    	for(District dt: m_district)
    	{
    		if(districtName.equalsIgnoreCase(dt.getDistrict().getName()))
        	{
        		for(Card cp: dt.getCurrentPlayerDistricts(selectPlayer))
        		{
        			if(cp.getName().equalsIgnoreCase(cardName))
        				returnedCards.add(cp);
        		}
        	}
    	}
    	
    	return returnedCards;
    }
    
    public String getDetails(String districtName, String cardName)
    {
    	String response="";
    	
    	//percorrer cada um dos distritos
    	
    	
    	for(District dt: m_district)
    	{
    		if(dt.getDistrict().getName().equalsIgnoreCase(districtName))
    		{
    			//no caso de o nome da carta nao for introduzido, so queremos obter os detalhes do distrito
    			if(cardName.equalsIgnoreCase(""))
    			{
    				if(dt.getDistrict().getName().equalsIgnoreCase("excuse"))
        			{
        				return response = "Distrito: Excuse ===========================\nNaipe: Aceita todos os naipes\n\n";
        			}
        			
        				
        			//no caso de ser um distrito
        			CardPawn pawn = (CardPawn) dt.getDistrict();
        			return response = "Distrito: " + dt.getDistrict().getName() + "===========================\nNaipes: " + pawn.getSuit() + " | " + pawn.get_Suit2() + " | " + pawn.get_Suit3() + "\n";
    			}
    			else
    			{
    				//se nao for um distrito entao tem que ir ver as cartas do distrito para o jogador 1
    	    		
    	    		if(cardName.equalsIgnoreCase("ace"))
    	    		{
    	    			//escrever para todos os jogadores os detalhes do AS
    	    			for(Card cDT: dt.getPlayer1Districts())
    	    			{
    	    				if(cDT.getName().equalsIgnoreCase(cardName))
    	    				{
	    	    				response += "Cartas do Jogador 1...........\n";
	    	    				response += "ACE ========================= \nNaipe: " + cDT.getSuit()+ "\n\n";
    	    				}
    	    			}
    	    			
    	    			for(Card cDT: dt.getPlayer2Districts())
    	    			{
    	    				if(cDT.getName().equalsIgnoreCase(cardName))
    	    				{
	    	    				response += "Cartas do Jogador 2...........\n";
	    	    				response += "ACE ========================= \nNaipe: " + cDT.getSuit()+ "\n\n";
    	    				}
    	    			}
    	    			return response;
    	    		}
    	    		//Se forem do tipo numericas
    	    		else
    	    		{
    	    			for(Card cDT: dt.getPlayer1Districts())
    	    			{
    	    				if(cDT.getName().equalsIgnoreCase(cardName))
    	    				{
    	    					CardNumbered cNumbered = (CardNumbered) cDT;
    	        				
    	    					response += cNumbered.getName() + " ========================= \nValor: "+ cNumbered.getRank()+ "\nNaipe 1: " + cNumbered.getSuit() + "\nNaipe 2: " +cNumbered.getSuit2()+ 
    	    							"\nEstado da Carta: ";
    	    							if(cNumbered.get_DevelopStatus() == 1)
    	    							{
    	    								response+= "Em construcao\n" + "-> Recursos Pagos <- \n" +cNumbered.getSuit() + ": " + cNumbered.countPaidSuitTokens(cNumbered.getSuit()) + "\n" + cNumbered.getSuit2() + ": " + cNumbered.countPaidSuitTokens(cNumbered.getSuit2()) + "\n\n";
    	    								
    	    							}
    	    							else
    	    								response+= "Construida\n\n";
    	    					return response;
    	    				}
    	    			}
    	    			
    	    			for(Card cDT: dt.getPlayer2Districts())
    	    			{
    	    				if(cDT.getName().equalsIgnoreCase(cardName))
    	    				{
    	    					CardNumbered cNumbered = (CardNumbered) cDT;
    	        				
    	    					response += cNumbered.getName() + " ========================= \nValor: "+ cNumbered.getRank()+ "\nNaipe 1: " + cNumbered.getSuit() + "\nNaipe 2: " +cNumbered.getSuit2()+ 
    	    							"\nEstado da Carta: ";
    	    							if(cNumbered.get_DevelopStatus() == 1)
    	    								response+= "Em construcao\n" + "-> Recursos Pagos <- \n" +cNumbered.getSuit() + ": " + cNumbered.countPaidSuitTokens(cNumbered.getSuit()) + "\n" + cNumbered.getSuit2() + ": " + cNumbered.countPaidSuitTokens(cNumbered.getSuit2()) + "\n\n";
    	    							else
    	    								response+= "Construida\n\n";
    	    					return response;
    	    				}
    	    			}
    	    		}
    	    		
    			}
    			
    					
    		}
    		
    		
    	}
    	
    	return "";
    	
    }
    
    public ArrayList<District> get_Districts()
    {
        return m_district;
    }
    
    @Override
    public String toString()
    {
        String text = "";
        for(District d: m_district)
        {
            text+= d.toString();
        }
        
        return text;
    }
   
   
   

//    public String toString() {
//        String text = "Table: ";
//
//        if (m_cards.size() > 0) {
//            for (Card d : m_cards) {
//                text += d.getName() + "|";
//            }
//        } else {
//            text += "No Card on Table";
//        }
//
//        return text;
//    }
}