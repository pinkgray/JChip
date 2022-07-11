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

import model.vo.Project;
import model.vo.Sprint;
import model.vo.Work;

public class WorkDao {
	private String projectTitle;
	private ArrayList<Project> projectList;
	
	private String sprintTitle;
	private ArrayList<Sprint> sprintList;
	
	private String work_name;
	private ArrayList<Work> worklist;

	private int index;
	
	public WorkDao() {}

	public WorkDao(String projectTitle,String sprintTitle) {
		sprintList = new ArrayList();
		worklist = new ArrayList();
		
		this.projectTitle = projectTitle;
		this.sprintTitle = sprintTitle;
		
		try (ObjectInputStream sprIN = new ObjectInputStream(new FileInputStream(projectTitle + "_sprint_list.dat"))){
			
			while(true) {
				Sprint s = (Sprint)sprIN.readObject();
				sprintList.add(s);
				
			}


		} catch (FileNotFoundException e) {
			System.out.println("������Ʈ ������ ã�� �� �����ϴ�.");

		} catch (EOFException e){
			System.out.println("������Ʈ ���� �ҷ����� �Ϸ�!");
			
			try (ObjectInputStream workIn = new ObjectInputStream(new FileInputStream(sprintTitle +"_worklist.dat"));) {

				while(true) {

					Work w = (Work)workIn.readObject();
					worklist.add(w);

					System.out.println(w);

				}
				
			} catch (FileNotFoundException e1) {
				System.out.println("���� ������ ã�� �� �����ϴ�!");
			} catch (EOFException e1) {
				System.out.println("���� ���� �ҷ����� �Ϸ�");
			} catch (IOException e1) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WorkDao(String projectTitle,String sprintTitle,String work_name) {
		this.work_name = work_name;
		sprintList = new ArrayList();
		worklist = new ArrayList();
		
		this.projectTitle = projectTitle;
		this.sprintTitle = sprintTitle;
		
		try (ObjectInputStream sprIN = new ObjectInputStream(new FileInputStream(projectTitle + "_sprint_list.dat"))){
			
			while(true) {
				Sprint s = (Sprint)sprIN.readObject();
				sprintList.add(s);
			}


		} catch (FileNotFoundException e) {
			System.out.println("������Ʈ ������ ã�� �� �����ϴ�.");

		} catch (EOFException e){
			System.out.println("������Ʈ ���� �ҷ����� �Ϸ�!");
			
			try (ObjectInputStream workIn = new ObjectInputStream(new FileInputStream(sprintTitle +"_worklist.dat"));) {

				while(true) {

					Work w = (Work)workIn.readObject();
					worklist.add(w);

					System.out.println(w);

				}
				
			} catch (FileNotFoundException e1) {
				System.out.println("���� ������ ã�� �� �����ϴ�!");
			} catch (EOFException e1) {
				System.out.println("���� ���� �ҷ����� �Ϸ�");
			} catch (IOException e1) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public void saveWork(Sprint sprint) {

		//if(file.createNewFile())
		sprintTitle = sprint.getSprintTitle();
		
		File workfile = new File(sprintTitle + "_worklist.dat");
		try {
			if(!workfile.createNewFile()) {
				workfile.delete();
				System.out.println("���� �� ���� ������");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (ObjectOutputStream workOut = new ObjectOutputStream(new FileOutputStream(sprintTitle+ "_worklist.dat"))) {

			System.out.println("�� ���� �����");
			for(int i = 0; i < worklist.size(); i++) {
				workOut.writeObject(worklist.get(i));
				workOut.flush();
			}
			System.out.println("���ο� ���Ͽ� �� ���� �Ϸ�");


		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void makeWork(Sprint sprint ,Work work) {
		worklist.add(work);
		saveWork(sprint);
	}

	public Work findWork() {

		Work selectWork = null;
		for (int i = 0; i < worklist.size() ; i++) {
			Work work = (Work)worklist.get(i);
			if (work.getWork_name().equals(work_name)) {
				System.out.println("��ġ�ϴ� ���� ã��!");
				index  = i ; 
				selectWork = work;
				break;
			}
		}

		if (selectWork != null) {
			return selectWork;
		}else { 
			System.out.println("�����Ͻ� ���� ������ �����ϴ�.");
			return null;
		}
	}


	public void modifyWork(Sprint sprint,Work work) {
		Work changeWork = findWork();
		changeWork = work;
		work_name = changeWork.getWork_name();
		worklist.set(index, changeWork);
		saveWork(sprint);
	}


	public void deleteWork(Sprint sprint,Work work) {
		Work changeWork = findWork();
		changeWork = work;
		work_name = changeWork.getWork_name();
		worklist.remove(index);
		saveWork(sprint);
	}


	public ArrayList<Work> getWorkList(){
		return worklist;
	}



}
