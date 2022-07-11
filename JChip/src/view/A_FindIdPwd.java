package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.A_MemberManager;
import model.dao.A_MemberDao;

public class A_FindIdPwd extends JPanel {
	
	private MainFrame mf;
	private Dialog a_findIdPwd;
	private A_MemberDao memberDao = new A_MemberDao();
	private A_MemberManager a_mm = new A_MemberManager();
	
	
	public A_FindIdPwd(MainFrame mf) {
		
		a_findIdPwd = new Dialog(mf,"æ∆¿Ãµ/∫Òπ–π¯»£ √£±‚");
		a_findIdPwd.setSize(620, 420);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (a_findIdPwd.getWidth() / 2);
		int yPos = (dim.height / 2) - (a_findIdPwd.getHeight() / 2);
		a_findIdPwd.setLocation(xPos, yPos);
		a_findIdPwd.setLayout(null);
		a_findIdPwd.setUndecorated(true);
		a_findIdPwd.setBackground(Color.decode("#324d6b"));
		
		JLabel name = new JLabel("¿Ã∏ß");
		name.setLocation(10, 100);
		name.setSize(150, 50);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));

		JLabel phone1 = new JLabel("ø¨∂Ù√≥");
		phone1.setLocation(10, 150);
		phone1.setSize(150, 50);
		phone1.setForeground(Color.WHITE);
		phone1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));

		JLabel email1 = new JLabel("¿Ã∏ﬁ¿œ");
		email1.setLocation(10, 200);
		email1.setSize(150, 50);
		email1.setForeground(Color.WHITE);
		email1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));

		JLabel id = new JLabel("æ∆¿Ãµ");
		id.setLocation(350, 100);
		id.setSize(150, 50);
		id.setForeground(Color.WHITE);
		id.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));

		JLabel phone2 = new JLabel("ø¨∂Ù√≥");
		phone2.setLocation(350, 150);
		phone2.setSize(150, 50);
		phone2.setForeground(Color.WHITE);
		phone2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));

		JLabel email2 = new JLabel("¿Ã∏ﬁ¿œ");
		email2.setLocation(350, 200);
		email2.setSize(150, 50);
		email2.setForeground(Color.WHITE);
		email2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		
		
		
		JTextField namet1 = new JTextField(20);
		namet1.setLocation(60, 100);
		namet1.setSize(200, 40);

		JTextField phonet1 = new JTextField(20);
		phonet1.setLocation(60, 150);
		phonet1.setSize(200, 40);

		JTextField emailt1 = new JTextField(20);
		emailt1.setLocation(60, 200);
		emailt1.setSize(200, 40);

		JTextField idt = new JTextField(20);
		idt.setLocation(400, 100);
		idt.setSize(200, 40);

		JTextField phonet2 = new JTextField(20);
		phonet2.setLocation(400, 150);
		phonet2.setSize(200, 40);

		JTextField emailt2 = new JTextField(20);
		emailt2.setLocation(400, 200);
		emailt2.setSize(200, 40);
		
		
		JLabel idLabel = new JLabel("æ∆¿Ãµ √£±‚");
		idLabel.setLocation(10, 5);
		idLabel.setSize(250, 66);
		idLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 30));
		idLabel.setForeground(Color.WHITE);

		JLabel pwdLabel = new JLabel("∫Òπ–π¯»£ √£±‚");
		pwdLabel.setLocation(330, 5);
		pwdLabel.setSize(250, 66);
		pwdLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 30));
		pwdLabel.setForeground(Color.WHITE);
		
		
//		JButton find1 = new JButton("√£±‚");
		JButton find1 = new JButton(new ImageIcon("images/serchBtn1.png"));
		ImageIcon find12 = new ImageIcon("images/serchBtn2.png");
		find1.setBorderPainted(false);
		find1.setFocusPainted(false);
		find1.setContentAreaFilled(false);
		find1.setRolloverIcon(find12);
		find1.setLocation(192, 250);
		find1.setSize(70, 40);
		find1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				a_mm.findId(namet1.getText(), phonet1.getText(), emailt1.getText());
			}
		});
		
		
//		JButton find2 = new JButton("√£±‚");
		JButton find2 = new JButton(new ImageIcon("images/serchBtn1.png"));
		ImageIcon find22 = new ImageIcon("images/serchBtn2.png");
		find2.setBorderPainted(false);
		find2.setFocusPainted(false);
		find2.setContentAreaFilled(false);
		find2.setRolloverIcon(find22);
		find2.setSize(70, 40);
		find2.setLocation(530, 250);
		find2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				a_mm.findPwd(idt.getText(), phonet2.getText(), emailt2.getText());				
			}
		});
		
		JButton cancel = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancel2 = new ImageIcon("images/cancelbtn22.png");
		cancel.setBorderPainted(false);
		cancel.setFocusPainted(false);
		cancel.setContentAreaFilled(false);
		cancel.setRolloverIcon(cancel2);
		cancel.setLocation(280, 350);
		cancel.setSize(100,50);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				a_findIdPwd.dispose();
				
			}
		});
		
		
		a_findIdPwd.add(idLabel);
		a_findIdPwd.add(pwdLabel);
		a_findIdPwd.add(name);
		a_findIdPwd.add(phone1);
		a_findIdPwd.add(phone2);
		a_findIdPwd.add(email1);
		a_findIdPwd.add(email2);
		a_findIdPwd.add(id);
		a_findIdPwd.add(find1);
		a_findIdPwd.add(find2);
		a_findIdPwd.add(cancel);
		a_findIdPwd.add(namet1);
		a_findIdPwd.add(phonet1);
		a_findIdPwd.add(emailt1);
		a_findIdPwd.add(idt);
		a_findIdPwd.add(phonet2);
		a_findIdPwd.add(emailt2);
	
		a_findIdPwd.setResizable(false); 
		a_findIdPwd.setVisible(true);
		
	}
	 public Dialog getA_FindIdPwd() {
	      return a_findIdPwd;
	   }
	
	
}
