package securite2;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ig.Connecteur;
import requetes.Requetes_liste;

import java.sql.*;
/*Application de gestion des notes des étudiants
 */
public class CRUD_mdp extends JFrame  implements ActionListener{
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	JLabel  lb1,lb2,lb3,lb4,lb5,lb6;
	JTextField jtf2,jtf3,jtf4,jtf5,tf1,tf2;
	JButton jbajt1,jbajt2,jb5,jbsup1,jbsup2,jbmodif1,jbmodif2,jbrech1,jbrech2;
    JRadioButton rb1,rb2;
    JComboBox jcb,jcb2;
	JTable tb,tb2;
	JScrollPane scrl,scrl2;
	
	
	public CRUD_mdp(){
		this.setTitle("CRUD_PASS");
		this.setSize(1000,650);
		this.setResizable(false);
		this.setLocation(350,30);
	JPanel jp=new JPanel();
	jp.setLayout(null);
	jp.setBackground(Color.orange);
	add(jp);
	lb1=new JLabel(" Gestion des mots de passe");
	lb1.setFont(new Font("Arial",Font.BOLD,20));
	lb1.setForeground(Color.blue);
	lb1.setBounds(70,10,400,30);
	jp.add(lb1);
	
	// identifiant matiere
	lb2=new JLabel("Identifiant");
	lb2.setFont(new Font("Arial",Font.BOLD,15));
	lb2.setForeground(Color.blue);
	lb2.setBounds(50,90,100,30);
	jp.add(lb2);
	
	jtf2=new JTextField();
	jtf2.setBounds(130,90,150,30);
	jp.add(jtf2);
	
	
	// login
	lb3=new JLabel("Login");
	lb3.setFont(new Font("Arial",Font.BOLD,15));
	lb3.setForeground(Color.blue);
	lb3.setBounds(80,130,100,30);
	jp.add(lb3);
	
	jtf3=new JTextField();
	jtf3.setBounds(130,130,150,30);
	jp.add(jtf3);
	// password
		lb4=new JLabel("Password");
		lb4.setFont(new Font("Arial",Font.BOLD,15));
		lb4.setForeground(Color.blue);
		lb4.setBounds(50,170,100,30);
		jp.add(lb4);
		
		jtf4=new JTextField();
		jtf4.setBounds(130,170,150,30);
		jp.add(jtf4);
			
	//les boutons
	  //insertion1
	jbajt1=new JButton("Inserer dans USER");
	jbajt1.setBounds(30,230,150,30);
	jbajt1.setForeground(Color.blue);
	jbajt1.setBackground(Color.green);
	jbajt1.addActionListener(this);
	jp.add(jbajt1);
	  //insertion2
		jbajt2=new JButton("Inserer dans ADMIN");
		jbajt2.setBounds(230,230,150,30);
		jbajt2.setForeground(Color.blue);
		jbajt2.setBackground(Color.green);
		jbajt2.addActionListener(this);
		jp.add(jbajt2);
	//supression
		jbsup1=new JButton("Supprimer dans USER");
		jbsup1.setBounds(30,280,160,30);
		jbsup1.setForeground(Color.blue);
		jbsup1.setBackground(new Color(250,100,0));
		jbsup1.addActionListener(this);
		jp.add(jbsup1);
		
		jbsup2=new JButton("Supprimer dans ADMIN");
		jbsup2.setBounds(220,280,170,30);
		jbsup2.setForeground(Color.blue);
		jbsup2.setBackground(new Color(250,100,0));
		jbsup2.addActionListener(this);
		jp.add(jbsup2);
	
	//modification
		jbmodif1=new JButton("Modifier dans USER");
		jbmodif1.setBounds(30,330,160,30);
		jbmodif1.setForeground(Color.blue);
		jbmodif1.setBackground(Color.ORANGE);
		jbmodif1.addActionListener(this);
		jp.add(jbmodif1);
		
		jbmodif2=new JButton("Modifier dans ADMIN");
		jbmodif2.setBounds(220,330,160,30);
		jbmodif2.setForeground(Color.blue);
		jbmodif2.setBackground(Color.ORANGE);
		jbmodif2.addActionListener(this);
		jp.add(jbmodif2);
	
	//recherche 
		jbrech1=new JButton("Recherche dans USER");
		jbrech1.setBounds(30,380,170,30);
		jbrech1.addActionListener(this);
		jp.add(jbrech1);
		
		tf1=new JTextField("identifiant");
		tf1.setBounds(220,380,150,30);
		jp.add(tf1);
		
		jbrech2=new JButton("Recherche dans ADMIN");
		jbrech2.setBounds(30,430,170,30);
		jbrech2.addActionListener(this);
		jp.add(jbrech2);
		
		tf2=new JTextField("identifiant");
		tf2.setBounds(220,430,150,30);
		jp.add(tf2);
		
		
	//actualiser
		jb5=new JButton("Actualiser");
		jb5.setBounds(120,490,120,30);
		jb5.setForeground(Color.blue);
		jb5.setBackground(Color.green);
		jb5.addActionListener(this);
		jp.add(jb5);
		//label tableau
		lb5=new JLabel("Mots de passe des utilisateurs");
		lb5.setBounds(550,10,330,30);
		//lb5.setForeground(Color.blue);
		lb5.setFont(new Font("Arial",Font.BOLD,18));
		jp.add(lb5);
		
		lb6=new JLabel("Mots de passe des administrateurs");
		lb6.setBounds(550,320,330,30);
		//lb5.setForeground(Color.blue);
		lb6.setFont(new Font("Arial",Font.BOLD,18));
		jp.add(lb6);
		
		
		
		
		//df		
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("Identifiant");
		df.addColumn("Login");
		df.addColumn("Password");
		
		tb.setModel(df);
		jp.add(scrl);
		
		String qry="select * from secret1";
		try{
			st=cn.chrisConnect().createStatement();
			rst=st.executeQuery(qry);
			while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("id"),rst.getString("login"),
						rst.getString("password")
						
						
				});
				
			}
		}
		catch(SQLException ex){
			
		}
		
		//df2		
				DefaultTableModel df2=new DefaultTableModel();
				init2();
				df2.addColumn("Identifiant");
				df2.addColumn("Login");
				df2.addColumn("Password");
				
				tb2.setModel(df2);
				jp.add(scrl2);
				
				String qry2="select * from secret2";
				try{
					st=cn.chrisConnect().createStatement();
					rst=st.executeQuery(qry2);
					while(rst.next()){
						df2.addRow(new Object[]{
								rst.getString("id"),rst.getString("login"),
								rst.getString("password")
								
								
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
		scrl.setBounds(400,40,580,260);
		
	}
	private void init2(){
		tb2=new JTable();
		scrl2=new JScrollPane();
		scrl2.setViewportView(tb2);
		scrl2.setBounds(400,350,580,250);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CRUD_mdp mt=new CRUD_mdp();
		mt.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ajout dans user
		if(e.getSource()==jbajt1){
			String a,b,c;
		a=jtf2.getText();
		b=jtf3.getText();
		c=jtf4.getText();
	
			
		String query="insert into secret1 values('"+a+"','"+b+"','"+c+"')";
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
			
		}
		//ajout dans admin
				if(e.getSource()==jbajt2){
					String a,b,c;
				a=jtf2.getText();
				b=jtf3.getText();
				c=jtf4.getText();
			
					
				String query="insert into secret2 values('"+a+"','"+b+"','"+c+"')";
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
					
				}
		//suppression dans user
				if(e.getSource()==jbsup1){
					String a;
					a=jtf2.getText();
				String query="delete from secret1 where id='"+a+"'";
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
				//suppression dans admin
				if(e.getSource()==jbsup2){
					String a;
					a=jtf2.getText();
				String query="delete from secret2 where id='"+a+"'";
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
				//modification dans user
				if(e.getSource()==jbmodif1){
					String a,b,c;
					a=jtf2.getText();
					b=jtf3.getText();
					c=jtf4.getText();
					
				String query="update secret1 set login='"+b+"',password='"+c+"' where id='"+a+"'";
				try{
					st=cn.chrisConnect().createStatement();
					if(JOptionPane.showConfirmDialog(this,"Voulez vous modifiez",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(this,"Modification reuissie!");
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(this,"Modification impossible!",null,JOptionPane.ERROR_MESSAGE);
					
				}
					
				}
				//modification dans admin
				if(e.getSource()==jbmodif2){
					String a,b,c;
					a=jtf2.getText();
					b=jtf3.getText();
					c=jtf4.getText();
					
				String query="update secret2 set login='"+b+"',password='"+c+"' where id='"+a+"'";
				try{
					st=cn.chrisConnect().createStatement();
					if(JOptionPane.showConfirmDialog(this,"Voulez vous modifiez",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(this,"Modification reuissie!");
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(this,"Modification impossible!",null,JOptionPane.ERROR_MESSAGE);
					
				}
					
				}
				//recherche dans user
				if(e.getSource()==jbrech1){
					String a;
					a=tf1.getText();
				String query="select * from secret1 where id='"+a+"'";
				try{
					st=cn.chrisConnect().createStatement();
					rst=st.executeQuery(query);
					if(rst.next()){
						jtf2.setText(rst.getString("id"));
						jtf3.setText(rst.getString("login"));
						jtf4.setText(rst.getString("password"));
					}
					else
						JOptionPane.showMessageDialog(this, "Introuvable!",null,JOptionPane.ERROR_MESSAGE);
					
					
				}
				catch(SQLException ex){
				
					
				}
					
				}//
				//recherche dans admin
				if(e.getSource()==jbrech2){
					String a;
					a=tf2.getText();
				String query="select * from secret2 where id='"+a+"'";
				try{
					st=cn.chrisConnect().createStatement();
					rst=st.executeQuery(query);
					if(rst.next()){
						jtf2.setText(rst.getString("id"));
						jtf3.setText(rst.getString("login"));
						jtf4.setText(rst.getString("password"));
					}
					else
						JOptionPane.showMessageDialog(this, "Introuvable!",null,JOptionPane.ERROR_MESSAGE);
					
					
				}
				catch(SQLException ex){
				
					
				}
					
				}//
				//actualiser 
				if(e.getSource()==jb5){
					dispose();
					CRUD_mdp mp=new CRUD_mdp();
					mp.setVisible(true);
					
					
				}
				

				
		
		
		
		
		
	}

}

