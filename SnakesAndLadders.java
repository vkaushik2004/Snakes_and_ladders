package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SnakesAndLadders extends Application {
        
        int board[][] = new int[10][10];                                // EEMAN AND VEDANT: you guys can possibly use these arrays for the snakes and ladders
        int snakesAndLaddersPos[][] = new int[6][3];
        
        Board gameField = new Board();
        
        @Override
        public void start(Stage primaryStage) throws Exception{
                
                Scene scene = new Scene(gameField.board());
                
                primaryStage.setTitle("Snakes and Ladders"); // sets title at top
                primaryStage.setScene(scene);                                 // creates scene
                primaryStage.show();                                                 // shows scene
        }
        
        public static void main(String[] args) {
                launch(args);
        }
}