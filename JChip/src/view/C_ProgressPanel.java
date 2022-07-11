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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.WorkManager;
import model.dao.WorkDao;
import model.vo.A_Member;
import model.vo.Project;
import model.vo.Sprint;
import model.vo.Work;


public class C_ProgressPanel extends JPanel implements ActionListener,MouseListener{
	private MainFrame mainFrame;
	private C_ProgressPanel progressPanel;
	private C_SprintMainPage sprintMain;
	
	private JTable progress_Table;
	private JButton Add_Work;
	private JButton Progress_move_button;
	private JButton Progress_To_open_btn;
	
	private Work work = new Work();
	private WorkManager wm = new WorkManager();
	private WorkDao wdao = new WorkDao();
	
	private ArrayList<Work> workArrList = new ArrayList<Work>(); //추가 한부분
	
	private JList<Work> progresslist;
	DefaultListModel<Work> progrssmodel = new DefaultListModel<>();
	private int index; 
	
	private Project selectproject;
	private Sprint selectsprint;	
	
	private B_ProjectPage projectPage;
	private A_Member user;
	
	public C_ProgressPanel(C_SprintMainPage sprintMain,MainFrame mainFrame, B_ProjectPage projectPage, Project selectProject, Sprint selectSprint, A_Member user) {
		this.mainFrame = mainFrame;
		this.sprintMain = sprintMain;
		this.progressPanel = this;
		
		this.selectproject = selectProject;
		this.selectsprint = selectSprint;
		
		this.projectPage = projectPage;
		this.user = user;
		wdao = new WorkDao(selectproject.getProjectTitle(),selectsprint.getSprintTitle());
		
		this.setPreferredSize(new Dimension(340,700));
		//this.setBackground(Color.yellow);
		this.setLayout(new BorderLayout());
		
		//In Progress이라고 글자 나오는 거
		JPanel Progress_Title_panel = new JPanel();
		Progress_Title_panel.setPreferredSize(new Dimension(340,45));
		Progress_Title_panel.setBackground(Color.decode( "#98FB98"));
		Progress_Title_panel.setLayout(new BorderLayout());
		
		
		Progress_To_open_btn = new JButton("<");
		Progress_To_open_btn = new JButton(new ImageIcon("images/LButton.png"));
		Progress_To_open_btn.setBorderPainted(false);
		Progress_To_open_btn.setFocusPainted(false);
		Progress_To_open_btn.setContentAreaFilled(false);
		Progress_To_open_btn.setPreferredSize(new Dimension(50,55));
		
		Progress_To_open_btn.addActionListener(this);
		
		JPanel Progress_Title_Center_panel = new JPanel();
		Progress_Title_Center_panel.setPreferredSize(new Dimension(100,55));
		Progress_Title_Center_panel.setBackground(Color.decode( "#98FB98"));
		
		JLabel Progress_Title_label = new JLabel("In Progress");
		Progress_Title_label.setFont(new Font("Tahoma",Font.PLAIN,25));
		//Progress_Title_label.setForeground(Color.DARK_GRAY);
		
		Progress_Title_Center_panel.add(Progress_Title_label);
		
		
		Progress_move_button = new JButton(new ImageIcon("images/RButton.png"));
		Progress_move_button.setBorderPainted(false);
		Progress_move_button.setFocusPainted(false);
		Progress_move_button.setContentAreaFilled(false);
		Progress_move_button.setPreferredSize(new Dimension(50,55));
		
		Progress_move_button.addActionListener(this);
		
		Progress_Title_panel.add(Progress_To_open_btn,"West");
		Progress_Title_panel.add(Progress_Title_Center_panel,"Center");
		Progress_Title_panel.add(Progress_move_button,"East");

		this.add(Progress_Title_panel,"North");
		
		//Progress 리스트 창
		JScrollPane progress_scroll = new JScrollPane(progresslist = createprogresslist());
		progress_scroll.setPreferredSize(new Dimension(340,600));
		progress_scroll.setBackground(Color.white);
		
		//리스트 확인창 이벤트
		progresslist.addMouseListener(this);
		
		//리스트 선택
		progresslist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		
		//버튼 패널 -> 실행중인 패널에 추가 하는것
		JPanel btn_Panel = new JPanel();
		btn_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn_Panel.setBackground(Color.white);
		btn_Panel.setPreferredSize(new Dimension(340,60));
		
		/*JButton*/ Add_Work = new JButton(new ImageIcon("images/Sprint_add.PNG"));
		Add_Work.setPreferredSize(new Dimension(50,50));
		
		Add_Work.setBorderPainted(false); Add_Work.setFocusPainted(false); Add_Work.setContentAreaFilled(false);
		
		Add_Work.addActionListener(this);
		
		btn_Panel.add(Add_Work);
		
		
		this.add(Progress_Title_panel, "North");
		this.add(progress_scroll, "Center");
		this.add(btn_Panel, "South");
		
		sprintMain.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Add_Work) {
			new C_CreatePU(this.mainFrame,this.progressPanel).getCreatePU().setVisible(true);
		}

