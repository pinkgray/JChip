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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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

import model.vo.MOM;

public class B_NewMOMPopUp extends JPanel {

	private MainFrame mainFrame;
	private JButton plusButton;
	private Dialog newMompopup;
	
	
	private String MOMTitle;
	private String MOMWriter;
	private String MOMPerson;
	private String MOMDescription;
	private Date MOMDay;
	
	private MOM momSelected;
	
	private int nameCtn = 0;
	private int descriptionCtn = 0;

	public B_NewMOMPopUp(MainFrame mainFrame, B_MOMPanel MOMPanel, MOM momSelected) {

		this.mainFrame = mainFrame;
		this.momSelected = momSelected;
		
		
		newMompopup = new Dialog(mainFrame, "�� ȸ�Ƿ�");
		newMompopup.setLayout(null);
		newMompopup.setUndecorated(true);
		newMompopup.setBackground(B_ProjectPage.POPUP_COLOR);

		// ��ü ��Ʈ ��Ÿ�� ����
		//setUIFont(new javax.swing.plaf.FontUIResource("���� ���", Font.ITALIC, 15));

		// �˾���ġ ����(ȭ�� ���)
		newMompopup.setSize(515, 620);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (newMompopup.getWidth() / 2);
		int yPos = (dim.height / 2) - (newMompopup.getHeight() / 2);
		newMompopup.setLocation(xPos, yPos);

		// �̸�
		JTextField MOMName = new JTextField("ȸ�Ǹ�", 30);
		MOMName.setFont(new Font("���� ���", Font.BOLD, 20));
		MOMName.setLocation(20, 50);
		MOMName.setSize(450, 45);
		newMompopup.add(MOMName);

		// textField�� ���콺 Ŭ���� ������������ ��ȭ������ �ٲ�
		MOMName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �ƹ��͵� �Էµ��� ���� ���¿����� ��ĭ���� �����
				if (nameCtn == 0) {
					MOMName.setText("");
				}
			}
		});

		MOMName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Ű���� �Է� �߻��� ctn++���ְ� mouse�̺�Ʈ �߻����� �ʵ�����
				nameCtn++;
			}
		});

		JLabel writerLabel = new JLabel("�ۼ���");
		writerLabel.setLocation(40, 115);
		writerLabel.setSize(100, 45);
		writerLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		writerLabel.setForeground(Color.WHITE);
		// �۾����� ������� ����
		// writerLabel.setForeground(Color.WHITE);

		newMompopup.add(writerLabel);

		JTextField writer = new JTextField(50);
		writer.setFont(new Font("���� ���", Font.PLAIN, 15));
		writer.setLocation(130, 120);
		writer.setSize(120, 40);
		newMompopup.add(writer);

		// ȸ�� ��¥
		JLabel DayLabel = new JLabel("��¥");
		DayLabel.setLocation(280, 115);
		DayLabel.setSize(140, 40);
		DayLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		DayLabel.setForeground(Color.WHITE);

		// �۾����� ������� ����
		// DayLabel.setForeground(Color.WHITE);
		newMompopup.add(DayLabel);
		// �޷�
		JXDatePicker Day = new DatePicker().getDatePicker();
		JButton btn_pick = (JButton) Day.getComponent(1);
		ImageIcon startIcon = new ImageIcon("images/Calendar.png");
		btn_pick.setIcon(startIcon);
		btn_pick.setBorderPainted(false);
		btn_pick.setFocusPainted(false);
		btn_pick.setContentAreaFilled(false);
		Day.setLocation(330, 120);
		Day.setSize(140, 40);
		Day.setFont(new Font("���� ���", Font.PLAIN, 15));
		newMompopup.add(Day);

		JLabel attend = new JLabel("������");
		attend.setLocation(40, 175);
		attend.setSize(100, 45);
		attend.setFont(new Font("���� ���", Font.BOLD, 20));
		attend.setForeground(Color.WHITE);

		// �۾����� ������� ����
		// attend.setForeground(Color.WHITE);
		newMompopup.add(attend);

		// ������ Text�ʵ�
		JTextField name = new JTextField("�������̸� �߰�", 50);
		name.setFont(new Font("���� ���", Font.PLAIN, 15));
		name.setLocation(130, 185);
		name.setSize(300, 30);
		newMompopup.add(name);

		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				name.setText("");
			}
		});

		// ������ �Է��� +��ư Ŭ���� �Ʒ� textArea�� �Ѿ�� ��
		plusButton = new JButton("+");

		plusButton.setFont(new Font("���� ���", Font.BOLD, 20));
		plusButton.setLocation(440, 175);
		plusButton.setOpaque(false);
		plusButton.setContentAreaFilled(false);
		plusButton.setForeground(Color.WHITE);
		plusButton.setBorder(null);
		plusButton.setSize(30, 40);
		plusButton.setToolTipText("������ �߰�");
		// �۾����� ������� ����
		// plusButton.setForeground(Color.WHITE);

		newMompopup.add(plusButton);

		// �Է��� ������ �����ִ� textArea
		// readOnly (�����Ұ�)
		JTextArea person = new JTextArea(30, 10);
		person.setFont(new Font("���� ���", Font.PLAIN, 15));
		person.setLocation(130, 230);
		person.setSize(350, 50);
		person.setEditable(false);

		newMompopup.add(person);

		plusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)

			{
				String text = name.getText();

				person.append(text + "  ");
				name.setText("");
				name.requestFocus();

			}
		});
		// ���� ������ �߰���
		name.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				if (e.getKeyChar() == '\n') {
					person.append

					(name.getText() + "  ");
					name.setText("");
				}
			}

		});

		// ���� ��
		JLabel text = new JLabel("����");
		text.setLocation(40, 300);
		text.setSize(100, 45);
		text.setFont(new Font("���� ���", Font.BOLD, 20));
		text.setForeground(Color.white);
		// �۾����� ������� ����
		// text.setForeground(Color.WHITE);
		newMompopup.add(text);

		// ���� �ۼ� ĭ
		JTextArea description = new JTextArea("���� �ۼ�", 3, 30);
		description.setFont(new Font("���� ���", Font.PLAIN, 15));
		description.setLocation(130, 300);
		description.setSize(350, 220);
		newMompopup.add(description);

		description.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (descriptionCtn == 0) {
					description.setText("");
				}
			}
		});
		
		if (momSelected != null) {
			MOMName.setText(momSelected.getMOMTitle());
			writer.setText(momSelected.getMOMWriter());
			Day.setDate(momSelected.getMOMDay());
			person.setText(momSelected.getMOMPerson());
			description.setText(momSelected.getMOMDescription());
		
		}
		
		
		// ���� ��ư
		JButton deleteBtn = new JButton(new ImageIcon("images/delete1.png"));
		ImageIcon deleteBtn2 = new ImageIcon("images/delete2.png");
		deleteBtn.setBorderPainted(false);
		deleteBtn.setFocusPainted(false);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setRolloverIcon(deleteBtn2);
		deleteBtn.setLocation(25, 559);
		deleteBtn.setSize(100, 33);
