package com.renan.jogo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {

    private OrthographicCamera camera;
    private ShapeRenderer shape;

    Player player;
    Enemy enemy;

    private float deathTimer = 0;
    boolean isGameOver = false;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        player = new Player(0, 0);
        enemy = new Enemy(400, 300);

        shape = new ShapeRenderer();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        enemy.update(delta, player);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        player.draw(shape);
        enemy.draw(shape);

        shape.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && player.isAlive) {
            player.attack(enemy);
        }

        if (enemy.isAlive) {
            enemy.update(delta, player);
        }

        if (!player.isAlive && !isGameOver) {
            isGameOver = true;
            deathTimer = 2f; // 2 segundos
        }
        if (isGameOver) {
            deathTimer -= delta;

            if (deathTimer <= 0) {
                resetGame();
                isGameOver = false;
            }
        }
    }

    private void resetGame(){
        player = new Player(0,0);
        enemy = new Enemy(400,300);
    }
    @Override
    public void dispose() {
        shape.dispose();
    }
}