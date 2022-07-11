package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.vo.Work;

public class C_WorkRenderer extends JPanel implements ListCellRenderer<Work>{
	private JLabel work_Title = new JLabel();
	private JLabel Ass_Label = new JLabel();
	private JButton label = new JButton();
	private JPanel panelText;
	private JPanel emergyIcon;

	//private String allocator = "없음";
	private Work work;

	public C_WorkRenderer() {
		this.setLayout(new BorderLayout(2,2));

		panelText = new JPanel(new GridLayout(1,0));
		panelText.add(work_Title);
		panelText.add(Ass_Label);		

		label.setSize(30, 60);

		panelText.add(label);

		add(panelText, BorderLayout.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Work> list, Work work, int index, boolean isSelected,
			boolean cellHasFocus) {

		work_Title.setText(work.getWork_name());
		work_Title.setFont(new Font("Serif",Font.BOLD,20));
		String allocator = ((work.getAllocator() == null) ? "없음" : work.getAllocator()[0]);
		
		Ass_Label.setText(allocator);
		Ass_Label.setFont(new Font("Serif",Font.BOLD,20));

		//label 버튼
		label.setText(work.getLabel_name());


		//JLabel의 배경색을 변경하는 불투명도 설정
		work_Title.setOpaque(true);
		Ass_Label.setOpaque(true);
		label.setOpaque(true);

		// when select item
		if (isSelected) {
			work_Title.setBackground(list.getSelectionBackground());
			Ass_Label.setBackground(list.getSelectionBackground());
			label.setBackground(work.getLabel_color());
			panelText.setBackground(list.getSelectionBackground());
			//emergyIcon.setBackground(list.getSelectionBackground());
		} else { // when don't select
			work_Title.setBackground(list.getBackground());
			Ass_Label.setBackground(list.getBackground());
			label.setBackground(work.getLabel_color());
			panelText.setBackground(list.getBackground());
			//emergyIcon.setBackground(list.getBackground());	
		}


		return this;

	}

	public JButton getLabel() {
		return label;
	}

	public void setLabel(JButton label) {
		this.label = label;
	}




}


