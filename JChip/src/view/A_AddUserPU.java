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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.vo.A_Member;

public class A_AddUserPU extends JPanel{
	private MainFrame mf; 
	private A_AddUserPU a_AddUserPU;
	private Dialog userPU;
	private A_MainPage mainPage;
	private JButton logoutbtn;
	private A_LoginPage lp;
	private Dialog userEdit;
	private JButton Editbtn;
	//(민)
	private A_Member user;
	
	public A_AddUserPU() {}
	//(민)
	public A_AddUserPU(MainFrame mf, A_Member user) {
		this.mf = mf;
		
		//(민)
		this.user = user;
		
		this.lp = lp;
		//사용자 @@@@@@@@@@@@@@@@@@@@@@@@@@@
		//사용자 Menu
		userPU = new Dialog(mf,"사용자 정보"); 
		userPU.setLayout(null);
		userPU.setUndecorated(true);	//태두리 없에는거
		//P_User.setBackground(Color.red);
		userPU.setSize(335,235);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width - 120)  - (userPU.getWidth() * 2);
		int yPos = (dim.height / 3 + 45) - (userPU.getHeight() / 2);
		
		userPU.setLocation(xPos,yPos);
		userPU.setBackground(Color.decode("#5A5959"));
		userPU.setVisible(false);
		
				
		JLabel U_label = new JLabel("개인정보");
		U_label.setFont(new Font("맑은 고딕",Font.BOLD,25));
		U_label.setForeground(Color.WHITE);
		U_label.setLocation(10,0);
		U_label.setSize(120, 40);
		userPU.add(U_label);
				
		//개인정보
		
		//(민)
		String userInfo = user.getName() + "\n" + user.getEmail() + "\n" + user.getPhone();
		JTextArea T_Inf = new JTextArea(userInfo);
		//
		T_Inf.setLocation(20, 50);
		T_Inf.setSize(290,100);
		T_Inf.setEditable(false);
		userPU.add(T_Inf);
				
		/*JLabel Pro_label = new JLabel("진행중인 프로젝트");
		Pro_label.setFont(new Font("맑은 고딕",Font.BOLD,17));
		Pro_label.setForeground(Color.WHITE);
		Pro_label.setLocation(10,140);
		Pro_label.setSize(150, 40);
		userPU.add(Pro_label);*/

		/*//진행중인 프로젝트
		JTextArea T_Pro = new JTextArea("미니프로젝트");
		T_Pro.setLocation(20, 180);
		T_Pro.setSize(290,100);
		T_Pro.setEditable(false);
		userPU.add(T_Pro);
				
		//진행중인 프로젝트 스크롤
		JScrollPane Pro_Scroll = new JScrollPane(T_Pro); 
		Pro_Scroll.setLocation(20, 180);
		Pro_Scroll.setSize(290,100);
		userPU.add(Pro_Scroll);*/
		
		
		JButton closebtn = new JButton(new ImageIcon("images/close1.png"));
		ImageIcon closebtn2 = new ImageIcon("images/close2.png");
		closebtn.setBorderPainted(false); 
		closebtn.setFocusPainted(false); 
		closebtn.setContentAreaFilled(false);
		closebtn.setRolloverIcon(closebtn2);
		closebtn.setSize(60, 30);
		closebtn.setLocation(250,185);
		closebtn.addActionListener(new CloseEvent());
		
		JButton logoutbtn = new JButton(new ImageIcon("images/logout1.png"));
		ImageIcon logoutbtn2 = new ImageIcon("images/logout2.png");
		logoutbtn.setBorderPainted(false); 
		logoutbtn.setFocusPainted(false); 
		logoutbtn.setContentAreaFilled(false);
		logoutbtn.setRolloverIcon(logoutbtn2);
		logoutbtn.setSize(100, 30);
		logoutbtn.setLocation(150, 185);
		logoutbtn.addActionListener(new LogoutEvent());
		userPU.add(logoutbtn);
		
		//정보 수정을 누르면 A_UserEdit로 경로 이동

		JButton Editbtn = new JButton(new ImageIcon("images/Changinginformation1.png"));
		ImageIcon Editbtn2 = new ImageIcon("images/Changinginformation2.png");
		Editbtn.setBorderPainted(false); 
		Editbtn.setFocusPainted(false); 
		Editbtn.setContentAreaFilled(false);
		Editbtn.setRolloverIcon(Editbtn2);
		Editbtn.setSize(180,30);
		Editbtn.setLocation(-30, 185);
		userPU.add(Editbtn);
				
				Editbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						userPU.dispose();
						new A_UserEdit(mf,	user).setVisible(true);
						
					}
				});
		
				PointerInfo pointerInfo = MouseInfo.getPointerInfo();
				pointerInfo.getLocation();
				Dimension my = userPU.getSize();
				userPU.setLocation(pointerInfo.getLocation().x-my.width, pointerInfo.getLocation().y+10);
		
		
		
		userPU.add(closebtn);
	}
	
	public Dialog getUserPU() {
		return userPU;
	}
	
	private class CloseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			userPU.dispose();
		}

	}

class LogoutEvent implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		userPU.dispose();
		ChangePanel.changePanel(mf, lp, new A_LoginPage(mf));

	}
	
	
}
}
