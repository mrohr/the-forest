package org.mrohr.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/26/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Item extends CollidableEntity {
    public Item(Shape bb, Image img) {
        super(bb, img, false);
    }



    public void onCollision(CollidableEntity other){

    }

    public abstract void use();
}
