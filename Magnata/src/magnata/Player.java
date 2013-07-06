/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import java.io.Serializable;

/**
 *
 * @author JoãoOliveira
 */
public class Player implements Serializable {
    
    private Hand m_hand; 
    private TempPile m_tempPile;
    private CrowsPile m_crowsPile;
    private TokensPile m_tokensPile;
    private static int m_staticNumber = 0;
    private final int m_playerNumber;
    private final String m_playerName;
    private boolean m_finalTurn;
    
    public Player(String nome)
    {
        m_hand = new Hand();
        m_tempPile = new TempPile();
        m_crowsPile = new CrowsPile();
        m_tokensPile = new TokensPile();
        m_playerNumber = ++m_staticNumber;
        m_playerName = nome;
        m_finalTurn = false;
    }
    

 
    
    public Hand get_Hand() {
        return m_hand;
    }

    public TempPile get_tempPile() {
        return m_tempPile;
    }

    public CrowsPile get_CrowsPile() {
        return m_crowsPile;
    }

    public TokensPile get_TokensPile() {
        return m_tokensPile;
    }

    public int get_PlayerNumber() {
        return m_playerNumber;
    }

    public String get_PlayerName() {
        return m_playerName;
    }
    
    public boolean get_finalTurn()
    {
    	return m_finalTurn;
    }
    
    public void setFinalTurn(boolean value)
    {
    	m_finalTurn = value;
    }

}
