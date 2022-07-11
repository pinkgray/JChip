package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import model.dao.WorkDao;
import model.vo.Project;
import model.vo.Sprint;
import model.vo.Work;

public class WorkManager {
	
	private WorkDao wdao = new WorkDao();
	private ArrayList<Work>  worklist = new ArrayList<Work>();
	private ArrayList<Sprint> sprints = new ArrayList<Sprint>();

	public WorkManager() {}
	
	//¿œ√ﬂ∞°
	public void makeWork(Project project,Sprint sprint,String work_name,Date work_start, Date work_end,String work_content,
			String label_name, Color label_color, String[] allocator,String feedback,String work_inf) {
		Work newWork = new Work(work_name,work_start,work_end,work_content,label_name,label_color,allocator,feedback,work_inf);
		wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),work_name);
		wdao.makeWork(sprint,newWork);
	}
	
	
	public Work getWork(Project project,Sprint sprint,String selectedworkname) {
		wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),selectedworkname);
		
		return wdao.findWork();
	}
	
	public ArrayList<Work> getWorklist(){
		worklist = wdao.getWorkList();
		
		return worklist;
	}
	
	
	
	public void ModifyWork(Project project,Sprint sprint,Work work, String work_name, Date work_start , Date work_end
			,String work_content , String[] allocator , String feedback,String label_name , Color label_color) {
	
		wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),work.getWork_name());
		work.setWork_name(work_name);
		work.setWork_start(work_start);
		work.setWork_end(work_end);
		work.setWork_content(work_content);
		work.setLabel_name(label_name);
		work.setLabel_color(label_color);
		work.setAllocator(allocator);
		work.setFeedback(feedback);
		wdao.modifyWork(sprint,work);
		
	}
	
	public void ChangNextWork(Project project,Sprint sprint,Work work) {
		if (work.getWork_inf().equals("open")) {
			work.setWork_inf("progress");
			wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),work.getWork_name());
			wdao.makeWork(sprint,work);
		}else if (work.getWork_inf().equals("progress")) {
			work.setWork_inf("done");
			wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),work.getWork_name());
			wdao.makeWork(sprint, work);
		}
	}
	

	public void DeleteWork(Project project, Sprint sprint,Work work) {
		
		wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),work.getWork_name());
		
		wdao.deleteWork(sprint,work);
	}
	
	public void AddWork(Project project,Sprint sprint,Work work) {
		Work addwork = work;
		
		wdao = new WorkDao(project.getProjectTitle(),sprint.getSprintTitle(),addwork.getWork_name());
		
		wdao.makeWork(sprint,addwork);
		
	}

	public void ChangePreWork(Project selectproject, Sprint selectsprint, Work work) {
		if (work.getWork_inf().equals("progress")) {
			work.setWork_inf("open");
			wdao = new WorkDao(selectproject.getProjectTitle(),selectsprint.getSprintTitle(),work.getWork_name());
			wdao.makeWork(selectsprint,work);
		}else if (work.getWork_inf().equals("done")) {
			work.setWork_inf("progress");
			wdao = new WorkDao(selectproject.getProjectTitle(),selectsprint.getSprintTitle(),work.getWork_name());
			wdao.makeWork(selectsprint, work);
		}
		
	}
	
	

}
