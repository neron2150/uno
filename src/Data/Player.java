package Data;

import java.util.ArrayList;

import Data.Cards.Card;

public class Player {
	private static Player[] players;
	private ArrayList<Card> Deck = new ArrayList<Card>();
	private String name;
	private int ID;
	private static int current;
	
	public Player(String name,int ID){
		setName(name);
		setID(ID);
	}
	public ArrayList<Card> getDeck() {
		return Deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		 Deck = deck;
	}
	public void giveCard(Card card) {
		Deck.add(card);
	}
	public void putCard(Card card) {
		Deck.remove(card);
		Game.board.addFacedUpCard(card);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	public static Player[] getPlayers() {
		return players;
	}
	public static void setPlayers(Player[] players) {
		Player.players = players;
	}
	public static void nextPlayer() {
		if(Game.board.isSequence()){
			setCurrent(getCurrentNum()+1);
		}
		else{
			setCurrent(getCurrentNum()-1);
		}
		
		switch(current){
		case 4:setCurrent(0);break;
		case -1:setCurrent(3);break;
		}
	}
	public static Player getCurrent() {
		return players[current];
	}
	public static Player getNext() {
		int result = 0;
		if(Game.board.isSequence()){
			result =  getCurrentNum()+1;
		}
		else{
			result = getCurrentNum()-1;
		}
		
		switch(result ){
		case 4:result = 0;break;
		case -1:result = 3;break;
		}
		return players[result];
	}
	public static int getCurrentNum() {
		return current;
	}
	public static int get() {
		return current+1;
	}
	public static void setCurrent(int current) {
		Player.current = current;
	}
	
}
