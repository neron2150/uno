package Data;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Data.Cards.ActionCard;
import Data.Cards.Card;
import Data.Cards.MixedTypeCard;
import Data.Cards.NumCard;
import Enums.Color;
import Enums.ActionType;;
public class DeckWorker {
	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static ArrayList<Card> mixedDeck = new ArrayList<Card>();
	public static ArrayList<Card> createNewDeck(){
		addPackNum(Color.RED);
		addPackNum(Color.GREEN);
		addPackNum(Color.BLUE);
		addPackNum(Color.YELLOW);
		
		addActionColoredPack(Color.RED);
		addActionColoredPack(Color.GREEN);
		addActionColoredPack(Color.BLUE);
		addActionColoredPack(Color.YELLOW);
		
		addActionPack();
		shuffle();
		
		return mixedDeck;
	}
	private static void shuffle() {
		Random r = new Random();
		int x = 0;
	for(int i = 0;i<108;i++){
		
		x = r.nextInt(108-i);
		mixedDeck.add(deck.get(x));
		deck.remove(x);
	}
		
	}
	private static void addPackNum(Color color){
		try{
			String clr = "";
			switch(color){
			case RED:clr = "red";break;
			case YELLOW:clr = "yellow";break;
			case BLUE:clr = "blue";break;
			case GREEN:clr = "green";break;
			}
			for(int i = 0;i<10;i++){
				Image img;
				String name;
				name = clr+"_"+i;
				img  = ImageIO.read(new File("src/Img/"+name+".png"));
				Card card = new NumCard(i,color);
				card.setImg(img);
				card.setName(name);
				deck.add(card);
			}
			for(int i = 1;i<10;i++){
				Image img;
				String name;
				name = clr+"_"+i;
				img  = ImageIO.read(new File("src/Img/"+name+".png"));
				Card card = new NumCard(i,color);
				card.setImg(img);
				card.setName(name);
				deck.add(card);
			}
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
	
	private static void addActionColoredPack(Color color){
		deck.add(createCard(color,10));
		deck.add(createCard(color,11));
		deck.add(createCard(color,12));
		
		deck.add(createCard(color,10));
		deck.add(createCard(color,11));
		deck.add(createCard(color,12));
		
	}
	private static void addActionPack(){
		for(int i = 0;i<4;i++){
		deck.add(createCard("add_4"));
		deck.add(createCard("change_color"));
		}
		
	}
	private static Card createCard(Color color,int num){
		try{
			String clr = "";
			switch(color){
			case RED:clr = "red";break;
			case YELLOW:clr = "yellow";break;
			case BLUE:clr = "blue";break;
			case GREEN:clr = "green";break;
			}
			ActionType AT = ActionType.ADD_2;
			switch(num)
			{

			case 10:AT = ActionType.SKIP;break;
			case 11:AT = ActionType.TURN_SEQUENCE;break;
			case 12:AT = ActionType.ADD_2;break;
			
			}
				Image img;
				String name;
				name = clr+"_"+num;
				img  = ImageIO.read(new File("src/Img/"+name+".png"));
				Card card = new MixedTypeCard(AT,color);
				card.setImg(img);
				card.setName(AT.toString());
				return (card);
			
			
			
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	private static Card createCard(String name){
		try{
			ActionType AT;
			String clr = "";
			if(name == "add_4"){
			AT = ActionType.ADD_4;
			}
			else
			{
			 AT = ActionType.SWITCH_COLOR;
			}
			
				Image img;
				img  = ImageIO.read(new File("src/Img/"+name+".png"));
				Card card = new ActionCard(AT);
				card.setImg(img);
				card.setName(AT.toString());
				return (card);
			
			
			
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	
}
