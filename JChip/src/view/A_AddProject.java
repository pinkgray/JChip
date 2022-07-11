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

	private MainFrame mf;           //AddProject객체를 필드에다가 만들어놓음   //mf를 또 쓰려고 필드에 만들어줌.
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
		
		addProject = new Dialog(mf, "새 프로젝트 만들기");       


		// 팝업위치 조정(화면 가운데)
		addProject.setSize(515, 700);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (addProject.getWidth() / 2);
		int yPos = (dim.height / 2) - (addProject.getHeight() / 2);
		addProject.setLocation(xPos, yPos);

		addProject.setUndecorated(true);
		addProject.setBackground(new Color(0, 0, 0, 0)); 
		addProject.setLayout(null);               



		JLabel label = new JLabel("프로젝트 추가");
		label.setFont(new Font("맑은 고딕",Font.BOLD, 30));
		label.setForeground(Color.WHITE);
		label.setLocation(10,10);            
		label.setSize(200,100);
		addProject.add(label);

		JTextField proName = new JTextField("프로젝트명",20);
		proName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		proName.setLocation(10,85);
		proName.setSize(485,50);
		if(project != null) {
			proName.setText(project.getProjectTitle());
		}
		addProject.add(proName);

		//textField에 마우스 클릭시 내용지워지고 빈화면으로 바뀜
		proName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//아무것도 입력되지 않은 상태에서만 빈칸으로 만들기
				if (nameCtn == 0) {
					proName.setText("");
				}
			}
		});
		
		
		
		//시작일
		JLabel start = new JLabel("시작일");
		start.setLocation(30, 155);
		start.setSize(70, 40);
		start.setForeground(Color.WHITE);
		start.setFont(new Font("맑은 고딕", Font.BOLD, 20));
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
		startDay.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		if(project != null) {
			startDay.setDate(project.getProjectStartDay());
		}
		addProject.add(startDay);


		JLabel middle = new JLabel("~");
		middle.setLocation(250,155);
		middle.setForeground(Color.WHITE);
		middle.setSize(300,40);
		middle.setFont(new Font("맑은 고딕",Font.BOLD, 30));
		addProject.add(middle);

		
		//종료일
		JLabel end = new JLabel("종료일");
		end.setLocation(290, 155);
		end.setSize(80, 40);
		end.setForeground(Color.WHITE);
		end.setFont(new Font("맑은 고딕", Font.BOLD, 20));
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
		endDay.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		if(project != null) {
			endDay.setDate(project.getProjectEndDay());
		}
		addProject.add(endDay);


		
		

		//두번째 패널
		JPanel projectPanel = new JPanel();
		projectPanel.setLayout(null);
		projectPanel.setSize(515,630);


		JLabel sprintLabel = new JLabel("스프린트 추가");
		sprintLabel.setLocation(15,180);
		sprintLabel.setSize(400, 100);
		sprintLabel.setForeground(Color.WHITE);
		sprintLabel.setFont(new Font("맑은 고딕",Font.BOLD, 20));

		projectPanel.add(sprintLabel);



		//스프린트 추가 버튼
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
		//스프린트 버튼 클릭시 스프린트 창 나오기
		sprintAdd.addActionListener(this);
		projectPanel.add(sprintAdd);
		addProject.add(projectPanel);


		//생성한 스프린트 리스트
		sprintModel = new DefaultListModel();
		if(project != null) {
			
			sprintList = project.getSprintList();
			for(int i = 0; i< sprintList.size(); i++) {
				sprintModel.addElement(sprintList.get(i));
			}
		}
		sprintJList = new JList(sprintModel);
		JScrollPane pane = new JScrollPane(sprintJList);   
		pane.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		pane.setLocation(10,245);
		pane.setSize(480,100);

		projectPanel.add(pane);
		projectPanel.setBackground(new Color(66, 66, 66, 220));
		
		
		

		//관리자
		JLabel admin = new JLabel("관리자");
		admin.setFont(new Font("맑은 고딕",Font.BOLD, 20));
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
		
		
		
		
		
		//초대

		JLabel invite = new JLabel("초대");
		invite.setFont(new Font("맑은 고딕",Font.BOLD, 20));
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


		//초대한 사람들 리스트
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

		//멤버삭제기능
		memberJList.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == 3) {
					String member = (String)memberJList.getSelectedValue();
					int answer = JOptionPane.showConfirmDialog(null, "선택하신 멤버를 삭제하시겠습니까?");
					
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
		
		
		//삭제버튼
		JButton deleteBtn = new JButton(new ImageIcon("images/delete1.png"));
		ImageIcon deleteBtn2 = new ImageIcon("images/delete2.png");
		deleteBtn.setBorderPainted(false); 
		deleteBtn.setFocusPainted(false); 
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setRolloverIcon(deleteBtn2);
		deleteBtn.setLocation(10,575);
		deleteBtn.setSize(100,33);
		projectPanel.add(deleteBtn);
		
		//삭제버튼클릭시 이벤트
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (project != null) {
					int answer = JOptionPane.showConfirmDialog(null, "프로젝트를 삭제하시겠습니까?");
					if(answer == 0) {
						mainPage.deleteProject(project);
					}
				}
				addProject.dispose();
			}
		});
		

		//취소버튼
		JButton cancelBtn  = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelbtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false); 
		cancelBtn.setFocusPainted(false); 
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelbtn2);

		cancelBtn.setLocation(282,575);
		cancelBtn.setSize(100,33);
		projectPanel.add(cancelBtn);


		//취소 버튼 클릭시 이벤트
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addProject.dispose();

			}
		});


		//확인버튼
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okbtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false); 
		okBtn.setFocusPainted(false); 
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okbtn2);
		okBtn.setLocation(392,575);
		okBtn.setSize(100,33);
		projectPanel.add(okBtn);
		
		
		//확인버튼 클릭시 이벤트
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
	
	

	
//메소드
	//스프린트 추가
	public void putSprintOnList(Sprint newSprint) {
		sprintList.add(newSprint);
		sprintModel.addElement(newSprint);
		addProject.revalidate();
	}
	
	//멤버추가
	public void putMemberOnList(A_Member member) {
		
		memberList.add(member.getId());
		memberModel.addElement(member.getId());
		//System.out.println(member.getId());
		addProject.revalidate();
	}
	
	//멤버삭제
	public void subtractMemberFromList(String member) {
		
		memberList.remove(member);
		memberModel.removeElement(member);
		addProject.revalidate();
	}
	
	//스프린트 리스트 업데이트
	public void updateSprintList(Project projectUpdated) {
		
		sprintModel.clear();
		project = projectUpdated;
		sprintList = project.getSprintList();
		for(int i = 0; i < sprintList.size(); i++) {
			sprintModel.addElement(sprintList.get(i));
		}
		addProject.revalidate();
	}
	
	//멤버리스트 업데이트
	public void updateMemberList(Project projectUpdated) {
		
		memberModel.clear();
		project = projectUpdated;
		memberList = project.getMemberList();
		for(int i = 0; i < memberList.size(); i++) {
			memberModel.addElement(memberList.get(i));
		}
		addProject.revalidate();
	}
	
	//프로젝트 수정
	public void modifyProject(String projectTitle, Date projectStartDay, Date projectEndDay) {
		mainPage.modifyProject(project, projectTitle, projectStartDay, projectEndDay, sprintList, memberList);
	}
	

	public Dialog getAddProject() {
		return addProject;
	}
