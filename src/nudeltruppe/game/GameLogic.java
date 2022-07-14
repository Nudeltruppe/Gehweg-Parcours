package nudeltruppe.game;

import nudeltruppe.game.GameField.FieldType;
import nudeltruppe.userinterface.GUI;
import nudeltruppe.utils.Vector2D;
import javax.swing.JOptionPane;
import java.awt.Component;

// Class for game logic
public class GameLogic
{
	public Player player = new Player();
	public PoopsInCloseProximity poops = new PoopsInCloseProximity();

	public void checkForDeath(Vector2D<Integer> v, GameField field)
	{
		if (field.getField()[v.x][v.y].equals(FieldType.POOPED))
		{
			player.setGameStatusAlive(false);
		} else if (field.getField()[v.x][v.y].equals(FieldType.FLAGGED)) {
			player.setGameStatusAlive(false);
		} else {
			field.clearField(this.player.getPosition());
		}
	}

	public void handleDeath(final GUI my_gui) {
		Component frame = null;
		JOptionPane.showMessageDialog(frame, "Sie haben verloren");

		my_gui.setShowPoops(true);

		new Thread() {
			@Override
			public void run() {
				for (int i = 3; i > 0; i--) {
					my_gui.setTitle(GUI.window_name + " | Restart in " + i + " seconds");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				my_gui.restart();
			}
		}.start();
	}

	public void handleWon(GUI my_gui) {
		Component frame = null;
		JOptionPane.showMessageDialog(frame, "Sie haben gewonnen!!!");
		my_gui.restart();
	}
}