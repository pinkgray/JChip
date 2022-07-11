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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXDatePicker;

import model.vo.Sprint;

public class B_NewSprintPopUp extends JPanel {
	
	private MainFrame mainFrame;
	private JButton plusButton;
	private Dialog newSprintPopUp;
	private int nameCtn = 0;
	private int descriptionCtn = 0;
	
	//Sprint필드에 저장할 속성
	private String sprintTitle;
	private Date sprintStartDay;
	private Date sprintEndDay;
	private String sprintDetail;
	private String sprintToDo;
	
	private Sprint selectedSprint;
	
	public B_NewSprintPopUp(MainFrame mainFrame, B_SprintPanel sprintPanel, Sprint selectedSprint) {
		this.selectedSprint = selectedSprint;
		
		newSprintPopUp = new Dialog(mainFrame, "새 스프린트 만들기");
		
		//팝업위치조정(화면가운데)
		newSprintPopUp.setSize(515, 680);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (newSprintPopUp.getWidth() / 2);
		int yPos = (dim.height / 2) - (newSprintPopUp.getHeight() / 2);
		newSprintPopUp.setLocation(xPos, yPos);
		
		
		newSprintPopUp.setLayout(null);
		newSprintPopUp.setUndecorated(true);
		newSprintPopUp.setBackground(B_ProjectPage.POPUP_COLOR);
		
		//setUIFont (new javax.swing.plaf.FontUIResource("맑은 고딕", Font.ITALIC, 15));
		
		
		//이름
		JTextField sprintName = new JTextField("새 스프린트 이름", 30);
		sprintName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		if(selectedSprint != null) {
			sprintName.setText(selectedSprint.getSprintTitle());
		}

		sprintName.setLocation(30, 50);				
		sprintName.setSize(450,45);
		newSprintPopUp.add(sprintName);

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
		start.setSize(50, 40);
		start.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		start.setForeground(Color.WHITE);
		newSprintPopUp.add(start);
		
		//시작일 선택
		JXDatePicker startDayPicker = new DatePicker().getDatePicker();
		startDayPicker.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		JButton startbtn_pick = (JButton) startDayPicker.getComponent(1);
		ImageIcon startIcon = new ImageIcon("images/Calendar.png");
		startbtn_pick.setIcon(startIcon);
		startbtn_pick.setBorderPainted(false);
		startbtn_pick.setFocusPainted(false);
		startbtn_pick.setContentAreaFilled(false);
		startDayPicker.setLocation(90, 115);
		startDayPicker.setSize(130, 40);
		if(selectedSprint != null) {
			startDayPicker.setDate(selectedSprint.getSprintStartDay());
		}
		newSprintPopUp.add(startDayPicker);	
		
		
		
		
		//~
		JLabel fromTo = new JLabel("~");
		fromTo.setLocation(240, 115);
		fromTo.setSize(50, 40);
		fromTo.setFont(new Font("",Font.BOLD, 30));
		fromTo.setForeground(Color.WHITE);
		newSprintPopUp.add(fromTo);
		
		//종료일
		JLabel end = new JLabel("종료일");
		end.setLocation(290, 115);
		end.setSize(50, 40);
		end.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		end.setForeground(Color.WHITE);
		
		newSprintPopUp.add(end);


		
		//종료일 선택
		JXDatePicker endDayPicker = new DatePicker().getDatePicker();
		endDayPicker.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		JButton endBtn_pick = (JButton) endDayPicker.getComponent(1);
		ImageIcon endIcon = new ImageIcon("images/Calendar.png");
		endBtn_pick.setIcon(endIcon);
		endBtn_pick.setBorderPainted(false);
		endBtn_pick.setFocusPainted(false);
		endBtn_pick.setContentAreaFilled(false);
		endDayPicker.setLocation(350, 115);
		endDayPicker.setSize(130, 40);
		if(selectedSprint != null) {
			endDayPicker.setDate(selectedSprint.getSprintEndDay());
		}
		newSprintPopUp.add(endDayPicker);
		
		

		
		
		//설명
		JTextArea description = new JTextArea("설명", 3, 30);
		description.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		if(selectedSprint != null) {
			description.setText(selectedSprint.getSprintDetail());
		}
		
		description.setLocation(30, 175);
		description.setSize(453,135);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		
		newSprintPopUp.add(description);
		
		
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
		JTextField toDo = new JTextField("주요 할 일", 50);
		toDo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		toDo.setLocation(30, 330);
		toDo.setSize(410, 45);
		newSprintPopUp.add(toDo);
		
		toDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toDo.setText("");
			}
		});
		
		//할일 입력후 +버튼 클릭시 아래 textArea로 넘어가야 함
		plusButton = new JButton("+");
		
		plusButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		plusButton.setForeground(Color.WHITE);
		plusButton.setLocation(450, 330);
		plusButton.setOpaque(false);
		plusButton.setContentAreaFilled(false);
		plusButton.setBorder(null);
		plusButton.setSize(30, 40);
		
		plusButton.setToolTipText("할 일 추가");
		
		newSprintPopUp.add(plusButton);
		
		

		//입력한 할일 보여주는 textArea
		//readOnly (수정불가)
		JTextArea toDoList = new JTextArea(30, 10);
		if(selectedSprint != null) {
			toDoList.setText(selectedSprint.getSprintToDo());
		}
		
		toDoList.setLocation(30, 390);
		toDoList.setSize(455, 200);
		toDoList.setFont(new Font("", Font.PLAIN, 15));
		toDoList.setEditable(false);
		toDoList.setAutoscrolls(true);
		
		newSprintPopUp.add(toDoList);
		
		//+버튼 입력시 아래 할일 창에 내용 추가됨
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
		
		//스프린트 삭제 버튼
		JButton deleteBtn = new JButton(new ImageIcon("images/delete1.png"));
		ImageIcon deleteBtn2 = new ImageIcon("images/delete2.png");
		deleteBtn.setBorderPainted(false);
		deleteBtn.setFocusPainted(false);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setRolloverIcon(deleteBtn2);
		deleteBtn.setLocation(25, 619);
		deleteBtn.setSize(100, 33);
		newSprintPopUp.add(deleteBtn);
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (selectedSprint != null) {
					int answer = JOptionPane.showConfirmDialog(null, "스프린트를 삭제하시겠습니까?");
					if(answer == 0) {
						sprintPanel.deleteSprint(selectedSprint);
					}
				}
				newSprintPopUp.dispose();
				
			}
		});
		

		//스프린트 생성 취소버튼
		JButton cancelBtn = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelBtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelBtn2);
		cancelBtn.setSize(100,50);
		cancelBtn.setLocation(275,610);
		newSprintPopUp.add(cancelBtn);
		
		//취소버튼 클릭시 스프린트 생성 팝업창 닫힘
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newSprintPopUp.dispose();
				
			}
		});
		
		//스프린트생성 확인버튼
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okBtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false);
		okBtn.setFocusPainted(false);
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okBtn2);
		okBtn.setSize(100,50);
		okBtn.setLocation(385,610);
		newSprintPopUp.add(okBtn);
		
		
		
		//확인버튼 클릭시 이벤트 연결
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sprintTitle = sprintName.getText();
				sprintStartDay = startDayPicker.getDate();
				sprintEndDay = endDayPicker.getDate();
				sprintDetail = description.getText();
				sprintToDo = toDoList.getText();
				newSprintPopUp.dispose();

				if(selectedSprint == null) {
					sprintPanel.addSprintOnList(sprintTitle, sprintStartDay, sprintEndDay, sprintDetail, sprintToDo);
				}else {
					sprintPanel.modifySprint(selectedSprint, sprintTitle, sprintStartDay, sprintEndDay, sprintDetail, sprintToDo);
				}
			}
		});
		
		
		sprintName.requestFocus();
		
		
	}
//
	
	
	public Dialog getNewSprintPopUp() {
		return newSprintPopUp;
	}
	
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	 } 
	
	
}
