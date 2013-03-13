package org.mrohr.game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Driver {

    public static void main(String[] args) throws SlickException{
        Game game = new Game();
        AppGameContainer gc = new AppGameContainer(game);
        gc.setDisplayMode(500,500,false);
        gc.setShowFPS(true);
        gc.start();
    }
}
