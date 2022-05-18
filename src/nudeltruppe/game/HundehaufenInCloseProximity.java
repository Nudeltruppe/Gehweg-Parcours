package nudeltruppe.game;

import nudeltruppe.game.GameField.FieldType;

public class HundehaufenInCloseProximity extends GameLogic {
	// Returns the Number of Hundehaufen found in close proximity
	public int GetHundehaufenInCloseProximityIndices(int PositionX, int PositionY, GameField Field) {
		int FoundPoops = 0;
        int OffsetX1 = 1;
        int OffsetX2 = 1;
        int OffsetY1 = 1;
        int OffsetY2 = 1;

        if (PositionX == 0) {
            OffsetX1 = 0;
            OffsetX2 = 1;
        } else if (PositionX == Field.getHeight() - 1) {
            OffsetX1 = 1;
            OffsetX2 = 0;
        }

        if (PositionY == 0) {
            OffsetY1 = 0;
            OffsetY2 = 1;
        } else if (PositionY == Field.getWidth() - 1) {
            OffsetY1 = 1;
            OffsetY2 = 0;
        }


		for (int y = PositionY - OffsetY1; y <= PositionY + OffsetY2; y++) {
			for (int x = PositionX - OffsetX1; x <= PositionX + OffsetX2; x++) {
                if (x == PositionX && y == PositionY) continue;
				if (Field.getFieldType(x, y) == FieldType.POOPED) {
                    FoundPoops++;
				}
			}
		}

		return FoundPoops;
	}
}
