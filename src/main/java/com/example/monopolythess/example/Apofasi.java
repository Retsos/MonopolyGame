package com.example.monopolythess.example;

public class Apofasi extends Cards{
    private boolean HasTaxMethod;
    public Apofasi(String cardName, String Type,int x,int y) {
        super(cardName, Type,x,y);
    }
    public Apofasi(String cardName, String Type,boolean HasTaxMethod) {
        super(cardName, Type);
        this.HasTaxMethod=HasTaxMethod;
    }

    public void setHasTaxMethod(boolean hasTaxMethod) {HasTaxMethod = hasTaxMethod;}

    public boolean isHasTaxMethod() {return HasTaxMethod;}

    @Override
    public boolean Do(Player player){return  true;}
}
