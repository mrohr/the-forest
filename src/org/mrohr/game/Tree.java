package org.mrohr.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
public class Tree extends GameObject{
    float x;
    float y;
    public Block treeBlock;
    SpriteSheet sheet;
    int sheetXOffset = 4;
    int sheetYOffset = 0;

    public Tree(float x, float y,SpriteSheet tileset){
      this.x = x;
      this.y = y;
      this.treeBlock = new Block(x +(Block.width),y +(Block.height * 2));
      sheet = tileset;
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Shape fullTreeBB(){
        return new Rectangle(x,y,Block.width*3,Block.height*4);
    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void renderTop(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawImage(sheet.getSubImage(sheetXOffset + 1, sheetYOffset), x + Block.width, y);

        graphics.drawImage(sheet.getSubImage(sheetXOffset ,sheetYOffset + 1),   x+Block.width *0,y+ Block.height);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +1,sheetYOffset + 1), x+Block.width *1,y+ Block.height);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +2,sheetYOffset + 1), x+Block.width *2,y+ Block.height);

        graphics.drawImage(sheet.getSubImage(sheetXOffset ,sheetYOffset + 2),   x+Block.width *0,y+ Block.height *2);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +1,sheetYOffset + 2), x+Block.width *1,y+ Block.height *2);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +2,sheetYOffset + 2), x+Block.width *2,y+ Block.height *2);
    }

    public void renderBottom(MyGameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.drawImage(sheet.getSubImage(sheetXOffset ,sheetYOffset + 3),   x+Block.width *0,y+ Block.height *3);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +1,sheetYOffset + 3), x+Block.width *1,y+ Block.height *3);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +2,sheetYOffset + 3), x+Block.width *2,y+ Block.height *3);
    }
}
