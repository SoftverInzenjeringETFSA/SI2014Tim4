package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UnosPlinskogRezervoara extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosPlinskogRezervoara frame = new UnosPlinskogRezervoara();
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
	public UnosPlinskogRezervoara() {
		setTitle("Unos plinskog rezervoara");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblSerijskiBroj = new JLabel("Serijski broj:");
		contentPane.add(lblSerijskiBroj, "cell 2 3,alignx trailing");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 3,growx");
		textField.setColumns(10);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		contentPane.add(lblKapacitet, "cell 2 5,alignx trailing");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 3 5,growx");
		textField_2.setColumns(10);
		
		JLabel lblTeina = new JLabel("Težina:");
		contentPane.add(lblTeina, "cell 2 8,alignx trailing");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 3 8,growx");
		textField_1.setColumns(10);
		
		JLabel lblNapunjenost = new JLabel("Napunjenost:");
		contentPane.add(lblNapunjenost, "cell 2 11");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 3 11,growx");
		textField_3.setColumns(10);
		
		JLabel lblTip = new JLabel("Tip:");
		contentPane.add(lblTip, "cell 2 13,alignx trailing");
		
		JLabel lblL = new JLabel("");
		contentPane.add(lblL, "cell 6 1");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 3 13,growx");
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Unesi");
		contentPane.add(btnNewButton, "cell 3 19,alignx right");
	}

}
