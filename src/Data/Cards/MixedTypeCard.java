package Data.Cards;

import Enums.ActionType;
import Enums.CardType;
import Enums.Color;

public class MixedTypeCard extends ActionCard implements Colored{
	private Color color;
	public MixedTypeCard(ActionType AT,Color c) {
		super(AT);
		setType(CardType.MIXED);
		setColor(c);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

}
