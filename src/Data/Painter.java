package Data;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Data.Cards.Card;

public class Painter {
	private static Graphics g;
	private static Window window;
	private static Point[]p = {
			new Point(395,450),
			new Point(130,295),
			new Point(395,150),
			new Point(670,295)
	};
	private static Image fdc,arrow1,arrow2, help;
	private static BufferedImage buffer;
	private static boolean waiting = true;
	private static int end;
	static void init(Graphics g, Window w){
		buffer =new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		
		Painter.g = buffer.createGraphics();
		Painter.window = w;
		try {

			arrow1 = ImageIO.read(new File("src/Img/arrow1.png"));
			arrow2 = ImageIO.read(new File("src/Img/arrow2.png"));
			fdc = ImageIO.read(new File("src/Img/back_side.png"));
			help = ImageIO.read(new File("src/Img/help.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void paintGame() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		fillBoard();
		paintPlayerCards();
		paintBoardCards();
		paintDecks();
		paintArrow();
		paintColors();
		Point point = p[Player.getCurrent().getID()] ;
		g.setColor(new java.awt.Color(1f,1f,1f));
		g.fillOval(point.x, point.y, 20, 20);
		g.drawImage(help,590, 30,window);
		update();
	}
	private static void paintColors() {
		
		g.setColor(new java.awt.Color(1f,0f,0f));
		g.fillRect(0, 560, 40, 40);
		g.setColor(new java.awt.Color(0f,1f,0f));
		g.fillRect(40, 560, 40, 40);
		g.setColor(new java.awt.Color(1f,1f,0f));
		g.fillRect(80, 560, 40, 40);
		g.setColor(new java.awt.Color(0f,0f,1f));
		g.fillRect(120, 560, 40, 40);
		
		switch(Game.getSelectedColor()){
		case RED :{
			g.setColor(new java.awt.Color(1f,0f,0f));
			g.fillRect(0, 557, 46, 46);
			break;
		}
		case GREEN :{
			g.setColor(new java.awt.Color(0f,1f,0f));
			g.fillRect(40, 557, 46, 46);
			break;
		}
		case YELLOW :{
			g.setColor(new java.awt.Color(1f,1f,0f));
			g.fillRect(80, 557, 46, 46);
			break;
		}
		case BLUE :{
			g.setColor(new java.awt.Color(0f,0f,1f));
			g.fillRect(120, 557, 46, 46);
			break;
		}
		}
		
	}
	private static void paintArrow() {
		if(Game.board.isSequence()){
		g.drawImage(arrow1, 800/2-150, 600/2-150, 300, 300, window);}
		else
			{g.drawImage(arrow2, 800/2-150, 600/2-150, 300, 300, window);}
		
		
	}
	private static void paintDecks() {
		int x =  Player.getPlayers()[1].getDeck().size();
		int a = 0;
		for(int i = 0;i<x;i++){
			g.drawImage(fdc, 50, (600-(a*10)-47)/2, 57, 85, window);
			a++;
		}
		
		x =  Player.getPlayers()[2].getDeck().size();
		a = 0;
		for(int i = 0;i<x;i++){
			g.drawImage(fdc, ((800-(10*x)-47)/2+(a*10)), 50, 57, 85, window);
			a++;
		}
		
		x =  Player.getPlayers()[3].getDeck().size();
		a = 0;
		for(int i = 0;i<x;i++){
			g.drawImage(fdc, 700, (600-(a*10)-47)/2, 57, 85, window);
			a++;
		}
		
	}
	private static void paintBoardCards() {
		
		g.drawImage(fdc, 50, 50, 57, 85, window);
		g.drawImage(Game.board.getFUC().getImg(), (800-57)/2, (600-85)/2, 57, 85, window);
		
	}
	private static void paintPlayerCards() {
		int x =  Player.getPlayers()[0].getDeck().size();
		int a = 0;
		for(int i = 0; i<Player.getPlayers()[0].getDeck().size();i++){
			Card c = Player.getPlayers()[0].getDeck().get(i);
			if(i!=Game.getCurrentCard())
			g.drawImage(c.getImg(), ((800-(30*x)-27)/2+(a*30)), 500, 57, 85, window);
			else
				g.drawImage(c.getImg(), ((800-(30*x)-27)/2+(a*30)), 490, 57, 85, window);
				
			a++;
		}
	}
	private static void fillBoard() {
		Enums.Color color = Game.board.getColor();
		java.awt.Color c = null;
		switch(color){
		case RED: c = new java.awt.Color(0.4f,0f,0f); break;
		case YELLOW: c = new java.awt.Color(0.4f,0.4f,0f); break;
		case BLUE: c = new java.awt.Color(0f,0f,0.4f); break;
		case GREEN: c = new java.awt.Color(0f,0.4f,0f); break;
		}
		g.setColor(c);
		g.fillRect(0, 0, 800, 600);
		
	}
	public static void paintMenu(){
		
		for( end = 15;end>0;end--){
			
			fillBoard();
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("TimesRoman", Font.BOLD,   30));
			g.drawString("Вы "+(Game.checkWhoWin()==0?"выиграли!":"проиграли!"),300, 180);
			
			g.drawString(""+end, 360, 290);
			
			
			update();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.exit(0);
		
	}
	private static void update() {
		window.getGraphics().drawImage(buffer, 0, 0, window);
	}
	public static void stopWaiting() {
		waiting  = false;
		
	}
	

}
