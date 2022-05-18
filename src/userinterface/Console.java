package userinterface;

import nudeltruppe.game.GameField;

public class Console {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		GameField gf = new GameField();

		System.out.println("Width: " + gf.getWidth());
		System.out.println("Height: " + gf.getHeight());

		gf.plantPoops(20);

		gf.dbg();
	}
}
