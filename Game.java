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

public class Game extends Application {

    private Rectangle playerRectangle = new Rectangle();
    private List<Block> pinkBlocks = new ArrayList<>();
    private int pinkBlockIndex = 0;
    private boolean timerActive = false;

    public void start(Stage primaryStage) {
        Group root = new Group();
        HBox hbox = new HBox(1);
        int initialY = 650;

        for (int i = 0; i < 3; i++) {
            Block pinkBlock = new Block(50, 50, Color.HOTPINK);
            pinkBlock.setTranslateX(850 + i * 60);
            pinkBlock.setTranslateY(20);
            pinkBlocks.add(pinkBlock);
            root.getChildren().add(pinkBlock);
        }

        for (int i = 0; i < 200; i++) {
            Block block = new Block(200, 100);
            block.setTranslateY((double) initialY);
            hbox.getChildren().add(block);
        }

        root.getChildren().add(hbox);

        Scene scene = new Scene(root, 400, 400.0, Color.PURPLE);
        primaryStage.setTitle("Display Multiple Blocks");
        primaryStage.setScene(scene);
        primaryStage.show();

        Player player = new Player(root, scene);
        playerRectangle = player.getPlayer();
        root.getChildren().add(playerRectangle);

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for (Node node : hbox.getChildren()) {
                    node.setTranslateX(node.getTranslateX() - 200);
                    if (node instanceof Rectangle && node.getBoundsInParent().intersects(playerRectangle.getBoundsInParent())) {
                        Rectangle block = (Rectangle) node;
                        if (block.getFill().equals(Color.RED) && !timerActive) {
                            timerActive = true;
                            ObjectProperty<java.time.Duration> remainingDuration
                                    = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(2));
                            Timeline countDownTimeLine = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), (ActionEvent event1) ->
                                    remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
                            countDownTimeLine.setCycleCount((int) remainingDuration.get().getSeconds());
                            countDownTimeLine.play();
                            countDownTimeLine.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event2) {
                                    if (block.getFill().equals(Color.RED)) {
                                        if (pinkBlockIndex < pinkBlocks.size()) {
                                            pinkBlocks.get(pinkBlockIndex).setFill(Color.WHITE);
                                            pinkBlockIndex++;
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

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode().toString().equals("ENTER")) {
                    for (Node node : hbox.getChildren()) {
                        if (node instanceof Rectangle) {
                            Rectangle block = (Rectangle) node;
                            if (block.getFill().equals(Color.RED) && block.getBoundsInParent().intersects(playerRectangle.getBoundsInParent())) {
                                block.setFill(Color.GRAY);
                                timerActive = false; // این خط را اضافه کنید
                            }
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
