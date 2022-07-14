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
	public String poop_count;
	public String size_x;
	public String size_y;

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
        
        JButton play_button = new JButton("Play!");
        play_button.setBounds((420/2)-50, 420-100, 100, 50);
        contentPanel.add(play_button);

		// platten size x
		final JLabel platten_size_label_x = new JLabel("Anzahl der Platten (x)");
		platten_size_label_x.setBounds(10, 10, 250, 50);
		contentPanel.add(platten_size_label_x);

		final JTextField platten_size_x = new JTextField("16");
		platten_size_x.setBounds(8, 45, 50, 25);
		contentPanel.add(platten_size_x);

		// platten size y
		final JLabel platten_size_label_y = new JLabel("Anzahl der Platten (y)");
		platten_size_label_y.setBounds(10, 100, 250, 50);
		contentPanel.add(platten_size_label_y);
		
		final JTextField platten_size_y = new JTextField("6");
		platten_size_y.setBounds(8, 135, 50, 25);
		contentPanel.add(platten_size_y);
		
		// poops
		final JLabel poop_count_label = new JLabel("Anzahl der Hundehaufen");
		poop_count_label.setBounds(10, 200, 250, 50);
		contentPanel.add(poop_count_label);

		final JTextField poop_count = new JTextField("20");
		poop_count.setBounds(8, 250, 50, 25);
		contentPanel.add(poop_count);

		play_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MenuScreenGUI.this.poop_count = poop_count.getText();
				MenuScreenGUI.this.size_x = platten_size_x.getText();
				MenuScreenGUI.this.size_y = platten_size_y.getText();

				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						try {
							GUI.main(new String[] {"-width=" + size_x, "-height=" + size_y, "-poopcount=" + MenuScreenGUI.this.poop_count});
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
				dispose();
			}
			
		});
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
