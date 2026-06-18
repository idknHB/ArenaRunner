package com.renan.jogo.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.renan.jogo.entity.Enemy;
import com.renan.jogo.entity.Player;

public class HUD {

    public void draw(ShapeRenderer shape, Player player, Enemy enemy) {

        drawPlayerHp(shape, player);
        drawPlayerStamina(shape, player);
        drawEnemyHp(shape, enemy);
    }

    private void drawPlayerHp(ShapeRenderer shape, Player player) {

        float width = 200;
        float height = 15;

        float percent = player.getHp()/player.getMaxHp();

        shape.setColor(Color.DARK_GRAY);
        shape.rect(20,560,width,height);

        shape.setColor(Color.RED);
        shape.rect(20,560,width * percent,height);
    }

    private void drawPlayerStamina(ShapeRenderer shape, Player player) {

        float width = 200;
        float height = 15;

        float percent = player.getStamina()/player.getMaxStamina();

        shape.setColor(Color.DARK_GRAY);
        shape.rect(20,535,width,height);

        shape.setColor(Color.GREEN);
        shape.rect(20,535,width,height);
    }

    private void drawEnemyHp(ShapeRenderer shape, Enemy enemy) {
        if(!enemy.isAlive())
            return;

        float width = 50;
        float height = 5;

        float percent = enemy.getHp()/enemy.getMaxHp();

        float barX = enemy.getX();
        float barY = enemy.getY() + enemy.getHeight() + 8;

        shape.setColor(Color.DARK_GRAY);
        shape.rect(barX,barY,width,height);

        shape.setColor(Color.RED);
        shape.rect(barX,barY,width * percent,height);
    }
}
