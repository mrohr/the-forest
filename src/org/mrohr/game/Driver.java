package org.mrohr.game;


import org.mrohr.game.states.*;
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
        TITLE_SCREEN,
        MAIN_MENU,
        CONTROLS,
        GAME_OVER,
        FINISH;
    }

    public void init()throws SlickException{
        gc = new MyGameContainer(this,debug);
        gc.setDisplayMode(800,600,true);
        gc.setShowFPS(false);
        gc.setMouseGrabbed(true);
        gc.setSoundVolume(0);
        gc.setMusicVolume(0);
        gc.start();

    }
    public static void main(String[] args) throws SlickException{
        /*try{
        System.setProperty("java.library.path","natives");
        Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
        fieldSysPath.setAccessible( true );
        fieldSysPath.set( null, null );
        }catch(Exception e){
            e.printStackTrace();
        } */
        Driver driver = new Driver("The Forest");
        driver.init();


    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        TitleState title= new TitleState("The Forest",GameStates.GAMEPLAY.ordinal());
        title.init(gameContainer,this);
        addState(title);
        addState(new GameplayState());

        MenuState menu = new MainMenuState(getTitle(),GameStates.GAMEPLAY.ordinal());
        menu.init(gameContainer,this);
        addState(menu);

        MenuState gameover = new GameOverState();
        gameover.init(gameContainer,this);
        addState(gameover);

        MenuState controls = new ControlsMenuState(GameStates.MAIN_MENU.ordinal());
        controls.init(gameContainer,this);
        addState(controls);

        EndingState finish = new EndingState();
        finish.init(gameContainer,this);
        addState(finish);
    }
}
