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

import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodavanjePravnogKomitenta extends JDialog {

	private JPanel contentPane;
	private JTextField nazivFirmeTF;
	private JTextField pdvBrojTF;
	private JTextField adresaTF;
	private JTextField telefonTF;
	private JTextField emailTF;
	private Validator validator = new Validator();

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjePravnogKomitenta frame = new DodavanjePravnogKomitenta();
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
	public DodavanjePravnogKomitenta() {
		setModal(true);
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Dodavanje komitenta (Pravno lice)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][grow][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNazivFirme = new JLabel("Naziv firme:");
		contentPane.add(lblNazivFirme, "cell 3 5");
		
		nazivFirmeTF = new JTextField();
		contentPane.add(nazivFirmeTF, "cell 4 5 3 1,growx");
		nazivFirmeTF.setColumns(10);
		
		JLabel lblPdvBroj = new JLabel("PDV broj:");
		contentPane.add(lblPdvBroj, "cell 3 9");
		
		pdvBrojTF = new JTextField();
		contentPane.add(pdvBrojTF, "cell 4 9 3 1,growx");
		pdvBrojTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Adresa:");
		contentPane.add(lblNewLabel, "cell 3 14");
		
		adresaTF = new JTextField();
		contentPane.add(adresaTF, "cell 4 14 3 1,growx");
		adresaTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefon:");
		contentPane.add(lblNewLabel_1, "cell 3 18");
		
		telefonTF = new JTextField();
		contentPane.add(telefonTF, "cell 4 18 3 1,growx");
		telefonTF.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		contentPane.add(lblNewLabel_2, "cell 3 22");
		
		emailTF = new JTextField();
		contentPane.add(emailTF, "cell 4 22 3 1,growx");
		emailTF.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj komitenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = nazivFirmeTF.getText();
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
				kds.insert(new PravniKomitent("Pravno lice", adresa, telefon, email, naziv, pdvBroj));
				clearControls();
			}
		});
		contentPane.add(btnNewButton, "cell 6 28,growx");
	}
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void clearControls()
	{
		nazivFirmeTF.setText("");
		adresaTF.setText("");
		pdvBrojTF.setText("");
		telefonTF.setText("");
		emailTF.setText("");
	}
}
