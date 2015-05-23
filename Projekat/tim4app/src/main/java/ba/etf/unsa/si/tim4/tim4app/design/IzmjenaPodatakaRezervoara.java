package ba.etf.unsa.si.tim4.tim4app.design;


import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IzmjenaPodatakaRezervoara extends JDialog {

	private JPanel contentPane;
	private JTextField serijskiBrojTF;
	private JTextField kapacitetTF;
	private JTextField tezinaTF;
	private JComboBox rezervoarComboBox;
	private JLabel lblIzaberiteRezervoar;
	private JRadioButton punRadioButton;
	private JRadioButton prazanRadioButton;
	private JRadioButton uzemniRadioButton;
	private JRadioButton nadzemniRadioButton;
	private JLabel lblLokacija;
	private JTextField lokacijaTF;
	private JDatePickerImpl datePicker;
	private JLabel lblDatumZadnjegBadarenja;
	private ItemChangeListener comboBoxChangeListener;
	private Validator validator = new Validator();

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
		setModal(true);
		setTitle("Izmjena podataka rezervoara");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][grow]", "[][][][][][][][][][][][][][][][][][][][][][][]"));
		comboBoxChangeListener = new ItemChangeListener();
		
		lblIzaberiteRezervoar = new JLabel("Izaberite rezervoar:");
		contentPane.add(lblIzaberiteRezervoar, "cell 3 1,alignx trailing");
		
		rezervoarComboBox = new JComboBox();
		contentPane.add(rezervoarComboBox, "cell 4 1,growx");
		fillCMB();
		rezervoarComboBox.addItemListener(comboBoxChangeListener);
		
		JLabel lblSerijskiBroj = new JLabel("Serijski broj:");
		contentPane.add(lblSerijskiBroj, "cell 3 3,alignx right");
		
		serijskiBrojTF = new JTextField();
		contentPane.add(serijskiBrojTF, "cell 4 3,growx");
		serijskiBrojTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kapacitet:");
		contentPane.add(lblNewLabel, "cell 3 6,alignx trailing");
		
		kapacitetTF = new JTextField();
		contentPane.add(kapacitetTF, "cell 4 6,growx");
		kapacitetTF.setColumns(10);
		
		JLabel lblTezina = new JLabel("Tezina:");
		contentPane.add(lblTezina, "cell 3 8,alignx trailing");
		
		tezinaTF = new JTextField();
		contentPane.add(tezinaTF, "cell 4 8,growx");
		tezinaTF.setColumns(10);
		
		JLabel lblNapunjenost = new JLabel("Napunjenost");
		contentPane.add(lblNapunjenost, "cell 3 11,alignx trailing");
		
		punRadioButton = new JRadioButton("Pun");
		contentPane.add(punRadioButton, "flowx,cell 4 11");
		
		lblLokacija = new JLabel("Lokacija:");
		contentPane.add(lblLokacija, "cell 3 13,alignx trailing");
		
		lokacijaTF = new JTextField();
		contentPane.add(lokacijaTF, "cell 4 13,growx");
		lokacijaTF.setColumns(10);
		
		JLabel lblTip = new JLabel("Tip:");
		contentPane.add(lblTip, "cell 3 15,alignx trailing");
		
		uzemniRadioButton = new JRadioButton("Uzemni");
		contentPane.add(uzemniRadioButton, "flowx,cell 4 15");
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		lblDatumZadnjegBadarenja = new JLabel("Datum zadnjeg baždarenja:");
		contentPane.add(lblDatumZadnjegBadarenja, "cell 3 16");
		datePicker = new JDatePickerImpl(datePanel);
		contentPane.add(datePicker, "cell 4 16,growx");
		
		JButton btnNewButton = new JButton("   Izmijeni   ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serijskiBroj = serijskiBrojTF.getText();
				String kapacitet = kapacitetTF.getText();
				String tezina = tezinaTF.getText();
				int napunjenost = punRadioButton.isSelected() ? 1 : 0;
				Date datum = (Date) datePicker.getModel().getValue();
				String tip = uzemniRadioButton.isSelected() ? "Uzemni" : "Nadzemni";
				String validateSerijskiBroj = validator.validateSerijskiBroj(serijskiBroj);
				String validateKapacitet = validator.validateOnlyNumbers(kapacitet, "Kapacitet");
				String validateTezina = validator.validateOnlyNumbers(tezina, "Tezina");
				if(!validateSerijskiBroj.equals("")) { showMessageBox(validateSerijskiBroj, "Greška"); return; }
				else if(!validateKapacitet.equals("")) { showMessageBox(validateKapacitet, "Greška"); return;}
				else if(!validateTezina.equals("")) { showMessageBox(validateTezina, "Greška"); return;}
				else if(!punRadioButton.isSelected() && !prazanRadioButton.isSelected()) { showMessageBox("Morate odabrati napunjenost!", "Greška"); return; }
				else if(!uzemniRadioButton.isSelected() && !nadzemniRadioButton.isSelected()) { showMessageBox("Morate odabrati tip rezervoara!", "Greška"); return;}
				else if(uzemniRadioButton.isSelected() && nadzemniRadioButton.isSelected()) { showMessageBox("Samo jedan tip može biti odabran!", "Greška"); return;}
				else if(punRadioButton.isSelected() && prazanRadioButton.isSelected()) { showMessageBox("Rezervoar može biti ili pun ili prazan!", "Greška"); return;}
				else if(datum == null) { showMessageBox("Morate izabrati datum!", "Greška"); return; }
				PlinskiRezervoarDataSource pds = new PlinskiRezervoarDataSource();
				int id = ((PlinskiRezervoar)rezervoarComboBox.getSelectedItem()).getId();
				PlinskiRezervoar pr = new PlinskiRezervoar(id, serijskiBroj, Integer.valueOf(kapacitet), Integer.valueOf(tezina),
						   napunjenost, tip, datum, "Skladiste", "Skladiste");
				pds.update(pr);
			}
		});
		contentPane.add(btnNewButton, "cell 4 19,alignx right");
		
		prazanRadioButton = new JRadioButton("Prazan");
		contentPane.add(prazanRadioButton, "cell 4 11");
		
		nadzemniRadioButton = new JRadioButton("Nadzemni");
		contentPane.add(nadzemniRadioButton, "cell 4 15");

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
		   serijskiBrojTF.setText("");
		   lokacijaTF.setText("");
		   kapacitetTF.setText("");
		   tezinaTF.setText("");
		   punRadioButton.setSelected(false);
		   prazanRadioButton.setSelected(false);
		   uzemniRadioButton.setSelected(false);
		   nadzemniRadioButton.setSelected(false);
	}
	
	class ItemChangeListener implements ItemListener
	{
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   
	    	   // uzimamo objekat na kojem se desio događaj
	    	   Object sourceObject = event.getSource();
	    	   // određujemo koji je od objekata bio
	    	   if(sourceObject == rezervoarComboBox)
	    	   {
	    		   // selektovan je combobox, sada mijenjamo podatke
	    		   Object selectedItem = event.getItem();
	    		   PlinskiRezervoar pr = (PlinskiRezervoar) selectedItem;
	    		   serijskiBrojTF.setText(pr.getSerijskiBroj());
	    		   lokacijaTF.setText(pr.getLokacija());
	    		   if(pr.getNapunjenost() == 1) punRadioButton.setSelected(true);
	    		   else prazanRadioButton.setSelected(true);
	    		   if(pr.getTipRezervoara().equals("Uzemni")) uzemniRadioButton.setSelected(true);
	    		   else nadzemniRadioButton.setSelected(true);
	    		   kapacitetTF.setText(String.valueOf(pr.getKapacitet()));
	    		   tezinaTF.setText(String.valueOf(pr.getTezina()));
	    	   }
	       }
	    }   
	}

}
