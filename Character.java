import processing.core.*;

class Character extends PApplet {
	//constants
	float height;
	float width;
	float posX;
	float posY;
	
	Character(){
		height = 75;
		width = 100;
		posX = 250;
		posY = 0;
	}
	
	public void drawPlayer() {
		//currently put in a brown rectangle as player but could change with an image
		// starts at the bottom and right in the middle of horizontal width
		stroke(204,132,0);
		fill(204,102,0);
		rect(posX, posY, width, height);
	}
	
	public void Movement() {
		// if player presses left key, move by 50px, presses right key move by 50px, starts right at middle 
		if (keyPressed) {
			if (key == CODED) {
				if (keyCode == LEFT) {
					posX--;
				} 
				else if (keyCode == RIGHT) {
					posX++;
				}
			}
		}
	}

	public boolean Intersect(FallingObject fallingObject) {
		// Finds The Distance Between The Player And Apple
		float distance = dist(posX, posY, fallingObject.xPosition, fallingObject.yPosition);

		if (distance < height/2 + fallingObject.Radius) {
			// Player And Apple Intersected
			return true;
		}
		else {
			// Player And Apple Did Not intersect
			return false;
		}
	}
	
}

	
