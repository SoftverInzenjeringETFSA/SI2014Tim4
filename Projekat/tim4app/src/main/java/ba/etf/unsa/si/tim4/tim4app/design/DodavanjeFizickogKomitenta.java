package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DodavanjeFizickogKomitenta extends JDialog {

	private JPanel contentPane;
	private JTextField prezimeTF;
	private JTextField imeTF;
	private JTextField jmbTF;
	private JTextField brojlkTF;
	private JTextField telefonTF;
	private JTextField emailTF;
	private JTextField adresaTF;
	private Validator validator = new Validator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeFizickogKomitenta frame = new DodavanjeFizickogKomitenta();
					frame.setVisible(true);
				} catch (Exception e) {
					Logger l = Logger.getAnonymousLogger();
					l.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DodavanjeFizickogKomitenta() {
		setModal(true);
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Dodavanje komitenta(Fizičko lice)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][]", "[][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel label = new JLabel("");
		contentPane.add(label, "cell 5 1");
		
		JLabel lblIme = new JLabel("Ime:");
		contentPane.add(lblIme, "cell 3 3,alignx trailing");
		
		imeTF = new JTextField();
		contentPane.add(imeTF, "cell 4 3,growx");
		imeTF.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		contentPane.add(lblPrezime, "cell 3 5,alignx trailing");
		
		prezimeTF = new JTextField();
		contentPane.add(prezimeTF, "cell 4 5,growx");
		prezimeTF.setColumns(10);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		contentPane.add(lblJmbg, "cell 3 8,alignx trailing");
		
		jmbTF = new JTextField();
		contentPane.add(jmbTF, "cell 4 8,growx");
		jmbTF.setColumns(10);
		
		JLabel lblBrojLineKarte = new JLabel("Broj lične karte:");
		contentPane.add(lblBrojLineKarte, "cell 3 11,alignx trailing");
		
		brojlkTF = new JTextField();
		contentPane.add(brojlkTF, "cell 4 11,growx");
		brojlkTF.setColumns(10);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		contentPane.add(lblAdresa, "cell 3 14,alignx trailing");
		
		adresaTF = new JTextField();
		contentPane.add(adresaTF, "cell 4 14,growx");
		adresaTF.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		contentPane.add(lblTelefon, "cell 3 16,alignx trailing");
		
		telefonTF = new JTextField();
		contentPane.add(telefonTF, "cell 4 16,growx");
		telefonTF.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "cell 3 18,alignx trailing");
		
		emailTF = new JTextField();
		contentPane.add(emailTF, "cell 4 18,growx");
		emailTF.setColumns(10);
		
		JButton btnNewButton = new JButton(" Dodaj komitenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = imeTF.getText();
				String prezime = prezimeTF.getText();
				String adresa = adresaTF.getText();
				String jmb = jmbTF.getText();
				String brojLK = brojlkTF.getText();
				String telefon = telefonTF.getText();
				String email = emailTF.getText();
				String validateIme = validator.validateOnlyLetters(ime, "Ime");
				String validatePrezime = validator.validateOnlyLetters(prezime, "Prezime");
				String validateAdresa = validator.validateAdresa(adresa);
				String validateJMB = validator.validateJMB(jmb);
				String validateBrojLK = validator.validateBrojLicneKarte(brojLK);
				String validateTelefon = validator.validateTelefon(telefon);
				String validateEmail = validator.validateEmail(email);
				String validateNovaLicnaKarta = validator.validateNovaLicnaKarta(brojLK);
				if(validateNovaLicnaKarta.equals("")) validateBrojLK = "";
				if(!validateIme.equals("")) {showMessageBox(validateIme, "Greška kod unosa imena"); return; }
				else if(!validatePrezime.equals("")) {showMessageBox(validatePrezime, "Greška kod unosa prezimena"); return;}
				else if(!validateAdresa.equals("")) { showMessageBox(validateAdresa, "Greška kod unosa adrese"); return;}
				else if(!validateBrojLK.equals("")) { showMessageBox(validateBrojLK, "Greška kod unosa broja lične karte"); return;}
				else if(!validateJMB.equals("")) {showMessageBox(validateJMB, "Greška kod unosa JMBG"); return;}
				else if(!validateTelefon.equals("")) {showMessageBox(validateTelefon, "Greška kod unosa telefona"); return;}
				else if(!validateEmail.equals("")) { showMessageBox(validateEmail, "Greška kod unosa email adrese"); return;}
				KomitentDataSource kds = new KomitentDataSource();
				kds.insert(new FizickiKomitent(ime, prezime, jmb, brojLK, "Fizicko lice", adresa, telefon, email));
				clearControls();
			}
		});
		contentPane.add(btnNewButton, "cell 4 21,alignx right");
		
		
	}
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void clearControls()
	{
		imeTF.setText("");
		prezimeTF.setText("");
		adresaTF.setText("");
		jmbTF.setText("");
		telefonTF.setText("");
		emailTF.setText("");
		brojlkTF.setText("");
	}
}
