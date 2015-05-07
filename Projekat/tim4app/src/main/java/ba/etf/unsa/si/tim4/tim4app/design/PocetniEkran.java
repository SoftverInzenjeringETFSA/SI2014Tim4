package ba.etf.unsa.si.tim4.tim4app.design;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ba.etf.unsa.si.tim4.tim4app.daldao.TestDBClass;

public class PocetniEkran {

	private JFrame frmPoetniEkran;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetniEkran window = new PocetniEkran();
					window.frmPoetniEkran.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PocetniEkran() {
		initialize();
		TestDBClass tbc = new TestDBClass();
		tbc.printToConsoleFromDBRegular();
		System.out.println("Izlaz iz hibernate");
		tbc.printToConsoleFromDBHibernate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPoetniEkran = new JFrame();
		frmPoetniEkran.setTitle("Početni ekran");
		frmPoetniEkran.setBounds(100, 100, 450, 300);
		frmPoetniEkran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
