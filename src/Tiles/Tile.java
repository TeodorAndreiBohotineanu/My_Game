package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile =  new GrassTile(0);
    public  static Tile treeTile = new TreeTile(1);
    public static Tile floorTile = new FloorTile(2);
    public static Tile airTile = new AirTile(3);
    public static Tile cloudsTile = new CloudsTile(4);
    public static Tile Obstacles = new ObstacleTile (5);
    public static Tile Sea = new SeaTile(6);
    public static Tile DeepSea = new DeepSeaTile (7);
    public static Tile dirtTile = new DirtTile(8);
    public static Tile blackFloor = new BlackTile (9);
    public static Tile ladderTile = new LadderTile(10);
    public static Tile InsideLadderTile = new Ladder2Tile(11);
    public static Tile spikeTile= new SpikeTile(12);
    public static Tile tableaTile = new TableaTile(101);
    public static Tile tablebTile = new TablebTile (102);
    public static Tile tablecTile = new TablecTile (103);
    public static Tile tabledTile = new TabledTile (104);
    public static Tile batTile = new BatTile (13);
    public static Tile spike2Tile = new Spike2Tile (14);
    protected  BufferedImage texture;
    public static final int TWIDTH=42, THEIGHT=42;
    protected final int id; /// E final pentru ca fiecare tile are un ID unic, sa nu fie confuzie and stuff
    public Tile (BufferedImage texture, int id )
    {
        this.texture = texture;
        this.id = id;
        tiles[id]=this;
    }
    public void update ( )
    {

    }
     public void render (Graphics g, int x, int y )
     {
         g.drawImage(texture,x,y,TWIDTH,THEIGHT,null);
     }
     public boolean Solid ( )
     {
         return false;
     }
    public int GetID ( )
    {
        return id;
    }
}
