package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import controller.ProjectManager;
import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;





public class A_AddProject extends JPanel implements ActionListener{

	private MainFrame mf;           //AddProject��ü�� �ʵ忡�ٰ� ��������   //mf�� �� ������ �ʵ忡 �������.
	private A_MainPage mainPage;

	private Dialog addProject;

	private JButton sprintAdd;
	private JTextField adminField;
	private JButton changeAdmin; 
	private JButton cancelBtn;
	private DefaultListModel sprintModel;
	private JList sprintJList;
	private DefaultListModel memberModel;
	private JList memberJList;
	private int nameCtn = 0;
	
	private Project project;
	private String projectTitle;
	private Date projectStartDay;
	private Date projectEndDay;
	private String peopleProject;
	private ArrayList<Sprint> sprintList = new ArrayList<Sprint>();
	private String projectAdmin;
	private ArrayList<String> memberList = new ArrayList<String>();
	
	private A_Member user;
	
	public A_AddProject(MainFrame mf, A_MainPage mainPage, Project project, A_Member user) { 

		this.mf = mf;               
		this.mainPage = mainPage;
		this.project = project;
		this.user = user;
		
		addProject = new Dialog(mf, "�� ������Ʈ �����");       


		// �˾���ġ ����(ȭ�� ���)
		addProject.setSize(515, 700);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (addProject.getWidth() / 2);
		int yPos = (dim.height / 2) - (addProject.getHeight() / 2);
		addProject.setLocation(xPos, yPos);

		addProject.setUndecorated(true);
		addProject.setBackground(new Color(0, 0, 0, 0)); 
		addProject.setLayout(null);               



		JLabel label = new JLabel("������Ʈ �߰�");
		label.setFont(new Font("���� ���",Font.BOLD, 30));
		label.setForeground(Color.WHITE);
		label.setLocation(10,10);            
		label.setSize(200,100);
		addProject.add(label);

		JTextField proName = new JTextField("������Ʈ��",20);
		proName.setFont(new Font("���� ���", Font.PLAIN, 15));
		proName.setLocation(10,85);
		proName.setSize(485,50);
		if(project != null) {
			proName.setText(project.getProjectTitle());
		}
		addProject.add(proName);

		//textField�� ���콺 Ŭ���� ������������ ��ȭ������ �ٲ�
		proName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//�ƹ��͵� �Էµ��� ���� ���¿����� ��ĭ���� �����
				if (nameCtn == 0) {
					proName.setText("");
				}
			}
		});
		
		
		
		//������
		JLabel start = new JLabel("������");
		start.setLocation(30, 155);
		start.setSize(70, 40);
		start.setForeground(Color.WHITE);
		start.setFont(new Font("���� ���", Font.BOLD, 20));
		addProject.add(start);

		JXDatePicker startDay = new DatePicker().getDatePicker();
		JButton startDay_pick = (JButton) startDay.getComponent(1);
		ImageIcon startIcon = new ImageIcon("images/Calendar.png");
		startDay_pick.setIcon(startIcon);
		startDay_pick.setBorderPainted(false);
		startDay_pick.setFocusPainted(false);
		startDay_pick.setContentAreaFilled(false);
		startDay.setLocation(100, 155);
		startDay.setSize(130, 40);
		startDay.setFont(new Font("���� ���", Font.PLAIN, 15));
		if(project != null) {
			startDay.setDate(project.getProjectStartDay());
		}
		addProject.add(startDay);


		JLabel middle = new JLabel("~");
		middle.setLocation(250,155);
		middle.setForeground(Color.WHITE);
		middle.setSize(300,40);
		middle.setFont(new Font("���� ���",Font.BOLD, 30));
		addProject.add(middle);

		
		//������
		JLabel end = new JLabel("������");
		end.setLocation(290, 155);
		end.setSize(80, 40);
		end.setForeground(Color.WHITE);
		end.setFont(new Font("���� ���", Font.BOLD, 20));
		addProject.add(end);
		

		JXDatePicker endDay = new DatePicker().getDatePicker();
		JButton endDay_pick = (JButton) endDay.getComponent(1);
		ImageIcon endIcon = new ImageIcon("images/Calendar.png");
		endDay_pick.setIcon(endIcon);
		endDay_pick.setBorderPainted(false);
		endDay_pick.setFocusPainted(false);
		endDay_pick.setContentAreaFilled(false);
		endDay.setLocation(360, 155);
		endDay.setSize(130, 40);
		endDay.setFont(new Font("���� ���", Font.PLAIN, 15));
		if(project != null) {
			endDay.setDate(project.getProjectEndDay());
		}
		addProject.add(endDay);


		
		

		//�ι�° �г�
		JPanel projectPanel = new JPanel();
		projectPanel.setLayout(null);
		projectPanel.setSize(515,630);


		JLabel sprintLabel = new JLabel("������Ʈ �߰�");
		sprintLabel.setLocation(15,180);
		sprintLabel.setSize(400, 100);
		sprintLabel.setForeground(Color.WHITE);
		sprintLabel.setFont(new Font("���� ���",Font.BOLD, 20));

		projectPanel.add(sprintLabel);



		//������Ʈ �߰� ��ư
		sprintAdd = new JButton(new ImageIcon("images/plus1.png"));
		ImageIcon sprintAdd2 = new ImageIcon("images/plus2.png");
		sprintAdd.setBorderPainted(false);
		sprintAdd.setFocusPainted(false);
		sprintAdd.setContentAreaFilled(false);
		sprintAdd.setRolloverIcon(sprintAdd2);
		sprintAdd.setLocation(145,220);
		//sprintAdd.setBackground(Color.WHITE);
		sprintAdd.setBorder(null);
		sprintAdd.setSize(20,19);
		sprintAdd .setOpaque(false);
		//������Ʈ ��ư Ŭ���� ������Ʈ â ������
		sprintAdd.addActionListener(this);
		projectPanel.add(sprintAdd);
		addProject.add(projectPanel);


		//������ ������Ʈ ����Ʈ
		sprintModel = new DefaultListModel();
		if(project != null) {
			
			sprintList = project.getSprintList();
			for(int i = 0; i< sprintList.size(); i++) {
				sprintModel.addElement(sprintList.get(i));
			}
		}
		sprintJList = new JList(sprintModel);
		JScrollPane pane = new JScrollPane(sprintJList);   
		pane.setFont(new Font("���� ���", Font.PLAIN, 15));
		pane.setLocation(10,245);
		pane.setSize(480,100);

		projectPanel.add(pane);
		projectPanel.setBackground(new Color(66, 66, 66, 220));
		
		
		

		//������
		JLabel admin = new JLabel("������");
		admin.setFont(new Font("���� ���",Font.BOLD, 20));
		admin.setForeground(Color.WHITE);
		admin.setLocation(15, 375);
		admin.setSize(100,30);
		projectPanel.add(admin);
		
		projectAdmin = user.getId();
		adminField = new JTextField(user.getId());
		adminField.setLocation(100, 375);
		adminField.setSize(100, 35);
		projectPanel.add(adminField);
		
		
		
		changeAdmin = new JButton(new ImageIcon("images/rebtn11.png"));
		ImageIcon changeAdmin2 = new ImageIcon("images/rebtn22.png");
		changeAdmin.setBorderPainted(false); 
		changeAdmin.setFocusPainted(false); 
		changeAdmin.setContentAreaFilled(false);
		changeAdmin.setRolloverIcon(changeAdmin2);
		changeAdmin.setLocation(205,376);
		changeAdmin.setBorder(null);
		changeAdmin.setSize(40,35);
		changeAdmin.setOpaque(true);
		projectPanel.add(changeAdmin);
		changeAdmin.addActionListener(this); 
		
		
		
		
		
		//�ʴ�

		JLabel invite = new JLabel("�ʴ�");
		invite.setFont(new Font("���� ���",Font.BOLD, 20));
		invite.setLocation(15,420);
		invite.setSize(50,50);
		invite.setForeground(Color.WHITE);

		projectPanel.add(invite);

		JButton  personAdd = new JButton(new ImageIcon("images/plus1.png"));
		ImageIcon personAdd2 = new ImageIcon("images/plus2.png");
		personAdd.setBorderPainted(false);
		personAdd.setFocusPainted(false);
		personAdd.setContentAreaFilled(false);
		personAdd.setRolloverIcon(personAdd2);
		personAdd.setLocation(60,436);
		personAdd.setBackground(Color.WHITE);
		personAdd.setBorder(null);
		personAdd.setSize(20,19);
		personAdd .setOpaque(false);
		projectPanel.add(personAdd);
		personAdd.addActionListener(new Add_person()); 


		//�ʴ��� ����� ����Ʈ
		memberModel = new DefaultListModel();
		if(project != null && project.getMemberList() != null) {
			memberList = project.getMemberList();
			for(int i = 0; i< memberList.size(); i++) {
				memberModel.addElement(memberList.get(i));
			}
		}
		memberJList = new JList(memberModel);
		JScrollPane pane2 = new JScrollPane(memberJList);  
		pane2.setLocation(10,470);
		pane2.setSize(480,90);
		projectPanel.add(pane2);

		//����������
		memberJList.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == 3) {
					String member = (String)memberJList.getSelectedValue();
					int answer = JOptionPane.showConfirmDialog(null, "�����Ͻ� ����� �����Ͻðڽ��ϱ�?");
					
					if(answer == 0) {
					
						if(project == null) {
							subtractMemberFromList(member);
						}else {
							Project projectUpdated = new ProjectManager().deleteMember(project, member);
							updateMemberList(projectUpdated);
						}
						
					}
					
				}
			}
		});
		
		
		//������ư
		JButton deleteBtn = new JButton(new ImageIcon("images/delete1.png"));
		ImageIcon deleteBtn2 = new ImageIcon("images/delete2.png");
		deleteBtn.setBorderPainted(false); 
		deleteBtn.setFocusPainted(false); 
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setRolloverIcon(deleteBtn2);
		deleteBtn.setLocation(10,575);
		deleteBtn.setSize(100,33);
		projectPanel.add(deleteBtn);
		
		//������ưŬ���� �̺�Ʈ
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (project != null) {
					int answer = JOptionPane.showConfirmDialog(null, "������Ʈ�� �����Ͻðڽ��ϱ�?");
					if(answer == 0) {
						mainPage.deleteProject(project);
					}
				}
				addProject.dispose();
			}
		});
		

		//��ҹ�ư
		JButton cancelBtn  = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelbtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false); 
		cancelBtn.setFocusPainted(false); 
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelbtn2);

		cancelBtn.setLocation(282,575);
		cancelBtn.setSize(100,33);
		projectPanel.add(cancelBtn);


		//��� ��ư Ŭ���� �̺�Ʈ
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addProject.dispose();

			}
		});


		//Ȯ�ι�ư
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okbtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false); 
		okBtn.setFocusPainted(false); 
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okbtn2);
		okBtn.setLocation(392,575);
		okBtn.setSize(100,33);
		projectPanel.add(okBtn);
		
		
		//Ȯ�ι�ư Ŭ���� �̺�Ʈ
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//okBtnClickedCtn++;
				projectTitle = proName.getText();
				projectStartDay = startDay.getDate();
				projectEndDay = endDay.getDate();
				//peopleProject = peoples.getText();
				addProject.dispose();

				
				if(project == null) {
					mainPage.makeNewProject(projectTitle, projectStartDay, projectEndDay, sprintList, projectAdmin, memberList);
				
				}else {
					modifyProject(projectTitle, projectStartDay, projectEndDay);
				}
			}
		});



		addProject.add(projectPanel);
		addProject.setResizable(false); 
		addProject.setVisible(true);

	}
