package Main;

import Main.Game;

public class Launcher {
    public static void main(String[] args) {
        Game game =  new Game("The Medieval Sorcerer", 1280,720);
        game.start();
        Sound.sound1.loop();
    }
}
