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
public class Dice implements Serializable {
    private int m_dice10_1;
    private int m_dice10_2;
    private int m_dice6;
    
    public Dice()
    {
        m_dice10_1 = 0;
        m_dice10_2 = 0;
        m_dice6 = 0;
    }
    
    public void setValueDice10_1(int value)
    {
        m_dice10_1 = value;
    }
    
    public void setValueDice10_2(int value)
    {
        m_dice10_2 = value;
    }
    
    public void setValueDice6(int value)
    {
        m_dice6 = value;
    }

    public int get_dice10_1() {
        return m_dice10_1;
    }

    public int get_dice10_2() {
        return m_dice10_2;
    }

    public int get_dice6() {
        return m_dice6;
    }
    
    public void resetDices()
    {
        m_dice10_1 = 0;
        m_dice10_2 = 0;
        m_dice6 = 0;
    }
    
    
    
}
