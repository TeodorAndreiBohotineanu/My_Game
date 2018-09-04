package States;

import Entities.Statics.StaticEntity;
import Main.Game;
import Main.Handler;
import Main.Sound;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import gfx.Assets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MenuState  extends  State
{
    private UIManager uiManager;
    public  MenuState (Handler handler)
    {
        super (handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager( ).setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(5,650,129,64, Assets.button_start, new ClickListener( ) {
            @Override
            public void onClick( )
            {
                handler.getMouseManager().setUIManager(null);
                State.SetState(handler.getGame( ).gameState);
            }
        }));
    }
    @Override
    public void update( )
    {
        uiManager.update( );

    }

    @Override
    public void render(Graphics graph)
    {
        uiManager.render(graph);
    }
    }
