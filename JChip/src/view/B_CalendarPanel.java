package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.ProjectManager;
import model.vo.Project;

public class B_CalendarPanel extends JPanel implements MouseListener{

	
	private Calendar cal;
	private DefaultTableModel model;
	private JTable calendarTable;
	private JLabel monthName;
	private B_CalendarPanel calendarPanel;
	
	private Project selectedProject;
	
	private int numberOfDays;
	private int startDay;
	private int weeks;
	private String[] sarr;
	private HashMap calendarMap = new HashMap();
	
	private boolean isModified;
	
	
	public B_CalendarPanel(B_ProjectPage projectPage, Project selectedProject) {
		
		this.selectedProject = selectedProject;
		
		if(selectedProject.getCalendarMap() != null) {
			calendarMap = selectedProject.getCalendarMap();
		}
		
		
		this.setBackground(B_ProjectPage.BG_COLOR);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		
		
		JPanel addMemoPanel = new JPanel();
		
		JButton addMemo = new JButton("달력에 일정 추가");
		
		
		
		//calendar -> 캘린더구성할 패널
		JPanel calendar = new JPanel();
		calendar.setBackground(B_ProjectPage.BG_COLOR);
		calendar.setLayout(new BorderLayout());
		
		
		//월이름, 이전달, 다음달 버튼 포함 패널
		JPanel calendarHeadPanel = new JPanel();
		calendarHeadPanel.setBackground(B_ProjectPage.BG_COLOR);
		calendarHeadPanel.setLayout(new BorderLayout());
		calendarHeadPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		
		
		JPanel showTodayPanel = new JPanel();
		showTodayPanel.setBackground(B_ProjectPage.BG_COLOR);
		showTodayPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel showToday = new JLabel();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(today);
		showToday.setFont(new Font("", Font.ITALIC, 17));
		showToday.setText("Today : " + str);
		showTodayPanel.add(showToday);
		calendarHeadPanel.add(showTodayPanel, "North");
		
		
		
		//월이름 보여줄 라벨
		monthName = new JLabel();
		monthName.setHorizontalAlignment(JLabel.CENTER);
		monthName.setFont(new Font("", Font.ITALIC, 20));
		calendarHeadPanel.add(monthName, "Center");
		
		//캘린더 객체
		cal = new GregorianCalendar();
		//이전달로 넘어갈 버튼
		JButton prevMonthBtn = new JButton(new ImageIcon("images/backM1.png"));
		ImageIcon prevMonthBtn2 = new ImageIcon("images/backM2.png");
		prevMonthBtn.setBorderPainted(false);
		prevMonthBtn.setFocusPainted(false);
		prevMonthBtn.setContentAreaFilled(false);
		prevMonthBtn.setRolloverIcon(prevMonthBtn2);
		
		calendarHeadPanel.add(prevMonthBtn, "West");
		prevMonthBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//월 -1
				cal.add(Calendar.MONTH, -1);
				updateMonth();
				
			}
			
		});
		//다음달로 넘어갈 버튼
		JButton nextMonthBtn = new JButton(new ImageIcon("images/nextM1.png"));
		ImageIcon nextMonthBtn2 = new ImageIcon("images/nextM2.png");
		nextMonthBtn.setBorderPainted(false);
		nextMonthBtn.setFocusPainted(false);
		nextMonthBtn.setContentAreaFilled(false);
		nextMonthBtn.setRolloverIcon(nextMonthBtn2);
		calendarHeadPanel.add(nextMonthBtn, "East");
		nextMonthBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//월 +1
				cal.add(Calendar.MONTH, 1);
				updateMonth();
			}
		});
		
		
		
		String[] columns = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		model = new DefaultTableModel(null, columns);
		
		calendarTable = new JTable(model);
		calendarTable.setCellSelectionEnabled(true);
		calendarTable.setRowSelectionAllowed(false);
		calendarTable.setColumnSelectionAllowed(false);
		calendarTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		calendarTable.setFont(new Font("", Font.PLAIN, 15));
		
		calendarTable.getTableHeader().setReorderingAllowed(false);
		
		
		
		calendarTable.getColumn("SUN").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				setBackground(Color.RED);
				setForeground(Color.RED);
				return this;
			}
		});
		
		
		
		calendarTable.getColumn("SUN").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("SUN").setCellEditor(new TextAreaEditor());
		calendarTable.getColumn("MON").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("MON").setCellEditor(new TextAreaEditor());
		calendarTable.getColumn("TUE").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("TUE").setCellEditor(new TextAreaEditor());
		calendarTable.getColumn("WED").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("WED").setCellEditor(new TextAreaEditor());
		calendarTable.getColumn("THU").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("THU").setCellEditor(new TextAreaEditor());
		calendarTable.getColumn("FRI").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("FRI").setCellEditor(new TextAreaEditor());
		calendarTable.getColumn("SAT").setCellRenderer(new TextAreaRenderer());
		calendarTable.getColumn("SAT").setCellEditor(new TextAreaEditor());
		
		
		calendarTable.addMouseListener(this);
		
		
		JScrollPane scroll = new JScrollPane(calendarTable);
		scroll.setSize(calendarTable.getSize());
		scroll.setViewportBorder(BorderFactory.createEmptyBorder());
		scroll.setViewportBorder(null);
		scroll.getViewport().setBackground(B_ProjectPage.BG_COLOR);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		
		calendar.add(calendarHeadPanel, "North");
		calendar.add(scroll, "Center");
		
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(B_ProjectPage.BG_COLOR);
		southPanel.setSize(300, 500);
		calendar.add(southPanel, "South");
		
		
		
		this.add(calendar, "Center");
		
		
		this.updateMonth();
		
		projectPage.add(this);
	}

	
	
	
	
	
	public void updateMonth() {
		//초기값 1일로 set하기
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		//월을 January로 표현하기
		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = cal.get(Calendar.YEAR);
		monthName.setText(month + " " + year);
		
		//int today 
		
		//1일이 무슨요일인지
		startDay = cal.get(Calendar.DAY_OF_WEEK);
		//한달에 몇일인지
		numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//한달에 몇주인지
		weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		model.setRowCount(0);
		model.setRowCount(weeks*2);
		
		for(int r = 0; r < weeks*2; r++) {
			calendarTable.setRowHeight((2*r), 25);
			calendarTable.setRowHeight((2*r + 1), 70);
		}
		
		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			
			model.setValueAt(day + "", (i/7)*2, i%7);
			
			if(calendarMap.get(monthName.getText()) != null) {
				String[] sarr = (String[])calendarMap.get(monthName.getText());
				String str = sarr[day - 1];
				model.setValueAt(str, (i/7*2 + 1), i%7);
			}
			i = i + 1;
		}
	}



	
	public void saveMonth() {
		
		sarr = new String[numberOfDays];
		int i = startDay - 1;
		for (int d = 0; d < numberOfDays; d++) {
			
			sarr[d] = (String)model.getValueAt((i/7*2 + 1), i%7);
			System.out.println(sarr[d]);
			//model.setValueAt(day, i/7, i%7);
			
			i = i + 1;
		}
		calendarMap.put(monthName.getText(), sarr);
		
		new ProjectManager().saveCalendar(selectedProject, calendarMap);
	}
	
	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		isModified = true;
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		
		
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
		if(isModified) {
			saveMonth();
		}
		
	}
	
	
}

class TextAreaRenderer extends JScrollPane implements TableCellRenderer {

	JTextArea textArea;
	public TextAreaRenderer() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		//textArea.setBorder(new TitledBorder("일정"));
		getViewport().add(textArea);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if(isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
			textArea.setForeground(table.getSelectionForeground());
			textArea.setBackground(table.getSelectionBackground());
		}else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
			textArea.setForeground(table.getForeground());
			textArea.setBackground(table.getBackground());
		}
		if(value != null) {
			textArea.setText(value + "");
		}else {
			textArea.setText("");
		}
		textArea.setCaretPosition(0);
		return this;
	}
	
}

class TextAreaEditor extends DefaultCellEditor {

	protected JScrollPane scrollpane;
	protected JTextArea textArea;
	
	public TextAreaEditor() {
		super(new JTextField());
		scrollpane = new JScrollPane();
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollpane.getViewport().add(textArea);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column) {
		if (value != null) {
			textArea.setText(value + "");
		}else {
			textArea.setText("");
		}
		return scrollpane;
	}
	public Object getCellEditorValue() {
		return textArea.getText();
	}
}


