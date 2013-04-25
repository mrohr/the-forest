package org.mrohr.game.entities.items;

import org.mrohr.game.MyGameContainer;
import org.mrohr.game.entities.CollidableEntity;
import org.mrohr.game.entities.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/26/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Medkit extends Item {
    private int healingAmount;

    public Medkit(float x,float y) throws SlickException{
        super(new Rectangle(x,y,32,32), new Image("res/images/medkit.png"));
        this.healingAmount = 15;
    }

    public int getHealingAmount(){
        return this.healingAmount;
    }

    public void onCollision(CollidableEntity other){
        if(other instanceof Player){
            ((Player)other).heal(healingAmount);
        }

    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
