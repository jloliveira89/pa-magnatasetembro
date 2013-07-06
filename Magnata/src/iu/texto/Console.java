/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iu.texto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import magnata.District;
import magnata.FileOperations;
import magnata.Magnata;
import magnata.Player;
import magnata.Token;
import Cards.Card;
import Cards.CardNumbered;
import Cards.CardSuit;
import Cards.CardType;

public class Console {

    private Magnata m_logicaJogo;
    private FileOperations m_fileOperations;
    
    public Console() {
        m_logicaJogo = null;
        m_fileOperations = new FileOperations();
        
        menuStart();
    }
    
    private void menuStart()
    {
        	int leitura;
       writeTextNL("Bem Vindo ao Jogo do Magnata");
       
       do{
    	   leitura = askNumber("1 - Começar novo Jogo\n2 - Carregar um jogo\n3 - Sair\nEscolha: ", 1,3);
       }while(leitura == -1);
       
       if(leitura == 1)
    	   newGame();
       else
    	   if(leitura == 2)
    	   {
    		   loadGame();
    	   }
    	   else
    		   System.exit(0);
    }
    
private void newGame()
{
	String jogador1_name, jogador2_name;
	
	 m_logicaJogo = new Magnata();
	 
	jogador1_name = askString("\n\nIndique o nome para o jogador1: ");
	jogador2_name = askString("\n\nIndique o nome para o jogador2: ");
	 
	m_logicaJogo.setupGame(jogador1_name,jogador2_name);
	 
	 game_basics();
     
}

private void loadGame()
{
	//perguntar ao utilizador qual o jogo que pretende carregar... TODO
	String nome_fich;
	ArrayList<String> fileNames = m_fileOperations.readNameFilesFromTXT();
	
	String writer = "\nIndique o nome para o jogo que vai guardar\n";
	
	for(int i = 0; i < fileNames.size(); i++)
	{
		writer+=fileNames.get(i)+"\n";
	}
	
	nome_fich = askString(writer + "Nome do Ficheiro: ");
	
	
	//carregar o jogo
	m_logicaJogo  = (Magnata) m_fileOperations.readFile(nome_fich+".dat"); 
	game_basics();
     
}



private void game_basics()
{
       
       writeTextNL("Deck: "+m_logicaJogo.getDeck().Count()+"\n\n" + m_logicaJogo.getTable().toString());
        
        do{
            askAcommand();
        }while(m_logicaJogo.getGameState() == 0);
        
        switch(m_logicaJogo.getStateMachine().verifyGrandDuke())
		{
		case 0: //Empate
			writeTextNL("Parabens Jogador1 e Jogador2 voces sao os Grande Duques, teem que exercer a tarefa em dias alternados\n");
			break;
			
		case 1: //Jogador 1 ganha
			writeTextNL("Parabens " + m_logicaJogo.getPlayers().get(0).get_PlayerName() + " es o Grande Duque... Boas tarefas!!!\n");
			break;
			
		case 2: //Jogador 2 ganha
			writeTextNL("Parabens " + m_logicaJogo.getPlayers().get(1).get_PlayerName() + " es o Grande Duque... Boas tarefas!!!\n");
			break;
		}
        
        int leitura;
        do{
        leitura = askNumber("\n\nPretende iniciar um novo jogo?\n1 - Sim\n2 - Nao\nEscolha: ", 1, 2);
        }while(leitura == -1);
        
        if(leitura == 1)
        {
        	newGame();
        }
    }
    
        private void askAcommand() {
         
            Player currentPlayer = m_logicaJogo.getCurrentPlayer();
            String command = askString(currentPlayer.get_PlayerName()+ " comando: ");

            
            try{

                if(!interpretCommand(command))
                    writeTextNL("Comando Invalido! - Para ajuda, escreva 'help'");
            }
            catch(UnsupportedOperationException ue)
            {
                writeTextNL("Nao e possivel fazer esse comando no estado atual");
            }
            catch(Error e)
            {
                writeTextNL(e.getMessage());
                if(e.getMessage().equalsIgnoreCase("DENIED"))
                {
                    writeTextNL("O comando introduzido é invalido!");
                }
            }

            
    }
    
