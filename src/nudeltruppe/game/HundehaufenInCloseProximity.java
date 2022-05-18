package nudeltruppe.game;

import nudeltruppe.game.GameField.FieldType;

public class HundehaufenInCloseProximity extends GameLogic {
	// Returns the Number of Hundehaufen found in close proximity
	public int GetHundehaufenInCloseProximityIndices(int PositionX, int PositionY, GameField Field) {
		int FoundPoops = 0;

		for (int y = PositionY - 1; y <= PositionY + 1; y++) {
			for (int x = PositionX - 1; x <= PositionX + 1; x++) {
                if (x == PositionX && y == PositionY) continue;
				if (Field.getFieldType(x, y) == FieldType.POOPED) {
                    FoundPoops++;
				}
			}
		}

		return FoundPoops;
	}
}
