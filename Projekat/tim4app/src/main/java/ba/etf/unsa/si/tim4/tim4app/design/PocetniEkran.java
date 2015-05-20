package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
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
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.reports.ReportManager;
import ba.etf.unsa.si.tim4.tim4app.validation.Validator;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.ListSelectionModel;

public class PocetniEkran extends JFrame {


	private JPanel contentPane;
	private JTable pretragaKorisnikaTable;
	private JTable pretragaKomitenataTable;
	private JTable pretragaRezervoaraTable;
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PocetniEkran() {
		setTitle("Početni ekran");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		validator = new Validator();
		
		comboBoxSelectionChangedListener = new ItemChangeListener();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 709, 332);
		contentPane.add(tabbedPane);
		
		JPanel izvjestajiPanel = new JPanel();
		tabbedPane.addTab("Izvještaji", null, izvjestajiPanel, null);
		izvjestajiPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		panel.setBounds(10, 35, 684, 258);
		izvjestajiPanel.add(panel);
		panel.setLayout(null);
		
		lblIzaberiteIzvjetaj = new JLabel("Izaberite izvještaj:");
		lblIzaberiteIzvjetaj.setBounds(10, 11, 158, 14);
		panel.add(lblIzaberiteIzvjetaj);
		
		izborIzvjestajaComboBox = new JComboBox();
		izborIzvjestajaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Izvještaj o stanju plinskih boca na skladištu", "Izvještaj o stanju velikih plinskih rezervoara na skladištu", "Izvještaj za pojedinačni veliki plinski rezervoar", "Izvještaj o trenutnom stanju za komitenta"}));
		izborIzvjestajaComboBox.setBounds(10, 36, 345, 20);
		izborIzvjestajaComboBox.addItemListener(comboBoxSelectionChangedListener);
		panel.add(izborIzvjestajaComboBox);
		izborIzvjestajaComboBox.setSelectedIndex(0);
		
