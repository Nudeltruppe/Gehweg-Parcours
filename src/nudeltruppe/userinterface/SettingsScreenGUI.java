package nudeltruppe.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.script.ScriptContext;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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


public class SettingsScreenGUI extends JFrame {
    private final JPanel contentPanel = new JPanel();
	public String poop_count;
	public String size_x;
	public String size_y;

	public boolean data_ready = false;

    public SettingsScreenGUI() {
		setBounds(0, 0, 420, 420);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton play_button = new JButton("OK!");
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

        addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				Log.log("Window activated");
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				Log.log("Window closed");

				SettingsScreenGUI.this.poop_count = poop_count.getText();
				SettingsScreenGUI.this.size_x = platten_size_x.getText();
				SettingsScreenGUI.this.size_y = platten_size_y.getText();

				if (SettingsScreenGUI.this.poop_count.startsWith("0") || SettingsScreenGUI.this.poop_count.startsWith("-")) {
					JOptionPane.showMessageDialog(null, "Value is 0 or negative");
					SettingsScreenGUI.this.poop_count = "20";
					return;
				}
				if (SettingsScreenGUI.this.size_x.startsWith("0") || SettingsScreenGUI.this.size_x.startsWith("-")) {
					JOptionPane.showMessageDialog(null, "Value is 0 or negative");
					SettingsScreenGUI.this.size_x = "16";
					return;
				}
				if (SettingsScreenGUI.this.size_y.startsWith("0") || SettingsScreenGUI.this.size_y.startsWith("-")) {
					JOptionPane.showMessageDialog(null, "Value is 0 or negative");
					SettingsScreenGUI.this.size_y = "6";
					return;
				}

				SettingsScreenGUI.this.data_ready = true;

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				Log.log("Window closing");
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

		final SettingsScreenGUI _this = this;
		play_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
    }
}
