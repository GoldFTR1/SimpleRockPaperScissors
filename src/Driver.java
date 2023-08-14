import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Driver {
	public static void main(String args[]) {
	Game ThisGame = new Game();
	ThisGame.setGameType(Game.Begin());
	
	ThisGame.setId(Game.GenerateId("games.ser"));
	
	System.out.println(ThisGame.getGameType().toString());
	ThisGame.counter=1;
	Game.SingleGame(ThisGame);
	
	System.out.println("Counter is " + ThisGame.counter);
	Game.WriteGameToFile("games.ser", ThisGame);
	
	
	}
}
