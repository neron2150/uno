package Data;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import Data.Cards.Card;


public class Window extends JFrame implements Runnable {
	
	Window(){
		super("Uno");
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Painter.init(getGraphics(),this);
		Game.frame = this;
		
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent evt){
				switch(evt.getKeyCode()){
				case KeyEvent.VK_LEFT:if(!Game.checkForWin()){ Game.prePlayerCard(); } break;
				case KeyEvent.VK_RIGHT:if(!Game.checkForWin()){Game.nextPlayerCard();} break;
				case KeyEvent.VK_UP:if(!Game.checkForWin()){ Game.prePlayerColor(); } break;
				case KeyEvent.VK_DOWN:if(!Game.checkForWin()){Game.nextPlayerColor();} break;
				case KeyEvent.VK_T:if(!Game.checkForWin()){if(Game.cardAble){
					Player.getCurrent().giveCard(Game.board.takeCard());
					Game.cardAble = false;}
					} 
				break;
				case KeyEvent.VK_S:if(!Game.checkForWin()){
					if(Player.getCurrentNum() == 0){
						
						Game.skiped = true;
						Game.waitingForPlayer = false;
					}
				}
					break;
					
				case KeyEvent.VK_SPACE:if(!Game.checkForWin()){ if(Player.getCurrentNum()==0){
					Card c = AI.checkCard(Player.getCurrent().getDeck().get(Game.getCurrentCard()));
					
					if(c!=null)
					{
						
						Game.waitingForPlayer = false;
					}
					
				}} break;
				
				
				
				}}
		});
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		int i = 0;
		while(true){
		try {
			Thread.sleep(30);
			if(!Game.checkForWin()){
				Painter.paintGame();
			}
			
			else{
				break;
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		Painter.paintMenu();
	}
	
	
	
	

}
