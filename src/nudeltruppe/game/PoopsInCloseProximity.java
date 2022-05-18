package nudeltruppe.game;

import nudeltruppe.game.GameField.FieldType;

public class PoopsInCloseProximity extends GameLogic {
	// Returns the Number of poops found in close proximity
	public int getPoopsInCloseProximityIndices(int position_x, int position_y, GameField field) {
		int found_poops = 0;
			
		int offsetX1 = 1;
		int offsetX2 = 1;
		int offsetY1 = 1;
		int offsetY2 = 1;

		if (position_x == 0)
		{
			offsetX1 = 0;
			offsetX2 = 1;
		}
		else if (position_x == field.getHeight() - 1)
		{
			offsetX1 = 1;
			offsetX2 = 0;
		}

		if (position_y == 0)
		{
			offsetY1 = 0;
			offsetY2 = 1;
		}
		else if (position_y == field.getWidth() - 1)
		{
			offsetY1 = 1;
			offsetY2 = 0;
		}


		for (int y = position_y - offsetY1; y <= position_y + offsetY2; y++)
		{
			for (int x = position_x - offsetX1; x <= position_x + offsetX2; x++)
			{
				if (x == position_x && y == position_y)
				{
					continue;
				}
		
				if (field.getFieldType(x, y) == FieldType.POOPED)
				{
					found_poops++;
				}
			}
		}

		return found_poops;
	}
}