//	
	
	

	
//�޼ҵ�
	//������Ʈ �߰�
	public void putSprintOnList(Sprint newSprint) {
		sprintList.add(newSprint);
		sprintModel.addElement(newSprint);
		addProject.revalidate();
	}
	
	//����߰�
	public void putMemberOnList(A_Member member) {
		
		memberList.add(member.getId());
		memberModel.addElement(member.getId());
		//System.out.println(member.getId());
		addProject.revalidate();
	}
	
	//�������
	public void subtractMemberFromList(String member) {
		
		memberList.remove(member);
		memberModel.removeElement(member);
		addProject.revalidate();
	}
	
	//������Ʈ ����Ʈ ������Ʈ
	public void updateSprintList(Project projectUpdated) {
		
		sprintModel.clear();
		project = projectUpdated;
		sprintList = project.getSprintList();
		for(int i = 0; i < sprintList.size(); i++) {
			sprintModel.addElement(sprintList.get(i));
		}
		addProject.revalidate();
	}
	
	//�������Ʈ ������Ʈ
	public void updateMemberList(Project projectUpdated) {
		
		memberModel.clear();
		project = projectUpdated;
		memberList = project.getMemberList();
		for(int i = 0; i < memberList.size(); i++) {
			memberModel.addElement(memberList.get(i));
		}
		addProject.revalidate();
	}
	
	//������Ʈ ����
	public void modifyProject(String projectTitle, Date projectStartDay, Date projectEndDay) {
		mainPage.modifyProject(project, projectTitle, projectStartDay, projectEndDay, sprintList, memberList);
	}
	

	public Dialog getAddProject() {
		return addProject;
	}
