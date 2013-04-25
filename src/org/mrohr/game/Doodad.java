package org.mrohr.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/9/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Doodad extends GameObject {
    private float x;
    private float y;
    private SpriteSheet sheet;
    private int tileX;
    private int tileY;

    public static final int[] choices = {71,72,73,76,33,34,44,54,35,45,55,26,27,37,47,57};
    public Doodad(float x, float y,SpriteSheet sheet){
        this.x=x;
        this.y=y;
        this.sheet = sheet;
        Random random = new Random();
        int choice = choices[random.nextInt(choices.length)];
        tileX = choice / 10;
        tileY = choice % 10;
    }

    public float getX(){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }

    public float getY(){
        return y;
    }

    public void setY(float y){
        this.y = y;
    }
    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawImage(sheet.getSubImage(tileX,tileY),x,y);
    }
}
