package requetes;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ig.Connecteur;

import java.sql.*;

public class List_excel extends JFrame implements ActionListener{
	
	
	JLabel lab1,lab2;
	JComboBox jcb1,jcb2;
	JButton jb1,jb2,jb3,jb88;
	JTable tb;
	JScrollPane scrl;
	Statement st;
	ResultSet rst;


	Connecteur cn=new Connecteur();
	public List_excel(){
		this.setTitle("Requetes");
		this.setSize(1000,600);
		this.setLocation(350,30);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.orange);
		add(pn);
		//
		lab1=new JLabel("LISTE DES MATIERES D'UN NIVEAU D'UNE FILIERE");
		lab1.setBounds(150,10,650,30);
		lab1.setFont(new Font("Arial",Font.BOLD,25));
		lab1.setBorder(BorderFactory.createLineBorder(Color.black));
		pn.add(lab1);
		
				//
		
		jb88=new JButton("ExportExcel");
		jb88.setBounds(200,490,150,30);
		jb88.setForeground(Color.blue);
		jb88.setBackground(Color.white);
		jb88.addActionListener(this);
		pn.add(jb88);

		
				//
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("Identifiant");
				df.addColumn("Appellation");
				//df.addColumn("Filiere");
				//df.addColumn("Niveau");
				tb.setModel(df);
				pn.add(scrl);
				

	}
	private void init(){
		tb=new JTable();
		scrl=new JScrollPane();
		scrl.setViewportView(tb);
		scrl.setBounds(10,150,960,400);
		
	}
public static void main(String[] args){
		
	List_excel ls=new List_excel();
		ls.setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
//	DefaultTableModel List_excel = (DefaultTableModel) tb.getModel();
	
	if(e.getSource()==jb88) {
				
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
//		FileInputStream excelFIS = null;
//		BufferedInputStream excelBIS = null;
//		XSSFWorkbook excelImportWorkBook = null;
//		
//		
//		String currentDirectoryPath = "C:\\Exccell";
//		JFileChooser excelFileChooserImport = new JFileChooser(currentDirectoryPath);
//		int excelChooser = excelFileChooserImport.showOpenDialog(null);
//		
//		if(excelChooser == JFileChooser.APPROVE_OPTION) {
//			
//			try {
//				File excelFile = excelFileChooserImport.getSelectedFile();
//				excelFIS = new FileInputStream(excelFile);
//				excelBIS = new BufferedInputStream(excelFIS);
//				
//				excelImportWorkBook = new XSSFWorkbook(excelBIS);
//				XSSFSheet excelSheet = excelImportWorkBook.getSheetAt(0);
//				
//				for(int i=0; i<excelSheet.getLastRowNum(); i++ ) {
//					XSSFRow excelRow = excelSheet.getRow(i);
//					
//					XSSFCell cell1 = excelRow.getCell(0);
//					
//					System.out.println(cell1);
//					
//					List_excel.addRow(new Object[] {cell1});
//					
//				}
//				
//			} catch(FileNotFoundException ex) {
//				ex.printStackTrace();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//			
//		}
//		
//	}
	
	
//	if(e.getSource()==jb1){
//		String a,b;
//		a=jcb1.getSelectedItem().toString();
//		b=jcb2.getSelectedItem().toString();
//		DefaultTableModel df=new DefaultTableModel();
//		df.addColumn("Identifiant");
//		df.addColumn("Appellation");
//		//df.addColumn("Filiere");
//		//df.addColumn("Niveau");
//		tb.setModel(df);
//		String qry="select * from matiere where filiere='"+a+"' and niveau='"+b+"' ";
//		try{
//			st=cn.chrisConnect().createStatement();
//			rst=st.executeQuery(qry);
//			while(rst.next()){
//				df.addRow(new Object[]{
//						rst.getString("idmat"),rst.getString("appelation")
//					/*,rst.getString("filiere")*///,rst.getString("niveau")
//						
//				});
//				
//			}
//			
//		}
//		catch(SQLException ex){
//			JOptionPane.showMessageDialog(this,"INTROUVABLE",null,JOptionPane.ERROR_MESSAGE);
//		}
//		
//	}
	
}


}
