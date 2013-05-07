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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EndingState extends BasicGameState implements KeyListener{

    protected TrueTypeFont fontBig;
    protected TrueTypeFont fontHuge;

    protected Music music;
    protected Music gameover;
    protected boolean done = false;

    int currentText = 0;
    List<String> text;

    StateBasedGame game;
    public EndingState(){
      super();
      text = new ArrayList<String>();
      text.add("As you enter the cave, you feel uneasy");
      text.add("All you can hear is the rain from outside..");
      text.add("Suddenly, the gate closes behind you and your flashlight goes out");
      text.add("The cave is pitch black, no sound coming from inside");
      text.add("All you can hear is the rain from outside...");
      text.add("Suddenly, you are lightheaded, and fall to the ground");
      text.add("All you can hear is the rain from outside...");
      text.add("But as you fall asleep, you think you hear a familiar laughter");
      text.add("...");
      text.add("All you can hear is the rain from outside...");
      text.add("....");
      text.add("You slowly awake, to the feeling of rain...");
    }

    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        // load font from a .ttf file
        this.game = game;
        music = new Music("res/sounds/nature.ogg");
        gameover = new Music("res/sounds/gameover.ogg");
        try {


            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/beer money.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


            Font largeFont = baseFont.deriveFont(28f); // set font size
            fontBig = new TrueTypeFont(largeFont, false);

            Font hugeFont = baseFont.deriveFont(50f);
            fontHuge = new TrueTypeFont(hugeFont,false);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enter(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        currentText = 0;
        done = false;
        container.getInput().addKeyListener(this);
        music.play();
    }

    public void leave(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        container.getInput().removeKeyListener(this);
        music.stop();
    }

    public void render(GameContainer gameContainer,StateBasedGame game, Graphics graphics) throws SlickException {

        graphics.setColor(Color.white);

        if(currentText < text.size()){
        String string = text.get(currentText);
            int y =  (gameContainer.getHeight() - fontBig.getHeight(string)) / 2;
        fontBig.drawString((gameContainer.getWidth() - fontBig.getWidth(string)) / 2,
                y, string);
            if(done){
                graphics.setColor(Color.red);
                graphics.setFont(fontHuge);
                graphics.drawString(game.getTitle(),(gameContainer.getWidth() - fontHuge.getWidth(game.getTitle())) / 2,y - fontHuge.getHeight() - 5);
            }
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void leaveMenu(){
        game.enterState(Driver.GameStates.GAMEPLAY.ordinal(),
                new FadeOutTransition(Color.black, 3000), new FadeInTransition(Color.black, 2000));
    }

    public void keyPressed(int key,char c){
        currentText ++;

        if(currentText == text.size() - 1 ){
            gameover.play();

        }
        if(currentText == text.size()){
            done = true;
            leaveMenu();
            currentText --;
        }
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
        return Driver.GameStates.FINISH.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * unused
     */
    @Override
    public void inputStarted() {
    }




}
