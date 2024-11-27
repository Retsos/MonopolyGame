package com.example.monopolythess.example;
public class Cards {
    private String CardName;
    private String Type;
    private int x,y;
    public Cards(){}
    public Cards(String cardName,String Type, int x, int y){
        this.CardName=cardName;
        this.Type=Type;
        this.x=x;
        this.y=y;
    }

    // Constructor with cardName and Type only
    public Cards(String cardName, String Type) {
        this.CardName = cardName;
        this.Type = Type;
    }

    public boolean  Do(Player player){
        return true ;
    }
    public  void DoTax(Player player){
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getCardName() {
        return CardName;
    }
    public String getType() {
        return Type;
    }
}
