
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.proteanit.sql.DbUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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

import java.awt.GridLayout;




public class GestionEtudiants extends JFrame {

	private JPanel contentPane;
	private JTextField prenomfeild;
	private JTextField nomfeild;
	private JTextField cinfeild;
	private JTextField numtelfeild;
	private JTextField adressefeild;
	private JTextField datenaissfeild;
	private String s ; 
	
	JComboBox filierecombox ;
	
	Connection cnx = null ; 
	PreparedStatement prepared = null ; 
	ResultSet resultat = null ; 
	private JTable table;

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
					GestionEtudiants frame = new GestionEtudiants();
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
	public GestionEtudiants() {
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
		
		prenomfeild = new JTextField();
		prenomfeild.setBounds(179, 83, 145, 30);
		contentPane.add(prenomfeild);
		prenomfeild.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\1513688583.png"));
		lblNewLabel.setBounds(70, 66, 988, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom : ");
		lblPrnom.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		lblPrnom.setBounds(110, 88, 64, 22);
		contentPane.add(lblPrnom);
		
		nomfeild = new JTextField();
		nomfeild.setColumns(10);
		nomfeild.setBounds(179, 124, 145, 30);
		contentPane.add(nomfeild);
		
		JLabel lblNom = new JLabel("Nom  : ");
		lblNom.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		lblNom.setBounds(126, 129, 48, 22);
		contentPane.add(lblNom);
		
		cinfeild = new JTextField();
		cinfeild.setColumns(10);
		cinfeild.setBounds(179, 165, 145, 30);
		contentPane.add(cinfeild);
		
		JLabel cin = new JLabel("CIN : ");
		cin.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		cin.setBounds(134, 170, 40, 22);
		contentPane.add(cin);
		cin.setOpaque(false);
		
		numtelfeild = new JTextField();
		numtelfeild.setColumns(10);
		numtelfeild.setBounds(179, 206, 145, 30);
		contentPane.add(numtelfeild);
		
		JLabel numtel = new JLabel("Num Tel : ");
		numtel.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		numtel.setBounds(106, 211, 68, 22);
		contentPane.add(numtel);
		
		adressefeild = new JTextField();
		adressefeild.setColumns(10);
		adressefeild.setBounds(179, 247, 285, 30);
		contentPane.add(adressefeild);
		JPanel panel = new JPanel();
		panel.setBounds(327, 83, 137, 122);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 1));
		
		JLabel labimage = new JLabel("");
		panel.add(labimage);
		JLabel adresse = new JLabel("Adresse : ");
		adresse.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		adresse.setBounds(110, 252, 64, 22);
		contentPane.add(adresse);
		
		datenaissfeild = new JTextField();
		datenaissfeild.setColumns(10);
		datenaissfeild.setBounds(179, 288, 285, 30);
		contentPane.add(datenaissfeild);
		
		JLabel Datenaissance = new JLabel("Date Naissance : ");
		Datenaissance.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		Datenaissance.setBounds(67, 288, 107, 22);
		contentPane.add(Datenaissance);
		
