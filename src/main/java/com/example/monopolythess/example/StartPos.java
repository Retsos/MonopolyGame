package com.example.monopolythess.example;

public class StartPos extends Cards{


    public StartPos(String cardName, String Type, int x, int y) {
        super(cardName, Type, x, y);
    }
    @Override
    public boolean Do(Player player){
        return true;
    }

}
