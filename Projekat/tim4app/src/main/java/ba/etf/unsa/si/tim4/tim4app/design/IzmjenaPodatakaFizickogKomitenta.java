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

public class IzmjenaPodatakaFizickogKomitenta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IzmjenaPodatakaFizickogKomitenta() {
		setTitle("Izmjena podataka fizičkog komitenta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][grow][][]", "[][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Ime:");
		contentPane.add(lblNewLabel, "cell 3 1,alignx trailing");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 4 1 5 1,growx");
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		contentPane.add(label, "cell 12 1");
		
		JLabel lblNewLabel_1 = new JLabel("Prezime:");
		contentPane.add(lblNewLabel_1, "cell 3 3,alignx trailing");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 4 3 5 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		contentPane.add(lblJmbg, "cell 3 5,alignx trailing");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 4 5 5 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Broj lične karte:");
		contentPane.add(lblNewLabel_2, "cell 3 7");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 4 7 5 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		contentPane.add(lblTelefon, "cell 3 9,alignx trailing");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 4 9 5 1,growx");
		textField_4.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "cell 3 11,alignx trailing");
		
		textField_5 = new JTextField();
		contentPane.add(textField_5, "cell 4 11 5 1,growx");
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Izmijeni");
		contentPane.add(btnNewButton, "cell 8 14,alignx right");
	}

}
