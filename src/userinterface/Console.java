package userinterface;

import nudeltruppe.game.GameField;
import nudeltruppe.game.HundehaufenInCloseProximity;

public class Console {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		GameField gf = new GameField();
		HundehaufenInCloseProximity hf = new HundehaufenInCloseProximity();

		System.out.println("Width: " + gf.getWidth());
		System.out.println("Height: " + gf.getHeight());

		gf.plantPoops(gf.getHeight() * gf.getWidth());

		gf.dbg();

		System.out.printf("Found %d Poops surrounding [%d, %d]", hf.GetHundehaufenInCloseProximityIndices(19, 0, gf), 0, 0);

	}
}
