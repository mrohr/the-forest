package org.mrohr.game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Entity extends GameObject {
    protected Shape boundingBox;
    protected Image img;
    protected Image renderedImage;
    protected Color debugColor;

    public Entity(Shape bb){
        this.boundingBox = bb;
        img = null;
        renderedImage = null;
        debugColor = new Color(255,0,0,200);
    }

    public Entity(Shape bb, Image img){
        this.boundingBox = bb;
        this.img = img;
        renderedImage = img.getScaledCopy((int)boundingBox.getHeight(),(int)boundingBox.getWidth());
        debugColor = new Color(255,0,0,200);
    }

    public void setDebugColor(Color color){
        debugColor = color;
    }



    @Override
    public void init(MyGameContainer gc) throws SlickException {

    }

    @Override
    public void update(MyGameContainer gc, int i) throws SlickException {

    }

    @Override
    public void render(MyGameContainer gc, Graphics graphics) throws SlickException {
        if(gc.debug){
            graphics.setColor(debugColor);
            graphics.fill(boundingBox);
        }
        if(renderedImage != null){
            System.out.println("HAS RENDERED");
            graphics.drawImage(renderedImage,boundingBox.getX(),boundingBox.getY());
        }

    }
}
