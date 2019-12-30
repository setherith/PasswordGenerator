package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import common.Information;
import core.Password;

public class Gui extends JFrame {

	private static final long serialVersionUID = -8976021562072214389L;
	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField txtLength;
	
	private JCheckBox chkSpecial;
	private JCheckBox chkNumber;
	private JCheckBox chkUpper;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gui() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) { }
		
		setResizable(false);
		setTitle("Password Generator " + Information.Version());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(10, 5, 313, 20);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setEditable(false);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(10, 164, 313, 23);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = GeneratePassword();
				txtPassword.setText(result);
			}
		});
		contentPane.add(btnGenerate);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 313, 117);
		contentPane.add(tabbedPane);
		
		JPanel pnlSettings = new JPanel();
		tabbedPane.addTab("Settings", null, pnlSettings, null);
		pnlSettings.setLayout(null);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setBounds(10, 11, 58, 14);
		pnlSettings.add(lblLength);
		
		txtLength = new JTextField();
		txtLength.setText("6");
		txtLength.setBounds(78, 8, 30, 20);
		pnlSettings.add(txtLength);
		txtLength.setColumns(3);
		
		chkSpecial = new JCheckBox("Special");
		chkSpecial.setBounds(144, 60, 97, 23);
		pnlSettings.add(chkSpecial);
		
		chkUpper = new JCheckBox("Uppercase");
		chkUpper.setBounds(144, 8, 97, 23);
		pnlSettings.add(chkUpper	);
		
		chkNumber = new JCheckBox("Number");
		chkNumber.setBounds(144, 34, 97, 23);
		pnlSettings.add(chkNumber);
		
		JPanel pnlAbout = new JPanel();
		tabbedPane.addTab("About", null, pnlAbout, null);
		pnlAbout.setLayout(null);
		
		JTextPane txtAbout = new JTextPane();
		txtAbout.setText(Information.About());
		txtAbout.setBounds(0, 0, 308, 89);
		pnlAbout.add(txtAbout);
		setLocationRelativeTo(null);
	}
	
	private String GeneratePassword() {
		boolean upper = chkUpper.isSelected();
		boolean number = chkNumber.isSelected();
		boolean special = chkSpecial.isSelected();
		int length = GetLength();
		return Password.Generate(length, upper, number, special);
	}
	
	private int GetLength() {
		String raw = txtLength.getText();
		try {
			int value = Integer.parseInt(raw);
			if (value > 0 && value < 256) return value;
		} catch (Exception e) {
			
		}
		
		// Not a valid option... Change to default...
		txtLength.setText("6");
		return 6;
	}
}
