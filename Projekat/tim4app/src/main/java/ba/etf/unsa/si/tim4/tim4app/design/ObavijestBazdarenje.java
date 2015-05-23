package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;

import javax.swing.JLabel;

public class ObavijestBazdarenje extends JDialog {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable bazdarenjeTable;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObavijestBazdarenje frame = new ObavijestBazdarenje();
					frame.setVisible(true);
				} catch (Exception e) {
					Logger l = Logger.getAnonymousLogger();
					l.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ObavijestBazdarenje() {
		setResizable(false);
		setModal(true);
		setTitle("Obavijest o skorom baždarenju ");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 459, 245);
		contentPane.add(scrollPane);
		
		bazdarenjeTable = new JTable();
		bazdarenjeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Serijski broj", "Tip rezervoara", "Datum zadnjeg baždarenja", "Kapacitet", "Trenutni status"
			}
		));
		bazdarenjeTable.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		scrollPane.setViewportView(bazdarenjeTable);
		
		lblNewLabel = new JLabel("Ovo su rezervoari kojima je potrebno baždarenje, za nastavak izađite iz ovog prozora!");
		lblNewLabel.setBounds(10, 11, 459, 14);
		contentPane.add(lblNewLabel);
		fillTable();
	}
	
	private void fillTable()
	{
		PlinskiRezervoarDataSource pds = new PlinskiRezervoarDataSource();
		LinkedList<PlinskiRezervoar> p = new LinkedList<PlinskiRezervoar>();
		p = pds.getAllBazdarenjeSoon();
		DefaultTableModel model = (DefaultTableModel) bazdarenjeTable.getModel();
		for(int i = 0; i < p.size(); i++)
		{
			PlinskiRezervoar pr = p.get(i);
			model.addRow(new Object[] {pr.getSerijskiBroj(), pr.getTipRezervoara(), (new SimpleDateFormat("dd-MM-yyyy").format(pr.getDatumZadnjegBazdarenja())), pr.getKapacitet(), pr.getTrenutniStatus()});
		}
	}
}
