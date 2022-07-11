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

//import view.SprintMainPage.PanelEvent;

//import view.SprintMainPage.Add_person;
//import view.SprintMainPage.DeleteEvent;

public class C_AddInvitePU extends JPanel implements ActionListener{
	private MainFrame mf; 
	private Dialog invitePU;
	private Project selectedProject;
	private A_Member user;

	
	private JButton Fire_Button;
	
	public C_AddInvitePU(MainFrame mf,Project selectedProject, A_Member user) {
		
		/*testFrame.setSize(500, 500);
		testFrame.setVisible(true);*/
		this.mf = mf;
		this.selectedProject = selectedProject;
		this.user = user;
		
		
		//초대 다이어로그
	
		invitePU = new Dialog(mf,"초대하기");
		invitePU.setUndecorated(true);	//태두리 없에는거
		
		invitePU.setSize(310, 290);	//310	310
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width - 200)  - (invitePU.getWidth() * 2);
		int yPos = (dim.height / 3 + 30) - (invitePU.getHeight() / 2);
		invitePU.setLocation(xPos, yPos);
		
		//invitePU.setLocation(625, 100);
		
		
		invitePU.setBackground(Color.decode("#5A5959"));
		//invitePU.setVisible(false);
		invitePU.setLayout(null);
		
		//testFrame.add(invitePU);
		
		//멤버
		JLabel Add_label = new JLabel("프로젝트 멤버");
		Add_label.setFont(new Font("맑은 고딕",Font.BOLD,18));
		Add_label.setForeground(Color.WHITE);
		Add_label.setLocation(10,0);
		Add_label.setSize(120, 40);
		
		invitePU.add(Add_label);
		
		//멤버 보여지는 곳
		
		
		String projectAdmin = selectedProject.getProjectAdmin();
		ArrayList<String> memberList = selectedProject.getMemberList();
		//String adminId = selectedProject.getAdminId();
		String str = projectAdmin + " (프로젝트 관리자)";
		if(projectAdmin.equals(user.getId())) {
			str += " (나)\n";
		}else {
			str += "\n";
		}
		
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).equals(user.getId())) {
				str += memberList.get(i) + " (나)\n";
			}else {
				str += memberList.get(i) + "\n";
			}
		}
		JTextArea T_Member = new JTextArea();
		T_Member.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		if (str != null) {
			T_Member.setText(str);
		}
		T_Member.setEditable(false);
		invitePU.add(T_Member);
		
		//멤버 스크롤
		JScrollPane M_Scroll = new JScrollPane(T_Member); 
		M_Scroll.setLocation(20, 40);
		M_Scroll.setSize(265,200);
		invitePU.add(M_Scroll);
		
		//관리자 보류
		JPanel Man_Div_panel = new JPanel();
		Man_Div_panel.setLayout(null);
		Man_Div_panel.setSize(310,100);
		Man_Div_panel.setLocation(0,242);
		Man_Div_panel.setBackground(Color.decode("#5A5959"));
		//invitePU.add(Man_Div_panel);
		
		//멤버 초대 버튼
		//JButton Add_Button = new JButton("초대");
		JButton Add_Button = new JButton(new ImageIcon("images/button1-1.png"));
		ImageIcon Add_ButtonIcon = new ImageIcon("images/invite button2.png");
		Add_Button.setBorderPainted(false); 
		Add_Button.setFocusPainted(false); 
		Add_Button.setContentAreaFilled(false);
		Add_Button.setRolloverIcon(Add_ButtonIcon);
		Add_Button.setSize(80,40);
		Add_Button.setLocation(20,10);
		Add_Button.addActionListener(new Add_person());		//이벤트
		//invitePU.add(Add_Button);
		////invitePU.add(Add_ButtonIcon);
		
		
		//멤버 강퇴 버튼
		//JButton Fire_Button = new JButton("강퇴");
		/*JButton*/ Fire_Button = new JButton(new ImageIcon("images/button2-1.png"));
		ImageIcon Fire_ButtonIcon = new ImageIcon("images/exit button2.png");
		Fire_Button.setBorderPainted(false); 
		Fire_Button.setFocusPainted(false); 
		Fire_Button.setContentAreaFilled(false);
		Fire_Button.setRolloverIcon(Fire_ButtonIcon);
		Fire_Button.setSize(80,40);
		Fire_Button.setLocation(115,10);
		
		Fire_Button.addActionListener(this);
				
		// 멤버 탈퇴 버튼
		//JButton Delete_Button = new JButton("탈퇴");
		JButton Delete_Button = new JButton(new ImageIcon("images/button3-1.png"));
		ImageIcon Delete_ButtonIcon = new ImageIcon("images/leave button2.png");
		Delete_Button.setBorderPainted(false); 
		Delete_Button.setFocusPainted(false); 
		Delete_Button.setContentAreaFilled(false);
		Delete_Button.setRolloverIcon(Delete_ButtonIcon);
		Delete_Button.setSize(80,40);
		Delete_Button.setLocation(210,10);
		//invitePU.add(Delete_Button);
		Delete_Button.addActionListener(new Mandate_Event());
				
		//Delete_Button.addActionListener(new DeleteEvent());   //이벤트
				
		//Man_Div_panel.add(Add_Button);
		//Man_Div_panel.add(Delete_Button);
		//Man_Div_panel.add(Fire_Button);
		
		
//		invitePU.add(Man_Div_panel);
		
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
	
	private class Add_person implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String id;
			id = JOptionPane.showInputDialog("초대 아이디를 입력하세요.");
			System.out.println(id);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Fire_Button) {
			String message = "강퇴시킬 멤버를 고르세요.";
			//new C_SprintDialog(this.mf,message).getAsspanel().setVisible(true);	
		}
	}
	
	
	public class Mandate_Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] member = {"문지원","송낙규","정민지","최인효","김규형"}; //테스트
			Object select = JOptionPane.showInputDialog(null, "관리자는 탈퇴하기전 다음 관리자를 선택해야합니다.\n관리자를 선택하세요", "탈퇴창",
				        JOptionPane.QUESTION_MESSAGE, null, member, member[0]);
			
			System.out.println(select);
		}
		
	}
}


