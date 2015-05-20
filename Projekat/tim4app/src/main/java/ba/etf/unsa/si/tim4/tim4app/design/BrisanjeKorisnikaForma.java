package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class BrisanjeKorisnikaForma {

	private JFrame frmBrisanjeKorisnika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKorisnikaForma window = new BrisanjeKorisnikaForma();
					window.frmBrisanjeKorisnika.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BrisanjeKorisnikaForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBrisanjeKorisnika = new JFrame();
		frmBrisanjeKorisnika.setTitle("Brisanje korisnika");
		frmBrisanjeKorisnika.setResizable(false);
		frmBrisanjeKorisnika.setBounds(100, 100, 449, 227);
		frmBrisanjeKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBrisanjeKorisnika.getContentPane().setLayout(new MigLayout("", "[][][][grow][50]", "[][][][37][][]"));
		
		JLabel lblNewLabel = new JLabel("Izaberite korisnika:");
		frmBrisanjeKorisnika.getContentPane().add(lblNewLabel, "cell 1 2");
		
		JComboBox comboBox = new JComboBox();
		frmBrisanjeKorisnika.getContentPane().add(comboBox, "cell 3 2,growx");
		
		
		JButton btnNewButton = new JButton("Obriši korisnika");
		frmBrisanjeKorisnika.getContentPane().add(btnNewButton, "cell 3 4,alignx center");
	}

}
