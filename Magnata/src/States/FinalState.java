package States;

import java.util.ArrayList;
import java.util.Iterator;

import Cards.Card;
import Cards.CardAce;
import Cards.CardNumbered;
import Cards.CardSuit;
import Cards.CardType;
import magnata.District;
import magnata.Magnata;
import magnata.Player;
import magnata.StateMachine;
import magnata.Token;

public class FinalState extends State{
	private int s_player1VictoryPoints, s_player2VictoryPoints, s_valuesPlayer1, s_valuesPlayer2, aux_valuePlayer1, aux_valuePlayer2;

	
	public FinalState(StateMachine statemachine, Magnata magnata) {
		super(statemachine, magnata);
		
		s_player1VictoryPoints = 0;
		s_player2VictoryPoints = 0;
		s_valuesPlayer1 = 0;
		s_valuesPlayer2 = 0;
		aux_valuePlayer1 = 0;
		aux_valuePlayer2 = 0;
	}

	
	
	public int verifyGrandDuke()
	{
		s_valuesPlayer1 = 0;
		s_valuesPlayer2 = 0;
		//descartar mao de todos os jogadores
		
		for(Player player: m_magnata.getPlayers())
		{	player.get_Hand().Empty();
		
		//descartar cartas em construção
		
		
			for(District dt: m_magnata.getTable().get_Districts())
			{
				for(Iterator<Card> it = dt.getCurrentPlayerDistricts(player).iterator(); it.hasNext();)
				{
					Card card = it.next();
					
					if(card.getType() == CardType.NUMBERED)
					{
						CardNumbered cNumbered = (CardNumbered) card;
						
						if(cNumbered.get_DevelopStatus() == 1)
							it.remove();
					}
				}
			}
		} //Fim de descartar as cartas de mao e escrituras
		
		
		//Contagem do valor de cada distrito
		
		
			for(District dt: m_magnata.getTable().get_Districts())
			{
				aux_valuePlayer1 = countValuesDistrict(dt.getPlayer1Districts());
				aux_valuePlayer2 = countValuesDistrict(dt.getPlayer2Districts()); 
				
				if( aux_valuePlayer1 > aux_valuePlayer2 ) //se o numero dos ranks do distrito do jogador 1 for maior que o do jogador 2 adicionar victory point
				{
					s_player1VictoryPoints++;
				}
				else
					if( aux_valuePlayer1 < aux_valuePlayer2 ) //se o numero dos ranks do distrito do jogador 2 for maior que o do jogador 1 adicionar victory point)
					{
						s_player2VictoryPoints++;
					}
				
				s_valuesPlayer1 += aux_valuePlayer1;
				s_valuesPlayer2 += aux_valuePlayer2;
				
			}//Fim de comparacao dos valores dos distritos
		 
	
		//comparacao dos victoryPoints
		
		if(s_player1VictoryPoints > s_player2VictoryPoints)
			return 1;
		else
			if(s_player1VictoryPoints < s_player2VictoryPoints)
				return 2;
	
		//Comparacao do numero total dos ranks das cartas
		if(s_valuesPlayer1 > s_valuesPlayer2)
			return 1;
		else
			if(s_valuesPlayer1 < s_valuesPlayer2)
				return 2;
		
		int player1Tokens = m_magnata.getPlayers().get(0).get_TokensPile().getCards().size();
		int player2Tokens = m_magnata.getPlayers().get(0).get_TokensPile().getCards().size();
		
		if(player1Tokens > player2Tokens)
			return 1;
		else
			if(player1Tokens < player2Tokens)
				return 2;
		
		return 0;
	}
	
	private int countValuesDistrict(ArrayList<Card> districtCards)
	{
		int aux_values = 0;
		
		for(Card cPlayer1 : districtCards)
		{
			
			if(cPlayer1.getType() == CardType.ACE)
			{
				CardAce ace = (CardAce) cPlayer1;
				
				aux_values+=countAceValues(ace, districtCards);
			}
			else
			{
				if( cPlayer1.getType() == CardType.NUMBERED )
				{
					CardNumbered cNumbered = (CardNumbered) cPlayer1;
					
					if(cNumbered.get_DevelopStatus() == 2)
					{
						aux_values += cNumbered.getRank(); 
					}
				}
			}
		}
		
		return aux_values;
	}

	private int countAceValues(CardAce ace, ArrayList<Card> districtCards)
	{
		int aux_values = 0;
		
			for(Card card : districtCards)
			{
				if(card.getType() == CardType.NUMBERED)
				{
					CardNumbered cNumbered = (CardNumbered) card;
					
					if(cNumbered.hasSuit(ace.getSuit()))
					{
						aux_values++;
					}
				}
			}
		
		return aux_values + 1;
	}
	
	@Override
	public String help() {
		return "Help (FinalState) ------------------------------------------\nSair->exit;";
		
	}

	@Override
	public GameState getGameState() {
		return GameState.FINALSTATE;
	}

	
	@Override
	public void setupGame(String player1Name, String player2Name){
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void rollDice10() {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void rollDice6() {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void doTaxation() {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void getResources(int bigValue, Player selectPlayer) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void chooseResourceNumberedNotConstructed(int n_Suit, Card card,
			Player currentPlayer) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void chooseOption(int op) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void payTokensToCompletlyDevelop(District districtCard,
			Card card_toDevelop, ArrayList<Token> tokens_toPay) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void buyAdeed(District district, Card card) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void sellCard(Card card) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public void drawCard() {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public boolean developPropertie(ArrayList<Token> tk, CardNumbered card) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void tradeResources(ArrayList<Token> tokens, CardSuit suit) {
		throw new UnsupportedOperationException("Not supported yet.");
		
	}
	
	@Override
	public void getToPlayState() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
