import processing.core.*;

import java.util.Arrays;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name
        PApplet.main("Game");
    }


    Timer timer;
    FallingObject[] fallingObjects = new FallingObject[100];
    int totalObjects = 0;
    public static PImage Apple;
    public static PImage bg_game;

    public void settings() {
        size(580, 400);
    }


    public void setup() {
        Apple = loadImage("apple.png");
        bg_game = loadImage("bg_game.png");

        for (int i = 0 ; i< fallingObjects.length;i++){
            fallingObjects[i] = new FallingObject();
        }

        timer = new Timer(300);
        timer.start();
        smooth();
    }


    public void draw() {
        background(bg_game);
        if (timer.isFinished()) {
            fallingObjects[totalObjects] = new FallingObject();
            totalObjects++;
            if (totalObjects >= fallingObjects.length) {
                totalObjects= 0;
            }
            timer.start();
        }
        for (int i = 0 ; i< fallingObjects.length;i++){

            fallingObjects[i].display();
            fallingObjects[i].moveDown();
        }

    }

}