import processing.core.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name
        PApplet.main("Game");
    }

    Timer timer;
    Character player;
    FallingObject[] apples = new FallingObject[100];
    int totalObjects = 0;
    public static PImage Apple;
    public static PImage bg_game;

    public void settings() {
        size(580, 400);
    }


    public void setup() {
        Apple = loadImage("apple.png");
        bg_game = loadImage("bg_game.png");

        for (int i = 0 ; i< apples.length;i++){
            apples[i] = new FallingObject(this);
        }

        timer = new Timer(300);
        player = new Character();
        timer.start();
        smooth();
    }

    public void draw() {
        background(225);
        //background(bg_game);
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