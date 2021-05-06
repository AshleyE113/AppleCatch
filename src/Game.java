import processing.core.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name
    	
        PApplet.main("Game");
    }


    Timer timer;
    Player player;
    PImage playerImages[];
	//public static PImage player;
    FallingObject[] apples = new FallingObject[100];
    int totalObjects = 0;
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

        for (int i = 0 ; i< apples.length;i++){
            apples[i] = new FallingObject(this);
        }

        timer = new Timer(300);
        player = new Player();
    	playerImages = new PImage[player_img];
		for(int i = 0; i < player_img; i++) {
			playerImages[i] = loadImage("spr_player"+i+".png");
		}
        timer.start();
        smooth();
    }
    
    public void keyPressed() {
    	
    	// if (inMotion)
 			//image(playerImages[currentFrame],0, 200);
 		//else
 			//image(playerImages[1],0, 200);
         
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
       
        if (timer.isFinished()) {
            apples[totalObjects] = new FallingObject(this);
            totalObjects++;
            if (totalObjects >= apples.length) {
                totalObjects= 0;
            }
            timer.start();
        }
        for (FallingObject apple : apples) { //int i = 0 ; i< apples.length;i++
            apple.display();
            apple.moveDown();
            if (player.Intersect(apple)) {
                apple.caught();
                FallingObject.objectsCaught++;
            }
        }

    }

}