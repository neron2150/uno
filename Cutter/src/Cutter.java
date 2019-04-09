import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cutter {

	public static void main(String[] args) {
		
		for(int x = 0;x < 13;x++){
			cut("red_"+x,x,0);
			cut("yellow_"+x,x,1);
			cut("green_"+x,x,2);
			cut("blue_"+x,x,3);
		}
		
			cut("change_color",13,1);
			
				try{
				BufferedImage sub = ImageIO.read(new File("src/z.png")).getSubimage(800-57,686-85,57,85);
				ImageIO.write(sub, "png", new File("src/cards/add_4.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	static void cut(String s,int x,int y){
		try {
			if(y==3){y = 686/8*y;
			y+=2;}
			else
				y = 686/8*y;
			x = 800/14*x;
			
			
			BufferedImage sub = ImageIO.read(new File("src/z.png")).getSubimage(x+1,y+1,57,85);
			ImageIO.write(sub, "png", new File("src/cards/"+s+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
