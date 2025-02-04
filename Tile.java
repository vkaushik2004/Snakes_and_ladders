package application;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {
        
        public Tile(int SIZE) {
                setWidth(SIZE);
                setHeight(SIZE);
                setFill(Color.WHITE);        // sets background as white
                setStroke(Color.BLACK);        // sets border of each tile as black (though no longer visible because it's behind the image)
        }


}