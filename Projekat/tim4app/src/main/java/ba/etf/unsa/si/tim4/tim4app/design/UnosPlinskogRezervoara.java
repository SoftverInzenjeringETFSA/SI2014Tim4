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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class UnosPlinskogRezervoara extends JDialog {

	private JPanel contentPane;
	private JTextField serijskiBrojTF;
	private JTextField kapacitetTF;
	private JTextField tezinaTF;
	private JRadioButton punRadioButton;
	private JRadioButton prazanRadioButton;
	private JRadioButton uzemniRadioButton;
	private JRadioButton nadzemniRadioButton;
	private JButton btnNewButton;
	private Validator validator = new Validator();
	private JLabel lblDatumZadnjegBazdarenja;
	private JDatePickerImpl datePicker;

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
		setResizable(false);
		setModal(true);
		setTitle("Unos plinskog rezervoara");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblSerijskiBroj = new JLabel("Serijski broj:");
		contentPane.add(lblSerijskiBroj, "cell 2 3,alignx trailing");
		
		serijskiBrojTF = new JTextField();
		contentPane.add(serijskiBrojTF, "cell 3 3,growx");
		serijskiBrojTF.setColumns(10);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		contentPane.add(lblKapacitet, "cell 2 5,alignx trailing");
		
		kapacitetTF = new JTextField();
		contentPane.add(kapacitetTF, "cell 3 5,growx");
		kapacitetTF.setColumns(10);
		
		JLabel lblTeina = new JLabel("Težina:");
		contentPane.add(lblTeina, "cell 2 8,alignx trailing");
		
		tezinaTF = new JTextField();
		contentPane.add(tezinaTF, "cell 3 8,growx");
		tezinaTF.setColumns(10);
		
		JLabel lblNapunjenost = new JLabel("Napunjenost:");
		contentPane.add(lblNapunjenost, "cell 2 11,alignx right");
		
		punRadioButton = new JRadioButton("Pun");
		contentPane.add(punRadioButton, "flowx,cell 3 11");
		
		JLabel lblTip = new JLabel("Tip:");
		contentPane.add(lblTip, "cell 2 13,alignx trailing");
		
		JLabel lblL = new JLabel("");
		contentPane.add(lblL, "cell 6 1");
		
		uzemniRadioButton = new JRadioButton("Uzemni");
		contentPane.add(uzemniRadioButton, "flowx,cell 3 13");
		
		prazanRadioButton = new JRadioButton("Prazan");
		contentPane.add(prazanRadioButton, "cell 3 11");
		
		nadzemniRadioButton = new JRadioButton("Nadzemni");
		contentPane.add(nadzemniRadioButton, "cell 3 13");
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		btnNewButton = new JButton("Unesi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serijskiBroj = serijskiBrojTF.getText();
				String kapacitet = kapacitetTF.getText();
				String tezina = tezinaTF.getText();
				int napunjenost = punRadioButton.isSelected() ? 1 : 0;
				Date datum = (Date) datePicker.getModel().getValue();
				String tip = uzemniRadioButton.isSelected() ? "Uzemni" : "Nadzemni";
				PlinskiRezervoarDataSource pds = new PlinskiRezervoarDataSource();
				String validateSerijskiBroj = validator.validateSerijskiBroj(serijskiBroj);
				String validateKapacitet = validator.validateOnlyNumbers(kapacitet, "Kapacitet");
				String validateTezina = validator.validateOnlyNumbers(tezina, "Tezina");
				if(!validateSerijskiBroj.equals("")) { showMessageBox(validateSerijskiBroj, "Greška"); return; }
				else if(pds.isUniqueSerijskiBroj(serijskiBroj) != 0) { showMessageBox("Serijski broj mora biti jedinstven!", "Greška"); return; }
				else if(!validateKapacitet.equals("")) { showMessageBox(validateKapacitet, "Greška"); return;}
				else if(!validateTezina.equals("")) { showMessageBox(validateTezina, "Greška"); return;}
				else if(!punRadioButton.isSelected() && !prazanRadioButton.isSelected()) { showMessageBox("Morate odabrati napunjenost!", "Greška"); return; }
				else if(!uzemniRadioButton.isSelected() && !nadzemniRadioButton.isSelected()) { showMessageBox("Morate odabrati tip rezervoara!", "Greška"); return;}
				else if(uzemniRadioButton.isSelected() && nadzemniRadioButton.isSelected()) { showMessageBox("Samo jedan tip može biti odabran!", "Greška"); return;}
				else if(punRadioButton.isSelected() && prazanRadioButton.isSelected()) { showMessageBox("Rezervoar može biti ili pun ili prazan!", "Greška"); return;}
				PlinskiRezervoar pr = new PlinskiRezervoar(serijskiBroj, Integer.valueOf(kapacitet), Integer.valueOf(tezina),
														   napunjenost, tip, datum, "Skladiste", "Skladiste");
				pds.insert(pr);
				clearControls();
			}
		});
		
		lblDatumZadnjegBazdarenja = new JLabel("Datum zadnjeg bazdarenja:");
		contentPane.add(lblDatumZadnjegBazdarenja, "cell 2 16");
		datePicker = new JDatePickerImpl(datePanel);
		contentPane.add(datePicker, "cell 3 16,growx");
		contentPane.add(btnNewButton, "cell 3 21,alignx right");
	}
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void clearControls()
	{
		   serijskiBrojTF.setText("");
		   kapacitetTF.setText("");
		   tezinaTF.setText("");
		   punRadioButton.setSelected(false);
		   prazanRadioButton.setSelected(false);
		   uzemniRadioButton.setSelected(false);
		   nadzemniRadioButton.setSelected(false);
	}

}
