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
    protected float prevX;
    protected float prevY;
    protected float heading;//0-360, degree that this object is facing. 0 north, 90 east, 180 south, 270 west
    protected Shape previousBB;



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
    }

    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        prevX = this.boundingBox.getX();
        prevY = this.boundingBox.getY();
        float newX = this.boundingBox.getX() + xspeed;
        float newY = this.boundingBox.getY() + yspeed;
        this.boundingBox.setX(newX);
        this.boundingBox.setY(newY);
    }

    public void render(MyGameContainer gc, Graphics graphics) throws SlickException {
        graphics.rotate(this.boundingBox.getCenterX(),this.boundingBox.getCenterY(),heading);
        super.render(gc,graphics);
        graphics.rotate(this.boundingBox.getCenterX(),this.boundingBox.getCenterY(),-heading);
    }

    public void onCollision(CollidableEntity other){
        System.out.println(this.solid);
        System.out.println(other.solid);
        if(this.solid && other.solid){
            System.out.println("Correcting");
            this.boundingBox.setX(prevX);
            this.boundingBox.setY(prevY);
        }
    }
    public void correctPosition(){

    }
}
