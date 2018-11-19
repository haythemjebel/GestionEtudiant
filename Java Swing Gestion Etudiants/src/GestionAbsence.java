
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.toedter.calendar.JCalendar;

import java.awt.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import com.toedter.calendar.JDateChooser;




public class GestionAbsence extends JFrame {

	private JPanel contentPane;
	private JCalendar calendrier ; 
	
	Connection cnx = null ; 
	PreparedStatement prepared = null ; 
	ResultSet resultat = null ; 
	private JTable table;
	private JComboBox NomEtudBox ;

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
					GestionAbsence frame = new GestionAbsence();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Gestion des Etudiants");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GestionAbsence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.ConnexionDB();
		
		JCalendar j = new JCalendar();
		
		
		JComboBox rcomboBox = new JComboBox();
		rcomboBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez", "Malade", "Retard"}));
		rcomboBox.setBounds(155, 242, 280, 29);
		contentPane.add(rcomboBox);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				"C:\\Users\\PROXIMEDIA\\Desktop\\img\\1.png"));
		lblNewLabel_1.setBounds(0, 0, 988, 72);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\1513688583.png"));
		lblNewLabel.setBounds(70, 66, 988, 14);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/mm/dd");
		dateChooser.setBounds(155, 204, 280, 30);
		contentPane.add(dateChooser);
		
		JLabel adresse = new JLabel("Nom Etudiant : ");
		adresse.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		adresse.setBounds(46, 171, 107, 22);
		contentPane.add(adresse);
		
		JLabel Datenaissance = new JLabel("Date Absence : ");
		Datenaissance.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		Datenaissance.setBounds(46, 209, 107, 22);
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
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\back.png"));
		lblNewLabel_6.setBounds(10, 77, 47, 45);
		contentPane.add(lblNewLabel_6);
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				String nometud = NomEtudBox.getSelectedItem().toString();
				String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String raison = rcomboBox.getSelectedItem().toString();
				
				String sql =" Insert into absence(nometud,DateAbsence,raison) values (?,?,?) ";
				
				try {
					
					if(rcomboBox.getSelectedItem().equals("Sélectionnez") || Date.equals("") || NomEtudBox.getSelectedItem().equals("Sélectionnez"))
					{
						JOptionPane.showMessageDialog(null, " Completez les informations ");

					}else
					{
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, nometud);
						prepared.setString(2, Date);
						prepared.setString(3, raison);
						prepared.execute();
						

						rcomboBox.setSelectedItem("Sélectionnez");
						NomEtudBox.setSelectedItem("Sélectionnez");
						
						
						JOptionPane.showMessageDialog(null, " Absence Ajoutée :D ");
						
					}

					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\Accept_icon.png"));
		btnNewButton.setBounds(134, 388, 64, 67);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
                 int ligne = table.getSelectedRow();
                
                 String nometud = NomEtudBox.getSelectedItem().toString();
                 String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
                 String raison = rcomboBox.getSelectedItem().toString();

                if(ligne == -1 )
                {
                	JOptionPane.showMessageDialog(null, "Sélectionnz une Absence " );
                }
                else
                {
                	String id = table.getModel().getValueAt(ligne , 0).toString();
    				
    				String sql = " Update absence set nometud = ? , DateAbsence = ? , raison = ? where nometud = '"+id+"'" ;
    				
    				try {
    					
    					prepared = cnx.prepareStatement(sql);
    					
    					prepared.setString(1, nometud);
    					prepared.setString(2, Date);
    					prepared.setString(3, raison);
    					prepared.execute();
    					JOptionPane.showMessageDialog(null, "Absence Modifié :D");
    					UpdateTable();
    						
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
			}
			}});
		button.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\edit.png"));
		button.setBounds(282, 388, 64, 67);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                int ligne = table.getSelectedRow();
                
                if(ligne == -1)
                {
                	JOptionPane.showMessageDialog(null, "Sélectionnz une absence " );
                }
                else
                {
                	String id = table.getModel().getValueAt(ligne , 0).toString();
    				
    				String sql = " delete from absence where nometud = '"+id+"'";
    				
    				try {
    					
    					prepared = cnx.prepareStatement(sql);
    					prepared.execute();
    					
    					
    					JOptionPane.showMessageDialog(null, "Absence Supprimé :D " );
    					UpdateTable();
    					
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
                }
				
				
			}
		});
		button_1.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\-----.png"));
		button_1.setBounds(208, 388, 64, 67);
		contentPane.add(button_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\PROXIMEDIA\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\img\\\\\\\\\\\\\\\\trait.png"));
		lblNewLabel_3.setBounds(7, 363, 485, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\PROXIMEDIA\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\img\\\\\\\\\\\\\\\\trait.png"));
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
				
				String sql = " Select * from absence where nometud = '"+id+"'";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					if(resultat.next())
					{
						NomEtudBox.setSelectedItem(resultat.getString("nometud"));;
						String datee = resultat.getString("DateAbsence");
						Date date = new SimpleDateFormat("yyyy-mm-dd").parse(datee);
						dateChooser.setDate(date);
						rcomboBox.setSelectedItem(resultat.getString("raison"));
					}
					
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("Table des Absences :");
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
		lblNewLabel_4.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\refrech.png"));
		lblNewLabel_4.setBounds(943, 83, 35, 29);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblRaison = new JLabel("Raison : ");
		lblRaison.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		lblRaison.setBounds(89, 247, 64, 22);
		contentPane.add(lblRaison);
		
		NomEtudBox = new JComboBox();
		NomEtudBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez"}));
		NomEtudBox.setBounds(155, 166, 280, 29);
		contentPane.add(NomEtudBox);
		remplivCombobox();
		



		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\b26.jpg"));
		lblNewLabel_2.setBounds(0, 74, 988, 392);
		contentPane.add(lblNewLabel_2);
	}
	
	public void UpdateTable()
	{
		String sql = " Select  nometud , DATE_FORMAT(DateAbsence, '%m-%d-%y') , raison from absence ";
           try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void remplivCombobox()
	{
		
		String sql =" Select * from etudiants";
		
		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			
			while(resultat.next())
			{
				String nom = resultat.getString("nom").toString();
				String prenom = resultat.getString("prenom").toString();
				NomEtudBox.addItem(prenom + " " + nom);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}