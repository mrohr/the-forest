package org.mrohr.game.entities.items;

import org.mrohr.game.MyGameContainer;
import org.mrohr.game.entities.Block;
import org.mrohr.game.entities.CollidableEntity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/16/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Key extends Item{


    public enum KeyColor{
        RED, BLUE, YELLOW, ORANGE
    }
    private KeyColor color;
    private SpriteSheet sheet;
    public Key(int x, int y, KeyColor color,SpriteSheet sheetRef){
        //this.sheet = sheetRef;
        super(new Rectangle(x, y, Block.width, Block.height), colorToImage(color,sheetRef));
        this.color = color;
        this.sheet = sheetRef;

    }

    public KeyColor getColor(){
        return color;
    }

    public void setColor(KeyColor color){
        this.color = color;
    }

    public Image lockImage(){
        switch(color){
            case RED:
                return sheet.getSubImage(3,18);
            case BLUE:
                return sheet.getSubImage(5,18);
            case YELLOW:
                return sheet.getSubImage(7,18);
            case ORANGE:
                return sheet.getSubImage(7,17);
        }
        return null;
    }
    private static Image colorToImage(KeyColor color,SpriteSheet sheet){
        switch(color){
            case RED:
                return sheet.getSubImage(2,18);
            case BLUE:
                return sheet.getSubImage(4,18);
            case YELLOW:
                return sheet.getSubImage(6,18);
            case ORANGE:
                return sheet.getSubImage(6,17);
        }
        return null;

    }
    public void init(MyGameContainer gameContainer) throws SlickException {
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void onCollision(CollidableEntity other) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

