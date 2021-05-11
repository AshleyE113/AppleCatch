import processing.core.*;
import ddf.minim.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name

        PApplet.main("Game");
    }
    
    Minim music;
    Pause pauseButton;
    Timer timer; // For Game Level
    Timer waitTime; // For Apples
    Timer gameStatus; // For status text
    Player player;
    PImage playerImages[];
    //public static PImage player;
    FallingObject[] apples = new FallingObject[100];
    int activeApples = 0; // Apples That Have Been Created
    int Level = 1; // To Keep Track Of What Level The Player Is On
    public static int minApples = 0; // Minimum Apples Required
    public static PImage Apple;
    public static PImage bg_game;
    public static PImage TitleScreen;
    public static PImage LossScreen;
    public static PImage WinScreen;
	public static PImage StartB;
	public static PImage RestartB;
	//____________________________________________________
    float height = 200;
    float width = 225;
    float posX = 250;
    float posY = 225;
    float yBasket = 320;
    float xLeftBasket = 315;
    float xRightBasket = 423;
    int currentFrame = 0;
    int player_img = 3;
    //_____________________________________________________
    boolean inMotion = false;
	boolean paused = false;
	boolean playGame = false;
	boolean mouseInStart = false;
	boolean mouseInRestart = false;
	boolean ToStart = false;
	boolean Win = false;
	boolean Loss = false;
	boolean caught = false;
	//______________________________________________________
	AudioPlayer TS_music;
	AudioPlayer Game_music;
	AudioPlayer WinMusic;
	AudioPlayer LossMusic;
	AudioPlayer CaughtSFX;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
    	frameRate(60);
        Apple = loadImage("apple.png");
        bg_game = loadImage("bg_game.png");
        TitleScreen = loadImage("ACTS.png");
        LossScreen = loadImage("AC_Loss.png");
        WinScreen = loadImage("AC_Win.png");
        StartB = loadImage("StartB.png");
		RestartB = loadImage("RestartB.png");
		
		//Timers
        gameStatus = new Timer(3000);
        waitTime = new Timer(3000);// 3 Seconds
        waitTime.start();
        timer = new Timer(40000); // 40 Seconds
        timer.start();
        smooth();
        
        //Initialiing Player info
        player = new Player();
        playerImages = new PImage[player_img];
        for(int i = 0; i < player_img; i++) {
            playerImages[i] = loadImage("spr_player"+i+".png");
        }
        
        music = new Minim(this);
        TS_music = music.loadFile("StartMusic.mp3");
        Game_music = music.loadFile("GameMusic.mp3");
        WinMusic = music.loadFile("Winner.mp3");
        LossMusic = music.loadFile("LossMusic.mp3");
        CaughtSFX = music.loadFile("CaughtSFX.mp3");
    }

    public void keyPressed() {

        if (key == CODED) {
        	
            if (keyCode == LEFT) {
                inMotion = true;
                //System.out.println("Left");
                posX = posX - 5;
                xLeftBasket -= 5;
                xRightBasket -= 5;
            }
            else if (keyCode == RIGHT) {
                inMotion = true;
                posX = posX + 5;
                xLeftBasket += 5;
                xRightBasket += 5;
                //System.out.println("Right");
            }else {
                inMotion = false;
            }
           
           if (key == CODED) {
            if (keyCode == SHIFT){
				paused = !paused;
				if (paused) {
					noLoop();
				} else {
					loop();
				}
            }
       }
    }
  }
    
    public void StartScreen() {
    	
    	TS_music.play();
    	background(TitleScreen);
    	image(StartB, 10, 420);
    	if (WinMusic.isPlaying()) {
    		WinMusic.pause();
    	}
    	if (LossMusic.isPlaying()) {
        	LossMusic.pause();
    	}
    	if (Game_music.isPlaying()) {
    		Game_music.pause();
    	}
    	
    	
    	//ToStart = false;
    	
    	//System.out.println("Start!");
    	
    	if ((mouseX >= 10 && mouseY >= 419) && (mouseX <= 138 && mouseY <= 482)){
    		text("TEST", 10, 419);
    		text("TEST2", 138, 482);
    		mouseInStart = true;
    	}
    	
    }

    public void LoseScreen() {
    	background(LossScreen);
    	image(RestartB, 180, 355);
    	LossMusic.play();
    	
    	if (Game_music.isPlaying()) {
    		Game_music.pause();
    		TS_music.pause();
    	}
    	
    	if ((mouseX >= 176 && mouseY >= 352) && (mouseX <= 305 && mouseY <= 416)){
    		mouseInRestart = true;
    	}
    	
    }

    public void WinScreen() {
    	background(WinScreen);
    	image(RestartB, 180, 400);
    	WinMusic.play();
    	if (Game_music.isPlaying()) {
    		Game_music.pause();
    		TS_music.pause();
    	}
    	if ((mouseX >= 179 && mouseY >= 398) && (mouseX <= 305 && mouseY <= 460)){
    		mouseInRestart = true;
    	}
    	
    }

    public void mouseClicked() {
    	if (mouseInStart) {
    		playGame = true;
    		System.out.println("In Start Clicked!");
    	}
    	
    	if (mouseInRestart) {
    		ToStart = true;
    		System.out.println("Restart Clicked!");
    	}
    }

    public void draw() {
    	StartScreen();
    	
    	
        if (playGame) {
        	
        	if (TS_music.isPlaying()) {
	    		TS_music.pause();
	    		LossMusic.pause();
	    		WinMusic.pause();
        	}
	        background(bg_game);
	        
	        Game_music.play();
	        
	        if (inMotion) {
	            currentFrame = (int) Math.abs((posX)%3); //Makes ani happen
	            image(playerImages[currentFrame], posX, posY, width, height);
	
	        } else {
	        	image(playerImages[1], posX, posY, width, height);
	        	//caught = false;
	        }
	        
	        /*
			if (posX > 505) {
				posX = 0;
			} else if (posX < 0) {
				posX = 500;
			}*/
	        
	        
	        //ORIGINL VALS: 12, 13, 15, 16, 18
	       //Handles levels
	        switch (Level) {
	            case 1:
	                minApples = 12;
	                timer = new Timer(40000); // 40 Seconds
	                FallingObject.Velocity = 2;
	                break;
	            case 2:
	                minApples = 13;
	                timer = new Timer(35000); // 35 Seconds
	                FallingObject.Velocity = 2;
	                break;
	            case 3:
	                minApples = 15;
	                timer = new Timer(30000); // 30 Seconds
	                FallingObject.Velocity = 2;
	                break;
	            case 4:
	                minApples = 16;
	                timer = new Timer(25000); // 25 Seconds
	                FallingObject.Velocity = 2;
	                break;
	            case 5:
	                minApples = 18;
	                timer = new Timer(20000); // 20 Seconds
	                FallingObject.Velocity = 2;
	                break;
	        }

	        if (FallingObject.objectsCaught == minApples){
	            Level++;
                FallingObject.objectsCaught = 0;
                player.lives = 10;
	            //tell them they move onto next level
	            if (Level>=6){
	            	playGame = false;
	            	Win = true;
	            }
	        }
	        
	        if ((player.lives <= 0) || (timer.isFinished() && (FallingObject.objectsCaught < minApples))) {
	        	playGame = false;
	        	Loss = true;
	        }
	
	        
	        
	        fill(0, 0, 0);
	        textSize(12);
	        text("Level: " +  + Level, 20, 20);
	        text("Score: " + FallingObject.objectsCaught, 20, 35);
	        text("Apples Required: " + minApples, 20, 50);
	        text((timer.passedTime/1000), 20, 56);
	        text("Lives: " + player.lives, 20, 80);
	
	        if (waitTime.isFinished()){
	            // Populate
	            apples[activeApples] = new FallingObject(this);
	            // Add to the Apples Ready
	            activeApples++;
	            // Start Over
	            if (activeApples >= apples.length) {
	                activeApples= 0;
	            }
	            // Wait Time For Next Apple To Be Created
	            waitTime.start();
	            
	        }
	
	        // Only Apples That Have Been created Can Fall
	        // Will Make On Apple Fall At A Time Since The Number Of Active Apples Is Always Changing by 1
	        for (int i = 0 ; i< activeApples;i++) {
	        	println(caught);
	            apples[i].display();
	            apples[i].moveDown();
	            //caught = false;
	            if (player.Intersect(apples[i], xLeftBasket, xRightBasket, yBasket)){
	                apples[i].caught();
	                caught = true;
	  
	                //if (!CaughtSFX.isLooping()) {}
	           
	                FallingObject.objectsCaught++;
	            }
	            else if (apples[i].reachedBottom()){
	                player.lives--;
	            }
	            
	        }
        }
        
        if (Win) {
        	WinScreen();
        	///*
        	if (ToStart) {
        		StartScreen();
            	mouseInRestart = false;
            	mouseInStart = false;
            	
        		//System.out.println("Back to Start!");
        	}
        }
        ///*
        if (caught)
        {
        	CaughtSFX.play();
        	System.out.print("playing");
        	caught = false;
        }//*/
        
        if (Loss) {
        	
        	LoseScreen();
    	///*
    	if (ToStart) {
    		StartScreen();
        	mouseInRestart = false;
        	mouseInStart = false;
        	//*/
    		//System.out.println("Back to Start!");
    	}
       }
	        
	        //TEMPORARY! DON'T WORRY ABOUT IT! TRYING TO FIX AN ISSUE
	        /*
	        if (FallingObject.objectsCaught >= 5 && (!timer.isFinished())){
	        	WinScreen();
	        	
	        	
	        	///*
	        	if (ToStart) {
	        		StartScreen();
	            	mouseInRestart = false;
	            	mouseInStart = false;
	            	
	        		//System.out.println("Back to Start!");
	        	}
	        
	        } else if (FallingObject.objectsCaught <= 5 && timer.isFinished()) {
	        	LoseScreen();
	        
	        		//System.out.println("Back to Start!");
	            	//playGame = false;
	        	///*
	        	if (ToStart) {
	        		StartScreen();
	            	mouseInRestart = false;
	            	mouseInStart = false;
	            	//*/
	        		//System.out.println("Back to Start!");
	        	}
	        	//*/
	        //}
        
        /*
        if (ToStart) {
        	
        	mouseInRestart = false;
        	mouseInStart = false;
        	playGame = false;
        } else if (Loss && ToStart){
        	
        	mouseInRestart = false;
        	mouseInStart = false;
        	playGame = false;
        }*/
      
	    }
    
   
  //}
//}

//}

