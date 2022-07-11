package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class C_InvitePU extends JPanel {
	private JFrame mf;
	private JPanel mp;
	
	private JButton inviteB; 	//초대버튼
	private JButton closeB; 	//닫기 버튼
	
	public C_InvitePU (JFrame mf) {
		this.mf= mf;
		this.mp = this;
		
		mf.setBounds(200, 200, 300, 200);
		mf.setResizable(false);    //프레임 고정
		
		this.setLayout(null);
		
		JLabel nameL = new JLabel("초대할	이름");
		nameL.setSize(800, 50);
		nameL.setLocation(30, 1);
		
		//초대할 인원의 이름을 넣는다
		JTextField nameT = new JTextField(15);	
		nameT.setSize(200, 30);	
		nameT.setLocation(30, 50);
		
		//초대 버튼을 누르면 TextField에 있는
		//사용자가 초대 된다
		/*JButton */ inviteB = new JButton("초대");	  
		inviteB.setSize(60, 30);				 
		inviteB.setLocation(190, 110);
		
		//닫기 버튼을 누르면 창이 닫아진다
		/*JButton*/ closeB = new JButton("닫기");		
		closeB.setSize(60, 30);
		closeB.setLocation(30, 110);
		closeB.addActionListener(new CloseEvent());
		
		
		this.add(nameL);
		this.add(nameT);
		this.add(inviteB);
		this.add(closeB);
		
		mf.add(this);
		
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private class CloseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == closeB) {
				
				mf.dispose();
			}
		}
		
	}
}
