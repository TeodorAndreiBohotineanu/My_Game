package Tiles;

import gfx.Assets;


public class DeepSeaTile extends Tile {
    public DeepSeaTile(int id)
    {
        super(Assets.deepsea, id);
    }
    @Override
    public boolean Solid ( )
    {
        return true;
    }
}