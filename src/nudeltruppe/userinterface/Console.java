package nudeltruppe.userinterface;

import nudeltruppe.game.GameField;
import nudeltruppe.game.PoopsInCloseProximity;
import nudeltruppe.utils.Log;

public class Console {
	public static void main(String[] args) {
		Log.log("Hello World!");

		GameField gf = new GameField(16, 4);
		PoopsInCloseProximity hf = new PoopsInCloseProximity();

		Log.log("Width: " + gf.getWidth());
		Log.log("Height: " + gf.getHeight());

		gf.plantPoops(gf.getHeight() * gf.getWidth());

		Log.log(String.format("Found %d Poops surrounding [%d, %d]", hf.getPoopsInCloseProximityIndices(14, 0, gf), 0, 0));

	}
}
