package application;


import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Movement {
        
        public void movementAnimation(int xPos, int yPos, Circle player) {
                TranslateTransition animate = new TranslateTransition(Duration.millis(100), player);        // sets how fast the animation runs
                animate.setToX(xPos);        // moves object to x-position
                animate.setToY(yPos);        // moves object to y-position 
                animate.play();                        // plays animation
        }
}