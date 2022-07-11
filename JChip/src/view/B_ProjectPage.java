package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.vo.A_Member;
import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;

//������Ʈ ���� ������
public class B_ProjectPage extends JPanel{

	private MainFrame mainFrame;
	public static final Color BG_COLOR = new Color(204, 245,255); 
	public static final Color POPUP_COLOR =new Color(66, 66, 66, 220);
	
	private A_MainPage mainPage;
	private B_ProjectPage projectPage;
	
	private Project selectedProject;
	private A_Member user;
	
	public B_ProjectPage(MainFrame mainFrame, A_MainPage mainPage, Project selectedProject, A_Member user) {
		
		this.mainFrame = mainFrame;
		this.projectPage = this;
		
		this.mainPage = mainPage;
		//������������ ����� ��ü
		this.selectedProject = selectedProject;
		this.user = user;
		
		this.setSize(1024, 768);
		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		GridBagConstraints gc = new GridBagConstraints();
		
		
		//��ܹ�
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.0;
		gc.weighty = 0.0;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(new B_HomeBarPanel(mainFrame, this, selectedProject, user), gc);
		
		//�����г�
		gc.weightx = 1;
		gc.gridwidth = 1;
		gridbag.setConstraints(new B_DdayPanel(this, selectedProject), gc);
		
		//������Ʈ�� �г�
		gc.weightx = 0.5;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		
		gridbag.setConstraints(new B_ProjectNamePanel(this), gc);
		
		
		
		//������Ʈ �г�
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridwidth = 1;
		gridbag.setConstraints(new B_SprintPanel(this, mainFrame, selectedProject), gc);
		
		
		//�޷��г�
		gc.weightx = 1;
		gc.gridwidth = 1;
		gridbag.setConstraints(new B_CalendarPanel(this, selectedProject), gc);
		
		//ȸ�Ƿ� �г�
		gc.weightx = 1;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(new B_MOMPanel(this, mainFrame, selectedProject), gc);
		
		this.setVisible(true);
		
		
		mainFrame.add(this);
	}
	
	public void goToMainPage() {
		ChangePanel.changePanel(mainFrame, this, mainPage);
	}
	
	public void goToSprintPage(Project selectedProject, Sprint selectedSprint) {
		ChangePanel.changePanel(mainFrame, this, new C_SprintMainPage(mainFrame, this, selectedProject, selectedSprint,user));
	}
	
	
	
	
}
