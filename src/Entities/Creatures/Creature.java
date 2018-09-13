package Entities.Creatures;

import Entities.Entity;
import Main.Handler;
import Tiles.Tile;

public abstract class Creature extends Entity
{
    public static final float DEFAULT_SPEED=3.5f;
    public static final int DEFAULT_CREATURE_Enemy_WIDTH=36,
            DEFAULT_CREATURE_Enemy_HEIGHT=51;
    public static final int DEFAULT_Player_WIDTH=38,
            DEFAULT_Player_HEIGHT=54;
    protected float Speed;
    protected float xMove, yMove;
        protected float maxMoveY;
    protected float gravity;
    protected boolean falling;
    protected boolean jumping;
    public Creature(Handler handler, float x, float y, int width, int height)
    {
        super(handler,x, y,width,height); /// super duce la ce am dat extend, aici Entity
        Speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }
    public void Move ( )
    {
        if (!checkEntityCollisions(xMove,0f))
        {
            moveX();
        }
        if (!checkEntityCollisions(0f,yMove))
        moveY();
    }
    public void moveX(){
        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TWIDTH) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TWIDTH)){
                x += xMove;
            }else{
                x = tx * Tile.TWIDTH - bounds.x - bounds.width - 1;
            }

        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TWIDTH) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TWIDTH)){
                x += xMove;
            }else{
                x = tx * Tile.TWIDTH + Tile.TWIDTH - bounds.x;
            }

        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TWIDTH;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.THEIGHT + Tile.THEIGHT - bounds.y;
            }

        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.THEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.THEIGHT, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.THEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }
    protected boolean collisionWithTile (int x, int y)
    {
            return handler.getWorld().getTile(x,y).Solid();
    }
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getSpeed() {
        return Speed;
    }

    public void setSpeed(float speed) {
        Speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
