/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;
import Cards.Card;
import Cards.CardNumbered;
import Cards.CardSuit;

import java.io.Serializable;
import java.util.ArrayList;

import javax.print.attribute.EnumSyntax;

import magnata.*;

public abstract class State implements Serializable {

    
   
    protected StateMachine m_stateMachine;
    protected Magnata m_magnata;
    protected StateEnum m_oldState;
    protected State m_oldState2;
    
   
    
    public enum GameState {

        PLAYING, FINALSTATE;
    };

    public State(StateMachine statemachine, Magnata magnata) {
        
        m_stateMachine = statemachine;
        m_magnata = magnata;
        
    }
    
    public enum StateEnum{
        Initial, Roll_Dice, Taxation, Collect_Resources, PlayCard, Max_Property, Purchase_Deed, Sell_Card, DrawCard;
    }

    //ALL STATES
    abstract public String help();
    abstract public State.GameState getGameState();
    
    //Initial State
    abstract public void setupGame(String player1Name, String player2Name);
    
    //Roll_Dice State
    abstract public void rollDice10();
    
    //Taxation State
    abstract public void rollDice6 ();
    abstract public void doTaxation ();
    
    //Collect_Resources State
    abstract public void getResources(int bigValue, Player selectPlayer);
    abstract public void chooseResourceNumberedNotConstructed(int n_Suit, Card card, Player currentPlayer);
    
    //PlayCard State
    abstract public void chooseOption(int op);
    abstract public void getToPlayState();
    
    //Max_Property State
    abstract public void payTokensToCompletlyDevelop(District districtCard, Card card_toDevelop, ArrayList<Token> tokens_toPay);
    
    //Purchase_Deed State
    abstract public void buyAdeed(District district, Card card);
    
    //Sell_Card State
    abstract public void sellCard(Card card);
    
    //Draw Card
    abstract public void drawCard();
    
    //SpecialStates
    abstract public boolean developPropertie(ArrayList<Token> tk, CardNumbered card);
    abstract public void tradeResources(ArrayList<Token> tokens, CardSuit suit);
    
    //FinalState
    abstract public int verifyGrandDuke();

}