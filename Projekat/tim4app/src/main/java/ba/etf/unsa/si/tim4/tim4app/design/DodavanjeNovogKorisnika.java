package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class DodavanjeNovogKorisnika extends JDialog {

	private JPanel contentPane;
	private JTextField imeTF;
	private JTextField prezimeTF;
	private JTextField brojlkTF;
	private JTextField adresaTF;
	private JTextField telefonTF;
	private JTextField usernameTF;
	private JDatePickerImpl datePicker;
	private Validator validator = new Validator();
	private JRadioButton administratorRadioButton;
	private JRadioButton korisnikRadioButton;
	private JPasswordField passwordPF;

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
					Logger l = Logger.getAnonymousLogger();
					l.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DodavanjeNovogKorisnika() {
		setModal(true);
		setTitle("Dodavanje korisnika");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 475, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30][][grow][40]", "[37][37][37][37][37][37][37][37][37][37][][37]"));
		
		JLabel label = new JLabel("Unesite podatke novog korisnika:");
		contentPane.add(label, "cell 2 0,alignx center");
		
		JLabel label_1 = new JLabel("Ime:");
		contentPane.add(label_1, "cell 1 1,alignx trailing");
		
		imeTF = new JTextField();
		imeTF.setColumns(28);
		contentPane.add(imeTF, "cell 2 1,growx");
		
		JLabel label_2 = new JLabel("Prezime:");
		contentPane.add(label_2, "cell 1 2,alignx trailing");
		
		prezimeTF = new JTextField();
		prezimeTF.setColumns(28);
		contentPane.add(prezimeTF, "cell 2 2,growx");
		
		JLabel label_3 = new JLabel("Broj lične karte:");
		contentPane.add(label_3, "cell 1 3,alignx trailing");
		
		brojlkTF = new JTextField();
		brojlkTF.setColumns(28);
		contentPane.add(brojlkTF, "cell 2 3,growx");
		
		JLabel label_4 = new JLabel("Adresa:");
		contentPane.add(label_4, "cell 1 4,alignx trailing");
		
		adresaTF = new JTextField();
		adresaTF.setColumns(28);
		contentPane.add(adresaTF, "cell 2 4,growx");
		
		JLabel label_5 = new JLabel("Broj telefona:");
		contentPane.add(label_5, "cell 1 5,alignx trailing");
		
		telefonTF = new JTextField();
		telefonTF.setColumns(28);
		contentPane.add(telefonTF, "cell 2 5,growx");
		
		JLabel label_7 = new JLabel("Korisničko ime:");
		contentPane.add(label_7, "cell 1 6,alignx trailing");
		
		usernameTF = new JTextField();
		usernameTF.setColumns(28);
		contentPane.add(usernameTF, "cell 2 6,growx");
		
		JLabel label_8 = new JLabel("Lozinka");
		contentPane.add(label_8, "cell 1 7,alignx trailing");
		
		passwordPF = new JPasswordField();
		contentPane.add(passwordPF, "cell 2 7,growx");
		
		JLabel label_9 = new JLabel("Datum zapošljavanja:");
		contentPane.add(label_9, "cell 1 8,alignx trailing");
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		contentPane.add(datePicker, "cell 2 8,growx");
		
		JButton button = new JButton("Dodaj");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = imeTF.getText();
				String prezime = prezimeTF.getText();
				String adresa = adresaTF.getText();
				String brojLK = brojlkTF.getText();
				String telefon = telefonTF.getText();
				String username = usernameTF.getText();
				String password = new String(passwordPF.getPassword());
				Date datum = (Date) datePicker.getModel().getValue();
				String tip = administratorRadioButton.isSelected() ? "Administrator" : "Korisnik";
				String validateIme = validator.validateOnlyLetters(ime, "Ime");
				String validatePrezime = validator.validateOnlyLetters(prezime, "Prezime");
				String validateAdresa = validator.validateAdresa(adresa);
				String validateBrojLK = validator.validateBrojLicneKarte(brojLK);
				String validateTelefon = validator.validateTelefon(telefon);
				KorisnikDataSource kds = new KorisnikDataSource();
				if(!validateIme.equals("")) {showMessageBox(validateIme, "Greška kod unosa imena korisnika"); return; }
				else if(!validatePrezime.equals("")) {showMessageBox(validatePrezime, "Greška kod unosa prezimena korisnika"); return;}
				else if(!validateAdresa.equals("")) { showMessageBox(validateAdresa, "Greška kod unosa adrese korisnika"); return;}
				else if(!validateBrojLK.equals("")) { showMessageBox(validateBrojLK, "Greška kod unosa broja lične karte korisnika"); return;}
				else if(!validateTelefon.equals("")) {showMessageBox(validateTelefon, "Greška kod unosa broja telefona korisnika"); return;}
				else if(username.equals("")) { showMessageBox("Morate unijeti korisničko ime!", "Greška kod unosa korisničkog imena korisnika"); return; }
				else if(password.equals("")) { showMessageBox("Morate unijeti lozinku!", "Greška kod unosa lozinke korisnika"); return; }
				else if(kds.isUsernameUnique(username)) { showMessageBox("Korisničko ime mora biti jedinstveno!", "Greška kod unosa korisničkog imena korisnika"); return; }
				else if(!administratorRadioButton.isSelected() && !korisnikRadioButton.isSelected()) { showMessageBox("Morate odabrati tip!", "Greška kod odabira tipa korisnika"); return;}
				else if(administratorRadioButton.isSelected() && korisnikRadioButton.isSelected()) { showMessageBox("Korisnik može biti ili administrator ili obični korisnik!", "Greška kod odabira tipa korisnika"); return;}
				else if(datum == null) { showMessageBox("Morate izabrati datum!", "Greška kod unosa datuma"); return; }
				else if(datum.after(new Date())) { showMessageBox("Datum zapošljavanja ne može biti veći od trenutnog!", "Greška kod unosa datuma"); return; }
				kds.insert(new Korisnik(tip, username, password, ime, prezime, brojLK, adresa, telefon, datum));
				clearControls();
				showMessageBoxSuccess("Uspjesno ste unijeli novog korisnika!", "Uspjeh");
			}
		});
		
		ItemListener radioButtonListener = new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) {
				JRadioButton b = (JRadioButton) e.getSource();
				if(b == administratorRadioButton)
				{
					if(e.getStateChange() == ItemEvent.SELECTED)
					{
						korisnikRadioButton.setSelected(false);
					}
				}
				else if(b == korisnikRadioButton)
				{
					if(e.getStateChange() == ItemEvent.SELECTED)
					{
						administratorRadioButton.setSelected(false);
					}
				}
			}
		};
		
		JLabel lblNewLabel = new JLabel("                              Tip korisnika:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel, "cell 1 9");
		
		administratorRadioButton = new JRadioButton("Administrator");
		contentPane.add(administratorRadioButton, "flowx,cell 2 9");
		contentPane.add(button, "cell 2 11,alignx right");
		administratorRadioButton.addItemListener(radioButtonListener);
		
		korisnikRadioButton = new JRadioButton("Korisnik");
		contentPane.add(korisnikRadioButton, "cell 2 9");
		korisnikRadioButton.addItemListener(radioButtonListener);
	}
	
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void showMessageBoxSuccess(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void clearControls()
	{
		imeTF.setText("");
		prezimeTF.setText("");
		adresaTF.setText("");
		telefonTF.setText("");
		usernameTF.setText("");
		brojlkTF.setText("");
		passwordPF.setText("");
		administratorRadioButton.setSelected(false);
		korisnikRadioButton.setSelected(false);
	}
}
