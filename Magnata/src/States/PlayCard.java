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


public class PlayCard extends State {

    public PlayCard(StateMachine stateMachine, Magnata magnata) {
        super(stateMachine, magnata); 
        
        m_oldState = State.StateEnum.PlayCard;
        
        
    }
    
    
    
    @Override
    public void chooseOption(int op)
    {
        m_magnata.getDice().resetDices();
        
        switch(op)
        {
            case 1: //caso de pretender construir uma propriedade
                m_stateMachine.setState(new States.Max_Property(m_stateMachine, m_magnata));
                break;
            case 2: //Adquirir uma escritura para uma propriedade em construção
                m_stateMachine.setState(new States.Purchase_Deed(m_stateMachine, m_magnata));
                break;
            case 3: //Vender uma carta
                m_stateMachine.setState(new States.Sell_Card(m_stateMachine, m_magnata));
                break;
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
        return "Help (PlayCard) ------------------------------------------\nSair->exit;";
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
    
}
