/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Cards.*;

import java.util.ArrayList;
import java.util.Iterator;
import magnata.*;


public class Initial extends State {

    
    public Initial(StateMachine stateMachine, Magnata magnata) {
        super(stateMachine, magnata);
        
    }
    
    @Override
    public void setupGame(String player1Name, String player2Name) {
        
        //Constroi os jogadores...
        m_magnata.getPlayers().add(new Player(player1Name));
        m_magnata.getPlayers().add(new Player(player2Name));
   
        // Constroi o deck
        addAceCards();
        
        addNumberedCards();
        
        //Adicionar as Crows para uma pilha temporaria e seguidamente baralhar
        addCrowns();
        
        m_magnata.getTempPile().shuffle();
        
        
        Card card; int count_jogador = 0;        
        while(m_magnata.getTempPile().Count() > 0)
        {
            card = m_magnata.getTempPile().RemoveFromTop();
            m_magnata.getPlayers().get(count_jogador).get_CrowsPile().addCard(card);
            count_jogador++;
            card = m_magnata.getTempPile().RemoveFromTop();
            m_magnata.getPlayers().get(count_jogador).get_CrowsPile().addCard(card);
            count_jogador--;
        }
        
        //build the resourcePile
        buildResourcePile();
        
        //baralhar o deck
        m_magnata.getDeck().shuffle();
        
    
        buildTable();
        
        // Vai buscar as cartas ao deck para fazer a hand
        buildHand();
    
        m_stateMachine.setState(new States.Roll_Dice(m_stateMachine, m_magnata));
    }

    private void buildResourcePile()
    {
        for(Player p:m_magnata.getPlayers())
        {
            CrowsPile crown_pile = p.get_CrowsPile();
            
            for (Iterator<Card> it = crown_pile.getCards().iterator(); it.hasNext();) {
                Card c = it.next();
                CardCrown cardC = (CardCrown) c;
                p.get_TokensPile().addToken(new Token(cardC.getSuit()));
            }
        	
//        	for(int i = 0; i < 10; i++){
//	        	p.get_TokensPile().addToken(new Token(CardSuit.SUNS));
//	        	p.get_TokensPile().addToken(new Token(CardSuit.KNOTS));
//	        	p.get_TokensPile().addToken(new Token(CardSuit.LEAVES));
//	        	p.get_TokensPile().addToken(new Token(CardSuit.MOONS));
//	        	p.get_TokensPile().addToken(new Token(CardSuit.WAVES));
//	        	p.get_TokensPile().addToken(new Token(CardSuit.WYRMS));
//        	}
        }        
    }
    
    private void buildTable()
    {
        m_magnata.getTable().addDistrict(new CardPawn("WATCHMAN", CardSuit.MOONS, CardSuit.WYRMS, CardSuit.KNOTS,10));
        m_magnata.getTable().addDistrict(new CardPawn("HARVEST", CardSuit.MOONS, CardSuit.SUNS, CardSuit.LEAVES,10));
        m_magnata.getTable().addDistrict(new CardSpecial("EXCUSE"));
        m_magnata.getTable().addDistrict(new CardPawn("BORDERLAND", CardSuit.WAVES, CardSuit.LEAVES, CardSuit.WYRMS,10));
        m_magnata.getTable().addDistrict(new CardPawn("LIGHT_KEEPER", CardSuit.SUNS, CardSuit.WAVES, CardSuit.KNOTS,10));
    }
    /*
     * Ao chamar a build hand, vou retirando sempre uma carta do topo do baralho
     * Faço uma chama à função addcard da hand, na qual me devolve um true se
     * esta for labyrinth se não for, é adicionada à limbo a carta Só saio deste
     * ciclo quando tiver 5 cartas na minha mão;
     */
    private void buildHand() {
        
        for(Player p:m_magnata.getPlayers())
        {
            do{
                
                p.get_Hand().addCard(m_magnata.getDeck().RemoveFromTop());
                
            }while(p.get_Hand().Count() < 3);
        }
    }

    private void addAceCards()
    {
        m_magnata.getDeck().addCard(new CardAce(CardSuit.KNOTS));
        m_magnata.getDeck().addCard(new CardAce(CardSuit.LEAVES));
        m_magnata.getDeck().addCard(new CardAce(CardSuit.MOONS));
        m_magnata.getDeck().addCard(new CardAce(CardSuit.SUNS));
        m_magnata.getDeck().addCard(new CardAce(CardSuit.WAVES));
        m_magnata.getDeck().addCard(new CardAce(CardSuit.WYRMS));
    }
    
    private void addCrowns()
    {
        m_magnata.getTempPile().addCard(new CardCrown("HUNTRESS", CardSuit.MOONS, 11));
        m_magnata.getTempPile().addCard(new CardCrown("BARD", CardSuit.SUNS, 11));
        m_magnata.getTempPile().addCard(new CardCrown("WINDFALL", CardSuit.KNOTS, 11));
        m_magnata.getTempPile().addCard(new CardCrown("END", CardSuit.LEAVES, 11));
        m_magnata.getTempPile().addCard(new CardCrown("CALAMITY", CardSuit.WYRMS, 11));
        m_magnata.getTempPile().addCard(new CardCrown("SEA", CardSuit.WAVES, 11));
        
    }
    
    private void addNumberedCards() 
    {
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.KNOTS, 2, "AUTHOR"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WAVES, CardSuit.LEAVES, 2, "ORIGIN"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.SUNS, CardSuit.WYRMS, 2, "DESERT"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.SUNS, CardSuit.KNOTS, 3, "PAINTER"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.LEAVES, CardSuit.WYRMS, 3, "SAVAGE"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.WAVES, 3, "JOURNEY"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WYRMS, CardSuit.KNOTS, 4, "BATTLE"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WAVES, CardSuit.LEAVES, 4, "SAILOR"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.SUNS, 4, "MOUNTAIN"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.SUNS, CardSuit.WAVES, 5, "DISCOVERY"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.LEAVES, 5, "FOREST"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WYRMS, CardSuit.KNOTS, 5, "SOLDIER"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.WAVES, 6, "LUNATIC"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.SUNS, CardSuit.WYRMS, 6, "PENITENT"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.LEAVES, CardSuit.KNOTS, 6, "MARKET"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.SUNS, CardSuit.KNOTS, 7, "CASTLE"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.LEAVES, 7, "CHANCE_MEETING"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WAVES, CardSuit.WYRMS, 7, "CAVE"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WYRMS, CardSuit.KNOTS, 8, "BETRAYAL"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.SUNS, 8, "DIPLOMAT"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WAVES, CardSuit.LEAVES, 8, "MILL"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.MOONS, CardSuit.SUNS, 9, "PACT"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.WAVES, CardSuit.WYRMS, 9, "DARKNESS"));
      m_magnata.getDeck().addCard(new CardNumbered(CardSuit.LEAVES, CardSuit.KNOTS, 9, "MERCHANT"));
    } 

    @Override
    public GameState getGameState() {
        return GameState.PLAYING;
    }

    

    @Override
    public void rollDice6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String help() {
        return "Help (Initial) ------------------------------------------\nSair->exit;";
    }

    @Override
    public void rollDice10() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doTaxation() {
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
