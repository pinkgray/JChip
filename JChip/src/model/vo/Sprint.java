package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sprint implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private Project selectedProject;
	private String sprintTitle;
	private Date sprintStartDay;
	private Date sprintEndDay;
	private String sprintDetail;
	private String sprintToDo;
	
	public Sprint() {}
	
	public Sprint(/*Project selectedProject,*/ String sprintTitle, Date sprintStartDay, Date sprintEndDay,
			String sprintDetail, String sprintToDo) {
		super();
		/*this.selectedProject = selectedProject;*/
		this.sprintTitle = sprintTitle;
		this.sprintStartDay = sprintStartDay;
		this.sprintEndDay = sprintEndDay;
		this.sprintDetail = sprintDetail;
		this.sprintToDo = sprintToDo;
				
	}
	
	
	
	public String getSprintTitle() {
		return sprintTitle;
	}

	public Date getSprintStartDay() {
		return sprintStartDay;
	}

	public Date getSprintEndDay() {
		return sprintEndDay;
	}

	public String getSprintDetail() {
		return sprintDetail;
	}

	public String getSprintToDo() {
		return sprintToDo;
	}

	
	
	
	

	public void setSprintTitle(String sprintTitle) {
		this.sprintTitle = sprintTitle;
	}

	public void setSprintStartDay(Date sprintStartDay) {
		this.sprintStartDay = sprintStartDay;
	}

	public void setSprintEndDay(Date sprintEndDay) {
		this.sprintEndDay = sprintEndDay;
	}

	public void setSprintDetail(String sprintDetail) {
		this.sprintDetail = sprintDetail;
	}
	
	public void setSprintToDo(String sprintToDo) {
		this.sprintToDo = sprintToDo;
	}



	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sprintTitle + " (" + sdf.format(sprintStartDay) + " ~ " + sdf.format(sprintEndDay) + ") ";
	}




	
	
}
