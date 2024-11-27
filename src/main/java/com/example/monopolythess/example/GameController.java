package com.example.monopolythess.example;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
public class GameController {
    @FXML
    private Label displayname1, displayname2;
    Random random = new Random();
    @FXML
    private ImageView dice1, dice2;
    @FXML
    private Label Galazio1;
    @FXML
    private Label Galazio2;
    @FXML
    private Label Galazio3;
    @FXML
    private Label Kafe1;
    @FXML
    private Label Kafe2;
    @FXML
    private Label Kitrino1;
    @FXML
    private Label Kitrino2;
    @FXML
    private Label Kitrino3;
    @FXML
    private Label Kokkino1;
    @FXML
    private Label Kokkino2;
    @FXML
    private Label Kokkino3;
    @FXML
    private Label Mov1;
    @FXML
    private Label Mov2;
    @FXML
    private Label Mov3;
    @FXML
    private Label Mple1;
    @FXML
    private Label Mple2;
    @FXML
    private Label Portokali1;
    @FXML
    private Label Portokali2;
    @FXML
    private Label Portokali3;
    @FXML
    private Label Prasino1;
    @FXML
    private Label Prasino2;
    @FXML
    private Label Prasino3;
    @FXML
    private Label Turns;
    @FXML
    private Label money1, money2;
    @FXML
    private Label cardType;
    @FXML
    private Label rent;
    @FXML
    private Label rent1house;
    @FXML
    private Label rent2house;
    @FXML
    private Label rent3house;
    @FXML
    private Label doublerent;
    @FXML
    private Label rent4house;
    @FXML
    private Label CardName;
    @FXML
    private Label renthotel;
    @FXML
    private Label IsBought;
    @FXML
    private Label CostOfArea;
    @FXML
    private Label CostOfRent;
    @FXML
    private Label PayNotPayRent;
    @FXML
    private Label fullset1;
    @FXML
    private Label fullset2;
    @FXML
    private Label Houses1, Houses2, CostOfHouse1, CostOfHouse2,Buying;
    @FXML
    private Button YesBuy, SellCard1, SellCard2,playbutton;
    @FXML
    private Button NoBuy, EndTurn, PayRentBtn, BuyHouse1, BuyHouse2, BuyHotel1, BuyHotel2, SellRegion1, SellRegion2, PayTax;
    private boolean endofround = true;
    //CARDS
    private StartPos start;
    private Prison prison;
    private FreePass freePass;
    private GoToPrison gotoPrison;
    private Odoi odos1, odos2, odos4, odos5, odos6, odos7, odos8, odos9, odos10, odos11, odos12, odos13, odos14, odos15, odos16, odos17, odos18, odos19, odos20, odos21, odos22, odos23;
    private Odoi odos24, odos25, odos26, odos27, odos28, odos29;
    private Cards odos30, odos31, odos32, odos33, odos34, odos35, odos37, odos3;
    private final Cards[] Board = new Cards[40]; // Ο πίνακας του παιχνιδιού με 40 θέσεις
    private int dice1Number, dice2Number, turn = 1, newPos;
    private Player player1, player2;
    @FXML
    private StackPane CarStack, HatStack, HorseStack, AgkyraStack;
    private boolean doubles;
    @FXML
    private CheckBox CheckPrison1, CheckPrison2;
    @FXML
    private TitledPane RegionShow1, RegionShow2;
    @FXML
    private ListView<String> Agores1, Agores2;
    //Methods
    Stack<Entoli> Entoles = new Stack<>();
    Stack<Apofasi> Apofaseis = new Stack<>();
    Stack<Entoli> UsedEntoles = new Stack<>();
    Stack<Apofasi> UsedApofaseis = new Stack<>();
    int diceSum, counter1doubles = 0, counter2doubles = 0, countEntoles = 0, countApofaseis = 0;
    @FXML
    private ImageView Car1, Car2, Hat1, Hat2, Horse1, Horse2, Anchor1, Anchor2;
    @FXML
    Stage Newstage;
    @FXML
    Scene Newscene;
    private int posoProsPlirwmi=0;
    private boolean ForosIsUp,RentIsUp,Buy,NotBuy;
    private int rounds=0;
    //Zaria
    public void Dice() {
        if (endofround) {
            endofround = false;
            YesBuy.setDisable(true);
            playbutton.setDisable(true);
            NoBuy.setDisable(true);
            Thread thread = new Thread(() -> {
                try {
                    for (int i = 0; i < 15; i++) {
                        dice1Number = random.nextInt(6) + 1;
                        dice2Number = random.nextInt(6) + 1;

                        String dice1Path = getDiceImagePath(dice1Number);
                        String dice2Path = getDiceImagePath(dice2Number);

                        Platform.runLater(() -> {
                            dice1.setImage(new Image(dice1Path));
                            dice2.setImage(new Image(dice2Path));
                        });

                        Thread.sleep(50);
                    }
                    // Υπολογισμός τελικών ζαριών μετά τη λήξη του βρόχου
                    diceSum = dice1Number + dice2Number;
                    doubles = dice1Number == dice2Number;
                    Platform.runLater(() -> {
                        YesBuy.setDisable(false);
                        NoBuy.setDisable(false);
                        Buy=false;
                        playbutton.setDisable(false);
                        NotBuy=false;
                        StartGame(diceSum);
                        rounds++;

                        System.out.println(doubles);
                        if (rounds>0) EndTurn.setDisable(false);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Σφάλμα");
                        alert.setHeaderText("Αδυναμία εκτέλεσης ζαριών");
                        alert.setContentText("Υπήρξε ένα σφάλμα κατά τη διαδικασία. Παρακαλώ δοκιμάστε ξανά.");
                        alert.showAndWait();
                    });
                }
            });
            thread.start();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Δεν έχεις τελειώσει τον γύρο σου");
            alert.showAndWait();
        }
    }
    private String getDiceImagePath(int diceNumber) {
        try {
            return Objects.requireNonNull(getClass().getResource("/dice" + diceNumber + ".png")).toExternalForm();
        } catch (NullPointerException e) {
            throw new RuntimeException("Η εικόνα για το ζάρι " + diceNumber + " δεν βρέθηκε.");
        }
    }
    // Αρχικοποίηση των δεδομένων του παιχνιδιού
    public void initializeGame(String username1, String username2, Avatar Avatar1, Avatar Avatar2) {
        Car1.setOpacity(0);
        Car2.setOpacity(0);
        Horse1.setOpacity(0);
        Horse2.setOpacity(0);
        Hat1.setOpacity(0);
        Hat2.setOpacity(0);
        Anchor1.setOpacity(0);
        Anchor2.setOpacity(0);
        PayTax.setOpacity(0);
        Buying.setText("");
        PayTax.setDisable(true);

        Cards[] cards1 = new Cards[29];
        Cards[] cards2 = new Cards[29];
        HatStack.setOpacity(0);
        CarStack.setOpacity(0);
        HorseStack.setOpacity(0);
        AgkyraStack.setOpacity(0);

        switch (Avatar1.getName()) {
            case "Αμάξι" -> {
                player1 = new Player(1000, username1, cards1, CarStack, 0, Agores1, 0, 0, money1, false, 0, 0, 0, 0);
                Car1.setOpacity(1);
            }
            case "Καπέλο" -> {
                player1 = new Player(1000, username1, cards1, HatStack, 0, Agores1, 0, 0, money1, false, 0, 0, 0, 0);
                Hat1.setOpacity(1);
            }
            case "Άλογο" -> {
                player1 = new Player(1000, username1, cards1, HorseStack, 0, Agores1, 0, 0, money1, false, 0, 0, 0, 0);
                Horse1.setOpacity(1);
            }
            case null, default -> {
                player1 = new Player(1000, username1, cards1, AgkyraStack, 0, Agores1, 0, 0, money1, false, 0, 0, 0, 0);
                Anchor1.setOpacity(1);
            }
        }
        switch (Avatar2.getName()) {
            case "Αμάξι" -> {
                player2 = new Player(1000, username2, cards2, CarStack, 0, Agores2, 0, 0, money2, false, 0, 0, 0, 0);
                Car2.setOpacity(1);
            }
            case "Καπέλο" -> {
                player2 = new Player(1000, username2, cards2, HatStack, 0, Agores2, 0, 0, money2, false, 0, 0, 0, 0);
                Hat2.setOpacity(1);
            }
            case "Άλογο" -> {
                player2 = new Player(1000, username2, cards2, HorseStack, 0, Agores2, 0, 0, money2, false, 0, 0, 0, 0);
                Horse2.setOpacity(1);
            }
            case null, default -> {
                player2 = new Player(1000, username2, cards2, AgkyraStack, 0, Agores2, 0, 0, money2, false, 0, 0, 0, 0);
                Anchor2.setOpacity(1);
            }
        }

        //show the chosen pics
        player1.getStack().setOpacity(1);
        player2.getStack().setOpacity(1);

        CreateApofaseis();
        CreateEntoles();
        initializeCards();
        createBoard();

        if (player1.getStack() != null) {
            player1.getStack().setLayoutX(Board[0].getX());
            player1.getStack().setLayoutY(Board[0].getY());
        }

        if (player2.getStack() != null) {
            player2.getStack().setLayoutX(Board[0].getX());
            player2.getStack().setLayoutY(Board[0].getY());
        }

        money1.setText("Χρήματα:" + player1.getMoney());
        money2.setText("Χρήματα:" + player2.getMoney());

        CheckPrison1.setDisable(true);
        CheckPrison2.setDisable(true);

        CheckPrison1.setSelected(false);
        CheckPrison2.setSelected(false);

        EndTurn.setDisable(true);

        displayname1.setText("Παίκτης 1: " + player1.getName());
        displayname2.setText("Παίκτης 2: " + player2.getName());
        Turns.setText("Σειρά του " + turn + "ου Παίκτη");

        cardType.setText("Τύπος Κάρτας " + Board[0].getType());
        CardName.setText("Ονομασία Περιοχής " + Board[0].getCardName());

        UIRefresher();
        DisableButtons();
        Agores1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Cards selectedCard = getCardByName(newValue, player1);

                if (selectedCard instanceof Odoi odos) {
                    RegionShow1.setText("Όνομα Περιοχής " + odos.getCardName());
                    String color = odos.getColor();
                    boolean isCompleteSet = CompletedSet(color, player1);
                    fullset1.setText(isCompleteSet ? "Ολοκληρωμένο Σετ: Ναι" : "Ολοκληρωμένο Σετ: Όχι");
                    Houses1.setText("Πλήθος Σπιτιών: " + odos.getSpitia());
                    CostOfHouse1.setText("Κόστος Σπιτιού: " + odos.getTimiSpiti());
                }
            }
        });
        Agores2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Cards selectedCard = getCardByName(newValue, player2);

                if (selectedCard instanceof Odoi odos) {
                    RegionShow2.setText("Όνομα Περιοχής " + odos.getCardName());
                    String color = odos.getColor();
                    boolean isCompleteSet = CompletedSet(color, player2);
                    fullset2.setText(isCompleteSet ? "Ολοκληρωμένο Σετ: Ναι" : "Ολοκληρωμένο Σετ: Όχι");
                    Houses2.setText("Πλήθος Σπιτιών: " + odos.getSpitia());
                    CostOfHouse2.setText("Κόστος Σπιτιού: " + odos.getTimiSpiti());
                }
            }
        });

        BuyHouse1.setOnAction(event -> {
            if (turn == 1) {
                String selectedCardName = Agores1.getSelectionModel().getSelectedItem();
                Cards selectedCard = getCardByName(selectedCardName, player1);
                if (selectedCard instanceof Odoi odos) {
                    switch (odos.getType()) {
                        case "Οδός" -> {
                            String color = odos.getColor();
                            boolean isCompleteSet = CompletedSet(color, player1);
                            if (isCompleteSet) {
                                HandleBuyHouse(player1, selectedCardName, event);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Αγορά Σπιτιού");
                                alert.setHeaderText("Αποτυχία Αγοράς");
                                alert.setContentText("Πρέπει πρώτα να ολοκληρώσεις το χρωματικό σετ για να αγοράσεις σπίτι!");
                                alert.showAndWait();
                            }
                        }
                        case null, default -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Αγορά Σπιτιού");
                            alert.setHeaderText("Αποτυχία Αγοράς");
                            alert.setContentText("Δεν μπορείς να αγοράσεις σπίτι σε αυτήν την περιοχή");
                            alert.showAndWait();
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να αγοράσεις σπίτι.");
                alert.showAndWait();
            }
        });
        BuyHouse2.setOnAction(event -> {
            if (turn == 2) {
                String selectedCardName = Agores2.getSelectionModel().getSelectedItem();
                Cards selectedCard = getCardByName(selectedCardName, player2);
                if (selectedCard instanceof Odoi odos) {
                    switch (odos.getType()) {
                        case "Οδός" -> {
                            String color = odos.getColor();
                            boolean isCompleteSet = CompletedSet(color, player2);
                            if (isCompleteSet) {
                                // Προχωράει στην αγορά σπιτιού αν το σετ είναι ολοκληρωμένο
                                HandleBuyHouse(player2, selectedCardName, event);
                            } else {
                                // Εμφανίζει μήνυμα ότι πρέπει πρώτα να ολοκληρωθεί το σετ
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Αγορά Σπιτιού");
                                alert.setHeaderText("Αποτυχία Αγοράς");
                                alert.setContentText("Πρέπει πρώτα να ολοκληρώσεις το χρωματικό σετ για να αγοράσεις σπίτι!");
                                alert.showAndWait();
                            }
                        }
                        case null, default -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Αγορά Σπιτιού");
                            alert.setHeaderText("Αποτυχία Αγοράς");
                            alert.setContentText("Δεν μπορείς να αγοράσεις σπίτι σε αυτήν την περιοχή");
                            alert.showAndWait();
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να αγοράσεις σπίτι.");
                alert.showAndWait();
            }
        });
        BuyHotel1.setOnAction(event -> {
            if (turn == 1) {
                String selectedCardName = Agores1.getSelectionModel().getSelectedItem();
                Cards selectedCard = getCardByName(selectedCardName, player1);
                if (selectedCard instanceof Odoi odos) {
                    switch (odos.getType()) {
                        case "Οδός" -> {
                            if (odos.getSpitia() == 4) {
                                HandleBuyHotel(player1, selectedCardName, event);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Αγορά Ξενοδοχείου");
                                alert.setHeaderText("Αποτυχία Αγοράς");
                                alert.setContentText("Πρέπει πρώτα να αγοράσεις 4 σπίτια για να αγοράσεις ξενοδοχείο!");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image("monopoly-man.jpg"));
                                alert.showAndWait();
                            }
                        }
                        case null, default -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Αγορά Ξενοδοχείου");
                            alert.setHeaderText("Αποτυχία Αγοράς");
                            alert.setContentText("Δεν μπορείς να αγοράσεις Ξενοδοχείο σε αυτήν την περιοχή");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image("monopoly-man.jpg"));
                            alert.showAndWait();
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να αγοράσεις Ξενοδοχείο.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        });
        BuyHotel2.setOnAction(event -> {
            if (turn == 2) {
                String selectedCardName = Agores2.getSelectionModel().getSelectedItem();
                Cards selectedCard = getCardByName(selectedCardName, player2);
                if (selectedCard instanceof Odoi odos) {
                    switch (odos.getType()) {
                        case "Οδός" -> {
                            if (odos.getSpitia() == 4) {
                                HandleBuyHotel(player2, selectedCardName, event);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Αγορά Ξενοδοχείου");
                                alert.setHeaderText("Αποτυχία Αγοράς");
                                alert.setContentText("Πρέπει πρώτα να αγοράσεις 4 σπίτια για να αγοράσεις ξενοδοχείο!");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image("monopoly-man.jpg"));
                                alert.showAndWait();
                            }
                        }
                        case null, default -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Αγορά Ξενοδοχείου");
                            alert.setHeaderText("Αποτυχία Αγοράς");
                            alert.setContentText("Δεν μπορείς να αγοράσεις Ξενοδοχείο σε αυτήν την περιοχή");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image("monopoly-man.jpg"));
                            alert.showAndWait();
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να αγοράσεις Ξενοδοχείο.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        });

        SellCard1.setOnAction(event -> {
            if (turn == 1) {
                SellPrisonCard(player1, event);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να πουλήσεις την κάρτα.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        });
        SellCard2.setOnAction(event -> {
            if (turn == 2) {
                SellPrisonCard(player2, event);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να πουλήσεις την κάρτα.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        });
        SellRegion1.setOnAction(event -> {
            if (turn == 1) {
                String selectedCardName = Agores1.getSelectionModel().getSelectedItem();
                Cards selectedCard = getCardByName(selectedCardName, player1);
                SellRegionCard(player1, selectedCard, event);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να πουλήσεις την κάρτα.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        });
        SellRegion2.setOnAction(event -> {
            if (turn == 2) {
                String selectedCardName = Agores2.getSelectionModel().getSelectedItem();
                Cards selectedCard = getCardByName(selectedCardName, player2);
                SellRegionCard(player2, selectedCard, event);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Λάθος Ενέργεια");
                alert.setContentText("Δεν είναι η σειρά σου για να πουλήσεις την κάρτα.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        });

        PayTax.setOnAction(event -> {
            boolean result;
            if (turn == 1) {
                result = ConfirmPayTax(player1, player1.getCurrentPos(), posoProsPlirwmi);
            }else{
                result = ConfirmPayTax(player2, player2.getCurrentPos(), posoProsPlirwmi);
            }
            if (result){
                ForosIsUp=false;
                PayTax.setDisable(true);
                BasicVisibility();
            }
        });
    }
    public  void BasicVisibility(){
        PayTax.setDisable(true);
        PayTax.setOpacity(0);
        PayRentBtn.setOpacity(1);
        PayRentBtn.setDisable(true);
    }
    public  void NotBasicVisibility(){
        PayTax.setDisable(false);
        PayTax.setOpacity(1);
        PayRentBtn.setOpacity(0);
        PayRentBtn.setDisable(true);
    }
    public void DisableButtons() {
        YesBuy.setDisable(true);
        NoBuy.setDisable(true);
        PayRentBtn.setDisable(true);
    }
    private Cards getCardByName(String cardName, Player player) {
        if (!(cardName == null)) {
            for (Cards card : player.getCards()) {
                if (card.getCardName().equals(cardName))
                    return card;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Λάθος Ενέργεια");
            alert.setContentText("Δεν έχεις επιλέξει κάποια περιοχή.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            return null;
        }
        return null;
    }
    public void CreateApofaseis() {
        Apofasi decision1 = new Apofasi("Πήγαινε στην Εκκίνηση", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε στην ΕκκίνησηΠαίρνεις 200€ ");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setCurrentPos(39);
                MovePlayer(player, 1);
                return true;
            }
        };
        Apofasi decision2 = new Apofasi("Ασφάλεια ζωής", "Apofasi",true) {
            @Override
            public void DoTax(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Ασφάλεια ζωής. Πλήρωσε 100€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                posoProsPlirwmi=100;
                NotBasicVisibility();
            }
        };
        Apofasi decision3 = new Apofasi("Φορολογία", "Apofasi",true) {
            @Override
            public void DoTax(Player player) {
                Player nextPlayer = (turn == 2) ? player1 : player2;
                int Synolo = (player.getSynoloSpitiwn() * 20 + player.getSynoloJenodoxeiwn() * 50 )+(nextPlayer.getSynoloSpitiwn() * 20 + nextPlayer.getSynoloJenodoxeiwn() * 50);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Φορολογία για την ανακατασκευή των οδών. Πλήρωσε 20€ για κάθε σπίτι και 50€ για κάθε ξενοδοχείο. Σύνολο: " + Synolo);
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                posoProsPlirwmi=Synolo;
                NotBasicVisibility();
            }
        };
        Apofasi decision4 = new Apofasi("Ασφάλεια ζωής", "Apofasi",true) {
            @Override
            public void DoTax(Player player) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Ασφάλεια ζωής. Πλήρωσε 100€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                // Κλήση της processPayment με το ποσό πληρωμής
                posoProsPlirwmi=150;
                NotBasicVisibility();
            }
        };
        Apofasi decision5 = new Apofasi("Κληρονομείς 500€", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Κληρονομείς 500€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 500);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Apofasi decision6 = new Apofasi("Κέρδισες το 2ο βραβείο ομορφιάς. Παίρνεις 250€", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Κέρδισες το 2ο βραβείο ομορφιάς. Παίρνεις 250€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 250);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Apofasi decision7 = new Apofasi("Πάρε για τις υπηρεσίες που προσέφερες 100€", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πάρε για τις υπηρεσίες που προσέφερες 100");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 100);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Apofasi decision8 = new Apofasi("Τραπεζικό λάθος υπέρ σου. Πάρε 200€ ", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Τραπεζικό λάθος υπέρ σου. Πάρε 200€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 200);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Apofasi decision9 = new Apofasi("Βγες δωρεάν έξω από τη φυλακή ", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Βγες δωρεάν έξω από τη φυλακή");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                if (player == player1) {
                    CheckPrison1.setSelected(true);
                } else CheckPrison2.setSelected(true);

                return true;
            }
        };
        Apofasi decision10 = new Apofasi("Πήγαινε κατευθείαν στη φυλακή. Δεν παίρνεις 200€ ", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε κατευθείαν στη φυλακή. Δεν παίρνεις 200€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setCurrentPos(10);
                player.getStack().setLayoutY(Board[10].getY());
                player.getStack().setLayoutX(Board[10].getX());
                player.setPrison(true);
                return true;
            }
        };
        Apofasi decision11 = new Apofasi("Επιστροφή φόρου εισοδήματος. Παίρνεις 100€", "Apofasi",false) {
            @Override
            public boolean Do(Player player) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Επιστροφή φόρου εισοδήματος. Παίρνεις 100€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 100);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Apofasi decision12 = new Apofasi(" Πλήρωσε για νοσοκομειακή περίθαλψη 100€", "Apofasi",true) {
            @Override
            public void DoTax(Player player) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πλήρωσε για νοσοκομειακή περίθαλψη 100€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                // Κλήση της processPayment με το ποσό πληρωμής
                posoProsPlirwmi=100;
                NotBasicVisibility();
            }
        };
        Apofasi decision13 = new Apofasi("Σε έπιασαν για φοροδιαφυγή! Πλήρωσε έξτρα 50€", "Apofasi",true) {
            @Override
            public void DoTax(Player player) {

                // Εμφάνιση μηνύματος για την κάρτα
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Απόφασης");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Σε έπιασαν για φοροδιαφυγή! Πλήρωσε έξτρα 50€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                posoProsPlirwmi=50;
                NotBasicVisibility();
            }
        };
        Apofaseis.push(decision1);
        Apofaseis.push(decision3);
        Apofaseis.push(decision4);
        Apofaseis.push(decision5);
        Apofaseis.push(decision6);
        Apofaseis.push(decision7);
        Apofaseis.push(decision8);
        Apofaseis.push(decision9);
        Apofaseis.push(decision10);
        Apofaseis.push(decision11);
        Apofaseis.push(decision12);
        Apofaseis.push(decision2);
        Apofaseis.push(decision13);
    }
    public void CreateEntoles() {
        Entoli entoli1 = new Entoli("Πήγαινε στην εκκίνηση Παίρνεις 200€", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε στην εκκίνηση Παίρνεις 200€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setCurrentPos(39);
                MovePlayer(player, 1);
                return true;
            }
        };
        Entoli entoli2 = new Entoli("Πήγαινε στο σταθμό Μετρό", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε στο σταθμό του Μετρό");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setCurrentPos(39);
                MovePlayer(player, 26);
                return true;

            }
        };
        Entoli entoli3 = new Entoli("Πήγαινε στον κοντινότερο σιδηροδρομικό σταθμό και πλήρωσε στον ιδιοκτήτη του δυο φορές το ενοίκιο που δικαιούται. Αν ο σταθμός δεν έχει πουληθεί μπορείς να τον αγοράσεις από την τράπεζα", "Εντολή") {
            @Override
            public boolean Do(Player player) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε στον κοντινότερο σιδηροδρομικό σταθμό και πλήρωσε στον ιδιοκτήτη του δυο φορές το ενοίκιο που δικαιούται. Αν ο σταθμός δεν έχει πουληθεί μπορείς να τον αγοράσεις από την τράπεζα.");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                int[] stationPositions = {5, 15, 25, 35};
                int currentPosition = player.getCurrentPos();
                int nearestStation = stationPositions[0];
                int minDistance = 40; // μέγιστη απόσταση στον πίνακα (κυκλικός πίνακας με 40 θέσεις)

                for (int station : stationPositions) {
                    // Υπολογισμός απόστασης προς τα εμπρός και προς τα πίσω
                    int distanceForward = (station >= currentPosition) ? station - currentPosition : station - currentPosition + 40;
                    int distanceBackward = (currentPosition >= station) ? currentPosition - station : currentPosition - station + 40;

                    // Επιλέγουμε τη μικρότερη από τις δύο αποστάσεις
                    int distance = Math.min(distanceForward, distanceBackward);

                    // Ενημέρωση της κοντινότερης απόστασης και σταθμού, αν χρειάζεται
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestStation = station;
                    }
                }
                player.setCurrentPos(0);
                player.getStack().setLayoutY(Board[nearestStation].getY());
                player.getStack().setLayoutX(Board[nearestStation].getX());

                MovePlayer(player, nearestStation);
                return true;
            }

        };
        Entoli entoli4 = new Entoli("Πήγαινε πίσω 3 τετράγωνα ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε πίσω 3 τετράγωνα");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                int sum = player.getCurrentPos() - 3;
                player.setCurrentPos(0);
                MovePlayer(player, sum);
                return true;
            }
        };
        Entoli entoli5 = new Entoli("Έχεις εκλεγεί πρόεδρος του παιχνιδιού. Πλήρωσε τον αντίπαλο παίκτη 50€ ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Έχεις εκλεγεί πρόεδρος του παιχνιδιού. Πλήρωσε τον αντίπαλο παίκτη 50€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                Player nextPlayer = (turn == 2) ? player1 : player2;

                if (player.getMoney() >= 50) {
                    player.setMoney(player.getMoney() - 50);
                    player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                    nextPlayer.setMoney(nextPlayer.getMoney() + 50);
                    nextPlayer.getShowmoney().setText("Χρήματα: " + nextPlayer.getMoney());
                    return true;
                } else {
                    CheckMoney(player);
                    return false;
                }
            }
        };
        Entoli entoli6 = new Entoli("Πάρε επίδομα ανεργίας 100€ ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πάρε επίδομα ανεργίας 100€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 100);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Entoli entoli7 = new Entoli("Πήγαινε κατευθείαν στη φυλακή. Δεν παίρνεις 200€ ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε κατευθείαν στη φυλακή. Δεν παίρνεις 200€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setCurrentPos(10);
                player.getStack().setLayoutY(Board[10].getY());
                player.getStack().setLayoutX(Board[10].getX());
                player.setPrison(true);
                return true;
            }
        };
        Entoli entoli8 = new Entoli("Βγες δωρεάν από τη φυλακή", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Βγες δωρεάν από τη φυλακή");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                if (player == player1) {
                    CheckPrison1.setSelected(true);
                } else CheckPrison2.setSelected(true);
                return true;
            }
        };
        Entoli entoli9 = new Entoli("Πήγαινε μπροστά 2 τετράγωνα", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε μπροστά 2 τετράγωνα");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                int sum = player.getCurrentPos() + 2;
                player.setCurrentPos(0);
                MovePlayer(player, sum);
                return true;
            }
        };
        Entoli entoli10 = new Entoli("Πήγαινε στη Θέρμη (Αν περνάς από την εκκίνηση πάρε 200€) ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε στη Θέρμη (Αν περνάς από την εκκίνηση πάρε 200€)");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setCurrentPos(39);
                MovePlayer(player, 38);
                return true;
            }
        };
        Entoli entoli11 = new Entoli("Πήγαινε στο σταθμό του ΟΑΣΘ (Αν περνάς από την εκκίνηση πάρε 200€)\" ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πήγαινε στο σταθμό του ΟΑΣΘ (Αν περνάς από την εκκίνηση πάρε 200€)");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                if (player.getCurrentPos() > 5) {
                    player.setMoney(player.getMoney() + 200);
                    player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                }
                player.setCurrentPos(39);
                MovePlayer(player, 6);
                return true;
            }
        };
        Entoli entoli12 = new Entoli("Το δάνειο σου ωριμάζει παίρνεις 150€ ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Το δάνειο σου ωριμάζει παίρνεις 150€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 150);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Entoli entoli13 = new Entoli("Πάρε Χριστουγεννιάτικο δώρο 300€ ", "Εντολή") {
            @Override
            public boolean Do(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Κάρτα Εντολής");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");

                Text content = new Text("Πάρε Χριστουγεννιάτικο δώρο 300€");
                content.setTextAlignment(TextAlignment.CENTER);
                content.setWrappingWidth(400);
                alert.getDialogPane().setContent(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                player.setMoney(player.getMoney() + 300);
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                return true;
            }
        };
        Entoles.push(entoli1);
        Entoles.push(entoli2);
        Entoles.push(entoli3);
        Entoles.push(entoli4);
        Entoles.push(entoli5);
        Entoles.push(entoli6);
        Entoles.push(entoli7);
        Entoles.push(entoli8);
        Entoles.push(entoli9);
        Entoles.push(entoli10);
        Entoles.push(entoli11);
        Entoles.push(entoli12);
        Entoles.push(entoli13);
    }
    private void initializeCards() {
        // Αρχικοποίηση των βασικών θέσεων
        start = new StartPos("Αφετηρία", "Σημείο", 644, 585);
        prison = new Prison("Φυλακή", "Σημείο", 5, 585);
        freePass = new FreePass("Ελεύθερη Στάθμευση", "Σημείο", -8, -54);
        gotoPrison = new GoToPrison("Πήγαινε Στην Φυλακή", "Σημείο", 654, -54);
        // Αρχικοποίηση των δρόμων (Οδοί) με τιμές που έχεις ήδη
        odos1 = new Odoi("Εύοσμος", "Οδός", 60, 2, 0, false, false, 570, 585, "", "Brown", 50, false);
        Kafe1.setText(odos1.getCardName());
        odos2 = new Odoi("Συκιές", "Οδός", 50, 4, 0, false, false, 450, 585, "", "Brown", 50, false);
        Kafe2.setText(odos2.getCardName());
        odos4 = new Odoi("Βαρδάρης", "Οδός", 100, 6, 0, false, false, 268, 585, "", "Cyan", 50, false);
        Galazio1.setText(odos4.getCardName());
        odos5 = new Odoi("Βενιζέλου", "Οδός", 120, 6, 0, false, false, 150, 585, "", "Cyan", 50, false);
        Galazio2.setText(odos5.getCardName());
        odos6 = new Odoi("Νεάπολη", "Οδός", 140, 8, 0, false, false, 90, 585, "", "Cyan", 50, false);
        Galazio3.setText(odos6.getCardName());
        odos7 = new Odoi("Λαγκαδάς", "Οδός", 140, 10, 0, false, false, 5, 505, "", "Pink", 50, false);
        Mov1.setText(odos7.getCardName());
        odos8 = new Odoi("Βούλγαρη", "Οδός", 140, 10, 0, false, false, 5, 384, "", "Pink", 100, false);
        Mov2.setText(odos8.getCardName());
        odos9 = new Odoi("Πολίχνη", "Οδός", 140, 12, 0, false, false, 5, 325, "", "Pink", 100, false);
        Mov3.setText(odos9.getCardName());
        odos10 = new Odoi("Ντεπώ", "Οδός", 160, 14, 0, false, false, 5, 203, "", "Orange", 100, false);
        Portokali1.setText(odos10.getCardName());
        odos11 = new Odoi("Φάληρο", "Οδός", 160, 14, 0, false, false, 5, 80, "", "Orange", 100, false);
        Portokali2.setText(odos11.getCardName());
        odos12 = new Odoi("Τριανδρία", "Οδός", 180, 16, 0, false, false, 5, 21, "", "Orange", 100, false);
        Portokali3.setText(odos12.getCardName());
        odos13 = new Odoi("Δελφών", "Οδός", 200, 18, 0, false, false, 87, -54, "", "Red", 100, false);
        Kokkino1.setText(odos13.getCardName());
        odos14 = new Odoi("Χαριλάου", "Οδός", 220, 18, 0, false, false, 210, -54, "", "Red", 150, false);
        Kokkino2.setText(odos14.getCardName());
        odos15 = new Odoi("Ανάληψη", "Οδός", 220, 20, 0, false, false, 270, -54, "", "Red", 150, false);
        Kokkino3.setText(odos15.getCardName());
        odos16 = new Odoi("Ν.Παραλία", "Οδός", 240, 20, 0, false, false, 390, -54, "", "Yellow", 150, false);
        Kitrino1.setText(odos16.getCardName());
        odos17 = new Odoi("Πυλαία", "Οδός", 260, 22, 0, false, false, 450, -54, "", "Yellow", 150, false);
        Kitrino2.setText(odos17.getCardName());
        odos18 = new Odoi("Τούμπα", "Οδός", 260, 22, 0, false, false, 573, -54, "", "Yellow", 150, false);
        Kitrino3.setText(odos18.getCardName());
        odos19 = new Odoi("Ρετζίκι", "Οδός", 280, 24, 0, false, false, 654, 21, "", "Green", 150, false);
        Prasino1.setText(odos19.getCardName());
        odos20 = new Odoi("Καλαμαριά", "Οδός", 300, 26, 0, false, false, 654, 84, "", "Green", 200, false);
        Prasino2.setText(odos20.getCardName());
        odos21 = new Odoi("Ν.Κρήνη", "Οδός", 300, 26, 0, false, false, 654, 204, "", "Green", 200, false);
        Prasino3.setText(odos21.getCardName());
        odos22 = new Odoi("Θέρμη", "Οδός", 320, 28, 0, false, false, 654, 384, "", "Blue", 200, false);
        Mple1.setText(odos22.getCardName());
        odos23 = new Odoi("Πανόραμα", "Οδός", 350, 35, 0, false, false, 654, 505, "", "Blue", 250, false);
        Mple2.setText(odos23.getCardName());
        //STATHMOI/ETAIRIES
        odos24 = new Odoi("ΟΑΣΘ", "Σταθμός", 200, false, 330, 585, "", "Grey");
        odos25 = new Odoi("ΟΣΕ", "Σταθμός", 200, false, 37, 263, "", "Grey");
        odos26 = new Odoi("ΜΕΤΡΟ", "Σταθμός", 200, false, 330, -54, "", "Grey");
        odos27 = new Odoi("SKG", "Σταθμός", 200, false, 654, 267, "", "Grey");
        odos28 = new Odoi("Εταιρία Ρεύματος", "Εταιρία", 150, false, 5, 450, "", "White");
        odos29 = new Odoi("Εταιρία Ύδρευσης ", "Εταιρία", 250, false, 512, -54, "", "White");
        //KARTES APOFASIS/ENTOLIS
        odos30 = new Apofasi("Απόφαση", "Απόφαση", 512, 585);
        odos31 = new Apofasi("Απόφαση", "Απόφαση", 5, 142);
        odos32 = new Apofasi("Απόφαση", "Απόφαση", 654, 147);
        odos33 = new Entoli("Εντολή", "Εντολή", 208, 585);
        odos34 = new Entoli("Εντολή", "Εντολή", 147, -54);
        odos35 = new Entoli("Εντολή", "Εντολή", 654, 326);
        //Foros
        odos37 = new Cards("Έκτακτη Φορολογία", "Φόρος", 654, 442) {
            @Override
            public void DoTax(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Φόρος");
                alert.setHeaderText("Έκτακτη Φορολογία");
                alert.setContentText("Η τράπεζα σε χρεώνει 100€ ");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                posoProsPlirwmi=100;
                NotBasicVisibility();
            }
        };
        odos3 = new Cards("Φόρος Πολυτελείας", "Φόρος", 385, 592) {
            @Override
            public void DoTax(Player player) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Φόρος");
                alert.setHeaderText("Φόρος Πολυτελείας");
                alert.setContentText("Η τράπεζα σε χρεώνει 200€ ");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();

                posoProsPlirwmi=200;
                NotBasicVisibility();
            }
        };
    }
    public void createBoard() {
        // Προσθήκη των θέσεων στον πίνακα του παιχνιδιού
        Board[0] = start;
        Board[1] = odos1;
        Board[2] = odos30;  // ΑΠΟΦΑΣΗ 1
        Board[3] = odos2;
        Board[4] = odos3;   //ΦΟΡΟΣ
        Board[5] = odos24;  // ΣΤΑΘΜΟΣ 1
        Board[6] = odos4;
        Board[7] = odos33;  // ΕΝΤΟΛΗ 1
        Board[8] = odos5;
        Board[9] = odos6;

        Board[10] = prison; // ΦΥΛΑΚΗ
        Board[11] = odos7;
        Board[12] = odos28; // ΕΤΑΙΡΙΑ ΗΛΕΚΤΡΙΣΜΟΥ
        Board[13] = odos8;
        Board[14] = odos9;
        Board[15] = odos25; // ΣΤΑΘΜΟΣ 2
        Board[16] = odos10;
        Board[17] = odos31; // ΑΠΟΦΑΣΗ 2
        Board[18] = odos11;
        Board[19] = odos12;

        Board[20] = freePass; // ΕΛΕΥΘΕΡΟΣ
        Board[21] = odos13;
        Board[22] = odos34; // ΕΝΤΟΛΗ 2
        Board[23] = odos14;
        Board[24] = odos15;
        Board[25] = odos26; // ΣΤΑΘΜΟΣ 3
        Board[26] = odos16;
        Board[27] = odos17;
        Board[28] = odos29; // ΕΤΑΙΡΙΑ ΥΔΡΕΥΣΗΣ
        Board[29] = odos18;

        Board[30] = gotoPrison; // ΠΑΕΙ ΦΥΛΑΚΗ
        Board[31] = odos19;
        Board[32] = odos20;
        Board[33] = odos32; // ΑΠΟΦΑΣΗ 3
        Board[34] = odos21;
        Board[35] = odos27; // ΣΤΑΘΜΟΣ 4
        Board[36] = odos35; // ΕΝΤΟΛΗ 3
        Board[37] = odos22;
        Board[38] = odos37; // ΦΟΡΟΣ
        Board[39] = odos23;
    }
    public void StartGame(int sum) {
        if (turn == 1) {
            HandleMovement(player1, sum);
        } else {
            HandleMovement(player2, sum);
        }
    }
    //RESET TA LABELS ARISTERA
    public void LeftLabelReset() {
        rent.setText("");
        doublerent.setText("");
        rent1house.setText("");
        rent2house.setText("");
        rent3house.setText("");
        rent4house.setText("");
        renthotel.setText("");
    }
    //Card sti mesi
    public void CardShow(int newPos) {
        Player currentPlayer = (turn == 1) ? player1 : player2;
        Player nextPlayer = (turn == 2) ? player1 : player2;
        if (!currentPlayer.isPrison()) {
            cardType.setText("Τύπος Κάρτας: " + Board[newPos].getType());
            CardName.setText("");
            switch (Board[newPos].getType()) {
                case "Φόρος" -> {
                    if (Board[newPos] instanceof Cards card){
                        Buying.setText("");
                        System.out.println(ForosIsUp);
                        CardName.setText("Ονομασία Περιοχής: " + Board[newPos].getCardName());
                        LeftLabelReset();
                        CostOfArea.setText("");
                        YesBuy.setDisable(true);
                        NoBuy.setDisable(true);
                        card.DoTax(currentPlayer);
                        ForosIsUp=true;
                        System.out.println(ForosIsUp);
                    }
                }
                case "Οδός" -> {
                    CardName.setText("Ονομασία Περιοχής: " + Board[newPos].getCardName());
                    if (Board[newPos] instanceof Odoi odos) {
                        rent.setText("Ενοίκιο " + odos.getEnoikio());
                        doublerent.setText("Ενοίκιο με όλο το σετ: " + odos.getEnoikio() * 2);
                        rent1house.setText("Ενοίκιο με 1 σπίτι: " + odos.CostOdos(false, 1, CompletedSet(odos.getColor(), currentPlayer)));
                        rent2house.setText("Ενοίκιο με 2 σπίτια: " + odos.CostOdos(false, 2, CompletedSet(odos.getColor(), currentPlayer)));
                        rent3house.setText("Ενοίκιο με 3 σπίτια: " + odos.CostOdos(false, 3, CompletedSet(odos.getColor(), currentPlayer)));
                        rent4house.setText("Ενοίκιο με 4 σπίτια: " + odos.CostOdos(false, 4, CompletedSet(odos.getColor(), currentPlayer)));
                        renthotel.setText("Ενοίκιο με ξενοδοχείο: " + odos.CostOdos(true, 0, CompletedSet(odos.getColor(), currentPlayer)));
                        if (odos.isAgorasmeno()) {
                            Buying.setText("Αγορασμένη Περιοχή");
                            IsBought.setText("Αγορασμένο: Ναι");
                            YesBuy.setDisable(true);
                            NoBuy.setDisable(true);
                            CostOfArea.setText("");
                            if (Belongs(currentPlayer, newPos)) {
                                PayNotPayRent.setText("Η περιοχή σου ανήκει");
                                PayRentBtn.setDisable(true);
                                YesBuy.setDisable(true);
                                NoBuy.setDisable(true);
                            } else {
                                RentIsUp=true;
                                PayNotPayRent.setText("Η περιοχή δεν σου ανήκει, πλήρωσε ενοίκιο!");
                                PayRentBtn.setDisable(false);
                                CostOfRent.setText("Κόστος Ενοικίου: " + odos.CostOdos(odos.isJenodoxeio(), odos.getSpitia(), CompletedSet(odos.getColor(), nextPlayer)));
                            }
                        } else {
                            IsBought.setText("Αγορασμένο: Όχι");
                            Buying.setText("Αγοράζεις?");
                            CostOfArea.setText("Κόστος Περιοχής: " + odos.getTimi());
                            YesBuy.setDisable(false);
                            NoBuy.setDisable(false);
                        }
                    }
                }
                case "Σταθμός" -> {
                    CardName.setText("Ονομασία Περιοχής: " + Board[newPos].getCardName());
                    if (Board[newPos] instanceof Odoi odos) {
                        rent.setText("Ενοίκιο " + odos.CostStathmos(1));
                        rent1house.setText("Ενοίκιο με 2 Σταθμούς " + odos.CostStathmos(2));
                        rent2house.setText("Ενοίκιο με 3 Σταθμούς " + odos.CostStathmos(3));
                        rent3house.setText("Ενοίκιο με 4 Σταθμούς " + odos.CostStathmos(4));
                        doublerent.setText("");
                        renthotel.setText("");
                        rent4house.setText("");
                        if (odos.isAgorasmeno()) {
                            Buying.setText("Αγορασμένη Περιοχή");
                            IsBought.setText("Αγορασμένο: Ναι");
                            YesBuy.setDisable(true);
                            NoBuy.setDisable(true);
                            CostOfArea.setText("");
                            LeftLabelReset();
                            if (Belongs(currentPlayer, newPos)) {
                                PayNotPayRent.setText("Η περιοχή σου ανήκει");
                                PayRentBtn.setDisable(true);
                            } else {
                                RentIsUp=true;
                                PayRentBtn.setDisable(false);
                                PayNotPayRent.setText("Η περιοχή δεν σου ανήκει, πλήρωσε ενοίκιο!");
                                CostOfRent.setText("Κόστος Ενοικίου: " + odos.CostStathmos(nextPlayer.getStathmoi()));
                            }
                        } else {
                            Buying.setText("Αγοράζεις?");
                            IsBought.setText("Αγορασμένο: Όχι");
                            CostOfArea.setText("Κόστος Περιοχής: " + odos.getTimi());
                            NoBuy.setDisable(false);
                            YesBuy.setDisable(false);
                        }
                    }
                }
                case "Εταιρία" -> {
                    CardName.setText("Ονομασία Περιοχής: " + Board[newPos].getCardName());
                    if (Board[newPos] instanceof Odoi odos) {
                        LeftLabelReset();
                        if (odos.isAgorasmeno()) {
                            Buying.setText("Αγορασμένη Περιοχή");
                            IsBought.setText("Αγορασμένο: Ναι");
                            YesBuy.setDisable(true);
                            NoBuy.setDisable(true);
                            if (Belongs(currentPlayer, newPos)) {
                                PayNotPayRent.setText("Η περιοχή σου ανήκει");
                                PayRentBtn.setDisable(true);
                            } else {
                                RentIsUp=true;
                                PayNotPayRent.setText("Η περιοχή δεν σου ανήκει, πλήρωσε ενοίκιο!");
                                CostOfRent.setText("Κόστος Ενοικίου: " + odos.CostEtairies(nextPlayer.getEtairies(), diceSum));
                                PayRentBtn.setDisable(false);
                            }
                        } else {
                            Buying.setText("Αγοράζεις?");
                            IsBought.setText("Αγορασμένο: Όχι");
                            CostOfArea.setText("Κόστος Περιοχής: " + odos.getTimi());
                            NoBuy.setDisable(false);
                            YesBuy.setDisable(false);
                        }
                    }
                }
                case "Σημείο" -> {
                    Buying.setText("");
                    CardName.setText("Ονομασία Περιοχής: " + Board[newPos].getCardName());
                    if (Board[newPos] instanceof StartPos) {
                        LeftLabelReset();
                        CostOfArea.setText("");
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);
                    } else if (Board[newPos] instanceof Prison) {
                        LeftLabelReset();
                        CostOfArea.setText("");
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);
                    } else if (Board[newPos] instanceof GoToPrison) {
                        LeftLabelReset();
                        CostOfArea.setText("");
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Monopoly");
                        alert.setHeaderText("Σε έπιασε η αστυνομία");
                        alert.setContentText("Πήγαινε κατευθείαν στη φυλακή");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();

                        currentPlayer.setPrison(true);
                        currentPlayer.setDiplesZaries(0);
                        currentPlayer.setCurrentPos(10);
                        currentPlayer.getStack().setLayoutY(Board[10].getY());
                        currentPlayer.getStack().setLayoutX(Board[10].getX());
                    } else if (Board[newPos] instanceof FreePass) {
                        LeftLabelReset();
                        CostOfArea.setText("");
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);
                    }
                }
                case "Απόφαση" -> {
                    Buying.setText("");
                    CardName.setText("");
                    LeftLabelReset();
                    if (Board[newPos] instanceof Apofasi) {
                        if (countApofaseis <= 13 && !Apofaseis.isEmpty()) {
                            // Καθαρισμός UI για την εντολή
                            LeftLabelReset();
                            CostOfArea.setText("");
                            NoBuy.setDisable(true);
                            YesBuy.setDisable(true);

                            // Εκτέλεση της επάνω εντολής και μεταφορά στη στοίβα `UsedApofaseis`
                            Apofasi apofasi = Apofaseis.pop();
                            if (!apofasi.isHasTaxMethod()){
                                boolean result = apofasi.Do(currentPlayer);
                                while (!result) {
                                    result = apofasi.Do(currentPlayer);
                                }
                            }else {
                                ForosIsUp=true;
                                apofasi.DoTax(currentPlayer);
                            }
                            UsedApofaseis.push(apofasi);
                            countApofaseis++;
                        } else if (!UsedApofaseis.isEmpty()) {
                            // Αρχίζουμε να τραβάμε εντολές από το `UsedApofaseis` όταν ο κύκλος συμπληρωθεί
                            LeftLabelReset();
                            CostOfArea.setText("");
                            NoBuy.setDisable(true);
                            YesBuy.setDisable(true);

                            // Εκτέλεση της εντολής από `UsedApofaseis` και επαναφορά στην `Apofaseis`
                            Apofasi apofasi = UsedApofaseis.pop();
                            if (!apofasi.isHasTaxMethod()){
                                boolean result = apofasi.Do(currentPlayer);
                                while (!result) {
                                    result = apofasi.Do(currentPlayer);
                                }
                            }else {
                                ForosIsUp=true;
                                apofasi.DoTax(currentPlayer);
                            }
                            Apofaseis.push(apofasi);
                            // Μείωση μετρητή για να ξεκινάει ξανά ο κύκλος όταν εξαντληθούν οι εντολές
                            if (UsedApofaseis.isEmpty()) {
                                countApofaseis = 0;
                            }
                        }
                    }
                }
                case "Εντολή" -> {
                    Buying.setText("");
                    CardName.setText("");
                    if (Board[newPos] instanceof Entoli) {
                        if (countEntoles <= 13 && !Entoles.isEmpty()) {
                            // Καθαρισμός UI για την εντολή
                            LeftLabelReset();
                            CostOfArea.setText("");
                            NoBuy.setDisable(true);
                            YesBuy.setDisable(true);

                            // Εκτέλεση της επάνω εντολής και μεταφορά στη στοίβα `UsedEntoles`
                            Entoli entoli = Entoles.pop();
                            boolean result = entoli.Do(currentPlayer);
                            while (!result) {
                                result = entoli.Do(currentPlayer);
                            }
                            UsedEntoles.push(entoli);

                            countEntoles++;
                        } else if (!UsedEntoles.isEmpty()) {
                            // Αρχίζουμε να τραβάμε εντολές από το `UsedEntoles` όταν ο κύκλος συμπληρωθεί
                            LeftLabelReset();
                            CostOfArea.setText("");
                            NoBuy.setDisable(true);
                            YesBuy.setDisable(true);

                            // Εκτέλεση της εντολής από `UsedEntoles` και επαναφορά στη `Entoles`
                            Entoli entoli = UsedEntoles.pop();
                            boolean result = entoli.Do(currentPlayer);
                            while (!result) {
                                result = entoli.Do(currentPlayer);
                            }
                            Entoles.push(entoli);

                            // Μείωση μετρητή για να ξεκινάει ξανά ο κύκλος όταν εξαντληθούν οι εντολές
                            if (UsedEntoles.isEmpty()) {
                                countEntoles = 0;
                            }
                        }
                    }
                }
                case null, default -> {
                    Buying.setText("");
                    CardName.setText("");
                    LeftLabelReset();
                    CostOfArea.setText("");
                    IsBought.setText("");
                    NoBuy.setDisable(true);
                    YesBuy.setDisable(true);
                }
            }
        }
    }
    //HANDLE MOVEMENT
    public void HandleMovement(Player player, int sum) {
        if (!player.isPrison()) {
            MovePlayer(player, sum);
        } else {
            checkPrison(player);
        }
    }
    public void MovePlayer(Player player, int sum) {
        System.out.println(turn );
        int oldPos = player.getCurrentPos();
        newPos = oldPos + sum;
        if (newPos >= 40) {// Αν το νέο pos υπερβαίνει το 39, υπολογιζω το υπόλοιπο για να επανέλθει στην αρχή
            newPos = newPos % 40;
            player.setMoney(player.getMoney() + 200);  // Αν ο παίκτης πέρασε από την αφετηρία, παιρνει € 200
            player.getShowmoney().setText("Χρήματα: " + player.getMoney());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Ενέργεια");

            Text content = new Text("Πέρασες από την εκκίνηση και παίρνεις 200€");
            content.setTextAlignment(TextAlignment.CENTER);
            content.setWrappingWidth(400);
            alert.getDialogPane().setContent(content);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
        }
        player.setCurrentPos(newPos);
        Player nextPlayer = (turn == 2) ? player1 : player2;
        player.getStack().setLayoutX(Board[newPos].getX());
        player.getStack().setLayoutY(Board[newPos].getY());
        if (nextPlayer.getCurrentPos() == player.getCurrentPos()) {
            player.getStack().setLayoutX(Board[newPos].getX()); // Ελαφριά μετατόπιση
            player.getStack().setLayoutY(Board[newPos].getY() + 20);
        }
        System.out.println(Board[newPos].getCardName());
        UIRefresher();
        CardShow(newPos);
    }
    //TI GINETAI AN EINAI STIN FYLAKI
    public void checkPrison(Player player) {
        YesBuy.setDisable(true);
        NoBuy.setDisable(true);
        LeftLabelReset();
        cardType.setText("Σημείο");
        CardName.setText("Φυλακή");
        IsBought.setText("");
        CostOfArea.setText("");
        Buying.setText("");

        if (player == player1) {
            if (CheckPrison1.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Φυλακή");
                alert.setContentText("Θες να χρησιμοποιήσεις την κάρτα εξόδου?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                ButtonType Yes = new ButtonType("Ναι");
                ButtonType No = new ButtonType("Όχι");
                // Προσθήκη των κουμπιών επιλογών στο alert
                alert.getButtonTypes().setAll(Yes, No);

                // Έλεγχος της επιλογής του παίκτη
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == Yes) {
                        player1.setPrison(false);
                        CheckPrison1.setSelected(false);
                        player1.setRounds(0);
                        player1.setCurrentPos(10);
                        MovePlayer(player1, dice1Number + dice2Number);
                    }
                }
            }
        } else {
            if (CheckPrison2.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Φυλακή");
                alert.setContentText("Θες να χρησιμοποιήσεις την κάρτα εξόδου?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                ButtonType Yes = new ButtonType("Ναι");
                ButtonType No = new ButtonType("Όχι");
                // Προσθήκη των κουμπιών επιλογών στο alert
                alert.getButtonTypes().setAll(Yes, No);

                // Έλεγχος της επιλογής του παίκτη
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == Yes) {
                        player2.setPrison(false);
                        CheckPrison2.setSelected(false);
                        player2.setRounds(0);
                        player2.setCurrentPos(10);
                        MovePlayer(player2, dice1Number + dice2Number);
                    }
                }
            }
        }

        if (doubles) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Monopoly");
            alert2.setHeaderText("Έφερες διπλή ζαριά και βγήκες από την φυλακή!");
            Stage stage = (Stage) alert2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert2.showAndWait();
            player.setPrison(false);
            player.setCurrentPos(10);
            player.setRounds(0);
            MovePlayer(player, dice1Number + dice2Number);
        }
        if (player.getRounds() == 0 && player.isPrison()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Φυλακή");
            alert.setContentText("Έχεις άλλους 3 γύρους στη φυλακή");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));

            ButtonType payToExit = new ButtonType("Πλήρωσε 100€  για να βγεις");
            ButtonType waitForTurns = new ButtonType("Περίμενε 3 γύρους");

            // Προσθήκη των κουμπιών επιλογών στο alert
            alert.getButtonTypes().setAll(payToExit, waitForTurns);

            // Έλεγχος της επιλογής του παίκτη
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == payToExit) {
                    if (player.getMoney() >= 100) {
                        player.setMoney(player.getMoney() - 100);
                        player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                        player.setPrison(false);
                        player.setRounds(0);
                        MovePlayer(player, dice1Number + dice2Number);
                    } else {
                        // Ενημέρωση ότι ο παίκτης δεν έχει αρκετά χρήματα
                        Alert noMoneyAlert = new Alert(Alert.AlertType.ERROR);
                        noMoneyAlert.setTitle("Monopoly");
                        noMoneyAlert.setHeaderText("Δεν έχεις αρκετά χρήματα!");
                        noMoneyAlert.setContentText("Χρειάζεσαι 100€  για να πληρώσεις την εγγύηση.");
                        Stage stage2 = (Stage) noMoneyAlert.getDialogPane().getScene().getWindow();
                        stage2.getIcons().add(new Image("monopoly-man.jpg"));
                        noMoneyAlert.showAndWait();
                        player.setRounds(player.getRounds() + 1);

                    }
                } else {
                    player.setRounds(player.getRounds() + 1);
                }
            }
        } else if (player.getRounds() == 1 && player.isPrison()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Φυλακή");
            alert.setContentText("Έχεις άλλους 2 γύρους στη φυλακή");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            ButtonType payToExit = new ButtonType("Πλήρωσε 100€  για να βγεις");
            ButtonType waitForTurns = new ButtonType("Περίμενε 2 γύρους");

            // Προσθήκη των κουμπιών επιλογών στο alert
            alert.getButtonTypes().setAll(payToExit, waitForTurns);

            // Έλεγχος της επιλογής του παίκτη
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == payToExit) {
                    if (player.getMoney() >= 100) {
                        player.setMoney(player.getMoney() - 100);
                        player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                        player.setPrison(false);
                        player.setRounds(0);
                        MovePlayer(player, dice1Number + dice2Number);
                    } else {
                        // Ενημέρωση ότι ο παίκτης δεν έχει αρκετά χρήματα
                        Alert noMoneyAlert = new Alert(Alert.AlertType.ERROR);
                        noMoneyAlert.setTitle("Monopoly");
                        noMoneyAlert.setHeaderText("Δεν έχεις αρκετά χρήματα!");
                        noMoneyAlert.setContentText("Χρειάζεσαι 100€  για να πληρώσεις την εγγύηση.");
                        Stage stage2 = (Stage) noMoneyAlert.getDialogPane().getScene().getWindow();
                        stage2.getIcons().add(new Image("monopoly-man.jpg"));
                        noMoneyAlert.showAndWait();
                        player.setRounds(player.getRounds() + 1);

                    }
                } else {
                    player.setRounds(player.getRounds() + 1);
                }
            }
        } else if (player.getRounds() == 2 && player.isPrison()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Φυλακή");
            alert.setContentText("Έχεις άλλον 1 γύρο στη φυλακή");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            ButtonType payToExit = new ButtonType("Πλήρωσε 100€  για να βγεις");
            ButtonType waitForTurns = new ButtonType("Περίμενε 1 γύρους");
            // Προσθήκη των κουμπιών επιλογών στο alert
            alert.getButtonTypes().setAll(payToExit, waitForTurns);

            // Έλεγχος της επιλογής του παίκτη
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                if (result.get() == payToExit) {
                    if (player.getMoney() >= 100) {
                        player.setMoney(player.getMoney() - 100);
                        player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                        player.setPrison(false);
                        player.setRounds(0);
                        MovePlayer(player, dice1Number + dice2Number);
                    } else {
                        // Ενημέρωση ότι ο παίκτης δεν έχει αρκετά χρήματα
                        Alert noMoneyAlert = new Alert(Alert.AlertType.ERROR);
                        noMoneyAlert.setTitle("Monopoly");
                        noMoneyAlert.setHeaderText("Δεν έχεις αρκετά χρήματα!");
                        noMoneyAlert.setContentText("Χρειάζεσαι 100€  για να πληρώσεις την εγγύηση.");
                        Stage stage2 = (Stage) noMoneyAlert.getDialogPane().getScene().getWindow();
                        stage2.getIcons().add(new Image("monopoly-man.jpg"));
                        noMoneyAlert.showAndWait();
                        player.setRounds(player.getRounds() + 1);

                    }
                } else {
                    player.setRounds(player.getRounds() + 1);
                }
            }
        } else if (player.isPrison()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Φυλακή");
            alert.setHeaderText("Σε αυτόν τον γύρο βγαίνεις απο την φυλακή και πληρώνεις 100€ ");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();

            player.setMoney(player.getMoney() - 100);
            player.setPrison(false);
            player.setRounds(0);
            player.getShowmoney().setText("Χρήματα: " + player.getMoney());
            MovePlayer(player, dice1Number + dice2Number);
        }
    }
    //Buttons no/endturn
    public int FindCurrentPos() {
        if (turn == 1) {
            return player1.getCurrentPos();
        } else return player2.getCurrentPos();
    }
    public void EndOfTurn() {
        if (!endofround) {
            Player currentPlayer = (turn == 1) ? player1 : player2;
            if (!currentPlayer.isPrison()) {
                switch (Board[FindCurrentPos()].getType()) {
                    case "Οδός" -> {
                        if (Board[FindCurrentPos()] instanceof Odoi odos) {
                            if (!odos.isAgorasmeno()) {
                                if (!Buy && !NotBuy) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Monopoly");
                                    alert.setHeaderText("Αποτέλεσμα Ενέργειας!");
                                    alert.setContentText("Πρέπει πρώτα να επιλέξεις αν θα αγοράσεις ή όχι");
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                                    alert.showAndWait();
                                } else {
                                    endofround = true;
                                    YesBuy.setDisable(true);
                                    NoBuy.setDisable(true);
                                    CheckForDoubles();
                                }
                            } else {
                                if (!RentIsUp) {
                                    endofround = true;
                                    YesBuy.setDisable(true);
                                    NoBuy.setDisable(true);
                                    CheckForDoubles();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Monopoly");
                                    alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                                    alert.setContentText("Πρέπει πρώτα να πληρώσεις το ενοίκιο!");
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                                    alert.showAndWait();
                                }
                            }
                        }
                    }
                    case "Εταιρία" ->{
                        if (Board[FindCurrentPos()] instanceof Odoi odos) {
                            if (!odos.isAgorasmeno()) {
                                if (!Buy && !NotBuy) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Monopoly");
                                    alert.setHeaderText("Αποτέλεσμα Ενέργειας!");
                                    alert.setContentText("Πρέπει πρώτα να επιλέξεις αν θα αγοράσεις ή όχι");
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                                    alert.showAndWait();
                                } else {
                                    endofround = true;
                                    YesBuy.setDisable(true);
                                    NoBuy.setDisable(true);
                                    CheckForDoubles();
                                }
                            } else {
                                if (!RentIsUp) {
                                    endofround = true;
                                    YesBuy.setDisable(true);
                                    NoBuy.setDisable(true);
                                    CheckForDoubles();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Monopoly");
                                    alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                                    alert.setContentText("Πρέπει πρώτα να πληρώσεις το ενοίκιο!");
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                                    alert.showAndWait();
                                }
                            }
                        }
                    }
                    case "Σταθμός" -> {
                        if (Board[FindCurrentPos()] instanceof Odoi odos) {
                            if (!odos.isAgorasmeno()) {
                                if (!Buy && !NotBuy) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Monopoly");
                                    alert.setHeaderText("Αποτέλεσμα Ενέργειας!");
                                    alert.setContentText("Πρέπει πρώτα να επιλέξεις αν θα αγοράσεις ή όχι");
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                                    alert.showAndWait();
                                } else {
                                    endofround = true;
                                    YesBuy.setDisable(true);
                                    NoBuy.setDisable(true);
                                    CheckForDoubles();
                                }
                            } else {
                                if (!RentIsUp) {
                                    endofround = true;
                                    YesBuy.setDisable(true);
                                    NoBuy.setDisable(true);
                                    CheckForDoubles();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Monopoly");
                                    alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                                    alert.setContentText("Πρέπει πρώτα να πληρώσεις το ενοίκιο!");
                                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                                    alert.showAndWait();
                                }
                            }
                        }
                    }
                    case null,default ->{
                        if (!ForosIsUp) {
                            endofround = true;
                            YesBuy.setDisable(true);
                            NoBuy.setDisable(true);
                            CheckForDoubles();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Monopoly");
                            alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                            alert.setContentText("Πρέπει πρώτα να πληρώσεις τον φόρο!");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image("monopoly-man.jpg"));
                            alert.showAndWait();
                        }
                    }
                }
            }else{
                endofround = true;
                CheckForDoubles();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Αποτέλεσμα Ενέργειας!");
            alert.setContentText("Δεν μπορείς να τελειώσεις τον γύρο πριν παίξεις!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
        }
    }
    public void CheckForDoubles(){
        if (endofround){
            if (turn == 1) {
                if (doubles && !player1.isPrison()) {
                    if (counter1doubles < 2) {
                        turn = 1; //DIATIRISI TIS SEIRAS
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Monopoly");
                        alert.setHeaderText("Είσαι τυχερός!!");
                        alert.setContentText("Έφερες διπλές ζαριές, και ξανά παίζεις");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();
                        counter1doubles++;
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Monopoly");
                        alert2.setHeaderText("Είσαι ασυνήθιστα τυχερός");
                        alert2.setContentText("Πάνε αμέσως φυλακή για να ηρεμήσεις!");
                        Stage stage = (Stage) alert2.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert2.showAndWait();

                        player1.setCurrentPos(10);
                        player1.getStack().setLayoutX(Board[10].getX());
                        player1.getStack().setLayoutY(Board[10].getY());
                        player1.setPrison(true);
                    }
                } else {
                    turn = 2; //ALLAGI SEIRAS
                    counter1doubles = 0;
                }
            } else {
                if (doubles && !player2.isPrison()) {
                    if (counter2doubles < 2) {
                        turn = 2; //DIATIRISI TIS SEIRAS
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Monopoly");
                        alert.setHeaderText("Είσαι τυχερός!!");
                        alert.setContentText("Έφερες διπλές ζαριές, και ξανά παίζεις");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();
                        counter2doubles++;
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Monopoly");
                        alert2.setHeaderText("Είσαι ασυνήθιστα τυχερός");
                        alert2.setContentText("Πάνε αμέσως φυλακή για να ηρεμήσεις!");
                        Stage stage = (Stage) alert2.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert2.showAndWait();

                        player2.setCurrentPos(10);
                        player2.getStack().setLayoutX(Board[10].getX());
                        player2.getStack().setLayoutY(Board[10].getY());
                        player2.setPrison(true);
                    }
                } else {
                    turn = 1; //ALLAGI SEIRAS
                    counter2doubles = 0;
                }
            }
            Turns.setText("Σειρά του " + turn + "ου Παίκτη");
        }
    }
    public boolean NoBuys() {
        NoBuy.setDisable(true);
        return NotBuy=true;
    }
    //Card se lista
    public void ListShow(Player player, Cards newCard) {
        if (newCard != null && newCard.getCardName() != null) {
            // Προσθήκη της νέας κάρτας στη λίστα καρτών του παίκτη
            List<Cards> sortedCards = Arrays.stream(player.getCards())
                    .filter(Objects::nonNull)  // Φιλτράρει τις μηδενικές τιμές
                    .sorted(Comparator.comparing(Cards::getCardName))  // Ταξινομημένη λίστα βάσει ονόματος κάρτας
                    .toList();

            // Καθαρισμός της λίστας GUI και προσθήκη ταξινομημένων καρτών
            player.getList().getItems().clear();
            for (Cards card : sortedCards) {
                player.getList().getItems().add(card.getCardName());
            }

            player.getList().setCellFactory(listView -> new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);

                        Cards associatedCard = sortedCards.stream()
                                .filter(c -> item.equals(c.getCardName()))
                                .findFirst()
                                .orElse(null);

                        if (associatedCard instanceof Odoi odos) {
                            String color = odos.getColor();
                            switch (color) {
                                case "Brown":
                                    setStyle("-fx-background-color: #D2B48C;");
                                    break;
                                case "Green":
                                    setStyle("-fx-background-color: #2ecc71;");
                                    break;
                                case "Blue":
                                    setStyle("-fx-background-color: #3498db;");
                                    break;
                                case "Cyan":
                                    setStyle("-fx-background-color: #85c1e9;");
                                    break;
                                case "Pink":
                                    setStyle("-fx-background-color: #d2b4de;");
                                    break;
                                case "Yellow":
                                    setStyle("-fx-background-color: #f4d03f;");
                                    break;
                                case "Orange":
                                    setStyle("-fx-background-color: #dc7633;");
                                    break;
                                case "Red":
                                    setStyle("-fx-background-color: #e74c3c;");
                                    break;
                                case "White":
                                    setStyle("-fx-background-color: #ecf0f1;");
                                    break;
                                case "Grey":
                                    setStyle("-fx-background-color: #b2babb;");
                                    break;
                                default:
                                    setStyle("");
                                    break;
                            }
                        } else {
                            setStyle("");
                        }
                    }
                }
            });
        }
    }
    //HANDLE PLIRWMES ENOIKIWN
    @FXML
    public void PlirwseEnoikio(ActionEvent event) {
        boolean plirwmi;
        if (turn == 1) {
            PayRentBtn.setDisable(false);
            plirwmi = ConfirmPayment(player1, player2, newPos);
            if (plirwmi) {
                RentIsUp=false;
            } else {
                CheckMoney(player1);
            }
        } else {
            PayRentBtn.setDisable(false);
            plirwmi = ConfirmPayment(player2, player1, newPos);
            if (plirwmi) {
                RentIsUp=false;
            } else {
                CheckMoney(player2);
            }
        }
    }
    public boolean ConfirmPayment(Player Enoikiastis, Player Afentiko, int newPos) {
        switch (Board[newPos].getType()) {
            case "Οδός" -> {
                if (Board[newPos] instanceof Odoi odos) {
                    if (Enoikiastis.getMoney() - odos.getEnoikio() > 0) {
                        Enoikiastis.setMoney(Enoikiastis.getMoney() - odos.getEnoikio());
                        Afentiko.setMoney(Afentiko.getMoney() + odos.getEnoikio());
                        player1.getShowmoney().setText("Χρήματα: " + player1.getMoney());
                        player2.getShowmoney().setText("Χρήματα: " + player2.getMoney());
                        PayRentBtn.setDisable(true);
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);
                        return true;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Πληρωμή Ενοικίου");
                        alert.setHeaderText("Αποτέλεσμα Κάρτας");
                        alert.setContentText("Δεν έχεις αρκετά χρήματα για να πληρώσεις το Ενοίκιο!");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();
                        return false;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Monopoly");
                    alert.setHeaderText("Αποτέλεσμα Κάρτας");
                    alert.setContentText("Λάθος Ενέργεια");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                    alert.showAndWait();
                    return false;
                }
            }
            case "Σταθμός" -> {
                if (Board[newPos] instanceof Odoi odos) {
                    if (Enoikiastis.getMoney() - odos.CostStathmos(Afentiko.getStathmoi()) > 0) {
                        Enoikiastis.setMoney(Enoikiastis.getMoney() - odos.CostStathmos(Afentiko.getStathmoi()));
                        Afentiko.setMoney(Afentiko.getMoney() + odos.CostStathmos(Afentiko.getStathmoi()));
                        player1.getShowmoney().setText("Χρήματα: " + player1.getMoney());
                        player2.getShowmoney().setText("Χρήματα: " + player2.getMoney());
                        PayRentBtn.setDisable(true);
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);
                        return true;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Πληρωμή Ενοικίου");
                        alert.setHeaderText("Αποτέλεσμα Κάρτας");
                        alert.setContentText("Δεν έχεις αρκετά χρήματα για να πληρώσεις το Ενοίκιο!");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();
                        return false;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Monopoly");
                    alert.setHeaderText("Αποτέλεσμα Κάρτας");
                    alert.setContentText("Λάθος Ενέργεια");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                    alert.showAndWait();
                    return false;
                }
            }
            case "Εταιρία" -> {
                if (Board[newPos] instanceof Odoi odos) {
                    if (Enoikiastis.getMoney() - odos.CostEtairies(diceSum, Afentiko.getEtairies()) > 0) {
                        Enoikiastis.setMoney(Enoikiastis.getMoney() - odos.CostEtairies(Afentiko.getEtairies(), diceSum));
                        Afentiko.setMoney(Afentiko.getMoney() + odos.CostEtairies(Afentiko.getEtairies(), diceSum));
                        player1.getShowmoney().setText("Χρήματα: " + player1.getMoney());
                        player2.getShowmoney().setText("Χρήματα: " + player2.getMoney());
                        PayRentBtn.setDisable(true);
                        NoBuy.setDisable(true);
                        YesBuy.setDisable(true);
                        return true;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Πληρωμή Ενοικίου");
                        alert.setHeaderText("Αποτέλεσμα Κάρτας");
                        alert.setContentText("Δεν έχεις αρκετά χρήματα για να πληρώσεις το Ενοίκιο!");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();
                        return false;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Monopoly");
                    alert.setHeaderText("Αποτέλεσμα Κάρτας");
                    alert.setContentText("Λάθος Ενέργεια");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                    alert.showAndWait();
                    return false;
                }
            }
        }
        return false;
    }
    //ELEGXOS AN YPARXEI STIN LISTA AGORWN
    public boolean Belongs(Player player, int newPos) {
        if (Board[newPos] instanceof Odoi odos && player.getCards() != null) {
            for (Cards playerCard : player.getCards()) {
                if (playerCard != null && playerCard.getCardName().equals(odos.getCardName())) {
                    return true;
                }
            }
        }
        return false;
    }
    //HANDLE BUY PERIOXI
    public boolean BuyOdos(Player player, int newPos, ActionEvent event) {
        if (Board[newPos] instanceof Odoi odoi) {
            if (!odoi.isAgorasmeno()) {
                if (player.getMoney() - odoi.getTimi() >= 0) {
                    player.addCard(odoi);
                    player.setMoney(player.getMoney() - odoi.getTimi());
                    odoi.setAgorasmeno(true);
                    ListShow(player, odoi); // Προσθήκη της νέας κάρτας στη λίστα
                    if (Objects.equals(Board[newPos].getType(), "Σταθμός")) {
                        player.setStathmoi(player.getStathmoi() + 1);
                    }
                    if (Objects.equals(Board[newPos].getType(), "Εταιρία")) {
                        player.setEtairies(player.getEtairies() + 1);
                    }
                    odoi.setOwner(player.getName());
                    IsBought.setText("Αγορασμένο: Ναι");
                    YesBuy.setDisable(true);
                    NoBuy.setDisable(true);
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Αγοραπωλησία");
                    alert.setHeaderText("Αποτέλεσμα Κάρτας");
                    alert.setContentText("Δεν έχεις αρκετά χρήματα για να αγοράσεις την περιοχή!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                    alert.showAndWait();
                    return false;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Αγοραπωλησία");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");
                alert.setContentText("Η περιοχή είναι ήδη αγορασμένη");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
                return false;
            }
        } else
            return false;
    }
    @FXML
    private void handleYesBuy(ActionEvent event) {
        boolean isBought;
        if (turn == 1) {
            isBought = BuyOdos(player1, newPos, event);
            if (isBought) {
                Buy=true;
                money1.setText("Χρήματα:" + player1.getMoney());
            }
            player1.ListSorted();
        } else {
            isBought = BuyOdos(player2, newPos, event);
            if (isBought) {
                Buy=true;
                money2.setText("Χρήματα:" + player2.getMoney());
            }
            player2.ListSorted();
        }
    }
    //refresh ui
    public void UIRefresher() {
        rent.setText("");
        renthotel.setText("");
        rent1house.setText("");
        rent4house.setText("");
        rent2house.setText("");
        rent3house.setText("");
        doublerent.setText("");
        IsBought.setText("");
        CostOfArea.setText("");
        PayNotPayRent.setText("");
        CostOfRent.setText("");
        DisableButtons();
    }
    //HANDLE BUY HOUSES
    public void HandleBuyHouse(Player player, String selectedCardName, ActionEvent event) {

        Cards selectedCard = Arrays.stream(player.getCards())
                .filter(card -> card.getCardName().equals(selectedCardName))
                .findFirst()
                .orElse(null);

        if (selectedCard instanceof Odoi odos) {
            BuyHouse(player, odos, event);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Αγορά Σπιτιού");
            alert.setHeaderText("Αποτέλεσμα Κάρτας");
            alert.setContentText("Δεν έχεις επιλέξει κατάλληλη κάρτα για την αγορά σπιτιού.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
        }
    }
    public void BuyHouse(Player player, Cards card, ActionEvent event) {
        if (card instanceof Odoi odos) {
            if (odos.getSpitia() < 4) {
                if (player.getMoney() - odos.getTimiSpiti() > 0) {
                    odos.setSpitia(odos.getSpitia() + 1);
                    player.setMoney(player.getMoney() - odos.getTimiSpiti());
                    player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                    player.setSynoloSpitiwn(player.getSynoloSpitiwn() + 1);
                    if (player == player2) {
                        Houses2.setText("Πλήθος Σπιτιών: " + odos.getSpitia());
                    } else {
                        Houses1.setText("Πλήθος Σπιτιών: " + odos.getSpitia());
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Αγορά Σπιτιού");
                    alert.setHeaderText("Αποτέλεσμα Κάρτας");
                    alert.setContentText("Δεν έχεις αρκετά χρήματα για να αγοράσεις σπίτι σε αυτήν την περιοχή!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Αγορά Σπιτιού");
                alert.setHeaderText("Αποτέλεσμα Κάρτας");
                alert.setContentText("Μπορείς να αγοράσεις μέχρι 4 σπίτια σε κάθε περιοχή!");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }

        }
    }
    //CHECK FOR SET
    public boolean CompletedSet(String color, Player player) {
        int requiredCards;

        // Determine the required number of cards for the color set
        if (color.equalsIgnoreCase("Brown") || color.equalsIgnoreCase("Blue")) {
            requiredCards = 2;
        } else {
            requiredCards = 3;
        }
        // Count the number of cards of the specified color
        int colorCount = 0;
        for (Cards card : player.getCards()) {
            if (card instanceof Odoi odos) {
                if (odos.getColor().equalsIgnoreCase(color)) {
                    colorCount++;
                }
            }
        }
        // Check if the player has the required number of cards for the set
        return colorCount >= requiredCards;
    }
    //HANDLE BUY HOTELS
    public void HandleBuyHotel(Player player, String selectedCardName, ActionEvent event) {
        Cards selectedCard = Arrays.stream(player.getCards())
                .filter(card -> card.getCardName().equals(selectedCardName))
                .findFirst()
                .orElse(null);

        if (selectedCard instanceof Odoi odos) {
            BuyHotel(player, odos, event);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Αγορά Σπιτιού");
            alert.setHeaderText("Αποτέλεσμα Κάρτας");
            alert.setContentText("Δεν έχεις επιλέξει κατάλληλη κάρτα για την αγορά σπιτιού.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
        }
    }
    public void BuyHotel(Player player, Cards card, ActionEvent event) {
        if (card instanceof Odoi odos) {
            if (!odos.isJenodoxeio()) {

                if (odos.getSpitia() == 4) {
                    if (player.getMoney() - odos.getTimiSpiti() > 0) {
                        odos.setJenodoxeio(true);
                        odos.setSpitia(odos.getSpitia() + 1);
                        player.setMoney(player.getMoney() - odos.getTimiSpiti());
                        player.getShowmoney().setText("Χρήματα: " + player.getMoney());
                        player.setSynoloJenodoxeiwn(player.getSynoloJenodoxeiwn() + 1);
                        if (player == player2) {
                            Houses2.setText("Ξενοδοχείο: Ναι");
                        } else {
                            Houses1.setText("Ξενοδοχείο: Ναι");
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Αγορά Ξενοδοχείου");
                        alert.setHeaderText("Αποτέλεσμα Κάρτας");
                        alert.setContentText("Δεν έχεις αρκετά χρήματα για να αγοράσεις Ξενοδοχείο σε αυτήν την περιοχή!");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("monopoly-man.jpg"));
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Αγορά Σπιτιού");
                    alert.setHeaderText("Αποτέλεσμα Κάρτας");
                    alert.setContentText("Πρέπει πρώτα να αγοράσεις 4 σπίτια σε αυτήν την περιοχή!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("monopoly-man.jpg"));
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Αγορά Σπιτιού");
            alert.setHeaderText("Αποτέλεσμα Ενέργειας");
            alert.setContentText("Έχεις αγοράσει ήδη ξενοδοχείο σε αυτή την περιοχή!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
        }
    }
    //SELL PRISON CARD
    public void SellPrisonCard(Player player, ActionEvent event) {
        if (player == player1) {
            if (CheckPrison1.isSelected() && !CheckPrison2.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                alert.setContentText("Θες να πουλήσεις την κάρτα εξόδου?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));

                ButtonType Yes = new ButtonType("Ναι");
                ButtonType No = new ButtonType("Όχι");
                // Προσθήκη των κουμπιών επιλογών στο alert
                alert.getButtonTypes().setAll(Yes, No);

                // Έλεγχος της επιλογής του παίκτη
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == Yes) {
                        if (player2.getMoney() - 80 >= 0) {
                            CheckPrison2.setSelected(true);
                            CheckPrison1.setSelected(false);
                            player2.setMoney(player2.getMoney() - 80);
                            player1.setMoney(player2.getMoney() + 80);
                            player1.getShowmoney().setText("Χρήματα: " + player1.getMoney());
                            player2.getShowmoney().setText("Χρήματα: " + player2.getMoney());

                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Monopoly");
                            alert2.setHeaderText("Αποτέλεσμα Ενέργειας");
                            alert2.setContentText("Πούλησες την κάρτα εξόδου σου για 80€  στον αντίπαλο.");
                            Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                            stage2.getIcons().add(new Image("monopoly-man.jpg"));

                            ButtonType Cont = new ButtonType("Οκ");
                            alert.getButtonTypes().setAll(Cont);
                        }
                    }
                }
            } else if (!CheckPrison1.isSelected()) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Monopoly");
                alert2.setHeaderText("Αποτέλεσμα Ενέργειας");
                alert2.setContentText("Δεν έχεις κάρτα εξόδου από την φυλακή.");
                Stage stage = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert2.showAndWait();
            } else if (CheckPrison2.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                alert.setContentText("Ο αντίπαλος παίκτης έχει ήδη μια κάρτα εξόδου από την φυλακή");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        } else {
            if (CheckPrison2.isSelected() && !CheckPrison1.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                alert.setContentText("Θες να πουλήσεις την κάρτα εξόδου?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));

                ButtonType Yes = new ButtonType("Ναι");
                ButtonType No = new ButtonType("Όχι");
                // Προσθήκη των κουμπιών επιλογών στο alert
                alert.getButtonTypes().setAll(Yes, No);

                // Έλεγχος της επιλογής του παίκτη
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == Yes) {
                        if (player1.getMoney() - 80 >= 0) {
                            CheckPrison1.setSelected(true);
                            CheckPrison2.setSelected(false);
                            player1.setMoney(player2.getMoney() - 80);
                            player2.setMoney(player2.getMoney() + 80);
                            player1.getShowmoney().setText("Χρήματα: " + player1.getMoney());
                            player2.getShowmoney().setText("Χρήματα: " + player2.getMoney());

                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Monopoly");
                            alert2.setHeaderText("Αποτέλεσμα Ενέργειας");
                            alert2.setContentText("Πούλησες την κάρτα εξόδου σου για 80€  στον αντίπαλο.");
                            Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                            stage2.getIcons().add(new Image("monopoly-man.jpg"));

                            ButtonType Cont = new ButtonType("Οκ");
                            // Προσθήκη των κουμπιών επιλογών στο alert
                            alert.getButtonTypes().setAll(Cont);
                        }
                    }
                }
            } else if (!CheckPrison2.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                alert.setContentText("Δεν έχεις κάρτα εξόδου από την φυλακή.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            } else if (CheckPrison1.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Monopoly");
                alert.setHeaderText("Αποτέλεσμα Ενέργειας");
                alert.setContentText("Ο αντίπαλος παίκτης έχει ήδη μια κάρτα εξόδου από την φυλακή");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("monopoly-man.jpg"));
                alert.showAndWait();
            }
        }
    }
    public void SellRegionCard(Player player, Cards selectedCard, ActionEvent event) {
        if (selectedCard instanceof Odoi odos) {
            // Δημιουργία του alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Monopoly");
            alert.setHeaderText("Αποτέλεσμα Ενέργειας");

            Text content = new Text("Έχεις επιλέξει να πουλήσεις την Οδό: " + odos.getCardName() +
                    " για " + ((odos.getTimi() / 2) + odos.getSpitia()) + "€. Θες να συνεχίσεις με τη διαδικασία;");
            content.setTextAlignment(TextAlignment.CENTER);
            content.setWrappingWidth(400);
            alert.getDialogPane().setContent(content);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            ButtonType Yes = new ButtonType("Ναι");
            ButtonType No = new ButtonType("Όχι");
            alert.getButtonTypes().setAll(Yes, No);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == Yes) {
                // Αφαίρεση της κάρτας από τον παίκτη
                player.removeCard(selectedCard);
                player.setMoney(player.getMoney() + (odos.getTimi() / 2) + odos.getSpitia());

                // Ενημέρωση λίστας και γραφικών
                player.ListSorted();
                player.getShowmoney().setText("Χρήματα: " + player.getMoney());

                resetOdosState(odos);
            }
        }
    }
    private void resetOdosState(Odoi odos) {
        odos.setSpitia(0);
        odos.setJenodoxeio(false);
        odos.setAgorasmeno(false);
        odos.setCompletedSet(false);
        odos.setOwner("");
    }
    public void EndGame(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Monopoly");
        alert.setHeaderText("Αποτέλεσμα Ενέργειας");
        Text content = new Text("Είσαι σίγουρος πως θες να τερματίσεις το παιχνίδι? " +
                "\n" + "Νικητής θα βγει ο παίκτης με τα περισσότερα περιουσιακά στοιχεία!");
        content.setTextAlignment(TextAlignment.CENTER);
        content.setWrappingWidth(400);
        alert.getDialogPane().setContent(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("monopoly-man.jpg"));
        ButtonType Yes = new ButtonType("Ναι");
        ButtonType No = new ButtonType("Όχι");
        // Προσθήκη των κουμπιών επιλογών στο alert
        alert.getButtonTypes().setAll(Yes, No);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == Yes) {
                GetNewScene();
            }
        }
    }
    public void CheckMoney(Player player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Monopoly");

        if (player == player1 && !Agores1.getItems().isEmpty() || player != player1 && !Agores2.getItems().isEmpty()) {
            alert.setHeaderText("Αποτέλεσμα Ενέργειας");
            Text content = new Text("Πούλησε κάποια από τις περιοχές σου για να πάρεις περισσότερα χρήματα!");
            content.setTextAlignment(TextAlignment.CENTER);
            content.setWrappingWidth(400);
            alert.getDialogPane().setContent(content);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            PayRentBtn.setDisable(false);
        } else {
            alert.setHeaderText("Το παιχνίδι τελείωσε!");
            Text content = new Text("Η περιουσία που έχεις δεν φτάνει για να πληρώσεις. Το παιχνίδι τελείωσε!");
            content.setTextAlignment(TextAlignment.CENTER);
            content.setWrappingWidth(400);
            alert.getDialogPane().setContent(content);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("monopoly-man.jpg"));
            alert.showAndWait();
            // Μετάβαση στη νέα σκηνή
            try {
                GetNewScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean processPayment(Player player, int amount) {
        if (player.getMoney() >= amount) {
            player.setMoney(player.getMoney() - amount);
            player.getShowmoney().setText("Χρήματα: " + player.getMoney());
            return true;
        } else {
            CheckMoney(player);
            return false;
        }
    }
    public void GetNewScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Scene3Controller.class.getResource("/com/example/monopolythess/Scene3.fxml"));
        Parent root2 = fxmlLoader.load();

        Scene3Controller scene3Controller = fxmlLoader.getController();
        scene3Controller.ParseParameters(player1, player2);

        // Αλλαγή της σκηνής
        Newstage = (Stage) PayRentBtn.getScene().getWindow(); // Βρίσκουμε το τρέχον stage
        Newscene = new Scene(root2, 1540, 790);
        Newstage.setScene(Newscene);
        Newstage.show();
    }
    public boolean ConfirmPayTax(Player player, int pos,int tax) {
        if (pos == 4) {
            return processPayment(player, tax);
        } else if (pos == 38) {
            return processPayment(player, tax);
        }else{
            return processPayment(player, tax);
        }
    }
}