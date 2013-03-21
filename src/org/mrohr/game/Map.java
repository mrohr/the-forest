package org.mrohr.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMapPlus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Map extends GameObject{
    TiledMapPlus tiled;
    private int height;
    private int width;


    List<Entity> entities;
    Player player;

    public Map(String tiledFile)throws SlickException{
        tiled = new TiledMapPlus(tiledFile);
        height = tiled.getHeight();
        width = tiled.getWidth();
        entities = new ArrayList<Entity>();
    }

    public void addEntity(Entity e){
        entities.add(e);
    }
    public void setPlayer(Player p){
        player = p;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {

        for(Entity e:entities){
            e.update(gameContainer,i);
        }
        player.update(gameContainer,i);

    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        tiled.render(0,0,0);
        for(Entity e: entities){
            e.render(gameContainer,graphics);
        }
        player.render(gameContainer,graphics);
    }
}
