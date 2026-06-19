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
    float windupTimer = 0;
    float recoveryTimer = 0;
    float attackDuration = 0.5f;
    float attackTimer = 0;

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

    public void update(float delta, Player player){

        if(!player.isAlive()) return;

        switch(state){

            case CHASE:
                updateChase(delta, player);
                break;

            case WINDUP:
                updateWindup(delta);
                break;

            case ATTACK:
                updateAttack(delta,player);
                break;

            case RECOVERY:
                updateRecovery(delta);
                break;

            case DEAD:
                updateDead(delta, player);
                break;
        }
    }

    private void updateAttack(float delta,Player player){
        attackTimer -= delta;

        if(attackTimer <=0){
            float dx = x - player.getX();
            float dy = y - player.getY();

            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if(distance <= attackRange){
                player.takeDamage(10);
            }

            attackCooldown = 1f;
            recoveryTimer = 0.3f;
            state = EnemyState.RECOVERY;
        }
    }

    private void updateRecovery(float delta){
            recoveryTimer -= delta;

            if(recoveryTimer <= 0){
                state = EnemyState.CHASE;
            }
    }

    private void updateWindup(float delta) {

        windupTimer -= delta;

        if(windupTimer <= 0){
             state = EnemyState.ATTACK;
             attackTimer=attackDuration;
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

            state = EnemyState.WINDUP;
            windupTimer = 0.5f;
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
        switch(state){

            case CHASE:
                shape.setColor(1,0,0,1);
                break;

            case WINDUP:
                shape.setColor(1,0.5f,0,1);
                shape.rect(x - 4, y -4, width + 8, height + 8);
                break;

            case ATTACK:
                shape.setColor(1,1,0,1);
                shape.rect(x - 5, y - 5, width + 10, height + 10);
                break;

            default:
                shape.setColor(1,0,0,1);
        }
        shape.rect(x, y, width, height);
    }
}
