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
public class Player extends LivingEntity implements KeyListener,MouseListener {
    private final float SPEED = 2f;
    int mousex = 0;
    int mousey = 0;
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
        super.onCollision(other);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        //super.init(gameContainer);
        gameContainer.getInput().addKeyListener(this);
        gameContainer.getInput().addMouseListener(this);

    }
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        float dx = this.boundingBox.getX() - mousex;
        float dy = this.boundingBox.getY() - mousey;
        this.heading =  (float)Math.toRadians(getTargetAngle(this.boundingBox.getX(),this.boundingBox.getY(),mousex,mousey));
        super.update(gameContainer,i);

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

    public void keyPressed(int key,char c){
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
        mousex = mx;
        mousey = my;
    }

    @Override
    public void mouseDragged(int i, int i2, int i3, int i4) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
