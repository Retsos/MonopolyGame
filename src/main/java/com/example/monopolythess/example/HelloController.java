package com.example.monopolythess.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloController {
    @FXML
    public void SwitchNextScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("/com/example/monopolythess/Scene1.fxml"));
        Parent root1 = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 1540, 790);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchOptions(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("/com/example/monopolythess/Options.fxml"));
        Parent root1 = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 1540, 790);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchAbout(ActionEvent event )throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("/com/example/monopolythess/About.fxml"));
        Parent root1 = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 1540, 790);
        stage.setScene(scene);
        stage.show();
    }
    public void Exit(){
        Platform. exit();
    }

}