package Entities.Statics;

import Main.Handler;
import gfx.Assets;

import java.awt.*;

import static Tiles.Tile.THEIGHT;
import static Tiles.Tile.TWIDTH;

public class Tree extends StaticEntity
{

    public Tree(Handler handler, float x, float y)
    {
        super(handler, x, y, TWIDTH, THEIGHT * 2);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics graph)
    {
        graph.drawImage(Assets.tree,( int ) ( x - handler.getGameCamera().getxOffset()),( int ) ( y -handler.getGameCamera().getyOffset()),width,height,null);
    }

    @Override
    public void dead( ) {

    }
}

