package com.example.monopolythess.example;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SceneController {
    @FXML
    private TextField player1name, player2name;
    @FXML
    private ComboBox<Avatar> ComboBox1 = new ComboBox<>(), ComboBox2 = new ComboBox<>();
    /**
     * This method is automatically called when the FXML file is loaded.
     * It sets up the ComboBoxes for the avatars.
     */
    @FXML
    public void initialize() {
        setupComboBoxes(ComboBox1,ComboBox2);
    }
    /**
     * Handles the transition to Scene 2, verifying input and passing player data.
     */
    public void SwitchToScene2(ActionEvent event) throws IOException {
        String Username1 = player1name.getText();
        String Username2 = player2name.getText();

        Avatar player1Avatar = ComboBox1.getValue();
        Avatar player2Avatar = ComboBox2.getValue();

        boolean PassValidation2=false;
        // Validation for empty fields
        boolean PassValidation = CheckValid(Username1,Username2);
        if (PassValidation){
             PassValidation2 = CheckValidAvatar(player1Avatar, player2Avatar);
        }
        if (PassValidation && PassValidation2){
            // Load Scene 2
            FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("/com/example/monopolythess/Scene2.fxml"));
            Parent root1 = fxmlLoader.load();
            GameController gameController = fxmlLoader.getController();
            gameController.initializeGame(Username1, Username2, player1Avatar, player2Avatar);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root1, 1540, 790);
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * Combobox me ta avatars.
     */
    public void setupComboBoxes(ComboBox<Avatar> comboBox1, ComboBox<Avatar> comboBox2) {
        // Δημιουργία λίστας avatar
        Avatar avatarCar = new Avatar("Αμάξι", new Image(Objects.requireNonNull(getClass().getResource("/Car.png")).toExternalForm()));
        Avatar avatarHat = new Avatar("Καπέλο", new Image(Objects.requireNonNull(getClass().getResource("/hat.png")).toExternalForm()));
        Avatar avatarAgkyra = new Avatar("Άγκυρα", new Image(Objects.requireNonNull(getClass().getResource("/Agkyra.png")).toExternalForm()));
        Avatar avatarHorse = new Avatar("Άλογο", new Image(Objects.requireNonNull(getClass().getResource("/horse.png")).toExternalForm()));

        comboBox1.getItems().addAll(avatarCar, avatarHat, avatarAgkyra,avatarHorse);
        comboBox2.getItems().addAll(avatarCar, avatarHat, avatarAgkyra,avatarHorse);

        setupAvatarDisabling(comboBox1, comboBox2);
        setupAvatarDisabling(comboBox2, comboBox1);

        // CellFactory για εμφάνιση εικόνας και ονόματος
        setupCellFactory(comboBox1);
        setupCellFactory(comboBox2);
    }
    private void setupAvatarDisabling(ComboBox<Avatar> sourceComboBox, ComboBox<Avatar> targetComboBox) {
        sourceComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue != null) {
                // Επαναφέρεται το avatar όταν αλλάζει η επιλογή
                targetComboBox.getItems().add(oldValue);
            }
            if (newValue != null) {
                // Αφαιρείται το avatar που επιλέχθηκε
                targetComboBox.getItems().remove(newValue);
            }
        });
    }
    private void setupCellFactory(ComboBox<Avatar> comboBox) {
        comboBox.setCellFactory(cb -> new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Avatar avatar, boolean empty) {
                super.updateItem(avatar, empty);
                if (empty || avatar == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(avatar.getName());
                    imageView.setImage(avatar.getImage());
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        });
        comboBox.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Avatar avatar, boolean empty) {
                super.updateItem(avatar, empty);
                if (empty || avatar == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(avatar.getName());
                    imageView.setImage(avatar.getImage());
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        });
    }
    private boolean CheckValid(String Field1, String Field2) {

        if (Field1.isEmpty() && !Field2.isEmpty()) {
            player1name.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            player2name.setStyle("-fx-border-color: #00ff09; -fx-background-color: transparent; -fx-border-radius: 15px");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Τo πεδίo για τo όνομα δεν μπορεί να είναι κενό");

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return false;
        } else if (!Field1.isEmpty() && Field2.isEmpty()) {
            player1name.setStyle("-fx-border-color: #00ff09; -fx-background-color: transparent; -fx-border-radius: 15px");
            player2name.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Τo πεδίo για τo όνομα δεν μπορεί να είναι κενό");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return false;
        } else if (Field1.isEmpty()) {
            player1name.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            player2name.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Τα πεδία για τα ονόματα δεν μπορεί να είναι κενά");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return false;
        }
        else {
            player1name.setStyle("-fx-border-color: #00ff09; -fx-background-color: transparent; -fx-border-radius: 15px");
            player2name.setStyle("-fx-border-color: #00ff09; -fx-background-color: transparent; -fx-border-radius: 15px");
            return true ;
        }
    }
    public boolean CheckValidAvatar(Avatar avatar1,Avatar avatar2){
        if (avatar1==null && avatar2!=null) {
            ComboBox1.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            ComboBox2.setStyle("-fx-border-color: #00ff09; -fx-background-color: transparent; -fx-border-radius: 15px");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Πρέπει ο παίκτης να επιλέξει ένα Avatar");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return false;
        } else if (avatar1!=null    && avatar2==null) {
            ComboBox1.setStyle("-fx-border-color: #00ff09; -fx-background-color: transparent; -fx-border-radius: 15px");
            ComboBox2.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Πρέπει ο παίκτης να επιλέξει ένα Avatar");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return false;
        } else if (avatar1==null) {
            ComboBox1.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            ComboBox2.setStyle("-fx-border-color: red; -fx-background-color: transparent; -fx-border-radius: 15px");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Πρέπει οι παίκτες να επιλέξουν από ένα Avatar");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return false;
        }
        else {
            return true ;
        }
    }
    public void Back(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("/com/example/monopolythess/hello-view.fxml"));
        Parent root1 = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 1540, 790);
        stage.setScene(scene);
        stage.show();
    }

}