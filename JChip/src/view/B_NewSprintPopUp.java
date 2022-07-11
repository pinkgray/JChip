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
	
	//Sprint�ʵ忡 ������ �Ӽ�
	private String sprintTitle;
	private Date sprintStartDay;
	private Date sprintEndDay;
	private String sprintDetail;
	private String sprintToDo;
	
	private Sprint selectedSprint;
	
	public B_NewSprintPopUp(MainFrame mainFrame, B_SprintPanel sprintPanel, Sprint selectedSprint) {
		this.selectedSprint = selectedSprint;
		
		newSprintPopUp = new Dialog(mainFrame, "�� ������Ʈ �����");
		
		//�˾���ġ����(ȭ�鰡�)
		newSprintPopUp.setSize(515, 680);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (newSprintPopUp.getWidth() / 2);
		int yPos = (dim.height / 2) - (newSprintPopUp.getHeight() / 2);
		newSprintPopUp.setLocation(xPos, yPos);
		
		
		newSprintPopUp.setLayout(null);
		newSprintPopUp.setUndecorated(true);
		newSprintPopUp.setBackground(B_ProjectPage.POPUP_COLOR);
		
		//setUIFont (new javax.swing.plaf.FontUIResource("���� ���", Font.ITALIC, 15));
		
		
		//�̸�
		JTextField sprintName = new JTextField("�� ������Ʈ �̸�", 30);
		sprintName.setFont(new Font("���� ���", Font.BOLD, 20));
		if(selectedSprint != null) {
			sprintName.setText(selectedSprint.getSprintTitle());
		}

		sprintName.setLocation(30, 50);				
		sprintName.setSize(450,45);
		newSprintPopUp.add(sprintName);

		//textField�� ���콺 Ŭ���� ������������ ��ȭ������ �ٲ�
		sprintName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//�ƹ��͵� �Էµ��� ���� ���¿����� ��ĭ���� �����
				if (nameCtn == 0) {
					sprintName.setText("");
				}
			}
		});

		
		sprintName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Ű���� �Է� �߻��� ctn++���ְ� mouse�̺�Ʈ �߻����� �ʵ�����
				nameCtn++;
			}
		});
		
		//������
		JLabel start = new JLabel("������");
		start.setLocation(30, 115);
		start.setSize(50, 40);
		start.setFont(new Font("���� ���", Font.BOLD, 15));
		start.setForeground(Color.WHITE);
		newSprintPopUp.add(start);
		
		//������ ����
		JXDatePicker startDayPicker = new DatePicker().getDatePicker();
		startDayPicker.setFont(new Font("���� ���", Font.PLAIN, 15));
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
		
		//������
		JLabel end = new JLabel("������");
		end.setLocation(290, 115);
		end.setSize(50, 40);
		end.setFont(new Font("���� ���", Font.BOLD, 15));
		end.setForeground(Color.WHITE);
		
		newSprintPopUp.add(end);


		
		//������ ����
		JXDatePicker endDayPicker = new DatePicker().getDatePicker();
		endDayPicker.setFont(new Font("���� ���", Font.PLAIN, 15));
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
		
		

		
		
		//����
		JTextArea description = new JTextArea("����", 3, 30);
		description.setFont(new Font("���� ���", Font.PLAIN, 15));
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

		

		//�����Է�
		JTextField toDo = new JTextField("�ֿ� �� ��", 50);
		toDo.setFont(new Font("���� ���", Font.PLAIN, 15));
		toDo.setLocation(30, 330);
		toDo.setSize(410, 45);
		newSprintPopUp.add(toDo);
		
		toDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toDo.setText("");
			}
		});
		
		//���� �Է��� +��ư Ŭ���� �Ʒ� textArea�� �Ѿ�� ��
		plusButton = new JButton("+");
		
		plusButton.setFont(new Font("���� ���", Font.BOLD, 20));
		plusButton.setForeground(Color.WHITE);
		plusButton.setLocation(450, 330);
		plusButton.setOpaque(false);
		plusButton.setContentAreaFilled(false);
		plusButton.setBorder(null);
		plusButton.setSize(30, 40);
		
		plusButton.setToolTipText("�� �� �߰�");
		
		newSprintPopUp.add(plusButton);
		
		

		//�Է��� ���� �����ִ� textArea
		//readOnly (�����Ұ�)
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
		
		//+��ư �Է½� �Ʒ� ���� â�� ���� �߰���
		plusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = toDo.getText();

				toDoList.append(text + "\n");
				toDo.setText("");
				toDo.requestFocus();

			}
		});
		
		//���� �Է��� ���ʹ����� �Ʒ� ���� â�� ���� �߰���
		toDo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				if(e.getKeyChar() == '\n') {
					toDoList.append(toDo.getText() + "\n");
					toDo.setText("");
				}
			}

		});
		
		//������Ʈ ���� ��ư
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
					int answer = JOptionPane.showConfirmDialog(null, "������Ʈ�� �����Ͻðڽ��ϱ�?");
					if(answer == 0) {
						sprintPanel.deleteSprint(selectedSprint);
					}
				}
				newSprintPopUp.dispose();
				
			}
		});
		

		//������Ʈ ���� ��ҹ�ư
		JButton cancelBtn = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelBtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelBtn2);
		cancelBtn.setSize(100,50);
		cancelBtn.setLocation(275,610);
		newSprintPopUp.add(cancelBtn);
		
		//��ҹ�ư Ŭ���� ������Ʈ ���� �˾�â ����
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newSprintPopUp.dispose();
				
			}
		});
		
		//������Ʈ���� Ȯ�ι�ư
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okBtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false);
		okBtn.setFocusPainted(false);
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okBtn2);
		okBtn.setSize(100,50);
		okBtn.setLocation(385,610);
		newSprintPopUp.add(okBtn);
		
		
		
		//Ȯ�ι�ư Ŭ���� �̺�Ʈ ����
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