    public boolean interpretCommand(String command) {

        boolean hasExecCommand = false;
        
        try {
            
            //ALL STATES
        	if(!hasExecCommand)
                hasExecCommand = exec_command_SaveToFile(command);
        	
            if(!hasExecCommand)
                hasExecCommand = exec_command_showHand(command);
             
            if(!hasExecCommand)
                hasExecCommand = exec_command_help(command);
            
            if(!hasExecCommand)
            	hasExecCommand = exec_command_showTokens(command);
            
            if(!hasExecCommand)
            	hasExecCommand = exec_command_showTable(command);
            
            if(!hasExecCommand)
            	hasExecCommand = exec_command_cardDetails(command);
            
            if(!hasExecCommand)
            	hasExecCommand = exec_command_detailsTable(command);
            //Roll_Dice State
    
            if(!hasExecCommand)
                hasExecCommand = exec_command_rollDice10(command);
    
            //Taxation State
            if(!hasExecCommand)
                hasExecCommand = exec_command_rollDice6(command);
             
           if(!hasExecCommand)
        	   hasExecCommand = exec_command_doTaxation(command);
    
            //Collect_Resources State
            if(!hasExecCommand)
                hasExecCommand = getResources(command);
    
    
            //Max_Property State
            if(!hasExecCommand)
                hasExecCommand = exec_command_payTokensToCompletlyDevelop(command);
    
    
            //Purchase_Deed State
            if(!hasExecCommand)
                hasExecCommand = exec_command_buyAdeed(command);
            
    
            //Sell_Card State
            if(!hasExecCommand)
                hasExecCommand = exec_command_sellCard(command);
            
    
            //Draw Card
            if(!hasExecCommand)
                hasExecCommand = exec_command_drawCard(command);
    
            //Develop_Properties       
            if(!hasExecCommand)
                hasExecCommand = exec_command_developProperties(command);

            //Trade 
            if(!hasExecCommand)
                hasExecCommand = exec_command_tradeResources(command);
            

        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("Nao e possivel executar esse comando! Pode estar num estado diferente do que e permitido!");
        } catch (Error r) {
            throw r;
        }
        
        return hasExecCommand;
        
        
        

    }

//ALL STATES ==============================================================================
    
private boolean exec_command_exitGame(String command)
{
	if(command.trim().equalsIgnoreCase("sair"))
	{
		int leitura;
		leitura = askNumber("Quer gravar o jogo antes de sair?\n1 - Sim\n2 - Nao\n3 - Voltar...Escolha: ",1,3);
		
		if(leitura == 1)
		{
			String nome_fich;
			nome_fich = askString("\nIndique o nome para o jogo que vai guardar\nNome do Ficheiro: ");
			
			try{
				m_fileOperations.writeFile((nome_fich + ".dat"), m_logicaJogo);
				m_fileOperations.writeNameFileToTXT(nome_fich);
			}catch(Exception e)
			{
				writeTextNL("Falha a gravar ficheiros\n");
				return true;
			}
			
			writeTextNL("\nJogo gravado com sucesso\n");
			
			System.exit(0);
		}
		else
		{
			if(leitura == 2)
				System.exit(0);
			else
				return true;
		}
		
		return true;
	}
	return false;
}

private boolean exec_command_SaveToFile(String command)
{
	if(command.trim().equalsIgnoreCase("salvar jogo"))
	{
		String nome_fich;
		nome_fich = askString("\nIndique o nome para o jogo que vai guardar\nNome do Ficheiro: ");
		
		try{
			m_fileOperations.writeFile((nome_fich + ".dat"), m_logicaJogo);
			m_fileOperations.writeNameFileToTXT(nome_fich);
		}catch(Exception e)
		{
			writeTextNL("Falha a gravar ficheiros\n");
			return true;
		}
		writeTextNL("\nJogo gravado com sucesso\n");
		return true;
	}
	
	return false;
}
    
private boolean exec_command_showHand(String command)
{
        if(command.trim().equalsIgnoreCase("Mostrar Mao"))
        {
             
             writeTextNL(m_logicaJogo.getCurrentPlayer().get_Hand().toString());
             return true;
        }
        
        return false;
}

private boolean exec_command_help(String command)
{
       if(command.trim().equalsIgnoreCase("Ajuda"))
       {
           writeTextNL(m_logicaJogo.getStateMachine().help());
           return true;
       }
       
       return false;
}

private boolean exec_command_showTokens(String command)
{
	if(command.trim().equalsIgnoreCase("Mostrar recursos"))
	{
		writeTextNL(m_logicaJogo.getCurrentPlayer().get_TokensPile().toString());
		return true;
	}
	
	return false;
}

private boolean exec_command_showTable(String command)
{
	if(command.trim().equalsIgnoreCase("Mostrar mesa"))
	{
	    writeTextNL("Deck: "+m_logicaJogo.getDeck().getCards().size()+"\n\n" + m_logicaJogo.getTable().toString());
	    return true;
	}
	
	return false;
    
}

private boolean exec_command_detailsTable(String command)
{
	StringTokenizer tokenzier = new StringTokenizer(command);
	
	if(tokenzier.countTokens() == 3 || tokenzier.countTokens() == 4) //detalhes mesa <distrito> <carta>
	{
		if(tokenzier.nextToken().trim().equalsIgnoreCase("Detalhes") && (tokenzier.nextToken().trim().equalsIgnoreCase("mesa")))
		{
			String aux, districtName, cardName;
			districtName = tokenzier.nextToken();
			
			if(tokenzier.hasMoreTokens())
				cardName = tokenzier.nextToken();
			else
				cardName = "";
			
			aux = m_logicaJogo.getTable().getDetails(districtName, cardName);
			
			if(aux.equals(""))
				writeTextNL("Nao foi encontrada nenhuma carta pertencete a mesa\n\n" );
			else
				writeTextNL(aux);
			
			return true;
			
		}
	}
	
	return false;
}

private boolean exec_command_cardDetails(String command)
{
	StringTokenizer tokenzier = new StringTokenizer(command);
	
	if(tokenzier.countTokens() == 2)
	{
		if(tokenzier.nextToken().trim().equalsIgnoreCase("Detalhes")) //detalhes <carta>
		{
			String aux, cardName;
			cardName = tokenzier.nextToken();
			aux = m_logicaJogo.getCurrentPlayer().get_Hand().getDetailsCard(cardName);
			
			if(aux.equals(""))
				writeTextNL("Nao foi encontrada nenhuma carta na mao do jogador com o nome de:" + cardName );
			else
				writeTextNL(aux);
			
			return true;
			
		}
	}
	
	return false;
}


//Roll_Dice State =========================================================================
    
private boolean exec_command_rollDice10(String command)
{
        if(command.trim().equalsIgnoreCase("rodar dados 10"))
        {
            int tax = m_logicaJogo.getStateMachine().rollDicesTen();
                writeTextNL( "Calhou " + m_logicaJogo.getDice().get_dice10_1()+ " e " + m_logicaJogo.getDice().get_dice10_2() + "...");
            if (tax == 1) {
                writeTextNL("Como num dos dados calhou 1, sera necessario fazer a taxacao! Rode o dado de 6 faces para determinar qual o recurso a taxar\n");
            }
            
            return true;
        }
        
        return false;
}
                
//Taxation State ==========================================================================

private boolean exec_command_rollDice6(String command)
{
       if(command.trim().equalsIgnoreCase("rodar dado 6"))
       {
           m_logicaJogo.getStateMachine().rollDiceSix();
           
           writeTextNL("Ao rodar os dados de 6 calhou o numero : " + m_logicaJogo.getDice().get_dice6());
           return true;
       }
       
       return false;
}

private boolean exec_command_doTaxation(String command)
{
	if(command.trim().equalsIgnoreCase("taxar"))
	{  
		String nameTaxedSuit = "";
		
		for (CardSuit c:CardSuit.values()) {
			if (c.getValue() == m_logicaJogo.getDice().get_dice6()) {
				nameTaxedSuit = c.toString();
			}
		}
		
		m_logicaJogo.getStateMachine().doTaxation();
		writeTextNL("\nFoi taxado o naipe: " + nameTaxedSuit + "\n");
		
		return true;
	}
	
	return false;
}



//Collect_Resources State ================================================================

private boolean getResources(String command) {
        
	if(command.trim().equalsIgnoreCase("obter recursos"))
	{
	    Integer n_valueDice1, n_valueDice2, bigValue;
	    String returnedresp;
	    
	    int n_moonsAdd = 0, n_sunsAdd = 0, n_wavesAdd = 0, n_leavesAdd = 0, n_wyrmsAdd = 0, n_knotsAdd = 0; 
	    
	    n_valueDice1 = m_logicaJogo.getDice().get_dice10_1();
	    n_valueDice2 = m_logicaJogo.getDice().get_dice10_2();
	    
	    if(n_valueDice1 >=  n_valueDice2)
	    	 bigValue = n_valueDice1;
	    else
	    	bigValue = n_valueDice2;
	    		 
	    if(bigValue == 10)
	        	writeTextNL("Como o maior valor e 10 vai ser adicionado os recursos correspondentes as coroas");
	    else if(bigValue == 1)
	    		writeTextNL("Como o maior valor e 1 vai ser adicionado os recursos correspondentes aos Ases");
	    	else
	    		writeTextNL("O maior valor e : " + bigValue.toString() + ". Vao ser recolhidos recursos das cartas que tenham o mesmo numero.\n");
	    		
	        for(Player selectPlayer : m_logicaJogo.getPlayers())
	        {
	        	n_moonsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.MOONS);
	        	n_sunsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.SUNS);
	        	n_wavesAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.WAVES);
	        	n_leavesAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.LEAVES);
	        	n_wyrmsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.WYRMS);
	        	n_knotsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.KNOTS);
	        	
	        	m_logicaJogo.getStateMachine().getResources(bigValue, selectPlayer);
	        	
	        	if(selectPlayer.get_tempPile().Count() != 0)
	        	{
	        		Integer n_cardsNotBuilded = selectPlayer.get_tempPile().Count();
	        		writeTextNL("\n\n Ao obter os recursos, " + n_cardsNotBuilded.toString() + " aidna nao se encontravam totalmente construidas, por favor seleccione qual o tipo de recurso que pretende receber\n" );
	        		
	        		for(Card tempCard: selectPlayer.get_tempPile().getCards())
	        		{
	        			CardNumbered cNumbered = (CardNumbered) tempCard;
	        			int resp;
	        			do{
	        				
	        				resp = askNumber("Carta:  " + cNumbered.getName() + " ===================\n Indique o tipo de recurso que pretende obter\n-> "
	        						+ cNumbered.getSuit() + "\n2 -> " + cNumbered.getSuit2()+ "\n"+ selectPlayer.get_PlayerName() + " ->", 1, 2);
	        			}while(resp == -1);
	        			
	        			m_logicaJogo.getStateMachine().chooseResourceNumberedNotConstructed(resp, tempCard, selectPlayer);
	        			
	        		
	        		}
	        	}
	        	
	        	n_moonsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.MOONS) - n_moonsAdd;
	        	n_sunsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.SUNS) - n_sunsAdd;
	        	n_wavesAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.WAVES) - n_wavesAdd;
	        	n_leavesAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.LEAVES) - n_leavesAdd;
	        	n_wyrmsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.WYRMS) - n_wyrmsAdd;
	        	n_knotsAdd = selectPlayer.get_TokensPile().countTokensSuit(CardSuit.KNOTS) - n_knotsAdd;
	        	
	        	
	        	writeTextNL(selectPlayer.get_PlayerName() + " ==========================\nForam adicionados os recursos:\nMoons: " +
	        			n_moonsAdd + "\nSuns: " + n_sunsAdd + "\nWaves: " + n_wavesAdd + "\nLeaves: " + n_leavesAdd + "\nWyrms: "
	        			+ n_wyrmsAdd + "\nKnots: " + n_knotsAdd + "\n\n");
	        }
	        
	        m_logicaJogo.getStateMachine().setState(new States.PlayCard(m_logicaJogo.getStateMachine(), m_logicaJogo));
	        
	        exec_command_chooseOptionsPlay();
	        return true;
	}
	
	return false;
}


