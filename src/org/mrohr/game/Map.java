package org.mrohr.game;

import org.mrohr.game.entities.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.util.ResourceLoader;

import javax.swing.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Map extends GameObject implements MouseListener {
    TiledMapPlus tiled;
    private int height;
    private int width;
    public Camera cam;
    int mousex;
    int mousey;
    Image alphamap;
    SpriteSheet tileset;

    List<Entity> blocks;
    List<Tree> trees;
    List<Doodad> doodads;
    public List<Item> worldItems;

    CaveEntrance caveEntrance;
    Player player;
    static final int NUM_TREES = 200;
    static final int NUM_DOODADS = 400;

    public Map(String tiledFile)throws SlickException{
        tiled = new TiledMapPlus(ResourceLoader.getResourceAsStream(tiledFile),"res/tilesets");
        height = tiled.getHeight();
        width = tiled.getWidth();
        blocks = new LinkedList<Entity>();
        trees = new LinkedList<Tree>();
        doodads = new LinkedList<Doodad>();
        worldItems = new LinkedList<Item>();
    }

    public void setPlayer(Player p){
        player = p;
        p.setCurrentMap(this);
        cam = new Camera(player);
        cam.setMap(this);
    }
    public Player getPlayer(){
        return this.player;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        gameContainer.getInput().addMouseListener(this);
        Layer collisions = tiled.getLayer("Collision");
        for(int i = 0;i<tiled.getWidth();i++){
            for(int k = 0;k<tiled.getHeight();k++){
                String prop = tiled.getTileProperty(collisions.getTileID(i,k),"solid","false");
                if(Boolean.parseBoolean(prop)){
                   Block block = new Block(tiled.getTileWidth()*i,tiled.getTileWidth()*k);
                   blocks.add(block);
                }
            }
        }
        alphamap = new Image("res/alphamap/flashlight.png");
        alphamap = alphamap.getScaledCopy(6);

        tileset = new SpriteSheet("res/tilesets/dark_forest.png",(int)Block.height,(int)Block.width);

        generateCaveEnterance();
        generateTrees();
        for(Tree t: trees){
            t.init(gameContainer);
        }

        generateDoodads();

        Key[] keys = generateKeys();
        for(int i=0; i< keys.length; i++){
            worldItems.add(keys[i]);
        }
        caveEntrance.setKeys(keys);




    }

    private Key[] generateKeys(){
        Key[] keys = new Key[4];
        ArrayList<CollidableEntity> things = new ArrayList<CollidableEntity>();

        things.addAll(trees);
        things.addAll(worldItems);
        things.add(player);
        things.add(caveEntrance);

        for(int i=0;i<4;i++){
            Key.KeyColor color = Key.KeyColor.values()[i];
            Key key = generateKey(things,color);
            keys[i] = key;
            things.add(key);

            System.out.println(color.name() + " Key at " + key.getBoundingBox().getCenterX() + "," + key.getBoundingBox().getCenterY());
        }
        System.out.println(keys.length);

        return keys;
    }
    private Key generateKey(List<CollidableEntity> things,Key.KeyColor color){
        Random random = new Random();
        int minX = (int)Block.width * 2;
        int maxX = (int)(getWidth() * Block.width) - minX - (int)(Block.width * 2);

        int minY = (int)Block.height * 2;
        int maxY = (int)(getHeight() * Block.height) - minY - (int)(Block.height * 2);

        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        Key key = new Key(x,y,color,tileset);
        for(CollidableEntity t :things){
            if(key.getBoundingBox().intersects(t.getBoundingBox()) ||
                    key.getBoundingBox().intersects(caveEntrance.getBoundingBox())){
                return generateKey(things, color);
            }
        }
        return key;
    }
    private void generateCaveEnterance(){
        Random random = new Random();
        int minX = (int)Block.width * 2;
        int maxX = (int)(getWidth() * Block.width) - minX - (int)(Block.width * 7);

        int minY = (int)Block.height * 2;
        int maxY = (int)(getHeight() * Block.height) - minY - (int)(Block.height * 4);

        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        caveEntrance = new CaveEntrance(x,y,tileset,this);
        //if(debugging){
            System.out.println("Enterance at " + x + "," + y);
        //}
    }

    private void generateTrees(){
        for(int i=0; i<NUM_TREES; i++){
            trees.add(generateTree(trees));
        }
    }

    private Tree generateTree(List<Tree> existingTrees){
        Random random = new Random();
        int minX = (int)Block.width * 2;
        int maxX = (int)(getWidth() * Block.width) - minX - (int)(Block.width * 3);

        int minY = (int)Block.height * 2;
        int maxY = (int)(getHeight() * Block.height) - minY - (int)(Block.height * 4);

        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        Tree tree = new Tree(x,y,tileset);
        for(Tree t :existingTrees){
            if(tree.getBoundingBox().intersects(t.getBoundingBox()) ||
               tree.getBoundingBox().intersects(caveEntrance.getBoundingBox())){
                return generateTree(existingTrees);
            }
        }
        return tree;
    }

    private void generateDoodads(){
        for(int i=0; i<NUM_DOODADS; i++){
            doodads.add(generateDoodad(doodads));
        }
    }

    private Doodad generateDoodad(List<Doodad> existingDoodads){
        Random random = new Random();
        int minX = 2;
        int maxX = getWidth() - minX;

        int minY = 2;
        int maxY = getHeight() - minY;

        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        Doodad doodad = new Doodad(Block.width *x,Block.height * y,tileset);
        for(Doodad d :existingDoodads){
            if(doodad.x == d.x && doodad.y == d.y){
                return generateDoodad(existingDoodads);
            }
        }
        return doodad;
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        mousex = gameContainer.getInput().getMouseX() + (int)cam.getX();
        mousey = gameContainer.getInput().getMouseY() + (int)cam.getY();
        player.update(gameContainer,i);
        cam.update(gameContainer,i);
        ListIterator<Entity> blockItr = blocks.listIterator();
        while(blockItr.hasNext()){
            Entity e = blockItr.next();
            e.update(gameContainer,i);
            if(e instanceof CollidableEntity){
                player.testCollision((CollidableEntity)e);
            }
        }

        ListIterator<Tree> treeItr = trees.listIterator();
        while(treeItr.hasNext()){
            Tree e = treeItr.next();
            player.testCollision(e);
        }

        ListIterator<Item> itemItr = worldItems.listIterator();
        while(itemItr.hasNext()){
            Item item = itemItr.next();
            if(player.testCollision(item)){
                if(item instanceof Key){
                    player.getInventory().add(item);
                }
                itemItr.remove();

            }
        }
        caveEntrance.testCollision(player);




    }

    @Override
    public void render(MyGameContainer gameContainer, Graphics graphics) throws SlickException {

        //draw the map/any entities
        //translate graphics for camera
        graphics.translate(-cam.getX(),-cam.getY());
        tiled.render(0, 0, tiled.getLayerID("Main"));
        tiled.render(0,0,tiled.getLayerID("Under"));
        for(Doodad d: doodads){
            d.render(gameContainer,graphics);
        }
        for(Entity e: blocks){
            e.render(gameContainer,graphics);
        }
        for(Item i: worldItems){
            i.render(gameContainer, graphics);
        }
        for(Tree t: trees){
            t.renderBottom(gameContainer,graphics);
        }
        caveEntrance.render(gameContainer,graphics);
        player.render(gameContainer,graphics);
        tiled.render(0,0,tiled.getLayerID("Over"));
        for(Tree t: trees){
            t.renderTop(gameContainer, graphics);
        }

        //lighting
        graphics.setDrawMode(Graphics.MODE_ALPHA_MAP);
        float alphaW = alphamap.getWidth();
        float alphaH = alphamap.getHeight();
        float alphaX = player.getBoundingBox().getX() - (alphaW/2);
        float alphaY = player.getBoundingBox().getY() - (alphaH/2);
        graphics.rotate(player.getBoundingBox().getCenterX(),player.getBoundingBox().getCenterY(),player.getHeading());

        graphics.drawImage(alphamap,alphaX,alphaY);
        graphics.rotate(player.getBoundingBox().getCenterX(),player.getBoundingBox().getCenterY(),-player.getHeading());

        graphics.setDrawMode(Graphics.MODE_ALPHA_BLEND);
        graphics.setColor(Color.black);
        graphics.translate(cam.getX(),cam.getY());
        graphics.fillRect(0,0,gameContainer.getScreenWidth(),gameContainer.getScreenHeight());
        //graphics.fillRect(0,0,tiled.getWidth()*tiled.getTileWidth(),tiled.getHeight()*tiled.getTileHeight());
        graphics.setDrawMode(Graphics.MODE_NORMAL);
        graphics.translate(-cam.getX(),-cam.getY());


        //aiming cursor
        Circle cursor = new Circle(mousex,mousey,5f);
        graphics.setColor(Color.red);
        graphics.draw(cursor);
        graphics.drawLine(cursor.getCenterX() - 10,cursor.getCenterY(),
                cursor.getCenterX() +10,cursor.getCenterY());
        graphics.drawLine(cursor.getCenterX(),cursor.getCenterY() - 10,
                cursor.getCenterX(),cursor.getCenterY() + 10);


        //restore translation
        graphics.translate(cam.getX(),cam.getY());
    }


    public float calculatePlayerHeading(){
        float dx = player.getBoundingBox().getX() - mousex;
        float dy = player.getBoundingBox().getY() - mousey;
        return (float)Math.toRadians(getTargetAngle(player.getBoundingBox().getX(),player.getBoundingBox().getY(),mousex,mousey));

    }
    public double getDistanceBetween(float startX, float startY, float endX, float endY) {
        return Math.sqrt((Math.pow((endX - startX), 2)) + (Math.pow((endY - startY), 2)));
    }

    public double getTargetAngle(float startX, float startY, float targetX, float targetY) {
        double dist = getDistanceBetween(startX, startY, targetX, targetY);
        double sinNewAng = (startY - targetY) / dist;
        double cosNewAng = (targetX - startX) / dist;
        double angle = 0;

        if (sinNewAng > 0) {
            if (cosNewAng > 0) {
                angle = 90 - Math.toDegrees(Math.asin(sinNewAng));
            } else {
                angle = Math.toDegrees(Math.asin(sinNewAng)) + 270;
            }
        } else {
            angle = Math.toDegrees(Math.acos(cosNewAng)) + 90;
        }
        return angle;
    }


    @Override
    public void mouseWheelMoved(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseClicked(int i, int i2, int i3, int i4) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(int i, int i2, int i3) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(int i, int i2, int i3) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(int i, int i2, int mx, int my) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public void mouseDragged(int i, int i2, int i3, int i4) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setInput(Input input) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAcceptingInput() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void inputEnded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void inputStarted() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void finishMap(){
        JOptionPane.showMessageDialog(null, "You did it! :3");
        System.exit(0);
    }
}
