/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

/**
 *
 * @author JoãoOliveira
 */
public enum CardSuit {
    MOONS(1), SUNS(2), WAVES(3), LEAVES(4), WYRMS(5), KNOTS(6) ;

private final int value;
private CardSuit(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }

}


