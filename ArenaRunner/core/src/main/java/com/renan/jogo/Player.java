package com.renan.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    float x , y;
    float speed = 200;
    float maxHp = 100;
    float hp = maxHp;
    float maxStamina = 100;
    float stamina = maxStamina;
    boolean isAlive = true;

    //variaveis ROLL
    float rollSpeed = 450f;
    float rollDuration = 0.25f;
    float rollTimer = 0f;

    boolean invulnerable = false;

    float directionX;
    float directionY;

    float newX = x;
    float newY = y;

    float width = 50;
    float height = 50 ;

    PlayerState state = PlayerState.IDLE;

    public Player(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void update(float delta){
        if(!isAlive) {
            state = PlayerState.DEAD;
            return;
        }
        switch (state) {

            case IDLE:
                idle();
                break;

            case MOVE:
                move(delta);
                break;

            case ROLL:
                updateRoll(delta);
                break;

            default:
                break;
        }
        if(hp <= 0){
            isAlive = false;
        }

        boolean isAttacking = Gdx.input.isKeyPressed(Input.Keys.E);
    }

    private void updateRoll(float delta) {
        float dx = directionX * rollSpeed * delta;
        float dy = directionY * rollSpeed * delta;

        tryMove(dx, dy);

        rollTimer -= delta;

        if(rollTimer <= 0) {

            invulnerable = false;

            if (isMoving()) {
                state = PlayerState.MOVE;
            } else {
                state = PlayerState.IDLE;
            }
        }
    }

    private void move(float delta){


        if(!isMoving()){
            state = PlayerState.IDLE;
            return;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            startRoll();
            return;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            tryMove (0,speed * delta);
            directionY = 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            tryMove (0,- speed * delta);
            directionY = -1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            tryMove (-speed * delta, 0 );
            directionX = -1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            tryMove (speed * delta, 0);
            directionX = 1;
        }

        float length = (float)Math.sqrt(directionX * directionX + directionY * directionY);

        if(length != 0){
            directionX /= length;
            directionY /= length;
        }
    }

    private void idle() {

        if(isMoving()){
            state = PlayerState.MOVE;
            return;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            startRoll();
        }
    }

    private void startRoll(){

        state = PlayerState.ROLL;

        rollTimer = rollDuration;

        invulnerable = true;

    }
    private boolean isMoving(){

        return
                Gdx.input.isKeyPressed(Input.Keys.W) ||
                        Gdx.input.isKeyPressed(Input.Keys.A) ||
                        Gdx.input.isKeyPressed(Input.Keys.S) ||
                        Gdx.input.isKeyPressed(Input.Keys.D);

    }

    public void takeDamage(float damage){
        if(invulnerable) return;

        hp -= damage;
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

    public void tryMove(float dx, float dy){
        float newX = x + dx;
        float newY = y + dy;

        if(canMove(newX, y)){
            x = newX;
        }

        if(canMove(x, newY)){
            y = newY;
        }

    }
    public boolean canMove(float newX, float newY){
        return newX  >= 0 && newX + width <= 800 && newY >= 0 && newY + height<= 600;
    }

    public void draw(ShapeRenderer shape){
        if(!isAlive) return;

        if(state == PlayerState.ROLL) {
            shape.setColor(0.3f, 0.8f, 1, 1);
            shape.rect(x, y, width - 4, height - 4);
        }else {
            shape.setColor(0, 0, 1, 1);
            shape.rect(x, y, width, height);
        }
    }
}
