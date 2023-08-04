package requetes;
/*Application de gestion des notes des étudiants
 * réalisée en 2016 à Ndjaména au Tchad par 
 * Targoto Christian
 * Contact: +23560316682  / ct@chrislink.net*/
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ig.Connecteur;

import java.sql.*;
import java.text.MessageFormat;

public class List_etd_nv_fl extends JFrame implements ActionListener{
	JLabel lab1,lab2;
	JComboBox jcb1,jcb2;
	JButton jb1,jb2,jb3,jb15;
	JTable tb;
	JScrollPane scrl;
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	public List_etd_nv_fl(){
		this.setTitle("ETUD");
		this.setSize(1000,600);
		this.setLocation(350,30);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.orange);
		add(pn);
		//
		lab1=new JLabel("LISTE DES ETUDIANTS D'UN NIVEAU D'UNE FILIERE");
		lab1.setBounds(150,10,650,30);
		lab1.setFont(new Font("Arial",Font.BOLD,25));
		lab1.setBorder(BorderFactory.createLineBorder(Color.black));
		pn.add(lab1);
		//label1
		lab1=new JLabel("FILIERE :");
		lab1.setBounds(60,80,80,30);
		lab1.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lab1);
		//combo1
		jcb1=new JComboBox();
		jcb1.addItem("GI");
		jcb1.addItem("GEL");
		jcb1.addItem("GM");
		jcb1.addItem("GEN");
		jcb1.setBounds(140,80,80,30);
		pn.add(jcb1);
		
		//label2
				lab2=new JLabel("NIVEAU :");
				lab2.setBounds(360,80,80,30);
				lab2.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lab2);
				//combo2
				jcb2=new JComboBox();
				jcb2.addItem("1");
				jcb2.addItem("2");
				jcb2.addItem("3");
				jcb2.setBounds(440,80,80,30);
				pn.add(jcb2);
				//
				jb1=new JButton("Recherche");
				jb1.setBounds(640,80,100,30);
				jb1.setBackground(Color.white);
				jb1.setForeground(Color.blue);
				jb1.addActionListener(this);
				pn.add(jb1);
				
				//
				
				// Imprimer
				jb15=new JButton("Impr");
				jb15.setBounds(760,80,100,30);
				jb15.setForeground(Color.blue);
				jb15.setBackground(Color.white);
				jb15.addActionListener(this);
				pn.add(jb15);
				
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("Matricule");
				df.addColumn("Nom");
				df.addColumn("Prenom");
				df.addColumn("DatNaissance");
				df.addColumn("Sexe");
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
		
	List_etd_nv_fl ls=new List_etd_nv_fl();
		ls.setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	
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
	
	//
	if(e.getSource()==jb1){
		String a,b;
		a=jcb1.getSelectedItem().toString();
		b=jcb2.getSelectedItem().toString();
		DefaultTableModel df=new DefaultTableModel();
		df.addColumn("Matricule");
		df.addColumn("Nom");
		df.addColumn("Prenom");
		df.addColumn("DatNaissance");
		df.addColumn("Sexe");
		//df.addColumn("Filiere");
		//df.addColumn("Niveau");
		tb.setModel(df);
		String qry="select * from etudiant where filiere='"+a+"'and niveau='"+b+"'";
	
				
		try{
			st=cn.chrisConnect().createStatement();
			rst=st.executeQuery(qry);
			while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("matricule"),rst.getString("Nom"),rst.getString("Prenom"),rst.getString("datnaissance")
						,rst.getString("sexe")/*,rst.getString("filiere")*///,rst.getString("niveau")
						
				});
				
			}
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(this,"INTROUVABLE",null,JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
}


}
