package com.vbazh.beavertest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vbazh.beavertest.BeaverGameTest;
import com.vbazh.beavertest.Scenes.Controller;
import com.vbazh.beavertest.Sprites.Hero;
import com.vbazh.beavertest.Tools.WorldCreator;

/**
 * Created by vladislav on 17.08.2016.
 */
public class PlayScreen implements Screen {
    private BeaverGameTest game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Controller controller;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr; //green lines
    private WorldCreator creator;

    private Hero player;

    private Levels levels;
    int level;

    public PlayScreen(BeaverGameTest game, int level) {
        this.level = level;
        atlas = new TextureAtlas("beaver_and_enemies.pack");
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(BeaverGameTest.WIDTH / BeaverGameTest.PPM, BeaverGameTest.HEIGHT / BeaverGameTest.PPM, gamecam);
        controller = new Controller();

//        mapLoader = new TmxMapLoader();
//        map = mapLoader.load("level1.tmx");

        levels = new Levels(1);


        renderer = new OrthogonalTiledMapRenderer(levels.getMap(), 1 / BeaverGameTest.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2, 0);

        //MAKING WORLD
        world = new World(new Vector2(0,-10), true); //gravity and smthing else
        creator = new WorldCreator(this, levels);
        player = new Hero(this);


        b2dr = new Box2DDebugRenderer();

//        BodyDef bodyDef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fixtureDef = new FixtureDef();
//        Body body;
//
//        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BeaverGameTest.PPM, (rect.getY() + rect.getHeight() / 2) / BeaverGameTest.PPM);
//
//            body = world.createBody(bodyDef);
//            shape.setAsBox(rect.getWidth() / 2 / BeaverGameTest.PPM, rect.getHeight() / 2 / BeaverGameTest.PPM);
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//        }




    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (controller.isRightPressed() && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.07f, 0), player.b2body.getWorldCenter(), true);
        if (controller.isLeftPressed() && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.07f, 0), player.b2body.getWorldCenter(), true);
        if (controller.isJumpPressed() && player.b2body.getLinearVelocity().y == 0 )
            player.b2body.applyLinearImpulse(new Vector2(0, 5f), player.b2body.getWorldCenter(), true);





    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6,2);
        if (player.b2body.getPosition().x >= gamePort.getWorldWidth()/2) {
            gamecam.position.x = player.b2body.getPosition().x;
            gamecam.update();
        }

        float position = gamecam.position.x;
        Gdx.app.log("position of cam",""+position);



        player.update(dt);
        renderer.setView(gamecam);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, gamecam.combined);



        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
        controller.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
        controller.resize(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }

    @Override
    public void dispose() {
        b2dr.dispose();
        renderer.dispose();
        world.dispose();
        map.dispose();

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
}