		parametriIzvjestajaPanel = new JPanel();
		parametriIzvjestajaPanel.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		parametriIzvjestajaPanel.setBounds(380, 36, 294, 170);
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
					rm.printReport(comboIndex, komitentIzvjestajComboBox.getSelectedItem());
				}
				else 
				{
					if(!empty) rm.printReport(comboIndex, null);
					else { showMessageBox("Na skladištu nema plinskih boca!", "Greška"); return; }
				}
			}
		});
		kreirajIzvjestajButton.setBounds(538, 224, 136, 23);
		panel.add(kreirajIzvjestajButton);
		
		prijavaIzvjestajiLabel = new JLabel("Prijavljeni ste kao:");
		prijavaIzvjestajiLabel.setBounds(10, 10, 185, 14);
		izvjestajiPanel.add(prijavaIzvjestajiLabel);
		
		odjavaIzvjestajiButton = new JButton("Odjava");
		odjavaIzvjestajiButton.setBounds(185, 6, 96, 23);
		izvjestajiPanel.add(odjavaIzvjestajiButton);
		
		JPanel fakturePanel = new JPanel();
		tabbedPane.addTab("Fakture", null, fakturePanel, null);
		fakturePanel.setLayout(null);
		
		JLabel lblPrijavljeniSteKao_1 = new JLabel("Prijavljeni ste kao:");
		lblPrijavljeniSteKao_1.setBounds(30, 15, 94, 14);
		fakturePanel.add(lblPrijavljeniSteKao_1);
		
		JButton btnOdjava_1 = new JButton("Odjava");
		btnOdjava_1.setBounds(178, 11, 67, 23);
		fakturePanel.add(btnOdjava_1);
		
		JLabel lblOdaberiteTipFakture = new JLabel("Odaberite tip fakture:");
		lblOdaberiteTipFakture.setBounds(385, 16, 105, 14);
		fakturePanel.add(lblOdaberiteTipFakture);
		
		JRadioButton rdbtnProdaja = new JRadioButton("Prodaja");
		rdbtnProdaja.setBounds(511, 12, 67, 23);
		fakturePanel.add(rdbtnProdaja);
		
		JRadioButton rdbtnIznajmljivajne = new JRadioButton("Iznajmljivajne");
		rdbtnIznajmljivajne.setBounds(580, 12, 94, 23);
		fakturePanel.add(rdbtnIznajmljivajne);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 40, 360, 253);
		fakturePanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOdaberitePlinskiRezervoar = new JLabel("Odaberite plinski rezervoar");
		lblOdaberitePlinskiRezervoar.setBounds(26, 11, 145, 14);
		panel_1.add(lblOdaberitePlinskiRezervoar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(166, 8, 165, 20);
		panel_1.add(comboBox);
		
		JLabel lblDodajtePlinskeBoce = new JLabel("Dodajte plinske boce:");
		lblDodajtePlinskeBoce.setBounds(26, 37, 126, 14);
		panel_1.add(lblDodajtePlinskeBoce);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(26, 62, 305, 125);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		lblKapacitet.setBounds(53, 11, 57, 14);
		panel_2.add(lblKapacitet);
		
		JLabel lblBrojBoca = new JLabel("Broj boca:");
		lblBrojBoca.setBounds(148, 11, 57, 14);
		panel_2.add(lblBrojBoca);
		
		JLabel lbll_1 = new JLabel("5 (l)");
		lbll_1.setBounds(81, 36, 29, 14);
		panel_2.add(lbll_1);
		
		JLabel lbll_2 = new JLabel("10 (l)");
		lbll_2.setBounds(81, 64, 29, 14);
		panel_2.add(lbll_2);
		
		JLabel lbll = new JLabel("15 (l)");
		lbll.setBounds(81, 97, 36, 14);
		panel_2.add(lbll);
		
		textField = new JTextField();
		textField.setBounds(140, 36, 86, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 61, 86, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140, 94, 86, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnUnesiteRezervoare = new JButton("Unesite rezervoare");
		btnUnesiteRezervoare.setBounds(187, 210, 126, 23);
		panel_1.add(btnUnesiteRezervoare);
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_3.setBounds(385, 40, 309, 253);
		fakturePanel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblOdaberiteKomitenta = new JLabel("Odaberite komitenta:");
		lblOdaberiteKomitenta.setBounds(10, 11, 110, 14);
		panel_3.add(lblOdaberiteKomitenta);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(130, 8, 169, 20);
		panel_3.add(comboBox_1);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(25);
		table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		table.setFillsViewportHeight(true);
		table.setBounds(22, 63, 277, 125);
		panel_3.add(table);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Red. br.", "Tip rezervoara", "Serijski broj", "Kapacitet", "Tezina", "Kolicina", "Cijena"
			}
		));
		
		JButton btnKreirajFaktru = new JButton("Kreiraj fakturu");
		btnKreirajFaktru.setBounds(189, 211, 110, 31);
		panel_3.add(btnKreirajFaktru);
		table.getColumnModel().getColumn(0).setPreferredWidth(51);
		table.getColumnModel().getColumn(1).setPreferredWidth(84);
		
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
		dodajVelikiRezervoarButton.setBounds(10, 47, 211, 23);
		administracijaRezervoaraPanel.add(dodajVelikiRezervoarButton);
		
		JButton izmjenaPodatakaVelikiRezervoarButton = new JButton("Izmjena podataka velikih p. rezervoara");
		izmjenaPodatakaVelikiRezervoarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		izmjenaPodatakaVelikiRezervoarButton.setBounds(10, 90, 211, 23);
		administracijaRezervoaraPanel.add(izmjenaPodatakaVelikiRezervoarButton);
		
		JButton brisanjeVelikiRezervoarButton = new JButton("Brisanje velikog plinskog rezervoara");
		brisanjeVelikiRezervoarButton.setBounds(10, 134, 211, 23);
		administracijaRezervoaraPanel.add(brisanjeVelikiRezervoarButton);
		
		JButton btnNewButton_1 = new JButton("Unos promjena na rezervoaru");
		btnNewButton_1.setBounds(10, 173, 211, 23);
		administracijaRezervoaraPanel.add(btnNewButton_1);
		
		pretragaRezervoaraTable = new JTable();
		pretragaRezervoaraTable.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		pretragaRezervoaraTable.setBounds(251, 42, 443, 251);
		rezervoariPanel.add(pretragaRezervoaraTable);
		
		JLabel prijavaRezervoariLabel = new JLabel("Prijavljeni ste kao: ");
		prijavaRezervoariLabel.setBounds(10, 11, 184, 14);
		rezervoariPanel.add(prijavaRezervoariLabel);
		
		JButton odjavaRezervoariButton = new JButton("Odjava");
		odjavaRezervoariButton.setBounds(158, 7, 83, 23);
		rezervoariPanel.add(odjavaRezervoariButton);
		
		JLabel lblIzaberiteKriterijPretrage = new JLabel("Odaberite kriterij pretrage:");
		lblIzaberiteKriterijPretrage.setBounds(251, 11, 167, 14);
		rezervoariPanel.add(lblIzaberiteKriterijPretrage);
		
		JComboBox kriterijPretrageRezervoariComboBox = new JComboBox();
		kriterijPretrageRezervoariComboBox.setBounds(412, 8, 111, 20);
		rezervoariPanel.add(kriterijPretrageRezervoariComboBox);
		
		JButton btnNewButton = new JButton("Pretraga rezervoara");
		btnNewButton.setBounds(533, 7, 161, 23);
		rezervoariPanel.add(btnNewButton);
		
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
		dodajPravnogKomitentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dodajPravnogKomitentaButton.setBounds(26, 49, 181, 23);
		administracijaKomitenataPanel.add(dodajPravnogKomitentaButton);
		
		JButton dodajFizickogKomitentaButton = new JButton("Dodavanje fizičkog komitenta");
		dodajFizickogKomitentaButton.setBounds(26, 83, 181, 23);
		administracijaKomitenataPanel.add(dodajFizickogKomitentaButton);
		
		JButton izmijeniKomitentaButton = new JButton("Izmjena podataka komitenta");
		izmijeniKomitentaButton.setBounds(26, 117, 181, 23);
		administracijaKomitenataPanel.add(izmijeniKomitentaButton);
		
		JButton brisanjeKomitentaButton = new JButton("Brisanje komitenta");
		brisanjeKomitentaButton.setBounds(26, 151, 181, 23);
		administracijaKomitenataPanel.add(brisanjeKomitentaButton);
		
		pretragaKomitenataTable = new JTable();
		pretragaKomitenataTable.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		pretragaKomitenataTable.setBounds(251, 37, 443, 256);
		komitentiPanel.add(pretragaKomitenataTable);
		
		JLabel prijavaLabel_Komitenti = new JLabel("Prijavljeni ste kao: ");
		prijavaLabel_Komitenti.setBounds(10, 11, 164, 14);
		komitentiPanel.add(prijavaLabel_Komitenti);
		
		JButton odjavaButton = new JButton("Odjava");
		odjavaButton.setBounds(168, 7, 73, 23);
		komitentiPanel.add(odjavaButton);
		
		JLabel lblOdaberiteKriterijPretrage_1 = new JLabel("Odaberite kriterij pretrage:");
		lblOdaberiteKriterijPretrage_1.setBounds(251, 12, 192, 14);
		komitentiPanel.add(lblOdaberiteKriterijPretrage_1);
		
		JComboBox kriterijPretrageKomitentiComboBox = new JComboBox();
		kriterijPretrageKomitentiComboBox.setBounds(426, 8, 105, 20);
		komitentiPanel.add(kriterijPretrageKomitentiComboBox);
		
		JButton pretragaKomitenataButton = new JButton("Pretraga komitenata");
		pretragaKomitenataButton.setBounds(541, 7, 153, 23);
		komitentiPanel.add(pretragaKomitenataButton);
		
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
		dodajKorisnikaButton.setBounds(32, 49, 174, 23);
		adminFunkcijePanel.add(dodajKorisnikaButton);
		
		JButton brisanjeKorisnikaButton = new JButton("Brisanje korisnika");
		brisanjeKorisnikaButton.setBounds(32, 102, 174, 23);
		adminFunkcijePanel.add(brisanjeKorisnikaButton);
		
		JButton izmjenaKorisnikaButton = new JButton("Izmjena podataka korisnika");
		izmjenaKorisnikaButton.setBounds(32, 153, 174, 23);
		adminFunkcijePanel.add(izmjenaKorisnikaButton);
		
		pretragaKorisnikaTable = new JTable();
		pretragaKorisnikaTable.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient")));
		pretragaKorisnikaTable.setBounds(266, 41, 428, 252);
		korisniciPanel.add(pretragaKorisnikaTable);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao:");
		lblPrijavljeniSteKao.setBounds(10, 11, 194, 14);
		korisniciPanel.add(lblPrijavljeniSteKao);
		
		JLabel lblOdaberiteKriterijPretrage = new JLabel("Odaberite kriterij pretrage:");
		lblOdaberiteKriterijPretrage.setBounds(266, 11, 167, 14);
		korisniciPanel.add(lblOdaberiteKriterijPretrage);
		
		JComboBox kriterijPretrageComboBox = new JComboBox();
		kriterijPretrageComboBox.setBounds(424, 8, 113, 20);
		korisniciPanel.add(kriterijPretrageComboBox);
		
		JButton pretragaKorisnikaButton = new JButton("Pretraga korisnika");
		pretragaKorisnikaButton.setBounds(547, 7, 147, 23);
		korisniciPanel.add(pretragaKorisnikaButton);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(172, 7, 84, 23);
		korisniciPanel.add(btnOdjava);
		
	}
	
	
	private void showMessageBox(String message, String messageBoxTitle)
	{
		JOptionPane.showMessageDialog(null, message, messageBoxTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	class ItemChangeListener implements ItemListener
	{
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
