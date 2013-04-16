package org.mrohr.game.entities;

import org.mrohr.game.Map;
import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/9/13
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class CaveEntrance extends CollidableEntity{
    SpriteSheet sheet;
    float x;
    float y;
    Shape entrance;
    Map map;
    boolean locked;
    Key[] keys;
    int locksLeft;
    Shape[] keysBB;
    public CaveEntrance(float x, float y, SpriteSheet tileset,Map map){
      super(new Rectangle(x, y, Block.width * 7, Block.height * 4), true);
      this.x = x;
      this.y = y;
      this.map = map;
      this.locked = true;
      keys = new Key[0];


      entrance = new Rectangle(x + (Block.width * 3) + (Block.width / 3), y + (Block.height * 4) - (Block.height / 4),Block.width / 4,Block.height / 4);

      sheet = tileset;
    }

    public void setKeys(Key[] keys){
        this.keys = keys;
        locksLeft = keys.length;
        this.keysBB = new Shape[keys.length];
        for(int i=0; i <keys.length;i++){
            keysBB[i] = new Rectangle(x + (Block.width * (1 +(i/2*3) + (i%2))),
                    y + (Block.height * 3),Block.width,Block.height);
        }
    }

    public Key[] getKeys(){
        return keys;
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public boolean testCollision(CollidableEntity other){

        Player player =map.getPlayer();
        for(int i=0;i<keys.length;i++){
            if(keys[i] != null && player.getInventory().contains(keys[i]) &&
                    other.getBoundingBox().intersects(keysBB[i])){
                player.getInventory().remove(keys[i]);
                keys[i] = null;
                keysBB[i] = null;
                locksLeft--;
                if(locksLeft <= 0){
                    locked = false;
                }
            }
        }
        if(entrance.intersects(other.getCollidableShape())){
            if(locked){
                System.out.println("Still Locked!");
                onCollision(other);
                other.onCollision(this);
                return true;
            }else{
                map.finishMap();
                return true;
            }
        }
        return super.testCollision(other);
    }

    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {

        //top of top of dungeon
        graphics.drawImage(sheet.getSubImage(0,0), x, y);
        for(int i = 0; i < 5; i++){
            graphics.drawImage(sheet.getSubImage(1,0),x + (Block.width * (i + 1)),y);
        }
        graphics.drawImage(sheet.getSubImage(2,0),x + (Block.width * 6),y);

        //bottom of top of dungeon
        graphics.drawImage(sheet.getSubImage(0,2), x, y + Block.height);
        for(int i = 0; i < 5; i++){
            graphics.drawImage(sheet.getSubImage(1,2),x + (Block.width * (i + 1)),y + Block.height);
        }
        graphics.drawImage(sheet.getSubImage(2,2),x + (Block.width * 6),y + Block.height);

        //top of side of dungeon
        graphics.drawImage(sheet.getSubImage(0,3),x,y + (Block.height * 2));

        graphics.drawImage(sheet.getSubImage(1,3),x + (Block.width * 1),y + (Block.height * 2));
        graphics.drawImage(sheet.getSubImage(1,3),x + (Block.width * 2),y + (Block.height * 2));

        graphics.drawImage(sheet.getSubImage(3,0),x + (Block.width * 3),y + (Block.height * 2));
        if(locked){
            //draw bars
            graphics.drawImage(sheet.getSubImage(4,0),x + (Block.width * 3),y + (Block.height * 2));
        }

        graphics.drawImage(sheet.getSubImage(1,3),x + (Block.width * 4),y + (Block.height * 2));
        graphics.drawImage(sheet.getSubImage(1,3),x + (Block.width * 5),y + (Block.height * 2));

        graphics.drawImage(sheet.getSubImage(2,3),x + (Block.width * 6),y + (Block.height * 2));

        //bottom of side of dungeon
        graphics.drawImage(sheet.getSubImage(0,5),x,y + (Block.height * 3));

        graphics.drawImage(sheet.getSubImage(1,5),x + (Block.width * 1),y + (Block.height * 3));
        graphics.drawImage(sheet.getSubImage(1,5),x + (Block.width * 2),y + (Block.height * 3));

        graphics.drawImage(sheet.getSubImage(3,1),x + (Block.width * 3),y + (Block.height * 3));
        if(locked){
            //draw bars
            graphics.drawImage(sheet.getSubImage(4,0),x + (Block.width * 3),y + (Block.height * 3));
        }

        graphics.drawImage(sheet.getSubImage(1,5),x + (Block.width * 4),y + (Block.height * 3));
        graphics.drawImage(sheet.getSubImage(1,5),x + (Block.width * 5),y + (Block.height * 3));

        graphics.drawImage(sheet.getSubImage(2,5),x + (Block.width * 6),y + (Block.height * 3));


        //draw locks
        for(int i=0; i<keys.length;i++){
            if(keys[i] != null){
                graphics.drawImage(keys[i].lockImage(),x + (Block.width * (1 +(i/2*3) + (i%2))),y + (Block.height * 3));
            }
        }
        if(gameContainer.debug){
           graphics.setColor(Color.orange);
           graphics.fill(entrance);
        }


    }


    @Override
    public void onCollision(CollidableEntity other) {

    }
}
