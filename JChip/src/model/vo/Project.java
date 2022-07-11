package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Project implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String projectTitle;
	protected Date projectStartDay;
	protected Date projectEndDay;
	protected transient ArrayList<Sprint> sprintList;
	protected String projectAdmin;
	protected ArrayList<String> memberList;
	protected transient ArrayList<MOM> MOMList;
	protected HashMap calendarMap;
	

	public Project() {}
	
	

	public Project(String projectTitle, Date projectStartDay, Date projectEndDay, 
			ArrayList<Sprint> sprintList, String projectAdmin, ArrayList<String> memberList, ArrayList<MOM> MOMList) {
		super();
		this.projectTitle = projectTitle;
		this.projectStartDay = projectStartDay;
		this.projectEndDay = projectEndDay;
		this.sprintList = sprintList;
		this.projectAdmin = projectAdmin;
		this.memberList = memberList;
		this.MOMList = MOMList;
	}



	public String getProjectTitle() {
		return projectTitle;
	}

	public Date getProjectStartDay() {
		return projectStartDay;
	}

	public Date getProjectEndDay() {
		return projectEndDay;
	}

	public ArrayList<Sprint> getSprintList() {
		return sprintList;
	}

	public String getProjectAdmin() {
		return projectAdmin;
	}

	public ArrayList<String> getMemberList() {
		return memberList;
	}

	public ArrayList<MOM> getMOMList() {
		return MOMList;
	}

	public HashMap getCalendarMap() {
		return calendarMap;
	}


	
	
	

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public void setProjectStartDay(Date projectStartDay) {
		this.projectStartDay = projectStartDay;
	}

	public void setProjectEndDay(Date projectEndDay) {
		this.projectEndDay = projectEndDay;
	}

	public void setSprintList(ArrayList<Sprint> sprintList) {
		this.sprintList = sprintList;
	}

	public void setProjectAdmin(String projectAdmin) {
		this.projectAdmin = projectAdmin;
	}

	public void setMemberList(ArrayList<String> memberList) {
		this.memberList = memberList;
	}

	public void setMOMList(ArrayList<MOM> mOMList) {
		MOMList = mOMList;
	}

	public void setCalendarMap(HashMap calendarMap) {
		this.calendarMap = calendarMap;
	}


	
	
	
	

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return projectTitle + "  (" + sdf.format(projectStartDay) + " ~ " + sdf.format(projectEndDay) + ")" /*+ projectPeople*/;
	}




}