//PlayCard State ========================================================================
private void exec_command_chooseOptionsPlay() {
        
	int leitura;
	
	do{
		leitura = askNumber("\n Escolha o que pretende fazer:\n1 - Completar uma Propriedade\n2 - Comprar uma escritura\n3 - Vender uma carta\nEscolha: ", 1, 3);
	}while(leitura == -1);
	
	m_logicaJogo.getStateMachine().chooseOption(leitura);
}

private boolean exec_command_backPlayState(String command){
	
	if(command.trim().equalsIgnoreCase("Voltar atras"))
	{
		//m_logicaJogo.getStateMachine().
		return true;
	}
	return false;
}

//Max_Property State ===================================================================

private boolean exec_command_payTokensToCompletlyDevelop(String command) {
	
	StringTokenizer tokenizer = new StringTokenizer(command);

	if(tokenizer.countTokens() == 3) //construir <nome_distrito> <nome_carta>
	{	
	
		if(tokenizer.nextToken().trim().equalsIgnoreCase("Construir"))
		{  
			String name_districtCard = tokenizer.nextToken().trim();
			String name_card = tokenizer.nextToken().trim();
			Card card = null;
			District cDistrict = null;
			CardNumbered cNumbered = null;
			
			
			int number_recurso1 = 0, number_recurso2 = 0, chooseAce = 0;
			
			for(District dt:m_logicaJogo.getTable().get_Districts())
			{
				if(dt.getDistrict().getName().equalsIgnoreCase(name_districtCard))
				{
					cDistrict = dt;
				}
			}
			
			if(cDistrict == null)
			{
				writeTextNL("<Erro> Nao foi encontrado o distrito pretendido\n");
				return true;
			}
			
			ArrayList<Card> cardsFromHand = m_logicaJogo.getCurrentPlayer().get_Hand().getHandCardbyName(name_card);
			
			if(cardsFromHand.size() <= 0)
			{
				writeTextNL("<Erro> Nao foi encontrado a carta da mao pretendida\n");
				return true;
			}
			
			
			
			if(cardsFromHand.size() > 1) //tem mais que um As
			{
				String aux = "", something;
				for(int i = 0; i < cardsFromHand.size(); i++)
				{
					aux+="\nAs - " + (i+1) + "======================" + "\nNaipe: " + cardsFromHand.get(i).getSuit() + "\n\n";
				}
				
				something = "Foram encontrados mais que um tipo de As, por favor escolha qual pretende adicionar\n" + aux;
				
				do{
					chooseAce = askNumber(something, 1, cardsFromHand.size());
				}while(chooseAce == -1);
				
				card = cardsFromHand.get(chooseAce-1);
			}
			
			if(cardsFromHand.size() == 1)
				card = cardsFromHand.get(0);
			
			if(card.getType().equals(CardType.ACE))
			{
				
//				number_recurso1 = askNumber("\nCarta a ser construida: " +card.getName() + "\nQual os recursos a gastar para o naipe: \n" + card.getSuit() +": ");
//				
//				if(number_recurso1 == -1)
//					return true;
				
				number_recurso1 = m_logicaJogo.getCurrentPlayer().get_TokensPile().countTokensSuit(card.getSuit());
				
				if(number_recurso1 < 3)
					throw new Error("<Erro> Nao e possivel comprar o AS, pois nao possui 3 recursos do mesmo tipo");
			}
			
			if(card.getType().equals(CardType.NUMBERED))
			{
				cNumbered = (CardNumbered) card;
				number_recurso1 = askNumber("\nCarta a ser construida: " +cNumbered.getName() + "\nQual os recursos a gastar para o naipe: \n" + cNumbered.getSuit().toString()+ ": ");
				
				if(number_recurso1 == -1)
					return true;
				
				number_recurso2 = askNumber("\n"+cNumbered.getSuit2().toString()+ ": ");
					
				if(number_recurso2 == -1)
					return true;
			}
			
			ArrayList<Token> tokens_toPay = new ArrayList<>();
			
			for(int i = 0 ; i < number_recurso1; i++)
				tokens_toPay.add(new Token(card.getSuit()));
			
			
			if(number_recurso2 > 0)
			{
				for(int i = 0 ; i < number_recurso2; i++)
					tokens_toPay.add(new Token(cNumbered.getSuit2()));
			}
		
			m_logicaJogo.getStateMachine().payTokensToCompletlyDevelop(cDistrict, card, tokens_toPay);
			
			return true;
		
		}
	}      
	
	return false;
	
}
    
    
//Purchase_Deed State ==================================================================

