package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.vo.A_Member;
import model.vo.Project;


public class B_AddInvitePU extends JPanel {
	private MainFrame mf; 
	private Dialog invitePU;
	
	private Project selectedProject;
	private A_Member user;
	
	public B_AddInvitePU() {}
	
	public B_AddInvitePU(MainFrame mf, Project selectedProject, A_Member user) {
		
		this.mf = mf;
		this.selectedProject = selectedProject;
		this.user = user;
		
		invitePU = new Dialog(mf,"ÃÊ´ëÇÏ±â");
		invitePU.setUndecorated(true);
		
		invitePU.setSize(310, 290);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width - 120)  - (invitePU.getWidth() * 2);
		int yPos = (dim.height / 3 + 45) - (invitePU.getHeight() / 2);
		invitePU.setLocation(xPos, yPos);
		
		
		
		invitePU.setBackground(Color.decode("#5A5959"));
		invitePU.setLayout(null);
		
		
		//¸â¹ö
		JLabel Add_label = new JLabel("ÇÁ·ÎÁ§Æ® ¸â¹ö");
		Add_label.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,18));
		Add_label.setForeground(Color.WHITE);
		Add_label.setLocation(10,0);
		Add_label.setSize(120, 40);
		
		invitePU.add(Add_label);
		
		//¸â¹ö º¸¿©Áö´Â °÷
		String projectAdmin = selectedProject.getProjectAdmin();
		ArrayList<String> memberList = selectedProject.getMemberList();
		String str = projectAdmin + " (ÇÁ·ÎÁ§Æ® °ü¸®ÀÚ)";
		if(projectAdmin.equals(user.getId())) {
			str += " (³ª)\n";
		}else {
			str += "\n";
		}
		
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).equals(user.getId())) {
				str += memberList.get(i) + " (³ª)\n";
			}else {
				str += memberList.get(i) + "\n";
			}
		}
		
		JTextArea T_Member = new JTextArea();
		T_Member.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		if (str != null) {
			T_Member.setText(str);
		}
		T_Member.setEditable(false);
		invitePU.add(T_Member);
		
		//¸â¹ö ½ºÅ©·Ñ
		JScrollPane M_Scroll = new JScrollPane(T_Member); 
		M_Scroll.setLocation(20, 40);
		M_Scroll.setSize(265,200);
		invitePU.add(M_Scroll);
		
		
		
		//´Ý±â¹öÆ°
		JButton closebtn = new JButton(new ImageIcon("images/close1.png"));
		ImageIcon closebtn2 = new ImageIcon("images/close2.png");
		closebtn.setBorderPainted(false); 
		closebtn.setFocusPainted(false); 
		closebtn.setContentAreaFilled(false);
		closebtn.setRolloverIcon(closebtn2);
		closebtn.setSize(48,30);
		closebtn.setLocation(240,250);
		closebtn.addActionListener(new CloseEvent());
		
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        pointerInfo.getLocation();
        Dimension my = invitePU.getSize();
        invitePU.setLocation(pointerInfo.getLocation().x-my.width, pointerInfo.getLocation().y);
		invitePU.add(closebtn);
				
		
		
	}
	public Dialog getinvitePU() {
		return invitePU;
	}
	
	private class CloseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			invitePU.dispose();
		}

	}
	

}


