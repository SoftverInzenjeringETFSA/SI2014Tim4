package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class IzmjenaPodatakaFizickogKomitenta extends JDialog {

	private JPanel contentPane;
	private JTextField imeTF;
	private JTextField prezimeTF;
	private JTextField jmbTF;
	private JTextField brojlkTF;
	private JTextField adresaTF;
	private JTextField telefonTF;
	private JTextField emailTF;
	private JComboBox komitentComboBox;
	private JLabel lblIzaberiteKomitenta;
	private Validator validator = new Validator();
	private ItemChangeListener comboBoxSelectionChangedListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaPodatakaFizickogKomitenta frame = new IzmjenaPodatakaFizickogKomitenta();
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
	public IzmjenaPodatakaFizickogKomitenta() {
		setModal(true);
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Izmjena podataka fizičkog komitenta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBoxSelectionChangedListener = new ItemChangeListener();
		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setBounds(125, 43, 22, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setSize(lblNewLabel.getPreferredSize());
		
		imeTF = new JTextField();
		imeTF.setBounds(170, 40, 148, 20);
		contentPane.add(imeTF);
		imeTF.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(376, 29, 0, 0);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime:");
		lblNewLabel_1.setBounds(106, 74, 41, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setSize(lblNewLabel_1.getPreferredSize());
		
		prezimeTF = new JTextField();
		prezimeTF.setBounds(170, 71, 148, 20);
		contentPane.add(prezimeTF);
		prezimeTF.setColumns(10);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setBounds(117, 105, 32, 14);
		contentPane.add(lblJmbg);
		lblJmbg.setSize(lblJmbg.getPreferredSize());
		
		jmbTF = new JTextField();
		jmbTF.setBounds(170, 102, 148, 20);
		contentPane.add(jmbTF);
		jmbTF.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Broj lične karte:");
		lblNewLabel_2.setBounds(72, 136, 85, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setSize(lblNewLabel_2.getPreferredSize());
		
		brojlkTF = new JTextField();
		brojlkTF.setBounds(170, 133, 148, 20);
		contentPane.add(brojlkTF);
		brojlkTF.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Adresa:");
		lblTelefon.setBounds(106, 167, 41, 14);
		contentPane.add(lblTelefon);
		lblTelefon.setSize(lblTelefon.getPreferredSize());
		
		adresaTF = new JTextField();
		adresaTF.setBounds(170, 164, 148, 20);
		contentPane.add(adresaTF);
		adresaTF.setColumns(10);
		
		JLabel lblEmail = new JLabel("Telefon:");
		lblEmail.setBounds(106, 198, 41, 14);
		contentPane.add(lblEmail);
		lblEmail.setSize(lblEmail.getPreferredSize());
		
		telefonTF = new JTextField();
		telefonTF.setBounds(170, 195, 148, 20);
		contentPane.add(telefonTF);
		telefonTF.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail:");
		lblNewLabel_3.setBounds(115, 235, 32, 14);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setSize(lblNewLabel_3.getPreferredSize());
		
		emailTF = new JTextField();
		emailTF.setBounds(170, 232, 148, 20);
		contentPane.add(emailTF);
		emailTF.setColumns(10);
		
		JButton btnNewButton = new JButton("Izmijeni");
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
				Komitent selected = (Komitent)komitentComboBox.getSelectedItem();
				FizickiKomitent fk = new FizickiKomitent(ime, prezime, jmb, brojLK, selected.getId(), "Fizicko lice", adresa, telefon, email);
				kds.update(fk);
				clearControls();
				fillCMB();
			}
		});
		btnNewButton.setBounds(249, 263, 69, 23);
		contentPane.add(btnNewButton);
		
		komitentComboBox = new JComboBox();
		komitentComboBox.setBounds(170, 11, 148, 20);
		contentPane.add(komitentComboBox);
		komitentComboBox.addItemListener(comboBoxSelectionChangedListener);
		fillCMB();
		
		lblIzaberiteKomitenta = new JLabel("Izaberite komitenta:");
		lblIzaberiteKomitenta.setBounds(50, 18, 97, 14);
		contentPane.add(lblIzaberiteKomitenta);
		lblIzaberiteKomitenta.setSize(lblIzaberiteKomitenta.getPreferredSize());
	}
	
	private void fillCMB()
	{
		KomitentDataSource kds = new KomitentDataSource();
		   LinkedList<Komitent> komitenti = kds.getAll();
		   if(komitenti != null)
		   {
			   komitentComboBox.removeAllItems();
			   for(int i = 0; i < komitenti.size(); i++)
			   {
				   if(!komitenti.get(i).getTipKomitenta().equals("Pravno lice"))
				   komitentComboBox.addItem((FizickiKomitent)komitenti.get(i)); 
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
		   emailTF.setText("");
		   jmbTF.setText("");
		   brojlkTF.setText("");
		   telefonTF.setText("");
	}
	
	class ItemChangeListener implements ItemListener, Serializable
	{

		private static final long serialVersionUID = -3147101148591613530L;

		public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   
	    	   // uzimamo objekat na kojem se desio događaj
	    	   Object sourceObject = event.getSource();
	    	   // određujemo koji je od objekata bio
	    	   if(sourceObject == komitentComboBox)
	    	   {
	    		   // selektovan je combobox, sada mijenjamo podatke
	    		   Object selectedItem = event.getItem();
	    		   FizickiKomitent k = (FizickiKomitent) selectedItem;
	    		   imeTF.setText(k.getIme());
	    		   prezimeTF.setText(k.getPrezime());
	    		   adresaTF.setText(k.getAdresa());
	    		   emailTF.setText(k.getEmail());
	    		   jmbTF.setText(k.getJMB());
	    		   brojlkTF.setText(k.getBrojLicneKarte());
	    		   telefonTF.setText(k.getBrojTelefona());
	    	   }
	       }
	    }   
	}
	
}
