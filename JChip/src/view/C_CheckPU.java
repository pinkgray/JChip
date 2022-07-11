package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import model.vo.Project;
import model.vo.Work;

//import org.jdesktop.swingx.JXDatePicker;

public class C_CheckPU extends JPanel implements ActionListener{
	private MainFrame mf;
	private Dialog checkpu;

	private String work_title; 		//���ϸ�
	private JButton iB;				//
	private JButton label_btn;		//�� ���� ��ư
	private Color color;			//����
	private JComboBox asscom;		//�ĺ��ڽ� - �Ҵ���
	private JButton reviseB;		//�ݱ�
	
	private JTextField wN;			//���ϸ� TextField
	private JTextField label_Text;	//�� TextField
	private JTextField fT;			//
	private JTextArea fa;			//�ǵ��
	private JButton insertB;		//�ǵ�� �Է� ��ư
	private JXDatePicker term1DayPicker; //���ڳ�¥
	private JXDatePicker term2DayPicker; //���ᳯ¥
	private JTextArea dT;
	private JButton deleteB;
	
	private String[] ass_member;
	
	private C_OpenPanel openpanel;
	private C_ProgressPanel progresspanel;
	private C_DonePanel donepanel;
	
	
	private Work work;
	private Project project;
	private String work_name;
	private String work_content;
	private String[] Allocator;
	private String label_name;
	private Color btn_color;
	private Date work_start;
	private Date work_end;
	private String feedback;
	
	private String[] checkmember;
	
	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	
	public C_CheckPU(MainFrame mf,Work work,Project project) {
		this.mf = mf;
		this.work = work;
		this.project = project;
		
		checkpu= new Dialog(mf, "���� ����");

		checkpu.setBounds(200, 100, 500, 680);
		checkpu.setResizable(false);     	//������ ����
		checkpu.setUndecorated(true);		//���¹� ���ֱ�
		checkpu.setLayout(null);

		checkpu.setBackground(new Color(66, 66, 66));
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width /2 )  - (checkpu.getWidth() / 2);
		int yPos = (dim.height / 2) - (checkpu.getHeight() / 2);

		checkpu.setLocation(xPos, yPos);

		//���� �̸��̶ߴ� �κ� 
		//CreatePU ���� JTextField wT�� �Է��� ���� ��
		/*JTextField*/ wN = new JTextField(15);
		//wN.setFont(new Font("Serif",Font.BOLD,20));
		wN.setSize(200, 40);
		wN.setLocation(150, 10);
		wN.setText(work.getWork_name());
		//System.out.println(this.getWork_title());
		
