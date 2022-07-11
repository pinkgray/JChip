package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import view.testPopup;

public class MOM implements Serializable {

	private String MOMTitle;
	private String MOMWriter;
	private Date MOMDay;
	private String MOMPerson;
	private String MOMDescription;

	public MOM() {
	}

	public MOM(String MOMTitle, Date MOMDay) {
		super();
		this.MOMTitle = MOMTitle;
		this.MOMDay = MOMDay;

	}

	public MOM(String MOMTitle, String MOMWriter, Date MOMDay, String MOMPerson, String MOMDescription) {
		this.MOMTitle = MOMTitle;
		this.MOMWriter = MOMWriter;
		this.MOMDay = MOMDay;
		this.MOMPerson = MOMPerson;
		this.MOMDescription = MOMDescription;

	}

	public String getMOMWriter() {
		return MOMWriter;
	}

	public String getMOMPerson() {
		return MOMPerson;
	}

	public String getMOMDescription() {
		return MOMDescription;
	}

	public String getMOMTitle() {
		return MOMTitle;
	}

	public Date getMOMDay() {
		return MOMDay;
	}

	public void setMOMWriter(String mOMWriter) {
		MOMWriter = mOMWriter;
	}

	public void setMOMPerson(String mOMPerson) {
		MOMPerson = mOMPerson;
	}

	public void setMOMDescription(String mOMDescription) {
		MOMDescription = mOMDescription;
	}

	public void setMOMTitle(String MOMTitle) {
		this.MOMTitle = MOMTitle;
	}

	public void setMOMDay(Date MOMDay) {
		this.MOMDay = MOMDay;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return MOMTitle + " " + sdf.format(MOMDay);
	}

}
