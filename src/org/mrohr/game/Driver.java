package org.mrohr.game;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Driver {

    public static void main(String[] args) throws SlickException{
        try{
        System.setProperty("java.library.path","natives");
        Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
        fieldSysPath.setAccessible( true );
        fieldSysPath.set( null, null );
        }catch(Exception e){
            e.printStackTrace();
        }
        Game game = new Game();
        boolean debug = false;
        AppGameContainer gc = new MyGameContainer(game,debug);
        gc.setDisplayMode(800,600,false);
        //gc.setShowFPS(true);
        gc.start();
    }
}
