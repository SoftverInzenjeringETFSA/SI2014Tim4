package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.classes.Promjena;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.PromjeneDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class UnosPromjeneRezervoara extends JDialog {

	private JPanel contentPane;
	private JDatePickerImpl datePicker;
	private JComboBox rezervoarComboBox;
	private JTextArea promjenaTA;
	private JComboBox tipComboBox;
	private JButton btnUnesiPromjenu;
	private JLabel promjenaLabel;
	private JTextField lokacijaTF;
	private Validator validator = new Validator();
	private ItemChangeListener comboBoxListener;

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
		setModal(true);
		setTitle("Unos promjene plinskog rezervoara");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][grow][][][][][grow]", "[][][][][][][][grow][][][][][]"));
		comboBoxListener = new ItemChangeListener();
		
		JLabel lblIzaberiRezervoar = new JLabel("Izaberite rezervoar:");
		contentPane.add(lblIzaberiRezervoar, "cell 2 0");
		
		rezervoarComboBox = new JComboBox();
		contentPane.add(rezervoarComboBox, "cell 4 0 4 1,growx");
		fillCMB();
		
		JLabel lblPromjena = new JLabel("Promjena:");
		contentPane.add(lblPromjena, "cell 2 1,alignx right");
		
		promjenaTA = new JTextArea();
		promjenaTA.setEnabled(false);
		contentPane.add(promjenaTA, "cell 3 1 5 4,grow");
		
		promjenaLabel = new JLabel("Nova lokacija:");
		contentPane.add(promjenaLabel, "cell 2 5,alignx right");
		
		lokacijaTF = new JTextField();
		lokacijaTF.setEnabled(false);
		contentPane.add(lokacijaTF, "cell 4 5 4 1,growx");
		lokacijaTF.setColumns(10);
		
		JLabel lblTipPromjene = new JLabel("Tip promjene:");
		contentPane.add(lblTipPromjene, "cell 2 6,alignx right");
		
		tipComboBox = new JComboBox();
		tipComboBox.setModel(new DefaultComboBoxModel(new String[] {"Bazdarenje", "Promjena lokacije", "Punjenje"}));
		contentPane.add(tipComboBox, "cell 3 6 5 1,growx");
		tipComboBox.addItemListener(comboBoxListener);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		btnUnesiPromjenu = new JButton("Unesi promjenu");
		btnUnesiPromjenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjeneDataSource pds = new PromjeneDataSource();
				PlinskiRezervoarDataSource prds = new PlinskiRezervoarDataSource();
				String tip = (String) tipComboBox.getSelectedItem();
				PlinskiRezervoar p = (PlinskiRezervoar) rezervoarComboBox.getSelectedItem();
				Date datumPromjene = (Date)datePicker.getModel().getValue();
				String opis = promjenaTA.getText();
				if(tip.equals("Promjena lokacije"))
				{
					String adresa = lokacijaTF.getText();
					String validateAdresa = validator.validateAdresa(adresa);
					if(!validateAdresa.equals("")) { showMessageBox(validateAdresa, "Greška"); return;}
					else if(datumPromjene == null) { showMessageBox("Morate izabrati datum!", "Greška"); return;}
					else if(opis.equals("")) { showMessageBox("Morate unijeti opis promjene!", "Greška"); return;}
					Promjena px = new Promjena(p.getSerijskiBroj(), datumPromjene, pds.getTipPromjeneFromSifarnik(tip), opis);
					p.setLokacija(adresa);
					pds.insert(px);
					prds.update(p);
				}
				else if(tip.equals("Bazdarenje"))
				{
					if(datumPromjene == null) { showMessageBox("Morate izabrati datum!", "Greška"); return;}
					Promjena px = new Promjena(p.getSerijskiBroj(), datumPromjene, pds.getTipPromjeneFromSifarnik(tip), opis);
					p.setDatumZadnjegBazdarenja(p.getDatumSljedecegBazdarenja());
					pds.insert(px);
					prds.update(p);
				}
				else if(tip.equals("Punjenje"))
				{
					if(datumPromjene == null) { showMessageBox("Morate izabrati datum!", "Greška"); return;}
					Promjena px = new Promjena(p.getSerijskiBroj(), datumPromjene, pds.getTipPromjeneFromSifarnik(tip), opis);
					p.setNapunjenost(1);
					pds.insert(px);
					prds.update(p);
				}
				clearControls();
			}
		});
		
		JLabel lblDatum = new JLabel("Datum:");
		contentPane.add(lblDatum, "cell 2 9,alignx right");
		datePicker = new JDatePickerImpl(datePanel);
		contentPane.add(datePicker, "cell 3 9 5 1,growx");
		contentPane.add(btnUnesiPromjenu, "cell 7 10");
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
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void clearControls()
	{
		   promjenaTA.setText("");
		   lokacijaTF.setText("");
	}
	
	class ItemChangeListener implements ItemListener
	{
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   
	    	   // uzimamo objekat na kojem se desio događaj
	    	   Object sourceObject = event.getSource();
	    	   // određujemo koji je od objekata bio
	    	   if(sourceObject == tipComboBox)
	    	   {
	    		   // selektovan je combobox, sada mijenjamo podatke
	    		   String tip = (String) tipComboBox.getSelectedItem();
	    		   PlinskiRezervoar p = (PlinskiRezervoar) rezervoarComboBox.getSelectedItem();
	    		   if(tip.equals("Bazdarenje"))
	    		   {
	    			   lokacijaTF.setEnabled(false);
	    			   Date datumNext = p.getDatumSljedecegBazdarenja();
	    			   promjenaTA.setText("Bazdarenje datum sljedeceg bazdarenja " + (new SimpleDateFormat("dd-MM-yyyy").format(datumNext)));
	    		   }
	    		   else if(tip.equals("Promjena lokacije"))
	    		   {
	    			   lokacijaTF.setEnabled(true);
	    			   promjenaTA.setText("Promjena lokacije na " + lokacijaTF.getText());
	    		   }
	    		   else if(tip.equals("Punjenje"))
	    		   {
	    			   lokacijaTF.setEnabled(false);
	    			   promjenaTA.setText("Punjenje na " + p.getKapacitet() + " l");
	    		   }
	    	   }
	       }
	    }   
	}
}