private boolean exec_command_buyAdeed(String command) {
	StringTokenizer tokenizer = new StringTokenizer(command);

	if(tokenizer.countTokens() == 4) //comprar escritura <nome_distrito> <nome_carta>
	{	
		
		if(tokenizer.nextToken().trim().equalsIgnoreCase("comprar") && tokenizer.nextToken().trim().equalsIgnoreCase("escritura"))
		{  
			String name_districtCard = tokenizer.nextToken().trim();
			String name_card = tokenizer.nextToken().trim();
			Card card = null;
			District cDistrict = null;
			CardNumbered cNumbered = null;
			
			
			int number_recurso1 = 0, number_recurso2 = 0, chooseAce = 0;
			
			
			//Obter a carta de distrito
			for(District dt:m_logicaJogo.getTable().get_Districts())
			{
				if(dt.getDistrict().getName().equalsIgnoreCase(name_districtCard))
				{
					cDistrict = dt;
				}
			}
			
			if(cDistrict == null)
			{
				writeTextNL("<Erro> Nao foi encontrado o distrito pretendido\n");
				return true;
			}
			
			//obter a carta da mao
			ArrayList<Card> cardsFromHand = m_logicaJogo.getCurrentPlayer().get_Hand().getHandCardbyName(name_card);
			
			if(cardsFromHand.size() <= 0)
			{
				writeTextNL("<Erro> Nao foi encontrado a carta da mao pretendida\n");
				return true;
			}
			
			card = cardsFromHand.get(0);
			
			
		
			m_logicaJogo.getStateMachine().buyAdeed(cDistrict, card);
			
			return true;
		
		}
	}      
	
	return false;
}

