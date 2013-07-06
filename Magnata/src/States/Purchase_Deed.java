/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.Card;
import Cards.CardNumbered;
import Cards.CardPawn;
import Cards.CardSuit;
import Cards.CardType;
import java.util.ArrayList;
import magnata.District;
import magnata.Magnata;
import magnata.Player;
import magnata.StateMachine;
import magnata.Token;



public class Purchase_Deed extends State {
   
	public Purchase_Deed(StateMachine statemachine, Magnata magnata) {
        super(statemachine, magnata); 
    }

     
     @Override
    public void buyAdeed(District district, Card card)
    {
    	 
    	if(card.getType().equals(CardType.ACE))
		{
				throw new Error("<Informacao> Nao compre uma escritura para o tipo de As, so iria gastar recursos, sendo assim esta bloqueada esta escritura");
		}			
    	
    	CardNumbered numbered = (CardNumbered) card;
    	
    	 //verificar se o jogador tem os tokens necessarios para por a carta em jogo
    	 
    	 if(m_magnata.getCurrentPlayer().get_TokensPile().countTokensSuit(numbered.getSuit()) < 1 && m_magnata.getCurrentPlayer().get_TokensPile().countTokensSuit(numbered.getSuit2()) < 1)
    	 {
    		 throw new Error("<Informacao> Nao tem os recursos necessarios para comprar esta escritura");
    	 }
    	 
        try{
            placeCardonDistrict(district, numbered);
        }catch(Error t)
        {
              throw t;
        }
        
        	district.getCurrentPlayerDistricts(m_magnata.getCurrentPlayer()).add(numbered);
            numbered.setStatus(1);
            numbered.paySuit1();
            numbered.paySuit2();
            
            m_magnata.getCurrentPlayer().get_TokensPile().removeNtokens(numbered.getSuit(), 1);
            m_magnata.getCurrentPlayer().get_TokensPile().removeNtokens(numbered.getSuit2(), 1);
            
            m_stateMachine.setState(new States.DrawCard(m_stateMachine, m_magnata));
        
        
    }
   
    

