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

public class IzmjenaPodatakaKorisnikaForma extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaPodatakaKorisnikaForma frame = new IzmjenaPodatakaKorisnikaForma();
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
	public IzmjenaPodatakaKorisnikaForma() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][50]", "[22.00][37][37][37][37][37][37][37][36.00][44.00]"));
		
		JLabel lblNewLabel = new JLabel("Izmijenite podatke korisnika:");
		contentPane.add(lblNewLabel, "cell 1 1,aligny center");
		
		JLabel lblIme = new JLabel("Ime:");
		contentPane.add(lblIme, "cell 1 2,alignx right");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 2,alignx left");
		textField.setColumns(28);
		
		JLabel lblPrezime = new JLabel("Prezime: ");
		contentPane.add(lblPrezime, "cell 1 3,alignx right");
		
		textField_1 = new JTextField();
		textField_1.setColumns(28);
		contentPane.add(textField_1, "cell 3 3,growx");
		
		JLabel lblJmbg = new JLabel("JMBG: ");
		contentPane.add(lblJmbg, "cell 1 4,alignx right");
		
		textField_2 = new JTextField();
		textField_2.setColumns(28);
		contentPane.add(textField_2, "cell 3 4,growx");
		
		JLabel lblBrojLineKarte = new JLabel("Broj lične karte:");
		contentPane.add(lblBrojLineKarte, "cell 1 5,alignx right");
		
		textField_3 = new JTextField();
		textField_3.setColumns(28);
		contentPane.add(textField_3, "cell 3 5,growx");
		
		JLabel lblAdresa = new JLabel("Adresa: ");
		contentPane.add(lblAdresa, "cell 1 6,alignx right");
		
		textField_4 = new JTextField();
		textField_4.setColumns(28);
		contentPane.add(textField_4, "cell 3 6,growx");
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		contentPane.add(lblBrojTelefona, "cell 1 7,alignx right");
		
		textField_5 = new JTextField();
		textField_5.setColumns(28);
		contentPane.add(textField_5, "cell 3 7,growx");
		
		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "cell 1 8,alignx right");
		
		textField_6 = new JTextField();
		textField_6.setColumns(28);
		contentPane.add(textField_6, "cell 3 8,growx");
		
		JButton btnNewButton = new JButton("Izmijeni");
		contentPane.add(btnNewButton, "cell 3 9,alignx center");
		

	}

}
