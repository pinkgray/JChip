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

	//�⺻�����ڴ� �α��νÿ� �ҷ���. ��� ������Ʈ ����Ʈ ���Ͽ��� �о��
	public ProjectDao() {
		
		projectList = new ArrayList();
		
		try (ObjectInputStream prjIn = new ObjectInputStream(
				new FileInputStream("project_list.dat"))) {
			//���������Ʈ �о��
			while(true) {
				Project p = (Project)prjIn.readObject();
				projectList.add(p);
			}

		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
		} catch (EOFException e) {
			System.out.println("������Ʈ ���� �ҷ����� �Ϸ�");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	
	//������Ʈ�� �־ ������ ȣ���, ��� ������Ʈ ����Ʈ/�ش�������Ʈ�� ������Ʈ����Ʈ/ȸ�Ƿ� ����Ʈ 
	//���Ͽ��� �ҷ���
	public ProjectDao(String projectTitle) {
		
		this.projectTitle = projectTitle;
		projectList = new ArrayList();
		sprintList = new ArrayList();
		MOMList = new ArrayList();

		try (ObjectInputStream prjIn = new ObjectInputStream(
				new FileInputStream("project_list.dat"))) {
			//��� ������Ʈ �ҷ���
			while(true) {
				Project p = (Project)prjIn.readObject();
				projectList.add(p);
			}

		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
		} catch (EOFException e) {
			System.out.println("������Ʈ ���� �ҷ����� �Ϸ�");
			try(ObjectInputStream sprIn = new ObjectInputStream(
					new FileInputStream(projectTitle + "_sprint_list.dat"))) {
				
				//������Ʈ �� �ҷ����� EOFException�߻��� sprintlist �ҷ���
				while(true) {
					Sprint s = (Sprint)sprIn.readObject();
					sprintList.add(s);
				}
				
			} catch (FileNotFoundException e1) {
				System.out.println("������Ʈ ������ ã�� �� �����ϴ�!");
			} catch (EOFException e1) {
				System.out.println("������Ʈ ���� �ҷ����� �Ϸ�");
				//��� ������Ʈ�� �ҷ����� EOFException�߻��� ȸ�Ƿϸ���Ʈ �ҷ���
				try (ObjectInputStream MOMIn = new ObjectInputStream(
						new FileInputStream(projectTitle + "_MOM_list.dat"))) {

					while (true) {
						MOM m = (MOM) MOMIn.readObject();
						MOMList.add(m);
					}

				} catch (FileNotFoundException e11) {
					System.out.println("ȸ�Ƿ� ������ ã�� �� �����ϴ�!");
				} catch (EOFException e11) {
					System.out.println("ȸ�Ƿ� ���� �ҷ����� �Ϸ�");
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
	
	
//���Ͽ� ����
	//������Ʈ ����Ʈ ���Ͽ� ����
	public void saveProjects() {
		
		File projectfile = new File("project_list.dat");
		try {
			//������ �̹� �����ϸ� �����ϰ�
			if(!projectfile.createNewFile()) {
				projectfile.delete();
				System.out.println("���� ������Ʈ ���� ������");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//�ٽ� ���� �����ؼ� ��������
		try (ObjectOutputStream prjOut = new ObjectOutputStream(
				new FileOutputStream("project_list.dat"))) {

			System.out.println("������Ʈ ���� �����");
			for(int i = 0; i < projectList.size(); i++) {
				prjOut.writeObject(projectList.get(i));
				prjOut.flush();
			}
			System.out.println("���ο� ���Ͽ� ������Ʈ �ۼ� �Ϸ�");

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//������Ʈ ����Ʈ ���Ͽ� ����
	public void saveSprints() {
		
		File sprintfile = new File(projectTitle + "_sprint_list.dat");
		//������ �̹� �����ϸ� �����ϰ�
		try {
			if(!sprintfile.createNewFile()) {
				sprintfile.delete();
				System.out.println("���� ������Ʈ ���� ������");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//�ٽ� ���� �����ؼ� ��������
		try (ObjectOutputStream sprOut = new ObjectOutputStream(
				new FileOutputStream(projectTitle+ "_sprint_list.dat"))) {

			System.out.println("������Ʈ ���� �������");
			for(int i = 0; i < sprintList.size(); i++) {
				sprOut.writeObject(sprintList.get(i));
				sprOut.flush();
			}
			System.out.println("���ο� ���Ͽ� ������Ʈ ���� �Ϸ�");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//ȸ�Ƿ� ����Ʈ ���Ͽ� ����
	public void saveMOMList() {

		File MOMFile = new File(projectTitle + "_MOM_list.dat");
		//������ �����ϸ� �����ϰ�
		try {
			if (!MOMFile.createNewFile()) {
				MOMFile.delete();
				System.out.println("���� ȸ�Ƿ� ���� ������");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//�ٽ� ���ϻ����ؼ� ��������
		try (ObjectOutputStream MOMOut = new ObjectOutputStream(
				new FileOutputStream(projectTitle + "_MOM_list.dat"))) {

			System.out.println("ȸ�Ƿ� ���� �������");
			for (int i = 0; i < MOMList.size(); i++) {
				MOMOut.writeObject(MOMList.get(i));
				MOMOut.flush();
			}
			System.out.println("���ο� ���Ͽ� ȸ�Ƿ� ���� �Ϸ�");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	
	
	
	
	
	
//������Ʈ ����
	//���� ������Ʈ ������ ������Ʈ ����Ʈ�� �÷��ְ� ���Ͽ� ��������
	//���� ������� ������Ʈ�� ������Ʈ�� ���� ������Ʈ ����Ʈ�� �÷��� ���Ͽ� ��������
	public void makeProject(Project project) {
		projectList.add(project);
		sprintList.addAll(project.getSprintList());
		saveSprints();
		saveProjects();
	}
//	
	
	
	
	
//����ã��	
	//������Ʈ ����Ʈ���� ���� ������Ʈ ���� ã�Ƽ� �ε����� ����
	//ã�� ������Ʈ ����
	public Project findProject() {

		Project selectedProject = null;
		for(int i = 0; i < projectList.size(); i++) {
			Project project = (Project)projectList.get(i);
			if(project.getProjectTitle().equals(projectTitle)) {
				System.out.println("��ġ�ϴ� ������Ʈ ã��!");
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
			System.out.println("!�����Ͻ� ������Ʈ�� �������� �ʽ��ϴ�!");
			return null;
		}
	}
	
	//������Ʈ����Ʈ���� ���� ������Ʈ ���� ã��. �ε��� ����
	public Sprint findSprint(Sprint sprint) {
		
		for(int i = 0;  i < sprintList.size(); i++) {
			Sprint s = (Sprint)sprintList.get(i);
			if(s.toString().equals(sprint.toString())) {
				System.out.println("��ġ�ϴ� ������Ʈ ã��");
				index = i;
				return s;
			}
		}
		
		System.out.println("������Ʈ�� �������� �ʽ��ϴ�.");
		return null;
	}
	
	//ȸ�Ƿϸ���Ʈ���� ���� ȸ�Ƿ� ���� ã��. �ε��� ����
	public MOM findMOM(MOM mom) {
		
		for(int i = 0;  i < MOMList.size(); i++) {
			MOM m = (MOM)MOMList.get(i);
			if(m.toString().equals(mom.toString())) {
				System.out.println("��ġ�ϴ� ȸ�Ƿ� ã��");
				index = i;
				return m;
			}
		}
		System.out.println("ȸ�Ƿ��� �������� �ʽ��ϴ�.");
		return null;
	}
	
	//������Ʈ ����Ʈ ����
	public ArrayList<Project> getProjectList() {
		return projectList;
	}
	
	//������Ʈ����Ʈ ����
	public ArrayList<Sprint> getSprintList() {
		return sprintList;
	}
	
	//ȸ�Ƿϸ���Ʈ ����
	public ArrayList<MOM> getMOMList() {
		return MOMList;
	}
//

	
	
	
	
	
	
//������Ʈ����, �������(�����ڱ���)
	//������Ʈ ������ ���� ������Ʈ ã�Ƽ� �޾ƿ� ������Ʈ�� �ٲٰ�
	//�ش� �ε����� ������Ʈ �ٽ� ���� ��, ���Ͽ� ����
	//������Ʈ����Ʈ�� ���� �޾ƿ�������Ʈ�� ������Ʈ ����Ʈ�� ������Ʈ �� ���Ͽ� ����
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
	
	//����������Ʈ ������Ʈ����Ʈ���� ã�Ƽ� index�̿��ؼ� �����ϱ�
	//�����ϰ� ���� �ٽ� ����Ʈ ���Ͽ� ����
	public void deleteProject() {
		findProject();
		projectList.remove(index);
		saveProjects();
	}
	
	//���� ������Ʈ ã�Ƽ� ������Ʈ�� �������Ʈ �޾ƿ¸���Ʈ�� ������Ʈ
	//�ش� �ε����� �ٽ� ������Ʈ�� ������Ʈ ������ ���Ͽ� ����
	public void addMember(ArrayList<String> memberList) {
		Project projectToBeChanged = findProject();
		projectToBeChanged.setMemberList(memberList);
		projectList.set(index, projectToBeChanged);
		saveProjects();
		
	}
	
	//���� ������Ʈ ã�Ƽ� ������Ʈ�� ���� �޾ƿ¾������� �ٲٱ�
	//�ش� �ε����� ������Ʈ �ٽ� ���� �� ���Ͽ� ����
	public void changeAdmin(String projectAdmin) {
		Project projectToBeChanged = findProject();
		projectToBeChanged.setProjectAdmin(projectAdmin);
		projectList.set(index, projectToBeChanged);
		saveProjects();
	}
//	
	
	
	
	
	
//���� ����	
	//������Ʈ ������ �ش� ������Ʈ ã�Ƽ� �޾ƿ� ������Ʈ�� �ٲ��ְ�
	//������Ʈ����Ʈ�� ������ ���Ͽ� ����
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

	//ȸ�Ƿ� ������ �Ȱ���
	public ArrayList<MOM> modifyMOM(Project project, MOM mom, MOM updatedMOM) {

		MOM momToBeChanged = findMOM(mom);
		if(momToBeChanged != null) {
			momToBeChanged = updatedMOM;
			MOMList.set(index, momToBeChanged);
		}
		saveMOMList();
		return MOMList;
	}

	//���� ����Ǿ��ִ� ������Ʈ ����Ʈ Ŭ�����ϰ� �޾ƿ� ������Ʈ ����Ʈ�� ������Ʈ �� ���Ͽ� ����
	public void addSprint(ArrayList<Sprint> sprintList) {
		this.sprintList.clear();
		this.sprintList.addAll(sprintList);
		saveSprints();
	}
	//ȸ�Ƿ�
	public void addMOM(ArrayList<MOM> MOMList) {
		this.MOMList.clear();
		this.MOMList.addAll(MOMList);
		saveMOMList();
	}
//
	
	
	
	
	

}
