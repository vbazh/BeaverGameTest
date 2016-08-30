package com.vbazh.beavertest.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vbazh.beavertest.BeaverGameTest;
import com.vbazh.beavertest.Screens.PlayScreen;

/**
 * Created by vladislav on 19.08.2016.
 */
public class Hero extends Sprite {
    public World world;
    public Body b2body;

    private TextureRegion heroStand;

    public Hero(PlayScreen screen){
        this.world = screen.getWorld();
        //Array<TextureRegion> frames = new Array<TextureRegion>();
        heroStand = new TextureRegion(screen.getAtlas().findRegion("Beaver"), 0,0,16,32);

        defineHero();
        setBounds(0,0,16 /BeaverGameTest.PPM,32 / BeaverGameTest.PPM);
        setRegion(heroStand);


    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(80 / BeaverGameTest.PPM, 120 / BeaverGameTest.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape body = new PolygonShape();
        body.setAsBox(10 / BeaverGameTest.PPM, 20 / BeaverGameTest.PPM);
//        fdef.filter.categoryBits = BeaverGameTest.HERO_BIT;
//        fdef.filter.maskBits = BeaverGameTest.GROUND_BIT;
//

        fdef.shape = body;
        b2body.createFixture(fdef).setUserData(this);

        //HEAD OUR HERO
//        PolygonShape head = new PolygonShape();
//        //head.setAsBox(5 / BeaverGameTest.PPM, 10 / BeaverGameTest.PPM, new Vector2(BeaverGameTest.));
//        head.setAsBox(-9 / BeaverGameTest.PPM, 6 / BeaverGameTest.PPM , new Vector2(b2body.getPosition().x / BeaverGameTest.PPM - 0.01f, b2body.getPosition().y/BeaverGameTest.PPM+0.1f),0);
//
//        //fdef.filter.categoryBits = BeaverGameTest.HERO_HEAD_BIT;
//        fdef.shape = head;
//        fdef.isSensor = true;
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / BeaverGameTest.PPM, 6 / BeaverGameTest.PPM ), new Vector2(2 / BeaverGameTest.PPM, 6 / BeaverGameTest.PPM ));
        //fdef.filter.categoryBits = BeaverGameTest.HERO_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;



        b2body.createFixture(fdef).setUserData(this);



    }
    public void update(float dt){
            setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight() / 2 - 6 /BeaverGameTest.PPM);
            setRegion(heroStand);

    }




}
