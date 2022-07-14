package nudeltruppe.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputAdapter;

import nudeltruppe.game.GameField;
import nudeltruppe.game.GameLogic;
import nudeltruppe.game.GameField.FieldType;
import nudeltruppe.utils.ArgParser;
import nudeltruppe.utils.Log;


public class MenuScreenGUI extends JFrame {
    private final JPanel contentPanel = new JPanel();

    public MenuScreenGUI() {
		setBounds(0, 0, 420, 420);
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

		// platten size
		JLabel platten_size_label = new JLabel("Anzahl der Platten");
		platten_size_label.setBounds(10, 10, 250, 50);
		contentPanel.add(platten_size_label);

		final JTextField platten_size = new JTextField("14 x 6");
		platten_size.setBounds(8, 45, 50, 25);
		contentPanel.add(platten_size);

		// poops
		JLabel poop_count_label = new JLabel("Anzahl der Hundehaufen");
		poop_count_label.setBounds(10, 100, 250, 50);
		contentPanel.add(poop_count_label);

		final JTextField poop_count = new JTextField("20");
		poop_count.setBounds(8, 135, 50, 25);
		contentPanel.add(poop_count);

		JButton play_button = new JButton("Play!");
        play_button.setBounds((420/2)-50, 420-100, 100, 50);
		play_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final String[] args = new String[] {
					"-width=" + platten_size.getText().split("x")[0].replace(" ", ""),
					"-height=" + platten_size.getText().split("x")[1].replace(" ", ""),
					"-poopcount=" + poop_count.getText()
				};

				new Thread() {
					public void run() {
						try {
							GUI.main(args);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
				dispose();
			}
			
		});
        contentPanel.add(play_button);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        MenuScreenGUI dialog;

        try
        {
            dialog = new MenuScreenGUI();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
}
