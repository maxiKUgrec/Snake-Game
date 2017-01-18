package objects;


import max.snakegame.Snakegame;

public class Apple {

    Snakegame main;
    public int posX;
    public int posY;

    public Apple(int startX, int startY){
        posX=startX;
        posY=startY;
    }

    @SuppressWarnings("static-access")

    public void setRandomPos(){
        posX=(int)(Math.random()*main.WIDTH);
        posY=(int)(Math.random()*main.WIDTH);
    }


}
