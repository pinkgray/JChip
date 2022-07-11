package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import controller.ProjectManager;
import model.vo.Project;
import model.vo.Sprint;

public class B_SprintPanel extends JPanel implements ActionListener, MouseListener {


	private MainFrame mainFrame;
	private B_ProjectPage projectPage;
	private B_SprintPanel sprintPanel;
	
	private JButton newSprintButton;
	private String[] sprintInfo;
	private DefaultListModel sprintModel;
	private JList sprintJList;
	
	private Project selectedProject;
	
	public B_SprintPanel(B_ProjectPage projectPage, MainFrame mainFrame, Project selectedProject) {
		
		this.mainFrame = mainFrame;
		this.projectPage = projectPage;
		this.sprintPanel = this;
		
		this.selectedProject = selectedProject;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 20));
		this.setBackground(B_ProjectPage.BG_COLOR);
		
		
		
		//전체 폰트 스타일 적용
		//setUIFont (new javax.swing.plaf.FontUIResource("맑은 고딕", Font.PLAIN, 15));
		
		
		//스프린트생성버튼, 스프린트 리스트 패널
		JPanel sprintPanel = new JPanel();
		sprintPanel.setBackground(B_ProjectPage.BG_COLOR);
		
		sprintPanel.setLayout(new BorderLayout());
		
		JPanel newSprintPanel = new JPanel();
		newSprintPanel.setBackground(B_ProjectPage.BG_COLOR);
		newSprintPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		//스프린트 생성 버튼 (클릭시 팝업 떠야함)
		newSprintButton = new JButton(new ImageIcon("images/newSprint1.png"));
		ImageIcon newSprintButton2 = new ImageIcon("images/newSprint2.png");
		newSprintButton.setSize(150, 50);
		newSprintButton.setBorderPainted(false);
		newSprintButton.setFocusPainted(false);
		newSprintButton.setContentAreaFilled(false);
		newSprintButton.setRolloverIcon(newSprintButton2);
		
		
		//스프린트 생성 버튼에 이벤트 연결
		newSprintButton.addActionListener(this);
		
		newSprintPanel.add(newSprintButton, "North");

		sprintPanel.add(newSprintPanel, "North");
		
		
		//생성된 스프린트 리스트
		sprintModel = new DefaultListModel();
		sprintListUpdate();
		//스프린트 리스트 올릴 리스트
		sprintJList = new JList(sprintModel);
		sprintJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sprintJList.setToolTipText("더블클릭시 해당 스프린트로 이동");
		sprintJList.addMouseListener(this);
		
		
		
		
		sprintJList.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		//스크롤러에 리스트 올리기
		JScrollPane scroller = new JScrollPane(sprintJList);
		sprintPanel.add(scroller, "Center");
		
		
		this.add(sprintPanel, "Center");
		
		
		
		projectPage.add(this);
	}
//
	
	

//메소드	
	//리스트에 스프린트 추가
	public void addSprintOnList(String sprintTitle, Date sprintStartDay, Date sprintEndDay, String sprintDetail, String sprintToDo) {
		
		selectedProject = new ProjectManager().addNewSprint(selectedProject, sprintTitle, sprintStartDay, sprintEndDay, sprintDetail, sprintToDo);
		sprintListUpdate();
		sprintPanel.revalidate();
	}
	
	//스프린트 삭제
	public void deleteSprint(Sprint sprint) {
		
		selectedProject = new ProjectManager().deleteSprint(selectedProject, sprint);
		sprintListUpdate();
		sprintPanel.revalidate();
	}
	
	//스프린트 수정
	public void modifySprint(Sprint sprint, String sprintTitle, Date sprintStartDay, Date sprintEndDay, String sprintDetail, String sprintToDo) {
		
		selectedProject = new ProjectManager().modifySprint(selectedProject, sprint, sprintTitle, sprintStartDay, sprintEndDay, sprintDetail, sprintToDo);
		sprintListUpdate();
		sprintPanel.revalidate();
	}

	//스프린트 리스트 업데이트
	public void sprintListUpdate() {
		sprintModel.clear();
		//ArrayList<Sprint> sprintList = new ProjectManager().getSprintList();
		ArrayList<Sprint> sprintList = selectedProject.getSprintList();
		for(int i = 0; i < sprintList.size(); i++) {
			sprintModel.addElement(sprintList.get(i));
		}
		this.revalidate();
	}

	
	
	
	
//이벤트
	//스프린트 추가버튼 클릭시
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == newSprintButton) {
			new B_NewSprintPopUp(mainFrame, sprintPanel, null).getNewSprintPopUp().setVisible(true);;
		}
	}
	
	//spirntJList에서 마우스클릭시
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == sprintJList) {
			
			if(e.getButton() == 1) {
				if(e.getClickCount() == 2) {
					Sprint selectedSprint = (Sprint)sprintJList.getSelectedValue();
					projectPage.goToSprintPage(selectedProject, selectedSprint);
				}
				
			}
			
			if(e.getButton() == 3) {
				
				Sprint selectedSprint = (Sprint)sprintJList.getSelectedValue();
				System.out.println(selectedSprint);
				new B_NewSprintPopUp(mainFrame, sprintPanel, selectedSprint).getNewSprintPopUp().setVisible(true);
				
			}
			
			
		}
	}
//	
	
	
	
	
	
	//전체 폰트적용 메소드
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get (key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put (key, f);
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




	
	
	
	
	
	
}
