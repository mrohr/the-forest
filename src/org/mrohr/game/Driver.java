package org.mrohr.game;


import org.mrohr.game.states.GameOverState;
import org.mrohr.game.states.GameplayState;
import org.mrohr.game.states.MainMenuState;
import org.mrohr.game.states.MenuState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Driver extends StateBasedGame{
    boolean hasPressedEsc;
    boolean debug = false;
    boolean toggleFullscreen;
    AppGameContainer gc;

    public Driver(String name) throws SlickException {
        super(name);
    }

    public enum GameStates{
        GAMEPLAY,
        MAIN_MENU,
        GAME_OVER;
    }

    public void init()throws SlickException{
        gc = new MyGameContainer(this,debug);
        gc.setDisplayMode(800,600,false);
        gc.setShowFPS(false);
        gc.setMouseGrabbed(true);
        gc.start();
    }
    public static void main(String[] args) throws SlickException{
        try{
        System.setProperty("java.library.path","natives");
        Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
        fieldSysPath.setAccessible( true );
        fieldSysPath.set( null, null );
        }catch(Exception e){
            e.printStackTrace();
        }
        Driver driver = new Driver("Top Down Shooter");
        driver.init();

    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new GameplayState());

        MenuState menu = new MainMenuState(getTitle(),GameStates.GAMEPLAY.ordinal());
        menu.init(gameContainer,this);
        addState(menu);

        MenuState gameover = new GameOverState();
        gameover.init(gameContainer,this);
        addState(gameover);
    }
}
