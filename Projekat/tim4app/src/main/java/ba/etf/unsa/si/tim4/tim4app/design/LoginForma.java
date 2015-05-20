package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForma frame = new LoginForma();
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
	public LoginForma() {
		setTitle("Prijava na sistem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		panel.setBounds(10, 11, 381, 207);
		contentPane.add(panel);
		panel.setLayout(null);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(170, 60, 125, 20);
		panel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JButton prijavaButton = new JButton("Prijava");
		prijavaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		prijavaButton.setBounds(170, 148, 125, 23);
		panel.add(prijavaButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 104, 125, 20);
		panel.add(passwordField);
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(55, 63, 113, 14);
		panel.add(lblKorisnikoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setBounds(91, 107, 77, 14);
		panel.add(lblLozinka);
	}
}
