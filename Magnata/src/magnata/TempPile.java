/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magnata;

import Cards.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TempPile extends CardsPile implements Serializable {

    public TempPile() {
        m_cards = new ArrayList<>();
    }
    
    /*
     * A função addCards verifica se o arraylist passado como parametro não se encontra vazia
     * Caso nao se encontra vazio, para cada carta percorrida chama a função addCard, passando como parametro a carta
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
    
    /*
     * A função select card, devolve a posição de cada uma das cartas no array
     * Utilizada no repositioncardsondeck;
     */
    public Card selectCard(Integer position) {
        Card card = m_cards.get(position - 1);

        return card;

    }

    /*
     * Acrescenta a card pile do temppile as cartas
     */
    @Override
    public boolean addCard(Card card) {

        m_cards.add(card);
        return true;
    }

    /*
     * Retorna uma string com a informação dos nomes das cartas
     */
    @Override
    public String toString() {
        String text = "TempPile: ";


        for (Card d : m_cards) {
            text += d.getName() + "|";
        }

        return text;
    }
    
     public void shuffle() {
        Collections.shuffle(m_cards);
    }
    
}