//Sell_Card State
private boolean exec_command_sellCard(String command) {
	StringTokenizer tokenizer = new StringTokenizer(command);

	if(tokenizer.countTokens() == 2) //vender <nome_carta>
	{	
	
		if(tokenizer.nextToken().trim().equalsIgnoreCase("vender"))
		{  
			String name_card = tokenizer.nextToken().trim();
			Card card = null;
			CardNumbered cNumbered = null;
			int chooseAce = 0;
			
			ArrayList<Card> cardsFromHand = m_logicaJogo.getCurrentPlayer().get_Hand().getHandCardbyName(name_card);
			
			if(cardsFromHand.size() <= 0)
			{
				writeTextNL("<Erro> Nao foi encontrado a carta da mao pretendida\n");
				return true;
			}
			
			
			
			if(cardsFromHand.size() > 1) //tem mais que um As
			{
				String aux = "", something;
				for(int i = 0; i < cardsFromHand.size(); i++)
				{
					aux+="\nAs - " + (i+1) + "======================" + "\nNaipe: " + cardsFromHand.get(i).getSuit() + "\n\n";
				}
				
				something = "Foram encontrados mais que um tipo de As, por favor escolha qual pretende adicionar\n" + aux+"Escolha:";
				
				do{
					chooseAce = askNumber(something, 1, cardsFromHand.size());
				}while(chooseAce == -1);
				
				card = cardsFromHand.get(chooseAce-1);
			}
			
			if(cardsFromHand.size() == 1)
				card = cardsFromHand.get(0);
					
			m_logicaJogo.getStateMachine().sellCard(card);
			
			return true;
		
		}
	}      
	
	return false;
	
}
            
    
//Draw Card
private boolean exec_command_drawCard(String command) {
	 
	 if(command.trim().equalsIgnoreCase("comprar carta"))
     {
         m_logicaJogo.getStateMachine().drawCard();
         
         writeTextNL("Foi adicionada uma nova carta a Mao, mudanca de turno");
         return true;
     }
     
     return false;
}

           
    
