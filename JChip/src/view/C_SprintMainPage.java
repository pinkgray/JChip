package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;

public class C_SprintMainPage extends JPanel{
	private MainFrame mf;
	private C_SprintMainPage sprintPage;
	//�ʵ��߰�(��)
	private B_ProjectPage projectPage;
	private Project selectedProject;
	private Sprint selectedSprint;
	private A_Member user;
	
	/*private C_ProgressPanel p;
	
	private C_DonePanel d;
	private C_OpenPanel o;*/
	//�Ű���������(��)
	
	
	public C_SprintMainPage(MainFrame mf, B_ProjectPage projectPage, Project selectedProject, Sprint selectedSprint,A_Member user) {
		this.mf = mf;
		this.sprintPage = sprintPage;
		
		//(��)
		this.projectPage = projectPage;
		this.selectedProject = selectedProject;
		this.selectedSprint = selectedSprint;
		this.user = user;
		
		this.setSize(1024,768);
		//this.setBackground(Color.WHITE);
		GridBagLayout gridBag = new GridBagLayout();
		this.setLayout(gridBag);
		GridBagConstraints gc = new GridBagConstraints();
		
		//�޴��� == ��ܹ�
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.0;
		gc.weighty = 0.0;
		gc.gridwidth= GridBagConstraints.REMAINDER;
		//gc.gridheight = 1;
		//�Ű���������(��)
		gridBag.setConstraints(new C_MenuBarPanel(this, this.mf, this.selectedProject, this.selectedSprint,user ), gc); 
	
		//updateWorkStatus();
		
		//OPEN�г�
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridwidth = 1;
		//gc.gridheight = 1;
		gridBag.setConstraints(new C_OpenPanel(this, this.mf, this.projectPage, this.selectedProject,this.selectedSprint, this.user), gc);
		
		//IN Progress �г�
		gc.weightx = 1;
		//gc.weighty = 0.9;
		gc.gridwidth = 1;
		//gc.gridheight = 1;
		gridBag.setConstraints(new C_ProgressPanel(this, this.mf, this.projectPage, this.selectedProject,this.selectedSprint, this.user), gc);
		
		//Done �г�
		gc.weightx = 1;
		//gc.weighty = 0.9;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		//gc.gridwidth = 1;
		gridBag.setConstraints(new C_DonePanel(this, this.mf,this.projectPage, this.selectedProject,this.selectedSprint, this.user), gc);
		
		this.setVisible(true);
				
		mf.add(this);
		
	}
	
	/*public void updateWorkStatus() {
		o = new C_OpenPanel(this, this.mf,this.selectedProject,this.selectedSprint);
		p = new C_ProgressPanel(this, this.mf,this.selectedProject,this.selectedSprint);
		d = new C_DonePanel(this, this.mf,this.selectedProject,this.selectedSprint);
		this.revalidate();
	}*/
	
	public void goToMainPage() {
		
		ChangePanel.changePanel(mf, this, new A_MainPage(mf,user));
	}
	
	public void goToProjectPage() {
		ChangePanel.changePanel(mf, this, projectPage);
	}
	
	
	public void goToLoginPage(C_SprintMainPage sprintPage) {
		this.sprintPage=sprintPage;
		System.out.println("�α׾ƿ� �޼ҵ� ������Ʈ�������� ����");
		ChangePanel.changePanel(mf, sprintPage, new A_LoginPage(mf));
	}
}
