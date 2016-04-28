import imacstadium.game.Game;
/**
 * <b>ImacStadium is the main class</b>
 * <p>
 * It initialise and executes the Game.
 * </p>
 * @see Game
 */
public class ImacStadium {
	public static void main(String[] argv){
		Game game = Game.getInstance();
		System.out.println("Execute");
		game.execute();
	}
}
