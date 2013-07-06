/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.*;

import java.util.ArrayList;
import magnata.District;
import magnata.Magnata;
import magnata.Player;
import magnata.StateMachine;
import magnata.Token;


public class Taxation extends State {

    public Taxation(StateMachine statemachine, Magnata magnata) {
        super(statemachine, magnata);
    }
    
    /**
     *
     */
    @Override
    public void doTaxation () {
        
        if(m_magnata.getDice().get_dice6() == 0)
            throw new Error("Necessario rodar o dado de 6");
        
        CardSuit taxSuit = null;
        
        for (CardSuit c:CardSuit.values()) {
            if (c.getValue() == m_magnata.getDice().get_dice6()) {
                taxSuit = c;
            }
        }
        
        // para cada jogador, vai verificar se tem mais que um token da suit taxada e vai remover os mesmos
        for (Player player:m_magnata.getPlayers()) {
            int count = player.get_TokensPile().countTokensSuit(taxSuit);
	            if(count > 1)
	            {
	            	player.get_TokensPile().removeNtokens(taxSuit, count-1);
	            }
            
            
        }
        
        m_stateMachine.setState(new States.Collect_Resources(m_stateMachine, m_magnata));
    }
    
     @Override
    public void rollDice6() {
        int dice;
        dice = 1 + (int)(Math.random() * 6);
        
        m_magnata.getDice().setValueDice6(dice);
        
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
        return "Help (Taxation) ------------------------------------------\nRodar dado 6 -> rodar o dado de 6 faces\nTaxar -> Fazer a taxacao\nDetalhes <nome_carta> -> Detalhes de uma carta da mao\nDetalhes mesa <nome_distrito> <nome_carta> - Mostrar detalhes de uma carta da mesa\n" +
        		"Mostrar mesa -> mostrar a mesa\nMostrar recursos -> Mostrar os recursos do jogador\nAjuda -> mostrar a ajuda\nMostrar mao -> mostra a mao do jogador\nSalvar Jogo -> Salvar o corrente jogo\nSair -> Sair do Jogo\n";
    }

    @Override
    public void rollDice10() {
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
    public void tradeResources(ArrayList<Token> tokens, CardSuit suit) {
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
