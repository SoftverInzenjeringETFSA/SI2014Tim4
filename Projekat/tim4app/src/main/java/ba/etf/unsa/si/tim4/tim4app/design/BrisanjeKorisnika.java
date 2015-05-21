package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class BrisanjeKorisnika extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKorisnika frame = new BrisanjeKorisnika();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BrisanjeKorisnika() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30][][200][40]", "[][][][][]"));
		
		JLabel label = new JLabel("Izaberite korisnika:");
		contentPane.add(label, "cell 1 2,alignx trailing");
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 2 2,alignx left");
		
		JButton button = new JButton("Obriši korisnika");
		contentPane.add(button, "cell 2 4");
	}

}
