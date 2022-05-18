package userinterface;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


public class GUI extends JDialog
{
	
	private static final long serialVersionUID = 1L;
	
	private final int width = 14;
	private final int height = 6;
	private final int button_size = 25;

	private final JPanel contentPanel = new JPanel();
	private JButton[][] butons;

	
	public static void main(String[] args)
	{
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
		setBounds(0, 0, (width + 1) * button_size, (height + 2) * button_size);
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
	}
}
