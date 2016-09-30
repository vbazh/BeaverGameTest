package com.vbazh.beavertest.Screens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by vladislav on 24.08.2016.
 */
public class Levels {
    int level;
    private TmxMapLoader mapLoader;
    private TiledMap map;


    public Levels(int level) {
        this.level = level;
        mapLoader = new TmxMapLoader();
        chooseMap(level);
    }
    public TiledMap chooseMap(int level){
        switch (level){
            case 1:
                map = mapLoader.load("level1.tmx");
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;

        }
        return map;
    }

    public TiledMap getMap(){
        return map;
    }

    public TiledMap getNextLevel(){
        chooseMap(level++);
        return map;
    }
    public void dispose(){
        map.dispose();
    }
}
