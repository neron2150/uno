package Data;
import Enums.CardType;
import Enums.Color;

import java.util.Random;

import Data.Cards.ActionCard;
import Data.Cards.Card;
import Data.Cards.Colored;
import Data.Cards.NumCard;

public class AI {
	public static Card getMove(){
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Card bc = Game.board.getFUC();
		Color color = Game.board.getColor();
		for(Card pc : Player.getCurrent().getDeck()){
			switch (bc.getType()) {
				case NUMBER:	if(	checkColor(pc,color)
									||
									checkNum(pc,bc)){		Game.board.setColor(((Colored)pc).getColor());	return pc;}else if(checkOnlyAction(pc,bc)){Game.board.setRandomColor(); return pc;}
								break;
				
				case MIXED:		if(	checkColor(pc,color)
									||
									checkAction(pc,bc))	{	Game.board.setColor(((Colored)pc).getColor());	return pc;}else if(checkOnlyAction(pc,bc)){Game.board.setRandomColor(); return pc;}
								break;
								
				case ACTION:	if(	checkColor(pc,color)){	Game.board.setColor(((Colored)pc).getColor());	return pc;} else if(checkOnlyAction(pc,bc)){	Game.board.setRandomColor();	return pc;}
				break;
								
				
			}
		
		
		}
		Random r = new Random();
		if(r.nextBoolean()){
			return null;
		}
		else {
			Card pc = Game.board.takeCard();
			Player.getCurrent().giveCard(pc);
		
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch (bc.getType()) {
			case NUMBER:	if(	checkColor(pc,color)
								||
								checkNum(pc,bc)){		Game.board.setColor(((Colored)pc).getColor());	return pc;}else if(checkOnlyAction(pc,bc)){Game.board.setRandomColor(); return pc;}
							break;
			
			case MIXED:		if(	checkColor(pc,color)
								||
								checkAction(pc,bc))	{	Game.board.setColor(((Colored)pc).getColor());	return pc;}else if(checkOnlyAction(pc,bc)){Game.board.setRandomColor(); return pc;}
							break;
							
			case ACTION:	if(	checkColor(pc,color)){	Game.board.setColor(((Colored)pc).getColor());	return pc;} else if(checkOnlyAction(pc,bc)){	Game.board.setRandomColor();	return pc;}
							break;
							
			
		}
			return null;
		}
	}
	private static boolean checkOnlyAction(Card pc, Card bc) {
		if(pc.getType()==CardType.ACTION){
			
					return true;
		}
		return false;
	}
	private static boolean checkAction(Card pc, Card bc) {
		if(pc.getType()==CardType.MIXED){
			if(((ActionCard)pc).getAction()==((ActionCard)bc).getAction())
					return true;
		}
		return false;
	}
	private static boolean checkColor(Card pc, Color color) {
		if(pc.getType()==CardType.NUMBER||pc.getType()==CardType.MIXED)
		{
			if(((Colored)pc).getColor()==color)
				return true;
			
			
			}
		return false;
	}
	private static boolean checkNum(Card pc,Card bc){
		if(pc.getType()==CardType.NUMBER)
		{
			if(((NumCard)pc).getNum()==((NumCard)bc).getNum())
				return true;
			
			
			}
		return false;
		
	}
	public static Card checkCard(Card pc){
		Card bc = Game.board.getFUC();
		Color color = Game.board.getColor();
		
			switch (bc.getType()) {
				case NUMBER:	if(	checkColor(pc,color)
									||
									checkNum(pc,bc)){		Game.board.setColor(((Colored)pc).getColor());	return pc;}else if(checkOnlyAction(pc,bc)){ return pc;}
								break;
				
				case MIXED:		if(	checkColor(pc,color)
									||
									checkAction(pc,bc)
										
										)	{	Game.board.setColor(((Colored)pc).getColor());	return pc;}else if(checkOnlyAction(pc,bc)){ return pc;}
								break;
								
				case ACTION:	if(	checkColor(pc,color)){	Game.board.setColor(((Colored)pc).getColor());	return pc;} else if(checkOnlyAction(pc,bc)){	Game.board.setRandomColor();	return pc;}
				break;
								
				
			}
		
		
		return null;
	}
}
