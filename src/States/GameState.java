package States;

import Entities.Creatures.Player;
import Entities.Statics.Tree;
import Main.Handler;
import Worlds.World;

import java.awt.*;
public class GameState extends State
{
    private World world;
    public GameState (Handler handler) /// constructor
    {
        super(handler);
        world = new World(handler,"resources/worlds/level2.txt");
        handler.setWorld(world);
    }
    @Override
    public void update( )
    {
        world.update( );
    }

    @Override
    public void render(Graphics graph)
    {
        world.render(graph);
    }
}
