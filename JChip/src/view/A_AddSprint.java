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
		
		
		AddSprint = new Dialog(mf, "�� ������Ʈ �����"); 
		AddSprint.setLayout(null);                 


		//�˾���ġ����(ȭ�鰡�)
		AddSprint.setSize(515, 680);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (AddSprint.getWidth() / 2);
		int yPos = (dim.height / 2) - (AddSprint.getHeight() / 2);
		AddSprint.setLocation(xPos, yPos);


		AddSprint.setUndecorated(true);
		AddSprint.setBackground(new Color(66, 66, 66, 220));


		//�̸�
		JTextField sprintName = new JTextField("�� ������Ʈ �̸�", 30);
		sprintName.setFont(new Font("���� ���",Font.BOLD, 20));
		sprintName.setLocation(20, 50);            
		sprintName.setSize(450,45);
		AddSprint.add(sprintName);

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
		start.setSize(100, 40);
		start.setFont(new Font("���� ���", Font.BOLD, 20));
		start.setForeground(Color.WHITE);
		AddSprint.add(start);


		//������ ����
		JXDatePicker startDayPicker = new DatePicker().getDatePicker();
		JButton startDayButton = (JButton) startDayPicker.getComponent(1);
		ImageIcon startIcon = new ImageIcon("images/Calendar.png");
		startDayButton.setIcon(startIcon);
		startDayButton.setBorderPainted(false);
		startDayButton.setFocusPainted(false);
		startDayButton.setContentAreaFilled(false);
		
		startDayPicker.setLocation(100, 115);
		startDayPicker.setSize(130, 40);
		startDayPicker.setFont(new Font("���� ���", Font.PLAIN, 15));
		AddSprint.add(startDayPicker);   


		//~
		JLabel fromTo = new JLabel("~");
		fromTo.setLocation(240, 115);
		fromTo.setSize(308,40);
		fromTo.setFont(new Font("���� ���",Font.PLAIN, 30));
		fromTo.setForeground(Color.WHITE);
		AddSprint.add(fromTo);

		//������
		JLabel end = new JLabel("������");
		end.setLocation(280, 115);
		end.setSize(100, 40);
		end	.setForeground(Color.WHITE);
		end.setFont(new Font("���� ���", Font.BOLD, 20));
		AddSprint.add(end);
		

		//������ ����
		JXDatePicker endDayPicker = new DatePicker().getDatePicker();
		JButton endDayButton = (JButton) endDayPicker.getComponent(1);
		ImageIcon endtIcon = new ImageIcon("images/Calendar.png");
		endDayButton.setIcon(endtIcon);
		endDayButton.setBorderPainted(false);
		endDayButton.setFocusPainted(false);
		endDayButton.setContentAreaFilled(false);
		
		endDayPicker.setLocation(340, 115);
		endDayPicker.setSize(130, 40);
		endDayPicker.setFont(new Font("���� ���", Font.PLAIN, 15));
		AddSprint.add(endDayPicker);



		//����
		JTextArea description = new JTextArea("����", 3, 30);
		description.setFont(new Font("���� ���", Font.PLAIN, 15));
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


		//�����Է�
		JTextField toDo = new JTextField("����", 50);
		toDo.setFont(new Font("���� ���", Font.PLAIN, 15));
		toDo.setLocation(20, 330);
		toDo.setSize(410, 45);
		AddSprint.add(toDo);

		toDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toDo.setText("");
			}
		});

		//���� �Է��� +��ư Ŭ���� �Ʒ� textArea�� �Ѿ�� ��
		plusButton = new JButton("+");

		plusButton.setFont(new Font("",Font.PLAIN, 20));
		plusButton.setForeground(Color.WHITE);
		plusButton.setLocation(440, 330);
		plusButton.setOpaque(false);
		plusButton.setBackground(Color.lightGray);
		plusButton.setBorder(null);
		plusButton.setSize(30, 40);

		AddSprint.add(plusButton);



		//�Է��� ���� �����ִ� textArea
		//readOnly (�����Ұ�)
		JTextArea toDoList = new JTextArea(30, 10);
		toDoList.setFont(new Font("���� ���", Font.PLAIN, 15));
		toDoList.setLocation(20, 390);
		toDoList.setSize(455, 200);
		toDoList.setEditable(false);

		AddSprint.add(toDoList);

		//���� �߰���ư Ŭ���� �̺�Ʈ ����
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

		//������Ʈ ���� ��ҹ�ư
		JButton cancelBtn  = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelbtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false); 
		cancelBtn.setFocusPainted(false); 
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelbtn2);
		
		
		cancelBtn.setLocation(282,615);
		cancelBtn.setSize(90,40);
		AddSprint.add(cancelBtn);

		//��ҹ�ư Ŭ���� ������Ʈ ���� �˾�â ����
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddSprint.dispose();

			}
		});

		//������Ʈ���� Ȯ�ι�ư
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okbtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false); 
		okBtn.setFocusPainted(false); 
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okbtn2);
		okBtn.setLocation(392,615);
		okBtn.setSize(100,40);
		AddSprint.add(okBtn);
		
		//Ȯ�ι�ư Ŭ���� �̺�Ʈ ����
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