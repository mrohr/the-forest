package org.mrohr.game.entities;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
    public Key(int x, int y, KeyColor color,SpriteSheet sheetRef){
        //this.sheet = sheetRef;
        super(new Rectangle(x, y, Block.width, Block.height), colorToImage(color,sheetRef));

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
}
