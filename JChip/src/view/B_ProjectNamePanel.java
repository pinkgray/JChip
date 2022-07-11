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
		//������Ʈ�� : ������Ʈ ������ �Է��� �̸����� �޾ƿ;� ��
		JLabel projectName = new JLabel("ȸ�Ƿ�", JLabel.RIGHT);
		projectName.setHorizontalAlignment(JLabel.CENTER);
		projectName.setVerticalAlignment(JLabel.CENTER);
		projectName.setFont(new Font("���� ���", Font.BOLD, 20));
		this.add(projectName);
		
		projectPage.add(this);
	}
}
