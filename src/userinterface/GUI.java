package userinterface;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nudeltruppe.game.GameField;


public class GUI extends JDialog
{
	
	private static final long serialVersionUID = 1L;
	
	private final int width = 14;
	private final int height = 6;
	private final int button_size = 50;
	private final int poop_count = 20;

	private final JPanel contentPanel = new JPanel();
	private JButton[][] butons;

	private GameField game_field;

	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		try
		{
			GUI dialog = new GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public GUI()
	{
		game_field = new GameField(width, height);
		game_field.plantPoops(poop_count);


		setBounds(0, 0, width * button_size, (height + 1) * button_size);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		butons = new JButton[width][height];
		
		for (int i = 0; i < width; i++) 
		{
			for (int k = 0;k < height;k++)
			{
				butons[i][k] = new JButton(" ");
				butons[i][k].setBounds(i*button_size, k*button_size, button_size, button_size);
				contentPanel.add(butons[i][k]);
			}
		}

		update();
	}

	private void update()
	{
		for (int i = 0; i < width; i++) 
		{
			for (int k = 0;k < height;k++)
			{
				switch (game_field.getFieldType(i, k))
				{
					case EMPTY:
						butons[i][k].setText(" ");
						break;
					case FLAGGED:
						butons[i][k].setText("F");
						break;
					case FAKE_FLAGGED:
						butons[i][k].setText("f");
						break;
					case POOPED:
						butons[i][k].setText("P");
						break;
					case CLEAN:
						butons[i][k].setText("C");
						break;
				}
			}
		}
	}
}
