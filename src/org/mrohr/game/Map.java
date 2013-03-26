package org.mrohr.game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.Tile;
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
public class Map extends GameObject implements MouseListener {
    TiledMapPlus tiled;
    private int height;
    private int width;
    public Camera cam;
    int mousex;
    int mousey;

    List<Entity> blocks;
    Player player;

    public Map(String tiledFile)throws SlickException{
        tiled = new TiledMapPlus(tiledFile);
        height = tiled.getHeight();
        width = tiled.getWidth();
        blocks = new ArrayList<Entity>();
    }

    public void setPlayer(Player p){
        player = p;
        p.setCurrentMap(this);
        cam = new Camera(player);
        cam.setMap(this);
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
        gameContainer.getInput().addMouseListener(this);
        Layer collisions = tiled.getLayer("Collision");
        for(int i = 0;i<tiled.getWidth();i++){
            for(int k = 0;k<tiled.getHeight();k++){
                String prop = tiled.getTileProperty(collisions.getTileID(i,k),"solid","false");
                System.out.println(prop);
                if(Boolean.parseBoolean(prop)){
                   Block block = new Block(tiled.getTileWidth()*i,tiled.getTileWidth()*k);
                   blocks.add(block);
                }
            }
        }


    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        mousex = gameContainer.getInput().getMouseX() + (int)cam.getX();
        mousey = gameContainer.getInput().getMouseY() + (int)cam.getY();
        player.update(gameContainer,i);
        cam.update(gameContainer,i);
        for(Entity e:blocks){
            e.update(gameContainer,i);
            if(e instanceof CollidableEntity){
                player.testCollision((CollidableEntity)e);
            }
        }


    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        graphics.translate(-cam.getX(),-cam.getY());
        tiled.render(0,0,tiled.getLayerID("Main"));
        tiled.render(0,0,tiled.getLayerID("Under"));
        for(Entity e: blocks){
            e.render(gameContainer,graphics);
        }
        player.render(gameContainer,graphics);
        tiled.render(0,0,tiled.getLayerID("Over"));


        Circle cursor = new Circle(mousex,mousey,5f);
        graphics.setColor(Color.red);
        graphics.draw(cursor);
        graphics.drawLine(cursor.getCenterX() - 10,cursor.getCenterY(),
                cursor.getCenterX() +10,cursor.getCenterY());
        graphics.drawLine(cursor.getCenterX(),cursor.getCenterY() - 10,
                cursor.getCenterX(),cursor.getCenterY() + 10);
    }

    public float calculatePlayerHeading(){
        float dx = player.boundingBox.getX() - mousex;
        float dy = player.boundingBox.getY() - mousey;
        return (float)Math.toRadians(getTargetAngle(player.boundingBox.getX(),player.boundingBox.getY(),mousex,mousey));

    }
    public double getDistanceBetween(float startX, float startY, float endX, float endY) {
        return Math.sqrt((Math.pow((endX - startX), 2)) + (Math.pow((endY - startY), 2)));
    }

    public double getTargetAngle(float startX, float startY, float targetX, float targetY) {
        double dist = getDistanceBetween(startX, startY, targetX, targetY);
        double sinNewAng = (startY - targetY) / dist;
        double cosNewAng = (targetX - startX) / dist;
        double angle = 0;

        if (sinNewAng > 0) {
            if (cosNewAng > 0) {
                angle = 90 - Math.toDegrees(Math.asin(sinNewAng));
            } else {
                angle = Math.toDegrees(Math.asin(sinNewAng)) + 270;
            }
        } else {
            angle = Math.toDegrees(Math.acos(cosNewAng)) + 90;
        }
        return angle;
    }


    @Override
    public void mouseWheelMoved(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseClicked(int i, int i2, int i3, int i4) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(int i, int i2, int i3) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(int i, int i2, int i3) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(int i, int i2, int mx, int my) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public void mouseDragged(int i, int i2, int i3, int i4) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setInput(Input input) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAcceptingInput() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void inputEnded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void inputStarted() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
