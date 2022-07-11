package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.dao.ProjectDao;
import model.vo.A_Member;
import model.vo.MOM;
import model.vo.Project;
import model.vo.Sprint;

public class ProjectManager {

	private ProjectDao pdao;
	private ArrayList<Project> projectList = new ArrayList<Project>();
	private ArrayList<Sprint> sprintList = new ArrayList<Sprint>(); 
	private ArrayList<String> memberList = new ArrayList<String>();
	private ArrayList<MOM> MOMList = new ArrayList<MOM>(); 
	
	public ProjectManager() {}
	

	
//������Ʈ ����	
	//������Ʈ ������ ������Ʈ ����, ����Ʈ�� �߰�
	public Sprint makeNewSprint(String sprintTitle, Date sprintListtartDay, 
			Date sprintEndDay, String sprintDetail, String sprintToDo) {
		
		Sprint newSprint = new Sprint(sprintTitle, sprintListtartDay, sprintEndDay, sprintDetail, sprintToDo);
		sprintList.add(newSprint);
		return newSprint;
	}
	
	//������Ʈ ����
	public void makeNewProject(String projectTitle, Date projectStartDay, Date projectEndDay, 
			ArrayList<Sprint> sprintList, String projectAdmin, ArrayList<String> memberList) {
		
		Project newProject = new Project(projectTitle, projectStartDay, projectEndDay, sprintList, 
				projectAdmin, memberList, MOMList);
		pdao = new ProjectDao(projectTitle);
		pdao.makeProject(newProject);
	}
//
	
	
	
	
	
//������Ʈ ã��
	//��� ������Ʈ ����Ʈ get (�α��ν� ����)
	public ArrayList<Project> getProjectList() {
		
		pdao = new ProjectDao();
		ArrayList<Project> projectList = pdao.getProjectList();
		if(projectList != null) {
			this.projectList = projectList;
		}
		return this.projectList;
	}
	
	//������Ʈ ��ư Ŭ���� �ش� ������Ʈ ã��
	public Project getProject(String titleSelected) {
		
		pdao = new ProjectDao(titleSelected);
		//getSprintList();
		return pdao.findProject();
	}
//	
	
	
	
	
	

//������Ʈ ����(�����ڱ���)
	//������� ������Ʈ�� ��� �߰��ϱ�(������ ����)
	public Project addMember(Project project, A_Member member) {

		memberList = project.getMemberList();
		memberList.add(member.getId());
		project.setMemberList(memberList);
		if(project != null) {
			pdao = new ProjectDao(project.getProjectTitle());
			pdao.addMember(memberList);
		}
		
		return project;
	}
	
	//������� ������Ʈ���� ��� �����ϱ�(������ ����)
	public Project deleteMember(Project project, String member) {
		
		memberList = project.getMemberList();
		memberList.remove(member);
		project.setMemberList(memberList);
		if(project != null) {
			pdao = new ProjectDao(project.getProjectTitle());
			pdao.addMember(memberList);
		}
		return project;
	}
	
	//������� ������Ʈ���� ������ �ٲٱ�(�����ڱ���)
	public Project changeAdmin(Project project, String projectAdmin) {
	
		project.setProjectAdmin(projectAdmin);
		pdao = new ProjectDao(project.getProjectTitle());
		pdao.changeAdmin(projectAdmin);
		return project;
	}
	
	//������Ʈ ����(������Ʈ ����) �����ϱ�(������ ����)
	public void modifyProject(Project project, String projectTitle, Date projectStartDay, Date projectEndDay, ArrayList<Sprint> sprintList, ArrayList<String> memberList) {
		
		pdao = new ProjectDao(project.getProjectTitle());
		project.setProjectTitle(projectTitle);
		project.setProjectStartDay(projectStartDay);
		project.setProjectEndDay(projectEndDay);
		project.setSprintList(sprintList);
		project.setMemberList(memberList);
		pdao.modifyProject(project);
		
	}
	
