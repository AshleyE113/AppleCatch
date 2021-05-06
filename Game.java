import processing.core.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name
    	
        PApplet.main("Game");
    }


    Timer timer; // For Game Level
    Timer waitTime; // For Apples
    Player player;
    PImage playerImages[];
	//public static PImage player;
    FallingObject[] apples = new FallingObject[100];
    int activeApples = 0; // Apples That Have Been Created
    public static int minApples = 0; // Minimum Apples Required
    public static PImage Apple;
    public static PImage bg_game;
    float height = 200;
    float width = 225;
    float posX = 250;
    float posY = 225;
    int currentFrame = 0;
	int player_img = 3;
	//int i = 0;
	boolean inMotion = false;

    public void settings() {
        size(500, 500);
    }


    public void setup() {
        Apple = loadImage("apple.png");
        bg_game = loadImage("bg_game.png");

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
    	
    	 if (inMotion)
 			image(playerImages[currentFrame],0, 200);
 		else
 			image(playerImages[1],0, 200);
         
 		if (key == CODED) {
 			if (keyCode == LEFT) {
 				inMotion = true;
				System.out.println("Left");
				posX = posX - 5;
			} 
 			else if (keyCode == RIGHT) {
 				inMotion = true;
 				posX = posX + 5;
 				System.out.println("Right");					
 			}
 			else {
 				image(playerImages[1], posX, posY, width, height);
 			}
 				
		}  
    }
    
    

    public void draw() {
        background(bg_game);

        fill(50, 10, 10, 150);
        text("Level: ", 10, 20);
        text("Score: " , 10, 30);
        text("Apples Required: ", 10, 40);
        text("Lives: ", 10, 50);

        while (inMotion) {
        	for (int i = 0; i < 3; i++) {
        	image(playerImages[i], posX, posY, width, height);
        	if (!(i < 2)) {
        		break;
        	}
        }

        }
        while (!inMotion) {
        	image(playerImages[1], posX, posY, width, height);
        }

        //player.drawPlayer();       

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
            }
        }
    }
}