package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrisanjeKorisnika extends JDialog {

	private JPanel contentPane;
	private JComboBox korisnikComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKorisnika frame = new BrisanjeKorisnika();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
					Logger l = Logger.getAnonymousLogger();
					l.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BrisanjeKorisnika() {
		setResizable(false);
		setTitle("Brisanje korisnika");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30][][200,grow][][40]", "[][][][][]"));
		
		JLabel label = new JLabel("Izaberite korisnika:");
		contentPane.add(label, "cell 1 1,alignx trailing");
		
		korisnikComboBox = new JComboBox();
		contentPane.add(korisnikComboBox, "cell 2 1,growx");
		fillCMB();
		
		JButton button = new JButton("Obriši korisnika");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Korisnik k =  (Korisnik) korisnikComboBox.getSelectedItem();
				KorisnikDataSource kds = new KorisnikDataSource();
				kds.delete(k.getId());
				fillCMB();
			}
		});
		contentPane.add(button, "cell 2 3,alignx right");
	}
	
	private void fillCMB()
	{
		KorisnikDataSource kds = new KorisnikDataSource();
		   LinkedList<Korisnik> korisnici = kds.getAll();
		   if(korisnici != null)
		   {
			   korisnikComboBox.removeAllItems();
			   for(int i = 0; i < korisnici.size(); i++)
			   {
				   korisnikComboBox.addItem(korisnici.get(i));
			   }
		   }
	}

}
