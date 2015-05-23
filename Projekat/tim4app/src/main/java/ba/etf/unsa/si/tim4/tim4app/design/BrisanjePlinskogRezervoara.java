package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrisanjePlinskogRezervoara extends JDialog {

	private JPanel contentPane;
	private JPanel panel;
	private JComboBox rezervoarComboBox;
	private JLabel lblIzaberiteRezervoar;
	private JButton btnObrii;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjePlinskogRezervoara frame = new BrisanjePlinskogRezervoara();
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
	public BrisanjePlinskogRezervoara() {
		setModal(true);
		setResizable(false);
		setTitle("Brisanje plinskog rezervoara");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		panel.setBounds(10, 11, 323, 136);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rezervoarComboBox = new JComboBox();
		rezervoarComboBox.setBounds(133, 28, 136, 20);
		panel.add(rezervoarComboBox);
		fillCMB();
		
		lblIzaberiteRezervoar = new JLabel("Izaberite rezervoar:");
		lblIzaberiteRezervoar.setBounds(10, 31, 113, 14);
		panel.add(lblIzaberiteRezervoar);
		
		btnObrii = new JButton("Obriši");
		btnObrii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlinskiRezervoarDataSource kds = new PlinskiRezervoarDataSource();
				int id = ((PlinskiRezervoar)rezervoarComboBox.getSelectedItem()).getId();
				kds.delete(id);
				fillCMB();
			}
		});
		btnObrii.setBounds(180, 84, 89, 23);
		panel.add(btnObrii);
	}
	
	private void fillCMB()
	{
		PlinskiRezervoarDataSource kds = new PlinskiRezervoarDataSource();
		   LinkedList<PlinskiRezervoar> rezervoari = kds.getAll();
		   if(rezervoari != null)
		   {
			   rezervoarComboBox.removeAllItems();
			   for(int i = 0; i < rezervoari.size(); i++)
			   {
				   
				   rezervoarComboBox.addItem((PlinskiRezervoar)rezervoari.get(i)); 
			   }
		   }
	}
}
