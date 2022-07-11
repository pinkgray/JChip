package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.vo.Project;

public class B_DdayPanel extends JPanel{

	public static int totalDay;
	public static int dayPast;
	
	
	public B_DdayPanel(B_ProjectPage projectPage, Project selectedProject) {

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(B_ProjectPage.BG_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 20));
		//���� : ������Ʈ ������ ��¥�� ī��Ʈ �ؾ���//��� ���ϵ��� 
		JLabel projectDdayMinus = new JLabel("D-Day", JLabel.LEFT);
		
		Date startDay = selectedProject.getProjectStartDay();
		Date endDay = selectedProject.getProjectEndDay();
		Date today = new Date();
		
		
		long totalTime = endDay.getTime() - startDay.getTime(); 	//�и�������
		totalDay = (int)(((((totalTime / 1000) / 60) / 60) / 24)); 
		long timePast = today.getTime() - startDay.getTime();
		dayPast = (int)(((((timePast / 1000) / 60) / 60) / 24)) + 1; 
		//System.out.println(dayLeft);
		
		int dayLeft = totalDay - dayPast;
		dayLeft = (dayLeft >= 0)? dayLeft: 0;
		
		projectDdayMinus.setText("D - " + (dayLeft));
		projectDdayMinus.setHorizontalAlignment(JLabel.CENTER);
		projectDdayMinus.setFont(new Font("���� ���", Font.BOLD, 30));
		this.add(projectDdayMinus);
		
		
		JLabel projectDdayPlus = new JLabel("", JLabel.LEFT);
		
		projectDdayPlus.setText("(D + " + dayPast + ")");
		projectDdayPlus.setHorizontalAlignment(JLabel.CENTER);
		projectDdayPlus.setFont(new Font("���� ���", Font.BOLD, 20));
		projectDdayPlus.setForeground(Color.GRAY);
		this.add(projectDdayPlus);
		projectPage.add(this);
	}
}
