package com.example.example;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//class player represents the player
public class Player {

    //attributes from class Playerclass
    //player attribute data type is Rectangle
    private Rectangle player;
    //root attribute data type is Group
    private Group root;
    //scene attribute data type is Scene
    private Scene scene;

    //class constructor 1 for the class Player
    //class constructor 1 has two arguments
    //root and scene attributes those arguments
    //the input root argument data type is Group
    public Player(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;

        //create object player of class Rectangle
        player = new Rectangle(250, 550, 100, 100);

        //set the dark green color from player object
        player.setFill(Color.DARKGREEN);
    }//end constructor 1

    //class constructor 2 for the class Player
    //class constructor 2 has two arguments
    //root and scene attributes those arguments
    //the input root argument data type is Pane
    public Player(Pane root, Scene scene) {
    }//end constructor 2

    //method getPlayer return player value
    public Rectangle getPlayer() {
        return player;
    }//end getPlayer method

}//end class