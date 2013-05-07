package org.mrohr.game;

import org.mrohr.game.entities.*;
import org.mrohr.game.entities.items.*;
import org.mrohr.game.states.GameplayState;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.util.ResourceLoader;

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
    Image flashlightMap;
    Image noLightMap;
    SpriteSheet tileset;

    List<Entity> blocks;
    List<Tree> trees;
    List<LivingTree> livingTrees;
    List<Doodad> doodads;
    public List<Item> worldItems;

    CaveEntrance caveEntrance;
    Player player;
    Animation rain;
    boolean seenKey  = false;
    boolean seenCave = false;
    boolean pickedKey = false;
    boolean treeTurned = false;
    static final int NUM_TREES = 200;
    static final int NUM_DOODADS = 400;
    static final int NUM_BERRIES = 5;
    static final int NUM_MEDKITS = 5;
    static final int NUM_BATTERIES = 5;

    private GameplayState parentState;
    Sound laugh;
    int livingTimer;
    public final int livingTimerPeriod = 30000;

    public Map(String tiledFile,GameplayState gameplaystate)throws SlickException{
        tiled = new TiledMapPlus(ResourceLoader.getResourceAsStream(tiledFile),"res/tilesets");
        height = tiled.getHeight();
        width = tiled.getWidth();
        blocks = new LinkedList<Entity>();
        trees = new LinkedList<Tree>();
        livingTrees = new LinkedList<LivingTree>();
        doodads = new LinkedList<Doodad>();
        worldItems = new LinkedList<Item>();
        livingTimer = livingTimerPeriod;
        this.parentState = gameplaystate;
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
        blocks = new LinkedList<Entity>();
        trees = new LinkedList<Tree>();
        livingTrees = new LinkedList<LivingTree>();
        doodads = new LinkedList<Doodad>();
        worldItems = new LinkedList<Item>();
        livingTimer = livingTimerPeriod;
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

        laugh = new Sound("res/sounds/laugh.ogg");
        flashlightMap = new Image("res/alphamap/flashlight.png");
        flashlightMap = flashlightMap.getScaledCopy(6);

        noLightMap = new Image("res/alphamap/nolight.png");
        noLightMap = noLightMap.getScaledCopy(6);

        rain = new Animation(new SpriteSheet(new Image("res/images/rainss.tga"),32,32),200);

        tileset = new SpriteSheet("res/tilesets/dark_forest.png",(int)Block.height,(int)Block.width);


        //world generation
        generateDoodads();

        List<CollidableEntity> things = new LinkedList<CollidableEntity>();
        Shape caveShape = generateShape(things,(int)Block.width * 7,(int)Block.height * 4);
        caveEntrance = new CaveEntrance(caveShape.getX(),caveShape.getY(),tileset,this);

        if(debugging){
            System.out.println("Cave at " + caveEntrance.getBoundingBox().getCenterX() + "," + caveEntrance.getBoundingBox().getCenterY());
        }
        Key[] keys = new Key[4];
        for(int i=0;i<4;i++){
            Key.KeyColor color = Key.KeyColor.values()[i];
            Shape shape = generateShape(things,(int)Block.width,(int)Block.height);
            Key key = new Key((int)shape.getX(),(int)shape.getY(),color,tileset);
            keys[i] = key;
            things.add(key);
            worldItems.add(key);

            if(debugging){
                System.out.println(color.name() + " Key at " + key.getBoundingBox().getCenterX() + "," + key.getBoundingBox().getCenterY());
            }
        }
        caveEntrance.setKeys(keys);

        for(int i=0; i<NUM_TREES; i++){
            Shape shape = generateShape(things,(int)Block.width * 3,(int)Block.height * 4);
            Tree tree = new Tree(shape.getX(),shape.getY(),tileset);
            trees.add(tree);
            things.add(tree);
            tree.init(gameContainer);
        }

        for(int i=0;i<NUM_BERRIES;i++){
            Shape shape = generateShape(things,(int)Block.width,(int)Block.height);
            Berry berry = new Berry((int)shape.getX(),(int)shape.getY());
            worldItems.add(berry);
            things.add(berry);
        }

        for(int i=0;i<NUM_MEDKITS;i++){
            Shape shape = generateShape(things,(int)Block.width,(int)Block.height);
            Medkit medkit = new Medkit((int)shape.getX(),(int)shape.getY());
            worldItems.add(medkit);
            things.add(medkit);
        }

        for(int i=0;i<NUM_BATTERIES;i++){
            Shape shape = generateShape(things,(int)Block.width,(int)Block.height);
            Battery b = new Battery((int)shape.getX(),(int)shape.getY());
            worldItems.add(b);
            things.add(b);
        }

        Shape playerShape = generateShape(things,(int)Block.width,(int)Block.height);
        this.setPlayer(new Player((int)playerShape.getX(),(int)playerShape.getY()));
        player.init(gameContainer);
        cam.init(gameContainer);

        if(debugging){
            System.out.println("Player at " + player.getBoundingBox().getX() + "," + player.getBoundingBox().getY());
        }

    }

    private Shape generateShape(List<CollidableEntity> things,int width, int height) throws SlickException{
        Random random = new Random();
        int minX = width * 2;

        int maxX = (tiled.getWidth() * this.tiled.getTileWidth())  - (width * 2);

        int minY = height;
        int maxY = (tiled.getHeight() * this.tiled.getTileHeight()) - (height * 2);

        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        Shape shape = new Rectangle(x,y,width,height);
        for(CollidableEntity t :things){
            if(shape.intersects(t.getBoundingBox())){
                return generateShape(things, width, height);
            }
        }
        return shape;
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
            if(doodad.getX() == d.getX() && doodad.getY() == d.getY()){
                return generateDoodad(existingDoodads);
            }
        }
        return doodad;
    }

    public void turnLivingTree(){
        int numTrees = trees.size();
        if(numTrees > 0){
            Random r = new Random();
            Tree tree = trees.remove(r.nextInt(numTrees));
            LivingTree livingTree = new LivingTree(tree.getBoundingBox().getX(),tree.getBoundingBox().getY(),tileset,player);
            livingTrees.add(livingTree);
            laugh.play();
        }
        if(!treeTurned){
            GameplayState.message = "What was that? I saw something move...";
            treeTurned = true;
        }
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        mousex = gameContainer.getInput().getMouseX() + (int)cam.getX();
        mousey = gameContainer.getInput().getMouseY() + (int)cam.getY();
        player.update(gameContainer,i);
        cam.update(gameContainer,i);
        if(!seenCave){
            if(cam.isVisible(caveEntrance.getBoundingBox())){

                GameplayState.message = "Is that cave locked?";
                seenCave = true;
            }
        }

        if(!seenKey){
            Key[] keys =caveEntrance.getKeys();
            for(int j=0;j<keys.length;j++){
                if(cam.isVisible(keys[j].getBoundingBox())){
                    GameplayState.message = "Is that a key? Why is that here?";
                    seenKey = true;
                }
            }
        }
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

        ListIterator<LivingTree> livingTreeItr = livingTrees.listIterator();
        while(livingTreeItr.hasNext()){
            LivingTree e = livingTreeItr.next();
            e.update(gameContainer, i);
            player.testCollision(e);
            caveEntrance.testCollision(e);
            treeItr = trees.listIterator();
            while(treeItr.hasNext()){
                Tree tree = treeItr.next();
                e.testCollision(tree);
            }
            ListIterator<LivingTree> otherLivingItr = livingTrees.listIterator();
            while(otherLivingItr.hasNext()){
                LivingTree lt = otherLivingItr.next();
                if(!e.equals(lt)){
                    e.testCollision(lt);
                }
            }
        }

        ListIterator<Item> itemItr = worldItems.listIterator();
        while(itemItr.hasNext()){
            Item item = itemItr.next();
            if(player.testCollision(item)){
                if(item instanceof Key){
                    if(!pickedKey && !seenCave){
                        GameplayState.message = "I wonder what this goes to...";
                        pickedKey = true;
                    }
                    if(!pickedKey && seenCave){
                        GameplayState.message = "This must go to that cave";
                        pickedKey = true;
                    }
                    if(pickedKey){
                        GameplayState.message = "Another key!";
                    }
                    player.getInventory().add(item);
                }
                if(item instanceof Food){
                    GameplayState.message = "Mmm, I was getting hungry";
                }
                if(item instanceof Medkit){
                    GameplayState.message = "Ahh, that feels much better";

                }
                itemItr.remove();

            }
        }
        caveEntrance.testCollision(player);

        livingTimer -= i;
        if(livingTimer < 0){
            System.out.println("Spawn a tree");
            livingTimer = livingTimerPeriod;
            turnLivingTree();
        }
        rain.update(i);



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

        for(LivingTree t: livingTrees){
            t.renderBottom(gameContainer,graphics);
        }
        caveEntrance.render(gameContainer,graphics);
        player.render(gameContainer,graphics);
        tiled.render(0,0,tiled.getLayerID("Over"));
        for(Tree t: trees){
            t.renderTop(gameContainer, graphics);
        }
        for(LivingTree t: livingTrees){
            t.renderTop(gameContainer,graphics);
        }

        //restore translation
        graphics.translate(cam.getX(),cam.getY());
        //render rain
        for(int i =0;i<gameContainer.getWidth();i+=32){
         for(int j=0;j<gameContainer.getHeight();j+=32){
             graphics.drawAnimation(rain,i,j);
         }
        }

        //lighting

        //reset translation
        graphics.translate(-cam.getX(),-cam.getY());

        graphics.setDrawMode(Graphics.MODE_ALPHA_MAP);
        Image alphamap;
        if(player.flashlightOn){
            alphamap = flashlightMap;
        }else{
            alphamap = noLightMap;
        }
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

    public void finishMap() throws SlickException{
        parentState.finishGame();
    }

    public void playerDeath() throws SlickException{
        parentState.gameOver();
    }
}
