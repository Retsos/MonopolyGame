package com.example.monopolythess.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Scene3Controller {
    @FXML
    private Label Player1,Player2,Money1,Money2,Winner,Regions1,Regions2,Houses1,Houses2,Hotels1,Hotels2,Total1,Total2;
    @FXML
    private ListView<String> Agores1,Agores2;
    public void ParseParameters(Player player1,Player player2){
        Player1.setText("Παίκτης 1: " + player1.getName());
        Player2.setText("Παίκτης 2: " + player2.getName());
        Agores1.getItems().clear();
        Agores2.getItems().clear();
        // Παράδειγμα για ListView στο δεύτερο Controller
        Agores1.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                // Αν το item είναι κενό ή null, καθαρίζουμε το κείμενο και το στυλ
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    // Αναζήτηση για την κάρτα που αντιστοιχεί στο item
                    Cards associatedCard = null;

                    // Αν η player1.getCards() είναι μια λίστα από κάρτες
                    for (Cards card : player1.getCards()) {
                        if (card.getCardName().equals(item)) {
                            associatedCard = card;
                            break;
                        }
                    }

                    // Αν βρούμε την κάρτα, εφαρμόζουμε το χρώμα της
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
                                setStyle(""); // Δεν υπάρχει χρώμα, οπότε το στυλ παραμένει αμετάβλητο
                                break;
                        }
                    } else {
                        setStyle(""); // Αν δεν είναι κάρτα "Odoi", το στυλ παραμένει κενό
                    }
                }
            }
        });

        Agores2.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                // Αν το item είναι κενό ή null, καθαρίζουμε το κείμενο και το στυλ
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    // Αναζήτηση για την κάρτα που αντιστοιχεί στο item
                    Cards associatedCard = null;

                    // Αν η player1.getCards() είναι μια λίστα από κάρτες
                    for (Cards card : player2.getCards()) {
                        if (card.getCardName().equals(item)) {
                            associatedCard = card;
                            break;
                        }
                    }

                    // Αν βρούμε την κάρτα, εφαρμόζουμε το χρώμα της
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
                                setStyle(""); // Δεν υπάρχει χρώμα, οπότε το στυλ παραμένει αμετάβλητο
                                break;
                        }
                    } else {
                        setStyle(""); // Αν δεν είναι κάρτα "Odoi", το στυλ παραμένει κενό
                    }
                }
            }
        });
// Προσθήκη στοιχείων στη λίστα
        for (Cards card : player1.getCards()) {
            if (card instanceof Odoi) {
                Agores1.getItems().add(card.getCardName());
            }
        }

        for (Cards card : player2.getCards()) {
            if (card instanceof Odoi) {
                Agores2.getItems().add(card.getCardName());
            }
        }
        FindWinner(player1,player2);
    }
    public void FindWinner(Player player1,Player player2){
        int sum1 = 0;
        int sum2 = 0;
        int CostHotel1=0,CostHotel2=0,CostHouse1=0,CostHouse2=0,CostRegions1=0,CostRegions2=0;

        for (Cards card : player1.getCards()) {
            if (card instanceof Odoi) {
                CostRegions1=CostRegions1+((Odoi) card).getTimi();
                if (((Odoi) card).isJenodoxeio()){
                    CostHotel1=CostHotel1 +((Odoi) card).getSpitia()*5;
                }
                else if (((Odoi) card).getSpitia()>0){
                    CostHouse1=CostHouse1+((Odoi) card).getSpitia()*((Odoi) card).getTimiSpiti();
                }
            }
        }
        sum1=sum1+CostHotel1+CostHouse1+CostRegions1+player1.getMoney();

        for (Cards card : player2.getCards()) {
            if (card instanceof Odoi) {
                CostRegions2=CostRegions2+((Odoi) card).getTimi();
                if (((Odoi) card).isJenodoxeio()){
                    CostHotel2=CostHotel2 +((Odoi) card).getSpitia()*5;
                }
                else if (((Odoi) card).getSpitia()>0){
                    CostHouse2=CostHouse2+((Odoi) card).getSpitia()*((Odoi) card).getTimiSpiti();
                }
            }
        }
        sum2=sum2+CostHotel2+CostHouse2+CostRegions2+player2.getMoney();

        Regions1.setText("Αξία Περιοχών: "+CostRegions1);
        Houses1.setText("Αξία Σπιτιών: "+CostHouse1);
        Money1.setText("Χρήματα: "+player1.getMoney());
        Hotels1.setText("Αξία Ξενοδοχείων: "+CostHotel1);
        Total1.setText("Αξία Περιουσίας: "+sum1);

        Regions2.setText("Αξία Περιοχών: "+CostRegions2);
        Houses2.setText("Αξία Σπιτιών: "+CostHouse2);
        Money2.setText("Χρήματα: "+player2.getMoney());
        Hotels2.setText("Αξία Ξενοδοχείων: "+CostHotel2);
        Total2.setText("Αξία Περιουσίας: "+sum2);

        Winner.setPrefWidth(400);
        Winner.setWrapText(true);
        if (sum1>sum2){
            Winner.setText("Νικητής είναι ο " +player1.getName() +" με συνολική περιουσία: "+sum1);
        }else if (sum1<sum2){
            Winner.setText("Νικητής είναι ο " +player2.getName()+ " με συνολική περιουσία: "+sum2);
        }else Winner.setText("Απίστευτο, Έχουμε Ισοπαλία!!!");
    }
    public void Exit(ActionEvent event){
        Platform. exit();
    }
}