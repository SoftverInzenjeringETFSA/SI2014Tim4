package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UnosPromjeneRezervoara extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosPromjeneRezervoara frame = new UnosPromjeneRezervoara();
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
	public UnosPromjeneRezervoara() {
		setTitle("Unos promjene plinskog rezervoara");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][grow]", "[][][][][][][grow][][][][][]"));
		
		JLabel lblPromjena = new JLabel("Promjena:");
		contentPane.add(lblPromjena, "cell 2 1");
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, "cell 3 1 5 3,grow");
		
		JLabel lblTipPromjene = new JLabel("Tip promjene:");
		contentPane.add(lblTipPromjene, "cell 2 5");
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 3 5 5 1,growx");
		
		JLabel lblDatum = new JLabel("Datum:");
		contentPane.add(lblDatum, "cell 2 7");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 7 5 1,growx");
		textField.setColumns(10);
		
		JButton btnUnesiPromjenu = new JButton("Unesi promjenu");
		contentPane.add(btnUnesiPromjenu, "cell 7 9");
	}

}
