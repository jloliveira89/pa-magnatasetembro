/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.Card;
import Cards.CardNumbered;
import Cards.CardSuit;
import java.util.ArrayList;
import magnata.District;
import magnata.Magnata;
import magnata.Player;
import magnata.StateMachine;
import magnata.Token;

public class DrawCard extends State{
    
    public DrawCard(StateMachine statemachine, Magnata magnata){
        super(statemachine, magnata);
    }
    
    @Override
    public void drawCard()
    {
        m_magnata.getCurrentPlayer().get_Hand().addCard(m_magnata.getDeck().RemoveFromTop());
        
        if(m_magnata.getDeck().getCountDeck() >= 2)
        {
        	m_magnata.getCurrentPlayer().setFinalTurn(true);
        }
        
        //aqui fazer o verifyGrandDuke
        if(m_magnata.getDeck().getCountDeck() >= 2 && m_magnata.getPlayers().get(0).get_finalTurn() && m_magnata.getPlayers().get(1).get_finalTurn())
        {
        	m_magnata.getStateMachine().setState(new States.FinalState(m_stateMachine, m_magnata));
        	return;
        }
        
        //passar as cartas que foram discartadas para o novo deck e baralhar
        if(m_magnata.getDeck().Count() == 0 && ( m_magnata.getDeck().getCountDeck()< 2 ) )
        {
            if(m_magnata.getDiscardPile().getCards().size() == 0)
            {
            	m_magnata.nexTurn();
            	m_stateMachine.setState(new States.Roll_Dice(m_stateMachine, m_magnata));
            	m_magnata.getDeck().countNewDeck();
            	return;
            }
            
        	do{
                m_magnata.getDeck().addCard(m_magnata.getDiscardPile().RemoveFromTop());
            }while(m_magnata.getDiscardPile().Count() > 0);
            
            
            m_magnata.getDeck().shuffle();
            m_magnata.getDeck().countNewDeck();
        }
        
      
        
        	m_magnata.nexTurn();
        	m_stateMachine.setState(new States.Roll_Dice(m_stateMachine, m_magnata));
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
        return "Help (DrawCard) ------------------------------------------\nComprar carta -> Comprar uma carta do baralho\nDesenvolver Propriedade <nome_distrito> <nome_carta> -> Desenvolver uma propriedade em construcao\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
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
    public void tradeResources(ArrayList<Token> tokens, CardSuit suit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void chooseResourceNumberedNotConstructed(int n_Suit, Card card, Player currentPlayer) {
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
