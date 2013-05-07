package org.mrohr.game.states;

import org.mrohr.game.Driver;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

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
        options.add("Controls");
        options.add("Exit Game");

    }

    @Override
    public void optionSelected(String option) {
        if(option.equals("Return")){
            leaveMenu();
        }

        if(option.equals("Controls")){
            game.enterState(Driver.GameStates.CONTROLS.ordinal(),new FadeOutTransition(Color.black,200),new FadeInTransition(Color.black,200));
        }
        if(option.equals("Exit Game")){
            System.exit(0);
        }

    }
    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        super.init(gameContainer, game);
        this.text = new String[]{"Created by Matt Rohr","Radford University Spring 2013", "Advanced Game Development Individual Project"};
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
