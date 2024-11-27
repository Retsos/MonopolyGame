package com.example.monopolythess.example;

public class Entoli extends Cards{
    public Entoli(String cardName, String Type,int x,int y) {
        super(cardName, Type,x,y);
    }
    public Entoli(String cardName, String Type) {
        super(cardName, Type);
    }

    @Override
    public boolean Do(Player player){ return true;}
}
