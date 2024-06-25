package com.example.example;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//class Block represents the Block
public class Block {

    //attributes from class Block
    //block attribute data type is Rectangle
    private Rectangle block;
    //root attribute data type is Group
    private Group root;
    //scene attribute data type is Scene
    private Scene scene;

    //class constructor 1 for the class Block
    //class constructor 1 has two arguments
    //root and scene attributes those arguments
    //the input root argument data type is Group
    public Block(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;

        //create object block of class Rectangle
        block = new Rectangle(250, 550, 100, 100);
        //set the dark green color from player object
        block.setFill(Color.DARKGREEN);
    }//end constructor 1

    //class constructor 2 for the class Block
    //class constructor 2 has two arguments
    //root and scene attributes those arguments
    //the input root argument data type is Pane
    public Block(Pane root, Scene scene) {
    }//end constructor 2



}//end class