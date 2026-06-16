package com.renan.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.renan.jogo.Enemy;
import com.renan.jogo.Player;

public class HUD {

    public void draw(ShapeRenderer shape, Player player, Enemy enemy) {

        drawPlayerHp(shape, player);
        drawPlayerStamina(shape, player);
        drawEnemyHp(shape, enemy);
    }

    private void drawPlayerHp(ShapeRenderer shape, Player player) {

        float width = 200;
        float height = 15;

        float percent = player.hp/player.maxHp;

        shape.setColor(Color.DARK_GRAY);
        shape.rect(20,560,width,height);

        shape.setColor(Color.RED);
        shape.rect(20,560,width * percent,height);
    }

    private void drawPlayerStamina(ShapeRenderer shape, Player player) {

        float width = 200;
        float height = 15;

        float percent = player.stamina/player.maxStamina;

        shape.setColor(Color.DARK_GRAY);
        shape.rect(20,535,width,height);

        shape.setColor(Color.GREEN);
        shape.rect(20,535,width,height);
    }

    private void drawEnemyHp(ShapeRenderer shape, Enemy enemy) {
        if(!enemy.isAlive)
            return;

        float width = 50;
        float height = 5;

        float percent = enemy.hp/enemy.maxHp;

        float barX = enemy.x;
        float barY = enemy.y + enemy.height + 8;

        shape.setColor(Color.DARK_GRAY);
        shape.rect(barX,barY,width,height);

        shape.setColor(Color.RED);
        shape.rect(barX,barY,width * percent,height);
    }
}