//
	
	
	
	
	
//이벤트
	//스프린트 추가 버튼 클릭시 동작하는 이벤트
	//스프린트 생성 다이얼로그를 불러온다.
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == sprintAdd) {
			new A_AddSprint(mf, mainPage, this, project).getAddSprint().setVisible(true);
		}

		if(e.getSource() == changeAdmin) {
			String adminId = JOptionPane.showInputDialog("관리자로 설정할 회원의 아이디를 입력하세요.");
			A_Member member = mainPage.findMember(adminId);
			if(member != null) {
				projectAdmin = member.getId();
				System.out.println("member불러옴");
				adminField.setText(projectAdmin);
				if(project != null) {
					Project projectUpdated = new ProjectManager().changeAdmin(project, projectAdmin);
					project = projectUpdated;

					addProject.revalidate();
				}

			}else {
				System.out.println("불러올 멤버정보가 존재하지 않음");
			}
		}
	}

	//초대 +버튼 클릭시 동작하는 이벤트
	private class Add_person implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("초대할 회원의 아이디를 입력하세요.");
			
			A_Member member = mainPage.findMember(id);
			
			if(member != null) {
				System.out.println("member불러옴");
				if(project == null) {
					putMemberOnList(member);
				}else {
					Project projectUpdated = new ProjectManager().addMember(project, member);
					updateMemberList(projectUpdated);
				}
			}else {
				System.out.println("불러올 멤버정보가 존재하지 않음");
			}
			
		}
	}
//	
	
	
	
}