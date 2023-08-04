package requetes;
/*Application de gestion des notes des étudiants
*/

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

public class List_moy_mat extends JFrame implements ActionListener{
	JLabel lab1,lab2;
	JComboBox jcb1,jcb2;
	JButton jb1,jb2,jb3,jb15;
	JTable tb;
	JScrollPane scrl;
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	public List_moy_mat(){
		this.setTitle("LMNF");
		this.setSize(1000,600);
		this.setLocation(350,30);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.orange);
		add(pn);
		//
		lab1=new JLabel("LISTE DES MOYENNES DES ETUDIANTS DANS UNE MATIERE");
		lab1.setBounds(150,10,750,30);
		lab1.setFont(new Font("Arial",Font.BOLD,25));
		lab1.setBorder(BorderFactory.createLineBorder(Color.black));
		pn.add(lab1);
		//label1
		lab1=new JLabel("MATIERE :");
		lab1.setBounds(60,80,80,30);
		lab1.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lab1);
		//combo1
		jcb1=new JComboBox();
		jcb1.addItem("SDD");
		jcb1.addItem("OL");
		jcb1.addItem("PM");
		jcb1.addItem("ALGO1");
		jcb1.setBounds(140,80,80,30);
		pn.add(jcb1);
		
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
				
				//
				DefaultTableModel df=new DefaultTableModel();
				init();
				df.addColumn("NOM");
				df.addColumn("PRENOM");
				df.addColumn("MATIERE");
				df.addColumn("MOYENNE");
				df.addColumn("APPRECIATION");
				
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
		
	List_moy_mat ls=new List_moy_mat();
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
	
	if(e.getSource()==jb1){
		String a;
		a=jcb1.getSelectedItem().toString();
		DefaultTableModel df=new DefaultTableModel();
		df.addColumn("NOM");
		df.addColumn("PRENOM");
		df.addColumn("MATIERE");
		df.addColumn("MOYENNE");
		df.addColumn("APPRECIATION");
				//df.addColumn("Filiere");
		//df.addColumn("Niveau");
		tb.setModel(df);
		String qry="select nom,prenom,appelation,round(moyenne,2) as moyenne,case when moyenne>=18 then 'EXCELLENT'"
				+ "when moyenne>=16 then 'TRES-BIEN' when moyenne>=14 then 'BIEN' "
				+ "when moyenne>=12 then 'ASSEZ-BIEN' when moyenne>=10 then 'PASSABLE' else 'SOUS-MOYENNE'"
				+ "end as mention from v_etd_note  where idmat='"+a+"'";
		
	/*String qry="create view v_etd_note as"
			+ "select idmat, nom,prenom,appelation,(controle+examen+tp)/3 as moyenne from etudiant inner join "
			+ "note on etudiant.matricule=note.idetudiant inner join"
		+ "matiere on note.idmatiere=matiere.idmat where idmat='"+a+"'";*/
		try{
			st=cn.chrisConnect().createStatement();
			rst=st.executeQuery(qry);
			while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("nom"),rst.getString("prenom"),rst.getString("appelation"),
						rst.getString("moyenne"),rst.getString("mention")
						
				});
				
			}
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(this,"INTROUVABLE",null,JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}


}
