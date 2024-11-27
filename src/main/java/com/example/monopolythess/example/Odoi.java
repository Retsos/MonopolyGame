package com.example.monopolythess.example;

public class Odoi extends Cards{
    private int Enoikio;
    private int spitia,Timi,TimiSpiti;
    private boolean jenodoxeio,agorasmeno,CompletedSet;
    private String owner,Color;

    public Odoi(){}
    //gia perioxes
    public Odoi(String Cardname,String type,int Timi,int Enoikio,int spitia,boolean jenodoxeio,boolean agorasmeno,int x,int y,String owner,String Color,int TimiSpiti,boolean CompletedSet) {
        super(Cardname,type,x,y);
        this.Timi=Timi;
        this.CompletedSet=CompletedSet;
        this.TimiSpiti=TimiSpiti;
        this.owner=owner;
        this.agorasmeno=agorasmeno;
        this.jenodoxeio=jenodoxeio;
        this.Enoikio=Enoikio;
        this.spitia=spitia;
        this.Color=Color;
    }
    //gia stathmo/etairia
    public Odoi (String Cardname,String type,int Timi,boolean agorasmeno, int x ,int y,String owner,String Color){
        super(Cardname,type,x,y);
        this.agorasmeno=agorasmeno;
        this.Color=Color;
        this.Timi=Timi;
        this.owner=owner;
    }
    public boolean isCompletedSet() {return CompletedSet;}
    public void setCompletedSet(boolean completedSet) {CompletedSet = completedSet;}
    public int getTimiSpiti() {return TimiSpiti;}
    public void setTimiSpiti(int timiSpiti) {TimiSpiti = timiSpiti;}

    public String getColor() {return Color;}
    public void setColor(String color) {Color = color;}
    public String getOwner() {return owner;}
    public void setOwner(String owner) {this.owner = owner;}
    public int getTimi() {
        return Timi;
    }
    public void setTimi(int timi) {
        Timi = timi;
    }
    public void setAgorasmeno(boolean agorasmeno) {this.agorasmeno = agorasmeno;}
    public boolean isAgorasmeno() {
        return agorasmeno;
    }
    public boolean isJenodoxeio() {
        return jenodoxeio;
    }
    public int getEnoikio() {
        return Enoikio;
    }
    public int getSpitia() {
        return spitia;
    }
    public void setEnoikio(int enoikio) {
        Enoikio = enoikio;
    }
    public void setJenodoxeio(boolean jenodoxeio) {
        this.jenodoxeio = jenodoxeio;
    }
    public void setSpitia(int spitia) {
        this.spitia = spitia;
    }
    public int CostOdos(boolean jenodoxeio,int spitia,boolean Set){
        if(jenodoxeio){
            return getEnoikio()*7;
        }else if(spitia==4){
            return getEnoikio()*6;
        }else if(spitia==3){
            return getEnoikio()*5;
        } else if (spitia==2) {
            return getEnoikio()*4;
        }else if (spitia==1)
            return getEnoikio()*3;
        else if (Set)
            return getEnoikio()*2;
        else return getEnoikio();
    }
    public int CostStathmos(int ownedStathmoi){
        if (ownedStathmoi==1){
            return 50;
        }else if(ownedStathmoi==2)
            return 100;
        else if (ownedStathmoi==3)
            return 150;
        else
            return 200;
    }
    public int CostEtairies(int Etairies,int sum){
        if (Etairies==1)
            return  sum*5;
        else
            return sum*10;
    }
}