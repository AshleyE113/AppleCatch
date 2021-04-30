import processing.core.*;

class FallingObject extends PApplet {
    float Radius;
    float xPosition;
    float yPosition;
    float Velocity;


    FallingObject() {
        Radius = 8;
        xPosition = random(width);
        yPosition = -Radius*4;
        Velocity = random(1, 5);
    }

    void moveDown() {
        yPosition += Velocity;
    }

    public boolean reachedBottom() {
        if (yPosition > height + Radius*4) {
            Velocity = 0;
            yPosition = -1000;
            return true;
        } else {
            return false;
        }
    }

    void display() {
       //image(Game.Apple, xPosition, yPosition, Radius, Radius);
        noStroke();
        fill(230,167,45);
        ellipse(xPosition, yPosition, Radius*2, Radius*2);
    }


    public void caught() {
        Velocity = 0;
        yPosition = -1000;
    }
}
