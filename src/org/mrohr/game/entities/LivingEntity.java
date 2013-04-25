package org.mrohr.game.entities;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LivingEntity extends MoveableEntity {
    private int maxHealth;
    private int currentHealth;

    private int damageTimer;
    private final int damageTimerPeriod = 1000;
    private boolean damaged = false;

    public LivingEntity(Shape bb, boolean solid,int health){
        super(bb,solid);
        maxHealth = health;
        currentHealth = health;
        damageTimer = damageTimerPeriod;
    }

    public LivingEntity(Shape bb, Image img, boolean solid,int health){
        super(bb,img,solid);
        maxHealth = health;
        currentHealth = health;
        damageTimer = damageTimerPeriod;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth(){
        return currentHealth;
    }

    public void damage(int amount){
        if(!damaged){
            currentHealth -= amount;
            damaged = true;
            onDamaged(amount);
            if(currentHealth <= 0){
                onDeath();
            }
        }
    }

    public void heal(int amount){
        currentHealth += amount;
        currentHealth = currentHealth > maxHealth? maxHealth : currentHealth;
        onHealed(amount);
    }

    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        super.update(gameContainer, i);
        if(damaged){
            damageTimer -= i;
            if(damageTimer < 0){
                damaged = false;
                damageTimer = damageTimerPeriod;
            }
        }

    }

    public abstract void onDamaged(int amount);
    public abstract void onHealed(int amount);
    public abstract void onDeath();
}

