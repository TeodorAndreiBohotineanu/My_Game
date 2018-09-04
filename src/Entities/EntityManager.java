package Entities;

import Entities.Creatures.Player;
import Main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private ArrayList <Entity> entities; /// Array-ul o sa tina toate Entitie-urile, ii mai bun pentru ca nu are un size predefinit, se pot pune
    private Comparator<Entity> renderSorter = new Comparator<Entity>( ){

        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY ( ) + a.getHeight() <b.getY ( ) + b.getHeight( ))
                return -1;
            return 1;
        }
    };
    public EntityManager (Handler handler, Player player  )
    {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }
    public void update ( )
    {
        Iterator<Entity> it = entities.iterator();
       while (it.hasNext()) /// cat timp inca sunt chestii in iterator
        {
            Entity e = it.next();
            e.update();
            if (!e.isActive())
                it.remove();
        }
        entities.sort(renderSorter);

    }
    public void render (Graphics graphics)
    {
        for ( int i = 0 ; i < entities.size( ) ; i ++ )
        {
            Entity e = entities.get(i);
            e.render(graphics);
        }
    }
    public void addEntity (Entity e)
    {
        entities.add(e); /// il pune la ArrayList
    }
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
