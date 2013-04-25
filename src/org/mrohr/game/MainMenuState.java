package org.mrohr.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/25/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuState extends MenuState {
    public MainMenuState(String title, int rootStateId) {
        super(title, rootStateId);
        options.add("Return");
        options.add("Exit Game");
    }

    @Override
    public void optionSelected(String option) {
        if(option.equals("Return")){
            leaveMenu();
        }

        if(option.equals("Exit Game")){
            System.exit(0);
        }

    }

    @Override
    public int getID() {
        return Driver.GameStates.MAIN_MENU.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
