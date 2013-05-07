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
public class OptionsMenuState extends MenuState {
    private GameContainer gc;
    public OptionsMenuState(int rootStateId) {
        super("Controls", rootStateId);
        options.add("Toggle Fullscreen");
        options.add("Toggle Sound");
        options.add("Back");


    }

    @Override
    public void optionSelected(String option) {
        if(option.equals("Back")){
            leaveMenu();
        }
        if(option.equals("Toggle Fullscreen")){
            try{
            gc.setFullscreen(!gc.isFullscreen());
            }catch(SlickException e){
                e.printStackTrace();
            }
        }

        if(option.equals("Toggle Sound")){
                gc.setSoundOn(!gc.isSoundOn());
                gc.setMusicOn(!gc.isMusicOn());
        }

    }
    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        super.init(gameContainer, game);
        this.text = new String[]{"",""};
        gc = gameContainer;
    }
    @Override
    public int getID() {
        return Driver.GameStates.OPTIONS.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        gc = gameContainer;
        String fullscreen = "Fullscreen: ";
        fullscreen += gameContainer.isFullscreen() ? "Yes" : "No";

        String sound = "Sound: ";
        sound += gameContainer.isSoundOn() ? "Yes":"No";
        text[0] = fullscreen;
        text[1] = sound;
    }

}
