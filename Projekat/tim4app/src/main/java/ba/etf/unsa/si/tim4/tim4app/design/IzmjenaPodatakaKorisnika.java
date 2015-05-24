package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.JRadioButton;

public class IzmjenaPodatakaKorisnika extends JDialog {

	private JPanel contentPane;
	private JTextField imeTF;
	private JTextField prezimeTF;
	private JTextField brojlkTF;
	private JTextField adresaTF;
	private JTextField telefonTF;
	private JButton izmijeniButton;
	private JLabel lblNewLabel;
	private JComboBox korisnikComboBox;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordPF;
	private JTextField usernameTF;
	private ItemChangeListener changeListener;
	private Validator validator = new Validator();
	private JLabel lblNewLabel_3;
	private JRadioButton administratorRadioButton;
	private JRadioButton korisnikRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaPodatakaKorisnika frame = new IzmjenaPodatakaKorisnika();
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
	public IzmjenaPodatakaKorisnika() {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][40]", "[37][37][37][37][37][37][37][37][][37][][]"));
		changeListener = new ItemChangeListener();
		
		JLabel label = new JLabel("Izmijenite podatke korisnika:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, "cell 2 0,alignx center");
		
		lblNewLabel = new JLabel("Izaberite korisnika:");
		contentPane.add(lblNewLabel, "cell 1 1,alignx trailing");
		
		korisnikComboBox = new JComboBox();
		contentPane.add(korisnikComboBox, "cell 2 1,growx");
		fillCMB();
		korisnikComboBox.addItemListener(changeListener);
		JLabel label_1 = new JLabel("Ime:");
		contentPane.add(label_1, "cell 1 2,alignx trailing");
		imeTF = new JTextField();
		imeTF.setColumns(28);
		contentPane.add(imeTF, "cell 2 2,growx");
		
		JLabel label_2 = new JLabel("Prezime: ");
		contentPane.add(label_2, "cell 1 3,alignx trailing");
		
		prezimeTF = new JTextField();
		prezimeTF.setColumns(28);
		contentPane.add(prezimeTF, "cell 2 3,growx");
		
		JLabel label_4 = new JLabel("Broj lične karte:");
		contentPane.add(label_4, "cell 1 4,alignx trailing");
		
		brojlkTF = new JTextField();
		brojlkTF.setColumns(28);
		contentPane.add(brojlkTF, "cell 2 4,growx");
		
		JLabel label_5 = new JLabel("Adresa: ");
		contentPane.add(label_5, "cell 1 5,alignx trailing");
		
		adresaTF = new JTextField();
		adresaTF.setColumns(28);
		contentPane.add(adresaTF, "cell 2 5,growx");
		
		JLabel label_6 = new JLabel("Broj telefona:");
		contentPane.add(label_6, "cell 1 6,alignx trailing");
		
		telefonTF = new JTextField();
		telefonTF.setColumns(28);
		contentPane.add(telefonTF, "cell 2 6,growx");
		
		lblNewLabel_1 = new JLabel("Korisničko ime:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel_1, "cell 1 7,alignx trailing");
		
		usernameTF = new JTextField();
		contentPane.add(usernameTF, "cell 2 7,growx");
		usernameTF.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Tip:");
		contentPane.add(lblNewLabel_3, "cell 1 8,alignx right");
		
		administratorRadioButton = new JRadioButton("Administrator");
		contentPane.add(administratorRadioButton, "flowx,cell 2 8");
		
		lblNewLabel_2 = new JLabel("Lozinka:");
		contentPane.add(lblNewLabel_2, "cell 1 9,alignx trailing");
		
		passwordPF = new JPasswordField();
		contentPane.add(passwordPF, "cell 2 9,growx");
		
		izmijeniButton = new JButton("Izmijeni");
		izmijeniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = imeTF.getText();
				String prezime = prezimeTF.getText();
				String adresa = adresaTF.getText();
				String brojLK = brojlkTF.getText();
				String telefon = telefonTF.getText();
				String username = usernameTF.getText();
				String tip = administratorRadioButton.isSelected() ? "Administrator" : "Korisnik";
				String password = new String(passwordPF.getPassword());
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
				Korisnik selected = (Korisnik)korisnikComboBox.getSelectedItem();
				Korisnik fk = new Korisnik(selected.getId(), tip, username, password, ime, prezime, brojLK, adresa, telefon, selected.getDatumZaposlenja());
				kds.update(fk);
				clearControls();
				fillCMB();
			}
		});
		contentPane.add(izmijeniButton, "cell 2 11,alignx center");
		
		korisnikRadioButton = new JRadioButton("Korisnik");
		contentPane.add(korisnikRadioButton, "cell 2 8");
	}
	
	
	private void fillCMB()
	{
		KorisnikDataSource kds = new KorisnikDataSource();
		   LinkedList<Korisnik> korisnici = kds.getAll();
		   if(korisnici != null)
		   {
			   korisnikComboBox.removeAllItems();
			   for(int i = 0; i < korisnici.size(); i++)
			   {
				   korisnikComboBox.addItem(korisnici.get(i));
			   }
		   }
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
		   usernameTF.setText("");
		   passwordPF.setText("");
		   brojlkTF.setText("");
		   telefonTF.setText("");
	}
	
	class ItemChangeListener implements ItemListener, Serializable
	{

		private static final long serialVersionUID = 7226253792370032523L;

		public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   
	    	   // uzimamo objekat na kojem se desio događaj
	    	   Object sourceObject = event.getSource();
	    	   // određujemo koji je od objekata bio
	    	   if(sourceObject == korisnikComboBox)
	    	   {
	    		   // selektovan je combobox, sada mijenjamo podatke
	    		   Object selectedItem = event.getItem();
	    		   Korisnik k = (Korisnik) selectedItem;
	    		   imeTF.setText(k.getIme());
	    		   prezimeTF.setText(k.getPrezime());
	    		   adresaTF.setText(k.getAdresa());
	    		   brojlkTF.setText(k.getBrojLicneKarte());
	    		   telefonTF.setText(k.getBrojTelefona());
	    		   usernameTF.setText(k.getUsername());
	    		   passwordPF.setText(k.getPassword());
	    		   if(k.getTipKorisnika().equals("Administrator")) administratorRadioButton.setSelected(true);
	    		   else korisnikRadioButton.setSelected(true);
	    	   }
	       }
	    }   
	}

}
