package securite;
/*Application de gestion des notes des étudiants
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ig.Connecteur;

import java.sql.*;

public class ChangePass extends JFrame implements ActionListener{
	JLabel lb1,lb2,lb3,lb4,lbi;
	JTextField tf1,tf2,tf3,tf4,tfi;
	JButton jb;
	
	Statement st;
	ResultSet rst;
	Connecteur cn=new Connecteur();
	public ChangePass(){
		this.setTitle("Change_Pass");
		this.setSize(400,350);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.orange);
		add(pn);
		//
		lbi=new JLabel("Identifiant");
		lbi.setBounds(80,20,150,30);
		lbi.setFont(new Font("Arial",Font.BOLD,16));
		lbi.setForeground(Color.BLUE);
		pn.add(lbi);
		
		tfi=new JTextField();
		tfi.setBounds(190,20,150,30);
		pn.add(tfi);
		//
		lb1=new JLabel("Ancien Login");
		lb1.setBounds(40,60,150,30);
		lb1.setFont(new Font("Arial",Font.BOLD,16));
		lb1.setForeground(Color.BLUE);
		pn.add(lb1);
		
		tf1=new JTextField();
		tf1.setBounds(190,60,150,30);
		pn.add(tf1);
		//
		lb2=new JLabel("Ancien password");
		lb2.setBounds(20,100,150,30);
		lb2.setFont(new Font("Arial",Font.BOLD,16));
		lb2.setForeground(Color.BLUE);
		pn.add(lb2);
		
		tf2=new JTextField();
		tf2.setBounds(190,100,150,30);
		pn.add(tf2);
		//
		lb3=new JLabel("Nouveau login");
		lb3.setBounds(40,170,150,30);
		lb3.setFont(new Font("Arial",Font.BOLD,16));
		lb3.setForeground(Color.BLUE);
		pn.add(lb3);
		
		tf3=new JTextField();
		tf3.setBounds(190,170,150,30);
		pn.add(tf3);
		//
		lb4=new JLabel("Nouveau password");
		lb4.setBounds(20,210,150,30);
		lb4.setFont(new Font("Arial",Font.BOLD,16));
		lb4.setForeground(Color.BLUE);
		pn.add(lb4);
		
		tf4=new JTextField();
		tf4.setBounds(190,210,150,30);
		pn.add(tf4);
		//jbutton
		jb=new JButton("CHANGER");
		jb.setBounds(150,270,130,30);
		jb.setBackground(Color.green);
		jb.setForeground(Color.black);
		jb.setFont(new Font("Arial",Font.BOLD,14));
		jb.addActionListener(this);
		pn.add(jb);
		
	}
	public static void main(String[] args){
		ChangePass chg=new ChangePass();
		chg.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			String a,b,c,d,f;
			a=tf1.getText();
			b=tf2.getText();
			c=tf3.getText();
			d=tf4.getText();
			f=tfi.getText();
			Verification vr=new Verification();
			try{
				st=cn.chrisConnect().createStatement();
				if(JOptionPane.showConfirmDialog(this,"Voulez vous changer le mot de passe?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					if(vr.verifier(a, b)==true){
						st.executeUpdate("update secret1 set login='"+c+"',password='"+d+"' where id='"+f+"'");
						dispose();
						ChangePass cp=new ChangePass();
						cp.setVisible(true);
						JOptionPane.showMessageDialog(this,"Modification reussie!");
					}
					else{
						JOptionPane.showMessageDialog(this,"Modification impossible!",null,JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
			catch(SQLException ex){
				
			}
			
			
		}
		
	}

}
