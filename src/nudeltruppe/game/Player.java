package nudeltruppe.game;

import nudeltruppe.utils.Vector2D;

public class Player 
{
	private Vector2D<Integer> player_position = new Vector2D<Integer>(0, 0);
	private boolean game_status_alive = true;

	public void updatePosition(int x, int y) 
	{
		this.player_position.x = x;
		this.player_position.y = y;
	}

	public Vector2D<Integer> getPosition()
	{
		return this.player_position;
	}
}
