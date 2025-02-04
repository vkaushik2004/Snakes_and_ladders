package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Board {
        
        final int BOARD_WIDTH = 800;
        final int BOARD_HEIGHT = 800;
        final int TILE_WIDTH = 10;
        final int TILE_HEIGHT = 10;
        final int SIZE = 80;
        
        static int p1X = 40;        // x-position player 1
        static int p1Y = 760;        // y-position player 1
        static int p2X = 40;        // x-position player 2
        static int p2Y = 760;        // y-position player 2
        int rollValue;
        int rowCount1 = 1;
        int rowCount2 = 1;
        
        boolean gameStart = false;
        boolean player1Turn = true;
        boolean player2Turn = false;
        boolean wonGame = false;
        
        private Group group = new Group();
        Movement motion = new Movement();
        
        Circle player1;
        Circle player2; 
        Circle p1Indicator;
        Circle p2Indicator;
        
        Label rollValueP1;
        Label rollValueP2;
        Label diceValue;
        Label p1;
        Label p2;
        Label winText;
        
        public Parent board() {
                
                Pane root = new Pane();                 // initialize the pane
                root.setPrefSize(BOARD_WIDTH + 80, BOARD_HEIGHT + 40);  // sets dimensions of window. +40 and +80 adds space for buttons on bottom and side respectively.
                root.getChildren().addAll(group);                                                 // adds all group features to the game
                
                // this loop creates the 10 x 10 tile grid
                for(int i = 0; i < TILE_HEIGHT; i++) {
                        for(int j = 0; j < TILE_WIDTH; j++) {
                                Tile tile = new Tile(SIZE);
                                tile.setTranslateX(j * SIZE);
                                tile.setTranslateY(i * SIZE);
                                
                                group.getChildren().add(tile);
                        }
                }
                
                // creates player pieces
                player1 = new Circle(20);                // sets radius of circle
                player1.setFill(Color.BLUE);        // sets color of circle
                player1.setTranslateX(40);                // sets initial x-position
                player1.setTranslateY(200);                // sets initial y-position
                
                player2 = new Circle(15);
                player2.setFill(Color.RED);
                player2.setTranslateX(p2X);
                player2.setTranslateY(p2Y);
                
                // indicators for which player's turn it is
                p1Indicator = new Circle(15);
                p1Indicator.setFill(Color.BLUE);
                p1Indicator.setTranslateX(840);
                p1Indicator.setTranslateY(270);
                
                p2Indicator        = new Circle(15);
                p2Indicator.setFill(Color.RED);
                p2Indicator.setTranslateX(840);
                p2Indicator.setTranslateY(540);
                        
                //******START BUTTON********
                Button startButton = new Button("Start Game");
                startButton.setTranslateX(40);                        // x-position of button
                startButton.setTranslateY(810);                        // y-position of button
                startButton.setOnAction(new EventHandler<ActionEvent>() {        // sets function of button
                        @Override
                        public void handle(ActionEvent event) {
                                startButton.setText("Game Started");
                                group.getChildren().add(p1Indicator);        // shows player 1 goes first
                                gameStart = true;
                                
                                // sets the initial positions of the players for the game
                                player1.setTranslateX(p1X);
                                player1.setTranslateY(p1Y);
                                player2.setTranslateX(p2X);
                                player2.setTranslateY(p2Y);
                                
                        }
                });
                
                //******ROLL BUTTON********
                Button rollButton = new Button("Roll");
                rollButton.setTranslateX(820);
                rollButton.setTranslateY(390);
                rollButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                // loop that only runs if the game has started and nobody's won
                                if(gameStart == true && wonGame == false) {
                                        // loop for player 1's turn
                                        if(player1Turn == true) {
                                                group.getChildren().add(p2Indicator);                // Once player 1 goes, it's player 2's turn, so the indicators
                                                group.getChildren().remove(p1Indicator);        // show that player 2 is next once player 1 hits roll.
                                                
                                                roll();                                // calls roll method to generate random number
                                                rollValueP2.setText(String.valueOf(0));                        // shows 0 for player 2
                                                rollValueP1.setText(String.valueOf(rollValue));                // shows the number generated for player 1
                                                
                                                p1Move();                // calls p1Move method to change player 1 coordinates on the board
                                                motion.movementAnimation(p1X, p1Y, player1);        // calls movementAnimation method to move the circle on the board
                                                
                                                player1Turn = false;        // sets player 1 as false as it's now player 2's turn        
                                                player2Turn = true;
                                        }
                                        // loop for player 2's turn
                                        else if(player2Turn == true) {
                                                group.getChildren().add(p1Indicator);
                                                group.getChildren().remove(p2Indicator);
                                                
                                                roll();
                                                rollValueP1.setText(String.valueOf(0));
                                                rollValueP2.setText(String.valueOf(rollValue));
                                                
                                                p2Move();
                                                motion.movementAnimation(p2X, p2Y, player2);
                                                player1Turn = true;
                                                player2Turn = false;
                                        }
                                }
                        }
                });
                
                // LABELS-----------------------------------
                rollValueP1 = new Label("0");
                rollValueP1.setTranslateX(835);                // x-position of the text
                rollValueP1.setTranslateY(200);                // y-position of the text
                        
                rollValueP2 = new Label("0");
                rollValueP2.setTranslateX(835);
                rollValueP2.setTranslateY(590);
                
                p1 = new Label("P1");
                p1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));        // changes font style of text
                p1.setTranslateX(820);
                p1.setTranslateY(300);
                
                p2 = new Label("P2");
                p2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                p2.setTranslateX(820);
                p2.setTranslateY(470);
                
                winText = new Label("Player 1 won!");
                winText.setTranslateX(405);
                winText.setTranslateY(810);
                
                //Image code
                Image image = new Image("snakes.JPG");
                ImageView jpg = new ImageView();
                jpg.setImage(image);
                jpg.setFitHeight(BOARD_HEIGHT);
                jpg.setFitWidth(BOARD_WIDTH);
                
                group.getChildren().addAll(jpg,player1, player2, rollButton, startButton, rollValueP1, rollValueP2, p1, p2);        // adds all features to the board
                
                return root;        // returns all features and their functions
        }
        
        /**
         * Generates random number. Simulates a dice roll
         */
        public void roll() {
                rollValue = (int)(Math.random() * 6 + 1);
        }
        
        private void p1Move(){
                // loop that increases or decreases the x and y positions based on the roll value and location of player circle
                for(int i = 0; i < rollValue; i++) {
                        if(rowCount1 % 2 == 1) {        // checks if it's in an odd row. Moves player to right
                                p1X += 80;
                        }
                        if(rowCount1 % 2 == 0) {        // checks if it's in an even row. Moves player to left
                                p1X -= 80;
                        }
                        if(p1X > 760) {        // if at far right of board, move up and increase row count
                                p1Y -= 80;
                                p1X -= 80;
                                rowCount1++;
                        }
                        if(p1X == 40 && p1Y == 40) {        // if at tile 100
                                winText.setText("Player 1 Won!");
                                group.getChildren().add(winText);
                                wonGame = true;
                                break;                                // stops game
                        }
                        else if(p1X < 40) {        // if at far left of board, move up and increase row count
                                p1Y -= 80;
                                p1X += 80;
                                rowCount1++;
                        }
                }
                p1Snakes();
                p1Ladders();
        }
        
        private void p2Move(){
                for(int i = 0; i < rollValue; i++) {
                        if(rowCount2 % 2 == 1) {        // checks odd row
                                p2X += 80;
                        }
                        if(rowCount2 % 2 == 0) {        // checks even row
                                p2X -= 80;
                        }
                        if(p2X > 760) {        // if at far right 
                                p2Y -= 80;
                                p2X -= 80;
                                rowCount2++;
                        }
                        if(p2X == 40 && p2Y == 40) {        // if at tile 100
                                winText.setText("Player 2 Won!");
                                group.getChildren().add(winText);
                                wonGame = true;
                                break;
                        }
                        else if(p2X < 40) {        // if at far left 
                                p2Y -= 80;
                                p2X += 80;
                                rowCount2++;
                        }
                }
                p2Snakes();
                p2Ladders();
        }
        
        /**
         * Snake positions for player 1
         */
        private void p1Snakes() {
                
                if (p1Y == 40 && p1X == 200) {
                        p1Y = 360;
                        p1X = 440;
                }


                if (p1Y == 40 && p1X == 680) {
                        p1Y = 200;
                        p1X = 440;
                }


                if (p1Y == 120 && p1X == 120) {
                        p1Y = 440;
                        p1X = 120;
                }


                if (p1Y == 200 && p1X == 600) {
                        p1Y = 360;
                        p1X = 760;
                }


                if (p1Y == 360 && p1X == 360) {
                        p1Y = 680;
                        p1X = 120;
                }
                if (p1Y == 440 && p1X == 520) {
                        p1Y = 680;
                        p1X = 440;
                }
                if (p1Y == 600 && p1X == 760) {
                        p1Y = 760;
                        p1X = 520;
                }
        }


        /**
         * Snake positions for player 2
         */
        private void p2Snakes() {
                if (p2Y == 40 && p2X == 200) {
                        p2Y = 360;
                        p2X = 440;
                }


                if (p2Y == 40 && p2X == 680) {
                        p2Y = 200;
                        p2X = 440;
                }


                if (p2Y == 120 && p2X == 120) {
                        p2Y = 440;
                        p2X = 120;
                }


                if (p2Y == 200 && p2X == 600) {
                        p2Y = 360;
                        p2X = 760;
                }


                if (p2Y == 360 && p2X == 360) {
                        p2Y = 680;
                        p2X = 120;
                }


                if (p2Y == 440 && p2X == 520) {
                        p2Y = 680;
                        p2X = 440;
                }


                if (p2Y == 600 && p2X == 760) {
                        p2Y = 760;
                        p2X = 520;
                }
        }


        /**
         * Ladder positions for player 1
         */
        private void p1Ladders() {
                if (p1Y == 760 && p1X == 280) {
                        p1Y = 600;
                        p1X = 360;
                }


                if (p1Y == 600 && p1X == 40) {
                        p1Y = 520;
                        p1X = 120;
                }


                if (p1Y == 600 && p1X == 680) {
                        p1Y = 200;
                        p1X = 520;
                }


                if (p1Y == 440 && p1X == 200) {
                        p1Y = 200;
                        p1X = 360;
                }


                if (p1Y == 280 && p1X == 200) {
                        p1Y = 200;
                        p1X = 40;
                }


                if (p1Y == 200 && p1X == 760) {
                        p1Y = 120;
                        p1X = 680;
                }
                
                
        }


        /**
         * Ladder positions for player 2
         */
        private void p2Ladders() {
                if (p2Y == 760 && p2X == 280) {
                        p2Y = 600;
                        p2X = 360;
                }


                if (p2Y == 600 && p2X == 40) {
                        p2Y = 520;
                        p2X = 120;
                }


                if (p2Y == 600 && p2X == 680) {
                        p2Y = 200;
                        p2X = 520;
                }


                if (p2Y == 440 && p2X == 200) {
                        p2Y = 200;
                        p2X = 360;
                }
                if (p2Y == 280 && p2X == 200) {
                        p2Y = 200;
                        p2X = 40;
                }


                if (p2Y == 200 && p2X == 760) {
                        p2Y = 120;
                        p2X = 680;
                }
        }
}