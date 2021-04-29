import processing.core.*;

class FallingObjects extends PApplet {
    // Position
    float xPosition, yPosition;
    // Velocity
    float Velocity;
    // Radius
    float Radius;


    FallingObjects() {
        Radius = 8;
        xPosition = random(40, width-40);
        yPosition = -Radius*4;
        Velocity = random(1, 5);
        //image(Game.Apple, xPosition, yPosition, Radius, Radius);
    }

    public void move() {
        yPosition += Velocity;
    }

    boolean reachedBottom() {
        if (yPosition > height + Radius*4) {
            Velocity = 0;
            yPosition = -1000;
            return true;
        } else {
            return false;
        }
    }

    public void display() {
       image(Game.Apple, xPosition, yPosition, Radius, Radius);
    }


    public void caught() {
        Velocity = 0;
        yPosition = -1000;
    }
}
