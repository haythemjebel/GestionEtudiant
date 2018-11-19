import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

import java.awt.SystemColor;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;


public class GestionUsers extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String userold = null ;
	Connection cnx = null ; 
	PreparedStatement prepared = null ; 
	ResultSet resultat = null ; 
	private JTable table;

	void fermer()
	{
		dispose();
	}
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUsers frame = new GestionUsers();
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
	public GestionUsers() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.ConnexionDB();
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				"C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\1.png"));
		lblNewLabel_1.setBounds(0, 0, 988, 72);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\1513688583.png"));
		lblNewLabel.setBounds(70, 50, 980, 20);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(133, 234, 141, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			/*	String sql = " select password from utilisateurs where username = ? ";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					resultat = prepared.executeQuery();
					
					if(resultat.next())
					{
						String password = resultat.getString("password");
						textField_1.setText(password);
					}

					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  */
				
			}
		});
		textField.setBounds(133, 203, 141, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(59, 209, 74, 14);
		contentPane.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblPassword = new JLabel("Password  : ");
		lblPassword.setBounds(59, 240, 74, 14);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\Accept_icon.png"));
		btnNewButton.setBounds(94, 286, 64, 59);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql = " insert into utilisateurs (username , password) values ( ? , ? ) ";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					
					if(!textField.getText().equals("") && !textField_1.getText().equals("") )
					{
						
				    	prepared.execute();
						JOptionPane.showMessageDialog(null, "User Ajoutée .");
						
						UpdateTable();
						
						textField.setText("");
						textField_1.setText("");
					}else
					{
						JOptionPane.showMessageDialog(null, "Remplissez Les Champs Vides !!");
					}


					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
		JButton btnSupprimer = new JButton("");
		btnSupprimer.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\-----.png"));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql =" delete from utilisateurs where username = ? and password = ? ";
				
				try {
					
					if(!textField.getText().equals("") && !textField_1.getText().equals("")){
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textField.getText().toString());
						prepared.setString(2, textField_1.getText().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "User Supprimé");
						textField.setText("");
						textField_1.setText("");
						
						UpdateTable();
						
						
					}else
					{
						JOptionPane.showMessageDialog(null , " Choisissez un compte ");
					}

					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(168, 286, 64, 59);
		contentPane.add(btnSupprimer);
		
		JButton btnModifier = new JButton("");
		btnModifier.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\edit.png"));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String sql =" update utilisateurs set username = ? , password = ? where username = '"+userold+"' ";
				
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					
					if(!textField.getText().equals("") && !textField_1.getText().equals("") )
					{
						
					prepared.execute();
			        JOptionPane.showMessageDialog(null, "User Updated :D");
			        UpdateTable();
			        
			        textField.setText("");
					textField_1.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Remplissez Les Champs Vides !!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnModifier.setBounds(246, 286, 64, 59);
		contentPane.add(btnModifier);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(452, 139, 501, 316);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setForeground(SystemColor.activeCaption);
		table.setFont(new Font("Segoe UI Semilight", Font.ITALIC, 16));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
				userold  = table.getModel().getValueAt(ligne, 0).toString(); 
				String password = table.getModel().getValueAt(ligne, 1).toString();

				
				textField.setText(userold);
				textField_1.setText(password);
			}
		});
				scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\refrech.png"));
		lblNewLabel_3.setBounds(400, 72, 2, 394);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				UpdateTable();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\refrech.png"));
		lblNewLabel_4.setBounds(918, 107, 35, 29);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Table des utilisateurs: ");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_5.setBounds(452, 122, 148, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
				
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\back.png"));
		lblNewLabel_6.setBounds(10, 77, 47, 45);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\b64.jpg"));
		lblNewLabel_2.setBounds(0, 74, 988, 392);
		contentPane.add(lblNewLabel_2);
	}
	
	public void UpdateTable()
	{
		String sql = " Select username , password from utilisateurs ";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