		JLabel Filiere = new JLabel("Fili\u00E8re : ");
		Filiere.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 15));
		Filiere.setBounds(122, 332, 54, 22);
		contentPane.add(Filiere);
		
		filierecombox = new JComboBox();
		filierecombox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		filierecombox.setBounds(179, 329, 285, 27);
		contentPane.add(filierecombox);

		remplivCombobox();
		
		
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String prenom = prenomfeild.getText().toString();
				String nom = nomfeild.getText().toString();
				String cin = cinfeild.getText().toString();
				String num = numtelfeild.getText().toString();
				String adresse = adressefeild.getText().toString();
				String datenaiss =datenaissfeild.getText().toString();
				
				
				String sql =" Insert into etudiants ( prenom , nom , cin , tel , datenaissance , adresse , filiere , image  ) values ( ? , ? , ? , ? , ? , ? , ? , ?) ";
				try {
					
					
					InputStream imgg = new FileInputStream(new File(s));
					
					if(!prenom.equals("") && !nom.equals("") && !cin.equals("") && !num.equals("") && !adresse.equals("") && !datenaiss.equals("") )
					{
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, prenom);
						prepared.setString(2, nom);
						prepared.setString(3, cin);
						prepared.setString(4, num);
						prepared.setString(5, datenaiss);
						prepared.setString(6, adresse);
						prepared.setString(7, filierecombox.getSelectedItem().toString());
						prepared.setBlob(8, imgg);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, " Etudiant Ajoutée  ");
						
						prenomfeild.setText("");
						nomfeild.setText("");
						cinfeild.setText("");
						numtelfeild.setText("");
						adressefeild.setText("");
						datenaissfeild.setText("");
						
						
						
					}else
					{
						JOptionPane.showMessageDialog(null, " Remplissez Les Champs ");
					}

					
					
				} catch (SQLException | FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
				

			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\Accept_icon.png"));
		btnNewButton.setBounds(110, 388, 64, 67);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                int ligne = table.getSelectedRow();
                
                if(ligne == -1 )
                {
                	JOptionPane.showMessageDialog(null, "Séléctionnez un étudiant !");
                }
                else
                {
                	String id = table.getModel().getValueAt(ligne , 0).toString();
    				
    				String sql = " Update etudiants set prenom = ? , nom = ? , cin = ?  , tel = ? , datenaissance = ? , adresse = ? , filiere = ? , image = ?  where id_etudiant = '"+id+"'" ;
    				
    				
    				
    				try {
    					
    					InputStream in = new FileInputStream(new File(s));
    					prepared = cnx.prepareStatement(sql);
    					prepared.setString(1, prenomfeild.getText().toString());
    					prepared.setString(2, nomfeild.getText().toString());
    					prepared.setString(3, cinfeild.getText().toString());
    					prepared.setString(4, numtelfeild.getText().toString());
    					prepared.setString(5, datenaissfeild.getText().toString());
    					prepared.setString(6, adressefeild.getText().toString());
    					prepared.setString(7 , filierecombox.getSelectedItem().toString());
    					prepared.setBlob(8, in);
    			
    					prepared.execute();
    					JOptionPane.showMessageDialog(null, "Etudiant Modifié :D");
    					UpdateTable();
    					
    					prenomfeild.setText("");
						nomfeild.setText("");
						cinfeild.setText("");
						numtelfeild.setText("");
						adressefeild.setText("");
						datenaissfeild.setText("");
    					
    										
    				} catch (SQLException | FileNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
				
				
			}
		});
		button.setIcon(new ImageIcon("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\img\\\\edit.png"));
		button.setBounds(260, 388, 64, 67);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                int ligne = table.getSelectedRow();
                
                if(ligne == -1)
                {
                	JOptionPane.showMessageDialog(null, " Sélectionnez un étudiant ! " );
                }
                else
                {
                	String id = table.getModel().getValueAt(ligne , 0).toString();
    				
    				String sql = " delete from etudiants where id_etudiant = '"+id+"'";
    				try {
    					prepared = cnx.prepareStatement(sql);
    					prepared.execute();
    					JOptionPane.showMessageDialog(null, "Eudiant Supprimé :D " );
    					UpdateTable();
    					
    					prenomfeild.setText("");
						nomfeild.setText("");
						cinfeild.setText("");
						numtelfeild.setText("");
						adressefeild.setText("");
						datenaissfeild.setText("");
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
				
				
			}
		});
		button_1.setIcon(new ImageIcon("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\PROXIMEDIA\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\img\\\\\\\\\\\\\\\\-----.png"));
		button_1.setBounds(186, 388, 64, 67);
		contentPane.add(button_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\PC-PRO\\Desktop\\logoss\\45444.png"));
		lblNewLabel_3.setBounds(-2, 365, 485, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon("D:\\GPM\\MenuButton\\star3.png"));
		lblNewLabel_13.setBounds(502, 72, 2, 394);
		contentPane.add(lblNewLabel_13);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(515, 116, 463, 339);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(ligne , 0).toString();
				
				String sql = " Select * from etudiants where id_etudiant = '"+id+"'";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					if(resultat.next())
					{
						prenomfeild.setText(resultat.getString("prenom"));
						nomfeild.setText(resultat.getString("nom"));
						cinfeild.setText(resultat.getString("cin"));
						numtelfeild.setText(resultat.getString("tel"));
						datenaissfeild.setText(resultat.getString("datenaissance"));
						adressefeild.setText(resultat.getString("adresse")); 
						filierecombox.setSelectedItem(resultat.getString("filiere"));
						
						byte[] img = resultat.getBytes("image");
						ImageIcon image = new ImageIcon(img);
						java.awt.Image im = image.getImage();
					    java.awt.Image myImg = im.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
					    ImageIcon imggg = new ImageIcon(myImg);
					    labimage.setIcon(imggg);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("Table des Etudiants : ");
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
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Document doc = new Document();
				String sql = " Select * from etudiants";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					PdfWriter.getInstance(doc , new FileOutputStream("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop\\\\Etudiant.pdf"));
					doc.open();
					
					Image img = Image.getInstance("C:\\Users\\PC-PRO\\Desktop\\4.png");	
					img.scaleAbsoluteWidth(600);
					img.scaleAbsoluteHeight(92);
					img.setAlignment(Image.ALIGN_CENTER);
					doc.add(img);
					
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph("Liste des Etudiants : "));
					doc.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(7);
					table.setWidthPercentage(100);
					
					PdfPCell cell ; 
					
                    ///////////////////////////////////////////////////////////////////
					
					cell = new PdfPCell (new Phrase ("Prenom",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell (new Phrase ("Nom",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell (new Phrase ("CIN",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell (new Phrase ("Num Tel",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell (new Phrase ("Adresse",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell (new Phrase ("Date Naissance",FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell (new Phrase ("Filiere",FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					///////////////////////////////////////////////////////////////////
					
					while (resultat.next())
					{
						
						cell = new PdfPCell (new Phrase (resultat.getString("prenom").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell (new Phrase (resultat.getString("nom").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell (new Phrase (resultat.getString("cin").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell (new Phrase (resultat.getString("tel").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell (new Phrase (resultat.getString("adresse").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell (new Phrase (resultat.getString("datenaissance").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell (new Phrase (resultat.getString("filiere").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}

					
					///////////////////////////////////////////////////////////////////
					
					
					
					doc.add(table);
					
					doc.close();
					Desktop.getDesktop().open(new File ("C:\\Users\\PROXIMEDIA\\Desktop\\Etudiant.pdf"));
						
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\PROXIMEDIA\\Desktop\\img\\print.png"));
		button_2.setBounds(334, 388, 64, 67);
		contentPane.add(button_2);
		

		
		JButton btnNewButton_1 = new JButton("Parcourir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\\\Users\\\\PROXIMEDIA\\\\Desktop"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE" , "jpg" , "png" , "gif");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File selectedfile = fileChooser.getSelectedFile();
					String path = selectedfile.getAbsolutePath();
					ImageIcon myImage = new ImageIcon(path);
					java.awt.Image img = myImage.getImage();
					java.awt.Image newImage = img.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon finalImg = new ImageIcon(newImage);
					labimage.setIcon(finalImg);
					s = path ; 
				}else
				{
					if(result == JFileChooser.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null , " T'as rien choisi ");
				}
				
				
			}
		});
		btnNewButton_1.setBounds(327, 206, 137, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\\\\\\\Users\\\\\\\\PROXIMEDIA\\\\\\\\Desktop\\\\\\\\img\\\\\\\\orag.jpg"));
		lblNewLabel_2.setBounds(0, 74, 988, 392);
		contentPane.add(lblNewLabel_2);
	}
	
	public void UpdateTable()
	{
		String sql = " Select * from etudiants ";
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
		
		String sql =" Select * from filiere";
		
		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			
			while(resultat.next())
			{
				String nom = resultat.getString("nom").toString();
				filierecombox.addItem(nom);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
