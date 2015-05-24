package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import ba.etf.unsa.si.tim4.tim4app.daldao.SkladisteDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DodavanjePlinskihBoca extends JDialog {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblBoceOd;
	private JLabel lblNewLabel;
	private JLabel lblZaDodati;
	private JButton dodajPetLitarskeButton;
	private JButton dodajDesetLitarskeButton;
	private JButton dodajPetnaestLitarskeButton;
	private JTextField petLitaraTF;
	private JTextField desetLitaraTF;
	private JTextField petnaestLitaraTF;
	private JLabel petLitaraLabel;
	private JLabel desetLitaraLabel;
	private JLabel petnaestLitaraLabel;
	private JLabel labela;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private Validator validator = new Validator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjePlinskihBoca frame = new DodavanjePlinskihBoca();
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
	public DodavanjePlinskihBoca() {
		setResizable(false);
		setTitle("Dodavanje plinskih boca");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		panel.setBounds(10, 11, 414, 151);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblBoceOd = new JLabel("Boca:");
		lblBoceOd.setBounds(25, 30, 110, 14);
		panel.add(lblBoceOd);
		
		lblNewLabel = new JLabel("Trenutna količina:");
		lblNewLabel.setBounds(105, 30, 101, 14);
		panel.add(lblNewLabel);
		
		lblZaDodati = new JLabel("Za dodati:");
		lblZaDodati.setBounds(235, 30, 74, 14);
		panel.add(lblZaDodati);
		
		dodajPetLitarskeButton = new JButton("Dodaj");
		dodajPetLitarskeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String petLitara = petLitaraTF.getText();
				String validateKolicina = validator.validateQuantity(petLitara, 600);
				if(!validateKolicina.equals("")) { showMessageBox(validateKolicina, "Greška"); return; }
				SkladisteDataSource s = new SkladisteDataSource();
				int staraKolicina = s.getQuantityByCapacity(5);
				s.update(5, staraKolicina, Integer.valueOf(petLitara));
				fillLabels();
			}
		});
		dodajPetLitarskeButton.setBounds(315, 48, 89, 23);
		panel.add(dodajPetLitarskeButton);
		
		dodajDesetLitarskeButton = new JButton("Dodaj");
		dodajDesetLitarskeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desetLitara = desetLitaraTF.getText();
				String validateKolicina = validator.validateQuantity(desetLitara, 600);
				if(!validateKolicina.equals("")) { showMessageBox(validateKolicina, "Greška kod unosa količine"); return; }
				SkladisteDataSource s = new SkladisteDataSource();
				int staraKolicina = s.getQuantityByCapacity(10);
				s.update(10, staraKolicina, Integer.valueOf(desetLitara));
				fillLabels();
			}
		});
		dodajDesetLitarskeButton.setBounds(315, 82, 89, 23);
		panel.add(dodajDesetLitarskeButton);
		
		dodajPetnaestLitarskeButton = new JButton("Dodaj");
		dodajPetnaestLitarskeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String petnaestLitara = petnaestLitaraTF.getText();
				String validateKolicina = validator.validateQuantity(petnaestLitara, 600);
				if(!validateKolicina.equals("")) { showMessageBox(validateKolicina, "Greška kod unosa količine"); return; }
				SkladisteDataSource s = new SkladisteDataSource();
				int staraKolicina = s.getQuantityByCapacity(15);
				s.update(15, staraKolicina, Integer.valueOf(petnaestLitara));
				fillLabels();
			}
		});
		dodajPetnaestLitarskeButton.setBounds(315, 116, 89, 23);
		panel.add(dodajPetnaestLitarskeButton);
		
		petLitaraTF = new JTextField();
		petLitaraTF.setBounds(223, 49, 86, 20);
		panel.add(petLitaraTF);
		petLitaraTF.setColumns(10);
		
		desetLitaraTF = new JTextField();
		desetLitaraTF.setBounds(223, 83, 86, 20);
		panel.add(desetLitaraTF);
		desetLitaraTF.setColumns(10);
		
		petnaestLitaraTF = new JTextField();
		petnaestLitaraTF.setBounds(223, 117, 86, 20);
		panel.add(petnaestLitaraTF);
		petnaestLitaraTF.setColumns(10);
		
		petLitaraLabel = new JLabel("New label");
		petLitaraLabel.setBounds(126, 52, 46, 14);
		panel.add(petLitaraLabel);
		
		desetLitaraLabel = new JLabel("New label");
		desetLitaraLabel.setBounds(126, 86, 46, 14);
		panel.add(desetLitaraLabel);
		
		petnaestLitaraLabel = new JLabel("New label");
		petnaestLitaraLabel.setBounds(126, 120, 46, 14);
		panel.add(petnaestLitaraLabel);
		
		labela = new JLabel("5 litara");
		labela.setBounds(25, 52, 54, 14);
		panel.add(labela);
		
		lblNewLabel_1 = new JLabel("10 litara:");
		lblNewLabel_1.setBounds(25, 86, 54, 14);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("15 litara:");
		lblNewLabel_2.setBounds(25, 120, 54, 14);
		panel.add(lblNewLabel_2);
		
		fillLabels();
	}
	
	private void fillLabels()
	{
		SkladisteDataSource skladiste = new SkladisteDataSource();
		petLitaraLabel.setText(String.valueOf(skladiste.getQuantityByCapacity(5)));
		desetLitaraLabel.setText(String.valueOf(skladiste.getQuantityByCapacity(10)));
		petnaestLitaraLabel.setText(String.valueOf(skladiste.getQuantityByCapacity(15)));
	}
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}

}
