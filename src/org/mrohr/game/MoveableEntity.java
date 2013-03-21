package org.mrohr.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MoveableEntity extends CollidableEntity {
    protected float xspeed;
    protected float yspeed;
    protected float heading;//0-360, degree that this object is facing. 0 north, 90 east, 180 south, 270 west



    public MoveableEntity(Shape bb,boolean solid){
        super(bb,solid);
        xspeed = 0;
        yspeed = 0;
        heading = 0;
    }

    public MoveableEntity(Shape bb,Image img, boolean solid){
        super(bb,img,solid);
    }

    public void setSpeed(float x,float y){
       this.xspeed = x;
       this.yspeed = y;
    }

    public void setHeading(float h){
        this.heading = h;
        if(renderedImage != null){
            renderedImage.setRotation(h);
        }
    }

    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        float newX = this.boundingBox.getX() + xspeed;
        float newY = this.boundingBox.getY() + yspeed;
        this.boundingBox.setX(newX);
        this.boundingBox.setY(newY);
    }

    public void render(MyGameContainer gc, Graphics graphics) throws SlickException {
        if(gc.debug){
            graphics.setColor(debugColor);
            Shape rotatedShape = this.boundingBox;
            if(heading != 0){
                rotatedShape = boundingBox.transform(Transform.createRotateTransform(heading,
                        this.boundingBox.getCenterX(),this.boundingBox.getCenterY()));
            }
            graphics.fill(rotatedShape);
        }
        else if(renderedImage != null){
            graphics.drawImage(renderedImage,boundingBox.getX(),boundingBox.getY());
        }
    }
}
