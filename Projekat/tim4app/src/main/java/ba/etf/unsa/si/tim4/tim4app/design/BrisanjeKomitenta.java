package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class BrisanjeKomitenta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKomitenta frame = new BrisanjeKomitenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BrisanjeKomitenta() {
		setTitle("Brisanje komitenta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow]", "[][][][][][][][]"));
		
		JLabel lblOdaberiteKomitenta = new JLabel("Odaberite komitenta:");
		lblOdaberiteKomitenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblOdaberiteKomitenta, "cell 2 2");
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 3 2 6 1,growx");
		
		JButton btnNewButton = new JButton("Izbriši komitenta");
		contentPane.add(btnNewButton, "cell 3 4 6 1,growx");
	}

}
