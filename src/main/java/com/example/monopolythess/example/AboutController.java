package com.example.monopolythess.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class AboutController {

    public void Back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("/com/example/monopolythess/hello-view.fxml"));
        Parent root1 = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 1540, 790);
        stage.setScene(scene);
        stage.show();
    }
}
