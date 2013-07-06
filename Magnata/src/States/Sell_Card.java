/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.Card;
import Cards.CardNumbered;
import Cards.CardSuit;
import java.util.ArrayList;
import java.util.Iterator;

import magnata.District;
import magnata.Magnata;
import magnata.Player;
import magnata.StateMachine;
import magnata.Token;


public class Sell_Card extends State {
      
	public Sell_Card(StateMachine stateMachine, Magnata magnata) {
        super(stateMachine, magnata ); 
    }

      @Override
    public void sellCard(Card card)
    {
    	  int index = m_magnata.getCurrentPlayer().get_Hand().getCardPosition(card);
          
          if(index == -1)
        	  throw new Error("A carta que pretende vender nao foi encontrada");
    	  
        if("ACE".equals(card.getName()))
        {
            m_magnata.getCurrentPlayer().get_TokensPile().addToken(new Token(card.getSuit()));
            m_magnata.getCurrentPlayer().get_TokensPile().addToken(new Token(card.getSuit())); 
            
        }
        else
        {
            CardNumbered cardN = (CardNumbered) card;
            
            m_magnata.getCurrentPlayer().get_TokensPile().addToken(new Token(cardN.getSuit()));
            m_magnata.getCurrentPlayer().get_TokensPile().addToken(new Token(cardN.getSuit2()));
            
        }
        
       m_magnata.getCurrentPlayer().get_Hand().removeCard(card);
        
        m_magnata.getDiscardPile().addCard(card);
        
        m_stateMachine.setState(new States.DrawCard(m_stateMachine, m_magnata));
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
        return "Help (Sell_Card) ------------------------------------------\nVender <nome_carta_mao> - Vender uma carta da mao do jogador\nDesenvolver Propriedade <nome_distrito> <nome_carta> -> Desenvolver uma propriedade em construcao\nTrocar Recursos -> Trocar recursos com o banco\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
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
        	card.setStatus(2); //construída
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
