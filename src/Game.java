import processing.core.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name
    	
        PApplet.main("Game");
    }

    Pause pauseButton;
    Timer timer;
    Timer waitTime;
    Player player;
    PImage playerImages[];
	//public static PImage player;
    FallingObject[] apples = new FallingObject[100];
    int activeApples = 0; // Apples That Have Been Created
    public static int minApples = 0; // Minimum Apples Required
    int totalObjects = 0;
    public static PImage Apple;
    public static PImage bg_game;
    public static PImage TitleScreen;
    public static PImage LossScreen;
    public static PImage WinScreen;
    float height = 200;
    float width = 225;
    float posX = 250;
    float posY = 225;
    int currentFrame = 0;
	int player_img = 3;
	int toWin = 20;
	int playerLives = 3;
	boolean inMotion = false;
	boolean paused = false;

    public void settings() {
        size(500, 500);
    }


    public void setup() {
        Apple = loadImage("apple.png");
        bg_game = loadImage("bg_game.png");
        TitleScreen = loadImage("ACTS.png");
        LossScreen = loadImage("AC_Loss.png");
        WinScreen = loadImage("AC_Win.png");

        for (int i = 0 ; i< apples.length;i++){
            apples[i] = new FallingObject(this);
        }

        waitTime = new Timer(1000);// 1 Second
        waitTime.start();
        timer = new Timer(40000); // 40 Seconds
        timer.start();
        smooth();
        player = new Player();
    	playerImages = new PImage[player_img];
		for(int i = 0; i < player_img; i++) {
			playerImages[i] = loadImage("spr_player"+i+".png");
		}
        
    }
    
    public void keyPressed() {
 		if (key == CODED) {
 			if (keyCode == LEFT) {
 				inMotion = true;
				//System.out.println("Left");
				posX = posX - 5;
			} 
 			else if (keyCode == RIGHT) {
 				inMotion = true;
 				posX = posX + 5;
 				//System.out.println("Right");					
 			}
 			else {
 				inMotion = false;
 			}
 			
 //			/*
 			if (key == CODED) {
 				if (keyCode == SHIFT){
				paused = !paused;
				if (paused) {
					noLoop();
				} else {
					loop();
				}
 			}
				//*/
		}  
    }
}
    
    

    public void draw() {
        background(bg_game);
        fill(50, 10, 10, 150);
        text("Level: ", 10, 20);
        text("Score: " , 10, 30);
        text(FallingObject.objectsCaught, 50, 30);
        text("Apples Required: 20", 10, 40);
        text((timer.passedTime/100), 10, 50);
        text("Lives: ", 10, 60);
        
		if (inMotion) {
		 currentFrame = (int) Math.abs((posX)%3); //Makes ani happen
		 image(playerImages[currentFrame], posX, posY, width, height);		
		}  else {
			image(playerImages[1], posX, posY, width, height);
		}
       

        if (waitTime.isFinished()) {
            // Populate
            apples[activeApples] = new FallingObject(this);
            // Add to the Apples Ready
            activeApples++;
            // Start Over
            if (activeApples >= apples.length) {
                activeApples = 0;
            }
            // Wait Time For Next Apple To Be Created
            waitTime.start();
        }

        for (int i = 0; i < activeApples; i++) { //int i = 0 ; i< apples.length;i++
            apples[i].display();
            apples[i].moveDown();
            if (player.Intersect(apples[i])) {
                apples[i].caught();
                FallingObject.objectsCaught++;
                //fill(50, 10, 10, 150);
                System.out.println(FallingObject.objectsCaught);
            }
        }
        
        if (FallingObject.objectsCaught >= 20 && (!timer.isFinished())){
        	background(WinScreen);
        } else if (FallingObject.objectsCaught <= 20 && timer.isFinished()) {
        	background(LossScreen);
        }
    }

}