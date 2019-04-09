package Data;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Random;

import Data.Cards.ActionCard;
import Data.Cards.Card;
import Data.Cards.Colored;
import Enums.CardType;
import Enums.Color;

public class Game {
	static Board board;
	public static Window frame;
	private static boolean started = true;
	private static int currentCard = 0;
	private static int currentColor = 0;
	public static boolean waitingForPlayer = true;
	public static boolean cardAble = true;
	public static boolean skiped = false;
	private static Color selectedColor = Color.GREEN;
	
	public static void main(String[] args) {
		
			init();
			giveCards();
			
		
			play();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Painter.paintMenu();
		
	}
	
	private static void play() {
		Random r = new Random();
		Player.setCurrent(r.nextInt(4));
		while(isStarted()){
			if(checkForWin()){
				return;
			}
			if(Player.getCurrentNum()!=0){
			
			
			Card c = AI.getMove();
			if(c == null) {Player.nextPlayer(); continue;}
			Player.getCurrent().putCard(c);
			switch (c.getType()) {
			case NUMBER:	break;
			
			case ACTION:	
				switch(((ActionCard)c).getAction()){
				case ADD_4: add4(); break;
				
				case SWITCH_COLOR: break;
				
				}
				
				break;
			
			case MIXED:	
				switch(((ActionCard)c).getAction()){
				
				case ADD_2: add2();break;
				
				case SKIP: skip();break;
				case TURN_SEQUENCE: board.turnSequence(); break;
				}break;
			
		}
			
			Player.nextPlayer();
			
			log(c.getName());
		}
			else{
				cardAble = true;
		
				while(waitingForPlayer){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
				if(skiped) {Player.nextPlayer(); waitingForPlayer = true; skiped = false; continue;}
				
				
				Card c = Player.getCurrent().getDeck().get(getCurrentCard());
				
				
				Player.getCurrent().putCard(c);
				switch (c.getType()) {
				case NUMBER:	break;
				
				case ACTION:	
					switch(((ActionCard)c).getAction()){
					case ADD_4: add4(); board.setColor(selectedColor);break;
					
					case SWITCH_COLOR: board.setColor(selectedColor);break;
						
					}
					
					break;
				
				case MIXED:	
					switch(((ActionCard)c).getAction()){
					
					case ADD_2: add2();break;
					
					case SKIP: skip();break;
					case TURN_SEQUENCE: board.turnSequence(); break;
					}break;
				
			}
				
				Player.nextPlayer();
				
				log(c.getName());
				waitingForPlayer = true;
				prePlayerCard();
				
				
			}
		}
	}

	

	public static boolean checkForWin() {
		for(Player p : Player.getPlayers()){
			if (p.getDeck().size() == 0)
			{
			
				return true;
			}
		}
		return false;
	}
	public static int checkWhoWin() {
		for(Player p : Player.getPlayers()){
			if (p.getDeck().size() == 0)
			{
				return p.getID();
			}
		}
		return 0;
	}
	
	private static void giveCards() {
		for(int i = 0;i<7;i++){
			Player.getPlayers()[0].giveCard(board.takeCard());
			Player.getPlayers()[1].giveCard(board.takeCard());
			Player.getPlayers()[2].giveCard(board.takeCard());
			Player.getPlayers()[3].giveCard(board.takeCard());
		}
		
		while(true){
			Card c = board.takeCard();
			if(c.getType()== CardType.MIXED||c.getType()== CardType.NUMBER){
				board.addFacedUpCard(c);
				Colored color = (Colored) c;
				log("----"+c.getName()+"----");
				board.setColor(color.getColor());
				return;
			}
			
		}
	}

	static void init(){
		Game.board = new Board();
		Player[] p = new Player[4];
		p[0]=new Player("Игрок",0);
		p[1]=new Player("Компьютер 1",1);
		p[2]=new Player("Компьютер 2",2);
		p[3]=new Player("Компьютер 3",3);
		
		Player.setPlayers(p);
		Window window = new Window();
	}
	static void log(String s){
		System.out.println(s);
	}

	public static boolean isStarted() {
		return started;
	}

	public static void setStarted(boolean started) {
		Game.started = started;
	}
	private static void add4(){
		for(Card c: board.takeFourCards())
		Player.getNext().giveCard(c);
	}
	private static void add2(){
		for(Card c: board.takeTwoCards())
			Player.getNext().giveCard(c);
	}
	private static void skip(){
		Player.nextPlayer();
	}
	public static int getCurrentCard() {
		return currentCard;
	}
	public static void prePlayerCard() {
		currentCard--;
		if(currentCard == -1) currentCard = Player.getPlayers()[0].getDeck().size()-1;
	}
	public static void nextPlayerCard() {
		currentCard++;
		if(Player.getPlayers()[0].getDeck().size()==currentCard) currentCard=0;
	
	}
	
	public static void prePlayerColor() {
		currentColor--;
		if(currentColor == -1) currentColor = 3;
		switch(currentColor){
		case 0: selectedColor = Color.BLUE;break;
		case 1: selectedColor = Color.YELLOW;break;
		case 2: selectedColor = Color.GREEN;break;
		case 3: selectedColor = Color.RED;break;
		
		}
	}
	public static void nextPlayerColor() {
		currentColor++;
		if(currentColor == 4) currentColor = 0;
		switch(currentColor){
		case 0: selectedColor = Color.BLUE;break;
		case 1: selectedColor = Color.YELLOW;break;
		case 2: selectedColor = Color.GREEN;break;
		case 3: selectedColor = Color.RED;break;
		
		}
	}

	public static void setCurrentCard(int currentCard) {
		Game.currentCard = currentCard;
	}

	public static Color getSelectedColor() {
		return selectedColor;
	}

	public static void setSelectedColor(Color selectedColor) {
		Game.selectedColor = selectedColor;
	}

	public static int getCurrentColor() {
		return currentColor;
	}

	public static void setCurrentColor(int currentColor) {
		Game.currentColor = currentColor;
	}
}
