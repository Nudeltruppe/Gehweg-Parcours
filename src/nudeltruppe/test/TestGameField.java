package nudeltruppe.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nudeltruppe.game.GameField;
import nudeltruppe.game.GameField.FieldType;

public class TestGameField
{
	@Test
	public void test()
	{
		GameField field = new GameField(5, 5);
		
		field.setFieldType(0, 0, FieldType.POOPED);
		field.setFieldType(1, 1, FieldType.POOPED);
		field.setFieldType(2, 2, FieldType.POOPED);

		field.setFlaggedForField(0, 0, true);
		field.setFlaggedForField(1, 1, true);

		assertEquals(FieldType.FLAGGED, field.getFieldType(0, 0));
		assertEquals(FieldType.FLAGGED, field.getFieldType(1, 1));
		assertEquals(FieldType.POOPED, field.getFieldType(2, 2));
	}
}
