package com.example.monopolythess.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/monopolythess/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1540, 790);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/monopoly-man.jpg"))));

        stage.setTitle("Monopoly!");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}