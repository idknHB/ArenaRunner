package com.renan.jogo.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.renan.jogo.state.EnemyState;

public class Enemy {
    float x, y;
    float speed = 100;
    float maxHp = 80;
    float hp = maxHp;
    boolean isAlive = true;
    float respawnTimer = 0;

    float width = 50;
    float height = 50;

    float attackRange = 60f;
    float attackCooldown = 0;

    EnemyState state = EnemyState.CHASE;

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getMaxHp() {
        return maxHp;
    }
    public void setMaxHp(float maxHp) {
        this.maxHp = maxHp;
    }
    public float getHp() {
        return hp;
    }
    public void setHp(float hp) {
        this.hp = hp;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public float getRespawnTimer() {
        return respawnTimer;
    }
    public void setRespawnTimer(float respawnTimer) {
        this.respawnTimer = respawnTimer;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public Enemy(float x, float y){
        this.x = x;
        this.y = y;
    }

    int count;

    public void update(float delta, Player player){

        if(!player.isAlive()) return;

        switch(state){

            case CHASE:
                updateChase(delta, player);
                break;

            case DEAD:
                updateDead(delta, player);
                break;
        }
    }

    private void updateChase(float delta, Player player){

        float dx = player.getX() - x;
        float dy = player.getY() - y;

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        attackCooldown -= delta;

        if(distance > attackRange && attackCooldown <= 0){

            dx /= distance;
            dy /= distance;

            float newX = x + dx * speed * delta;
            float newY = y + dy * speed * delta;

            if(canMove(newX,y)) x = newX;
            if(canMove(x,newY)) y = newY;

        }else if(attackCooldown <= 0){

            player.takeDamage(10);
            attackCooldown = 1f;
        }

        if(hp <= 0){

            isAlive = false;
            respawnTimer = 2f;
            state = EnemyState.DEAD;

        }
    }

    private void updateDead(float delta, Player player){

        respawnTimer -= delta;

        if(respawnTimer <= 0){

            respawn(player);

            state = EnemyState.CHASE;

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
        } while (Math.hypot(newX - player.getX(), newY - player.getY()) < minDistance);

        x = newX;
        y = newY;

        hp = 80;
        isAlive = true;
    }

    public boolean canMove(float newX, float newY){
        return newX  >= 0 && newX + width <= 800 && newY >= 0 && newY + height<= 600;
    }
    public void takeDamage(float damage){

        hp -= damage;

        if(hp <= 0){
            isAlive = false;
        }
    }

    public void draw(ShapeRenderer shape){
        if(!isAlive) return;
        shape.setColor(1,0,0,1);
        shape.rect(x,y,width,height);
    }
}
