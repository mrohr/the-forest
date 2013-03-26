package org.mrohr.game;

import org.lwjgl.input.Cursor;
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
    Player player;
    public Game(){
      super("Top-Down Shooter");
    }
    public void init(GameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap = new Map("res/maps/test2.tmx");

        Player player = new Player(currentMap.getHeight(),currentMap.getWidth());
        currentMap.setPlayer(player);


        player.init((MyGameContainer)gameContainer);
        currentMap.init((MyGameContainer)gameContainer);
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        currentMap.update((MyGameContainer)gameContainer,i);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap.render((MyGameContainer)gameContainer,graphics);
    }
}
