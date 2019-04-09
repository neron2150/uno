package Data.Cards;

import java.awt.Image;

import Enums.CardType;

public  class Card {
	protected CardType type;
	
	protected Image img;
	protected String name;
	public Card(){
			
	}
	
	
	
	
	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
	
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
