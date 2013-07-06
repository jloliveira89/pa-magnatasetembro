/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.Card;
import Cards.CardAce;
import Cards.CardCrown;
import Cards.CardNumbered;
import Cards.CardSuit;
import Cards.CardType;
import java.util.ArrayList;
import magnata.District;
import magnata.Magnata;
import magnata.Player;
import magnata.StateMachine;
import magnata.Token;


public class Collect_Resources extends State {
    
    public Collect_Resources(StateMachine statemachine, Magnata magnata)
    {
        super(statemachine, magnata);
    }
    
    public void chooseResourceNumberedNotConstructed(int n_Suit, Card card, Player currentPlayer)
    {
        CardNumbered cNumbered = (CardNumbered) card;
        
        switch (n_Suit)
        {
        	case 1:
        		currentPlayer.get_TokensPile().addToken(new Token(cNumbered.getSuit()));
        		break;
        	
        	case 2:
        		currentPlayer.get_TokensPile().addToken(new Token(cNumbered.getSuit2()));
        		break;
        }
    }
    

    
  public void getResources(int bigValue, Player selectPlayer){
   
    	
        
	        if(bigValue == 10) //Crows
	        {
	            for( Card c : selectPlayer.get_CrowsPile().getCards())
	            {
	                CardCrown cCrow = (CardCrown) c;
	                
	                selectPlayer.get_TokensPile().addToken(new Token(cCrow.getSuit()));
	            }
	        }
	        
	        
	        
	        if(bigValue >= 2 && bigValue <= 9)
	        {
	        	if(selectPlayer.get_PlayerNumber() == m_magnata.getCurrentPlayer().get_PlayerNumber())
	        	{
		            //Percorrer todos os distritos
		            for(District dt : m_magnata.getTable().get_Districts())
		            {
		                ArrayList<Card> districtCards = dt.getCurrentPlayerDistricts(selectPlayer);
		                if(!districtCards.isEmpty())
		                {
		                    for(Card c: districtCards)
		                    {
		                        if(c.getRank() == bigValue)
		                        {
		                            CardNumbered cNumbered = (CardNumbered) c;
		                         
		                            switch(cNumbered.get_DevelopStatus())
		                            {
		                                case 2:
		                                	selectPlayer.get_TokensPile().addToken(new Token(cNumbered.getSuit()));
		                                	selectPlayer.get_TokensPile().addToken(new Token(cNumbered.getSuit2()));
		                                break;
		                                    
		                                case 1:
		                                	selectPlayer.get_tempPile().addCard(new CardNumbered(cNumbered.getSuit(), cNumbered.getSuit2(), cNumbered.getRank(), cNumbered.getName()));
		                                break;
		                            }
		                            
		                        }
		                    }
		                }
		            }
	        	}
	        }
	        
	            
	            
	        if(bigValue == 1) //As
	        {
	            for(District dt : m_magnata.getTable().get_Districts())
	            {
	                ArrayList<Card> districtCards = dt.getCurrentPlayerDistricts(selectPlayer);
	                
	                if(!districtCards.isEmpty())
	                {
	                    for(Card c: districtCards)
	                    {
	                        if(c.getRank() == bigValue)
	                        {
	                            CardAce cAce = (CardAce) c;
	                            
	                            selectPlayer.get_TokensPile().addToken(new Token(cAce.getSuit()));
	                        }
	                    }
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
        return State.GameState.PLAYING;
    }

    @Override
    public void rollDice6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String help() {
       return "Help (Collect_Resources) ------------------------------------------\nObter Recursos -> Obter os recursos\nTrocar Recursos -> Trocar recursos com o banco\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
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
    public boolean developPropertie(ArrayList<Token> tk, CardNumbered card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
	public int verifyGrandDuke() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void getToPlayState() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}