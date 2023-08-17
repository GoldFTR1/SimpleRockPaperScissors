import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Driver {
	public static void main(String args[]) {
	//DisplayGames.DisplayGameHistory();
	Game ThisGame = new Game();
	
	ThisGame.setGameType(Game.Begin());
	
	if(ThisGame.getGameType() == Game.GameType.SINGLEGAME) {
	
	Game.PlaySingleGame(ThisGame);
	
	}else {
		ThisGame.setId(Game.GenerateId("games.ser"));
		Game.BestOfThree(ThisGame);
		System.out.println("Game id is" + ThisGame.getId());
		Game.WriteGameToFile("games.ser", ThisGame);
	}
	
	
	
	}
}
