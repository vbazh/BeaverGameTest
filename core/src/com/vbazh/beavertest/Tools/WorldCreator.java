package com.vbazh.beavertest.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vbazh.beavertest.BeaverGameTest;
import com.vbazh.beavertest.Screens.Levels;
import com.vbazh.beavertest.Screens.PlayScreen;

/**
 * Created by vladislav on 18.08.2016.
 */
public class WorldCreator {
    public WorldCreator(PlayScreen screen, Levels level){
        World world = screen.getWorld();
        TiledMap map = level.getMap();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixdef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BeaverGameTest.PPM, (rect.getY() + rect.getHeight() / 2) / BeaverGameTest.PPM);

            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BeaverGameTest.PPM, rect.getHeight() / 2 / BeaverGameTest.PPM);
            fixdef.shape = shape;
            body.createFixture(fixdef);
        }


    }
}
