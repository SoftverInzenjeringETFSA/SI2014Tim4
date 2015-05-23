package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IzmjenaPodatakaPravnogKomitenta extends JDialog {

	private JPanel contentPane;
	private JTextField nazivTF;
	private JTextField pdvBrojTF;
	private JTextField adresaTF;
	private JTextField telefonTF;
	private JTextField emailTF;
	private Validator validator = new Validator();
	private JComboBox komitentComboBox;
	private JButton btnIzmijeni;
	private ItemChangeListener comboBoxSelectionChangedListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaPodatakaPravnogKomitenta frame = new IzmjenaPodatakaPravnogKomitenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					Logger l = Logger.getAnonymousLogger();
					l.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IzmjenaPodatakaPravnogKomitenta() {
		setResizable(false);
		setModal(true);
		setTitle("Izmjena podataka pravnog komitenta");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBoxSelectionChangedListener = new ItemChangeListener();
		JLabel lblNazivFirme = new JLabel("Naziv firme:");
		lblNazivFirme.setBounds(67, 66, 104, 14);
		contentPane.add(lblNazivFirme);
		
		nazivTF = new JTextField();
		nazivTF.setBounds(193, 63, 142, 20);
		contentPane.add(nazivTF);
		nazivTF.setColumns(10);
		
		JLabel lblPdvBroj = new JLabel("PDV broj:");
		lblPdvBroj.setBounds(67, 96, 104, 14);
		contentPane.add(lblPdvBroj);
		
		pdvBrojTF = new JTextField();
		pdvBrojTF.setBounds(193, 93, 142, 20);
		contentPane.add(pdvBrojTF);
		pdvBrojTF.setColumns(10);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setBounds(67, 126, 104, 17);
		contentPane.add(lblAdresa);
		
		adresaTF = new JTextField();
		adresaTF.setBounds(193, 124, 142, 20);
		contentPane.add(adresaTF);
		adresaTF.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(67, 158, 104, 14);
		contentPane.add(lblTelefon);
		
		telefonTF = new JTextField();
		telefonTF.setBounds(193, 155, 142, 20);
		contentPane.add(telefonTF);
		telefonTF.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(67, 189, 104, 14);
		contentPane.add(lblEmail);
		
		emailTF = new JTextField();
		emailTF.setBounds(193, 186, 142, 20);
		contentPane.add(emailTF);
		emailTF.setColumns(10);
		
		btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = nazivTF.getText();
				String adresa = adresaTF.getText();
				String pdvBroj = pdvBrojTF.getText();
				String telefon = telefonTF.getText();
				String email = emailTF.getText();
				String validateNaziv = validator.validateOnlyLetters(naziv, "Naziv firme");
				String validateAdresa = validator.validateAdresa(adresa);
				String validatePdv = validator.validatePDVBroj(pdvBroj);
				String validateTelefon = validator.validateTelefon(telefon);
				String validateEmail = validator.validateEmail(email);
				if(!validateNaziv.equals("")) {showMessageBox(validateNaziv, "Greška"); return; }
				else if(!validateAdresa.equals("")) { showMessageBox(validateAdresa, "Greška"); return;}
				else if(!validatePdv.equals("")) {showMessageBox(validatePdv, "Greška"); return;}
				else if(!validateTelefon.equals("")) {showMessageBox(validateTelefon, "Greška"); return;}
				else if(!validateEmail.equals("")) { showMessageBox(validateEmail, "Greška"); return;}
				KomitentDataSource kds = new KomitentDataSource();
				Komitent k = (PravniKomitent)komitentComboBox.getSelectedItem();
				kds.update(new PravniKomitent(k.getId(), "Pravno lice", adresa, telefon, email, naziv, pdvBroj));
				fillCMB();
				clearControls();
			}
		});
		btnIzmijeni.setBounds(250, 217, 85, 23);
		contentPane.add(btnIzmijeni);
		
		komitentComboBox = new JComboBox();
		komitentComboBox.setBounds(193, 32, 142, 20);
		contentPane.add(komitentComboBox);
		komitentComboBox.addItemListener(comboBoxSelectionChangedListener);
		fillCMB();
		JLabel lblIzaberiteKomitenta = new JLabel("Izaberite komitenta:");
		lblIzaberiteKomitenta.setBounds(67, 35, 104, 14);
		contentPane.add(lblIzaberiteKomitenta);
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
				   if(komitenti.get(i).getTipKomitenta().equals("Pravno lice"))
				   komitentComboBox.addItem((PravniKomitent)komitenti.get(i)); 
			   }
		   }
	}
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void clearControls()
	{
		   nazivTF.setText("");
		   adresaTF.setText("");
		   emailTF.setText("");
		   telefonTF.setText("");
		   pdvBrojTF.setText("");
	}
	
	class ItemChangeListener implements ItemListener
	{
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   
	    	   // uzimamo objekat na kojem se desio događaj
	    	   Object sourceObject = event.getSource();
	    	   // određujemo koji je od objekata bio
	    	   if(sourceObject == komitentComboBox)
	    	   {
	    		   // selektovan je combobox, sada mijenjamo podatke
	    		   Object selectedItem = event.getItem();
	    		   PravniKomitent k = (PravniKomitent) selectedItem;
	    		   nazivTF.setText(k.getNazivFirme());
	    		   pdvBrojTF.setText(k.getPDVbroj());
	    		   adresaTF.setText(k.getAdresa());
	    		   emailTF.setText(k.getEmail());
	    		   telefonTF.setText(k.getBrojTelefona());
	    	   }
	       }
	    }   
	}

}
