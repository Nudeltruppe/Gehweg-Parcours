package nudeltruppe.game;

import nudeltruppe.game.GameField.FieldType;
import nudeltruppe.utils.Vector2D;

// Class for game logic
public class GameLogic
{
	public Player player = new Player();

	public void checkForDeath(Vector2D<Integer> v, GameField field)
	{
		if (field.getField()[v.x][v.y].equals(FieldType.POOPED))
		{
			player.setGameStatusAlive(false);
		}
	}
}