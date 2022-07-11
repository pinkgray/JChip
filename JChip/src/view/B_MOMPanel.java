package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import controller.ProjectManager;
import model.vo.MOM;
import model.vo.Project;
import model.vo.Sprint;

public class B_MOMPanel extends JPanel implements ActionListener, MouseListener {

	private MainFrame mainFrame;
	private B_ProjectPage projectPage;
	private B_MOMPanel MOMPanel;
	private JButton newMOMButton;
	private String[] MOMInfo;
	private DefaultListModel MOMModel;
	private JList MOMJList;
	private ArrayList<MOM> MOMArrList = new ArrayList<MOM>();
	
	private Project selectedProject;

	public B_MOMPanel(B_ProjectPage projectPage, MainFrame mainFrame, Project selectedProject) {

		this.mainFrame = mainFrame;
		this.projectPage = projectPage;
		this.MOMPanel = this;
		
		this.selectedProject = selectedProject;
		this.setLayout(new BorderLayout());

		//(민)
		this.setBackground(B_ProjectPage.BG_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 50, 50));

		// 전체 폰트 스타일 적용
		//setUIFont(new javax.swing.plaf.FontUIResource("맑은 고딕", Font.PLAIN, 15));
		JPanel MOMPanel = new JPanel();
		MOMPanel.setBackground(B_ProjectPage.BG_COLOR);
		MOMPanel.setLayout(new BorderLayout());

		JPanel newMOMPanel = new JPanel();
		newMOMPanel.setBackground(B_ProjectPage.BG_COLOR);
		newMOMPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

		// 회의록 생성 버튼 (클릭시 팝업 떠야함)
		newMOMButton = new JButton(new ImageIcon("images/MOM1.png"));
		ImageIcon newMOMButton2 = new ImageIcon("images/MOM2.png");
		newMOMButton.setSize(150, 50);
		newMOMButton.setBorderPainted(false);
		newMOMButton.setFocusPainted(false);
		newMOMButton.setContentAreaFilled(false);
		newMOMButton.setRolloverIcon(newMOMButton2);

		// 회의록 생성 버튼에 이벤트 연결
		newMOMButton.addActionListener(this);

		newMOMPanel.add(newMOMButton, "North");

		MOMPanel.add(newMOMPanel, "North");

		MOMModel = new DefaultListModel();
		MOMListUpdate();
		
		// 회의록 리스트 올릴 리스트
		MOMJList = new JList(MOMModel);
		MOMJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MOMJList.setToolTipText("더블클릭시 해당 회의록 내용 띄움");

		MOMJList.addMouseListener(this);
		
		
		/*MOMJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					MOM selected = (MOM) MOMJList.getSelectedValue();
					new testPopup();
				}
			}
		});*/
		
		
		
		MOMJList.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		// 스크롤러에 리스트 올리기
		JScrollPane scroller = new JScrollPane(MOMJList);
		MOMPanel.add(scroller, "Center");
		this.add(MOMPanel, "Center");

		projectPage.add(this);
	}
	
	
	
	
	
	// 팝업에서 확인버튼 누르면 실행할 메소드
	public void addMOMOnList(String MOMTitle, String MOMWriter, Date MOMDay, String MOMPerson, String MOMDescription) {
		// 받아온 회의록명, 시작일, 종료일로 MOM객체 생성해서 arrayList에 올리기
		selectedProject = new ProjectManager().addNewMOM(selectedProject, MOMTitle, MOMWriter, MOMDay, MOMPerson,MOMDescription);
		MOMListUpdate();
		MOMPanel.revalidate();
		
		/*MOM newMOM = new MOM(MOMTitle, MOMDay);
		MOMArrList.add(newMOM);
		MOMModel.addElement(newMOM);
		MOMPanel.revalidate();*/
	}

	public void deleteMOM(MOM mom) {

		selectedProject = new ProjectManager().deleteMOM(selectedProject, mom);
		MOMListUpdate();

		MOMPanel.revalidate();
	}

	public void modifyMOM(MOM mom, String MOMTitle, String MOMWriter, Date MOMDay, String MOMPerson, String MOMDescription) {


		selectedProject = new ProjectManager().modifyMOM(selectedProject, mom, MOMTitle, MOMWriter, MOMDay, MOMPerson,MOMDescription);
		MOMListUpdate();


		MOMPanel.revalidate();
	}


	public void MOMListUpdate() {
		MOMModel.clear();
		ArrayList<MOM> MOMList = selectedProject.getMOMList();
		if (MOMList != null) {
			for (int i = 0; i < MOMList.size(); i++) {
				MOMModel.addElement(MOMList.get(i));
			}
		}

	}
	
	
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newMOMButton) {
			new B_NewMOMPopUp(mainFrame, MOMPanel, null).getMomPopup().setVisible(true);

		}

	}


	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}





	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == MOMJList) {
			if (e.getButton() == 1) {
				if (e.getClickCount() == 2) {
					
					MOM momSelected = (MOM) MOMJList.getSelectedValue();
					
					new B_NewMOMPopUp(mainFrame, MOMPanel, momSelected).getMomPopup().setVisible(true);
				}
			}
			
		}
	}





	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