//
	
	
	
	
	
//�̺�Ʈ
	//������Ʈ �߰� ��ư Ŭ���� �����ϴ� �̺�Ʈ
	//������Ʈ ���� ���̾�α׸� �ҷ��´�.
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == sprintAdd) {
			new A_AddSprint(mf, mainPage, this, project).getAddSprint().setVisible(true);
		}

		if(e.getSource() == changeAdmin) {
			String adminId = JOptionPane.showInputDialog("�����ڷ� ������ ȸ���� ���̵� �Է��ϼ���.");
			A_Member member = mainPage.findMember(adminId);
			if(member != null) {
				projectAdmin = member.getId();
				System.out.println("member�ҷ���");
				adminField.setText(projectAdmin);
				if(project != null) {
					Project projectUpdated = new ProjectManager().changeAdmin(project, projectAdmin);
					project = projectUpdated;

					addProject.revalidate();
				}

			}else {
				System.out.println("�ҷ��� ��������� �������� ����");
			}
		}
	}

	//�ʴ� +��ư Ŭ���� �����ϴ� �̺�Ʈ
	private class Add_person implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("�ʴ��� ȸ���� ���̵� �Է��ϼ���.");
			
			A_Member member = mainPage.findMember(id);
			
			if(member != null) {
				System.out.println("member�ҷ���");
				if(project == null) {
					putMemberOnList(member);
				}else {
					Project projectUpdated = new ProjectManager().addMember(project, member);
					updateMemberList(projectUpdated);
				}
			}else {
				System.out.println("�ҷ��� ��������� �������� ����");
			}
			
		}
	}
//	
	
	
	
}