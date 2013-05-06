package org.mrohr.game.states;

import org.mrohr.game.Driver;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class TitleState extends BasicGameState implements KeyListener{

    protected TrueTypeFont fontBig;
    protected TrueTypeFont fontHuge;

    protected String title;
    protected Music music;
    protected int rootStateId;

    StateBasedGame game;
    public TitleState(String title, int rootStateId){
      super();
      this.title = title;
      this.rootStateId = rootStateId;


    }

    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        // load font from a .ttf file
        this.game = game;
        music = new Music("res/sounds/nature.ogg");
        try {


            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/beer money.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


            Font largeFont = baseFont.deriveFont(42f); // set font size
            fontBig = new TrueTypeFont(largeFont, false);

            Font hugeFont = baseFont.deriveFont(80f);
            fontHuge = new TrueTypeFont(hugeFont,false);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enter(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        container.getInput().addKeyListener(this);
        music.play();
    }

    public void leave(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        container.getInput().removeKeyListener(this);
        music.stop();
    }

    public void render(GameContainer gameContainer,StateBasedGame game, Graphics graphics) throws SlickException {

        graphics.setColor(Color.white);

        fontHuge.drawString((gameContainer.getWidth() - fontHuge.getWidth(title)) / 2, 0, title);
        fontBig.drawString((gameContainer.getWidth() - fontBig.getWidth("Press Enter to Start...")) / 2,fontHuge.getHeight() + 5,"Press Enter to Start..." );
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void leaveMenu(){
        game.enterState(rootStateId,
                new FadeOutTransition(Color.black, 200), new FadeInTransition(Color.black, 200));
    }

    public void keyPressed(int key,char c){
        leaveMenu();
    }

    public void keyReleased(int key,char c){

    }
    public void setInput(Input input) {
    }
    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    /**
     * unused
     */
    @Override
    public void inputEnded() {
    }

    @Override
    public int getID() {
        return Driver.GameStates.TITLE_SCREEN.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * unused
     */
    @Override
    public void inputStarted() {
    }




}
