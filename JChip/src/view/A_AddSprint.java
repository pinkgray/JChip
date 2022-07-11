package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import controller.ProjectManager;
import model.vo.Project;
import model.vo.Sprint;

public class A_AddSprint {

	private MainFrame mf;
	private A_MainPage mainPage;

	private JButton plusButton;
	private Dialog AddSprint;

	private int nameCtn = 0;
	private int descriptionCtn = 0;
	
	private Project project;
	private String sprintTitle;
	private Date sprintStartDay;
	private Date sprintEndDay;
	private String sprintDetail;
	private String sprintToDo;


	public A_AddSprint(MainFrame mf, A_MainPage mainPage, A_AddProject addProject, Project project) {
		this.mf = mf;
		this.mainPage = mainPage;
		this.project = project;
		
		
		AddSprint = new Dialog(mf, "새 스프린트 만들기"); 
		AddSprint.setLayout(null);                 


		//팝업위치조정(화면가운데)
		AddSprint.setSize(515, 680);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (AddSprint.getWidth() / 2);
		int yPos = (dim.height / 2) - (AddSprint.getHeight() / 2);
		AddSprint.setLocation(xPos, yPos);


		AddSprint.setUndecorated(true);
		AddSprint.setBackground(new Color(66, 66, 66, 220));


		//이름
		JTextField sprintName = new JTextField("새 스프린트 이름", 30);
		sprintName.setFont(new Font("맑은 고딕",Font.BOLD, 20));
		sprintName.setLocation(20, 50);            
		sprintName.setSize(450,45);
		AddSprint.add(sprintName);

		//textField에 마우스 클릭시 내용지워지고 빈화면으로 바뀜
		sprintName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//아무것도 입력되지 않은 상태에서만 빈칸으로 만들기
				if (nameCtn == 0) {
					sprintName.setText("");
				}
			}
		});

		sprintName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//키보드 입력 발생시 ctn++해주고 mouse이벤트 발생하지 않도록함
				nameCtn++;
			}
		});



		//시작일
		JLabel start = new JLabel("시작일");
		start.setLocation(30, 115);
		start.setSize(100, 40);
		start.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		start.setForeground(Color.WHITE);
		AddSprint.add(start);


		//시작일 선택
		JXDatePicker startDayPicker = new DatePicker().getDatePicker();
		JButton startDayButton = (JButton) startDayPicker.getComponent(1);
		ImageIcon startIcon = new ImageIcon("images/Calendar.png");
		startDayButton.setIcon(startIcon);
		startDayButton.setBorderPainted(false);
		startDayButton.setFocusPainted(false);
		startDayButton.setContentAreaFilled(false);
		
		startDayPicker.setLocation(100, 115);
		startDayPicker.setSize(130, 40);
		startDayPicker.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		AddSprint.add(startDayPicker);   


		//~
		JLabel fromTo = new JLabel("~");
		fromTo.setLocation(240, 115);
		fromTo.setSize(308,40);
		fromTo.setFont(new Font("맑은 고딕",Font.PLAIN, 30));
		fromTo.setForeground(Color.WHITE);
		AddSprint.add(fromTo);

		//종료일
		JLabel end = new JLabel("종료일");
		end.setLocation(280, 115);
		end.setSize(100, 40);
		end	.setForeground(Color.WHITE);
		end.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		AddSprint.add(end);
		

		//종료일 선택
		JXDatePicker endDayPicker = new DatePicker().getDatePicker();
		JButton endDayButton = (JButton) endDayPicker.getComponent(1);
		ImageIcon endtIcon = new ImageIcon("images/Calendar.png");
		endDayButton.setIcon(endtIcon);
		endDayButton.setBorderPainted(false);
		endDayButton.setFocusPainted(false);
		endDayButton.setContentAreaFilled(false);
		
		endDayPicker.setLocation(340, 115);
		endDayPicker.setSize(130, 40);
		endDayPicker.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		AddSprint.add(endDayPicker);



		//설명
		JTextArea description = new JTextArea("설명", 3, 30);
		description.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		description.setLocation(20, 175);
		description.setSize(453,135);
		AddSprint.add(description);

		description.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(descriptionCtn == 0) {
					description.setText("");
				}
			}
		});

		description.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				descriptionCtn++;
			}
		});


		//할일입력
		JTextField toDo = new JTextField("할일", 50);
		toDo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		toDo.setLocation(20, 330);
		toDo.setSize(410, 45);
		AddSprint.add(toDo);

		toDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toDo.setText("");
			}
		});

		//할일 입력후 +버튼 클릭시 아래 textArea로 넘어가야 함
		plusButton = new JButton("+");

		plusButton.setFont(new Font("",Font.PLAIN, 20));
		plusButton.setForeground(Color.WHITE);
		plusButton.setLocation(440, 330);
		plusButton.setOpaque(false);
		plusButton.setBackground(Color.lightGray);
		plusButton.setBorder(null);
		plusButton.setSize(30, 40);

		AddSprint.add(plusButton);



		//입력한 할일 보여주는 textArea
		//readOnly (수정불가)
		JTextArea toDoList = new JTextArea(30, 10);
		toDoList.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		toDoList.setLocation(20, 390);
		toDoList.setSize(455, 200);
		toDoList.setEditable(false);

		AddSprint.add(toDoList);

		//할일 추가버튼 클릭시 이벤트 연결
		plusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = toDo.getText();

				toDoList.append(text + "\n");
				toDo.setText("");
				toDo.requestFocus();

			}
		});

		//할일 입력후 엔터눌러도 아래 할일 창에 내용 추가됨
		toDo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				if(e.getKeyChar() == '\n') {
					toDoList.append(toDo.getText() + "\n");
					toDo.setText("");
				}
			}

		});

		//스프린트 생성 취소버튼
		JButton cancelBtn  = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelbtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false); 
		cancelBtn.setFocusPainted(false); 
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelbtn2);
		
		
		cancelBtn.setLocation(282,615);
		cancelBtn.setSize(90,40);
		AddSprint.add(cancelBtn);

		//취소버튼 클릭시 스프린트 생성 팝업창 닫힘
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddSprint.dispose();

			}
		});

		//스프린트생성 확인버튼
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okbtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false); 
		okBtn.setFocusPainted(false); 
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okbtn2);
		okBtn.setLocation(392,615);
		okBtn.setSize(100,40);
		AddSprint.add(okBtn);
		
		//확인버튼 클릭시 이벤트 연결
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sprintTitle = sprintName.getText();
				sprintStartDay = startDayPicker.getDate();
				sprintEndDay = endDayPicker.getDate();
				sprintDetail = description.getText();
				sprintToDo = toDoList.getText();
				
				if(project == null) {
					Sprint newSprint = new ProjectManager().makeNewSprint(sprintTitle, sprintStartDay, sprintEndDay, sprintDetail, sprintToDo);
					addProject.putSprintOnList(newSprint);
				}else {
					Project projectUpdated = new ProjectManager().addNewSprint(project, sprintTitle, sprintStartDay, sprintEndDay, sprintDetail, sprintToDo);
					addProject.updateSprintList(projectUpdated);
				}
				
				AddSprint.dispose();
			}
		});

		AddSprint.add(okBtn);
		AddSprint.setResizable(false); 

	}
//
	
	
	
	public Dialog getAddSprint() {
		return AddSprint;
	}
	
}