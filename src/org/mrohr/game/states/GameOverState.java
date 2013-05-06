package org.mrohr.game.states;

import org.mrohr.game.Driver;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 4/25/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameOverState extends MenuState {
    public GameOverState() {
        super("You Have Died", Driver.GameStates.GAMEPLAY.ordinal());
        options.add("Try Again");
        options.add("Exit Game");

    }

    @Override
    public void optionSelected(String option) {
        if(option.equals("Try Again")){
            leaveMenu();
        }

        if(option.equals("Exit Game")){
            System.exit(0);
        }

    }
    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        super.init(gameContainer, game);
        this.text = new String[]{"You could not escape the forest...","Try again?"};
    }
    public void enter(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        super.enter(container, stateBasedGame);
        new Music("res/sounds/gameover.ogg").play();
    }
    @Override
    public int getID() {
        return Driver.GameStates.GAME_OVER.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
