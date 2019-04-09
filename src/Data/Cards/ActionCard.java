package Data.Cards;

import Enums.ActionType;
import Enums.CardType;
import Enums.Color;

public class ActionCard extends Card {
	private ActionType action;

	public ActionCard(ActionType AT){
		
		setType(CardType.ACTION);
		setAction(AT);
		
	}
	public ActionType getAction() {
		return action;
	}
	public void setAction(ActionType action) {
		this.action = action;
	}
}
