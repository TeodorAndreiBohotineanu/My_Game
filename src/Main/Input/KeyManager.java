package Main.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    public boolean Up, Down, Left, Right, Jump;
    public boolean aUp,aDown,aLeft,aRight; /// attack up/down/left/right;
    private boolean[] Keys;
    public KeyManager( )
    {
        Keys = new boolean[256];
    }
    public  void update ( )
    {
        Up = Keys[KeyEvent.VK_W]; /// cand se apasa W se inregistreaza
        Down = Keys[KeyEvent.VK_S];
        Left = Keys[KeyEvent.VK_A];
        Right = Keys[KeyEvent.VK_D];
        Jump = Keys[KeyEvent.VK_SPACE];

        aUp = Keys[KeyEvent.VK_UP];
        aDown = Keys[KeyEvent.VK_DOWN];
        aLeft = Keys[KeyEvent.VK_LEFT];
        aRight = Keys[KeyEvent.VK_RIGHT];
    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        Keys[e.getKeyCode( )] = true;
        System.out.println("Pressed ");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keys[e.getKeyCode( )] = false;
    }
}