//		deleteBtn.setLocation(170, 550);
//		deleteBtn.setSize(90, 40);
		newMompopup.add(deleteBtn);

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				if (momSelected != null) {
					int answer = JOptionPane.showConfirmDialog(null, "ȸ�Ƿ��� �����Ͻðڽ��ϱ�?");
					if(answer == 0) {
						MOMPanel.deleteMOM(momSelected);
					}
				}
				newMompopup.dispose();

			}
		});



		// ��ҹ�ư
		JButton cancelBtn = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancelBtn2 = new ImageIcon("images/cancelbtn22.png");
		cancelBtn.setBorderPainted(false);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setRolloverIcon(cancelBtn2);
		cancelBtn.setSize(100, 50);
		cancelBtn.setLocation(275, 550);
		newMompopup.add(cancelBtn);

		// ��ҹ�ư Ŭ���� �˾�â ����
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)

			{
				newMompopup.dispose();

			}
		});

		// Ȯ�ι�ư
		JButton okBtn = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon okBtn2 = new ImageIcon("images/okbtn2.png");
		okBtn.setBorderPainted(false);
		okBtn.setFocusPainted(false);
		okBtn.setContentAreaFilled(false);
		okBtn.setRolloverIcon(okBtn2);
		okBtn.setSize(100, 50);
		okBtn.setLocation(385, 550);
		newMompopup.add(okBtn);
		// ��ư�� �̺�Ʈ ����
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȸ�Ƿ� ��Ͽ� ȸ�Ƿ� �̸� �߰��ǰ�, ���� ȸ�Ƿ� ������ �����Ǿ����
				// ���� ȸ�Ƿ� �������� ����Ʈ�� �����Ǿ����
				MOMTitle = MOMName.getText();
				MOMWriter = writer.getText();
				MOMDay = Day.getDate();
				MOMPerson = person.getText();
				MOMDescription = description.getText();
				// ������ ȸ�Ƿ��� ����Ʈ�� �߰��ϴ� �޼ҵ� ȣ��
				if(momSelected == null) {
					MOMPanel.addMOMOnList(MOMTitle, MOMWriter, MOMDay, MOMPerson, MOMDescription);
				}else {
					MOMPanel.modifyMOM(momSelected, MOMTitle, MOMWriter, MOMDay, MOMPerson, MOMDescription);
				}
				

				/*MOM mom = new MOM(MOMTitle, MOMDay);
				try (ObjectOutputStream object = new ObjectOutputStream(
						new FileOutputStream("MOMList/MOM_" + MOMTitle + ".txt"))) {
					object.writeObject(mom);

					object.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}*/

				

				// Ȯ�ι�ư Ŭ���� �˾�â ������
				newMompopup.dispose();

			}
		});

		MOMName.requestFocus();

	}

	public Dialog getMomPopup() {
		return newMompopup;
	}

	// ��ü ��Ʈ���� �޼ҵ�
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}

}
