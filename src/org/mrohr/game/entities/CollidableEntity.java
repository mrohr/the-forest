package org.mrohr.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class CollidableEntity extends Entity {
    private boolean solid;
    private Shape collidableShape;


    public CollidableEntity(Shape bb,boolean solid){
        super(bb);
        collidableShape = bb;
        this.solid = solid;
        this.setDebugColor(new Color(0,255,0,200));
    }


    public CollidableEntity(Shape bb,Image img, boolean solid){
        super(bb,img);
        collidableShape = bb;
        this.solid = solid;
        this.setDebugColor(new Color(0,255,0,200));
    }

    public boolean isSolid(){
        return solid;
    }

    public boolean testCollision(CollidableEntity other){
      if(this.collidableShape.intersects(other.collidableShape)){
          onCollision(other);
          other.onCollision(this);
          return true;
      }
        return false;
    }

    public abstract void onCollision(CollidableEntity other);

    public Shape getCollidableShape() {
        return collidableShape;
    }

    protected void setCollidableShape(Shape shape){
        this.collidableShape = shape;
    }

    public void setX(float x){
        //System.out.println("Setting  " + this.getClass().toString() + " to " + x);
        float prevX = getBoundingBox().getX();
        super.setX(x);
        float newX = getBoundingBox().getX();
        collidableShape.setX(collidableShape.getX() + (newX - prevX));
    }

    public void setY(float y){

        float prevY = getBoundingBox().getY();
        super.setY(y);
        float newY = getBoundingBox().getY();
        collidableShape.setY(collidableShape.getY() + (newY - prevY));
    }

    public void setCenterX(float x){
        float prevX = getBoundingBox().getCenterX();
        super.setX(x);
        float newX = getBoundingBox().getCenterX();
        collidableShape.setCenterX(collidableShape.getCenterX() + (newX - prevX));
    }

    public void setCenterY(float y){
        float prevY = getBoundingBox().getCenterY();
        super.setY(y);
        float newY = getBoundingBox().getCenterY();
        collidableShape.setCenterY(collidableShape.getCenterY() + (newY - prevY));
    }
}
