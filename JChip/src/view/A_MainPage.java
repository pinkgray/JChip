package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.A_MemberManager;
import controller.ProjectManager;
import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;

public class A_MainPage extends JPanel{

	private MainFrame mf;
	private A_MainPage mainPage;
	
	private JPanel adminProjectsPanel = new JPanel();
	private JPanel projectsPanel = new JPanel();
	private JButton sprintAdd;
	private JButton projectBtn;
	private ArrayList adminBtnList = new ArrayList();
	private ArrayList projectBtnList = new ArrayList();
	
	private ProjectManager pm = new ProjectManager();
	private A_MemberManager mm = new A_MemberManager();
 	
	private Project selectedProject;
	private A_Member user;


	public A_MainPage(MainFrame mf, A_Member user) {


		this.mf=mf;
		this.mainPage=this;
		this.user = user;


		this.setSize(1024, 768);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode("#99cccc"));
		
		
//상단바
		JPanel topMenuPanel  = new JPanel();
		topMenuPanel.setLayout(new BorderLayout());
		topMenuPanel.setPreferredSize(new Dimension(1024,65));
		topMenuPanel.setBackground(Color.GRAY);
		
		//상단 홈 버튼
		JButton home = new JButton(new ImageIcon("images/home.png"));
		ImageIcon home2 = new ImageIcon("images/home2.png");
		home.setBorderPainted(false); 
		home.setFocusPainted(false); 
		home.setContentAreaFilled(false);
		home.setRolloverIcon(home2);
		home.setLocation(10,12);
		home.setSize(40, 40);

		//상단 계정 버튼
		JButton person = new JButton(new ImageIcon("images/user.PNG"));
		ImageIcon person2 = new ImageIcon("images/user2.PNG");
		person.setBorderPainted(false); 
		person.setFocusPainted(false); 
		person.setContentAreaFilled(false);
		person.setRolloverIcon(person2);
		person.setLocation(968,12);
		person.setSize(40, 40);
		person.addActionListener(new UserEvent());

		topMenuPanel.add(home, BorderLayout.WEST);
		topMenuPanel.add(person, BorderLayout.EAST);
		
		this.add(topMenuPanel, "North");
//



//왼쪽사이드패널
		JPanel sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(300,768));
		sidePanel.setBackground(Color.decode("#99cccc"));
		sidePanel.setLayout(null);

		//검색버튼
		JButton find = new JButton(new ImageIcon("images/serch.PNG"));
		ImageIcon find2 = new ImageIcon("images/serch2.PNG");
		find.setBorderPainted(false); 
		find.setFocusPainted(false); 
		find.setContentAreaFilled(false);
		find.setRolloverIcon(find2);
		find.setSize(30, 30);
		find.setLocation(10,20);

		//검색필드
		JTextField findt = new JTextField(20);
		findt.setLocation(40,20);
		findt.setSize(170,30);

		//새프로젝트 추가버튼
		JButton pro = new JButton(new ImageIcon("images/newProjectbtn1.png"));
		ImageIcon pro2 = new ImageIcon("images/newProjectbtn2.png");
		pro.setBorderPainted(false); 
		pro.setFocusPainted(false); 
		pro.setContentAreaFilled(false);
		pro.setRolloverIcon(pro2);
		pro.setSize(200,40);
		pro.setLocation(10,80);
		pro.addActionListener(new ProEvent());

		sidePanel.add(find);
		sidePanel.add(findt);
		sidePanel.add(pro);

		this.add(sidePanel, "West");
//
		
		
		
//가운데 패널		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(Color.decode("#99cccc"));

		//관리중인 프로젝트 모아놓은 패널
		JPanel adminPanel = new JPanel();
		adminPanel.setLayout(new BorderLayout());
		adminPanel.setBackground(Color.decode("#99cccc"));
		
		//관리중인 프로젝트 라벨
		JPanel adminlbPanel = new JPanel();
		adminlbPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		adminlbPanel.setBackground(Color.decode("#99cccc"));
		adminlbPanel.setPreferredSize(new Dimension(700, 50));
		JLabel adminLabel = new JLabel("◎ 내가 관리하고 있는 프로젝트 ◎");
		adminLabel.setBackground(Color.decode("#99cccc"));
		adminLabel.setFont(new Font("HY바다M", Font.BOLD, 21));
		adminlbPanel.add(adminLabel);
		
		
		//관리중인 프로젝트들 들어가는 패널
		adminProjectsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		adminProjectsPanel.setPreferredSize(new Dimension(1000, 250));
		adminProjectsPanel.setBackground(Color.decode("#99cccc"));
		adminProjectsPanel.setAutoscrolls(true);
		
		
		adminPanel.add(adminlbPanel, "North");
		adminPanel.add(adminProjectsPanel, "Center");
		



		//참여중인 프로젝트 모아놓은 패널
		JPanel myProjectPanel = new JPanel();
		myProjectPanel.setLayout(new BorderLayout());
		myProjectPanel.setBackground(Color.decode("#99cccc"));


		//참여중인 프로젝트 라벨
		JPanel projectlbPanel = new JPanel();
		projectlbPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		projectlbPanel.setBackground(Color.decode("#99cccc"));
		projectlbPanel.setPreferredSize(new Dimension(700, 50));
		JLabel projectLabel = new JLabel("◎ 내가 참여하고 있는 프로젝트 ◎");
		projectLabel.setBackground(Color.decode("#99cccc"));
		projectLabel.setFont(new Font("HY바다M", Font.BOLD, 21));
		projectlbPanel.add(projectLabel);


		//참여중인 프로젝트들 들어가는 패널
		projectsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		projectsPanel.setBackground(Color.decode("#99cccc"));
		projectsPanel.setAutoscrolls(true);


		myProjectPanel.add(projectlbPanel, "North");
		myProjectPanel.add(projectsPanel,"Center");
		
		
		centerPanel.add(adminPanel, "North");
		centerPanel.add(myProjectPanel, "Center");


		myProjectsSetUp();
		this.add(centerPanel, "Center");
	}

	
	
	