     private boolean placeCardonDistrict(District district, Card cardToPut) {
         
      Player currentPlayer = m_magnata.getCurrentPlayer();
      ArrayList<Card> districtCards = district.getCurrentPlayerDistricts(currentPlayer);
      
      if(!districtCards.isEmpty()) // se o jogador tem propriedades no distrito
      {
           Card lastDistrictCard = districtCards.get(districtCards.size()-1);
           
           if(lastDistrictCard.getType() == CardType.ACE)
           {               
               if(cardToPut.getType() == CardType.ACE)
               {
                   if(cardToPut.getSuit() == lastDistrictCard.getSuit())
                   {
                      
                       return true;
                   }
                   else
                       throw new Error("Nao foi possivel colocar a carta pois os naipes nao sao iguais");
               }
               else //caso a carta a por nao seja do tipo ás
               {
                   CardNumbered carNumberedToPut = (CardNumbered) cardToPut;
                   
                   if(carNumberedToPut.hasSuit(lastDistrictCard.getSuit()))
                   {  
                	   return true;
                   }
                   else
                       throw new Error("Nao foi possivel colocar a carta pois os naipes nao correspondem ao naipe do As");
               }
           
           }else {//Caso a carta nao seja do tipo As
                   CardNumbered cardNumbered_last = (CardNumbered) cardToPut;
                   
                   if(cardToPut.getType() == CardType.ACE)
                   {
                       if(cardNumbered_last.hasSuit(cardToPut.getSuit()))
                       {
                           
                           return true;
                       }
                       else
                            throw new Error("Nao foi possivel colocar a carta pois os naipes nao sao iguais");
                   }
                   else //se a carta a adicionar e uma carta numérica
                   {
                       CardNumbered cardToPutNumeric = (CardNumbered) cardToPut;
                       
                       if(cardToPutNumeric.hasOneSuit(cardNumbered_last))
                       {
                          
                           return true;
                       }
                        else
                            throw new Error("Nao foi possivel colocar a carta pois os naipes nao sao iguais");
                   }
                   
                 
                }
           }
           else //caso os nao haja cartas no distrito
           {
               if("EXCUSE".equalsIgnoreCase(district.getDistrict().getName()))
                    return true;
                else
                {
                    CardPawn pawn = (CardPawn) district.getDistrict();
                  
                    //ver se ha uma carta do tipo AS
                    if(cardToPut.getType() == CardType.ACE)
                    {
                        if(pawn.getSuit() == cardToPut.getSuit())
                        {   
                            return true;
                        }
                        
                        if(pawn.get_Suit2()== cardToPut.getSuit())
                        {   
                            return true;
                        }
                        
                        if(pawn.get_Suit3()== cardToPut.getSuit())
                        {   
                            return true;
                        }
                        
                        throw new Error("A carta a adicionar nao tem o mesmo tipo de naipe que a carta peao");
                        
                    }
                    else //se a carta for do tipo Numbered
                    {
                        CardNumbered cardNumbered_toPut = (CardNumbered) cardToPut;
                        
                        if(cardNumbered_toPut.hasSuit(pawn.getSuit()))
                        {
                            
                            return true;
                        }
                        
                        if(cardNumbered_toPut.hasSuit(pawn.get_Suit2()))
                        {
                            
                            return true;
                        }
                        
                        if(cardNumbered_toPut.hasSuit(pawn.get_Suit3()))
                        {
                            
                            return true;
                        }
                        
                        throw new Error("A carta numerica que pretende adicionar nao tem relação com os naipes da carta peão");
                    }
                    
                }
            
           }
      }
    
     
    @Override
    public void setupGame(String player1Name, String player2Name){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameState getGameState() {
        return GameState.PLAYING;
    }

    @Override
    public void rollDice6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String help() {
        return "Help (Purchase_Deed) ------------------------------------------\nComprar escritura <nome_distrito> <nome_carta> - Comprar uma escritura\nDesenvolver Propriedade <nome_distrito> <nome_carta> -> Desenvolver uma propriedade em construcao\nTrocar Recursos -> Trocar recursos com o banco\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
        		"Mostrar mesa -> mostrar a mesa\nMostrar recursos -> Mostrar os recursos do jogador\nAjuda -> mostrar a ajuda\nMostrar mao -> mostra a mao do jogador\nSalvar Jogo -> Salvar o corrente jogo\nSair -> Sair do Jogo\n";
    }

    @Override
    public void rollDice10() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doTaxation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void chooseOption(int op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void payTokensToCompletlyDevelop(District districtCard, Card card_toDevelop, ArrayList<Token> tokens_toPay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sellCard(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean developPropertie(ArrayList<Token> tk, CardNumbered card)
    {
        
        if(card.get_DevelopStatus() != 1)
            throw new Error("A carta nao se encontra em construcao!");
        
        
        ArrayList<Token> tokensPaid = card.getTokens();
        
        if(tk.size() + tokensPaid.size() > card.getRank())
        	throw new Error("<Informacao> Pretende adicionar mais recursos do que o valor que falta para completar a carta\nNumero de recursos restantes: "+ (card.getRank() - tokensPaid.size()) +"\n");
        
        
        tokensPaid.addAll(tk);
        m_magnata.getCurrentPlayer().get_TokensPile().removeArrayTokens(tk);
        
        if(tokensPaid.size() == card.getRank() ) //ja esta para ser construida
        {
        	card.setStatus(2); //constru�da
        	tokensPaid.clear();
        	return true;
        }
        
        return false;
    }

    @Override
    public void tradeResources(ArrayList<Token> tokens, CardSuit suit)
    {
        if(tokens.size() != 3)
            throw new Error("Necessita de ter apenas 3 recursos para fazer a troca");
        
        m_magnata.getCurrentPlayer().get_TokensPile().removeArrayTokens(tokens); 
        
        if(tokens.get(0).getTokenSuit() == tokens.get(1).getTokenSuit() && tokens.get(0).getTokenSuit() == tokens.get(2).getTokenSuit())
        {
            m_magnata.getCurrentPlayer().get_TokensPile().addToken(new Token(suit));
        }
        else
        {
            throw new Error("Os recursos tem que ser do mesmo tipo");
        }
    }


	@Override
	public void chooseResourceNumberedNotConstructed(int n_Suit, Card card,
			Player currentPlayer) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		
	}


	@Override
	public void getResources(int bigValue, Player selectPlayer){
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		
	}

	@Override
	public int verifyGrandDuke() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	@Override
	public void getToPlayState() {
		m_stateMachine.setState(new States.PlayCard(m_stateMachine, m_magnata));
	}
      
    
}
