package Tiles;

import gfx.Assets;

public class GrassTile extends  Tile {
    public GrassTile(int id)
    {
        super(Assets.grass, id);
    }
    @Override
    public boolean Solid ( )
    {
        return true;
    }
}