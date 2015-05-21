package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DodavanjeNovogKorisnika extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeNovogKorisnika frame = new DodavanjeNovogKorisnika();
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
	public DodavanjeNovogKorisnika() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30][][grow][40]", "[37][37][37][37][37][37][37][37][37][37][37]"));
		
		JLabel label = new JLabel("Unesite podatke novog korisnika:");
		contentPane.add(label, "cell 1 0,alignx trailing");
		
		JLabel label_1 = new JLabel("Ime:");
		contentPane.add(label_1, "cell 1 1,alignx trailing");
		
		textField_1 = new JTextField();
		textField_1.setColumns(28);
		contentPane.add(textField_1, "cell 2 1,growx");
		
		JLabel label_2 = new JLabel("Prezime:");
		contentPane.add(label_2, "cell 1 2,alignx trailing");
		
		textField_2 = new JTextField();
		textField_2.setColumns(28);
		contentPane.add(textField_2, "cell 2 2,growx");
		
		JLabel label_3 = new JLabel("Broj lične karte:");
		contentPane.add(label_3, "cell 1 3,alignx trailing");
		
		textField = new JTextField();
		textField.setColumns(28);
		contentPane.add(textField, "cell 2 3,growx");
		
		JLabel label_4 = new JLabel("Adresa:");
		contentPane.add(label_4, "cell 1 4,alignx trailing");
		
		textField_3 = new JTextField();
		textField_3.setColumns(28);
		contentPane.add(textField_3, "cell 2 4,growx");
		
		JLabel label_5 = new JLabel("Broj telefona:");
		contentPane.add(label_5, "cell 1 5,alignx trailing");
		
		textField_4 = new JTextField();
		textField_4.setColumns(28);
		contentPane.add(textField_4, "cell 2 5,growx");
		
		JLabel label_6 = new JLabel("E-mail adresa:");
		contentPane.add(label_6, "cell 1 6,alignx trailing");
		
		textField_5 = new JTextField();
		textField_5.setColumns(28);
		contentPane.add(textField_5, "cell 2 6,growx");
		
		JLabel label_7 = new JLabel("Korisničko ime:");
		contentPane.add(label_7, "cell 1 7,alignx trailing");
		
		textField_6 = new JTextField();
		textField_6.setColumns(28);
		contentPane.add(textField_6, "cell 2 7,growx");
		
		JLabel label_8 = new JLabel("Lozinka");
		contentPane.add(label_8, "cell 1 8,alignx trailing");
		
		textField_7 = new JTextField();
		textField_7.setColumns(28);
		contentPane.add(textField_7, "cell 2 8,growx");
		
		JLabel label_9 = new JLabel("Datum zapošljavanja:");
		contentPane.add(label_9, "cell 1 9,alignx trailing");
		
		textField_8 = new JTextField();
		textField_8.setColumns(28);
		contentPane.add(textField_8, "cell 2 9,growx");
		
		JButton button = new JButton("Dodaj");
		contentPane.add(button, "cell 2 10,alignx right");
	}

}
