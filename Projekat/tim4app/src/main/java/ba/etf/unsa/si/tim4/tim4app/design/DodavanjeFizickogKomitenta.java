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

public class DodavanjeFizickogKomitenta extends JFrame {

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
					DodavanjeFizickogKomitenta frame = new DodavanjeFizickogKomitenta();
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
	public DodavanjeFizickogKomitenta() {
		setTitle("Dodavanje komitenta(Fizičko lice)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][]", "[][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel label = new JLabel("");
		contentPane.add(label, "cell 5 1");
		
		JLabel lblIme = new JLabel("Ime:");
		contentPane.add(lblIme, "cell 3 3,alignx trailing");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 4 3,growx");
		textField_1.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		contentPane.add(lblPrezime, "cell 3 5,alignx trailing");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 4 5,growx");
		textField.setColumns(10);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		contentPane.add(lblJmbg, "cell 3 8,alignx trailing");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 4 8,growx");
		textField_2.setColumns(10);
		
		JLabel lblBrojLineKarte = new JLabel("Broj lične karte:");
		contentPane.add(lblBrojLineKarte, "cell 3 11,alignx trailing");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 4 11,growx");
		textField_3.setColumns(10);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		contentPane.add(lblAdresa, "cell 3 14,alignx trailing");
		
		textField_6 = new JTextField();
		contentPane.add(textField_6, "cell 4 14,growx");
		textField_6.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		contentPane.add(lblTelefon, "cell 3 16,alignx trailing");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 4 16,growx");
		textField_4.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "cell 3 18,alignx trailing");
		
		textField_5 = new JTextField();
		contentPane.add(textField_5, "cell 4 18,growx");
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton(" Dodaj komitenta");
		contentPane.add(btnNewButton, "cell 4 21,alignx right");
		
		
	}

}