//Develop_Properties
private boolean exec_command_developProperties(String command) {
	
	StringTokenizer tokenizer = new StringTokenizer(command);

	if(tokenizer.countTokens() == 4) //desenvolver propriedade <nome_distrito> <nome_carta>
	{	
		
		if(tokenizer.nextToken().trim().equalsIgnoreCase("desenvolver") && tokenizer.nextToken().trim().equalsIgnoreCase("propriedade"))
		{  
			String name_districtCard = tokenizer.nextToken().trim();
			String name_card = tokenizer.nextToken().trim();
			Card card = null;
			District cDistrict = null;
			CardNumbered cNumbered = null;
			
			
			int number_recurso1 = 0, number_recurso2 = 0, chooseAce = 0;
			
			
			ArrayList<Card> cardsfromTable = m_logicaJogo.getTable().getCardbyDistrictName(name_districtCard, name_card, m_logicaJogo.getCurrentPlayer());
			
			if(cardsfromTable.size() > 1 || cardsfromTable.size() <= 0)
				throw new Error("Nao foi possivel obter as cartas pretendidas, ou nao existem ou pretende obter Ases");
			
			cNumbered = (CardNumbered) cardsfromTable.get(0);
			
			number_recurso1 = askNumber("\nCarta a ser desenvolvida: " +cNumbered.getName() + "\nQual os recursos a gastar para o naipe: \n" + cNumbered.getSuit().toString()+ ": ");
				
			if(number_recurso1 == -1)
				return true;
				
			number_recurso2 = askNumber("\n"+cNumbered.getSuit2().toString()+ ": ");
					
			if(number_recurso2 == -1)
				return true;
			
			
			ArrayList<Token> tokens_toPay = new ArrayList<>();
			
			for(int i = 0 ; i < number_recurso1; i++)
				tokens_toPay.add(new Token(cNumbered.getSuit()));
			
			for(int i = 0 ; i < number_recurso2; i++)
				tokens_toPay.add(new Token(cNumbered.getSuit2()));
			
		
			if( m_logicaJogo.getStateMachine().developPropertie(tokens_toPay, cNumbered))
				writeTextNL("A carta " + cNumbered.getName() + " foi construida\n");
			
			return true;
		
		}
	}      
	
	return false;
}


