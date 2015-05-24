package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import ba.etf.unsa.si.tim4.tim4app.classes.FakturaIznajmljivanje;
import ba.etf.unsa.si.tim4.tim4app.classes.FakturaProdaje;
import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Skladiste;
import ba.etf.unsa.si.tim4.tim4app.daldao.FaktureIznajmljivanjaDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.FaktureProdajeDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.SkladisteDataSource;
import ba.etf.unsa.si.tim4.tim4app.reports.PreferenceManager;
import ba.etf.unsa.si.tim4.tim4app.reports.ReportManager;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;

import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import java.awt.ComponentOrientation;
import java.awt.Component;

import javax.swing.ListSelectionModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.javatuples.Triplet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.io.Serializable;

import javax.swing.JScrollPane;

public class PocetniEkran extends JFrame {


	private JPanel contentPane;
	private JTextField serijskiBrojTextField;
	private ItemChangeListener comboBoxSelectionChangedListener;
	private JLabel labelaParametriLabel;
	private JComboBox izborIzvjestajaComboBox;
	private JLabel lblIzaberiteIzvjetaj;
	private JButton odjavaIzvjestajiButton;
	private JLabel prijavaIzvjestajiLabel;
	private JButton kreirajIzvjestajButton;
	private JPanel parametriIzvjestajaPanel;
	private JComboBox komitentIzvjestajComboBox;
	private Validator validator;
	private JTextField petLitaraTextField;
	private JTextField desetLitaraTextField;
	private JTextField petnaestLitaraTextField;
	private JComboBox plinskiRezervoariComboBox;
	private JButton btnOdjava_1;
	private JLabel prijavaFaktureLabel;
	private JButton btnUnesiteRezervoare;
	private JComboBox komitentFaktureComboBox;
	private JButton btnKreirajFaktru;
	private JRadioButton prodajaRadioButton;
	private JRadioButton iznajmiRadioButton;
	private JLabel lblOdaberiteTipFakture;
	private JTabbedPane tabbedPane;
	private JLabel lblDostupno;
	private JLabel petLitaraLabel;
	private JLabel desetLitaraLabel;
	private JLabel petnaestLitaraLabel;
	private JButton dodajBoceButton;
	private Skladiste skladiste = new Skladiste();
	private String tipKorisnika = "";
	private String username = "";
	private JLabel prijavaKorisniciLabel;
	private JLabel prijavaKomitentiLabel;
	private JLabel prijavaRezervoariLabel;
	private JButton odjavaRezervoariButton;
	private JButton odjavaButton;
	private JButton btnOdjava;
	private JDatePickerImpl datePicker;
	private JScrollPane scrollPane;
	private JTable faktureTable;
	private JScrollPane scrollPane_1;
	private JTable korisniciTable;
	private JTextField korisnikPretragaTF;
	private JTextField pretragaKomitentiTF;
	private JTextField pretragaRezervoarTF;
	private JComboBox kriterijPretrageRezervoariComboBox;
	private JScrollPane scrollPane_2;
	private JTable pretragaRezervoaraTable;
	private JScrollPane scroll;
	private JTable pretragaKomitenataTable;
	private JComboBox kriterijPretrageKomitentiComboBox;
	private JComboBox kriterijPretrageKorisniciComboBox;
	private JButton izmijeniPravniButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetniEkran frame = new PocetniEkran();
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
	public PocetniEkran() {
		setResizable(false);
		setTitle("Početni ekran");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		validator = new Validator();
		
		ActionListener odjavaActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PocetniEkran.this.setVisible(false);
				LoginForma loginForma = new LoginForma();
				loginForma.setVisible(true);
				PocetniEkran.this.dispose();
			};
		};
		
		comboBoxSelectionChangedListener = new ItemChangeListener();
		ChangeListener tabChangedListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		    	fillComboBoxItemsOnTabChanged(index, sourceTabbedPane);
		      }
		    };

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 793, 332);
		contentPane.add(tabbedPane);
		tabbedPane.addChangeListener(tabChangedListener);
		
		JPanel izvjestajiPanel = new JPanel();
		tabbedPane.addTab("Izvještaji", null, izvjestajiPanel, null);
		izvjestajiPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		panel.setBounds(10, 35, 768, 258);
		izvjestajiPanel.add(panel);
		panel.setLayout(null);
		
		lblIzaberiteIzvjetaj = new JLabel("Izaberite izvještaj:");
		lblIzaberiteIzvjetaj.setBounds(10, 11, 158, 14);
		panel.add(lblIzaberiteIzvjetaj);

		izborIzvjestajaComboBox = new JComboBox();
		izborIzvjestajaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Izvještaj o stanju plinskih boca na skladištu", "Izvještaj o stanju velikih plinskih rezervoara na skladištu", "Izvještaj za pojedinačni veliki plinski rezervoar", "Izvještaj o trenutnom stanju za komitenta", "Izvještaj o iznajmljenim rezervoarima komitentu"}));
		izborIzvjestajaComboBox.setBounds(10, 36, 345, 20);
		izborIzvjestajaComboBox.addItemListener(comboBoxSelectionChangedListener);
		panel.add(izborIzvjestajaComboBox);
		izborIzvjestajaComboBox.setSelectedIndex(0);
		
		parametriIzvjestajaPanel = new JPanel();
		parametriIzvjestajaPanel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		parametriIzvjestajaPanel.setBounds(380, 36, 378, 170);
		panel.add(parametriIzvjestajaPanel);
		parametriIzvjestajaPanel.setLayout(null);
		
		labelaParametriLabel = new JLabel("Unesite serijski broj:");
		labelaParametriLabel.setBounds(10, 11, 165, 14);
		parametriIzvjestajaPanel.add(labelaParametriLabel);
		labelaParametriLabel.setText("Za ovaj izvještaj nisu potrebni parametri.");
		labelaParametriLabel.setSize(labelaParametriLabel.getPreferredSize());
		
		serijskiBrojTextField = new JTextField();
		serijskiBrojTextField.setBounds(10, 36, 165, 20);
		parametriIzvjestajaPanel.add(serijskiBrojTextField);
		serijskiBrojTextField.setColumns(10);
		serijskiBrojTextField.setVisible(false);
		
		komitentIzvjestajComboBox = new JComboBox();
		komitentIzvjestajComboBox.setBounds(10, 36, 165, 20);
		parametriIzvjestajaPanel.add(komitentIzvjestajComboBox);
		komitentIzvjestajComboBox.setVisible(false);
		
		JLabel lblParametriIzvjetaja = new JLabel("Parametri izvještaja:");
		lblParametriIzvjetaja.setBounds(380, 11, 119, 14);
		panel.add(lblParametriIzvjetaja);
		
		kreirajIzvjestajButton = new JButton("Kreiraj izvještaj");
		kreirajIzvjestajButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				boolean empty = rm.getSkladisteEmpty();
				int comboIndex = izborIzvjestajaComboBox.getSelectedIndex();
				if(serijskiBrojTextField.isVisible() && !komitentIzvjestajComboBox.isVisible())
				{
					String validation = validator.validateSerijskiBroj(serijskiBrojTextField.getText());
					if(validation != ""){ showMessageBox(validation, "Greška"); return; }
					rm.printReport(comboIndex, serijskiBrojTextField.getText());
				}
				else if(!serijskiBrojTextField.isVisible() && komitentIzvjestajComboBox.isVisible())
				{
					if(comboIndex == 4) comboIndex = 6;
					rm.printReport(comboIndex, komitentIzvjestajComboBox.getSelectedItem());
				}
				else 
				{
					if(!empty) rm.printReport(comboIndex, null);
					else { showMessageBox("Na skladištu nema plinskih boca!", "Greška"); return; }
				}
			}
		});
		kreirajIzvjestajButton.setBounds(622, 224, 136, 23);
		panel.add(kreirajIzvjestajButton);
		
		prijavaIzvjestajiLabel = new JLabel("Prijavljeni ste kao:");
		prijavaIzvjestajiLabel.setBounds(10, 10, 185, 14);
		izvjestajiPanel.add(prijavaIzvjestajiLabel);
		
		odjavaIzvjestajiButton = new JButton("Odjava");
		odjavaIzvjestajiButton.addActionListener(odjavaActionListener);
		odjavaIzvjestajiButton.setBounds(185, 6, 96, 23);
		izvjestajiPanel.add(odjavaIzvjestajiButton);
		
		JPanel fakturePanel = new JPanel();
		tabbedPane.addTab("Fakture", null, fakturePanel, null);
		fakturePanel.setLayout(null);
		
		prijavaFaktureLabel = new JLabel("Prijavljeni ste kao:");
		prijavaFaktureLabel.setBounds(30, 15, 138, 14);
		fakturePanel.add(prijavaFaktureLabel);
		
		btnOdjava_1 = new JButton("Odjava");
		btnOdjava_1.setBounds(178, 11, 94, 23);
		fakturePanel.add(btnOdjava_1);
		btnOdjava_1.addActionListener(odjavaActionListener);
		
		lblOdaberiteTipFakture = new JLabel("Odaberite tip fakture:");
		lblOdaberiteTipFakture.setBounds(396, 15, 146, 14);
		fakturePanel.add(lblOdaberiteTipFakture);
		

		prodajaRadioButton = new JRadioButton("Prodaja");
		prodajaRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JRadioButton b = (JRadioButton)e.getSource();
				if(b.isSelected()) datePicker.setVisible(false);
				else datePicker.setVisible(false);
				iznajmiRadioButton.setSelected(false);
			}
		});
		prodajaRadioButton.setBounds(520, 11, 67, 23);
		fakturePanel.add(prodajaRadioButton);
		
		iznajmiRadioButton = new JRadioButton("Iznajmljivanje");
		iznajmiRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JRadioButton b = (JRadioButton) e.getSource();
				if(b.isSelected()) datePicker.setVisible(true);
				else datePicker.setVisible(false);
				prodajaRadioButton.setSelected(false);
			}
		});
		iznajmiRadioButton.setBounds(590, 11, 94, 23);
		fakturePanel.add(iznajmiRadioButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.inactiveCaption));
		panel_1.setBounds(10, 40, 360, 253);
		fakturePanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOdaberitePlinskiRezervoar = new JLabel("Odaberite plinski rezervoar");
		lblOdaberitePlinskiRezervoar.setBounds(26, 11, 191, 14);
		panel_1.add(lblOdaberitePlinskiRezervoar);
		
		plinskiRezervoariComboBox = new JComboBox();
		plinskiRezervoariComboBox.setBounds(205, 8, 126, 20);
		panel_1.add(plinskiRezervoariComboBox);
		
		JLabel lblDodajtePlinskeBoce = new JLabel("Dodajte plinske boce:");
		lblDodajtePlinskeBoce.setBounds(26, 37, 191, 14);
		panel_1.add(lblDodajtePlinskeBoce);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.inactiveCaption));
		panel_2.setBounds(26, 62, 305, 125);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		lblKapacitet.setBounds(10, 11, 85, 14);
		panel_2.add(lblKapacitet);
		
		JLabel lblBrojBoca = new JLabel("Broj boca:");
		lblBrojBoca.setBounds(105, 11, 86, 14);
		panel_2.add(lblBrojBoca);
		
		JLabel lbll_1 = new JLabel("5 (l)");
		lbll_1.setBounds(10, 36, 29, 14);
		panel_2.add(lbll_1);
		
		JLabel lbll_2 = new JLabel("10 (l)");
		lbll_2.setBounds(10, 61, 29, 14);
		panel_2.add(lbll_2);
		
		JLabel lbll = new JLabel("15 (l)");
		lbll.setBounds(10, 86, 36, 14);
		panel_2.add(lbll);
		
		petLitaraTextField = new JTextField();
		petLitaraTextField.setBounds(105, 33, 86, 20);
		panel_2.add(petLitaraTextField);
		petLitaraTextField.setColumns(10);
		
		desetLitaraTextField = new JTextField();
		desetLitaraTextField.setBounds(105, 58, 86, 20);
		panel_2.add(desetLitaraTextField);
		desetLitaraTextField.setColumns(10);
		
		petnaestLitaraTextField = new JTextField();
		petnaestLitaraTextField.setBounds(105, 83, 86, 20);
		panel_2.add(petnaestLitaraTextField);
		petnaestLitaraTextField.setColumns(10);
		
		lblDostupno = new JLabel("Dostupno:");
		lblDostupno.setBounds(218, 11, 77, 14);
		panel_2.add(lblDostupno);
		
		petLitaraLabel = new JLabel("");
		petLitaraLabel.setBounds(228, 36, 46, 14);
		panel_2.add(petLitaraLabel);
		
		desetLitaraLabel = new JLabel("");
		desetLitaraLabel.setBounds(228, 61, 46, 14);
		panel_2.add(desetLitaraLabel);
		
		petnaestLitaraLabel = new JLabel("");
		petnaestLitaraLabel.setBounds(228, 86, 46, 14);
		panel_2.add(petnaestLitaraLabel);
		
		btnUnesiteRezervoare = new JButton("Unesite rezervoare");
		btnUnesiteRezervoare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlinskiRezervoar pr = (PlinskiRezervoar) plinskiRezervoariComboBox.getSelectedItem();
				boolean success = skladiste.addPlinskiRezervoar(pr);
				if(!success) showMessageBox("Već ste dodali ovaj plinski rezervoar!", "Greška");
				else addRowsToTableRezervoari();
			}
		});
		btnUnesiteRezervoare.setBounds(187, 219, 144, 23);
		panel_1.add(btnUnesiteRezervoare);
		
		dodajBoceButton = new JButton("Dodaj boce");
		dodajBoceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String petLitara = petLitaraTextField.getText();
			String desetLitara = desetLitaraTextField.getText();
			String petnaestLitara = petnaestLitaraTextField.getText();
			if(petLitara.equals("") && desetLitara.equals("") && petnaestLitara.equals("")) return;
			String errorMessagePetLitara = "";
			String errorMessageDesetLitara = "";
			String errorMessagePetnaestLitara = "";
			if(!petLitara.equals(""))errorMessagePetLitara = validator.validateQuantity(petLitara, Integer.valueOf(petLitaraLabel.getText()));
			if(!desetLitara.equals(""))errorMessageDesetLitara = validator.validateQuantity(desetLitara, Integer.valueOf(desetLitaraLabel.getText()));
			if(!petnaestLitara.equals("")) errorMessagePetnaestLitara = validator.validateQuantity(petnaestLitara, Integer.valueOf(petnaestLitaraLabel.getText()));
				if(!errorMessagePetLitara.equals("")){ showMessageBox(errorMessagePetLitara, "Greška");  return;}
				else if(!errorMessageDesetLitara.equals("")){ showMessageBox(errorMessageDesetLitara, "Greška");  return;}
				else if(!errorMessagePetnaestLitara.equals("")){ showMessageBox(errorMessagePetnaestLitara, "Greška");  return;}
			if(!petLitara.equals(""))	skladiste.addPetLitarske(Integer.valueOf(petLitara));
			if(!desetLitara.equals("")) skladiste.addDesetLitarske(Integer.valueOf(desetLitara));
		    if(!petnaestLitara.equals(""))skladiste.addPetnaestLitarske(Integer.valueOf(petnaestLitara));
			// dodavanje redova u tabelu
		    addRowsToTableBoce();
		    int oldPet = Integer.valueOf(petLitaraLabel.getText());
				int oldDeset = Integer.valueOf(desetLitaraLabel.getText());
				int oldPetnaest = Integer.valueOf(petnaestLitaraLabel.getText());
				if(!petLitara.equals(""))	petLitaraLabel.setText(oldPet - Integer.valueOf(petLitara) + "");
				if(!desetLitara.equals("")) desetLitaraLabel.setText(oldDeset - Integer.valueOf(desetLitara) + "");
				if(!petnaestLitara.equals("")) petnaestLitaraLabel.setText(oldPetnaest - Integer.valueOf(petnaestLitara) + "");
			}
		});
		dodajBoceButton.setBounds(27, 219, 150, 23);
		panel_1.add(dodajBoceButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(SystemColor.inactiveCaption));
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_3.setBounds(385, 40, 393, 253);
		fakturePanel.add(panel_3);
		panel_3.setLayout(null);
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setEnabled(false);
		datePicker.setSize(128, 23);
		datePicker.setLocation(105, 219);
		
		JLabel lblOdaberiteKomitenta = new JLabel("Odaberite komitenta:");
		lblOdaberiteKomitenta.setBounds(93, 11, 140, 14);
		panel_3.add(lblOdaberiteKomitenta);
		
		komitentFaktureComboBox = new JComboBox();
		komitentFaktureComboBox.setBounds(243, 8, 140, 20);
		panel_3.add(komitentFaktureComboBox);
		panel_3.add(datePicker);
		btnKreirajFaktru = new JButton("Kreiraj fakturu");
		btnKreirajFaktru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(skladiste.isEmpty()) { showMessageBox("Niste izabrali nijedan rezervoar ili bocu!", "Greška"); return; }
				LinkedList<Triplet<PlinskaBoca, Date, Double>> boceStavke = new LinkedList<Triplet<PlinskaBoca, Date, Double>>();
				LinkedList<Triplet<PlinskiRezervoar, Date, Double>> rezervoariStavke = new LinkedList<Triplet<PlinskiRezervoar, Date, Double>>();
				Komitent k = (Komitent) komitentFaktureComboBox.getSelectedItem();
				SkladisteDataSource skl = new SkladisteDataSource();
				PlinskiRezervoarDataSource prd = new PlinskiRezervoarDataSource();
				if(prodajaRadioButton.isSelected()){
					FaktureProdajeDataSource fds = new FaktureProdajeDataSource();
					if(skladiste.getPetLitarske() != 0)
					{
						boceStavke.add(new Triplet<PlinskaBoca, Date, Double>(new PlinskaBoca(5, 10, skladiste.getPetLitarske()), new Date(), (double) 10 * skladiste.getPetLitarske()));
						skl.update(5, Integer.valueOf(petLitaraTextField.getText()));
					}
					if(skladiste.getDesetLitarske() != 0)
					{
						boceStavke.add(new Triplet<PlinskaBoca, Date, Double>(new PlinskaBoca(10, 20, skladiste.getDesetLitarske()), new Date(), (double) 20 * skladiste.getDesetLitarske()));
						skl.update(10, Integer.valueOf(desetLitaraTextField.getText()));
					}
					if(skladiste.getPetnaestLitarske() != 0) 
					{
						boceStavke.add(new Triplet<PlinskaBoca, Date, Double>(new PlinskaBoca(15, 30, skladiste.getPetnaestLitarske()), new Date(), (double) 30 * skladiste.getPetnaestLitarske()));
						skl.update(15, Integer.valueOf(petnaestLitaraTextField.getText()));
					}
					LinkedList<PlinskiRezervoar> rezervoari = skladiste.getPlinskiRezervoari();
					for(int i = 0; i < rezervoari.size(); i++)
					{
						rezervoariStavke.add(new Triplet<PlinskiRezervoar, Date, Double>(rezervoari.get(i), new Date(), (double) rezervoari.get(i).getKapacitet() * PlinskiRezervoar.RESERVOIR_PRICE_CONSTANT));
						prd.updateStatus(rezervoari.get(i).getId(), k.getAdresa(), "Prodat");
					}
					FakturaProdaje fp = new FakturaProdaje(k, boceStavke, rezervoariStavke);
					fds.insert(fp);
					ReportManager rm = new ReportManager();
					rm.printReport(4, new Triplet<String, String, Integer>(k.toString(), fds.formBrojFakture(), fds.getMaxId()));
					skladiste.clearSkladiste();
					clearFaktureControls();
					clearTable();
					fillCMB();
				}
				else if(iznajmiRadioButton.isSelected())
				{
					Date iznajmiDatum = (Date)datePicker.getModel().getValue();
					if(iznajmiDatum == null){ showMessageBox("Morate izabrati datum", "Greška"); return; }
					else if(iznajmiDatum.before(new Date())) { showMessageBox("Datum mora biti veći od trenutnog!", "Greška"); return;}
					FaktureIznajmljivanjaDataSource fds = new FaktureIznajmljivanjaDataSource();
					if(skladiste.getPetLitarske() != 0){
						boceStavke.add(new Triplet<PlinskaBoca, Date, Double>(new PlinskaBoca(5, 10, skladiste.getPetLitarske()), iznajmiDatum, (double) 10 * skladiste.getPetLitarske()));
						skl.update(5, Integer.valueOf(petLitaraTextField.getText()));
					}
					if(skladiste.getDesetLitarske() != 0) 
					{
						boceStavke.add(new Triplet<PlinskaBoca, Date, Double>(new PlinskaBoca(10, 20, skladiste.getDesetLitarske()), iznajmiDatum, (double) 20 * skladiste.getDesetLitarske()));
						skl.update(10, Integer.valueOf(desetLitaraTextField.getText()));
					}
					if(skladiste.getPetnaestLitarske() != 0) 
					{
						boceStavke.add(new Triplet<PlinskaBoca, Date, Double>(new PlinskaBoca(15, 30, skladiste.getPetnaestLitarske()), iznajmiDatum, (double) 30 * skladiste.getPetnaestLitarske()));
						skl.update(15, Integer.valueOf(petnaestLitaraTextField.getText()));
					}
					LinkedList<PlinskiRezervoar> rezervoari = skladiste.getPlinskiRezervoari();
					for(int i = 0; i < rezervoari.size(); i++)
					{
						rezervoariStavke.add(new Triplet<PlinskiRezervoar, Date, Double>(rezervoari.get(i), iznajmiDatum, (double) rezervoari.get(i).getKapacitet() * PlinskiRezervoar.RESERVOIR_PRICE_CONSTANT));
						prd.updateStatus(rezervoari.get(i).getId(), k.getAdresa(), "Iznajmljen do " + (new SimpleDateFormat("dd-MM-yyyy").format(iznajmiDatum)));
					}
					FakturaIznajmljivanje fp = new FakturaIznajmljivanje(k, boceStavke, rezervoariStavke);
					fds.insert(fp);
					ReportManager rm = new ReportManager();
					rm.printReport(5, new Triplet<String, String, Integer>(k.toString(), fds.formBrojFakture(), fds.getMaxId()));
					skladiste.clearSkladiste();
					clearTable();
					clearFaktureControls();
					fillCMB();
				}
			}
		});
		btnKreirajFaktru.setBounds(243, 219, 140, 23);
		panel_3.add(btnKreirajFaktru);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 373, 178);
		panel_3.add(scrollPane);
		
		faktureTable = new JTable();
		faktureTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tip rezervoara", "Serijski broj", "Kapacitet(l)", "Tezina(kg)", "Broj boca/rezervoara", "Cijena"
			}
		));
		scrollPane.setViewportView(faktureTable);
		
		JPanel rezervoariPanel = new JPanel();
		tabbedPane.addTab("Administracija rezervoara", null, rezervoariPanel, null);
		rezervoariPanel.setLayout(null);
		
		JPanel administracijaRezervoaraPanel = new JPanel();
		administracijaRezervoaraPanel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		administracijaRezervoaraPanel.setBounds(10, 42, 231, 251);
		rezervoariPanel.add(administracijaRezervoaraPanel);
		administracijaRezervoaraPanel.setLayout(null);
		
		JLabel lblFunkcijeZaAdministraciju_1 = new JLabel("Funkcije za administraciju rezervoara:");
		lblFunkcijeZaAdministraciju_1.setBounds(10, 11, 231, 14);
		administracijaRezervoaraPanel.add(lblFunkcijeZaAdministraciju_1);
		
		JButton dodajVelikiRezervoarButton = new JButton("Dodavanje velikih plinskih rezervoara");
		dodajVelikiRezervoarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosPlinskogRezervoara upr = new UnosPlinskogRezervoara();
				upr.setVisible(true);
			}
		});
		dodajVelikiRezervoarButton.setBounds(10, 47, 211, 23);
		administracijaRezervoaraPanel.add(dodajVelikiRezervoarButton);
		
		JButton izmjenaPodatakaVelikiRezervoarButton = new JButton("Izmjena podataka velikih p. rezervoara");
		izmjenaPodatakaVelikiRezervoarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IzmjenaPodatakaRezervoara i = new IzmjenaPodatakaRezervoara();
				i.setVisible(true);
			}
		});
		izmjenaPodatakaVelikiRezervoarButton.setBounds(10, 90, 211, 23);
		administracijaRezervoaraPanel.add(izmjenaPodatakaVelikiRezervoarButton);
		
		JButton brisanjeVelikiRezervoarButton = new JButton("Brisanje velikog plinskog rezervoara");
		brisanjeVelikiRezervoarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BrisanjePlinskogRezervoara p = new BrisanjePlinskogRezervoara();
				p.setVisible(true);
			}
		});
		brisanjeVelikiRezervoarButton.setBounds(10, 134, 211, 23);
		administracijaRezervoaraPanel.add(brisanjeVelikiRezervoarButton);
		
		JButton btnNewButton_1 = new JButton("Unos promjena na rezervoaru");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UnosPromjeneRezervoara i = new UnosPromjeneRezervoara();
				i.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 173, 211, 23);
		administracijaRezervoaraPanel.add(btnNewButton_1);
		
		JButton dodajPlinskeBoceButton = new JButton("Dodavanje plinskih boca");
		dodajPlinskeBoceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodavanjePlinskihBoca d = new DodavanjePlinskihBoca();
				d.setVisible(true);
			}
		});
		dodajPlinskeBoceButton.setBounds(10, 207, 211, 23);
		administracijaRezervoaraPanel.add(dodajPlinskeBoceButton);
		
		prijavaRezervoariLabel = new JLabel("Prijavljeni ste kao: ");
		prijavaRezervoariLabel.setBounds(10, 11, 184, 14);
		rezervoariPanel.add(prijavaRezervoariLabel);
		
		odjavaRezervoariButton = new JButton("Odjava");
		odjavaRezervoariButton.setBounds(158, 7, 83, 23);
		rezervoariPanel.add(odjavaRezervoariButton);
		odjavaRezervoariButton.addActionListener(odjavaActionListener);
		
		JLabel lblIzaberiteKriterijPretrage = new JLabel("Odaberite kriterij pretrage:");
		lblIzaberiteKriterijPretrage.setBounds(251, 11, 167, 14);
		rezervoariPanel.add(lblIzaberiteKriterijPretrage);
		
		kriterijPretrageRezervoariComboBox = new JComboBox();
		kriterijPretrageRezervoariComboBox.setModel(new DefaultComboBoxModel(new String[] {"Serijski broj", "Kapacitet"}));
		kriterijPretrageRezervoariComboBox.setBounds(406, 8, 111, 20);
		rezervoariPanel.add(kriterijPretrageRezervoariComboBox);
		
		JButton btnNewButton = new JButton("Pretraga rezervoara");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String terminPretrage = pretragaRezervoarTF.getText();
				if(terminPretrage.equals(""))  {showMessageBox("Morate unijeti termin za pretragu!", "Greška"); return; }
				
				PlinskiRezervoarDataSource pds = new PlinskiRezervoarDataSource();
				LinkedList<PlinskiRezervoar> pr = pds.getAllNoStatus();
				DefaultTableModel rezervoariModel = (DefaultTableModel)pretragaRezervoaraTable.getModel();
				rezervoariModel.setRowCount(0);
				int matching = 0;
				if(((String)kriterijPretrageRezervoariComboBox.getSelectedItem()).equals("Serijski broj"))
				{
					
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							if(pr.get(i).getSerijskiBroj().equals(terminPretrage)){
								matching++;
								PlinskiRezervoar p = pr.get(i);
								rezervoariModel.addRow(new Object[] {p.getSerijskiBroj(), p.getKapacitet(), p.getTrenutniStatus(), p.getTezina()});
							}
						}
					}
				}
				else
				{
					if(!validator.validateOnlyNumbers(terminPretrage, "Termin za pretragu").equals(""))
					{
						showMessageBox(validator.validateOnlyNumbers(terminPretrage, "Termin za pretragu"), "Greška");
						return;
					}
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							if(pr.get(i).getKapacitet() == Integer.valueOf(terminPretrage)){
								matching++;
								PlinskiRezervoar p = pr.get(i);
								rezervoariModel.addRow(new Object[] {p.getSerijskiBroj(), p.getKapacitet(), p.getTrenutniStatus(), p.getTezina()});
							}
						}
					}
				}
				if(matching == 0) {showMessageBox("Ne postoji rezervoar koji odgovara kriteriju pretrage!", "Greška"); }
			}
		});
		btnNewButton.setBounds(617, 7, 161, 23);
		rezervoariPanel.add(btnNewButton);
		
		pretragaRezervoarTF = new JTextField();
		pretragaRezervoarTF.setBounds(520, 8, 86, 20);
		rezervoariPanel.add(pretragaRezervoarTF);
		pretragaRezervoarTF.setColumns(10);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(251, 42, 527, 251);
		rezervoariPanel.add(scrollPane_2);
		
		pretragaRezervoaraTable = new JTable();
		pretragaRezervoaraTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Serijski broj", "Kapacitet", "Trenutni status", "Tezina"
			}
		));
		pretragaRezervoaraTable.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		scrollPane_2.setViewportView(pretragaRezervoaraTable);
		
		JPanel komitentiPanel = new JPanel();
		tabbedPane.addTab("Administracija komitenata", null, komitentiPanel, null);
		komitentiPanel.setLayout(null);
		
		JPanel administracijaKomitenataPanel = new JPanel();
		administracijaKomitenataPanel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		administracijaKomitenataPanel.setBounds(10, 37, 231, 256);
		komitentiPanel.add(administracijaKomitenataPanel);
		administracijaKomitenataPanel.setLayout(null);
		
		JLabel lblFunkcijeZaAdministraciju = new JLabel("Funkcije za administraciju komitenata:");
		lblFunkcijeZaAdministraciju.setBounds(10, 11, 259, 14);
		administracijaKomitenataPanel.add(lblFunkcijeZaAdministraciju);
		
		JButton dodajPravnogKomitentaButton = new JButton("Dodavanje pravnog komitenta");
		dodajPravnogKomitentaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DodavanjePravnogKomitenta dpk = new DodavanjePravnogKomitenta();
				dpk.setVisible(true);
			}
		});
		dodajPravnogKomitentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dodajPravnogKomitentaButton.setBounds(10, 49, 211, 23);
		administracijaKomitenataPanel.add(dodajPravnogKomitentaButton);
		
		JButton dodajFizickogKomitentaButton = new JButton("Dodavanje fizičkog komitenta");
		dodajFizickogKomitentaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DodavanjeFizickogKomitenta dfk = new DodavanjeFizickogKomitenta();
				dfk.setVisible(true);
			}
		});
		dodajFizickogKomitentaButton.setBounds(10, 83, 211, 23);
		administracijaKomitenataPanel.add(dodajFizickogKomitentaButton);
		
		JButton izmijeniKomitentaButton = new JButton("Izmjena podataka fizičkog komitenta");
		izmijeniKomitentaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IzmjenaPodatakaFizickogKomitenta ipfk = new IzmjenaPodatakaFizickogKomitenta();
				ipfk.setVisible(true);
			}
		});
		izmijeniKomitentaButton.setBounds(10, 117, 211, 23);
		administracijaKomitenataPanel.add(izmijeniKomitentaButton);
		
		JButton brisanjeKomitentaButton = new JButton("Brisanje komitenta");
		brisanjeKomitentaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BrisanjeKomitenta bk = new BrisanjeKomitenta();
				bk.setVisible(true);
			}
		});
		brisanjeKomitentaButton.setBounds(10, 151, 211, 23);
		administracijaKomitenataPanel.add(brisanjeKomitentaButton);
		
		izmijeniPravniButton = new JButton("Izmjena podataka pravnog komitenta");
		izmijeniPravniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzmjenaPodatakaPravnogKomitenta ip = new IzmjenaPodatakaPravnogKomitenta();
				ip.setVisible(true);
			}
		});
		izmijeniPravniButton.setBounds(10, 185, 211, 23);
		administracijaKomitenataPanel.add(izmijeniPravniButton);
		
		prijavaKomitentiLabel = new JLabel("Prijavljeni ste kao: ");
		prijavaKomitentiLabel.setBounds(10, 11, 164, 14);
		komitentiPanel.add(prijavaKomitentiLabel);
		
		odjavaButton = new JButton("Odjava");
		odjavaButton.setBounds(168, 7, 73, 23);
		komitentiPanel.add(odjavaButton);
		odjavaButton.addActionListener(odjavaActionListener);
		
		JLabel lblOdaberiteKriterijPretrage_1 = new JLabel("Odaberite kriterij pretrage:");
		lblOdaberiteKriterijPretrage_1.setBounds(251, 12, 192, 14);
		komitentiPanel.add(lblOdaberiteKriterijPretrage_1);
		
		kriterijPretrageKomitentiComboBox = new JComboBox();
		kriterijPretrageKomitentiComboBox.setModel(new DefaultComboBoxModel(new String[] {"Ime", "Prezime", "Naziv firme"}));
		kriterijPretrageKomitentiComboBox.setBounds(404, 8, 105, 20);
		komitentiPanel.add(kriterijPretrageKomitentiComboBox);
		
		JButton pretragaKomitenataButton = new JButton("Pretraga komitenata");
		pretragaKomitenataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String terminPretrage = pretragaKomitentiTF.getText();
				if(terminPretrage.equals(""))  {showMessageBox("Morate unijeti termin za pretragu!", "Greška"); return; }
				
				KomitentDataSource pds = new KomitentDataSource();
				LinkedList<Komitent> pr = pds.getAll();
				DefaultTableModel komitentiModel = (DefaultTableModel)pretragaKomitenataTable.getModel();
				komitentiModel.setRowCount(0);
				int matching = 0;
				String kriterij = (String)kriterijPretrageKomitentiComboBox.getSelectedItem();
				if(kriterij.equals("Ime"))
				{
					
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							Komitent k = pr.get(i);
							if(k.getTipKomitenta().equals("Pravno lice")) continue;
							if(((FizickiKomitent)k).getIme().equals(terminPretrage)){
								matching++;
								FizickiKomitent fk = (FizickiKomitent) k;
								komitentiModel.addRow(new Object[] {"-", "-", fk.getIme() + " " + fk.getPrezime(), fk.getBrojLicneKarte(), fk.getAdresa()});
							}
						}
					}
				}
				else if(kriterij.equals("Prezime"))
				{
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							Komitent k = pr.get(i);
							if(k.getTipKomitenta().equals("Pravno lice")) continue;
							if(((FizickiKomitent)k).getPrezime().equals(terminPretrage)){
								matching++;
								FizickiKomitent fk = (FizickiKomitent) k;
								komitentiModel.addRow(new Object[] {"-", "-", fk.getIme() + " " + fk.getPrezime(), fk.getBrojLicneKarte(), fk.getAdresa()});
							}
						}
					}
				}
				else
				{
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							Komitent k = pr.get(i);
							if(k.getTipKomitenta().equals("Fizicko lice")) continue;
							if(((PravniKomitent)k).getNazivFirme().equals(terminPretrage)){
								matching++;
								PravniKomitent fk = (PravniKomitent) k;
								komitentiModel.addRow(new Object[] {fk.getNazivFirme(), fk.getPDVbroj(), "-", "-", fk.getAdresa()});
							}
						}
					}
				}
				if(matching == 0) {showMessageBox("Ne postoji komitent koji odgovara kriteriju pretrage!", "Greška"); }
			}
		});
		pretragaKomitenataButton.setBounds(625, 7, 153, 23);
		komitentiPanel.add(pretragaKomitenataButton);
		
		pretragaKomitentiTF = new JTextField();
		pretragaKomitentiTF.setBounds(529, 8, 86, 20);
		komitentiPanel.add(pretragaKomitentiTF);
		pretragaKomitentiTF.setColumns(10);
		
		scroll = new JScrollPane();
		scroll.setBounds(251, 37, 527, 256);
		komitentiPanel.add(scroll);
		
		pretragaKomitenataTable = new JTable();
		pretragaKomitenataTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Naziv firme", "PDV broj", "Ime i prezime", "Broj lične karte", "Adresa"
			}
		));
		scroll.setViewportView(pretragaKomitenataTable);
		
		JPanel korisniciPanel = new JPanel();
		tabbedPane.addTab("Administracija korisnika", null, korisniciPanel, null);
		korisniciPanel.setLayout(null);
		
		JPanel adminFunkcijePanel = new JPanel();
		adminFunkcijePanel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		adminFunkcijePanel.setBounds(10, 41, 246, 252);
		korisniciPanel.add(adminFunkcijePanel);
		adminFunkcijePanel.setLayout(null);
		
		JLabel lblFunkcijeZaAdministriranje = new JLabel("Funkcije za administraciju korisnika:");
		lblFunkcijeZaAdministriranje.setBounds(20, 11, 226, 14);
		adminFunkcijePanel.add(lblFunkcijeZaAdministriranje);
		
		JButton dodajKorisnikaButton = new JButton("Dodavanje korisnika");
		dodajKorisnikaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DodavanjeNovogKorisnika dodavanjeKorisnika = new DodavanjeNovogKorisnika();
				dodavanjeKorisnika.setModal(true);
				dodavanjeKorisnika.setVisible(true);
			}
		});
		dodajKorisnikaButton.setBounds(20, 49, 198, 23);
		adminFunkcijePanel.add(dodajKorisnikaButton);
		
		JButton brisanjeKorisnikaButton = new JButton("Brisanje korisnika");
		brisanjeKorisnikaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BrisanjeKorisnika brisanjeKorisnika = new BrisanjeKorisnika();
				brisanjeKorisnika.setModal(true);
				brisanjeKorisnika.setVisible(true);
				
			}
		});
		brisanjeKorisnikaButton.setBounds(20, 102, 198, 23);
		adminFunkcijePanel.add(brisanjeKorisnikaButton);
		
		JButton izmjenaKorisnikaButton = new JButton("Izmjena podataka korisnika");
		izmjenaKorisnikaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IzmjenaPodatakaKorisnika ipkf  = new IzmjenaPodatakaKorisnika();
				//ipkf.setModal(true);
				ipkf.setVisible(true);
			}
		});
	
		izmjenaKorisnikaButton.setBounds(20, 153, 198, 23);
		adminFunkcijePanel.add(izmjenaKorisnikaButton);
		
		prijavaKorisniciLabel = new JLabel("Prijavljeni ste kao:");
		prijavaKorisniciLabel.setBounds(10, 11, 194, 14);
		korisniciPanel.add(prijavaKorisniciLabel);
		
		JLabel lblOdaberiteKriterijPretrage = new JLabel("Odaberite kriterij pretrage:");
		lblOdaberiteKriterijPretrage.setBounds(266, 11, 167, 14);
		korisniciPanel.add(lblOdaberiteKriterijPretrage);
		
		kriterijPretrageKorisniciComboBox = new JComboBox();
		kriterijPretrageKorisniciComboBox.setModel(new DefaultComboBoxModel(new String[] {"Korisničko ime", "Ime", "Prezime"}));
		kriterijPretrageKorisniciComboBox.setBounds(426, 8, 113, 20);
		korisniciPanel.add(kriterijPretrageKorisniciComboBox);
		
		JButton pretragaKorisnikaButton = new JButton("Pretraga korisnika");
		pretragaKorisnikaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String terminPretrage = korisnikPretragaTF.getText();
				if(terminPretrage.equals(""))  {showMessageBox("Morate unijeti termin za pretragu!", "Greška"); return; }
				
				KorisnikDataSource pds = new KorisnikDataSource();
				LinkedList<Korisnik> pr = pds.getAll();
				DefaultTableModel korisniciModel = (DefaultTableModel)korisniciTable.getModel();
				korisniciModel.setRowCount(0);
				int matching = 0;
				String kriterij = (String)kriterijPretrageKorisniciComboBox.getSelectedItem();
				if(kriterij.equals("Ime"))
				{
					
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							Korisnik k = pr.get(i);
							if((k.getIme().equals(terminPretrage))){
								matching++;
								korisniciModel.addRow(new Object[] {k.getIme(), k.getPrezime(), k.getUsername(), k.getAdresa(), k.getBrojTelefona()});
							}
						}
					}
				}
				else if(kriterij.equals("Prezime"))
				{
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							Korisnik k = pr.get(i);
							if((k.getPrezime().equals(terminPretrage))){
								matching++;
								korisniciModel.addRow(new Object[] {k.getIme(), k.getPrezime(), k.getUsername(), k.getAdresa(), k.getBrojTelefona()});
							}
						}
					}
				}
				else
				{
					if(pr != null)
					{
						for(int i = 0; i < pr.size(); i++)
						{
							Korisnik k = pr.get(i);
							if((k.getUsername().equals(terminPretrage))){
								matching++;
								korisniciModel.addRow(new Object[] {k.getIme(), k.getPrezime(), k.getUsername(), k.getAdresa(), k.getBrojTelefona()});
							}
						}
					}
				}
				if(matching == 0) {showMessageBox("Ne postoji korisnik koji odgovara kriteriju pretrage!", "Greška"); }
			}
		});
		pretragaKorisnikaButton.setBounds(631, 7, 147, 23);
		korisniciPanel.add(pretragaKorisnikaButton);
		
		btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(172, 7, 84, 23);
		korisniciPanel.add(btnOdjava);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(276, 41, 502, 252);
		korisniciPanel.add(scrollPane_1);
		
		korisniciTable = new JTable();
		korisniciTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime", "Prezime", "Korisničko ime", "Adresa", "Telefon"
			}
		));
		korisniciTable.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		scrollPane_1.setViewportView(korisniciTable);
		
		korisnikPretragaTF = new JTextField();
		korisnikPretragaTF.setBounds(541, 8, 86, 20);
		korisniciPanel.add(korisnikPretragaTF);
		korisnikPretragaTF.setColumns(10);
		btnOdjava.addActionListener(odjavaActionListener);
		
		
		PreferenceManager pm = new PreferenceManager();
		pm.setDBPassword("2014SIEtf");
		
		PlinskiRezervoarDataSource pds = new PlinskiRezervoarDataSource();
		int count = pds.getAllBazdarenjeSoon().size();
		if(count != 0 && count != -1)
		{
			ObavijestBazdarenje ob = new ObavijestBazdarenje();
			ob.setVisible(true);
		}

	}
	
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	public void fillComboBoxItemsOnTabChanged(int tabIndex, JTabbedPane jPane)
	{
		switch(tabIndex)
		{
		case 0:
			skladiste.clearSkladiste();
			break;
		case 1:
			KomitentDataSource kds = new KomitentDataSource();
			   LinkedList<Komitent> komitenti = kds.getAll();
			   if(komitenti != null)
			   {
				   komitentFaktureComboBox.removeAllItems();
				   for(int i = 0; i < komitenti.size(); i++)
				   {
					   if(komitenti.get(i).getTipKomitenta().equals("Pravno lice"))
					   komitentFaktureComboBox.addItem((PravniKomitent)komitenti.get(i));
					   else komitentFaktureComboBox.addItem((FizickiKomitent)komitenti.get(i)); 
				   }
			   }
			fillCMB();
			// Setovanje labela
			SkladisteDataSource sd = new SkladisteDataSource();
			int petLitara = sd.getQuantityByCapacity(5);
			int desetLitara = sd.getQuantityByCapacity(10);
			int petnaestLitara = sd.getQuantityByCapacity(15);
			petLitaraLabel.setText((petLitara != -1 ? petLitara : 0) + "");
			petLitaraLabel.setSize(petLitaraLabel.getPreferredSize());
			desetLitaraLabel.setText((desetLitara != -1 ? desetLitara : 0) + "");
			desetLitaraLabel.setSize(desetLitaraLabel.getPreferredSize());
			petnaestLitaraLabel.setText((petnaestLitara != -1 ? petnaestLitara : 0) + "");
			petnaestLitaraLabel.setSize(petLitaraLabel.getPreferredSize());
			break;
		case 2:
			skladiste.clearSkladiste();
			break;
		case 3:
			skladiste.clearSkladiste();
			break;
		case 4:
			skladiste.clearSkladiste();
			break;
		}
	}
	
	
	private void clearFaktureControls()
	{
		petLitaraTextField.setText("");
		desetLitaraTextField.setText("");
		petnaestLitaraTextField.setText("");
		iznajmiRadioButton.setSelected(false);
		prodajaRadioButton.setSelected(false);
		setAllLabels();
		
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setTip(String tipKorisnika)
	{
		this.tipKorisnika = tipKorisnika;
	}
	
	public void setAllLabels()
	{
		prijavaIzvjestajiLabel.setText("Prijavljeni ste kao: " + username);
		prijavaIzvjestajiLabel.setSize(prijavaIzvjestajiLabel.getPreferredSize());
		prijavaKomitentiLabel.setText("Prijavljeni ste kao: " + username);
		prijavaKomitentiLabel.setSize(prijavaKomitentiLabel.getPreferredSize());
		prijavaIzvjestajiLabel.setText("Prijavljeni ste kao: " + username);
		prijavaIzvjestajiLabel.setSize(prijavaIzvjestajiLabel.getPreferredSize());
		prijavaRezervoariLabel.setText("Prijavljeni ste kao: " + username);
		prijavaRezervoariLabel.setSize(prijavaRezervoariLabel.getPreferredSize());
		prijavaFaktureLabel.setText("Prijavljeni ste kao: " + username);
		prijavaFaktureLabel.setSize(prijavaIzvjestajiLabel.getPreferredSize());
		prijavaKorisniciLabel.setText("Prijavljeni ste kao: " + username);
		prijavaKorisniciLabel.setSize(prijavaKorisniciLabel.getPreferredSize());
		if(!tipKorisnika.equals("Administrator"))
		{
			tabbedPane.setEnabledAt(4,  false);
		} else tabbedPane.setEnabledAt(4,  true);
	}
	
	private void addRowsToTableBoce()
	{
		DefaultTableModel model = (DefaultTableModel) faktureTable.getModel();
		int petice = skladiste.getPetLitarske();
		int desetke = skladiste.getDesetLitarske();
		int petnaeske = skladiste.getPetnaestLitarske();
		if(petice != 0) model.addRow(new Object[]{"Plinska boca", "-", "5", "5", petice, petice * 10});
		if(desetke != 0) model.addRow(new Object[]{"Plinska boca", "-", "10", "10", desetke, desetke * 20});
		if(petnaeske != 0) model.addRow(new Object[]{"Plinska boca", "-", "10", "10", petnaeske, petnaeske * 30});
	}
	
	private void addRowsToTableRezervoari()
	{
		DefaultTableModel model = (DefaultTableModel) faktureTable.getModel();
		LinkedList<PlinskiRezervoar> rezervoari = skladiste.getPlinskiRezervoari();
		for(int i = 0; i < rezervoari.size(); i++)
		{
			model.addRow(new Object[]{"Veliki plinski rezervoar", rezervoari.get(i).getSerijskiBroj(), rezervoari.get(i).getKapacitet(), rezervoari.get(i).getTezina(), "1", rezervoari.get(i).getKapacitet() * PlinskiRezervoar.RESERVOIR_PRICE_CONSTANT});
		}
	}

	private void clearTable()
	{
		DefaultTableModel model = (DefaultTableModel) faktureTable.getModel();
		model.setRowCount(0);
	}
	
	private void fillCMB()
	{
		PlinskiRezervoarDataSource pds = new PlinskiRezervoarDataSource();
		LinkedList<PlinskiRezervoar> rezervoarix = pds.getAll();
		if(rezervoarix != null)
		{
			plinskiRezervoariComboBox.removeAllItems();
			for(int i = 0; i < rezervoarix.size(); i++)
			{
				if(rezervoarix.get(i).getNapunjenost() == 1) plinskiRezervoariComboBox.addItem(rezervoarix.get(i));
			}
		}
	}
	
	class ItemChangeListener implements ItemListener, Serializable
	{

		private static final long serialVersionUID = 2079647288224998594L;

		public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   
	    	   // uzimamo objekat na kojem se desio događaj
	    	   Object sourceObject = event.getSource();
	    	   // određujemo koji je od objekata bio
	    	   if(sourceObject == izborIzvjestajaComboBox)
	    	   {
	    		   // selektovan je combobox na izvjestajima, sada mijenjamo parametre izvještaja na osnovu selekcije
	    		   Object selectedItem = event.getItem();
	    		   String selected = (String)selectedItem;
	    		   if(selected.equals("Izvještaj za pojedinačni veliki plinski rezervoar"))
	    		   {
	    			   serijskiBrojTextField.setVisible(true);
	    			   komitentIzvjestajComboBox.setVisible(false);
	    			   labelaParametriLabel.setText("Unesite serijski broj: ");
	    			   labelaParametriLabel.setSize(labelaParametriLabel.getPreferredSize());
	    		   }
	    		   else if(selected.equals("Izvještaj o trenutnom stanju za komitenta"))
	    		   {
	    			   serijskiBrojTextField.setVisible(false);
	    			   komitentIzvjestajComboBox.setVisible(true);
	    			   labelaParametriLabel.setText("Izaberite komitenta: ");
	    			   labelaParametriLabel.setSize(labelaParametriLabel.getPreferredSize());
	    			   KomitentDataSource kds = new KomitentDataSource();
	    			   LinkedList<Komitent> komitenti = kds.getAll();
	    			   if(komitenti != null)
	    			   {
	    				   komitentIzvjestajComboBox.removeAllItems();
	    				   for(int i = 0; i < komitenti.size(); i++)
	    				   {
	    					   if(komitenti.get(i).getTipKomitenta().equals("Pravno lice"))
	    					   komitentIzvjestajComboBox.addItem((PravniKomitent)komitenti.get(i));
	    					   else komitentIzvjestajComboBox.addItem((FizickiKomitent)komitenti.get(i)); 
	    				   }
	    			   }
	    		   }
	    		   else if(selected.equals("Izvještaj o iznajmljenim rezervoarima komitentu"))
	    		   {
	    			   serijskiBrojTextField.setVisible(false);
	    			   komitentIzvjestajComboBox.setVisible(true);
	    			   labelaParametriLabel.setText("Izaberite komitenta: ");
	    			   labelaParametriLabel.setSize(labelaParametriLabel.getPreferredSize());
	    			   KomitentDataSource kds = new KomitentDataSource();
	    			   LinkedList<Komitent> komitenti = kds.getAll();
	    			   if(komitenti != null)
	    			   {
	    				   komitentIzvjestajComboBox.removeAllItems();
	    				   for(int i = 0; i < komitenti.size(); i++)
	    				   {
	    					   if(komitenti.get(i).getTipKomitenta().equals("Pravno lice"))
	    					   komitentIzvjestajComboBox.addItem((PravniKomitent)komitenti.get(i));
	    					   else komitentIzvjestajComboBox.addItem((FizickiKomitent)komitenti.get(i)); 
	    				   }
	    			   }
	    		   }
	    		   else if(selected.equals("Izvještaj o stanju plinskih boca na skladištu"))
	    		   {
	    			   serijskiBrojTextField.setVisible(false);
	    			   komitentIzvjestajComboBox.setVisible(false);
	    			   labelaParametriLabel.setText("Za ovaj izvještaj nisu potrebni parametri.");
	    			   labelaParametriLabel.setSize(labelaParametriLabel.getPreferredSize());
	    		   }
	    		   else
	    		   {
	    			   serijskiBrojTextField.setVisible(false);
	    			   komitentIzvjestajComboBox.setVisible(false);
	    			   labelaParametriLabel.setText("Za ovaj izvještaj nisu potrebni parametri.");
	    			   labelaParametriLabel.setSize(labelaParametriLabel.getPreferredSize());
	    		   }
	    	   }
	       }
	    }   
	}
}
