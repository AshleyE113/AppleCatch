import processing.core.*;
class Timer extends PApplet {
    int savedTime;
    int totalTime;
    int passedTime;

    Timer(int tempTotalTime) {
        totalTime = tempTotalTime;
    }

    public void start() {
        savedTime = millis();
    }

    boolean isFinished() {
        passedTime = millis() - savedTime;
        if (passedTime > totalTime) {
            return true;
        } else {
            return false;
        }
    }
}
