package org.mrohr.game.entities;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/9/13
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tree extends CollidableEntity{
    private SpriteSheet sheet;
    private int sheetXOffset = 4;
    private int sheetYOffset = 0;
    private float x;
    private float y;

    public Tree(float x, float y,SpriteSheet tileset){
      super(new Rectangle(x, y, Block.width * 3, Block.height * 4), true);
      this.x = x;
      this.y = y;
      Rectangle collidable = new Rectangle(x+Block.width,y+(Block.height *2) ,Block.width,Block.height);
      this.setCollidableShape(collidable);

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

    @Override
    public void onCollision(CollidableEntity other) {

    }
}
