package org.mrohr.game;

import org.mrohr.game.entities.Block;
import org.mrohr.game.entities.Herb;
import org.mrohr.game.entities.Item;
import org.mrohr.game.entities.Player;
import java.awt.Font;
import java.io.InputStream;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.Base64;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/13/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGame implements KeyListener{
    Map currentMap;
    Player player;
    Image ui;
    TrueTypeFont font;
    boolean hasPressedEsc;
    boolean toggleFullscreen;
    public String message;
    public Game(){
      super("Top-Down Shooter");
    }
    public void init(GameContainer gameContainer) throws SlickException {
        hasPressedEsc = false;
        toggleFullscreen = false;
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap = new Map("res/maps/test2.tmx");
        ui = new Image("res/images/ui.png");

        Player player = new Player(100,100);
        this.player = player;
        //player.damage(30);
        currentMap.setPlayer(player);
        message = "Where am I? (WASD to move, look with mouse)";




        player.init((MyGameContainer)gameContainer);
        currentMap.init((MyGameContainer)gameContainer);



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

    public void update(GameContainer gameContainer, int i) throws SlickException {

        currentMap.update((MyGameContainer)gameContainer,i);
        if(toggleFullscreen){
            toggleFullscreen = false;
            gameContainer.setFullscreen(!gameContainer.isFullscreen());
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        currentMap.render((MyGameContainer)gameContainer,graphics);
        graphics.drawImage(ui, 0, 0);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(message,Block.width*1 + 10, gameContainer.getHeight() - (Block.height * 2) + 2 + 8);
        graphics.drawString("Health : " +player.getHealth(),Block.width * 1 + 10,gameContainer.getHeight() - Block.height + 2 + 8);
        graphics.drawString("Hunger : 100",Block.width * 6 + 10,gameContainer.getHeight() - Block.height + 2 + 8);
        graphics.drawString("Battery:100%",Block.width * 11 + 10,gameContainer.getHeight() - Block.height + 2 + 8);
        List<Item> inventory = player.getInventory();
        int i = 0;
        for(Item item : inventory){
            graphics.drawImage(item.getRenderedImage(),(Block.width *16) + (Block.width * i),gameContainer.getHeight() - (Block.height * 2) + 8);
            i++;
        }
    }


    public void keyPressed(int key,char c){
       if(key == Input.KEY_ESCAPE){
        if(!hasPressedEsc){
            hasPressedEsc = true;
            message = "Giving up? (esc again to quit)";
        }else{
            System.exit(0);
        }
       }
       if(key == Input.KEY_TAB){
         toggleFullscreen = true;
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
}
