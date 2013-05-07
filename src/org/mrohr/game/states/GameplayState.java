package org.mrohr.game.states;

import org.mrohr.game.Driver;
import org.mrohr.game.Map;
import org.mrohr.game.MyGameContainer;
import org.mrohr.game.entities.Block;
import org.mrohr.game.entities.items.Item;
import org.mrohr.game.entities.Player;
import java.awt.Font;
import java.io.InputStream;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameplayState extends BasicGameState implements KeyListener{
    Map currentMap;
    Music music;
    Music gameOverMusic;
    float musicPausePos = -1;
    Player player;
    Image ui;
    TrueTypeFont font;
    public static String message;
    boolean showMenu;
    boolean gameOver = false;
    boolean finish = false;
    public GameplayState(){
      super();
    }

    @Override
    public int getID() {
        return Driver.GameStates.GAMEPLAY.ordinal();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void init(GameContainer gameContainer,StateBasedGame game) throws SlickException {
        System.out.println("init");
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap = new Map("res/maps/test2.tmx",this);
        ui = new Image("res/images/ui.png");
        music = new Music("res/sounds/music.ogg");
        gameOverMusic = new Music("res/sounds/gameover.ogg");
        message = "Where am I? (WASD to move, look with mouse)";
        currentMap.init((MyGameContainer)gameContainer);
        player = currentMap.getPlayer();




        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/beer money.ttf");

            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(GameContainer gameContainer,StateBasedGame game, int i) throws SlickException {

        currentMap.update((MyGameContainer)gameContainer,i);
        if(showMenu){
            showMenu = false;
            game.enterState(Driver.GameStates.MAIN_MENU.ordinal(),
                    new FadeOutTransition(Color.black, 200), new FadeInTransition(Color.black, 200));
        }

        if(gameOver){
            this.init(gameContainer, game);
            game.enterState(Driver.GameStates.GAME_OVER.ordinal(),
                    new FadeOutTransition(Color.red,0), new FadeInTransition(Color.red,6000));
            gameOver = false;

        }
        if(finish){
            this.init(gameContainer, game);
            game.enterState(Driver.GameStates.FINISH.ordinal(),
                    new FadeOutTransition(Color.black,2000), new FadeInTransition(Color.black,2000));
            finish = false;

        }


    }

    public void render(GameContainer gameContainer,StateBasedGame game, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap.render((MyGameContainer)gameContainer,graphics);
        graphics.drawImage(ui, 0, 0);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(message,Block.width*1 + 10, gameContainer.getHeight() - (Block.height * 2) + 2 + 8);
        graphics.drawString("Health : " +player.getHealth(),Block.width * 1 + 10,gameContainer.getHeight() - Block.height + 2 + 8);
        graphics.drawString("Hunger : " +player.getHunger(),Block.width * 6 + 10,gameContainer.getHeight() - Block.height + 2 + 8);
        graphics.drawString("Battery: "+player.getBattery(),Block.width * 11 + 10,gameContainer.getHeight() - Block.height + 2 + 8);
        List<Item> inventory = player.getInventory();
        int i = 0;
        for(Item item : inventory){
            graphics.drawImage(item.getRenderedImage(),(Block.width *16) + (Block.width * i),gameContainer.getHeight() - (Block.height * 2) + 8);
            i++;
        }
        graphics.fill(new Circle(gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY(),2));

       // graphics.drawString(String.valueOf((int)player.getBoundingBox().getCenterX()) + "," +
       //         String.valueOf((int)player.getBoundingBox().getCenterY()),5,20);
    }

    public void gameOver() throws SlickException{
        gameOver=  true;
    }
    public void finishGame() throws SlickException{
        finish=  true;
    }
    public void enter(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {


        container.getInput().removeAllKeyListeners();
        container.getInput().addKeyListener(this);
        if(musicPausePos > 0){
            music.loop();
            music.setPosition(musicPausePos);

        }else{
            music.loop();
        }
    }

    public void leave(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        container.getInput().removeKeyListener(this);
        if(!gameOver){
            music.pause();
            musicPausePos = music.getPosition();
        }

    }

    public void keyPressed(int key,char c){
        player.keyPressed(key,c);
        if(key == Input.KEY_ESCAPE){
            showMenu = true;
        }
        if(key == Input.KEY_P){
            player.damage(1000);
        }
        if(key == Input.KEY_O){
            finish = true;
        }
    }

    public void keyReleased(int key,char c){
        player.keyReleased(key,c);

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



}
