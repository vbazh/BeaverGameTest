package com.vbazh.beavertest.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vbazh.beavertest.BeaverGameTest;

/**
 * Created by vladislav on 18.08.2016.
 */
public class Controller {
    Viewport viewport;
    Stage stage;
    boolean leftPressed, rightPressed, jumpPressed, firePressed;
    OrthographicCamera camera;

    public Controller(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(BeaverGameTest.WIDTH, BeaverGameTest.HEIGHT, camera);
        stage = new Stage(viewport, BeaverGameTest.batch);

        stage.addListener(new InputListener(){
            public boolean keyDown(InputEvent event, int keycode){
                switch (keycode){
                    case Input.Keys.UP:
                        jumpPressed = true;
                        Gdx.app.log("Jump pressed!","YO");
                        break;
                    case Input.Keys.DOWN:
                        firePressed = true;
                        break;
                    case Input.Keys.LEFT:
                        leftPressed = true;
                        break;
                    case Input.Keys.RIGHT:
                        rightPressed = true;
                        break;
                }
                return true;
            }

            public boolean keyUp(InputEvent event, int keycode){
                switch (keycode){
                    case Input.Keys.UP:
                        jumpPressed = false;
                        break;
                    case Input.Keys.DOWN:
                        firePressed = false;
                        break;
                    case Input.Keys.LEFT:
                        leftPressed = false;
                        break;
                    case Input.Keys.RIGHT:
                        rightPressed = false;
                        break;
                }
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image leftImg = new Image(new Texture("LEFT_Arrow.png"));
        leftImg.setSize(50,50);
        leftImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                Gdx.app.log("LEFT pressed!","YO");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image rightImg = new Image(new Texture("RIGHT_Arrow.png"));
        rightImg.setSize(50,50);
        rightImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                Gdx.app.log("RIGHT pressed!","YO");
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        Image fireImg = new Image(new Texture("FIRE.png"));
        fireImg.setSize(50,50);
        fireImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                firePressed = true;
                Gdx.app.log("FIRE pressed!","YO");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                firePressed = false;
            }
        });

        Image jumpImg = new Image(new Texture("UP_Arrow.png"));
        jumpImg.setSize(50,50);
        jumpImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = true;
                Gdx.app.log("JUMP pressed!","YO");
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = false;
            }
        });


        table.add();
        table.add(leftImg).size(leftImg.getWidth(),leftImg.getHeight());
        table.add();
        table.add(rightImg).size(rightImg.getWidth(),rightImg.getHeight());
        table.add().padRight(180);
        table.add(fireImg).size(fireImg.getWidth(),fireImg.getHeight());

        table.add();

        table.add(jumpImg).size(jumpImg.getWidth(),jumpImg.getHeight());
        table.add();
        table.row().padBottom(5);
        table.add();




        stage.addActor(table);

    }

    public void draw(){
        stage.draw();
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public boolean isJumpPressed(){
        return jumpPressed;
    }


}
