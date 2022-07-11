package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.WorkManager;
import model.dao.WorkDao;
import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;
import model.vo.Work;

public class C_OpenPanel extends JPanel implements ActionListener,MouseListener{
	private MainFrame mainFrame;
	private C_OpenPanel openPanel;
	private C_SprintMainPage sprintMain;

	private JButton Add_Work;
	private JButton Open_move_button;
	
	private ArrayList<Work> workArrList = new ArrayList<Work>(); //추가 한부분

	private Work work = new Work();
	private WorkManager wm = new WorkManager();
	private WorkDao wdao = new WorkDao();
	
	private B_ProjectPage projectPage;
	private Project selectproject;
	private Sprint selectsprint;
	private A_Member user;

	private JList<Work> openworklist;
	private DefaultListModel<Work> model = new DefaultListModel<>();

	private int index ;

	//public C_OpenPanel() {}

	public C_OpenPanel(C_SprintMainPage sprintMain,MainFrame mainFrame, B_ProjectPage projectPage, Project selectProject, Sprint selectSprint, A_Member user) {
		this.mainFrame = mainFrame;
		this.sprintMain = sprintMain;
		this.openPanel = this;
		
		this.projectPage = projectPage;
		this.selectproject = selectProject;
		this.selectsprint = selectSprint;
		this.user = user;
		
		wdao = new WorkDao(selectproject.getProjectTitle(),selectsprint.getSprintTitle());
		//this.setBackground(Color.white);

		this.setPreferredSize(new Dimension(340,700));
		//this.setBackground(Color.GREEN);
		this.setLayout(new BorderLayout());

		//OPEN이라고 글자 나오는 거
		JPanel Open_Title_panel = new JPanel();
		Open_Title_panel.setPreferredSize(new Dimension(340,45));
		Open_Title_panel.setBackground(Color.decode("#FFE65A"));
		Open_Title_panel.setLayout(new BorderLayout());

		
		JPanel Open_Title_Center_panel = new JPanel();
		Open_Title_Center_panel.setPreferredSize(new Dimension(100,55));
		Open_Title_Center_panel.setBackground(Color.decode( "#FFE65A"));
		

		JLabel Open_Title_label = new JLabel("OPEN");
		Open_Title_label.setFont(new Font("Tahoma",Font.PLAIN,25));
		Open_Title_label.setForeground(Color.DARK_GRAY);
		
		
		Open_Title_Center_panel.add(Open_Title_label);

		Open_move_button = new JButton(new ImageIcon("images/RButton.png"));
		Open_move_button.setBorderPainted(false);
		Open_move_button.setFocusPainted(false);
		Open_move_button.setContentAreaFilled(false);
		Open_move_button.setPreferredSize(new Dimension(50,55));
		
		Open_move_button.addActionListener(this);

		
		Open_Title_panel.add(Open_Title_Center_panel,"Center");
		Open_Title_panel.add(Open_move_button,"East");




		JScrollPane open_scroll = new JScrollPane(openworklist = createopenworklists());
		open_scroll.setPreferredSize(new Dimension(340,600));
		open_scroll.setBackground(Color.white);

		workUpdate();
		
		//리스트 확인창 이벤트
		openworklist.addMouseListener(this);


		//리스트 선택
		openworklist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		//OPEN 마지막에 버튼 창
		JPanel btn_Panel = new JPanel();
		btn_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn_Panel.setBackground(Color.white);
		btn_Panel.setPreferredSize(new Dimension(340,60));

		/*JButton */Add_Work = new JButton(new ImageIcon("images/Sprint_add.PNG"));
		Add_Work.setPreferredSize(new Dimension(50,50));

		Add_Work.setBorderPainted(false); Add_Work.setFocusPainted(false); Add_Work.setContentAreaFilled(false);

		Add_Work.addActionListener(this);

		btn_Panel.add(Add_Work);

		this.add(Open_Title_panel,"North");
		this.add(open_scroll, "Center");
		this.add(btn_Panel, "South");

		sprintMain.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Add_Work) {
			new C_CreatePU(this.mainFrame,this.openPanel).getCreatePU().setVisible(true);
		}

