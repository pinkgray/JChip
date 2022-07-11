package model.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import model.vo.A_Member;
import model.vo.MOM;
import model.vo.Project;
import model.vo.Sprint;

public class ProjectDao {

	private String projectTitle;
	private ArrayList<Project> projectList;
	private ArrayList<Sprint> sprintList;
	private ArrayList<MOM> MOMList;
	
	private int index;

	//기본생성자는 로그인시에 불러옴. 모든 프로젝트 리스트 파일에서 읽어옴
	public ProjectDao() {
		
		projectList = new ArrayList();
		
		try (ObjectInputStream prjIn = new ObjectInputStream(
				new FileInputStream("project_list.dat"))) {
			//모든프로젝트 읽어옴
			while(true) {
				Project p = (Project)prjIn.readObject();
				projectList.add(p);
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (EOFException e) {
			System.out.println("프로젝트 파일 불러오기 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	
	//프로젝트명 넣어서 생성자 호출시, 모든 프로젝트 리스트/해당프로젝트의 스프린트리스트/회의록 리스트 
	//파일에서 불러옴
	public ProjectDao(String projectTitle) {
		
		this.projectTitle = projectTitle;
		projectList = new ArrayList();
		sprintList = new ArrayList();
		MOMList = new ArrayList();

		try (ObjectInputStream prjIn = new ObjectInputStream(
				new FileInputStream("project_list.dat"))) {
			//모든 프로젝트 불러옴
			while(true) {
				Project p = (Project)prjIn.readObject();
				projectList.add(p);
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (EOFException e) {
			System.out.println("프로젝트 파일 불러오기 완료");
			try(ObjectInputStream sprIn = new ObjectInputStream(
					new FileInputStream(projectTitle + "_sprint_list.dat"))) {
				
				//프로젝트 다 불러오고 EOFException발생시 sprintlist 불러옴
				while(true) {
					Sprint s = (Sprint)sprIn.readObject();
					sprintList.add(s);
				}
				
			} catch (FileNotFoundException e1) {
				System.out.println("스프린트 파일을 찾을 수 없습니다!");
			} catch (EOFException e1) {
				System.out.println("스프린트 파일 불러오기 완료");
				//모든 스프린트다 불러오고 EOFException발생시 회의록리스트 불러옴
				try (ObjectInputStream MOMIn = new ObjectInputStream(
						new FileInputStream(projectTitle + "_MOM_list.dat"))) {

					while (true) {
						MOM m = (MOM) MOMIn.readObject();
						MOMList.add(m);
					}

				} catch (FileNotFoundException e11) {
					System.out.println("회의록 파일을 찾을 수 없습니다!");
				} catch (EOFException e11) {
					System.out.println("회의록 파일 불러오기 완료");
				} catch (IOException e11) {
					e11.printStackTrace();
				} catch (ClassNotFoundException e11) {
					e11.printStackTrace();
				}

			} catch (IOException e1) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
//
	
	
//파일에 저장
	//프로젝트 리스트 파일에 저장
	public void saveProjects() {
		
		File projectfile = new File("project_list.dat");
		try {
			//파일이 이미 존재하면 삭제하고
			if(!projectfile.createNewFile()) {
				projectfile.delete();
				System.out.println("이전 프로젝트 파일 삭제함");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//다시 파일 생성해서 저장해줌
		try (ObjectOutputStream prjOut = new ObjectOutputStream(
				new FileOutputStream("project_list.dat"))) {

			System.out.println("프로젝트 파일 재생성");
			for(int i = 0; i < projectList.size(); i++) {
				prjOut.writeObject(projectList.get(i));
				prjOut.flush();
			}
			System.out.println("새로운 파일에 프로젝트 작성 완료");

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//스프린트 리스트 파일에 저장
	public void saveSprints() {
		
		File sprintfile = new File(projectTitle + "_sprint_list.dat");
		//파일이 이미 존재하면 삭제하고
		try {
			if(!sprintfile.createNewFile()) {
				sprintfile.delete();
				System.out.println("기존 스프린트 파일 삭제함");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//다시 파일 생성해서 저장해줌
		try (ObjectOutputStream sprOut = new ObjectOutputStream(
				new FileOutputStream(projectTitle+ "_sprint_list.dat"))) {

			System.out.println("스프린트 파일 재생성함");
			for(int i = 0; i < sprintList.size(); i++) {
				sprOut.writeObject(sprintList.get(i));
				sprOut.flush();
			}
			System.out.println("새로운 파일에 스프린트 저장 완료");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//회의록 리스트 파일에 저장
	public void saveMOMList() {

		File MOMFile = new File(projectTitle + "_MOM_list.dat");
		//파일이 존재하면 삭제하고
		try {
			if (!MOMFile.createNewFile()) {
				MOMFile.delete();
				System.out.println("기존 회의록 파일 삭제함");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//다시 파일생성해서 저장해줌
		try (ObjectOutputStream MOMOut = new ObjectOutputStream(
				new FileOutputStream(projectTitle + "_MOM_list.dat"))) {

			System.out.println("회의록 파일 재생성함");
			for (int i = 0; i < MOMList.size(); i++) {
				MOMOut.writeObject(MOMList.get(i));
				MOMOut.flush();
			}
			System.out.println("새로운 파일에 회의록 저장 완료");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	
	
	
	
	
	
//프로젝트 생성
	//새로 프로젝트 생성시 프로젝트 리스트에 올려주고 파일에 저장해줌
	//새로 만들어진 프로젝트의 스프린트는 따로 스프린트 리스트에 올려서 파일에 저장해줌
	public void makeProject(Project project) {
		projectList.add(project);
		sprintList.addAll(project.getSprintList());
		saveSprints();
		saveProjects();
	}
//	
	
	
	
	
//정보찾기	
	//프로젝트 리스트에서 현재 프로젝트 정보 찾아서 인덱스값 저장
	//찾은 프로젝트 리턴
	public Project findProject() {

		Project selectedProject = null;
		for(int i = 0; i < projectList.size(); i++) {
			Project project = (Project)projectList.get(i);
			if(project.getProjectTitle().equals(projectTitle)) {
				System.out.println("일치하는 프로젝트 찾음!");
				index = i;
				selectedProject = project;
				selectedProject.setSprintList(sprintList);
				selectedProject.setMOMList(MOMList);
				break;
			}
		}
		if(selectedProject != null) {
			return selectedProject;
		}else {
			System.out.println("!선택하신 프로젝트가 존재하지 않습니다!");
			return null;
		}
	}
	
	//스프린트리스트에서 현재 스프린트 정보 찾기. 인덱스 저장
	public Sprint findSprint(Sprint sprint) {
		
		for(int i = 0;  i < sprintList.size(); i++) {
			Sprint s = (Sprint)sprintList.get(i);
			if(s.toString().equals(sprint.toString())) {
				System.out.println("일치하는 스프린트 찾음");
				index = i;
				return s;
			}
		}
		
		System.out.println("스프린트가 존재하지 않습니다.");
		return null;
	}
	
	//회의록리스트에서 현재 회의록 정보 찾기. 인덱스 저장
	public MOM findMOM(MOM mom) {
		
		for(int i = 0;  i < MOMList.size(); i++) {
			MOM m = (MOM)MOMList.get(i);
			if(m.toString().equals(mom.toString())) {
				System.out.println("일치하는 회의록 찾음");
				index = i;
				return m;
			}
		}
		System.out.println("회의록이 존재하지 않습니다.");
		return null;
	}
	
	//프로젝트 리스트 리턴
	public ArrayList<Project> getProjectList() {
		return projectList;
	}
	
	//스프린트리스트 리턴
	public ArrayList<Sprint> getSprintList() {
		return sprintList;
	}
	
	//회의록리스트 리턴
	public ArrayList<MOM> getMOMList() {
		return MOMList;
	}
//

	
	
	
	
	
	
//프로젝트삭제, 멤버관리(관리자권한)
	//프로젝트 수정시 현재 프로젝트 찾아서 받아온 프로젝트로 바꾸고
	//해당 인덱스에 프로젝트 다시 저장 후, 파일에 저장
	//스프린트리스트는 따로 받아온프로젝트의 스프린트 리스트로 업데이트 후 파일에 저장
	public void modifyProject(Project project) {
		Project projectToBeChanged = findProject();
		projectToBeChanged = project;
		projectTitle = projectToBeChanged.getProjectTitle();
		projectList.set(index, projectToBeChanged);
		sprintList.clear();
		sprintList.addAll(project.getSprintList());
		saveSprints();
		saveProjects();
		
	}
	
	//현재프로젝트 프로젝트리스트에서 찾아서 index이용해서 삭제하기
	//삭제하고 나서 다시 리스트 파일에 저장
	public void deleteProject() {
		findProject();
		projectList.remove(index);
		saveProjects();
	}
	
	//현재 프로젝트 찾아서 프로젝트의 멤버리스트 받아온리스트로 업데이트
	//해당 인덱스에 다시 업데이트된 프로젝트 저장후 파일에 저장
	public void addMember(ArrayList<String> memberList) {
		Project projectToBeChanged = findProject();
		projectToBeChanged.setMemberList(memberList);
		projectList.set(index, projectToBeChanged);
		saveProjects();
		
	}
	
	//현재 프로젝트 찾아서 프로젝트의 어드민 받아온어드민으로 바꾸기
	//해당 인덱스에 프로젝트 다시 저장 후 파일에 저장
	public void changeAdmin(String projectAdmin) {
		Project projectToBeChanged = findProject();
		projectToBeChanged.setProjectAdmin(projectAdmin);
		projectList.set(index, projectToBeChanged);
		saveProjects();
	}
//	
	
	
	
	
	
//정보 수정	
	//스프린트 수정시 해당 스프린트 찾아서 받아온 스프린트로 바꿔주고
	//스프린트리스트에 저장후 파일에 저장
	public ArrayList<Sprint> modifySprint(Project project, Sprint sprint, 
			Sprint updatedSprint) {
		
		Sprint sprintToBeChanged = findSprint(sprint);
		if(sprintToBeChanged != null) {
			sprintToBeChanged = updatedSprint;
			sprintList.set(index, sprintToBeChanged);
		}
		
		saveSprints();
		return sprintList;
	}

	//회의록 수정시 똑같음
	public ArrayList<MOM> modifyMOM(Project project, MOM mom, MOM updatedMOM) {

		MOM momToBeChanged = findMOM(mom);
		if(momToBeChanged != null) {
			momToBeChanged = updatedMOM;
			MOMList.set(index, momToBeChanged);
		}
		saveMOMList();
		return MOMList;
	}

	//현재 저장되어있는 스프린트 리스트 클리어하고 받아온 스프린트 리스트로 업데이트 후 파일에 저장
	public void addSprint(ArrayList<Sprint> sprintList) {
		this.sprintList.clear();
		this.sprintList.addAll(sprintList);
		saveSprints();
	}
	//회의록
	public void addMOM(ArrayList<MOM> MOMList) {
		this.MOMList.clear();
		this.MOMList.addAll(MOMList);
		saveMOMList();
	}
//
	
	
	
	
	

}
