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
	

	
//프로젝트 생성	
	//프로젝트 생성전 스프린트 생성, 리스트에 추가
	public Sprint makeNewSprint(String sprintTitle, Date sprintListtartDay, 
			Date sprintEndDay, String sprintDetail, String sprintToDo) {
		
		Sprint newSprint = new Sprint(sprintTitle, sprintListtartDay, sprintEndDay, sprintDetail, sprintToDo);
		sprintList.add(newSprint);
		return newSprint;
	}
	
	//프로젝트 생성
	public void makeNewProject(String projectTitle, Date projectStartDay, Date projectEndDay, 
			ArrayList<Sprint> sprintList, String projectAdmin, ArrayList<String> memberList) {
		
		Project newProject = new Project(projectTitle, projectStartDay, projectEndDay, sprintList, 
				projectAdmin, memberList, MOMList);
		pdao = new ProjectDao(projectTitle);
		pdao.makeProject(newProject);
	}
//
	
	
	
	
	
//프로젝트 찾기
	//모든 프로젝트 리스트 get (로그인시 실행)
	public ArrayList<Project> getProjectList() {
		
		pdao = new ProjectDao();
		ArrayList<Project> projectList = pdao.getProjectList();
		if(projectList != null) {
			this.projectList = projectList;
		}
		return this.projectList;
	}
	
	//프로젝트 버튼 클릭시 해당 프로젝트 찾기
	public Project getProject(String titleSelected) {
		
		pdao = new ProjectDao(titleSelected);
		//getSprintList();
		return pdao.findProject();
	}
//	
	
	
	
	
	

//프로젝트 수정(관리자권한)
	//만들어진 프로젝트에 멤버 추가하기(관리자 권한)
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
	
	//만들어진 프로젝트에서 멤버 삭제하기(관리자 권한)
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
	
	//만들어진 프로젝트에서 관리자 바꾸기(관리자권한)
	public Project changeAdmin(Project project, String projectAdmin) {
	
		project.setProjectAdmin(projectAdmin);
		pdao = new ProjectDao(project.getProjectTitle());
		pdao.changeAdmin(projectAdmin);
		return project;
	}
	
	//프로젝트 정보(스프린트 포함) 수정하기(관리자 권한)
	public void modifyProject(Project project, String projectTitle, Date projectStartDay, Date projectEndDay, ArrayList<Sprint> sprintList, ArrayList<String> memberList) {
		
		pdao = new ProjectDao(project.getProjectTitle());
		project.setProjectTitle(projectTitle);
		project.setProjectStartDay(projectStartDay);
		project.setProjectEndDay(projectEndDay);
		project.setSprintList(sprintList);
		project.setMemberList(memberList);
		pdao.modifyProject(project);
		
	}
	
	//프로젝트 삭제하기(관리자 권한)
	public void deleteProject(Project project) {

		pdao = new ProjectDao(project.getProjectTitle());
		pdao.deleteProject();
	}
//



	
	
	
//프로젝트 페이지 안에 스프린트, 회의록 생성/수정/삭제
	//스프린트 리스트 불러오기
	public ArrayList<Sprint> getSprintList() {

		sprintList = pdao.getSprintList();
		return sprintList;
	}

	
	//회의록 리스트 불러오기
	public ArrayList<MOM> getMOMList() {

		MOMList = pdao.getMOMList();
		return MOMList;
	}

	
	//만들어진 프로젝트에 새 스프린트 추가하기
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
	

	//만들어진 프로젝트에 새 회의록 추가하기
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


	//프로젝트 페이지에서 생성한 스프린트 수정하기
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

	//프로젝트 페이지에서 생성한 회의록 수정하기
	public Project modifyMOM(Project project, MOM mom, String MOMTitle, String MOMWriter, Date MOMDay, String MOMPerson, String MOMDescription) {

		pdao = new ProjectDao(project.getProjectTitle());
		MOM updatedMOM = new MOM(MOMTitle, MOMWriter, MOMDay, MOMPerson, MOMDescription);
		MOMList = pdao.modifyMOM(project, mom, updatedMOM);
		project.setMOMList(MOMList);

		return project;
	}

	//스프린트 삭제하기
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

	//회의록 삭제하기
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


	//캘린더에 입력한 정보 프로젝트에 저장하기
	public void saveCalendar(Project project, HashMap calendarMap) {
		project.setCalendarMap(calendarMap);
		pdao = new ProjectDao(project.getProjectTitle());
		pdao.modifyProject(project);
	}
//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*//새 회의록만들기
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
