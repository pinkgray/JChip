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


public class C_DonePanel extends JPanel implements ActionListener,MouseListener{
	private MainFrame mainFrame;
	private C_DonePanel DoenPanel;
	private C_SprintMainPage sprintMain;
	
	private JButton Add_Work;
	private JButton done_move_progress_btn;
	
	private ArrayList<Work> workArrList = new ArrayList<Work>(); //추가 한부분

	private Work work = new Work();
	private WorkManager wm = new WorkManager();
	private WorkDao wdao = new WorkDao();
	
	private JList<Work> doneworklist;
	private DefaultListModel<Work> donemodel = new DefaultListModel<>();
	
	private int index ;
	
	private Project selectproject;
	private Sprint selectsprint;
	
	private B_ProjectPage projectPage;
	private A_Member user;
	
	public C_DonePanel(C_SprintMainPage sprintMain,MainFrame mainFrame,B_ProjectPage projectPage, Project selectproject,Sprint selectsprint, A_Member user) {
		this.mainFrame = mainFrame;
		this.sprintMain = sprintMain;
		this.DoenPanel = this;
		
		
		this.selectproject = selectproject;
		this.selectsprint = selectsprint;
		
		this.projectPage = projectPage;
		this.user = user;
		
		wdao = new WorkDao(selectproject.getProjectTitle(),selectsprint.getSprintTitle());
		
		this.setPreferredSize(new Dimension(340,700));
		//this.setBackground(Color.blue);

		this.setLayout(new BorderLayout());

		//In Progress이라고 글자 나오는 거
		JPanel Done_Title_panel = new JPanel();
		Done_Title_panel.setPreferredSize(new Dimension(340,45));
		Done_Title_panel.setBackground(Color.decode("#6495ED"));
		Done_Title_panel.setLayout(new BorderLayout());

		done_move_progress_btn = new JButton(new ImageIcon("images/LButton.png"));
		done_move_progress_btn.setBorderPainted(false);
		done_move_progress_btn.setFocusPainted(false);
		done_move_progress_btn.setContentAreaFilled(false);
		done_move_progress_btn.setPreferredSize(new Dimension(50,55));
		
		done_move_progress_btn.addActionListener(this);
		
		JPanel Progress_Title_Center_panel = new JPanel();
		Progress_Title_Center_panel.setPreferredSize(new Dimension(100,55));
		Progress_Title_Center_panel.setBackground(Color.decode("#6495ED"));

		JLabel Done_Title_label = new JLabel("Done");
		Done_Title_label.setFont(new Font("Tahoma",Font.PLAIN,25));
		Done_Title_label.setForeground(Color.DARK_GRAY);
		
		Progress_Title_Center_panel.add(Done_Title_label);
		
		Done_Title_panel.add(done_move_progress_btn,"West");
		Done_Title_panel.add(Progress_Title_Center_panel,"Center");

		//리스트 할일
		JScrollPane done_scroll = new JScrollPane(doneworklist = createopenworklists());
		done_scroll.setPreferredSize(new Dimension(340,600));
		done_scroll.setBackground(Color.white);
		
		//리스트 확인창 이벤트
		doneworklist.addMouseListener(this);
		
		//리스트 선택 모션
		doneworklist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//버튼 패널 -> 다한 할일 모음
		JPanel btn_Panel = new JPanel();
		btn_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn_Panel.setBackground(Color.white);
		btn_Panel.setPreferredSize(new Dimension(340,60));
		
		/*JButton*/ Add_Work = new JButton(new ImageIcon("images/Sprint_add.PNG"));
		Add_Work.setPreferredSize(new Dimension(50,50));
		
		Add_Work.setBorderPainted(false); Add_Work.setFocusPainted(false); Add_Work.setContentAreaFilled(false);
		Add_Work.addActionListener(this);
		
		btn_Panel.add(Add_Work);
		
		
		this.add(Done_Title_panel,"North");
		this.add(done_scroll, "Center");
		this.add(btn_Panel, "South");
		
		sprintMain.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Add_Work) {
			new C_CreatePU(this.mainFrame,this.DoenPanel).getCreatePU().setVisible(true);
		}

