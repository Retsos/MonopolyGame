package com.example.monopolythess.example;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.List;

public class Player {
    private int money, currentPos,stathmoi,Etairies;
    private String Name;
    private Cards[] cards;
    private Label showmoney;
    private boolean prison;
    private StackPane Stack;
    private ListView<String> List;
    private int diplesZaries,rounds,SynoloSpitiwn,SynoloJenodoxeiwn;

    public Player(){}
    public Player(int money,String Name,Cards[] cards,StackPane Stack,int currentPos,ListView<String> List,int stathmoi,
                  int Etairies,Label showmoney,boolean prison,int diplesZaries,int rounds, int SynoloSpitiwn,int SynoloJenodoxeiwn){
        this.cards=cards;
        this.stathmoi=stathmoi;
        this.Stack=Stack;
        this.List=List;
        this.rounds=rounds;
        this.diplesZaries=diplesZaries;
        this.prison=prison;
        this.money=money;
        this.Name=Name;
        this.currentPos=currentPos;
        this.Etairies=Etairies;
        this.showmoney=showmoney;
        this.SynoloJenodoxeiwn=SynoloJenodoxeiwn;
        this.SynoloSpitiwn=SynoloSpitiwn;
    }
    public StackPane getStack() {return Stack;}
    public void setSynoloJenodoxeiwn(int synoloJenodoxeiwn) {SynoloJenodoxeiwn = synoloJenodoxeiwn;}
    public void setSynoloSpitiwn(int synoloSpitiwn) {SynoloSpitiwn = synoloSpitiwn;}

    public int getSynoloSpitiwn() {return SynoloSpitiwn;}
    public int getSynoloJenodoxeiwn() {return SynoloJenodoxeiwn;}
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
    public void setDiplesZaries(int diplesZaries) {
        this.diplesZaries = diplesZaries;
    }
    public int getRounds() {
        return rounds;
    }
    public void setPrison(boolean prison) {this.prison = prison;}
    public boolean isPrison() {return prison;}
    public Label getShowmoney() {return showmoney;}
    public ListView<String> getList() {return List;}
    public int getEtairies() {return Etairies;}
    public void setEtairies(int etairies) {Etairies = etairies;}
    public Cards[] getCards() {return cards;}
    public int getCurrentPos() {return currentPos;}
    public void setCurrentPos(int currentPos) {this.currentPos = currentPos;}
    public int getMoney() {return money;}
    public String getName() {return Name;}
    public void setCards(Cards[] cards) {this.cards = cards;}
    public void setMoney(int money) {this.money = money;}
    public int getStathmoi() {return stathmoi;}
    public void setStathmoi(int stathmoi) {this.stathmoi = stathmoi;}
    public void addCard(Cards card) {
        // Έλεγχος αν ο πίνακας είναι γεμάτος και επέκταση αν χρειάζεται
        if (cards == null) {
            cards = new Cards[1];
            cards[0] = card;
        } else {
            Cards[] newCards = new Cards[cards.length + 1];
            System.arraycopy(cards, 0, newCards, 0, cards.length);
            newCards[cards.length] = card;
            cards = newCards;
        }
    }
    public void removeCard(Cards cardToRemove) {
        if (cards == null || cardToRemove == null) {
            return;
        }
        Cards[] newCards = new Cards[cards.length - 1];
        int index = 0;
        boolean cardFound = false;
        for (Cards c : cards) {
            if (c != null && c.equals(cardToRemove) && !cardFound) {
                cardFound = true;
            } else {
                if (index < newCards.length) {
                    newCards[index++] = c;
                }
            }
        }
        if (cardFound) {
            cards = newCards;
        }
    }
    public void ListSorted() {
        // Define the color order we want
        List<String> colorOrder = Arrays.asList("Grey","White","Brown", "Cyan", "Pink", "Orange", "Red", "Yellow", "Green", "Blue");
        Arrays.sort(cards, (card1, card2) -> {
            if (card1 instanceof Odoi && card2 instanceof Odoi) {
                String color1 = ((Odoi) card1).getColor();
                String color2 = ((Odoi) card2).getColor();
                return Integer.compare(colorOrder.indexOf(color1), colorOrder.indexOf(color2));
            } else if (card1 instanceof Odoi) {
                return -1; // card1 should come before non-Odoi cards
            } else if (card2 instanceof Odoi) {
                return 1; // card2 should come before non-Odoi cards
            } else {
                return 0; // Both are non-Odoi, so keep the same order
            }
        });

        List.getItems().clear();
        for (Cards card : cards) {
            if (card instanceof Odoi) {
                List.getItems().add(card.getCardName());
            }
        }
    }
}