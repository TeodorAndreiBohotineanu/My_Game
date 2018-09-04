package Tiles;

import gfx.Assets;


public class SeaTile extends Tile
{
    public SeaTile( int id) {
        super(Assets.sea, id);
    }
    @Override
    public boolean Solid ( )
    {
        return true;
    }
}
