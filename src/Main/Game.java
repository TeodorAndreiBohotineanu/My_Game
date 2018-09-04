package Main;

import Display.Display;
import Main.Input.KeyManager;
import Main.Input.MouseManager;
import States.GameState;
import States.MenuState;
import States.State;
import gfx.Assets;
import gfx.GameCamera;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
public class Game implements  Runnable
{

    private Display Display;
    private int width,height;
    public String Title;

    private  boolean IsRunning = false;

    private BufferStrategy bufferStrat;
    private Graphics graph;
    private BufferedImage TestImage;


    private Thread thread; /// pentru optimizare
    public State gameState;
    public State MenuState;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private GameCamera gameCamera;
    private Handler handler;
    public Game(String Title, int width, int height )
    {
        this.width = width;
        this.height = height;
        this.Title = Title;
        keyManager = new KeyManager( );
        mouseManager = new MouseManager( );
    }
    private void init  ( ) /// se va apela o singura data, va initializa display, fereastra si tot ce trebe
    {
        Assets.init(); /// optimizare
        handler = new Handler(this);
        gameState = new GameState(handler);
        MenuState= new MenuState(handler);
        State.SetState(gameState);
        gameCamera = new GameCamera(handler,0,0);
/// Game Loop
        /// Mai intai se face update la variabile, locatii, obiecte and so on
        /// Apoi pune totul in fereastra
        /// Dupa o ia de la capat, update, draw ....
        Display = new Display( Title,width,height ); /// cand se creeaza o instanta Game o sa fie creat si display-ul
        Display.getFrame( ).addKeyListener(keyManager);
        Display.getFrame( ).addMouseListener(mouseManager);
        Display.getFrame( ).addMouseMotionListener(mouseManager);
        Display.getCanvas( ).addMouseListener(mouseManager);
        Display.getCanvas( ).addMouseMotionListener(mouseManager);
        String current = System.getProperty("user.dir");
      /// TestImage=ImageLoader.LoadImage("../textures/autismo.jpg"); /// importeaza imaginea din folder..
    }
    private  void update ( )
    {
        keyManager.update();
        if (State.getState( )!= null)
        State.getState().update();
    }
    private void render ( )
    {
        bufferStrat = Display.getCanvas( ).getBufferStrategy ( ); /// o sa seteze bufferStrat la ce e currently
        /// la buffer strat al canvas-ului
        /// Buffer e un computer-ish, un computer in computer, o zona care are in memorie ce e afisat pe ecran
        /// jocurile vechi aveau un fel de flicker din cand in cand pentru ca era pus direct pe ecran
        if (bufferStrat == null ) /// daca canvas-ul nu are niciun buffer pana acuma, il creez
        {
            Display.getCanvas().createBufferStrategy(3); /// e recomandat sa fie doar 3 buffers
            return ;
        }
        graph = bufferStrat.getDrawGraphics(); /// asta ma lasa sa pun pe canvas, display chestii
        /// Clrscr
        graph.clearRect(0,0,width,height); /// cu width si height se sterge tot ecranul
        /// Start draw , se deseneaza pe pixeli, primul pixel, (0,0) e coltul stanga sus, axa y creste de sus in jos
        ///graph.drawImage(TestImage,50,50,null);
        if (State.getState( )!= null)
            State.getState().render(graph);
        /// Ending draw
        bufferStrat.show( );
        graph.dispose( ); /// ca sa se inchida desenarea
    }
    @Override
    public void run() { /// se executa prima
        init ( );
        int fps = 60;
        double TimePerTick=1000000000/fps; /// timpul il masor in nano-secunde, pentru precizie
        double Delta = 0; /// cat timp e nevoie pana se face call la update si render
        long Now;
        long LastTime = System.nanoTime(); /// returneaza in nano-secunde cat timp ruleaza
        long Timer =0;
        int Ticks = 0;
        while (IsRunning)
        {
            Now = System.nanoTime(); /// Now - timpul curent in nano-secunde
            Delta += (Now - LastTime) / TimePerTick;
            Timer += Now - LastTime; /// cat timp a trecut de cand am foloit metoda
            LastTime=Now;
            if (Delta >= 1) /// daca e mai mare ca 1, atunci trebuie scazut, ca sa ajung la 60 fps
            {
                update();
                render();
                Ticks++;
                Delta--;
            }
            if (Timer>= 1000000000)
            {
                System.out.println("Time and Ticks : " + Ticks);
                Ticks=0;
                Timer = 0;
            }
        }
        stop ( ); /// in caz de orice, sa se opreasca metoda

    }
    public KeyManager getKeyManager( )
    {
        return keyManager;
    }
    public MouseManager getMouseManager ( )
    {
        return mouseManager;
    }
    public GameCamera getGameCamera( )
    {
        return  gameCamera;
    }
    public int getWidth( )
    {
        return width;
    }
    public int getHeight ( )
    {
        return height;
    }
    public synchronized void  start ( ) /// se foloseste synchronized cand le lucreaza direct cu un thread;
    {
        if (IsRunning)
            return ; /// daca merge deja sar peste tot, altfel s-ar strica tot;
        IsRunning = true;
        thread = new Thread(this);
        thread.start( ); /// ca sa porneasca thread-ul si apeleaza metoda "run" de mai sus;
    } /// o sa porneasca thread-ul
    public  synchronized  void stop ( )
    {
        if (!IsRunning)
            return ; /// daca jocul e deja oprit for some reason
        IsRunning = false;
        try {
            thread.join( ); /// trebuie pus try, catch, altfel nu merge;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } /// o sa opreasca thread-ul
}
