package com.example.hoppenhelmgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//define the main Game class that extends from the Application class of JavaFX.
//class Game represents the game scene
public class Game extends Application {

    //attributes from class Game
    //playerRectangle attribute is an object from class Rectangle
    //playerRectangle represents player
    private Rectangle playerRectangle = new Rectangle();
    //pinkBlocks attribute is a List from class Block
    //pinkBlocks represents player lives
    private List<Block> pinkBlocks = new ArrayList<>();
    //index to keep track of pink blocks.
    private int pinkBlockIndex = 0;
    private boolean timerActive = false;

    //the start method is the main entry point for all JavaFX applications
    public void start(Stage primaryStage) {
        Group root = new Group();
        HBox hbox = new HBox(1);
        //coordinates of blocks
        int initialY = 650;

        //create 3 pink blocks and add them to the root group and pinkBlocks list
        for (int i = 0; i < 3; i++) {
            //create pinkBlock object
            Block pinkBlock = new Block(50, 50, Color.HOTPINK);
            //Coordinates of pinkBlock
            pinkBlock.setTranslateX(850 + i * 60);
            //Brightness percentage of pinkBlock
            pinkBlock.setTranslateY(20);
            //add pinkBlock to pinkBlocks List
            pinkBlocks.add(pinkBlock);
            //add pinkBlock to root children list and access to root to show
            root.getChildren().add(pinkBlock);
        }

        //create 200 blocks and add them to the hbox layout.
        for (int i = 0; i < 200; i++) {
            //create block object
            Block block = new Block(200, 100);
            block.setTranslateY((double) initialY);
            //add block to root children list and access to root to show it own children
            hbox.getChildren().add(block);
        }

        root.getChildren().add(hbox);
        //add hbox to the root group

        //set up the scene with a purple background and add it to the primary stage.
        Scene scene = new Scene(root, 400, 400.0, Color.PURPLE);
        primaryStage.setTitle("Display Multiple Blocks");
        primaryStage.setScene(scene);
        primaryStage.show();

        //create a player object and add the player's rectangle to the root group.
        Player player = new Player(root, scene);
        playerRectangle = player.getPlayer();
        root.getChildren().add(playerRectangle);

        //Event handler for mouse clicks
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //method handle
            public void handle(MouseEvent event) {
                //move all blocks in hbox to the left by 200 pixels
                for (Node node : hbox.getChildren()) {
                    node.setTranslateX(node.getTranslateX() - 200);
                    //check for collision between player and blocks
                    if (node instanceof Rectangle && node.getBoundsInParent().intersects(playerRectangle.getBoundsInParent())) {
                        Block block = (Block) node;
                        //if the block is red and the timer is not active, start a countdown
                        if (block.getFill().equals(Color.RED) && !timerActive) {
                            timerActive = true;
                            ObjectProperty<java.time.Duration> remainingDuration
                                    = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(2));
                            Timeline countDownTimeLine = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), (ActionEvent event1) ->
                                    remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
                            countDownTimeLine.setCycleCount((int) remainingDuration.get().getSeconds());
                            countDownTimeLine.play();
                            //when the countdown finishes, check the block color again
                            countDownTimeLine.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                //if user sit on red block lose one live
                                public void handle(ActionEvent event2) {
                                    if (block.getFill().equals(Color.RED)) {
                                        //change the pink block to white and increment the index
                                        if (pinkBlockIndex < pinkBlocks.size()) {
                                            pinkBlocks.get(pinkBlockIndex).setFill(Color.WHITE);
                                            pinkBlockIndex++;
                                            //if all pink blocks are white, display the lose image
                                            if (pinkBlockIndex == 3) {
                                                Image blockLose = new Image("file:///home/bahar/univercity/HoppenHelm/loseGame.jpeg");
                                                ImageView imageView = new ImageView(blockLose);
                                                imageView.setFitHeight(primaryStage.getHeight());
                                                imageView.setFitWidth(primaryStage.getWidth());
                                                root.getChildren().addAll(imageView);
                                                Scene scene1 = new Scene(root, 400, 300);
                                                primaryStage.setScene(scene1);
                                                primaryStage.show();
                                            }
                                        }
                                        timerActive = false;
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

        //Event handler for key presses.
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                //if the ENTER key is pressed, change the intersecting red block to gray
                if (event.getCode().toString().equals("ENTER")) {
                    for (Node node : hbox.getChildren()) {
                        if (node instanceof Rectangle) {
                            Rectangle block = (Rectangle) node;
                            if (block.getFill().equals(Color.RED) && block.getBoundsInParent().intersects(playerRectangle.getBoundsInParent())) {
                                block.setFill(Color.GRAY);
                                timerActive = false;
                                //reset the timer flag
                            }
                        }
                    }
                }
            }//end method handle
        });//end mouse clicked
    }//end start method

    //the main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }//end main method
}//end class
