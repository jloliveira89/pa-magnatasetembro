/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;


import java.io.Serializable;
import java.util.ArrayList;


public class Magnata implements Serializable {

	private Deck m_deck;
    private Dice m_dice;
    private DiscardPile m_discardPile;
    private Table m_table;
    private ArrayList<Player> m_players;
    private TempPile m_tempPile;
    private StateMachine m_statemachine;
    private Player m_currentPlayer;

    public Magnata() {
        m_deck = new Deck();
        m_table = new Table();
        m_players = new ArrayList<>();
        m_tempPile = new TempPile();
        m_discardPile = new DiscardPile();
        m_dice = new Dice();
        
        m_statemachine = new StateMachine(this);
        
    }

// <editor-fold defaultstate="Getters" desc="Getters from the variables">
   
    public Player getCurrentPlayer()
    {
        return m_currentPlayer;
    }
    
    public DiscardPile getDiscardPile()
    {
        return m_discardPile;
    }
    
    public Deck getDeck()
    {
        return m_deck;
    }
    
    public Table getTable()
    {
        return m_table;
    }
    
    public Dice getDice()
    {
        return m_dice;
    }
    
    public ArrayList<Player> getPlayers()
    {
        return m_players;
    }
    
    public TempPile getTempPile()
    {
        return m_tempPile;
    }
    
    public StateMachine getStateMachine()
    {
        return m_statemachine;
    }
    
//</editor-fold>
    
    public void setupGame(String player1Name, String player2Name)
    {
       m_statemachine.setupGame(player1Name,player2Name); //Faz o setup
       m_currentPlayer = m_players.get(0);
    }
    
    public void nexTurn()
    {
        if(m_currentPlayer.equals(m_players.get(0)))
            m_currentPlayer = m_players.get(1);
        else
            m_currentPlayer = m_players.get(0);
    }
    
    public int getGameState()
    {
        return m_statemachine.getGameState();
    }
    
}
