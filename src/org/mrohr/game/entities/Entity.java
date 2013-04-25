package org.mrohr.game.entities;

import org.mrohr.game.GameObject;
import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity extends GameObject {

    private Shape boundingBox;
    private Image img;
    private Image renderedImage;
    private Color debugColor;


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
    public void render(MyGameContainer gc, Graphics graphics) throws SlickException {
        if(gc.debug){
            graphics.setColor(debugColor);
            graphics.fill(boundingBox);
        }
        else if(renderedImage != null){
            graphics.drawImage(renderedImage,boundingBox.getX(),boundingBox.getY());
        }

    }

    public Shape getBoundingBox() {
        return boundingBox;
    }

    public void setX(float x){
        boundingBox.setX(x);
    }

    public void setY(float y){
        boundingBox.setY(y);
    }

    public void setCenterX(float x){
        boundingBox.setCenterX(x);
    }

    public void setCenterY(float y){
        boundingBox.setCenterY(y);
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
        this.renderedImage = img.getScaledCopy((int)boundingBox.getHeight(),(int)boundingBox.getWidth());
    }

    public Image getRenderedImage() {
        return renderedImage;
    }

}
