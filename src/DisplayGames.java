import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DisplayGames {
	
	public static void DisplayGameHistory() {
		
		ArrayList<Game> ListOfGames = new ArrayList<Game>();
		
		try {
			FileInputStream filein = new FileInputStream("games.ser");
			ObjectInputStream objin = new ObjectInputStream(filein);
			
			while(true) {
				try {
					Game game = (Game) objin.readObject();
					ListOfGames.add(game);
				}catch(EOFException e) {
					e.printStackTrace();
					break;
				}
			}
			
		for(Game game : ListOfGames) {
			System.out.println("IDs are" + game.getId() + game.getResult());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
