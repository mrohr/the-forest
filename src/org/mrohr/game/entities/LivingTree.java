package org.mrohr.game.entities;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Color;
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
public class LivingTree extends EntityFollower{
    private SpriteSheet sheet;
    private int sheetXOffset = 4;
    private int sheetYOffset = 0;
    private float x;
    private float y;
    private boolean moving = false;
    public LivingTree(float x, float y,SpriteSheet tileset,Player poi){
        super(new Rectangle(x, y, Block.width * 3, Block.height * 4), true,poi);
        this.x = x;
        this.y = y;
        Rectangle collidable = new Rectangle(x+Block.width,y+(Block.height *2) ,Block.width,Block.height);
        this.setCollidableShape(collidable);
        this.SPEED = 0.1f;
        sheet = tileset;
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean facingPoi(){
        MoveableEntity poi = getPoi();
        double heading = -(Math.toDegrees(this.getMovementHeading()) - 180) % 360;
        heading = (heading + 180) %360;
        int diff = 60;
        if(heading > poi.getHeading() - diff &&
           heading < poi.getHeading() + diff){
            return true;
        }else return false;
    }
    public void update(MyGameContainer gameContainer, int i) throws SlickException {

        if(!this.getPoi().flashlightOn  || !facingPoi()){
            calcMovementHeading();
            calcSpeeds();
            moving = true;
        }
        else{
            this.setSpeed(0,0);
            moving = false;
        }
        super.update(gameContainer, i);

        this.x =this.getBoundingBox().getX();
        this.y =this.getBoundingBox().getY();

        this.getCollidableShape().setX(x+Block.width);
        this.getCollidableShape().setY(y+(Block.height *2));
    }
    public void renderTop(MyGameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawImage(sheet.getSubImage(sheetXOffset + 1, sheetYOffset), x + Block.width, y);

        graphics.drawImage(sheet.getSubImage(sheetXOffset ,sheetYOffset + 1),   x+Block.width *0,y+ Block.height);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +1,sheetYOffset + 1), x+Block.width *1,y+ Block.height);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +2,sheetYOffset + 1), x+Block.width *2,y+ Block.height);

        graphics.drawImage(sheet.getSubImage(sheetXOffset ,sheetYOffset + 2),   x+Block.width *0,y+ Block.height *2);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +1,sheetYOffset + 2), x+Block.width *1,y+ Block.height *2);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +2,sheetYOffset + 2), x+Block.width *2,y+ Block.height *2);

        if(moving){
            graphics.drawImage(sheet.getSubImage(6,7),x+Block.width *1,y+ Block.height *2);
        }
    }

    public void renderBottom(MyGameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.drawImage(sheet.getSubImage(sheetXOffset ,sheetYOffset + 3),   x+Block.width *0,y+ Block.height *3);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +1,sheetYOffset + 3), x+Block.width *1,y+ Block.height *3);
        graphics.drawImage(sheet.getSubImage(sheetXOffset +2,sheetYOffset + 3), x+Block.width *2,y+ Block.height *3);

    }

    @Override
    public void onCollision(CollidableEntity other) {
        super.onCollision(other);
    }
}
