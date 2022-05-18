package nudeltruppe.userinterface;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputAdapter;

import nudeltruppe.game.GameField;
import nudeltruppe.utils.Log;


public class GUI extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private final int width = 14;
	private final int height = 6;
	private final int button_size = 50;
	private final int poop_count = 20;

	private final JPanel contentPanel = new JPanel();
	private JButton[][] buttons;

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


		buttons = new JButton[width][height];
		
		for (int i = 0; i < width; i++) 
		{
			for (int k = 0;k < height;k++)
			{
				buttons[i][k] = new JButton(" ");
				buttons[i][k].setBounds(i*button_size, k*button_size, button_size, button_size);
				contentPanel.add(buttons[i][k]);

				buttons[i][k].addMouseListener(new MouseInputAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int x = (int) ( ((JButton)e.getSource()).getX() / button_size);
						int y = (int) ( ((JButton)e.getSource()).getY() / button_size);
	
						Log.log("x: " + x + " y: " + y);

						try
						{
							if (e.getButton() == MouseEvent.BUTTON1)
							{
								game_field.setFlaggedForField(x, y, false);
							}
							else if (e.getButton() == MouseEvent.BUTTON3)
							{
								game_field.setFlaggedForField(x, y, true);
							}
						}
						catch (IllegalArgumentException ex)
						{
							Log.log("IllegalArgumentException: " + ex.getMessage());
						}

						update();
					}
				});
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
						buttons[i][k].setText(" ");
						break;
					case FLAGGED:
						buttons[i][k].setText("F");
						break;
					case FAKE_FLAGGED:
						buttons[i][k].setText("f");
						break;
					case POOPED:
						buttons[i][k].setText("P");
						break;
					case CLEAN:
						buttons[i][k].setText("C");
						break;
				}
			}
		}
	}
}
