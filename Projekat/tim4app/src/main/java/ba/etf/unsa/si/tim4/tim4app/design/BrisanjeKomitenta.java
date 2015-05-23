package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BrisanjeKomitenta extends JDialog {

	private JPanel contentPane;
	private JComboBox komitentComboBox;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKomitenta frame = new BrisanjeKomitenta();
					frame.setVisible(true);
				} catch (Exception e) {
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
	public BrisanjeKomitenta() {
		setModal(true);
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Brisanje komitenta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow]", "[][][][][][][][]"));
		
		JLabel lblOdaberiteKomitenta = new JLabel("Odaberite komitenta:");
		lblOdaberiteKomitenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblOdaberiteKomitenta, "cell 2 2");
		
		komitentComboBox = new JComboBox();
		contentPane.add(komitentComboBox, "cell 3 2 6 1,growx");
		fillCMB();
		
		btnNewButton = new JButton("Izbriši komitenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Komitent k = (Komitent) komitentComboBox.getSelectedItem();
				KomitentDataSource kds = new KomitentDataSource();
				kds.delete(k.getId());
				fillCMB();
			}
		});
		contentPane.add(btnNewButton, "cell 3 4 6 1,growx");
	}
	
	private void fillCMB()
	{
		KomitentDataSource kds = new KomitentDataSource();
		   LinkedList<Komitent> komitenti = kds.getAll();
		   if(komitenti != null)
		   {
			   komitentComboBox.removeAllItems();
			   for(int i = 0; i < komitenti.size(); i++)
			   {
				   if(komitenti.get(i).getTipKomitenta().equals("Pravno lice"))
				   komitentComboBox.addItem((PravniKomitent)komitenti.get(i));
				   else komitentComboBox.addItem((FizickiKomitent)komitenti.get(i)); 
			   }
		   }
	}

}
