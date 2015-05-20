package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DodavanjeNovogKorisnikaForma extends JFrame {

	private JFrame frmDodavanjeKorisnika;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeNovogKorisnikaForma window = new DodavanjeNovogKorisnikaForma();
					window.frmDodavanjeKorisnika.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeNovogKorisnikaForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodavanjeKorisnika = new JFrame();
		frmDodavanjeKorisnika.setResizable(false);
		frmDodavanjeKorisnika.setTitle("Dodavanje novog korisnika");
		frmDodavanjeKorisnika.setBounds(100, 100, 515, 397);
		frmDodavanjeKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodavanjeKorisnika.getContentPane().setLayout(new MigLayout("", "[][][][100px:n:100px,grow]", "[37.00][37.00][37.00][37.00][37.00][37.00][37.00][37.00][37.00][37.00][37.00]"));
		
		JLabel lblNewLabel_1 = new JLabel("Unesite podatke novog korisnika:");
		frmDodavanjeKorisnika.getContentPane().add(lblNewLabel_1, "cell 1 0");
		
		JLabel lblNewLabel = new JLabel("Ime:");
		frmDodavanjeKorisnika.getContentPane().add(lblNewLabel, "cell 1 1,alignx trailing");
		
		textField_9 = new JTextField();
		textField_9.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_9, "cell 2 1,alignx left");
		
		
		
		JLabel lblPrezime = new JLabel("Prezime:");
		frmDodavanjeKorisnika.getContentPane().add(lblPrezime, "cell 1 2,alignx trailing");
		
		textField_1 = new JTextField();
		textField_1.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_1, "cell 2 2,alignx left");
		
		JLabel lblBrojLineKarte = new JLabel("Broj lične karte:");
		frmDodavanjeKorisnika.getContentPane().add(lblBrojLineKarte, "cell 1 3,alignx trailing");
		
		textField_2 = new JTextField();
		textField_2.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_2, "cell 2 3,alignx left");
		
		JLabel lblAdresa = new JLabel("Adresa:");
		frmDodavanjeKorisnika.getContentPane().add(lblAdresa, "cell 1 4,alignx trailing");
		
		textField_3 = new JTextField();
		textField_3.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_3, "cell 2 4,alignx left");
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		frmDodavanjeKorisnika.getContentPane().add(lblBrojTelefona, "cell 1 5,alignx trailing");
		
		textField_4 = new JTextField();
		textField_4.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_4, "cell 2 5,alignx left");
		
		JLabel lblEmailAdresa = new JLabel("E-mail adresa:");
		frmDodavanjeKorisnika.getContentPane().add(lblEmailAdresa, "cell 1 6,alignx trailing");
		
		textField_5 = new JTextField();
		textField_5.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_5, "cell 2 6,alignx left");
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		frmDodavanjeKorisnika.getContentPane().add(lblKorisnikoIme, "cell 1 7,alignx trailing");
		
		textField_6 = new JTextField();
		textField_6.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_6, "cell 2 7,alignx left");
		
		JLabel lblDatumZapoljavanja = new JLabel("Lozinka");
		frmDodavanjeKorisnika.getContentPane().add(lblDatumZapoljavanja, "cell 1 8,alignx trailing");
		
		textField_7 = new JTextField();
		textField_7.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_7, "cell 2 8,alignx left");
		
		JLabel lblDatumZapoljavanja_1 = new JLabel("Datum zapošljavanja:");
		frmDodavanjeKorisnika.getContentPane().add(lblDatumZapoljavanja_1, "cell 1 9,alignx trailing");
		
		textField_8 = new JTextField();
		textField_8.setColumns(28);
		frmDodavanjeKorisnika.getContentPane().add(textField_8, "cell 2 9,alignx left");
		
		JButton btnNewButton = new JButton("Dodaj");
		frmDodavanjeKorisnika.getContentPane().add(btnNewButton, "cell 2 10,alignx trailing");
	}

}
