package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.vo.Work;



public class C_ColorDialog extends JPanel implements ActionListener, ChangeListener{
	private MainFrame mf;
	private Dialog colordialog;

	private Work work;
	private JButton savebtn;
	private Color newColor;
	//미리보기	
	public JLabel preview;
	public JColorChooser jcc;
	
	private C_CheckPU checkpu;
	
	public C_ColorDialog() {}
	

	public C_ColorDialog(MainFrame mf,C_CheckPU checkpu) {
		this.mf = mf;
		this.checkpu =checkpu;
		colordialog = new Dialog(mf,"라벨 색상 생성");

		colordialog.setSize(200, 600);
		colordialog.setLayout(new BorderLayout());
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width /2 )  - (colordialog.getWidth() / 2);
		int yPos = (dim.height / 2) - (colordialog.getHeight() / 2);

		colordialog.setLocation(xPos, yPos);
		
		preview = new JLabel("입력한 글자", JLabel.CENTER);
		preview.setForeground(Color.white);
		preview.setBackground(checkpu.getColor());
		preview.setOpaque(true);
		preview.setFont(new Font("SansSerif", Font.BOLD, 15));
		preview.setPreferredSize(new Dimension(60, 60));

		JPanel bannerPanel = new JPanel(new BorderLayout());
		bannerPanel.add(preview, BorderLayout.CENTER);
		bannerPanel.setBorder(BorderFactory.createTitledBorder("미리보기"));

		jcc = new JColorChooser();
		jcc.getSelectionModel().addChangeListener(this);
		jcc.setBorder(BorderFactory.createTitledBorder("바꿀 컬러 선택"));

		//Remove the preview panel
		jcc.setPreviewPanel(new JPanel());

		//Override the chooser panels with our own
		AbstractColorChooserPanel panels[] = { new C_PalettePanel() };
		jcc.setChooserPanels(panels);
		jcc.setColor(preview.getBackground());
		
		/*JButton*/ savebtn = new JButton("저장");
		savebtn.setPreferredSize(new Dimension(200,30));
		savebtn.addActionListener(this);

		colordialog.add(bannerPanel, BorderLayout.PAGE_START);
		//add(panel, BorderLayout.CENTER);
		colordialog.add(jcc, BorderLayout.CENTER);
		colordialog.add(savebtn, BorderLayout.PAGE_END);
		
	}

	public Color getNewColor() {
		return newColor;
	}

	public void setNewColor(Color newColor) {
		this.newColor = newColor;
	}

	public Dialog getColordialog() {
		return colordialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == savebtn) {
			Color saveColor = newColor;
			System.out.println(saveColor);
			
			checkpu.ChangColor(saveColor);
			
			colordialog.dispose();
			
		}else {
			Color newColor = JColorChooser.showDialog(C_ColorDialog.this, "라벨 컬러 선택", preview.getBackground());
			if (newColor != null) {
				preview.setBackground(newColor);
			}
			
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println(newColor);
		/*Color*/ newColor = jcc.getColor();
		preview.setBackground(newColor);
		
	//	work.setLabel_color(newColor);
	}

}
