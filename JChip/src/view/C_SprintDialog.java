package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.A_MemberManager;
import model.vo.Project;
import model.vo.Work;

public class C_SprintDialog extends JPanel {
	private MainFrame mainframe;
	private C_CheckPU checkpu;
	private Dialog C_SprintDialog;

	private int count = 0;
	//private String[] checkmember = new String[10];
	private String result = "";

	private JButton OK_btn;

	private String message;
	private Project project;
	private A_MemberManager membermanager = new A_MemberManager();

	private Work work;

	public C_SprintDialog() {}

	public C_SprintDialog(MainFrame mainframe,C_CheckPU checkpu,String message,Project project) {
		this.mainframe = mainframe;
		this.checkpu = checkpu;
		this.project = project;
		this.message = message;


		C_SprintDialog = new Dialog(mainframe,"할당자 선택창");
		C_SprintDialog.setLayout(null);
		C_SprintDialog.setBackground(Color.decode("#324d6b"));
		C_SprintDialog.setUndecorated(true);
		C_SprintDialog.setSize(250,350);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width /2 )  - (C_SprintDialog.getWidth() / 2);
		int yPos = (dim.height / 2) - (C_SprintDialog.getHeight() / 2);

		C_SprintDialog.setLocation(xPos, yPos);


		JLabel title = new JLabel(message);
		title.setForeground(Color.white);
		title.setFont(new Font("Serif",Font.BOLD, 15));

		title.setSize(180, 50);
		title.setLocation(45, 15);

		//멤버 값을 checkbox에 넣음
		ArrayList<String> memberlist = new  ArrayList<String>();

		if (project != null) {
			memberlist.add(project.getProjectAdmin());

			ArrayList<String> projectmember = project.getMemberList();

			if (projectmember != null) {
				for (int i = 0 ; i < projectmember.size() ; i++) {
					memberlist.add(projectmember.get(i));
				}

			}
		}
		
		
		ArrayList<String> membername = membermanager.findMemberName(memberlist);

		JCheckBox[] Ass_Check = new JCheckBox[membername.size()];

		for (int i = 0 ; i < membername.size() ; i++) {
			Ass_Check[i] = new JCheckBox(membername.get(i));
			System.out.println(membername.get(i));
		}

		//Panel 위에 얹기 -> CheckPanel
		JPanel checkPanel = new JPanel();

		BoxLayout layout = new BoxLayout(checkPanel, BoxLayout.Y_AXIS);
		checkPanel.setLayout(layout);

		for (int i = 0 ; i < Ass_Check.length ; i++) {
			checkPanel.add(Ass_Check[i]);
		}


		for (int i = 0  ; i < Ass_Check.length ; i++) {
			Ass_Check[i].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					int tep = 0;
					//String result1 = "";

					for (int i = 0; i < Ass_Check.length ; i++) {
						//체크가 되어있는지 지속적으로 체크하는 것이다.
						if (Ass_Check[i].isSelected()) {
							result += Ass_Check[i].getText() + "/";
							tep++;

							//text.setText(result);

							if (tep == 0) {
								result = "선택한 하세요";
							}
						}
					}
				}
			});
		}


		//System.out.println(text.getText()+"");

		JScrollPane scroll = new JScrollPane(checkPanel);
		scroll.setSize(180, 200);
		scroll.setLocation(40, 60);
		//checkPanel.add(scroll);

		/*JButton */OK_btn = new JButton(new ImageIcon("images/okbtn11.png"));
		ImageIcon OK_btn2 = new ImageIcon("images/okbtn22.png");
		OK_btn.setBorderPainted(false);
		OK_btn.setFocusPainted(false);
		OK_btn.setContentAreaFilled(false);
		OK_btn.setRolloverIcon(OK_btn2);
		OK_btn.setLocation(170, 290);
		OK_btn.setSize(48,30);
		//OK_btn.addActionListener(this);

		JButton Cancel_btn = new JButton(new ImageIcon("images/close1.png"));
		ImageIcon Cancel_btn2 = new ImageIcon("images/close2.png");
		Cancel_btn.setBorderPainted(false);
		Cancel_btn.setFocusPainted(false);
		Cancel_btn.setContentAreaFilled(false);
		Cancel_btn.setRolloverIcon(Cancel_btn2);
		Cancel_btn.setLocation(20, 290);
		Cancel_btn.setSize(48,30);

		Cancel_btn.addActionListener(new CloseEvent());

		C_SprintDialog.add(title);
		C_SprintDialog.add(scroll);
		C_SprintDialog.add(OK_btn);
		C_SprintDialog.add(Cancel_btn);

		OK_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(result);

				checkpu.ChangeAss(result);
				
				C_SprintDialog.dispose();
			}

		});
	}

	public Dialog getAsspanel() {
		return this.C_SprintDialog;
	}

	//닫기
	private class CloseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			C_SprintDialog.dispose();
		}

	}



}
