package org.mrohr.game.entities.items;

import org.mrohr.game.entities.CollidableEntity;
import org.mrohr.game.entities.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/26/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Food extends Item {
    private int hungerAmount;

    public Food(Shape bb, Image img,int hungerAmount) {
        super(bb, img);
        this.hungerAmount = hungerAmount;
    }

    public int getHungerAmount(){
        return this.hungerAmount;
    }

    public void onCollision(CollidableEntity other){
        if(other instanceof Player){
            ((Player)other).eat(this);
        }

    }

}
