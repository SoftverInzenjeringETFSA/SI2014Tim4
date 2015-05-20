package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class IzmjenaPodatakaPravnogKomitenta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IzmjenaPodatakaPravnogKomitenta() {
		setTitle("Izmjena podataka pravnog komitenta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][grow][][][][][]", "[][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNazivFirme = new JLabel("Naziv firme:");
		contentPane.add(lblNazivFirme, "cell 4 4");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 5 4 3 1,growx");
		textField.setColumns(10);
		
		JLabel lblPdvBroj = new JLabel("PDV broj:");
		contentPane.add(lblPdvBroj, "cell 4 7");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 5 7 3 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		contentPane.add(lblAdresa, "cell 4 11");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 5 11 3 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		contentPane.add(lblTelefon, "cell 4 14");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 5 14 3 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "cell 4 17");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 5 17 3 1,growx");
		textField_4.setColumns(10);
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		contentPane.add(btnIzmijeni, "cell 6 22 2 1");
	}

}