//Trade 
private boolean exec_command_tradeResources(String command) {
	
	
	if(command.trim().equalsIgnoreCase("trocar recursos"))
	{
		ArrayList<Token> tk_toPay = new ArrayList<Token>();
		
		String suits;
		int resource_toTrade, resource_receive;
		suits = "1 - Moons\n2 - Suns\n3 - Waves\n4 - Leaves\n5 - Wyrms\n6 - Knots";
		
		resource_toTrade = askNumber("Indique quais os recursos (3) do mesmo tipo que serao retirados para troca:\n" + suits + "\nEscolha: ", 1, 6);
		
		if(resource_toTrade == -1)
			return  true;
		
		ArrayList<Token> aux_token = new ArrayList<>();
		
		CardSuit suitsToTrade = null; 
		//retirar os recursos do tokens_pile
		for(CardSuit suit : CardSuit.values())
		{
			if(suit.getValue() == resource_toTrade)
			{
				suitsToTrade = suit;
				break;
			}
		}	
		
		int counter = 3;
			
		
		for(Iterator<Token> it = m_logicaJogo.getCurrentPlayer().get_TokensPile().getCards().iterator(); it.hasNext(); )
		{
			Token tk = it.next();
		
			if(tk.getTokenSuit() == suitsToTrade && counter > 0)
			{
				counter--;
				tk_toPay.add(tk);
				it.remove();
			}
		}
		
		resource_toTrade = askNumber("Indique qual o recurso que pretende receber:\n" + suits + "\nEscolha: ", 1, 6);
		
		if(resource_toTrade == -1)
		{
			for(Token tk : tk_toPay)
			{
				m_logicaJogo.getCurrentPlayer().get_TokensPile().addToken(tk);
				
			}
			
			tk_toPay.clear();
			return  true;
		}
		
		suitsToTrade = null; 
		//retirar os recursos do tokens_pile
		for(CardSuit suit : CardSuit.values())
		{
			if(suit.getValue() == resource_toTrade)
			{
				suitsToTrade = suit;
				break;
			}
		}	
		
		try{
			m_logicaJogo.getStateMachine().tradeResources(tk_toPay, suitsToTrade);
		}catch(Exception e)
		{
			//adicionar de volta os recursos
			for(Token tk : tk_toPay)
			{
				m_logicaJogo.getCurrentPlayer().get_TokensPile().addToken(tk);
			}
			
			throw e;
			
		}
		return true;
		
		
	}
	
	return false;
}


    
    
// CONSOLE FUNCTIONS

private void writeTextNL(String text) //Escreve o text com um \n no fim
{
        System.out.println(text);
}

    private void writeText(String text) {
        System.out.print(text);
    }

    private String askString(String something) {
        String text;
        Scanner le_string = new Scanner(System.in);

        writeText(something);
        text = le_string.nextLine();

        return text;
    }
    
    private int askNumber(String something)
    {
    	Scanner le_int = new Scanner(System.in);
    	
    	writeText(something);
    	
    	if(le_int.hasNextInt())
    	{	
    		return le_int.nextInt();
    	}
    	
    	writeTextNL("O tipo de resposta nao e reconhecido, por favor utilize numeros");
    	return -1;
    }
    
    private int askNumber(String something, int min, int max)
    {
    	Scanner le_int = new Scanner(System.in);
    	int leitura;
    	writeText(something);
    	
    	if(le_int.hasNextInt())
    	{
    		leitura = le_int.nextInt();
    		
    		if(leitura < min || leitura > max)
    		{
    			writeTextNL("O numero escolhido nao esta dentro dos parametros de escolha!");
    			return -1;
    		}
    		
    		return leitura;
    	}
    	
    	writeTextNL("O tipo de resposta nao e reconhecido, por favor utilize numeros");
    	return -1;
    }
    

    
}

