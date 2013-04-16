package org.mrohr.game.entities;

import org.mrohr.game.Map;
import org.mrohr.game.MyGameContainer;
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
    private final float SPEED = 0.1f;
    private Map currentMap;
    boolean upPressed;
    boolean downPressed;
    boolean leftPressed;
    boolean rightPressed;
    public Player(int x, int y)throws SlickException{
        super(new Rectangle(x,y,32,32),new Image("res/images/player.png"),true,100);
        this.setDebugColor(Color.pink);
    }

    public void setCurrentMap(Map map){
        currentMap = map;
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
        super.onCollision(other);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        //super.init(gameContainer);
        gameContainer.getInput().addKeyListener(this);


    }
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        setHeading((float)Math.toDegrees(currentMap.calculatePlayerHeading()));
        super.update(gameContainer,i);

    }


    public void keyPressed(int key,char c){
        float currentX = this.xspeed;
        float currentY = this.yspeed;
        if(key ==Input.KEY_W){
            currentY = -SPEED;
            upPressed = true;
        }
        if(key ==Input.KEY_S){
            currentY = SPEED;
            downPressed = true;
        }
        if(key ==Input.KEY_A){
            currentX = -SPEED;
            leftPressed = true;
        }
        if(key ==Input.KEY_D){
            currentX = SPEED;
            rightPressed = true;
        }
        setSpeed(currentX,currentY);
    }

    public void keyReleased(int key,char c){
        float currentX = this.xspeed;
        float currentY = this.yspeed;
        if(key ==Input.KEY_W){
            currentY = 0;
            upPressed = false;
            if(downPressed){
                currentY = SPEED;
            }
        }
        if(key ==Input.KEY_S){
            currentY = 0;
            downPressed = false;
            if(upPressed){
                currentY = -SPEED;
            }
        }
        if(key ==Input.KEY_A){
            currentX = 0;
            leftPressed = false;
            if(rightPressed){
                currentX = SPEED;
            }
        }
        if(key ==Input.KEY_D){
            currentX = 0;
            rightPressed = false;
            if(leftPressed){
                currentX = -SPEED;
            }
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
