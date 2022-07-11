package model.vo;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Work implements Serializable{
	private String work_name;		//���� �̸�
	private Date work_start;		//���� ���۳�¥
	private Date work_end;		//���� ���ᳯ¥
	private String work_content; 	//���� ���γ���
	private String label_name; 		//���̸�
	private Color label_color = Color.YELLOW; //�󺧻���
	private String[] allocator;		//�Ҵ���
	private String feedback;		//�ǵ��
	private String work_inf = "open";		//�� ����
	
	public Work() {}
	
	
	


	public Work(String work_name,String[] allocator, String label_name, Color label_color) {
		super();
		this.work_name = work_name;
		this.label_name = label_name;
		this.label_color = label_color;
		this.allocator = allocator;
	}





	public Work(String work_name, Date work_start, Date work_end, String work_content, String label_name,
			Color label_color, String[] allocator, String feedback, String work_inf) {
		super();
		this.work_name = work_name;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_content = work_content;
		this.label_name = label_name;
		this.label_color = label_color;
		this.allocator = allocator;
		this.feedback = feedback;
		this.work_inf = work_inf;
	}





	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public Date getWork_start() {
		return work_start;
	}

	public void setWork_start(Date work_start) {
		this.work_start = work_start;
	}

	public Date getWork_end() {
		return work_end;
	}

	public void setWork_end(Date work_end) {
		this.work_end = work_end;
	}

	public String[] getAllocator() {
		return allocator;
	}

	public void setAllocator(String[] allocator) {
		this.allocator = allocator;
	}


	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}

	public String getWork_content() {
		return work_content;
	}

	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Color getLabel_color() {
		return label_color;
	}

	public void setLabel_color(Color label_color) {
		this.label_color = label_color;
	}

	public String getWork_inf() {
		return work_inf;
	}

	public void setWork_inf(String work_inf) {
		this.work_inf = work_inf;
	}

	@Override
	public String toString() {
		return "Work [work_name=" + work_name + ", work_start=" + work_start + ", work_end=" + work_end
				+ ", work_content=" + work_content + ", label_name=" + label_name + ", label_color=" + label_color
				+ ", allocator=" + Arrays.toString(allocator) + ", feedback=" + feedback + ", work_inf=" + work_inf
				+ "]";
	}


	

}
