package org.mrohr.game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGame {
    Map currentMap;
    public Game(){
      super("Top-Down Shooter");
    }
    public void init(GameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap = new Map("res/maps/test.tmx");
        currentMap.addEntity(new Block(200,200));
        currentMap.addEntity(new Block(210,200));
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        currentMap.update((MyGameContainer)gameContainer,i);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        graphics.drawString("Hello World!",0,0);
        currentMap.render((MyGameContainer)gameContainer,graphics);
    }
}
