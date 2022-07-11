package view;

import javax.swing.JPanel;

public class ChangePanel {
	public static void changePanel(MainFrame mf, JPanel op, JPanel np) {
		
		//mf.remove(op);
		//mf.add(np);
		//mf.repaint();
		
		//panel체인지 코드 바꿈
		mf.setContentPane(np);
		mf.invalidate();
		mf.validate();
		
	}
}
