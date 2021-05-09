import processing.core.*;

class Player extends PApplet {
	//constants
//	float height;
//	float width;
//	float posX;
//	float posY;
	int lives;

	Player(){
//		height = 75;
//		width = 100;
//		posX = 250;
//		posY = 0;
		lives = 3;
	}
	/*
	public void setup() {
		playerImages = new PImage[player_img];
		for(int i = 0; i < player_img; i++) {
			playerImages[i] = loadImage("spr_player"+i+".png");
		}

	}
	*/
	public void drawPlayer() {
		//currently put in a brown rectangle as player but could change with an image
		// starts at the bottom and right in the middle of horizontal width
		/*
		if (inMotion)
			image(playerImages[currentFrame],0, 200);
		else
			image(playerImages[1],0, 200);
			*/
		//currentFrame++;
		//System.out.print(currentFrame);

		//if (currentFrame == loopFrames-1) {
		//	currentFrame = 0;
		//}
	}

	public void Movement() {
		// if player presses left key, move by 50px, presses right key move by 50px, starts right at middle
		//currentFrame = (currentFrame + 0.5)%3;
		/*
		if (keyPressed) {
			if (key == CODED) {
				if (keyCode == LEFT) {
					inMotion = true;
					System.out.print("Left");
					posX--;
				}
				else if (keyCode == RIGHT) {
					inMotion = true;
					posX++;
					System.out.print("Right");
				}
			}
		}
		*/
	}

	public boolean Intersect(FallingObject fallingObject, float xLPosition, float xRPosition, float yPos) {
		if((fallingObject.yPosition == yPos) && ( fallingObject.xPosition >= xLPosition && fallingObject.xPosition <= xRPosition) ){
			return true;
		}
		else{
			return false;
		}
	}

	//public static void main(String[] args) {
	//	PApplet.main("Player");
	//}
}



