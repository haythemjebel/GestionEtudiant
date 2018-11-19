import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MenuAdministrateur extends JFrame {

	private JPanel contentPane;
	
	void fermer()
	{
		dispose();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrateur frame = new MenuAdministrateur();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Gestion des Etudiants");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuAdministrateur() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\1.png"));
		lblNewLabel_1.setBounds(36, 0, 926, 63);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\1513688583.png"));
		lblNewLabel.setBounds(78, 34, 892, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GestionEtudiants obj = new GestionEtudiants();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\etu.gif"));
		btnNewButton.setBounds(409, 83, 170, 156);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUsers obj = new GestionUsers();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\icon_res.png"));
		button.setBounds(229, 83, 170, 156);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GestionAbsence obj = new GestionAbsence();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
				}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\Agenda-Picto.png"));
		button_1.setBounds(229, 277, 170, 156);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\umat.png"));
		button_2.setBounds(409, 277, 170, 156);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionFilieres obj = new GestionFilieres();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_3.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\Staffs2.gif"));
		button_3.setBounds(589, 83, 170, 156);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\gest2.jpg"));
		button_4.setBounds(589, 277, 180, 156);
		contentPane.add(button_4);
		
		JLabel label = new JLabel("");
		label.setBounds(78, 239, 46, 14);
		contentPane.add(label);
		
		JLabel lblGestionDesAbscenses = new JLabel("Gestion des Etudiants");
		lblGestionDesAbscenses.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGestionDesAbscenses.setBounds(436, 250, 123, 14);
		contentPane.add(lblGestionDesAbscenses);
		
		JLabel lblGestionDesAbscences = new JLabel("Gestion des Absences");
		lblGestionDesAbscences.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGestionDesAbscences.setBounds(260, 442, 139, 14);
		contentPane.add(lblGestionDesAbscences);
		
		JLabel lblGestionDesMatires = new JLabel("Gestion des Mati\u00E8res");
		lblGestionDesMatires.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGestionDesMatires.setBounds(440, 442, 139, 14);
		contentPane.add(lblGestionDesMatires);
		
		JLabel lblGestionDesNotes = new JLabel("Gestion des Notes");
		lblGestionDesNotes.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGestionDesNotes.setBounds(635, 442, 106, 14);
		contentPane.add(lblGestionDesNotes);
		
		JLabel lblGestionDesFilieres = new JLabel("Gestion des Fili\u00E8res");
		lblGestionDesFilieres.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGestionDesFilieres.setBounds(618, 250, 123, 14);
		contentPane.add(lblGestionDesFilieres);
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion des Utilisateurs");
		lblGestionDesUtilisateurs.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGestionDesUtilisateurs.setBounds(249, 250, 139, 14);
		contentPane.add(lblGestionDesUtilisateurs);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\b25.jpg"));
		lblNewLabel_2.setBounds(0, 0, 988, 466);
		contentPane.add(lblNewLabel_2);
		
	}
}
