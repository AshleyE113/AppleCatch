import processing.core.*;

class FallingObject extends PApplet {
    protected float xPosition;
    protected float yPosition;
    protected float Radius;
    protected static double Velocity;
    static int objectsCaught = 0;

    PApplet canvas;

    public FallingObject ( PApplet canvas ) {
        this.canvas = canvas;
        Radius = 48;
        xPosition = canvas.random(canvas.width);
        yPosition = -Radius *4;

    }

    void display() {
        this.canvas.image(Game.Apple, xPosition, yPosition, Radius, Radius);
    }

    void moveDown() {
        yPosition += Velocity;
    }

    public boolean reachedBottom() {
        if (yPosition > height + Radius) {
            Velocity = 0;
            yPosition = -1000;
            return true;
        } else {
            return false;
        }
    }

    public void caught() {
    	//System.out.println("We caught one!");
        Velocity = 0;
        yPosition = -1000;
    }
}
