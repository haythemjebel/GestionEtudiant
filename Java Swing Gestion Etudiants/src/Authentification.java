import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JPasswordField;





import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame {

	private JFrame frame;
	private JTextField usernamefeild;
	private JPasswordField passwordfield;
	private JPanel jpane ;
	
	Connection cnx = null ; 
	PreparedStatement prepared = null ; 
	Statement stmt = null; 
	ResultSet resultat = null ; 
	
	void fermer()
	{
		frame.dispose();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Authentification frame = new Authentification();
					frame.frame.setVisible(true);
					frame.frame.setLocationRelativeTo(null);
					frame.frame.setTitle("Gestion des Etudiants");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 500);
		frame.getContentPane().setLayout(null);
		cnx = ConnexionMysql.ConnexionDB();

		usernamefeild = new JTextField();
		usernamefeild.setBounds(399, 178, 200, 26);
		frame.getContentPane().add(usernamefeild);
		usernamefeild.setColumns(10);

		JLabel lblUserName = new JLabel("Username :");
		lblUserName.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		lblUserName.setBounds(326, 184, 74, 14);
		frame.getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		lblPassword.setBounds(326, 213, 74, 14);
		frame.getContentPane().add(lblPassword);

		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
				fermer();
			}
		});
		btnNewButton.setBounds(399, 243, 200, 31);
		frame.getContentPane().add(btnNewButton);

		passwordfield = new JPasswordField();
		passwordfield.setBounds(399, 206, 200, 26);
		frame.getContentPane().add(passwordfield);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\1513688583.png"));
		lblNewLabel.setBounds(62, 70, 926, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de pass oubli\u00E9 ! ");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndicationPass obj = new IndicationPass();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_3.setBounds(511, 230, 94, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\1.png"));
		lblNewLabel_1.setBounds(32, 0, 926, 72);
		frame.getContentPane().add(lblNewLabel_1);


		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\backgound.png"));
		lblNewLabel_2.setBounds(0, 0, 988, 466);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
