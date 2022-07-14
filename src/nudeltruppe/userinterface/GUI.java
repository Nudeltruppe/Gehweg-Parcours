package nudeltruppe.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputAdapter;

import nudeltruppe.game.GameField;
import nudeltruppe.game.GameLogic;
import nudeltruppe.game.GameField.FieldType;
import nudeltruppe.utils.ArgParser;
import nudeltruppe.utils.Log;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static int width = 14;
	private static int height = 6;
	private static final int button_size = 50;
	private static int poop_count = 20;

	private final JPanel contentPanel = new JPanel();
	private JButton[][] buttons;

	private GameField game_field;
	private GameLogic game_logic;

	private static Thread t;

	private static boolean noemoji = false;
	private static boolean showpoops = false;
	private boolean showpoops2 = false;
	private boolean first_move = true;

	public static void main(String[] args) throws InterruptedException {
		ArgParser parser = new ArgParser(args);
		parser.parse();

		if (parser.is_option("-noemoji")) {
			noemoji = true;
		}

		if (parser.is_option("-showpoops")) {
			showpoops = true;
		}

		if (parser.is_option("-width")) {
			String width_s = parser.consume_option("-width");
			width = Integer.parseInt(width_s);
			Log.log("Width: " + width);
		}

		if (parser.is_option("-height")) {
			String height_s = parser.consume_option("-height");
			height = Integer.parseInt(height_s);
			Log.log("Height: " + height);
		}

		if (parser.is_option("-poopcount")) {
			String poop_count_s = parser.consume_option("-poopcount");
			poop_count = Integer.parseInt(poop_count_s);
			Log.log("Poop count: " + poop_count);
		}

		if (parser.is_option("-help")) {
			System.out.println("Usage: <prog> [options]");
			System.out.println("Options:");
			System.out.println("  -noemoji");
			System.out.println("  -showpoops");
			System.out.println("  -width=<width>");
			System.out.println("  -height=<height>");
			System.out.println("  -poopcount=<poopcount>");
			System.out.println("  -help");
			return;
		}


		while (true) {			 
			t = new Thread() {
				@Override
				public void run() {
					
					super.run();

					try {
						main_main();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}
			};

			t.start();

			t.join();
			
		}
	}

	public void restart() {
		this.dispose();
		t.stop();
	}

	public void setShowPoops(boolean showpoops) {
		this.showpoops2 = showpoops;
	}

	public static void main_main() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		GUI dialog;

		try
		{
			dialog = new GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}

		while (true) {
			
		}
	}

	public GUI()
	{
		game_logic = new GameLogic();
		game_field = new GameField(width, height);
		

		setBounds(0, 0, width * button_size, (height + 1) * button_size);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				Log.log("Window activated");
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				Log.log("Window closed");
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				Log.log("Window closing");

				// windowClosing() only gets called if the user clicks the exit button and not if we manually dispose the window
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				Log.log("Window deactivated");
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				Log.log("Window deiconified");
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				Log.log("Window iconified");
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				Log.log("Window opened");
			}
		});

		buttons = new JButton[width][height];

		final GUI _this = this;
		
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
						game_logic.player.updatePosition(x, y);
	
						Log.log("x: " + x + " y: " + y);

						try
						{
							if (e.getButton() == MouseEvent.BUTTON3)
							{
								if (game_field.getFieldType(x, y) == FieldType.FLAGGED || game_field.getFieldType(x, y) == FieldType.FAKE_FLAGGED) {
									game_field.setFlaggedForField(x, y, false);
								} else {
									game_field.setFlaggedForField(x, y, true);
								}
							} else if (e.getButton() == MouseEvent.BUTTON1) {
								game_logic.checkForDeath(game_logic.player.getPosition(), game_field);
							}
						}
						catch (IllegalArgumentException ex)
						{
							Log.log("IllegalArgumentException: " + ex.getMessage());
						}
						Component frame = null;
						
						
						if(!game_logic.player.getGameStatusAlive())
						{
							game_logic.handleDeath(_this);
						}

						if (first_move) {
							game_field.plantPoops(poop_count, x, y);
							first_move = false;
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
		int flagged_count = 0;
		for (int i = 0; i < width; i++)
		{
			for (int k = 0; k < height; k++)
			{
				switch (game_field.getFieldType(i, k))
				{
					case EMPTY:
						buttons[i][k].setText(" ");
						break;
					case FLAGGED:
						flagged_count++;
						buttons[i][k].setText(noemoji ? "X" : "🚩");
						break;
					case FAKE_FLAGGED:
						buttons[i][k].setText(noemoji ? "X" : "🚩");
						break;
					case POOPED:
						buttons[i][k].setText(showpoops || showpoops2 ? (noemoji ? "P" : "💩") : " ");
						break;
					case CLEAN:
						buttons[i][k].setText(Integer.toString(game_logic.poops.getPoopsInCloseProximityIndices(i, k, game_field)));
						break;
				}
			}
		}

		if (flagged_count == this.poop_count) {
			game_logic.handleWon(this);
		}
	}
}
