package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class B_ProjectNamePanel extends JPanel {

	public B_ProjectNamePanel(B_ProjectPage projectPage) {
		
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.setBackground(B_ProjectPage.BG_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 50));
		//프로젝트명 : 프로젝트 생성시 입력한 이름으로 받아와야 함
		JLabel projectName = new JLabel("회의록", JLabel.RIGHT);
		projectName.setHorizontalAlignment(JLabel.CENTER);
		projectName.setVerticalAlignment(JLabel.CENTER);
		projectName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		this.add(projectName);
		
		projectPage.add(this);
	}
}
