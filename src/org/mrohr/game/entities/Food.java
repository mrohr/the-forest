package org.mrohr.game.entities;

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
    private int healingAmount;

    public Food(Shape bb, Image img,int healingAmount) {
        super(bb, img);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount(){
        return this.healingAmount;
    }

    public void onCollision(CollidableEntity other){
        if(other instanceof Player){
            ((Player)other).heal(healingAmount);
        }

    }

}
