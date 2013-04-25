package org.mrohr.game.entities;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/18/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class EntityFollower extends MoveableEntity{
    private MoveableEntity poi;
    private float movementHeading;
    public float SPEED = 0f;

    public EntityFollower(Shape bb, Image img, boolean solid,MoveableEntity poi){
        super(bb,img,solid);
        this.poi = poi;
    }

    public EntityFollower(Shape bb, boolean solid,MoveableEntity poi){
        super(bb,solid);
        this.poi = poi;
    }

    public float getMovementHeading(){
        return movementHeading;
    }

    public void setMovementHeading(float mh){
        this.movementHeading = mh;
    }

    public void setPoi(MoveableEntity entity){
        this.poi = entity;
    }

    public MoveableEntity getPoi(){
        return poi;
    }
    public void calcMovementHeading(){
        float newY = getBoundingBox().getCenterY() - poi.getBoundingBox().getCenterY();
        float newX = getBoundingBox().getCenterX() - poi.getBoundingBox().getCenterX();
        double heading = Math.atan2(-newY,newX);
        heading = Math.toRadians(Math.toDegrees(heading) - 90);
        this.movementHeading = (float)heading;
    }
    public void calcSpeeds(){
        float newXSpeed = SPEED * (float)Math.sin(movementHeading);
        float newYSpeed = SPEED * (float)Math.cos(movementHeading);
        this.setSpeed(newXSpeed,newYSpeed);
    }
}
