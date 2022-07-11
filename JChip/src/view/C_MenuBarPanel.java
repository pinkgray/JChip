package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;



public class C_MenuBarPanel extends JPanel implements MouseListener,ActionListener{
	
	private MainFrame mf;
	private C_SprintMainPage sprintMain;
	private C_MenuBarPanel MenuPanel;
	//받아온 selectedSprint 필드에저장(민)
	private Project selectedProject;
	private Sprint selectedSprint;
	private C_SprintMainPage sprintPage;
	private JButton User_btn;
	private JButton home;
	private JButton back;
	private JButton Member_btn ;
	private A_Member user;
	
	//매개변수수정(민)
	public C_MenuBarPanel(C_SprintMainPage sprintMain, MainFrame mf, Project selectedProject, Sprint selectedSprint,A_Member user) {
		this.sprintMain = sprintMain;
		this.MenuPanel = this;
		this.mf = mf;
		this.user = user;
		//(민)
		this.selectedProject = selectedProject;
		this.selectedSprint = selectedSprint;
		
		//this.setPreferredSize(new Dimension(1024,65));
		this.setBackground(Color.GRAY);
		this.setLayout(new BorderLayout());
		
		//홈버튼과 뒤로가기 버튼
		JPanel West_Panel = new JPanel();
		West_Panel.setPreferredSize(new Dimension(130,65));
		West_Panel.setLayout(new BorderLayout());
		West_Panel.setBackground(Color.GRAY);
		
		//Home 버튼
		/*JButton*/ home = new JButton(new ImageIcon("images/home.PNG"));
		ImageIcon home2 = new ImageIcon("images/home2.PNG");
		//버튼 뒤에 배경 지우는 함수
		home.setBorderPainted(false); 
		home.setFocusPainted(false); 
		home.setContentAreaFilled(false);
		home.setRolloverIcon(home2);
		
		//버튼에 마우스 올릴시 텍스트 뜨도록 추가함
		home.setToolTipText("메인페이지로 이동");

		//home.setLocation(10,12);
		//home.setSize(40, 40);
		home.setPreferredSize(new Dimension(40,40));
		home.addActionListener(this);
		
		//back 버튼
		/*JButton*/ back = new JButton(new ImageIcon("images/back.PNG"));
		ImageIcon back2 = new ImageIcon("images/back2.PNG");

		//버튼 뒤에 배경 지우는 함수
		back.setBorderPainted(false); 
		back.setFocusPainted(false); 
		back.setContentAreaFilled(false);
		back.setRolloverIcon(back2);

		//버튼에 마우스 올릴시 보일것
		back.setToolTipText("프로젝트 페이지로 이동");
		
		
		back.addActionListener(this);
		//back.setLocation(65,13);
		//back.setSize(40, 40);
		
		back.setPreferredSize(new Dimension(40,40));
		
		West_Panel.add(home,"West");
		West_Panel.add(back,"Center");
		
		//중간 Panel = 포로젝트 명 과 프로젝트 바
		JPanel Center_Panel = new JPanel();
		Center_Panel.setPreferredSize(new Dimension(300,65));
		Center_Panel.setLayout(new BoxLayout(Center_Panel,BoxLayout.Y_AXIS));
		//Center_Panel.setLayout(null);
		Center_Panel.setBackground(Color.GRAY);
		
		JPanel pro_Panel = new JPanel();
		pro_Panel.setPreferredSize(new Dimension(100,10));
		pro_Panel.setBackground(Color.GRAY);
	
		//진행바
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		
		Date startDay = selectedSprint.getSprintStartDay();
		Date endDay = selectedSprint.getSprintEndDay();
		Date today = new Date();
		
		
		long totalTime = endDay.getTime() - startDay.getTime(); 	//밀리세컨드
		int totalDay = (int)(((((totalTime / 1000) / 60) / 60) / 24)); 
		long timePast = today.getTime() - startDay.getTime();
		int dayPast = (int)(((((timePast / 1000) / 60) / 60) / 24)) + 1;
		
		progressBar.setMaximum(totalDay);
		progressBar.setValue(dayPast);
		progressBar.setForeground(Color.decode("#72f07e"));

		progressBar.setPreferredSize(new Dimension(350, 10));
		progressBar.setAlignmentX(CENTER_ALIGNMENT);
		progressBar.setAlignmentY(CENTER_ALIGNMENT);

		pro_Panel.add(progressBar);
		
		

		//progressBar.setLocation(320,50);
		//progressBar.setSize(350,10);
		progressBar.setPreferredSize(new Dimension(350,10));
		progressBar.setAlignmentX(CENTER_ALIGNMENT);
		progressBar.setAlignmentY(CENTER_ALIGNMENT);
		
		pro_Panel.add(progressBar);
		
		JLabel Sprint_Title = new JLabel();
		
		//스프린트명 선택된 것으로 받아서 출력(민)
		Sprint_Title.setText(selectedProject.getProjectTitle() + " " + selectedSprint);	//에러
		
		Sprint_Title.setFont(new Font("맑은 고딕",Font.BOLD,19));
		Sprint_Title.setAlignmentX(CENTER_ALIGNMENT);
		Sprint_Title.setAlignmentY(CENTER_ALIGNMENT);
		//Sprint_Title.setLocation(100,10);
		//Sprint_Title.setSize(600, 35);
		
		Sprint_Title.setPreferredSize(new Dimension(600,50));
		
		Center_Panel.add(Sprint_Title);
		Center_Panel.add(pro_Panel);
		
		//Center_Panel.add(BoxLayout_Panel,"Center");
		
		//East Panel -> 사용자 정보 버튼, 멤버 버튼
		JPanel East_Panel = new JPanel();
		East_Panel.setPreferredSize(new Dimension(120,65));
		East_Panel.setLayout(new BorderLayout());
		East_Panel.setBackground(Color.gray);
		
		//사용자 버튼
		User_btn = new JButton(new ImageIcon("images/user.PNG"));
		ImageIcon User2_btn = new ImageIcon("images/user2.PNG");
		//버튼 뒤에 배경 지우는 함수
		User_btn.setBorderPainted(false); 
		User_btn.setFocusPainted(false); 
		User_btn.setContentAreaFilled(false);
		User_btn.setRolloverIcon(User2_btn);

		User_btn.setLocation(968,12);
		User_btn.setSize(40, 40);
		
		User_btn.addMouseListener(this);
		
		
		//멤버버튼
		/*JButton */ Member_btn = new JButton(new ImageIcon("images/circle.png"));
		ImageIcon Member_addIcon = new ImageIcon("images/circle2.PNG");
		Member_btn.setBorderPainted(false); 
		Member_btn.setFocusPainted(false); 
		Member_btn.setContentAreaFilled(false);
		Member_btn.setRolloverIcon(Member_addIcon);
		Member_btn.setBackground(Color.GRAY);
		Member_btn.setLocation(920, 12);
		Member_btn.setSize(40,40);
		
		Member_btn.addMouseListener(this);

		East_Panel.add(User_btn,"Center");
		East_Panel.add(Member_btn,"West");
		
		this.add(West_Panel, "West");
		this.add(Center_Panel,"Center");
		this.add(East_Panel,"East");
		
		sprintMain.add(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == User_btn) {
			new A_AddUserPU(mf,user).getUserPU().setVisible(true);
		
		}
		if (e.getSource() == Member_btn) {
				new C_AddInvitePU(mf,selectedProject, user).getinvitePU().setVisible(true);
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == home) {
			//C_SprintMainPage sm = new C_SprintMainPage(mf);
			//MainPage mp = new MainPage(mf);
			sprintMain.goToMainPage();
			//ChangePanel.changePanel(mf, loginPage, new Join());
			//ChangePanel.changePanel(mf,this.sprintMain, mp);
		}
		if (e.getSource() == back) {
			//B_ProjectPage projectPage = new B_ProjectPage(mf);
			sprintMain.goToProjectPage();
			//ChangePanel.changePanel(mf, this.sprintMain, projectPage);			
		}
	}
	
	
}
