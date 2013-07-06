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

public class Roll_Dice extends State {

    public Roll_Dice(StateMachine statemachine, Magnata magnata) {
        super(statemachine, magnata);
        m_oldState = StateEnum.Initial;
       
    }

    // rola os dados e devolve um valor entre 1 e 10
    @Override
    public void rollDice10 () {
        int dice;
        
        dice = (int)(Math.random() * (10) ) +1;
        m_magnata.getDice().setValueDice10_1(dice); 
        
        dice = (int)(Math.random() * (10) ) +1;
        m_magnata.getDice().setValueDice10_2(dice); 
        
        if(m_magnata.getDice().get_dice10_1() == 1 || m_magnata.getDice().get_dice10_2() == 1)
            m_stateMachine.setState(new States.Taxation(m_stateMachine, m_magnata));
        else
            m_stateMachine.setState(new States.Collect_Resources(m_stateMachine, m_magnata));
    }
    
    // rola os dados e devolve um valor entre 1 e 6
    @Override
    public void rollDice6 () {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String help() {
        return "Help (Roll_Dice) ------------------------------------------\nRodar Dados 10 -> Rodar os 2 Dados 10\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
        		"Mostrar mesa -> mostrar a mesa\nMostrar recursos -> Mostrar os recursos do jogador\nAjuda -> mostrar a ajuda\nMostrar mao -> mostra a mao do jogador\nSalvar Jogo -> Salvar o corrente jogo\nSair -> Sair do Jogo\n";
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
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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