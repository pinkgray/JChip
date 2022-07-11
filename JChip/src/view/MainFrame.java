package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public static final Color BG_COLOR = new Color(204, 245,255); 
	public static final Color POPUP_COLOR = new Color(1f, 0.7f, 0.7f, 0.8f);
	
	
	public MainFrame() {
		
		
		this.setSize(1024,768);
		
		//메인프레임 위치 조정(화면 가운데에)
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		
		new A_LoginPage(this);
		//new B_ProjectPage(this);
		//new C_SprintMainPage(this);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
	
}
