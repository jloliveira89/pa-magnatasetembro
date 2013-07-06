/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.Card;
import Cards.CardAce;
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


public class Max_Property extends State {
    public Max_Property(StateMachine stateMachine, Magnata magnata) {
        super(stateMachine, magnata);
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
                       throw new Error("Nao foi possível colocar a carta pois os naipes nao sao iguais");
               }
               else //caso a carta a por nao seja do tipo ás
               {
                   CardNumbered carNumberedToPut = (CardNumbered) cardToPut;
                   
                   if(carNumberedToPut.hasSuit(lastDistrictCard.getSuit()))
                   {  
                       return true;
                   }
                   else
                       throw new Error("Nao foi possível colocar a carta pois os naipes nao correspondem ao naipe do As");
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
                            throw new Error("Nao foi possível colocar a carta pois os naipes nao sao iguais");
                   }
                   else //se a carta a adicionar e uma carta numérica
                   {
                       CardNumbered cardToPutNumeric = (CardNumbered) cardToPut;
                       
                       if(cardToPutNumeric.hasOneSuit(cardNumbered_last))
                       {
                           return true;
                       }
                        else
                            throw new Error("Nao foi possível colocar a carta pois os naipes nao sao iguais");
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
                  
                    //ver se é uma carta do tipo ás
                    if(cardToPut.getType() == CardType.ACE)
                    {
                        if(pawn.getSuit() == cardToPut.getSuit()){
                            return true;
                        }
                        
                        if(pawn.get_Suit2()== cardToPut.getSuit()){
                            return true;
                        }
                        
                        if(pawn.get_Suit3()== cardToPut.getSuit())
                        {   
                            return true;
                        }
                        
                        throw new Error("A carta a adicionar nao tem o mesmo tipo de naipe que a carta peão");
                        
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
    public void payTokensToCompletlyDevelop(District districtCard, Card card_toDevelop, ArrayList<Token> tokens_toPay)
    {
        CardNumbered cardNumbered;
        CardAce cardAce;
       
        try{
            placeCardonDistrict(districtCard, card_toDevelop);
        }catch(Error t)
        {
            throw t;
        }
        
        //Comprar a propriedade desenvolvida
        
        if(card_toDevelop.getType() == CardType.ACE)
        {
            cardAce = (CardAce) card_toDevelop;
            
            developACE(tokens_toPay, cardAce, districtCard);
            m_magnata.getCurrentPlayer().get_TokensPile().removeArrayTokens(tokens_toPay);
            m_stateMachine.setState(new States.DrawCard(m_stateMachine, m_magnata));
            return;
                
        }
        else
        {
            if(card_toDevelop.getType() == CardType.NUMBERED)
            {
                cardNumbered = (CardNumbered) card_toDevelop;
                
                if(tokens_toPay.size() != cardNumbered.getRank())
                    throw new Error("O numero de tokens nao correspondem ao rank da carta");
                
                if(cardNumbered.hasAllSuitsTokens(tokens_toPay) == 3) //Se os tokens passados têm os dois naipes
                {
                    //Neste momento sabemos que os tokens correspondem ao rank da carta e que ambos têm o mesmo naipe
                    
                    //retirar os tokens e dar ordem que se pode construir a carta
                    
                    //m_magnata.getCurrentPlayer().get_TokensPile().removeToken(tk);
                    cardNumbered.setStatus(2);//Estado -> construída
                    m_magnata.getCurrentPlayer().get_TokensPile().removeArrayTokens(tokens_toPay);
                    m_stateMachine.setState(new States.DrawCard(m_stateMachine, m_magnata));
                    
                    return;
                    
                }
                else
                {
                    throw new Error("Os recursos passados nao correspodem aos naipes da carta");
                    
                }
            }   
        }
        
        throw new Error("A carta passada nao corresponde a nenhum As ou Carta Numerada\n");
    }
    
    private void developACE(ArrayList<Token> tokens, CardAce Ace, District district )
    {
        if(tokens.size() < 3)
        {
            throw new Error("Nao e possivel construir um As, minimo de 3 tokens");
            
        }
        
        
        if( (tokens.get(0).getTokenSuit() == tokens.get(1).getTokenSuit() ) && ( tokens.get(0).getTokenSuit() == tokens.get(2).getTokenSuit() ) )
        {
          m_magnata.getCurrentPlayer().get_Hand().removeCard(Ace);
          
          for(District dt: m_magnata.getTable().get_Districts())
          {
              if(dt.equals(district))
              {
            	  dt.getCurrentPlayerDistricts(m_magnata.getCurrentPlayer()).add(Ace);
            	  break;
              }
          }

          //remover os 3 tokens
          
          m_magnata.getCurrentPlayer().get_TokensPile().removeNtokens(tokens.get(0).getTokenSuit(), 3);
          
          
        }
        else
        {
            throw new Error("Os 3 tipos de recurso nao sao iguais");
            
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
        return "Help (Max_Property) ------------------------------------------\nConstruir <nome_distrito> <nome_carta> -> Construir por completo a proriedade\nDesenvolver Propriedade <nome_distrito> <nome_carta> -> Desenvolver uma propriedade em construcao\nTrocar Recursos -> Trocar recursos com o banco\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
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
    public void buyAdeed(District district, Card card) {
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
