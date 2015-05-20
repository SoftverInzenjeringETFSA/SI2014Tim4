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

public class DodavanjePravnogKomitenta extends JFrame {

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
		setTitle("Dodavanje komitenta (Pravno lice)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][grow][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNazivFirme = new JLabel("Naziv firme:");
		contentPane.add(lblNazivFirme, "cell 3 5");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 4 5 3 1,growx");
		textField.setColumns(10);
		
		JLabel lblPdvBroj = new JLabel("PDV broj:");
		contentPane.add(lblPdvBroj, "cell 3 9");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 4 9 3 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Adresa:");
		contentPane.add(lblNewLabel, "cell 3 14");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 4 14 3 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefon:");
		contentPane.add(lblNewLabel_1, "cell 3 18");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 4 18 3 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		contentPane.add(lblNewLabel_2, "cell 3 22");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 4 22 3 1,growx");
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj komitenta");
		contentPane.add(btnNewButton, "cell 6 28,growx");
	}

}
