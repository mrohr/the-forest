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
    public Shape cameraBB;

    public void setMap(Map map){
        this.map = map;
    }
    public float getX() {
        return cameraBB.getX();
    }

    public void setX(float x) {
        cameraBB.setX(x);
    }

    public float getY() {
        return cameraBB.getY();
    }

    public void setY(float y) {
        cameraBB.setX(y);
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
        cameraBB = new Rectangle(gameContainer.getWidth()/2,gameContainer.getHeight()/2,gameContainer.getWidth(),gameContainer.getHeight());
    }

    public boolean isVisible(Shape thing){
       return cameraBB.intersects(thing);

    }
    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        cameraBB.setCenterX(poi.getBoundingBox().getCenterX());
        cameraBB.setCenterY(poi.getBoundingBox().getCenterY());
        if(cameraBB.getX() < 0){
            cameraBB.setX(0);
        }
        if(cameraBB.getY() < 0){
            cameraBB.setY(0);
        }


        float maxX = map.getWidth() * map.tiled.getTileWidth() - cameraBB.getWidth();
        float maxY = map.getHeight() * map.tiled.getTileHeight() - cameraBB.getHeight();
        if(cameraBB.getX() > maxX){
            cameraBB.setX(maxX);
        }
        if(cameraBB.getY() > maxY){
            cameraBB.setY(maxY);
        }


    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
