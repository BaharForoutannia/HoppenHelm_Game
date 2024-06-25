package com.example.hoppenhelmgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

//class Block represents the block
public class Block extends Rectangle {

    //class constructor 1 for the class Block
    //class constructor 1 has three arguments
    //width and height and color attributes those arguments
    //this constructor used for pink block
    public Block(double width, double height, Color color) {
        super(width, height, color);
    }//end constructor 1

    //class constructor 2 for the class Block
    //class constructor 2 has two arguments
    //width and height attributes those arguments
    //this constructor used for red and gray block
    public Block(double width, double height) {
        super(width, height);
        //create object color of class Color
        Color color = (generateRandomNumber() % 4 == 0) ? Color.RED : Color.GRAY;
        setFill(color);
    }//end constructor 2

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }//end generateRandomNumber
}//end class