	//������Ʈ �����ϱ�(������ ����)
	public void deleteProject(Project project) {

		pdao = new ProjectDao(project.getProjectTitle());
		pdao.deleteProject();
	}
//



	
	
	
//������Ʈ ������ �ȿ� ������Ʈ, ȸ�Ƿ� ����/����/����
	//������Ʈ ����Ʈ �ҷ�����
	public ArrayList<Sprint> getSprintList() {

		sprintList = pdao.getSprintList();
		return sprintList;
	}

	
	//ȸ�Ƿ� ����Ʈ �ҷ�����
	public ArrayList<MOM> getMOMList() {

		MOMList = pdao.getMOMList();
		return MOMList;
	}

	
	//������� ������Ʈ�� �� ������Ʈ �߰��ϱ�
	public Project addNewSprint(Project project, String sprintTitle, Date sprintListtartDay, 
			Date sprintEndDay, String sprintDetail, String sprintToDo) {

		sprintList = project.getSprintList();
		Sprint newSprint = new Sprint(sprintTitle, sprintListtartDay, sprintEndDay, sprintDetail, sprintToDo);
		sprintList.add(newSprint);
		project.setSprintList(sprintList);

		if(project != null) {
			pdao = new ProjectDao(project.getProjectTitle());
			pdao.addSprint(sprintList);
		}
		return project;
	}
	

	//������� ������Ʈ�� �� ȸ�Ƿ� �߰��ϱ�
	public Project addNewMOM(Project project, String MOMTitle, String MOMWriter, Date MOMDay, 
			String MOMPerson, String MOMDescription) {

		if(project.getMOMList() != null) {
			MOMList = project.getMOMList();
		}
		MOM newMOM = new MOM( MOMTitle, MOMWriter, MOMDay, MOMPerson, MOMDescription);
		MOMList.add(newMOM);
		project.setMOMList(MOMList);
		pdao = new ProjectDao(project.getProjectTitle());
		pdao.addMOM(MOMList);
		return project;
	}


	//������Ʈ ���������� ������ ������Ʈ �����ϱ�
	public Project modifySprint(Project project, Sprint sprint, String sprintTitle, Date sprintListtartDay, 
			Date sprintEndDay, String sprintDetail, String sprintToDo) {

		//System.out.println("old : " + sprint);
		pdao = new ProjectDao(project.getProjectTitle());
		Sprint updatedSprint = new Sprint(sprintTitle, sprintListtartDay, sprintEndDay, sprintDetail, sprintToDo);
		//System.out.println("new : " + updatedSprint);
		sprintList = pdao.modifySprint(project, sprint, updatedSprint);
		project.setSprintList(sprintList);
		return project;
	}

	//������Ʈ ���������� ������ ȸ�Ƿ� �����ϱ�
	public Project modifyMOM(Project project, MOM mom, String MOMTitle, String MOMWriter, Date MOMDay, String MOMPerson, String MOMDescription) {

		pdao = new ProjectDao(project.getProjectTitle());
		MOM updatedMOM = new MOM(MOMTitle, MOMWriter, MOMDay, MOMPerson, MOMDescription);
		MOMList = pdao.modifyMOM(project, mom, updatedMOM);
		project.setMOMList(MOMList);

		return project;
	}

	//������Ʈ �����ϱ�
	public Project deleteSprint(Project project, Sprint sprint) {

		sprintList = project.getSprintList();
		sprintList.remove(sprint);
		project.setSprintList(sprintList);
		if(project != null) {
			pdao = new ProjectDao(project.getProjectTitle());
			pdao.addSprint(sprintList);
		}
		return project;
	}

	//ȸ�Ƿ� �����ϱ�
	public Project deleteMOM(Project project, MOM mom) {

		MOMList = project.getMOMList();
		MOMList.remove(mom);
		project.setMOMList(MOMList);
		if(project != null) {
			pdao = new ProjectDao(project.getProjectTitle());
			pdao.addMOM(MOMList);
		}
		return project;
	}


	//Ķ������ �Է��� ���� ������Ʈ�� �����ϱ�
	public void saveCalendar(Project project, HashMap calendarMap) {
		project.setCalendarMap(calendarMap);
		pdao = new ProjectDao(project.getProjectTitle());
		pdao.modifyProject(project);
	}
//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*//�� ȸ�Ƿϸ����
	public MOM makeNewMOM(String mOMTitle, String mOMWriter, Date mOMDay, String mOMPerson,
			String mOMDescription) {

		MOM newMOM = new MOM(mOMTitle, mOMWriter, mOMDay, mOMPerson, mOMDescription);
		MOMList.add(newMOM);
		return newMOM;
	}*/
	
	/*public void addSprintOnProject(Project project) {
	project.setSprintList(sprintList);
}*/
	
	/*///////////
	public void addMOMOnProject(Project project) {
		project.setMOMList(MOMList);
	}
	///////////
*/
	
}
