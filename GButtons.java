import processing.core.*;
public class GButtons extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("GButtons");
	}
	
	public static PImage Apple;
	public static PImage StartB;
	public static PImage RestartB;
	public static PImage TitleScreen;
	public static PImage WinScreen;
	public static PImage LossScreen;
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		Apple = loadImage("apple.png");
		StartB = loadImage("StartB.png");
		RestartB = loadImage("RestartB.png");
		TitleScreen = loadImage("ACTS.png");
		WinScreen = loadImage("AC_Win.png");
		LossScreen = loadImage("AC_Loss.png");
		
	}
	
	public void draw() {
		//background(TitleScreen);
		//image(StartB, 10, 420);
		//background(WinScreen);
		//image(RestartB, 180, 400);
		//background(LossScreen);
		//image(RestartB,180, 355);
	}

}