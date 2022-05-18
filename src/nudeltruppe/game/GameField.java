package nudeltruppe.game;

public class GameField {
	public enum FieldType {
		EMPTY,
		FLAGGED,
		FAKE_FLAGGED,
		POOPED,
		CLEAN
	};

	private FieldType[][] game_field; 

	public GameField(int width, int height) {
		game_field = new FieldType[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				game_field[i][j] = FieldType.EMPTY;
			}
		}
	}

	public GameField() {
		this(20, 20);
	}

	public void plantPoops(int amount) {
		for (int i = 0; i < amount; i++) {
			int x = (int) (Math.random() * game_field.length);
			int y = (int) (Math.random() * game_field[0].length);
			if (game_field[x][y] == FieldType.EMPTY) {
				game_field[x][y] = FieldType.POOPED;
			} else {
				i--;
			}
		}
	}

	public void dbg() {
		for (int i = 0; i < game_field.length; i++) {
			for (int j = 0; j < game_field[0].length; j++) {
				switch (game_field[i][j]) {
					case EMPTY:
						System.out.print(".");
						break;
					case FLAGGED:
						System.out.print("F");
						break;
					case FAKE_FLAGGED:
						System.out.print("f");
						break;
					case POOPED:
						System.out.print("P");
						break;
					case CLEAN:
						System.out.print("C");
						break;
				}
			}
			System.out.println();
		}
	}


	public int getWidth() {
		return game_field.length;
	}

	public int getHeight() {
		return game_field[0].length;
	}

	public FieldType[][] getField() {
		return game_field;
	}

	public void setFlaggedForField(int x, int y, boolean flagged) {
		if (!flagged) {
			if (game_field[x][y] == FieldType.FLAGGED) {
				game_field[x][y] = FieldType.POOPED;
			} else if (game_field[x][y] == FieldType.FAKE_FLAGGED) {
				game_field[x][y] = FieldType.EMPTY;
			} else {
				throw new IllegalArgumentException("Field is not flagged");
			}
		} else {
			if (game_field[x][y] == FieldType.POOPED) {
				game_field[x][y] = FieldType.FLAGGED;
			} else if (game_field[x][y] == FieldType.EMPTY) {
				game_field[x][y] = FieldType.FAKE_FLAGGED;
			} else {
				throw new IllegalArgumentException("Field is not empty");
			}
		}
	}

	public FieldType getFieldType(int x, int y) {
		return game_field[x][y];
	}
}