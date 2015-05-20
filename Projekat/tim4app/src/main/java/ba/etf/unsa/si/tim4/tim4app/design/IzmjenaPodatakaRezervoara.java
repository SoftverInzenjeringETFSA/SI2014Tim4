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

public class IzmjenaPodatakaRezervoara extends JFrame {

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
					IzmjenaPodatakaRezervoara frame = new IzmjenaPodatakaRezervoara();
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
	public IzmjenaPodatakaRezervoara() {
		setTitle("Izmjena podataka rezervoara");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][grow]", "[][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblSerijskiBroj = new JLabel("Serijski broj:");
		contentPane.add(lblSerijskiBroj, "cell 3 2");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 4 2,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kapacitet:");
		contentPane.add(lblNewLabel, "cell 3 5,alignx trailing");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 4 5,growx");
		textField_1.setColumns(10);
		
		JLabel lblTezina = new JLabel("Tezina:");
		contentPane.add(lblTezina, "cell 3 7,alignx trailing");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 4 7,growx");
		textField_2.setColumns(10);
		
		JLabel lblNapunjenost = new JLabel("Napunjenost");
		contentPane.add(lblNapunjenost, "cell 3 10,alignx trailing");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 4 10,growx");
		textField_3.setColumns(10);
		
		JLabel lblTip = new JLabel("Tip:");
		contentPane.add(lblTip, "cell 3 13,alignx trailing");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 4 13,growx");
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("   Izmijeni   ");
		contentPane.add(btnNewButton, "cell 4 17,alignx right");
	}

}
