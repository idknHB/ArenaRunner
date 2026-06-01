package com.renan.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    float x , y;
    float speed = 200;
    float hp = 100;
    boolean isAlive = true;

    float newX = x;
    float newY = y;

    float width = 50;
    float height = 50 ;

    public Player(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void update(float delta){
        if(!isAlive) return;

        if(canMove(newX,y))x = newX;
        if(canMove(x, newY)) y = newY;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) newY += speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) newY -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) newX -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) newX += speed * delta;

        if(hp <= 0){
            isAlive = false;
        }

        boolean isAttacking = Gdx.input.isKeyPressed(Input.Keys.E);
    }

    public void attack(Enemy enemy){
        float dx = enemy.x - x;
        float dy = enemy.y - y;

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if(distance <= 75){
            enemy.hp -= 30;
            System.out.println("Player hit");
        }
    }

    public boolean canMove(float newX, float newY){
        return newX  >= 0 && newX + width <= 800 && newY >= 0 && newY + height<= 600;
    }

    public void draw(ShapeRenderer shape){
        if(!isAlive) return;

        shape.setColor(0,0,1,1);
        shape.rect(x,y,width,height);
    }
}
