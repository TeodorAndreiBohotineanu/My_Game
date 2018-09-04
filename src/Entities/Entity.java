package Entities;

import Main.Handler;

import java.awt.*;

public abstract class Entity
{
    protected float x,y; /// protected -> e ca si privat doar ca acele clase care extind Entities au acces la ea
    protected int width, height;
    protected Handler handler;
    protected boolean active = true;
    protected Rectangle bounds;
    protected int HP;
    protected boolean gravity;
    protected boolean canJump = false;
    public static final int DEFAULT_HP=10;

    public Entity(Handler handler, float x, float y, int width, int height)
    {
      ///  gravity = 0.5f;
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        HP = DEFAULT_HP;
        bounds = new Rectangle(0,0,width,height); /// hit box-ul o sa fie toata entitatea
    }
    public abstract void update ( );
    public abstract void render (Graphics graph);
    public boolean checkEntityCollisions (float xOffset, float yOffset )
    {
        for (Entity e : handler.getWorld( ).getEntityManager( ).getEntities( ))
        {
            if (e.equals(this))
            {
                continue; /// nu verificam coliziunea cu obiectul insusi
            }
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
            {
                canJump = true;
                return true;
            }
        }
            return false;
    }
    public abstract void dead ( );
    public void hurt ( int amt )
    {
         HP = HP - amt;
         if (HP <= 0 )
         {
             active = false;
             dead ( );
         }
    }
    public Rectangle getCollisionBounds (float xOffset, float yOffset )
    {
        return new Rectangle((int) (x + bounds.x +xOffset) , (int) (y +bounds.y + yOffset), bounds.width, bounds.height);
    }
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
