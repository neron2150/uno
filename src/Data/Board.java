package Data;

import java.util.ArrayList;
import java.util.Random;

import Data.Cards.Card;
import Enums.Color;

public class Board {
	private boolean sequence = true;
	private ArrayList<Card> facedDownDeck = new ArrayList<Card>();
	private ArrayList<Card> facedUpDeck = new ArrayList<Card>();
	private Color color;
	
	public Board(){
		
		facedDownDeck = DeckWorker.createNewDeck();
		
	}
	
	
	
	public ArrayList<Card> getfacedDownDeck() {
		return facedDownDeck;
	}

	public void addFacedUpCard(Card card) {
		this.facedUpDeck.add(card);
	}

	public ArrayList<Card> getFacedUpDeck() {
		return facedUpDeck;
	}

	public void addFacedDuwnCard(Card card) {
		this.facedDownDeck.add(card);
	}
	
	public Card takeCard(){
		Card card = facedDownDeck.get(facedDownDeck.size()-1);
		facedDownDeck.remove(card);
		return card;
	}
	public Card getFUC(){
		Card card = facedUpDeck.get(facedUpDeck.size()-1);
		
		return card;
	}
	public Card[] takeTwoCards(){
		Card[] cards = new Card[2]; 
		cards[0] = takeCard();
		cards[1] = takeCard();
		return cards;
	}
	public Card[] takeFourCards(){
		Card[] cards = new Card[4]; 
		cards[0] = takeCard();
		cards[1] = takeCard(); 
		cards[2] = takeCard();
		cards[3] = takeCard();
		return cards;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}



	public boolean isSequence() {
		return sequence;
	}



	public void setSequence(boolean turn) {
		this.sequence = turn;
	}
	public void turnSequence() {
		this.sequence = !sequence;
	}


	public void setRandomColor() {
		Random r = new Random();
		int x = r.nextInt(5);
		switch(x){
		case 0: this.setColor(Color.BLUE); break;
		case 1: this.setColor(Color.GREEN); break;
		case 2: this.setColor(Color.RED); break;
		case 3: this.setColor(Color.YELLOW); break;
		}
		
	}
}
