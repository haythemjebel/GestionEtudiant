
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;




public class GestionFilieres extends JFrame {

	private JPanel contentPane;
	private JTextField nomfilierefeild;
	
	Connection cnx = null ; 
	PreparedStatement prepared = null ; 
	ResultSet resultat = null ; 
	private JTable table;
	private JComboBox<String> typecombobox ;

	/**
	 * Launch the application.
	 */
	
	void fermer()
	{
		dispose();
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFilieres frame = new GestionFilieres();
					frame.setVisible(true);
					frame.setTitle("Gestion des Etudiants");
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionFilieres() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.ConnexionDB();
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				"C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\1.png"));
		lblNewLabel_1.setBounds(0, 0, 988, 72);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\1513688583.png"));
		lblNewLabel.setBounds(70, 66, 988, 20);
		contentPane.add(lblNewLabel);
		
		nomfilierefeild = new JTextField();
		nomfilierefeild.setColumns(10);
		nomfilierefeild.setBounds(158, 211, 280, 30);
		contentPane.add(nomfilierefeild);
		

		
		JLabel adresse = new JLabel("Nom Fili\u00E8re : ");
		adresse.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		adresse.setBounds(55, 216, 93, 22);
		contentPane.add(adresse);
		
		JLabel Datenaissance = new JLabel("Type : ");
		Datenaissance.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		Datenaissance.setBounds(96, 249, 47, 22);
		contentPane.add(Datenaissance);
		
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
		lblNewLabel_6.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\back.png"));
		lblNewLabel_6.setBounds(10, 77, 47, 45);
		contentPane.add(lblNewLabel_6);
		typecombobox = new JComboBox();
		typecombobox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez", "Licence Professionelle ", "Licence Fondamentale "}));
		typecombobox.setBounds(158, 247, 280, 30);
		contentPane.add(typecombobox);
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				String nomfil = nomfilierefeild.getText().toString();
				String type = typecombobox.getSelectedItem().toString();

				
				String sql =" Insert into filiere ( nom , type) values (?, ?) ";
				
				try {
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, nomfil);
						prepared.setString(2, type);
						prepared.execute();
						
						nomfilierefeild.setText("");
						typecombobox.setSelectedItem("Sélectionnez");
						
						JOptionPane.showMessageDialog(null, " Filiere Ajoutée :D ");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\Accept_icon.png"));
		btnNewButton.setBounds(132, 349, 64, 67);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                int ligne = table.getSelectedRow();
                
                if(ligne == -1 )
                {
                	JOptionPane.showMessageDialog(null, "Sélectionnz une filiere " );
                }
                else
                {
                	String id = table.getModel().getValueAt(ligne , 0).toString();
    				
    				String sql = " Update filiere set nom = ? , type = ? where nom = '"+id+"'" ;
    				
    				try {
    					
    					prepared = cnx.prepareStatement(sql);
    					
    					prepared.setString(1, nomfilierefeild.getText().toString());
    					prepared.setString(2, typecombobox.getSelectedItem().toString());
    					prepared.execute();
    					JOptionPane.showMessageDialog(null, "Filiere Modifié :D");
    					UpdateTable();
    						
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
				
				
			}
		});
		button.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\edit.png"));
		button.setBounds(282, 349, 64, 67);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
                int ligne = table.getSelectedRow();
                
                if(ligne == -1)
                {
                	JOptionPane.showMessageDialog(null, "Sélectionnz une filiere " );
                }
                else
                {
                	
                	
                	
                	int a = JOptionPane.showConfirmDialog(null , " Voulez-vous Vraiment Suppimer cet élément ? " , " Supprimer Filiere" , JOptionPane.YES_NO_OPTION);
                	
                	String id = table.getModel().getValueAt(ligne , 0).toString();
    				
    				String sql = " delete from filiere where nom = '"+id+"'";
    				
    				try {
    					
    					prepared = cnx.prepareStatement(sql);
    					
    					if( a==0)
    					{
    						prepared.execute();
    						JOptionPane.showMessageDialog(null, " Filiere Supprimé :D ");
    					}
    					

    					UpdateTable();
    					
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
                }
				
				
			}
		});
		button_1.setIcon(new ImageIcon("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\PROXIMEDIA\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\img\\\\\\\\\\\\\\\\-----.png"));
		button_1.setBounds(208, 349, 64, 67);
		contentPane.add(button_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\trait.png"));
		lblNewLabel_3.setBounds(7, 313, 485, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\trait.png"));
		lblNewLabel_13.setBounds(502, 72, 2, 394);
		contentPane.add(lblNewLabel_13);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(515, 116, 463, 350);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(ligne , 0).toString();
				
				String sql = " Select * from filiere where nom = '"+id+"'";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					if(resultat.next())
					{
						nomfilierefeild.setText(resultat.getString("nom"));
						typecombobox.setSelectedItem(resultat.getString("type"));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("Table des Fili\u00E8res: ");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(514, 91, 148, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
                 UpdateTable();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\refrech.png"));
		lblNewLabel_4.setBounds(943, 83, 35, 29);
		contentPane.add(lblNewLabel_4);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\bfil.jpg"));
		lblNewLabel_2.setBounds(0, 74, 500, 500);
		contentPane.add(lblNewLabel_2);
	}
	
	public void UpdateTable()
	{
		String sql = " Select * from filiere ";
		
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
