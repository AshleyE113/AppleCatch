import processing.core.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name

        PApplet.main("Game");
    }


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
    public static PImage gameLoss;
    public static PImage playerImg;
    float height = 200;
    float width = 225;
    float posX = 250;
    float posY = 225;
    float yBasket = 320;
    float xLeftBasket = 315;
    float xRightBasket = 423;
    int currentFrame = 0;
    int player_img = 3;
    boolean inMotion = false;

    public void settings() {
        size(500, 500);
    }


    public void setup() {
        Apple = loadImage("apple.png");
        bg_game = loadImage("bg_game.png");
        gameLoss = loadImage("AC_Loss.png");
        playerImg = loadImage("player.png");

        gameStatus = new Timer(3000);
        waitTime = new Timer(3000);// 3 Seconds
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
                System.out.println("Left");
                posX = posX - 5;
                xLeftBasket -= 5;
                xRightBasket -= 5;
            }
            else if (keyCode == RIGHT) {
                inMotion = true;
                posX = posX + 5;
                xLeftBasket += 5;
                xRightBasket += 5;
                System.out.println("Right");
            }
            else {
                inMotion = false;
            }
        }
    }



    public void draw() {
        background(bg_game);

        switch (Level) {
            case 1:
                minApples = 12;
                timer = new Timer(40000); // 40 Seconds
                FallingObject.Velocity = 2;
                break;
            case 2:
                minApples = 13;
                timer = new Timer(35000); // 35 Seconds
                FallingObject.Velocity = 1.2;
                break;
            case 3:
                minApples = 15;
                timer = new Timer(30000); // 30 Seconds
                FallingObject.Velocity = 1.4;
                break;
            case 4:
                minApples = 16;
                timer = new Timer(25000); // 25 Seconds
                FallingObject.Velocity = 1.6;
                break;
            case 5:
                minApples = 18;
                timer = new Timer(20000); // 20 Seconds
                FallingObject.Velocity = 2;
                break;
        }

        fill(0, 0, 0);
        textSize(12);
        text("Level: " +  + Level, 20, 20);
        text("Score: " + FallingObject.objectsCaught, 20, 35);
        text("Apples Required: " + minApples, 20, 50);
        text("Lives: " + player.lives, 20, 65);

        if (inMotion) {
            currentFrame = (int) Math.abs((posX)%3); //Makes ani happen
            image(playerImages[currentFrame], posX, posY, width, height);

        }
        //line(xLeftBasket, yBasket, xRightBasket, yBasket);

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
        for (int i = 0 ; i< activeApples;i++) { //int i = 0 ; i< apples.length;i++
            apples[i].display();
            apples[i].moveDown();
            if (player.Intersect(apples[i], xLeftBasket, xRightBasket, yBasket)){
                apples[i].caught();
                FallingObject.objectsCaught++;
            }
            else if (apples[i].reachedBottom()){
                player.lives--;
            }

        }

    }

}

