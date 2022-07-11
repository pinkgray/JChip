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
	//�޾ƿ� selectedSprint �ʵ忡����(��)
	private Project selectedProject;
	private Sprint selectedSprint;
	private C_SprintMainPage sprintPage;
	private JButton User_btn;
	private JButton home;
	private JButton back;
	private JButton Member_btn ;
	private A_Member user;
	
	//�Ű���������(��)
	public C_MenuBarPanel(C_SprintMainPage sprintMain, MainFrame mf, Project selectedProject, Sprint selectedSprint,A_Member user) {
		this.sprintMain = sprintMain;
		this.MenuPanel = this;
		this.mf = mf;
		this.user = user;
		//(��)
		this.selectedProject = selectedProject;
		this.selectedSprint = selectedSprint;
		
		//this.setPreferredSize(new Dimension(1024,65));
		this.setBackground(Color.GRAY);
		this.setLayout(new BorderLayout());
		
		//Ȩ��ư�� �ڷΰ��� ��ư
		JPanel West_Panel = new JPanel();
		West_Panel.setPreferredSize(new Dimension(130,65));
		West_Panel.setLayout(new BorderLayout());
		West_Panel.setBackground(Color.GRAY);
		
		//Home ��ư
		/*JButton*/ home = new JButton(new ImageIcon("images/home.PNG"));
		ImageIcon home2 = new ImageIcon("images/home2.PNG");
		//��ư �ڿ� ��� ����� �Լ�
		home.setBorderPainted(false); 
		home.setFocusPainted(false); 
		home.setContentAreaFilled(false);
		home.setRolloverIcon(home2);
		
		//��ư�� ���콺 �ø��� �ؽ�Ʈ �ߵ��� �߰���
		home.setToolTipText("������������ �̵�");

		//home.setLocation(10,12);
		//home.setSize(40, 40);
		home.setPreferredSize(new Dimension(40,40));
		home.addActionListener(this);
		
		//back ��ư
		/*JButton*/ back = new JButton(new ImageIcon("images/back.PNG"));
		ImageIcon back2 = new ImageIcon("images/back2.PNG");

		//��ư �ڿ� ��� ����� �Լ�
		back.setBorderPainted(false); 
		back.setFocusPainted(false); 
		back.setContentAreaFilled(false);
		back.setRolloverIcon(back2);

		//��ư�� ���콺 �ø��� ���ϰ�
		back.setToolTipText("������Ʈ �������� �̵�");
		
		
		back.addActionListener(this);
		//back.setLocation(65,13);
		//back.setSize(40, 40);
		
		back.setPreferredSize(new Dimension(40,40));
		
		West_Panel.add(home,"West");
		West_Panel.add(back,"Center");
		
		//�߰� Panel = ������Ʈ �� �� ������Ʈ ��
		JPanel Center_Panel = new JPanel();
		Center_Panel.setPreferredSize(new Dimension(300,65));
		Center_Panel.setLayout(new BoxLayout(Center_Panel,BoxLayout.Y_AXIS));
		//Center_Panel.setLayout(null);
		Center_Panel.setBackground(Color.GRAY);
		
		JPanel pro_Panel = new JPanel();
		pro_Panel.setPreferredSize(new Dimension(100,10));
		pro_Panel.setBackground(Color.GRAY);
	
		//�����
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		
		Date startDay = selectedSprint.getSprintStartDay();
		Date endDay = selectedSprint.getSprintEndDay();
		Date today = new Date();
		
		
		long totalTime = endDay.getTime() - startDay.getTime(); 	//�и�������
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
		
		//������Ʈ�� ���õ� ������ �޾Ƽ� ���(��)
		Sprint_Title.setText(selectedProject.getProjectTitle() + " " + selectedSprint);	//����
		
		Sprint_Title.setFont(new Font("���� ���",Font.BOLD,19));
		Sprint_Title.setAlignmentX(CENTER_ALIGNMENT);
		Sprint_Title.setAlignmentY(CENTER_ALIGNMENT);
		//Sprint_Title.setLocation(100,10);
		//Sprint_Title.setSize(600, 35);
		
		Sprint_Title.setPreferredSize(new Dimension(600,50));
		
		Center_Panel.add(Sprint_Title);
		Center_Panel.add(pro_Panel);
		
		//Center_Panel.add(BoxLayout_Panel,"Center");
		
		//East Panel -> ����� ���� ��ư, ��� ��ư
		JPanel East_Panel = new JPanel();
		East_Panel.setPreferredSize(new Dimension(120,65));
		East_Panel.setLayout(new BorderLayout());
		East_Panel.setBackground(Color.gray);
		
		//����� ��ư
		User_btn = new JButton(new ImageIcon("images/user.PNG"));
		ImageIcon User2_btn = new ImageIcon("images/user2.PNG");
		//��ư �ڿ� ��� ����� �Լ�
		User_btn.setBorderPainted(false); 
		User_btn.setFocusPainted(false); 
		User_btn.setContentAreaFilled(false);
		User_btn.setRolloverIcon(User2_btn);

		User_btn.setLocation(968,12);
		User_btn.setSize(40, 40);
		
		User_btn.addMouseListener(this);
		
		
		//�����ư
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
