import processing.core.*;

//would need a description text in beginning of game to notify player to press ESC to pause and do the same to resume

class Pause extends PApplet{	
	boolean paused = false;
	
	public void pauseandResume() {
		if (keyPressed) {
			if (key == ESC) {
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

