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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import controller.WorkManager;
import model.vo.Work;

//import org.jdesktop.swingx.JXDatePicker;

public class C_CreatePU extends JPanel{
	private MainFrame mf;
	private Dialog cp;

	private JButton saveB;
	private JTextField work_subjectText;
	private JTextField wT;
	private JTextField dT;
	private JXDatePicker term1DayPicker;
	private JXDatePicker term2DayPicker;
	
	private String work_name;
	private String work_subject;
	private String inf;
	private String label_name;
	private Color label_color;
	private String work_content;
	private Date work_start;
	private Date work_end ;
	
	private WorkManager wm = new WorkManager();

	public C_CreatePU(MainFrame mf) {
		cp = new Dialog(mf,"할일 생성");

		cp.setSize(390,400);
		cp.setResizable(false);    //프레임 고정
		cp.setLayout(null);

		//프레임 창 안보이게
		cp.setUndecorated(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width /2 )  - (cp.getWidth() / 2);
		int yPos = (dim.height / 2) - (cp.getHeight() / 2);

		cp.setLocation(xPos, yPos);
		cp.setBackground(new Color(66,66,66));

		JLabel wL = new JLabel("할일 이름");	
		wL.setFont(new Font("맑은고딕", Font.BOLD, 15));
		wL.setForeground(Color.white);
		wL.setLocation(30, 4);
		wL.setSize(100, 100);


		//사용자가 할일의 이름을 입력하여 이름을 정해준다

		wT = new JTextField(15);	//할일 TextField
		wT.setLocation(100, 40);			
		wT.setSize(230, 25);



		//기간 Label
		JLabel L_tearm = new JLabel("기간");
		L_tearm.setFont(new Font("맑은 고딕",Font.BOLD,15));
		L_tearm.setForeground(Color.WHITE);
		L_tearm.setSize(100, 25);					
		L_tearm.setLocation(30, 80);    	//30,180


		term1DayPicker = new DatePicker().getDatePicker();
		JButton term1DayPicker_pick = (JButton) term1DayPicker.getComponent(1);
		ImageIcon term1Icon = new ImageIcon("images/Calendar.png");
		term1DayPicker_pick.setIcon(term1Icon);
		term1DayPicker_pick.setBorderPainted(false);
		term1DayPicker_pick.setFocusPainted(false);
		term1DayPicker_pick.setContentAreaFilled(false);
		term1DayPicker.setLocation(30, 110);	//30,210
		term1DayPicker.setSize(120, 40);
		cp.add(term1DayPicker);

		JLabel wave = new JLabel(" ~ ");
		wave.setSize(60, 50);
		wave.setFont(new Font("맑은 고딕",Font.BOLD,15));
		wave.setForeground(Color.white);
		wave.setLocation(170, 100);


		term2DayPicker = new DatePicker().getDatePicker();
		JButton term2DayPicker_pick = (JButton) term2DayPicker.getComponent(1);
		ImageIcon term2Icon = new ImageIcon("images/Calendar.png");
		term2DayPicker_pick.setIcon(term2Icon);
		term2DayPicker_pick.setBorderPainted(false);
		term2DayPicker_pick.setFocusPainted(false);
		term2DayPicker_pick.setContentAreaFilled(false);
		term2DayPicker.setLocation(220, 110);
		term2DayPicker.setSize(120, 40);
		cp.add(term2DayPicker);

		JLabel dL = new JLabel("세부 내용");
		dL.setFont(new Font("맑은 고딕",Font.BOLD,15));
		dL.setForeground(Color.WHITE);
		dL.setLocation(30, 130);			//30,40
		dL.setSize(100, 100);

		//사용자가 세부내용을 입력하는 부분, 입력을 끝내면 CheckPU dT 텍스트 필드에서 보여진다
		/*JTextField*/ dT = new JTextField("세부 사항을 입력 하시오",100);  //세부사항 TextField 
		dT.setLocation(30, 190);			
		dT.setSize(300, 70);

		//textField에 마우스 클릭시 내용지워지고 빈화면으로 바뀜
		dT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//아무것도 입력되지 않은 상태에서만 빈칸으로 만들기
				//if (nameCtn == 0) {
				dT.setText("");
				//}
			}
		});


		//worksubject !!!!!!!
		JLabel work_subjectLable = new JLabel("Label");
		work_subjectLable.setFont(new Font("맑은 고딕",Font.BOLD,15));
		work_subjectLable.setForeground(Color.white);
		work_subjectLable.setSize(110, 110);
		work_subjectLable.setLocation(30, 235);

		//긴급 textField
		/*JTextField*/ work_subjectText = new JTextField("Label 입력",15);
		work_subjectText.setSize(150, 25);
		work_subjectText.setLocation(100, 280);

		//textField에 마우스 클릭시 내용지워지고 빈화면으로 바뀜
		work_subjectText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//아무것도 입력되지 않은 상태에서만 빈칸으로 만들기
				//if (nameCtn == 0) {
				work_subjectText.setText("");
				//}
			}
		});

		//닫기 버튼을 누르면 창이 닫아진다
		JButton closeB = new JButton(new ImageIcon("images/cancelbtn11.png"));	
		ImageIcon closeb = new ImageIcon("images/cancelbtn22.png");
		closeB.setBorderPainted(false);
		closeB.setFocusPainted(false);
		closeB.setContentAreaFilled(false);
		closeB.setRolloverIcon(closeb);
		
		closeB.setSize(100,33);
		closeB.setLocation(30, 335);

		closeB.addActionListener(new CloseEvent());

		//저장 버튼을 누르면 변경 사항이 저장이 되어, openpanel에 올라간다
		/*JButton*/ saveB = new JButton(new ImageIcon("images/okbtn1.png"));
		ImageIcon saveb = new ImageIcon("images/okbtn2.png");
		saveB.setBorderPainted(false);
		saveB.setFocusPainted(false);
		saveB.setContentAreaFilled(false);
		saveB.setRolloverIcon(saveb);
		
		saveB.setSize(100,33);					
		saveB.setLocation(260, 335);

		cp.add(wL);
		cp.add(dL);
		cp.add(wT);
		cp.add(dT);
		cp.add(L_tearm);
		cp.add(wave);
		cp.add(work_subjectText);
		cp.add(work_subjectLable);
		cp.add(closeB);
		cp.add(saveB);
	}
	
	public C_CreatePU(MainFrame mf, C_OpenPanel openPanel) {
		
		this(mf);
		
		
		saveB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				work_name = wT.getText();
				work_subject = work_subjectText.getText();
				work_content = dT.getText();
				work_start = term1DayPicker.getDate();
				work_end = term2DayPicker.getDate();


				Work work = new Work();
				work.setWork_name(work_name);
				work.setLabel_name(work_subject);
				work.setWork_content(dT.getText());
				work.setWork_start(term1DayPicker.getDate());
				work.setWork_end(term2DayPicker.getDate());

				work.setWork_inf("open");
				inf = work.getWork_inf();
				//label_name = work.getLabel_name();
				label_color = work.getLabel_color();



				openPanel.makeNewWork(work_name, work_start, work_end,work_content, work_subject, label_color,null, null);


				cp.dispose();
			}
		});
	}

		
	public C_CreatePU(MainFrame mf, C_ProgressPanel progressPanel) {
		this(mf);

		saveB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				work_name = wT.getText();
				work_subject = work_subjectText.getText();
				work_content = dT.getText();
				work_start = term1DayPicker.getDate();
				work_end = term2DayPicker.getDate();


				Work work = new Work();
				work.setWork_name(work_name);
				work.setLabel_name(work_subject);
				work.setWork_content(dT.getText());
				work.setWork_start(term1DayPicker.getDate());
				work.setWork_end(term2DayPicker.getDate());

				work.setWork_inf("open");
				inf = work.getWork_inf();
				//label_name = work.getLabel_name();
				label_color = work.getLabel_color();



				progressPanel.makeNewWork(work_name, work_start, work_end,work_content, work_subject, label_color,null, null);

				cp.dispose();
			}
		});
	}

	public C_CreatePU(MainFrame mf, C_DonePanel DoenPanel) {
		this(mf);
		
		saveB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				work_name = wT.getText();
				work_subject = work_subjectText.getText();
				work_content = dT.getText();
				work_start = term1DayPicker.getDate();
				work_end = term2DayPicker.getDate();


				Work work = new Work();
				work.setWork_name(work_name);
				work.setLabel_name(work_subject);
				work.setWork_content(dT.getText());
				work.setWork_start(term1DayPicker.getDate());
				work.setWork_end(term2DayPicker.getDate());

				work.setWork_inf("open");
				inf = work.getWork_inf();
				//label_name = work.getLabel_name();
				label_color = work.getLabel_color();



				DoenPanel.makeNewWork(work_name, work_start, work_end,work_content, work_subject, label_color,null, null);



				cp.dispose();
			}
		});
		
	}


	//닫기
	private class CloseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cp.dispose();
		}

	}

	public Dialog getCreatePU() {
		return cp;
	}
}
