/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class CardsPile implements Serializable{
    // Atribuitos

    protected ArrayList<Card> m_cards;
    

    public CardsPile() {
        m_cards = new ArrayList<>();
    }

    //métodos
    public abstract boolean addCard(Card card);

    public boolean addCards(ArrayList<Card> cards) {
        //se dependedo do estado ele adiciona a uma das diferentes classes
        if (!cards.isEmpty()) {
            m_cards.addAll(cards);
        } else {
            return false;
        }

        return true;
    }

    /*
     * Remove uma carta do topo e devolve-a No caso de não haver cartas da
     * CardPile devolve null.
     */
    public Card RemoveFromTop() {
        if (m_cards.size() > 0) {
            return m_cards.remove(m_cards.size() - 1);
        } else {
            return null;
        }
    }

    public int Count() {
        return m_cards.size();
    }

    public ArrayList<Card> Empty() {
        ArrayList<Card> aux = new ArrayList<>();

        for (Card c : m_cards) {
            aux.add(c);
        }
        m_cards.clear();
        return aux;
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> aux = new ArrayList<>();

        for (Card c : m_cards) {
            aux.add(c);
        }
        return aux;
    }
}
