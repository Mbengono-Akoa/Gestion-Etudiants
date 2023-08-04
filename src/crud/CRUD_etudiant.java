package crud;
/*Application de gestion des notes des étudiants
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ig.Connecteur;
import requetes.Requetes_liste;
import securite2.CRUD_mdp;

import java.sql.*;
import java.text.MessageFormat;

public class CRUD_etudiant extends JFrame  implements ActionListener{
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	JLabel lb0, lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9;
	JTextField jtf2,jtf3,jtf4,jtf5;
	JButton jb1,jb2,jb3,jb4,jb5,jb7,jb8,jb9,jb10,jb15,jb16,jb88;
    JRadioButton rb1,rb2;
    JComboBox jcb,jcb2;
	JTable tb;
	JScrollPane scrl;
	
	
	public CRUD_etudiant(){
		this.setTitle("CRUD ETUDIANT");
		this.setSize(1000,650);
		this.setResizable(false);
		this.setLocation(350,30);
	JPanel jp=new JPanel();
	jp.setLayout(null);
	jp.setBackground(Color.orange);
	add(jp);
	lb1=new JLabel("Interface graphique de Gestion");
	lb1.setFont(new Font("Arial",Font.BOLD,20));
	lb1.setForeground(Color.red);
	lb1.setBounds(70,10,400,30);
	jp.add(lb1);
	
	lb0=new JLabel("ETUDIANT");
	lb0.setFont(new Font("Arial",Font.BOLD,16));
	lb0.setForeground(Color.red);
	lb0.setBounds(150,50,100,30);
	jp.add(lb0);
	// matricule
	lb2=new JLabel("Matricule");
	lb2.setFont(new Font("Arial",Font.BOLD,15));
	lb2.setForeground(Color.blue);
	lb2.setBounds(50,90,100,30);
	jp.add(lb2);
	
	jtf2=new JTextField();
	jtf2.setBounds(130,90,150,30);
	jp.add(jtf2);
	
	
	// Nom
	lb3=new JLabel("Nom");
	lb3.setFont(new Font("Arial",Font.BOLD,15));
	lb3.setForeground(Color.blue);
	lb3.setBounds(80,130,100,30);
	jp.add(lb3);
	
	jtf3=new JTextField();
	jtf3.setBounds(130,130,150,30);
	jp.add(jtf3);
	// Prenom
		lb4=new JLabel("Prenom");
		lb4.setFont(new Font("Arial",Font.BOLD,15));
		lb4.setForeground(Color.blue);
		lb4.setBounds(60,170,100,30);
		jp.add(lb4);
		
		jtf4=new JTextField();
		jtf4.setBounds(130,170,150,30);
		jp.add(jtf4);
	// datNais
		lb5=new JLabel("DatNaissance");
		lb5.setFont(new Font("Arial",Font.BOLD,15));
		lb5.setForeground(Color.blue);
		lb5.setBounds(20,210,100,30);
		jp.add(lb5);
				
		jtf5=new JTextField("   JJ-MM-AAAA");
		jtf5.setBounds(130,210,150,30);
		jp.add(jtf5);
	//sexe
		lb6=new JLabel("Sexe");
		lb6.setFont(new Font("Arial",Font.BOLD,15));
		lb6.setForeground(Color.blue);
		lb6.setBounds(75,250,100,30);
		jp.add(lb6);
		
		rb1=new JRadioButton("MASC");
		rb1.setBounds(130,250,80,25);
	
		rb2=new JRadioButton("FEM");
		rb2.setBounds(210,250,70,25);
		jp.add(rb1);
		jp.add(rb2);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
	//filiere
		lb7=new JLabel("Filiere");
		lb7.setFont(new Font("Arial",Font.BOLD,15));
		lb7.setForeground(Color.blue);
		lb7.setBounds(65,290,100,30);
		jp.add(lb7);
		
		jcb=new JComboBox();
		jcb.addItem("GI");
		jcb.addItem("GM");
		jcb.addItem("GEL");
		jcb.addItem("GEN");
		jcb.setBounds(130,290,100,25);
		jp.add(jcb);
	//Niveau
		lb8=new JLabel("Niveau");
		lb8.setFont(new Font("Arial",Font.BOLD,15));
		lb8.setForeground(Color.blue);
		lb8.setBounds(65,330,100,30);
		jp.add(lb8);
		
		jcb2=new JComboBox();
		jcb2.addItem("1");
		jcb2.addItem("2");
		jcb2.addItem("3");
		jcb2.setBounds(130,330,100,25);
		jp.add(jcb2);
		//
		lb9=new JLabel("Aller à:");
		lb9.setFont(new Font("Arial",Font.BOLD,18));
		lb9.setForeground(Color.blue);
		lb9.setBounds(30,490,100,30);
		jp.add(lb9);
		
			
	//les boutons
	  //insertion
	jb1=new JButton("Insertion");
	jb1.setBounds(130,390,90,30);
	jb1.setForeground(Color.blue);
	jb1.setBackground(Color.green);
	jb1.addActionListener(this);
	jp.add(jb1);
	//supression
	jb2=new JButton("Suppression");
	jb2.setBounds(240,390,110,30);
	jb2.setForeground(Color.blue);
	jb2.setBackground(new Color(250,100,0));
	jb2.addActionListener(this);
	jp.add(jb2);
	//modification
	jb3=new JButton("Modification");
	jb3.setBounds(240,440,120,30);
	jb3.setForeground(Color.blue);
	jb3.setBackground(Color.orange);
	jb3.addActionListener(this);
	jp.add(jb3);
	//recherche
		jb4=new JButton("Recherche");
		jb4.setBounds(280,90,100,30);
		jb4.setForeground(Color.white);
		jb4.setBackground(Color.gray);
		jb4.addActionListener(this);
		jp.add(jb4);
	//actualiser
		jb5=new JButton("Actualiser");
		jb5.setBounds(100,440,120,30);
		jb5.setForeground(Color.blue);
		jb5.setBackground(Color.green);
		jb5.addActionListener(this);
		jp.add(jb5);
		
		//jbmatiere
				jb7=new JButton("Matiere");
				jb7.setBounds(100,490,80,30);
				jb7.setForeground(Color.blue);
				jb7.setBackground(Color.white);
				jb7.addActionListener(this);
				jp.add(jb7);
		//jb note
			jb8=new JButton("Note");
			jb8.setBounds(190,490,80,30);
			jb8.setForeground(Color.blue);
			jb8.setBackground(Color.white);
			jb8.addActionListener(this);
			jp.add(jb8);
			
		//Imprimer
			jb15=new JButton("Impr");
			jb15.setBounds(285,490,80,30);
			jb15.setForeground(Color.blue);
			jb15.setBackground(Color.white);
			jb15.addActionListener(this);
			jp.add(jb15);
			
			//
			//Export Excel
			jb16=new JButton("Export");
			jb16.setBounds(730,550,110,30);
			jb16.setForeground(Color.blue);
			jb16.setBackground(Color.white);
			jb16.addActionListener(this);
			jp.add(jb16);
			
			//
			
			jb88=new JButton("ImportExcel");
			jb88.setBounds(620,550,110,30);
			jb88.setForeground(Color.blue);
			jb88.setBackground(Color.white);
			jb88.addActionListener(this);
			jp.add(jb88);
			
			//jb requetes
			jb9=new JButton("Requetes");
			jb9.setBounds(100,540,110,30);
			jb9.setForeground(Color.blue);
			jb9.setBackground(Color.white);
			jb9.addActionListener(this);
			jp.add(jb9);
			//
			//jb10
			jb10=new JButton("Password");
			jb10.setBounds(220,540,110,30);
			jb10.setForeground(Color.blue);
			jb10.setBackground(Color.white);
			jb10.addActionListener(this);
			jp.add(jb10);
		
			
			//
			
			
		
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("Matricule");
		df.addColumn("Nom");
		df.addColumn("Prenom");
		df.addColumn("DatNaissance");
		df.addColumn("Sexe");
		df.addColumn("Filiere");
		df.addColumn("Niveau");
		tb.setModel(df);
		jp.add(scrl);
		
		String qry="select * from etudiant";
		try{
			st=cn.chrisConnect().createStatement();
			rst=st.executeQuery(qry);
			while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("matricule"),rst.getString("Nom"),rst.getString("Prenom"),rst.getString("datnaissance")
						,rst.getString("sexe"),rst.getString("filiere"),rst.getString("niveau")
						
				});
				
			}
		}
		catch(SQLException ex){
			
		}
			
	}
	private void init(){
		tb=new JTable();
		scrl=new JScrollPane();
		scrl.setViewportView(tb);
		scrl.setBounds(400,10,580,530);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CRUD_etudiant igr=new CRUD_etudiant();
		igr.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		DefaultTableModel List_excel = (DefaultTableModel) tb.getModel();
		
		if(e.getSource()==jb88) {
			DefaultTableModel model= (DefaultTableModel) tb.getModel();
	        File excelFile;
	        FileInputStream excelFIS = null;
	        BufferedInputStream excelBIS = null;
	        XSSFWorkbook excelImportToJTable = null;
	        String defaultCurrentDirectoryPath = "C:\\Exccell";
	        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
	        excelFileChooser.setDialogTitle("Select Excel File");
	        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
	        excelFileChooser.setFileFilter(fnef);
	        int excelChooser = excelFileChooser.showOpenDialog(null);
	        if (excelChooser == JFileChooser.APPROVE_OPTION) {
	            try {
	                excelFile = excelFileChooser.getSelectedFile();
	                excelFIS = new FileInputStream(excelFile);                  
	                excelBIS = new BufferedInputStream(excelFIS);                  
	                excelImportToJTable = new XSSFWorkbook(excelBIS);                 
	                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
	                           
	                for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
	                    XSSFRow excelRow = excelSheet.getRow(row);                    
	                    XSSFCell excelLineNum = excelRow.getCell(0);
	                    XSSFCell excelItemName = excelRow.getCell(1);
	                    XSSFCell excelDescription = excelRow.getCell(2);
	                    XSSFCell excelServiceDuration = excelRow.getCell(3);
	                    XSSFCell excelQuantity = excelRow.getCell(4);
	                   model.addRow(new Object[]{excelLineNum, excelItemName, excelDescription, excelServiceDuration,excelQuantity});
	                }
	                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
	            } catch (IOException iOException) {
	                JOptionPane.showMessageDialog(null, iOException.getMessage());
	            } finally {
	                try {
	                    if (excelFIS != null) {
	                        excelFIS.close();
	                    }
	                    if (excelBIS != null) {
	                        excelBIS.close();
	                    }
	                    if (excelImportToJTable != null) {
	                        excelImportToJTable.close();
	                    }
	                } catch (IOException iOException) {
	                    JOptionPane.showMessageDialog(null, iOException.getMessage());
	                }
	            }
	        }
	    } 

//			FileInputStream excelFIS = null;
//			BufferedInputStream excelBIS = null;
//			XSSFWorkbook excelImportWorkBook = null;
//			
//			
//			String currentDirectoryPath = "C:\\Exccell";
//			JFileChooser excelFileChooserImport = new JFileChooser(currentDirectoryPath);
//			int excelChooser = excelFileChooserImport.showOpenDialog(null);
//			
//			if(excelChooser == JFileChooser.APPROVE_OPTION) {
//				
//				try {
//					File excelFile = excelFileChooserImport.getSelectedFile();
//					excelFIS = new FileInputStream(excelFile);
//					excelBIS = new BufferedInputStream(excelFIS);
//					
//					excelImportWorkBook = new XSSFWorkbook(excelBIS);
//					XSSFSheet excelSheet = excelImportWorkBook.getSheetAt(0);
//					
//					for(int i=0; i<excelSheet.getLastRowNum(); i++ ) {
//						XSSFRow excelRow = excelSheet.getRow(i);
//					
//						XSSFCell cell1 = excelRow.getCell(0);
//						
//						System.out.println(cell1);
//						
//						List_excel.addRow(new Object[] {cell1});
//						
//					}
//					
//				} catch(FileNotFoundException ex) {
//					ex.printStackTrace();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//				
//			}
//			
		
		
		
		
		
		//ajout
		if(e.getSource()==jb1){
			String a,b,c,d,f,g,h;
		a=jtf2.getText();b=jtf3.getText();c=jtf4.getText();d=jtf5.getText();
		if(rb1.isSelected()) f=rb1.getText(); else f=rb2.getText();
		g=jcb.getSelectedItem().toString();
		h=jcb2.getSelectedItem().toString();
			
		String query="insert into etudiant values('"+a+"','"+b+"','"+c+"','"+d+"','"+f+"','"+g+"','"+h+"')";
		try{
			st=cn.chrisConnect().createStatement();
			if(JOptionPane.showConfirmDialog(this,"Voulez vous ajoutez?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				st.executeUpdate(query);
				JOptionPane.showMessageDialog(this,"Insertion reussie!");
			}
			
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(this,"Echec insertion!",null,JOptionPane.ERROR_MESSAGE);
			
		}
			
		}//
		//suppression
				if(e.getSource()==jb2){
					String a;
					a=jtf2.getText();
				String query="delete from etudiant where matricule='"+a+"'";
				try{
					st=cn.chrisConnect().createStatement();
					if(JOptionPane.showConfirmDialog(this,"Voulez vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(this,"Suppression reussie!");
					}
					
					
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(this,"Echec suppression!",null,JOptionPane.ERROR_MESSAGE);
					
				}
					
				}//
				
				//Impression
				if(e.getSource()==jb15) {
					MessageFormat header = new MessageFormat("Liste des Candidats");
					MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					
					try {
						tb.print(JTable.PrintMode.NORMAL, header, footer);
					} catch(java.awt.print.PrinterException em) {
						System.err.format("Erreur d'impression", em.getMessage());
					}
				}
//				// Export
//				
  				if(e.getSource()==jb16) {
  					
  					try {
  						FileOutputStream fWriter = new FileOutputStream("Students.csv");
  						
  						PrintWriter printer = new PrintWriter(fWriter);
  						
  						printer.println("sep=,");
  						for(int i=0; i<tb.getRowCount(); i++) {
  							for(int j=0; j<tb.getColumnCount(); j++) {
  								printer.print(tb.getValueAt(i, j).toString() + ",");
  							}
  							printer.println();
  						}
  						printer.close();
  						
  						JOptionPane.showMessageDialog(null, "Openning to excel...");
  						Desktop.getDesktop().open(new java.io.File("Students.csv"));
  					} catch(Exception e1) {
  						JOptionPane.showMessageDialog(null, "error occured" +e1);
  					}
  				}
//					
//					try {
//						Workbook wb = new XSSFWorkbook();
//						Sheet sheet = wb.createSheet("Etudiants");
//						Row rowCol = sheet.createRow(0);
//						
//						
//						
//						for(int i=0; i<tb.getColumnCount(); i++){
//							Cell cell = rowCol.createCell(i);
//							cell.setCellValue(tb.getColumnName(i));
//						}
//						for(int j=0; j<tb.getRowCount(); j++) {
//							Row row = sheet.createRow(j+1);
//							for(int k=0; k<tb.getColumnCount(); k++) {
//								Cell cell = row.createCell(k);
//								if(tb.getValueAt(j,k) != null ) {
//									cell.setCellValue(tb.getValueAt(j, k).toString());
//								}
//							}
//						}
//						
//						FileOutputStream out = new FileOutputStream(new File("example.xlsx"));
//						wb.write(out);
//						wb.close();
//						out.close();
//						System.out.println(out);
//						
//					}
//						catch(FileNotFoundException e1) {
//						System.out.println(e1);	
//					} catch(IOException io) {
//						System.out.println(io);	
//					}
//				}
//				
				//
				//modification
				if(e.getSource()==jb3){
					String a,b,c,d,f,g,h;
					a=jtf2.getText();b=jtf3.getText();c=jtf4.getText();d=jtf5.getText();
					if(rb1.isSelected()) f=rb1.getText(); else f=rb2.getText();
					g=jcb.getSelectedItem().toString();
					h=jcb2.getSelectedItem().toString();
					
				String query="update etudiant set nom='"+b+"', prenom='"+c+"', datnaissance='"+d+"', sexe='"+f+"', filiere='"+g+"', niveau='"+h+"' where matricule='"+a+"' " ;
					
				try{
					st=cn.chrisConnect().createStatement();
					if(JOptionPane.showConfirmDialog(this,"Voulez vous modifier?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(this,"modification reussie!");
					}
					
					
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(this,"Echec modification!",null,JOptionPane.ERROR_MESSAGE);
					
				}
					
				}//
				//recherche
				if(e.getSource()==jb4){
					String a;
					a=jtf2.getText();
				String query="select * from etudiant where matricule='"+a+"'";
				try{
					st=cn.chrisConnect().createStatement();
					rst=st.executeQuery(query);
					if(rst.next()){
						jtf2.setText(rst.getString("matricule"));
						jtf3.setText(rst.getString("nom"));
						jtf4.setText(rst.getString("prenom"));
						jtf5.setText(rst.getString("datnaissance"));
						//affichage dans le bouton radio
						if(rst.getString("sexe").equals("MASC"))
							rb1.setSelected(true);
						else
							rb2.setSelected(true);
						//affichage dans les combos
						jcb.setSelectedItem(rst.getString("filiere"));
						jcb2.setSelectedItem(rst.getString("niveau"));	
					}
					else
						JOptionPane.showMessageDialog(this, "Introuvable!",null,JOptionPane.ERROR_MESSAGE);
					
					
				}
				catch(SQLException ex){
				
					
				}
					
				}//

				
		if(e.getSource()==jb5){
			this.dispose();
			CRUD_etudiant cr=new CRUD_etudiant();
			cr.setVisible(true);
			
		}
		
		
		if(e.getSource()==jb7){
			this.dispose();
			CRUD_matiere mat=new CRUD_matiere();
			mat.setVisible(true);
			
		}
		if(e.getSource()==jb8){
			this.dispose();
			CRUD_note nt=new CRUD_note();
			nt.setVisible(true);
			
		}
		if(e.getSource()==jb9){
			this.dispose();
			 Requetes_liste ls=new  Requetes_liste();
			ls.setVisible(true);
			
		}
		
		//
		if(e.getSource()==jb10){
		this.dispose();
		 CRUD_mdp ls=new  CRUD_mdp();
		ls.setVisible(true);
		
	}
	}

}
