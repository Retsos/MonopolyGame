package com.example.monopolythess.example;
import javafx.scene.image.Image;

public class Avatar {
    private final String name;
    private final Image image;
    public Avatar(String name, String imagePath) {this.name = name;this.image = new Image(imagePath);}
    public Avatar(String name, Image image) {this.name = name;this.image = image;}
    public String getName() {return name;}
    public Image getImage() {return image;}
}