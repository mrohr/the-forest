package org.mrohr.game;

import org.mrohr.game.entities.Block;
import org.mrohr.game.entities.Item;
import org.mrohr.game.entities.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import javax.print.DocFlavor;
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
public abstract class MenuState extends BasicGameState implements KeyListener{
    TrueTypeFont fontSmall;
    TrueTypeFont fontMedium;
    TrueTypeFont fontBig;

    String title;
    List<String> options;
    int rootStateId;
    int selectedIndex;
    boolean showMenu;

    StateBasedGame game;
    public MenuState(String title,int rootStateId){
      super();
      this.title = title;
      this.rootStateId = rootStateId;
      options = new LinkedList<String>();
      selectedIndex = 0;
    }

    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        // load font from a .ttf file
        this.game = game;
        try {


            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/beer money.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            Font smallFont = baseFont.deriveFont(24f); // set font size
            fontSmall = new TrueTypeFont(smallFont, false);

            Font mediumFont = baseFont.deriveFont(30f); // set font size
            fontMedium = new TrueTypeFont(mediumFont, false);

            Font largeFont = baseFont.deriveFont(42f); // set font size
            fontBig = new TrueTypeFont(largeFont, false);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enter(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        container.getInput().addKeyListener(this);
    }

    public void leave(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        container.getInput().removeKeyListener(this);
    }

    public void render(GameContainer gameContainer,StateBasedGame game, Graphics graphics) throws SlickException {

        graphics.setColor(Color.white);

        fontBig.drawString((gameContainer.getWidth() / 2) - fontBig.getWidth(title) / 2, 0, title);

        int count = 0;
        graphics.setFont(fontMedium);
        for(String option:options){
            if(count ==selectedIndex){
                graphics.setColor(Color.red);
            }else{
                graphics.setColor(Color.white);
            }
            graphics.drawString("- " + option,20,fontBig.getHeight() * 2 + fontMedium.getHeight() * count);

            count++;
        }
    }

    public void leaveMenu(){
        game.enterState(rootStateId,
                new FadeOutTransition(Color.black, 200), new FadeInTransition(Color.black, 200));
    }

    public void keyPressed(int key,char c){
        System.out.println(key);
        if(key == Input.KEY_UP || key == Input.KEY_W){
            selectedIndex --;
            if(selectedIndex <0) selectedIndex = 0;
        }

        if(key == Input.KEY_DOWN || key == Input.KEY_S){
            selectedIndex ++;
            if(selectedIndex >= options.size()) selectedIndex = options.size() - 1;
        }
        if(key == Input.KEY_ENTER || key == Input.KEY_SPACE){
            optionSelected(options.get(selectedIndex));
        }
        if(key == Input.KEY_ESCAPE){
            leaveMenu();
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


    /**
     * unused
     */
    @Override
    public void inputStarted() {
    }

    public abstract void optionSelected(String option);



}