		//textField�� ���콺 Ŭ���� ������������ ��ȭ������ �ٲ�
		wN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//�ƹ��͵� �Էµ��� ���� ���¿����� ��ĭ���� �����
				//if (nameCtn == 0) {
				wN.setText("");
				//}
			}
		});

		/*JXDatePicker*/ term1DayPicker = new DatePicker().getDatePicker();
		JButton term1DayPicker_pick = (JButton) term1DayPicker.getComponent(1);
		ImageIcon term1Icon = new ImageIcon("images/Calendar.png");
		term1DayPicker_pick.setIcon(term1Icon);
		term1DayPicker_pick.setBorderPainted(false);
		term1DayPicker_pick.setFocusPainted(false);
		term1DayPicker_pick.setContentAreaFilled(false);
		term1DayPicker.setSize(120, 40);
		term1DayPicker.setLocation(80, 70);
		checkpu.add(term1DayPicker);

		JLabel wave = new JLabel(" ~ ");
		wave.setSize(100, 25);
		wave.setLocation(230, 85);
		wave.setForeground(color.WHITE);
		wave.setFont(new Font("���� ���",Font.BOLD,20));


		/*JXDatePicker*/ term2DayPicker = new DatePicker().getDatePicker();
		JButton term2DayPicker_pick = (JButton) term2DayPicker.getComponent(1);
		ImageIcon term2Icon = new ImageIcon("images/Calendar.png");
		term2DayPicker_pick.setIcon(term2Icon);
		term2DayPicker_pick.setBorderPainted(false);
		term2DayPicker_pick.setFocusPainted(false);
		term2DayPicker_pick.setContentAreaFilled(false);
		term2DayPicker.setSize(120, 40);
		term2DayPicker.setLocation(300, 70);
		checkpu.add(term2DayPicker);


		//���λ����� ���� ���� ��
		//CreatePU ���� JTextField wT�� �Է��� ���� ���� �ȴ�
		/*JTextArea*/ dT = new JTextArea(work.getWork_content());	
		dT.setSize(400, 40);
		dT.setLocation(50, 125);
		//dT.setEditable(false);


		JLabel addMember = new JLabel("�Ҵ���");
		addMember.setSize(100, 70);
		addMember.setLocation(50, 150);
		addMember.setFont(new Font("���� ���",Font.BOLD,15));
		addMember.setForeground(color.WHITE);

		//InvitePU�� �̵��Ѵ� �ʴ��ư�� ������ �Ҵ��ڰ� �ʴ� �ȴ�
		/*JButton*/ iB = new JButton("+");	
		iB.setFont(new Font("���� ���",Font.BOLD,15));
		iB.setSize(60, 30);
		iB.setLocation(80, 170);
		iB.setForeground(color.WHITE);

		//��ư �ڿ� ��� ����� �Լ�
		iB.setBorderPainted(false); iB.setFocusPainted(false); iB.setContentAreaFilled(false);

		
		
		asscom = new JComboBox();

		if (work.getAllocator() == null) {
			asscom.addItem("�����ϼ���");
		}else {
			/*String[]*/ ass_member = new String[work.getAllocator().length];

			for (int i = 0 ; i < ass_member.length ; i++) {
				ass_member[i] = work.getAllocator()[i];

				System.out.println(ass_member[i]);
				asscom.addItem(ass_member[i]);
			}

			//asscom.addItem((String[])ass_member);
		}
		
		asscom.setSize(200,30);
		asscom.setLocation(130, 170);
		asscom.setSelectedIndex(0);

		JLabel label = new JLabel("��");
		label.setFont(new Font("���� ���",Font.BOLD,15));
		label.setSize(40,50);
		label.setLocation(50,210);
		label.setForeground(color.WHITE);

		label_Text = new JTextField(10);
		label_Text.setText(work.getLabel_name());
		label_Text.setSize(200,30);
		label_Text.setLocation(100,220);
		

		//textField�� ���콺 Ŭ���� ������������ ��ȭ������ �ٲ�
		label_Text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//�ƹ��͵� �Էµ��� ���� ���¿����� ��ĭ���� �����
				//if (nameCtn == 0) {
				label_Text.setText("");
				//}
			}
		});
		

		label_btn = new JButton();
		label_btn.setSize(30, 30);
		label_btn.setLocation(310, 220);
		label_btn.setBackground(work.getLabel_color());

		label_btn.addActionListener(this);

		JLabel feedback = new JLabel("�ǵ��");	
		feedback.setSize(100, 70);
		feedback.setLocation(50, 270);
		feedback.setFont(new Font("���� ���", Font.BOLD, 15));
		feedback.setForeground(color.WHITE);
		iB.setFont(new Font("���� ���",Font.BOLD,18));

		iB.addActionListener(this);


		//�ǵ���� �Է� �Ѵ�
		/*JTextField*/ fT = new JTextField(100);	
		fT.setSize(300, 65);
		fT.setLocation(50, 320);



		//�Է¹�ư�� ������ ���� �ǵ�� �������� �Է°��� ���޵ȴ�
		insertB = new JButton(new ImageIcon("images/insert1.png"));
		ImageIcon insertb = new ImageIcon("images/insert2.png");
		insertB.setBorderPainted(false);
		insertB.setFocusPainted(false);
		insertB.setContentAreaFilled(false);
		insertB.setRolloverIcon(insertb);
		
		insertB.setSize(65, 65);
		insertB.setLocation(360, 320);
		
		insertB.addActionListener(this);

		JLabel fDL = new JLabel("�ǵ�鳻��");	
		fDL.setSize(100, 70);
		fDL.setLocation(50, 365);
		fDL.setFont(new Font("���� ���", Font.BOLD, 15));
		fDL.setForeground(color.WHITE);

		//�ǵ���� �� �Է��ϸ�, �̰��� ���� �ȴ�
		/*JTextArea*/ fa = new JTextArea();
		fa.setEditable(false);
		/*fa.setSize(365, 270);
		fa.setLocation(50, 410);*/
		fa.setText(work.getFeedback());

		JScrollPane faScroller = new JScrollPane(fa);
		faScroller.setSize(365, 160);
		faScroller.setLocation(50, 410);

		fT.addActionListener(this);

		/*JButton*/ deleteB = new JButton(new ImageIcon("images/delete1.png")); //������ ������ ���� �Ѵ�
		ImageIcon deleteb = new ImageIcon("images/delete2.png");
		deleteB.setBorderPainted(false);
		deleteB.setFocusPainted(false);
		deleteB.setContentAreaFilled(false);
		deleteB.setRolloverIcon(deleteb);
		
		deleteB.setSize(100,33);
		deleteB.setLocation(50, 600);


		/*JButton*/ reviseB = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon reviseb = new ImageIcon("images/okbtn2.png");
		reviseB.setBorderPainted(false);
		reviseB.setFocusPainted(false);
		reviseB.setContentAreaFilled(false);
		reviseB.setRolloverIcon(reviseb);
		
		reviseB.setSize(100,33);
		reviseB.setLocation(350, 600);


		

		checkpu.add(wN);
		checkpu.add(dT);
		checkpu.add(wave);
		checkpu.add(addMember);
		checkpu.add(iB);
		checkpu.add(asscom);
		checkpu.add(label);
		checkpu.add(label_Text);
		checkpu.add(label_btn);
		checkpu.add(feedback);
		checkpu.add(fT);
		checkpu.add(insertB);
		checkpu.add(fDL);
		checkpu.add(faScroller);
		checkpu.add(deleteB);
		checkpu.add(reviseB);
		
	}
	
	//CheckPopUp ������ ������ ���λ����� ���̴� â
	public C_CheckPU(MainFrame mf,Work work,Project project,C_OpenPanel openpanel) {
		this(mf,work,project);
		
		this.openpanel = openpanel;
		
		reviseB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 work_name = wN.getText();
				 work_content = dT.getText();
				 Allocator = ass_member;
				 label_name = label_Text.getText();
				 btn_color = label_btn.getBackground();
				 work_start = term1DayPicker.getDate();
				 work_end = term2DayPicker.getDate();
				 feedback = fa.getText();
				 
				 modifyWork(work_name,work_start,work_end,work_content,Allocator,feedback,label_name,btn_color,openpanel);
			
				
				checkpu.dispose();
			}
			
		});
		
		deleteB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				deleteWork(work_name,openpanel);
				
				checkpu.dispose();
				
			}
			
		});
	}
	
	public C_CheckPU(MainFrame mf, Work work,Project project, C_ProgressPanel progresspanel) {
		this(mf,work,project);
		
		this.progresspanel = progresspanel;
		
		reviseB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				work_name = wN.getText();
				 work_content = dT.getText();
				 Allocator = ass_member;
				 label_name = label_Text.getText();
				 btn_color = label_btn.getBackground();
				 work_start = term1DayPicker.getDate();
				 work_end = term2DayPicker.getDate();
				 feedback = fa.getText();
				 
				modifyWork(work_name,work_start,work_end,work_content,Allocator,feedback,label_name,btn_color,progresspanel);
				
				checkpu.dispose();
			}
			
		});
		
		deleteB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				deleteWork(work_name,progresspanel);
				
				checkpu.dispose();
				
			}
		});
	}
	
	public C_CheckPU(MainFrame mf, Work work,Project project, C_DonePanel donepanel) {
		this(mf,work,project);
		
		this.donepanel = donepanel;
		
		reviseB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				work_name = wN.getText();
				 work_content = dT.getText();
				 Allocator = ass_member;
				 label_name = label_Text.getText();
				 btn_color = label_btn.getBackground();
				 work_start = term1DayPicker.getDate();
				 work_end = term2DayPicker.getDate();
				 feedback = fa.getText();
				 
				modifyWork(work_name,work_start,work_end,work_content,Allocator,feedback,label_name,btn_color,donepanel);
				
				checkpu.dispose();
			}
			
		});
		
		deleteB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				deleteWork(work_name,donepanel);
				
				checkpu.dispose();
				
			}
		});
		
		
	}


	public String getWork_title() {
		return work_title;
	}


	public void setWork_title(String work_title) {
		this.work_title = work_title;
	}


	//�ݱ�
	private class CloseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			checkpu.dispose();
		}

	}


	public Dialog getCheckPU() {
		return checkpu;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == iB) {
			String message = "�Ҵ��ų ����� ������.";
			new C_SprintDialog(this.mf,this,message,project).getAsspanel().setVisible(true);
		}

		if (e.getSource() == label_btn) {
			new C_ColorDialog(mf,this).getColordialog().setVisible(true);
		}
		
		if (e.getSource() == fT || e.getSource() == insertB) {
			String text = fT.getText();
			fa.append(text + "\n");

			fT.setText("");
			fT.requestFocus();
			
		}
	}


	public void ChangColor(Color color) {
		label_btn.setBackground(color);
		
		checkpu.revalidate();
	}
	
	public void ChangeAss(String str) {
		//�ʱ�ȭ
		asscom.removeAllItems();
	
		//System.out.println("----------------------");
		//System.out.println(str);
		
		String[] sarr = str.split("/");
		
		HashSet<String> memberSet = new HashSet<String>();
		
		for (int i = 0 ; i < sarr.length ; i++) {
			memberSet.add(sarr[i]);
			
		}
		
		//System.out.println(memberSet);
		
		Object[] objass = memberSet.toArray();
		
		/*String[]*/ checkmember = new String[objass.length];
		
		for (int i = 0 ; i < checkmember.length ; i++) {
			checkmember[i] = (String)objass[i];
			
		}
		
		work.setAllocator(checkmember);
		
		for (int i = 0 ; i < checkmember.length ; i++) {
			checkmember[i] = work.getAllocator()[i];
			
			asscom.addItem(checkmember[i]);
		}
		
		ass_member = checkmember;
		
		checkpu.revalidate();
	}
	
	public void modifyWork(String work_name, Date work_start , Date work_end
			,String work_content , String[] allocator , String feedback,String label_name , Color label_color,JPanel panel) {

		if (panel == openpanel) {
			openpanel.ModifyWork(work, work_name, work_start, work_end,work_content , allocator, feedback , label_name, label_color);			
		}
		
		if (panel == progresspanel) {
			progresspanel.ModifyWork(work, work_name, work_start, work_end,work_content , allocator, feedback , label_name, label_color);			
		}

		if (panel == donepanel) {
			donepanel.ModifyWork(work, work_name, work_start, work_end,work_content , allocator, feedback , label_name, label_color);			
		}
	}
	
	public void deleteWork(String work_name,JPanel panel){
		if (panel == openpanel) {
			openpanel.DeleteWork(work,work_name);
		}
		
		if (panel == progresspanel) {
			progresspanel.DeleteWork(work,work_name);
		}
		
		if (panel == donepanel) {
			donepanel.DeleteWork(work,work_name);
		}
	}
}
