package org.mrohr.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameObject  {
    public boolean debugging = true; //toggle this if debugging

    public abstract void init(MyGameContainer gameContainer) throws SlickException;
    public abstract void update(MyGameContainer gameContainer, int i) throws SlickException;
    public abstract void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException;
}
