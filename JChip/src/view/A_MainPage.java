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
		
		
//��ܹ�
		JPanel topMenuPanel  = new JPanel();
		topMenuPanel.setLayout(new BorderLayout());
		topMenuPanel.setPreferredSize(new Dimension(1024,65));
		topMenuPanel.setBackground(Color.GRAY);
		
		//��� Ȩ ��ư
		JButton home = new JButton(new ImageIcon("images/home.png"));
		ImageIcon home2 = new ImageIcon("images/home2.png");
		home.setBorderPainted(false); 
		home.setFocusPainted(false); 
		home.setContentAreaFilled(false);
		home.setRolloverIcon(home2);
		home.setLocation(10,12);
		home.setSize(40, 40);

		//��� ���� ��ư
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



//���ʻ��̵��г�
		JPanel sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(300,768));
		sidePanel.setBackground(Color.decode("#99cccc"));
		sidePanel.setLayout(null);

		//�˻���ư
		JButton find = new JButton(new ImageIcon("images/serch.PNG"));
		ImageIcon find2 = new ImageIcon("images/serch2.PNG");
		find.setBorderPainted(false); 
		find.setFocusPainted(false); 
		find.setContentAreaFilled(false);
		find.setRolloverIcon(find2);
		find.setSize(30, 30);
		find.setLocation(10,20);

		//�˻��ʵ�
		JTextField findt = new JTextField(20);
		findt.setLocation(40,20);
		findt.setSize(170,30);

		//��������Ʈ �߰���ư
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
		
		
		
//��� �г�		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(Color.decode("#99cccc"));

		//�������� ������Ʈ ��Ƴ��� �г�
		JPanel adminPanel = new JPanel();
		adminPanel.setLayout(new BorderLayout());
		adminPanel.setBackground(Color.decode("#99cccc"));
		
		//�������� ������Ʈ ��
		JPanel adminlbPanel = new JPanel();
		adminlbPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		adminlbPanel.setBackground(Color.decode("#99cccc"));
		adminlbPanel.setPreferredSize(new Dimension(700, 50));
		JLabel adminLabel = new JLabel("�� ���� �����ϰ� �ִ� ������Ʈ ��");
		adminLabel.setBackground(Color.decode("#99cccc"));
		adminLabel.setFont(new Font("HY�ٴ�M", Font.BOLD, 21));
		adminlbPanel.add(adminLabel);
		
		
		//�������� ������Ʈ�� ���� �г�
		adminProjectsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		adminProjectsPanel.setPreferredSize(new Dimension(1000, 250));
		adminProjectsPanel.setBackground(Color.decode("#99cccc"));
		adminProjectsPanel.setAutoscrolls(true);
		
		
		adminPanel.add(adminlbPanel, "North");
		adminPanel.add(adminProjectsPanel, "Center");
		



		//�������� ������Ʈ ��Ƴ��� �г�
		JPanel myProjectPanel = new JPanel();
		myProjectPanel.setLayout(new BorderLayout());
		myProjectPanel.setBackground(Color.decode("#99cccc"));


		//�������� ������Ʈ ��
		JPanel projectlbPanel = new JPanel();
		projectlbPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		projectlbPanel.setBackground(Color.decode("#99cccc"));
		projectlbPanel.setPreferredSize(new Dimension(700, 50));
		JLabel projectLabel = new JLabel("�� ���� �����ϰ� �ִ� ������Ʈ ��");
		projectLabel.setBackground(Color.decode("#99cccc"));
		projectLabel.setFont(new Font("HY�ٴ�M", Font.BOLD, 21));
		projectlbPanel.add(projectLabel);


		//�������� ������Ʈ�� ���� �г�
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

	
	
	
//�޼ҵ�
	//�α��ν� user�� �������� ������Ʈ ��������
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
	
	//�������� ������Ʈ 
	public void showMyAdminProject(String projectTitle) {
		
		
		projectBtn = new JButton(projectTitle);
		
		adminBtnList.add(projectBtn);
		projectBtn.setPreferredSize(new Dimension(100, 80));
		projectBtn.setVisible(true);
		projectBtn.setBackground(Color.decode("#EED4CC"));
		projectBtn.setBorderPainted(false);
		projectBtn.setToolTipText("���콺 ������ ��ư�� ���� ������Ʈ ������ �����ϼ���");
		adminProjectsPanel.add(projectBtn);
		adminProjectsPanel.revalidate();
		this.revalidate();
		adminEventLink();
	}
	
	//��������������Ʈ
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
	
	//�� ������Ʈ ������
	public void makeNewProject(String projectTitle, Date projectStartDay, Date projectEndDay, ArrayList<Sprint> sprintList, String projectAdmin, ArrayList<String> memberList) {

		pm.makeNewProject(projectTitle, projectStartDay, projectEndDay, sprintList, projectAdmin, memberList);
		
		if(projectAdmin.equals(user.getId())) {
			showMyAdminProject(projectTitle);
		}
		if(memberList.contains(user.getId())) {
			showMyProject(projectTitle);
		}
	}
	
	//������Ʈ ������
	public void modifyProject(Project project, String projectTitle, Date projectStartDay, Date projectEndDay, ArrayList<Sprint> sprintList, ArrayList<String> memberList) {
		
		pm.modifyProject(project, projectTitle, projectStartDay, projectEndDay, sprintList, memberList);
		
		projectBtn.setText(projectTitle);
		
		adminProjectsPanel.removeAll();
		projectsPanel.removeAll();
		myProjectsSetUp();
		this.revalidate();

	}

	//������Ʈ ������
	public void deleteProject(Project project) {
		pm.deleteProject(project);
		
		projectBtn.setVisible(false);
	}
	
	//������Ʈ �˾����� ��� �߰���
	public A_Member findMember(String id) {
		
		A_Member member = mm.findMember(id);

		return member;
	}
//

	
	
	
	
//�̺�Ʈ	
	//�������� ������Ʈ ��ư�� �̺�Ʈ��ũ
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

	//�Ϲ� �������� ������Ʈ ��ư�� �̺�Ʈ ��ũ
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

	//������Ʈ������ư Ŭ���� �����ϴ� �̺�Ʈ
	class ProEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new A_AddProject(mf, mainPage, null, user).getAddProject().setVisible(true);
		}
	}

	//������ư Ŭ���� �����ϴ� �̺�Ʈ
	class UserEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new A_AddUserPU(mf, user).getUserPU().setVisible(true);
		}
	}
//

	
	
	
//ü�����г�	
	public void goToProjectPage(Project selectedProject) {
		ChangePanel.changePanel(mf, this, new B_ProjectPage(mf, this, selectedProject, user));
	}

	public void goToLoginPage() {
		ChangePanel.changePanel(mf, this, new A_LoginPage(mf));
	}
//	




}