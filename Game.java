import processing.core.*;

public class Game extends PApplet {

    public static void main(String[] args) {
        // The argument passed to main must match the class name
        PApplet.main("Game");
    }


    Timer timer;
    FallingObjects[] Objects;
    int totalDrops = 0;
    public static PImage Apple;

    public void settings() {
        size(580, 400);
    }

    public void setup() {
        Apple = loadImage("../AppleCatch/apple.png");
        Objects = new FallingObjects[1000];
        timer = new Timer(300);
        timer.start();
        smooth();
    }

    public void draw() {
        background(240);
        if (timer.isFinished()) {
            Objects[totalDrops] = new FallingObjects();
            totalDrops++;
            if (totalDrops >= Objects.length) {
                totalDrops = 0;
            }
            timer.start();
        }

        for (int i = 0; i < totalDrops; i++) {
            Objects[i].display();
            Objects[i].move();

        }

    }

}