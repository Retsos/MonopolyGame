package com.example.monopolythess.example;

public class GoToPrison extends Cards{

    public GoToPrison(String cardName, String Type, int x, int y) {
        super(cardName, Type, x, y);
    }

    @Override
    public boolean Do(Player player){
        return true;
    }
}
