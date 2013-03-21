package org.mrohr.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LivingEntity extends MoveableEntity {
    int maxHealth;
    int currentHealth;

    public LivingEntity(Shape bb, boolean solid,int health){
        super(bb,solid);
        maxHealth = health;
        currentHealth = health;
    }

    public LivingEntity(Shape bb, Image img, boolean solid,int health){
        super(bb,img,solid);
        maxHealth = health;
        currentHealth = health;
    }

    public int getHealth(){
        return currentHealth;
    }

    public void damage(int amount){
        currentHealth -= amount;
        onDamaged(amount);
        if(currentHealth <= 0){
            onDeath();
        }
    }

    public void heal(int amount){
        currentHealth += amount;
        currentHealth = currentHealth > maxHealth? maxHealth : currentHealth;
        onHealed(amount);
    }

    public abstract void onDamaged(int amount);
    public abstract void onHealed(int amount);
    public abstract void onDeath();
}

