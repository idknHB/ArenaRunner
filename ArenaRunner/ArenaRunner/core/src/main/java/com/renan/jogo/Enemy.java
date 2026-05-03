package com.renan.jogo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Enemy {
    float x, y;
    float speed = 100;
    float hp = 80;
    boolean isAlive = true;
    float respawnTimer = 0;

    float width = 50;
    float height = 50;

    float attackRange = 60f;
    float attackCooldown = 0;

    public Enemy(float x, float y){
        this.x = x;
        this.y = y;
    }

    int count;

    public void update(float delta, Player player){
        if(!player.isAlive) return;

        float dx = player.x - x;
        float dy = player.y - y;

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        attackCooldown -= delta;

        if(distance > attackRange && attackCooldown <=0){
            dx /= distance;
            dy /= distance;

            float newX = x + dx * speed * delta;
            float newY = y + dy * speed * delta;

            if(canMove(newX,y))x = newX;
            if(canMove(x,newY))y = newY;
        }else if(attackCooldown <=0 && isAlive){
            player.hp -= 10;
            attackCooldown = 1f;
            System.out.println("Enemy hit");

        }

        if (hp <= 0 && isAlive) {
            isAlive = false;
            respawnTimer = 2f;
        }

        if(!isAlive){
            respawnTimer -= delta;
            if(respawnTimer <= 0){
                respawn(player);
            }
            return;
        }
    }

    public void respawn(){
        x = (float)Math.random() * 750;
        y = (float)Math.random() * 550;

        hp = 80;
        isAlive = true;
    }

    public void respawn(Player player) {
        float newX, newY;
        float minDistance = 100;

        do {
            newX = (float)Math.random() * 800;
            newY = (float)Math.random() * 600;
        } while (Math.hypot(newX - player.x, newY - player.y) < minDistance);

        x = newX;
        y = newY;

        hp = 80;
        isAlive = true;
    }

    public boolean canMove(float newX, float newY){
        return newX  >= 0 && newX + width <= 800 && newY >= 0 && newY + height<= 600;
    }

    public void draw(ShapeRenderer shape){
        if(!isAlive) return;
        shape.setColor(1,0,0,1);
        shape.rect(x,y,width,height);
    }
}