		if (e.getSource() == Open_move_button) {
			int[] selectindex = openworklist.getSelectedIndices();
			if(openworklist.getSelectedValuesList() != null) {
				ArrayList<Work> selectworklist = (ArrayList<Work>)openworklist.getSelectedValuesList();
				for (int i = 0 ; i < selectworklist.size() ; i++) {
					Work selectwork = selectworklist.get(i);
					
					if (selectwork != null) {
						dragMotion(selectwork);
					}
				}
				
			}
			
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == openworklist) {
			if (e.getClickCount() ==2) {
				/*int*/ index = openworklist.getSelectedIndex();
				Work work = openworklist.getSelectedValue();
				if (work != null) {
					new C_CheckPU(this.mainFrame,work,selectproject,this.openPanel).getCheckPU().setVisible(true);
					
				
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


	private JList<Work> createopenworklists(){
		model.clear();

		workUpdate();

		ArrayList<Work> worklist = wm.getWorklist();


		JList<Work> list = new JList<Work>(model);


		list.setCellRenderer(new C_WorkRenderer());		
		
		openPanel.repaint();
		
		return list;
	}


	//C_CreatePU에서 확인 버튼을 누르면 실행할 메소드
	public void addWorkOnList(Work newwork) {
		//입력한 할일명, 긴급표시 Work객체 생성 해서 List에 올리기
		//Work newWork = new Work();
		findWorkList();

		openPanel.revalidate();
	}

	//C_CheckPU에서 확인버튼 느렴 실행하는 메소드
	public void changeWorkOnList(Work changework) {
		model.removeElementAt(index);

		model.addElement(new Work(changework.getWork_name(),changework.getAllocator(),changework.getLabel_name(),changework.getLabel_color()));
		workArrList.add(changework);

		openPanel.revalidate();
	}

	public void findWorkList() {
		model.clear();

		//Work work = wm.getWork(selectedworkname);

		ArrayList<Work> list = wm.getWorklist();

		if (list == null) {

		}else {

			for (int i = 0 ; i < list.size() ; i++) {

				if (list.get(i).getWork_inf().equals("open")) {

					model.addElement(list.get(i));
				}
			}
		}
		
		openPanel.revalidate();
	}
	
	public void ModifyWork(Work work2, String work_name, Date work_start, Date work_end, String work_content,
			String[] allocator, String feedback, String label_name, Color label_color) {
		
		wm.ModifyWork(selectproject,selectsprint,work2, work_name, work_start, work_end, work_content, allocator, feedback, label_name, label_color);
		
		findWorkList();
	}

	public void makeNewWork(String work_name, Date work_start, Date work_end, String work_content, 
		 String label_name, Color btn_color,String[] allocator,String feedback) {
		wm.makeWork(selectproject,selectsprint,work_name, work_start, work_end, work_content, label_name, btn_color, allocator, feedback,"open");
		
		findWorkList();
	}
	
	
	public void workUpdate() {
		model.clear();

		ArrayList<Work> worklist = wdao.getWorkList();
		
		for (int i = 0 ; i < worklist.size() ; i++) {
			if (worklist.get(i).getWork_inf().equals("open")) {				
				model.addElement(worklist.get(i));
			}
		}
		
	}

	public void DeleteWork(Work work2, String work_name) {
		wm.DeleteWork(selectproject,selectsprint,work2);
		
		findWorkList();
	}

	public void dragMotion(Work work) {
		wm.ChangNextWork(selectproject,selectsprint,work);
		
		System.out.println(work);
		
		DeleteWork(work,work.getWork_name());
		ChangePanel.changePanel(mainFrame, this, new C_SprintMainPage(mainFrame, projectPage, selectproject, selectsprint, user));
		//sprintMain.updateWorkStatus();
	}

	
	

}
