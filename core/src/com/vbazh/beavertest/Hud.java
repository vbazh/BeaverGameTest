package com.vbazh.beavertest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by vladislav on 18.08.2016.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private static Integer lives;
    private float timeCount;
    private static Integer score;

    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label nameLabel;

    Image livesThree;
    Image livesTwo;
    Image livesOne;
    Image liveImage;

    Table table;



    public Hud(SpriteBatch sb){
        worldTimer = 0;
        timeCount = 0;
        score = 0;
        lives = 3;

        viewport = new FitViewport(BeaverGameTest.WIDTH, BeaverGameTest.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nameLabel = new Label("Biff", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        livesThree = new Image(new Texture("3lives.png"));
        livesTwo = new Image(new Texture("2lives.png"));
        livesOne = new Image(new Texture("1live.png"));
        liveImage = livesThree;

        table.add(nameLabel).expandX().padTop(0);
        table.add(timeLabel).expandX().padTop(0);
        table.add(worldLabel).expandX().padTop(0);
        table.row();
        table.add(liveImage).size(liveImage.getWidth(),liveImage.getHeight()).expandX();
        table.add(countdownLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);


    }

    public void update(float dt){
        timeCount +=dt;
        if(timeCount >=1){
            worldTimer++;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
        switch (lives){
            case 1:
                liveImage = livesOne;
                Gdx.app.log("lives UPDATED",""+lives);
                break;
            case 2:
                liveImage = livesTwo;
                Gdx.app.log("lives UPDATED",""+lives);
                break;
            case 3:
                liveImage = livesThree;
                Gdx.app.log("lives UPDATED",""+lives);
                break;
        }

        table.clearChildren();
        table.add(nameLabel).expandX().padTop(0);
        table.add(timeLabel).expandX().padTop(0);
        table.add(worldLabel).expandX().padTop(0);
        table.row();
        table.add(liveImage).size(liveImage.getWidth(),liveImage.getHeight()).expandX();
        table.add(countdownLabel).expandX();
        table.add(scoreLabel).expandX();






    }
    public static void addScore(int value){
        score+=value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public static void addLive(int value){
        if(lives!=3){
            lives++;
        }
    }

    public static void removeLive(int value){
        lives--;
        Gdx.app.log("number of lives",""+lives);


    }





    @Override
    public void dispose() {
        stage.dispose();

    }
}