		if (e.getSource() == done_move_progress_btn) {
			int[] selectindex = doneworklist.getSelectedIndices();
			ArrayList<Work> selectworklist = (ArrayList<Work>)doneworklist.getSelectedValuesList();

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
	
	

	private JList<Work> createopenworklists(){
		donemodel.clear();

		workUpdate();

		ArrayList<Work> worklist = wm.getWorklist();

		
		JList<Work> list = new JList<Work>(donemodel);
		

		list.setCellRenderer(new C_WorkRenderer());		

		return list;

	}
	
	private void workUpdate() {
		donemodel.clear();

		ArrayList<Work> worklist = wdao.getWorkList();
		
		for (int i = 0 ; i < worklist.size() ; i++) {
			if (worklist.get(i).getWork_inf().equals("done")) {				
				donemodel.addElement(worklist.get(i));
			}
		}
	}
	
	public void findWorkList() {
		donemodel.clear();
		
		//Work work = wm.getWork(selectedworkname);
		
		ArrayList<Work> list = wm.getWorklist();

		if (list == null) {

		}else {

			for (int i = 0 ; i < list.size() ; i++) {

				if (list.get(i).getWork_inf().equals("done")) {

					donemodel.addElement(list.get(i));
				}
			}
		}
		
		DoenPanel.revalidate();
	}


	//C_CreatePU에서 확인 버튼을 누르면 실행할 메소드
	public void addWorkOnList(Work newwork) {
		//입력한 할일명, 긴급표시 Work객체 생성 해서 List에 올리기
		//Work newWork = new Work();
		newwork.setAllocator(null);

		donemodel.addElement(new Work(newwork.getWork_name(),newwork.getAllocator(),newwork.getLabel_name(),newwork.getLabel_color()));
		workArrList.add(newwork);

		DoenPanel.revalidate();
	}

	//C_CheckPU에서 확인버튼 느렴 실행하는 메소드
	public void changeWorkOnList(Work changework) {
		donemodel.removeElementAt(index);
		
		donemodel.addElement(new Work(changework.getWork_name(),changework.getAllocator(),changework.getLabel_name(),changework.getLabel_color()));
		workArrList.add(changework);
		
		DoenPanel.revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == doneworklist) {
			if (e.getClickCount() ==2) {
				/*int*/ index = doneworklist.getSelectedIndex();
				Work work = doneworklist.getSelectedValue();
				new C_CheckPU(this.mainFrame,work,selectproject,this.DoenPanel).getCheckPU().setVisible(true);
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

	public void makeNewWork(String work_name, Date work_start, Date work_end, String work_content,
			String work_subject, Color label_color, String[] allocator, String feedback) {
		// TODO Auto-generated method stub
		
		wm.makeWork(selectproject,selectsprint,work_name, work_start, work_end, work_content, work_subject, label_color, allocator, feedback,"done");
		
		findWorkList();
	}

	public void ModifyWork(Work work2, String work_name, Date work_start, Date work_end, String work_content,
			String[] allocator, String feedback, String label_name, Color label_color) {
		
		wm.ModifyWork(selectproject,selectsprint,work2, work_name, work_start, work_end, work_content, allocator, feedback, label_name, label_color);
		
		findWorkList();
	}

	public void DeleteWork(Work work2, String work_name) {
		// TODO Auto-generated method stub
		wm.DeleteWork(selectproject,selectsprint,work2);
		
		findWorkList();
	}
	
	public void dragPreMotion(Work work) {
		wm.ChangePreWork(selectproject,selectsprint,work);
		
		DeleteWork(work,work.getWork_name());	
		ChangePanel.changePanel(mainFrame, this, new C_SprintMainPage(mainFrame, projectPage, selectproject, selectsprint, user));
	}


}