		if (e.getSource() == Progress_move_button) {
			int[] selectindex = progresslist.getSelectedIndices();
			ArrayList<Work> selectworklist = (ArrayList<Work>)progresslist.getSelectedValuesList();

			if (selectworklist != null) {
				for (int i = 0 ; i < selectworklist.size() ; i++) {
					Work selectwork = selectworklist.get(i);

					if (selectwork != null) {
						dragNextMotion(selectwork);
					}
				}
			}

		}

		if (e.getSource() == Progress_To_open_btn) {
			int[] selectindex = progresslist.getSelectedIndices();
			ArrayList<Work> selectworklist = (ArrayList<Work>)progresslist.getSelectedValuesList();

			if (selectworklist != null) {
				for (int i = 0 ; i < selectworklist.size() ; i++) {
					Work selectwork = selectworklist.get(i);

					if (selectwork != null) {
						dragPreMotion(selectwork);
					}
				}
			}
		}
	}

	private JList<Work> createprogresslist(){
		progrssmodel.clear();

		workUpdate();

		ArrayList<Work> worklist = wm.getWorklist();

		
		JList<Work> list = new JList<Work>(progrssmodel);
		

		list.setCellRenderer(new C_WorkRenderer());		

		return list;

	}
	
	private void workUpdate() {
		progrssmodel.clear();

		ArrayList<Work> worklist = wdao.getWorkList();
		
		for (int i = 0 ; i < worklist.size() ; i++) {
			if (worklist.get(i).getWork_inf().equals("progress")) {				
				progrssmodel.addElement(worklist.get(i));
			}
		}
	}
	
	
	public void findWorkList() {
		progrssmodel.clear();
		
		//Work work = wm.getWork(selectedworkname);
		
		ArrayList<Work> list = wm.getWorklist();

		if (list == null) {

		}else {

			for (int i = 0 ; i < list.size() ; i++) {

				if (list.get(i).getWork_inf().equals("progress")) {

					progrssmodel.addElement(list.get(i));
				}
			}
		}
		
		progressPanel.revalidate();
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == progresslist) {
			if (e.getClickCount() ==2) {
				/*int*/ index = progresslist.getSelectedIndex();
				Work work = progresslist.getSelectedValue();
				new C_CheckPU(this.mainFrame,work,selectproject,this.progressPanel).getCheckPU().setVisible(true);
				//System.out.println("누름");


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
		
		//C_CreatePU에서 확인 버튼을 누르면 실행할 메소드
		public void addWorkOnList(Work newwork) {
			//입력한 할일명, 긴급표시 Work객체 생성 해서 List에 올리기
			//Work newWork = new Work();
			newwork.setAllocator(null);
			newwork.setWork_inf("progress");

			progrssmodel.addElement(new Work(newwork.getWork_name(),newwork.getAllocator(),newwork.getLabel_name(),newwork.getLabel_color()));
			workArrList.add(newwork);

			progressPanel.revalidate();
		}
		
		//C_CheckPU에서 확인버튼 느렴 실행하는 메소드
		public void changeWorkOnList(Work changework) {
			progrssmodel.removeElementAt(index);
			
			progrssmodel.addElement(new Work(changework.getWork_name(),changework.getAllocator(),changework.getLabel_name(),changework.getLabel_color()));
			workArrList.add(changework);
			
			progressPanel.revalidate();
		}
		
		
		//open_peanel에서 move버튼 클릭시 실행 메소드
		public void moveWorkList(Work moveWork) {
			progrssmodel.addElement(new Work(moveWork.getWork_name(),moveWork.getAllocator(),moveWork.getLabel_name(),moveWork.getLabel_color()));
			moveWork.setWork_inf("progress");

			workArrList.add(moveWork);

			progressPanel.revalidate();
		}
		

		public void makeNewWork(String work_name, Date work_start, Date work_end, String work_content,
				String work_subject, Color label_color, String[] allocator, String feedback) {
			// TODO Auto-generated method stub
			
			wm.makeWork(selectproject,selectsprint,work_name, work_start, work_end, work_content, work_subject, label_color, allocator, feedback,"progress");
			
			findWorkList();
		}

		public void ModifyWork(Work work2, String work_name, Date work_start, Date work_end, String work_content,
				String[] allocator, String feedback, String label_name, Color label_color) {
			
			wm.ModifyWork(selectproject,selectsprint,work2, work_name, work_start, work_end, work_content, allocator, feedback, label_name, label_color);
			
			findWorkList();
		}

		public void repaintWork() {
			System.out.println("2번");
			findWorkList();
		}

		public void DeleteWork(Work work2, String work_name) {

			wm.DeleteWork(selectproject,selectsprint,work2);
			
			findWorkList();
		}
		
		public void dragNextMotion(Work work) {
			wm.ChangNextWork(selectproject,selectsprint,work);
			
			System.out.println(work);
			
			DeleteWork(work,work.getWork_name());	
			ChangePanel.changePanel(mainFrame, this, new C_SprintMainPage(mainFrame, projectPage, selectproject, selectsprint, user));
			
		}

		public void dragPreMotion(Work work) {
			wm.ChangePreWork(selectproject,selectsprint,work);
			
			DeleteWork(work,work.getWork_name());	
			ChangePanel.changePanel(mainFrame, this, new C_SprintMainPage(mainFrame, projectPage, selectproject, selectsprint, user));
		}
	
		

}