//메소드
	//로그인시 user가 참여중인 프로젝트 보여지기
	public void myProjectsSetUp() {
		
		ArrayList<Project> projectList = pm.getProjectList();
		
		if(projectList != null) {
			for(int i = 0; i < projectList.size(); i++) {
				
				Project project = projectList.get(i);
				if(project.getProjectAdmin().equals(user.getId())) {
					showMyAdminProject(project.getProjectTitle());
				}
				if(project.getMemberList().contains(user.getId())) {
					showMyProject(project.getProjectTitle());
				}
			}
		}
		
	}
	
	//관리중인 프로젝트 
	public void showMyAdminProject(String projectTitle) {
		
		
		projectBtn = new JButton(projectTitle);
		
		adminBtnList.add(projectBtn);
		projectBtn.setPreferredSize(new Dimension(100, 80));
		projectBtn.setVisible(true);
		projectBtn.setBackground(Color.decode("#EED4CC"));
		projectBtn.setBorderPainted(false);
		projectBtn.setToolTipText("마우스 오른쪽 버튼을 눌러 프로젝트 정보를 수정하세요");
		adminProjectsPanel.add(projectBtn);
		adminProjectsPanel.revalidate();
		this.revalidate();
		adminEventLink();
	}
	
	//참여중인프로젝트
	public void showMyProject(String projectTitle) {
		
		projectBtn = new JButton(projectTitle);

		projectBtnList.add(projectBtn);
		projectBtn.setPreferredSize(new Dimension(100, 80));
		projectBtn.setVisible(true);
		projectBtn.setBackground(Color.white); 
		projectBtn.setBorderPainted(false);
		projectsPanel.add(projectBtn);
		projectsPanel.revalidate();
		this.revalidate();
		eventLink();

	}
	
	//새 프로젝트 생성시
	public void makeNewProject(String projectTitle, Date projectStartDay, Date projectEndDay, ArrayList<Sprint> sprintList, String projectAdmin, ArrayList<String> memberList) {

		pm.makeNewProject(projectTitle, projectStartDay, projectEndDay, sprintList, projectAdmin, memberList);
		
		if(projectAdmin.equals(user.getId())) {
			showMyAdminProject(projectTitle);
		}
		if(memberList.contains(user.getId())) {
			showMyProject(projectTitle);
		}
	}
	
	//프로젝트 수정시
	public void modifyProject(Project project, String projectTitle, Date projectStartDay, Date projectEndDay, ArrayList<Sprint> sprintList, ArrayList<String> memberList) {
		
		pm.modifyProject(project, projectTitle, projectStartDay, projectEndDay, sprintList, memberList);
		
		projectBtn.setText(projectTitle);
		
		adminProjectsPanel.removeAll();
		projectsPanel.removeAll();
		myProjectsSetUp();
		this.revalidate();

	}

	//프로젝트 삭제시
	public void deleteProject(Project project) {
		pm.deleteProject(project);
		
		projectBtn.setVisible(false);
	}
	
	//프로젝트 팝업에서 멤버 추가시
	public A_Member findMember(String id) {
		
		A_Member member = mm.findMember(id);

		return member;
	}
//

	
	
	
	
//이벤트	
	//관리중인 프로젝트 버튼에 이벤트링크
	public void adminEventLink() {
		for(int i = 0; i < adminBtnList.size(); i++) {
			JButton projectBtn = (JButton)adminBtnList.get(i);
			projectBtn.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {

					if (e.getButton() == 1) {

						String titleSelected = projectBtn.getText();
						selectedProject = pm.getProject(titleSelected);
						goToProjectPage(selectedProject);
					}

					if (e.getButton() == 3) {
						String titleSelected = projectBtn.getText();
						selectedProject = pm.getProject(titleSelected);
						new A_AddProject(mf, mainPage, selectedProject, user).getAddProject().setVisible(true);

					}
				}
			});
		}
	}

	//일반 참여중인 프로젝트 버튼에 이벤트 링크
	public void eventLink() {
		for(int i = 0; i < projectBtnList.size(); i++) {
			JButton projectBtn = (JButton)projectBtnList.get(i);
			projectBtn.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {

					if (e.getButton() == 1) {

						String titleSelected = projectBtn.getText();
						selectedProject = pm.getProject(titleSelected);
						goToProjectPage(selectedProject);
					}
				}
			});
		}
	}

	//프로젝트생성버튼 클릭시 동작하는 이벤트
	class ProEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new A_AddProject(mf, mainPage, null, user).getAddProject().setVisible(true);
		}
	}

	//계정버튼 클릭시 동작하는 이벤트
	class UserEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new A_AddUserPU(mf, user).getUserPU().setVisible(true);
		}
	}
//

	
	
	
//체인지패널	
	public void goToProjectPage(Project selectedProject) {
		ChangePanel.changePanel(mf, this, new B_ProjectPage(mf, this, selectedProject, user));
	}

	public void goToLoginPage() {
		ChangePanel.changePanel(mf, this, new A_LoginPage(mf));
	}
//	




}