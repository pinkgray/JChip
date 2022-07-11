package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.A_MemberManager;
import model.vo.A_Member;

public class A_UserEdit extends JPanel implements MouseListener {
	//이베일 비번 연락처 수정
	private A_AddUserPU adduserPU;
	private Dialog userEdit;
	private MainFrame mf;
	private String EditEmail;
	private String userPwd;
	private String userPhoneNum;
	private JButton savebtn;
	private A_LoginPage lp;
	private A_MemberManager mm = new A_MemberManager();
	private A_Member user;
	
	private JTextField userEmailTF;
	private TextField userPwdTF;
	private JTextField userPhoneNumTF;
	
	
	public A_UserEdit() {}
	
	//public A_UserEdit(MainFrame mf, Dialog userEdit) {}

	public A_UserEdit(MainFrame mf, A_Member user) {
		this.mf = mf;
		this.adduserPU = adduserPU;
		this.user=user;
		
		userEdit = new Dialog(mf,"사용자 정보 수정");
		userEdit.setLayout(null);
		userEdit.setResizable(false);
		userEdit.setUndecorated(true);  //테두리 없에는것
		userEdit.setBackground(Color.decode("#5A5959"));
		userEdit.setSize(335, 335);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width - 120)  - (userEdit.getWidth() * 2);
		int yPos = (dim.height / 3 + 45) - (userEdit.getHeight() / 2);
		
		userEdit.setLocation(xPos,yPos);
		userEdit.setVisible(true);
		
		// 이메일 수정
		JLabel userEmailLB = new JLabel("이메일");
		userEmailLB.setFont(new Font("맑은 고딕",Font.BOLD,15));
		userEmailLB.setForeground(Color.white);
		userEmailLB.setLocation(10, 0);
		userEmailLB.setSize(120,40);
		userEdit.add(userEmailLB);
		
		userEmailTF = new JTextField(user.getEmail());
		userEmailTF.setSize(260,30);
		userEmailTF.setLocation(10, 40);
		userEmailTF.addMouseListener(this);
		userEdit.add(userEmailTF);
		
		//비밀번호 수정
		JLabel userPwdLB = new JLabel("비밀번호");
		userPwdLB.setFont(new Font("맑은 고딕",Font.BOLD,15));
		userPwdLB.setForeground(Color.white);
		userPwdLB.setLocation(10, 80);
		userPwdLB.setSize(120,40);
		userEdit.add(userPwdLB);
		
		userPwdTF = new TextField(user.getPwd());
		userPwdTF.setSize(260,30);
		userPwdTF.setLocation(10,120);
		userPwdTF.addMouseListener(this);
		userPwdTF.setEchoChar('●');
		userEdit.add(userPwdTF);
		
		//휴대폰 번호 변경
		JLabel userPhoneNumLB = new JLabel("번호");
		userPhoneNumLB.setFont(new Font("맑은 고딕",Font.BOLD,15));
		userPhoneNumLB.setForeground(Color.white);
		userPhoneNumLB.setLocation(10, 160);
		userPhoneNumLB.setSize(120,40);
		userEdit.add(userPhoneNumLB);
		
		userPhoneNumTF = new JTextField(user.getPhone());
		userPhoneNumTF.setSize(260,30);
		userPhoneNumTF.setLocation(10,200);
		userPhoneNumTF.addMouseListener(this);
		userEdit.add(userPhoneNumTF);
		
		//저장버튼
		JButton savebtn = new JButton(new ImageIcon("images/save1.png"));
		ImageIcon savebtn2 = new ImageIcon("images/save2.png");
		savebtn.setBorderPainted(false); 
		savebtn.setFocusPainted(false); 
		savebtn.setContentAreaFilled(false);
		savebtn.setRolloverIcon(savebtn2);
		
		savebtn.setSize(60, 30);
		savebtn.setLocation(20, 280);
		userEdit.add(savebtn);
		//변경 사항 저장 이벤트
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userPwd=userPwdTF.getText();
				userPhoneNum=userPhoneNumTF.getText();
				EditEmail=userEmailTF.getText();
				
				//chageUserDate(userPwd, EditEmail, userPhoneNum);
				
				mm.changeDate(user, userPwd, EditEmail, userPhoneNum);
				ChangePanel.changePanel(mf, lp, new A_LoginPage(mf));
				userEdit.dispose();
				
			}
		});
		JButton closebtn = new JButton(new ImageIcon("images/close1.png"));
		ImageIcon closebtn2 = new ImageIcon("images/close2.png");
		closebtn.setBorderPainted(false); 
		closebtn.setFocusPainted(false); 
		closebtn.setContentAreaFilled(false);
		closebtn.setRolloverIcon(closebtn2);
		
		closebtn.setSize(60, 30);
		closebtn.setLocation(250, 280);
		userEdit.add(closebtn);
		
		//닫기 이벤트
		closebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userEdit.dispose();
				
			}
		});
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == userEmailTF) {
			userEmailTF.setText("");
		}
		
		if (e.getSource() == userPhoneNumTF) {
			userPhoneNumTF.setText("");
		}
		
		
		if (e.getSource() == userPwdTF) {
			userPwdTF.setText("");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
		
}

