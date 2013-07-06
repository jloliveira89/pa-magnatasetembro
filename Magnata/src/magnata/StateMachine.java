/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;
import Cards.CardNumbered;
import Cards.CardSuit;
import States.Initial;
import States.State;
import States.State.StateEnum;

import java.io.Serializable;
import java.util.ArrayList;

public class StateMachine implements Serializable{

    
    private Magnata m_magnata;
    private States.State m_state;
    

    public StateMachine(Magnata magnata) {
        // Cria os vários CArdPiles necessários
        
       m_magnata = magnata;
        
        //Inicia o state como initial;
        m_state = new States.Initial(this,m_magnata);
    }
    
     //ALL STATES
    public String help()    {
        return m_state.help();
    }
    
    public int getGameState()    {
        State.GameState state = m_state.getGameState();

        if (state == State.GameState.PLAYING) {
            return 0;
        } else if (state == State.GameState.FINALSTATE) {
            return 1;
        }
		return -1;
    }

    //Initial State
     public void setupGame(String player1Name, String player2Name) {
        m_state.setupGame(player1Name,player2Name);
    }
    
    //Roll_Dice State
    public int rollDicesTen()   {
        m_state.rollDice10();
        
        if(m_magnata.getDice().get_dice10_1() == 1 || m_magnata.getDice().get_dice10_2() == 1)
            return 1;
        
        return 0;
    }
    
    //Taxation State
    public void rollDiceSix()   {
        m_state.rollDice6();
    }
    
    public void doTaxation (){
        m_state.doTaxation();
    }
    
    //Collect_Resources State
    public void getResources(int bigValue,Player selectPlayer){
    	m_state.getResources(bigValue, selectPlayer);
    }
    
    public void chooseResourceNumberedNotConstructed(int n_Suit, Card card, Player currentSelection)    {
    	m_state.chooseResourceNumberedNotConstructed(n_Suit, card, currentSelection);
    }
    
    //PlayCard State
    public void chooseOption(int op){
        m_state.chooseOption(op);
    }
    
    //Max_Property State
    public void payTokensToCompletlyDevelop(District districtCard, Card card_toDevelop, ArrayList<Token> tokens_toPay){
        m_state.payTokensToCompletlyDevelop(districtCard, card_toDevelop, tokens_toPay);
    }
    
    //Purchase_Deed State
    public void buyAdeed(District district, Card card){
         m_state.buyAdeed(district, card);
    }
    
    //Sell_Card State
    public void sellCard(Card card){
        m_state.sellCard(card);
    }
    
    //Draw Card
    public void drawCard(){
        m_state.drawCard();
    }
    
    //Develop_Properties
    public boolean developPropertie(ArrayList<Token> tk, CardNumbered card){
        return m_state.developPropertie(tk, card);
    }

    //Trade 
    public boolean tradeResources(ArrayList<Token> tokens, CardSuit suit)
    {
    	m_state.tradeResources(tokens, suit);
    	return true;
    }
    
    
    //FinalState
    
    public int verifyGrandDuke()
    {
    	return m_state.verifyGrandDuke();
    }
    
    
   //Método no qual efectua a alternancia entre estados
    public void setState(States.State state) {
        m_state = state;
    }
    
    public void setOldState(States.State state)
    {
    	m_state = state;
    }
    
}
