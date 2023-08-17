import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Game implements Serializable {
	int id;
	enum GameType{
		SINGLEGAME,
		BO3
	};
	GameType gameType;
	ArrayList<Move> P1Move = new ArrayList<Move>();
	ArrayList<Move> P2Move = new ArrayList<Move>();
	
	String result;
	int counter;
	
	
	public Game() {
		this.id=id;
		this.gameType = gameType;
		this.P1Move=P1Move;
		this.P2Move=P2Move;
		this.result=result;
		this.counter=counter;
		
	}
	
	public static GameType Begin() {
		System.out.println("Welcome! Choose your oponent");
		GameType gametype = null;
		Scanner a = new Scanner(System.in);
		
		try {
		System.out.println("Enter 1 for a Single Game, enter 2 for Best of Three" );
		int scanned = a.nextInt();
		
		switch(scanned) {
		case 1:
			gametype = GameType.SINGLEGAME;
			break;
		
		case 2:
			gametype = GameType.BO3;
			break;
			
		default:
			throw new InvalidGameTypeException();
		}
		
		}catch(InvalidGameTypeException e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
		return gametype;
		
	}
	
	public static String TheGame(Move P1, Move P2) {
		String result="";
		switch(P1.getMoveType()) {
		case ROCK:
			if(P2.getMoveType().toString()=="ROCK") {
			result="tie!";
			}
			else if(P2.getMoveType().toString()=="PAPER") {
			result="P2 Wins!";
			}
			else if(P2.getMoveType().toString()=="SCISSORS") {
			result="P1 Wins!";}
			break;
		case PAPER:
			if(P2.getMoveType().toString()=="ROCK")
				result="P1 Wins!";
				else if(P2.getMoveType().toString()=="PAPER")
				result="tie!";
				else if(P2.getMoveType().toString()=="SCISSORS")
				result="P2 Wins!";
			break;
		case SCISSORS:
			if(P2.getMoveType().toString()=="ROCK")
				result="P2 Wins!";
				else if(P2.getMoveType().toString()=="PAPER")
				result="P1 Wins!";
				else if(P2.getMoveType().toString()=="SCISSORS")
				result="tie!";
			break;
		}
		System.out.println(P1.getMoveType().toString());
		System.out.println(P2.getMoveType().toString());
		System.out.println(result);
		return result;
	}
	
	public static Game SingleGame(Game game) {
		
		if(game.gameType == GameType.SINGLEGAME) {
			System.out.println("This works");
			game.setCounter(1);
			
			game.setMove(game.P1Move,Move.P1ChooseMove(),game.counter-1);
			game.setMove(game.P2Move,Move.PVCChooseMove(),game.counter-1);
			game.setResult(TheGame(game.P1Move.get(game.counter-1),game.P2Move.get(game.counter-1)));
			//game.se(TheGame(game.P1Move.get(game.counter),game.P2Move.get(game.counter));
			while(game.result=="tie!") {
				
				game.counter++;
				game.setMove(game.P1Move,Move.P1ChooseMove(),game.counter-1);
				game.setMove(game.P2Move,Move.PVCChooseMove(),game.counter-1);
				game.setResult(TheGame(game.P1Move.get(game.counter-1),game.P2Move.get(game.counter-1)));
				
			}
			
			
		}
		return game;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public ArrayList<Move> getP1Move() {
		return P1Move;
	}

	/*public void setP1Move(Move p1Move) {
		P1Move = p1Move;
	}

	public Move getP2Move() {
		return P2Move;
	}
*/
	
	public void setMove(ArrayList<Move> p2Move,Move P2Move, int counter) {
		p2Move.add(counter, P2Move);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public static Game loadGames(String filename){
		Game lastGame = new Game();
		//lastGame.setId(0);
		
		
		
		try {
			File file = new File(filename);
			
			if(file.exists() && file.length()>0) {
				
			
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			
			Game currentGame;
			
			 while (true) {
		            try {
		                currentGame = (Game) ois.readObject();
		                if (currentGame == null) {
		                    break; // Reached end of file
		                }
		                lastGame = currentGame;
		            } catch (EOFException e) {
		                break; // Reached end of file
		            }
		        }
			}
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return lastGame;
		
		
		
		
		
		
		//ArrayList<Game> ListOfGames = new ArrayList<Game>();
		/*try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("games.ser"));
			Game readGame = (Game) ois.readObject();
			 System.out.println("Game object read from file:");
	         System.out.println("ID: " + readGame.id);
	         System.out.println("Game Type: " + readGame.gameType);
	         System.out.println("Player 1 Move: " + readGame.P1Move);
	         System.out.println("Player 2 Move: " + readGame.P2Move);
	         System.out.println("Result: " + readGame.result);
	         System.out.println("Counter: " + readGame.counter);
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/ //return ListOfGames;
	
	}
	
	public static int GenerateId(String filename) {
		Game lastGame = loadGames(filename);
		//System.out.println(lastGame.getId());
		int id= lastGame.id++;
		if(lastGame.getId()==0) {
			
		}
		
		System.out.println(lastGame.id);
		return lastGame.id;
		
	}
	
	public static void WriteGameToFile(String filename, Game Game) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("games.ser"));
			oos.writeObject(Game);
			System.out.println("Object written to file");
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void PlaySingleGame(Game ThisGame) {

		ThisGame.setId(Game.GenerateId("games.ser"));
		
		System.out.println(ThisGame.getGameType().toString());
		
		Game.SingleGame(ThisGame);
		Game.WriteGameToFile("games.ser", ThisGame);
	}
	
	public static void PlayBestOfThree(Game ThisGame) {
		ThisGame.setId(Game.GenerateId("games.ser"));
	}
	
	public static Game BestOfThree(Game game) {
			int P1Wins=0;
			int P2Wins=0;
			
			game.setCounter(0);
			
			while(P1Wins <2 && P2Wins < 2) {
			game.setMove(game.P1Move,Move.P1ChooseMove(),game.counter);
			game.setMove(game.P2Move,Move.PVCChooseMove(),game.counter);
			game.setResult(TheGame(game.P1Move.get(game.counter),game.P2Move.get(game.counter)));
			 game.setCounter(game.getCounter() + 1);
			if(game.result=="P1 Wins!") {
				P1Wins++;
				
			}
			else if(game.result=="P2 Wins!") {
				P2Wins++;
			
			}
			
			while(game.result=="tie!") {
				
				
				game.setMove(game.P1Move,Move.P1ChooseMove(),game.counter);
				game.setMove(game.P2Move,Move.PVCChooseMove(),game.counter);
				game.setResult(TheGame(game.P1Move.get(game.counter),game.P2Move.get(game.counter)));
				game.setCounter(game.getCounter() + 1);
				if(game.result=="P1 Wins!") {
					P1Wins++;
					
				}
				else if(game.result=="P2 Wins!") {
					P2Wins++;
					
				}
					}
			
			
			}
		if(P1Wins==2) {
			game.setResult("P1 Wins!");
			
		}
		else if(P2Wins==2) {
			game.setResult("P2 Wins!");
		}
			
		System.out.println(game.getResult());
		
		return game;
		
	}

}
