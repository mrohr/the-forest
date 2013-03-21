package org.mrohr.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyGameContainer extends AppGameContainer{
    public boolean debug = false;
    public MyGameContainer(Game game,boolean debug) throws SlickException{
        super(game);
        this.debug=debug;
    }
}
