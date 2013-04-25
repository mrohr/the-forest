package org.mrohr.game.entities;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MoveableEntity extends CollidableEntity {
    private float xspeed;
    private float yspeed;
    private float prevX;
    private float prevY;
    private float heading;//0-360, degree that this object is facing. 0 north, 90 east, 180 south, 270 west



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

    public float getXSpeed(){
        return this.xspeed;
    }

    public float getYSpeed(){
        return this.yspeed;
    }

    public void setHeading(float h){
        this.heading = h;
    }

    public float getHeading(){
        return this.heading;
    }

    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        prevX = this.getBoundingBox().getX();
        prevY = this.getBoundingBox().getY();
        float newX = this.getBoundingBox().getX() + (xspeed * i);
        float newY = this.getBoundingBox().getY() + (yspeed * i);
        this.setX(newX);
        this.setY(newY);
    }

    public void render(MyGameContainer gc, Graphics graphics) throws SlickException {
        graphics.rotate(this.getBoundingBox().getCenterX(),this.getBoundingBox().getCenterY(),heading);
        super.render(gc,graphics);
        graphics.rotate(this.getBoundingBox().getCenterX(),this.getBoundingBox().getCenterY(),-heading);
    }

    public void onCollision(CollidableEntity other){


        if(this.isSolid() && other.isSolid()){

            this.setX(prevX);
            this.setY(prevY);
        }
    }


}
