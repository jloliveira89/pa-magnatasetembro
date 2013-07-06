/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardsPile implements Serializable {

    private int m_countDeckTimes;
    public Deck() {
        m_cards = new ArrayList<>();
        m_countDeckTimes = 0;
    }

    //Método do qual baralha o array
    public void shuffle() {
        Collections.shuffle(m_cards);
    }
    
      public boolean addCardPosition(int position, Card card) {

      
            m_cards.add(position - 1, card);
            return true;
      
    }
      
    public void countNewDeck()
    {
        m_countDeckTimes++;
    }
    
    public int getCountDeck()
    {
        return m_countDeckTimes;
    }


    /*
     * Método que adiciona uma lista de cartas. Percorrendo cada uma das cartas,
     * e chamando o método addcard, passando a carta percorrida como parametro
     */
    @Override
    public boolean addCards(ArrayList<Card> cards) {
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                if (addCard(c)) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }

        return false;


    }

    //Método para adicionar carta
    @Override
    public boolean addCard(Card card) {

        m_cards.add(card);
        return true;
    }

    @Override
    public String toString() {
        String text = "Deck: ";

        for (Card d : m_cards) {
            text += d.getName() + "|";
        }

        return text;
    }
}
