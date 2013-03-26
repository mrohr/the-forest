package org.mrohr.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
    private float x;
    private float y;

    public void setMap(Map map){
        this.map = map;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        float camSizeX = gameContainer.getWidth() / 2;
        float camSizeY = gameContainer.getHeight() / 2;
        this.x = poi.boundingBox.getCenterX() - camSizeX;
        this.y = poi.boundingBox.getCenterY() - camSizeY;
        if(this.x < 0){
            this.x = 0;
        }
        if(this.y < 0){
            this.y = 0;
        }

        float maxX = map.getWidth() * map.tiled.getTileWidth() - camSizeX * 2;
        float maxY = map.getHeight() * map.tiled.getTileHeight() - camSizeY * 2;
        if(this.x > maxX){
            this.x = maxX;
        }
        if(this.y > maxY){
            this.y = maxY;
        }

    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
