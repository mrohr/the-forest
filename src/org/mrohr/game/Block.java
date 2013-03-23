package org.mrohr.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Block extends MoveableEntity {
    public static final float height = 16;
    public static final float width = 16;

    public Block(float x, float y, Image img){
        super(new Rectangle(x,y,height,width),img,true);
    }
    public Block(float x, float y){
        super(new Rectangle(x,y,height,width),true);
    }

    public void onCollision(CollidableEntity other) {
        //System.out.println("Colliding!");

    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
