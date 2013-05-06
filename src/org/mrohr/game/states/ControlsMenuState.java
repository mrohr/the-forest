package org.mrohr.game.states;

import org.mrohr.game.Driver;
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
public class ControlsMenuState extends MenuState {
    public ControlsMenuState(int rootStateId) {
        super("Controls", rootStateId);
        options.add("Back");

    }

    @Override
    public void optionSelected(String option) {
        if(option.equals("Back")){
            leaveMenu();
        }

    }
    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        super.init(gameContainer, game);
        this.text = new String[]{"Movement: WASD or Arrow Keys","Vision: Mouse","Sprint: Shift","Toggle Flashlight: Space","Pause: Esc"};
    }
    @Override
    public int getID() {
        return Driver.GameStates.CONTROLS.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
