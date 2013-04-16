package org.mrohr.game;

import org.mrohr.game.entities.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/23/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Camera extends GameObject {
    private Entity poi;
    private Map map;
    Shape cameraBB;

    public void setMap(Map map){
        this.map = map;
    }
    public float getX() {
        return cameraBB.getCenterX();
    }

    public void setX(float x) {
        cameraBB.setCenterX(x);
    }

    public float getY() {
        return cameraBB.getCenterY();
    }

    public void setY(float y) {
        cameraBB.setCenterX(y);
    }

    public Entity getPoi() {
        return poi;
    }

    public void setPoi(Entity poi,Map map) {
        this.poi = poi;

    }

    public Camera(Entity poi){
        this.poi = poi;
        this.map = map;
    }
    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        cameraBB = new Rectangle(0,0,gameContainer.getWidth() / 2,gameContainer.getHeight() / 2);
    }

    public boolean isVisible(Shape thing){
       return cameraBB.intersects(thing);

    }
    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.

        cameraBB.setCenterX(poi.getBoundingBox().getCenterX() - cameraBB.getWidth());
        cameraBB.setCenterY(poi.getBoundingBox().getCenterY() - cameraBB.getHeight());
        if(cameraBB.getCenterX() < 0){
            cameraBB.setCenterX(0);
        }
        if(cameraBB.getCenterY() < 0){
            cameraBB.setCenterY(0);
        }

        float maxX = map.getWidth() * map.tiled.getTileWidth() - cameraBB.getWidth() * 2;
        float maxY = map.getHeight() * map.tiled.getTileHeight() - cameraBB.getHeight() * 2;
        if(cameraBB.getCenterX() > maxX){
            cameraBB.setCenterX(maxX);
        }
        if(cameraBB.getCenterY() > maxY){
            cameraBB.setCenterY(maxY);
        }


    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
