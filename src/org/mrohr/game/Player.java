package org.mrohr.game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends LivingEntity implements KeyListener {
    private final float SPEED = 2f;
    public Player(int x, int y){
        super(new Rectangle(x,y,32,32),true,100);
        this.setDebugColor(Color.pink);
    }

    @Override
    public void onDamaged(int amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onHealed(int amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDeath() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCollision(CollidableEntity other) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        //super.init(gameContainer);
        gameContainer.getInput().addKeyListener(this);

    }

    public void keyPressed(int key,char c){
        System.out.println("pressed");
        float currentX = this.xspeed;
        float currentY = this.yspeed;
        if(key ==Input.KEY_W){
            currentY = -SPEED;
        }
        if(key ==Input.KEY_S){
            currentY = SPEED;
        }
        if(key ==Input.KEY_A){
            currentX = -SPEED;
        }
        if(key ==Input.KEY_D){
            currentX = SPEED;
        }
        setSpeed(currentX,currentY);
    }

    public void keyReleased(int key,char c){
        float currentX = this.xspeed;
        float currentY = this.yspeed;
        if(key ==Input.KEY_W){
            currentY = 0;
        }
        if(key ==Input.KEY_S){
            currentY = 0;
        }
        if(key ==Input.KEY_A){
            currentX = 0;
        }
        if(key ==Input.KEY_D){
            currentX = 0;
        }
        setSpeed(currentX,currentY);
    }
    public void setInput(Input input) {
    }
    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    /**
     * unused
     */
    @Override
    public void inputEnded() {
    }

    /**
     * unused
     */
    @Override
    public void inputStarted() {
    }
}
