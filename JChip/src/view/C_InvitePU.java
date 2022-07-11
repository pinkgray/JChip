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
	
	private JButton inviteB; 	//�ʴ��ư
	private JButton closeB; 	//�ݱ� ��ư
	
	public C_InvitePU (JFrame mf) {
		this.mf= mf;
		this.mp = this;
		
		mf.setBounds(200, 200, 300, 200);
		mf.setResizable(false);    //������ ����
		
		this.setLayout(null);
		
		JLabel nameL = new JLabel("�ʴ���	�̸�");
		nameL.setSize(800, 50);
		nameL.setLocation(30, 1);
		
		//�ʴ��� �ο��� �̸��� �ִ´�
		JTextField nameT = new JTextField(15);	
		nameT.setSize(200, 30);	
		nameT.setLocation(30, 50);
		
		//�ʴ� ��ư�� ������ TextField�� �ִ�
		//����ڰ� �ʴ� �ȴ�
		/*JButton */ inviteB = new JButton("�ʴ�");	  
		inviteB.setSize(60, 30);				 
		inviteB.setLocation(190, 110);
		
		//�ݱ� ��ư�� ������ â�� �ݾ�����
		/*JButton*/ closeB = new JButton("�ݱ�");		
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
