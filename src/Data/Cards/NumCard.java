
package Data.Cards;

import Enums.Color;
import Enums.CardType;;

public class NumCard extends Card implements Colored {
	private int num;
	private Color color;
	
		public NumCard(int num, Color c) {
			setNum(num);
			setColor(c);
			setType(CardType.NUMBER);
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
}
