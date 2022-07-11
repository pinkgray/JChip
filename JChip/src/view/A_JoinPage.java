package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.A_MemberManager;
import model.dao.A_MemberDao;
import model.vo.A_Member;




public class A_JoinPage extends JPanel {
	
	private MainFrame mf;
	private Dialog a_joinPage;
	private A_MemberDao memberDao = new A_MemberDao();
	private A_MemberManager a_mm = new A_MemberManager();
	private A_JoinPage ajp;
	private JTextField idt;
	private TextField pwdt;
	private TextField pwdt1;
	private JTextField namet;
	private JTextField gendert;
	private JTextField phonet;
	private JTextField emailt;
	
	
	public A_JoinPage(MainFrame mf) {
		
		
		
		
		
		a_joinPage = new Dialog(mf, "회원가입");
		a_joinPage.setSize(500, 550);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (a_joinPage.getWidth() / 2);
		int yPos = (dim.height / 2) - (a_joinPage.getHeight() / 2);
		a_joinPage.setLocation(xPos, yPos);
		a_joinPage.setLayout(null);
		a_joinPage.setUndecorated(true);
		a_joinPage.setBackground(Color.decode("#324d6b"));
		
		JLabel ilogo = new JLabel("회원가입");
		ilogo.setLocation(180,0);
		ilogo.setSize(208,66);
		ilogo.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		ilogo.setForeground(Color.WHITE);
		a_joinPage.add(ilogo);
		
		
		JLabel id = new JLabel("아이디");
		id.setLocation(50, 90);
		id.setSize(150, 50);
		id.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		id.setForeground(Color.WHITE);
		a_joinPage.add(id);
		
		JButton check = new JButton(new ImageIcon("images/check1.PNG"));
		ImageIcon check2 = new ImageIcon("images/check2.PNG");
		check.setBorderPainted(false);
		check.setFocusPainted(false);
		check.setContentAreaFilled(false);
		check.setRolloverIcon(check2);
		
		
		check.setLocation(390, 95);
		check.setSize(100, 40);
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					a_mm.idcheck(idt.getText());
			}
		});
		a_joinPage.add(check);
		
		idt = new JTextField(20);
		idt.setLocation(180, 100);
		idt.setSize(200, 30);
		a_joinPage.add(idt);
		
		JLabel pwd = new JLabel("비밀번호");
		pwd.setLocation(50, 140);
		pwd.setSize(130, 50);
		pwd.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pwd.setForeground(Color.WHITE);
		a_joinPage.add(pwd);
		
		pwdt = new TextField(20);
		pwdt.setLocation(180, 150);
		pwdt.setSize(200, 30);
		pwdt.setEchoChar('●');
		a_joinPage.add(pwdt);
		
		JLabel pwd1 = new JLabel("비밀번호 확인");
		pwd1.setLocation(50, 190);
		pwd1.setSize(130, 50);
		pwd1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pwd1.setForeground(Color.WHITE);
		a_joinPage.add(pwd1);
		
		pwdt1 = new TextField(20);
		pwdt1.setLocation(180, 200);
		pwdt1.setSize(200, 30);
		pwdt1.setEchoChar('●');
		a_joinPage.add(pwdt1);
		
		JLabel name = new JLabel("이름");
		name.setLocation(50, 240);
		name.setSize(150, 50);
		name.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		name.setForeground(Color.WHITE);
		a_joinPage.add(name);
		
		 namet = new JTextField(20);
		namet.setLocation(180, 250);
		namet.setSize(200, 30);
		a_joinPage.add(namet);
		
		JLabel gender = new JLabel("성별(남자/여자)");
		gender.setLocation(50, 290);
		gender.setSize(150, 50);
		gender.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		gender.setForeground(Color.WHITE);
		a_joinPage.add(gender);
		
		 gendert = new JTextField(20);
		gendert.setLocation(180, 300);
		gendert.setSize(200, 30);
		a_joinPage.add(gendert);
		
		JLabel phone = new JLabel("연락처(-제외)");
		phone.setLocation(50, 340);
		phone.setSize(150, 50);
		phone.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		phone.setForeground(Color.WHITE);
		a_joinPage.add(phone);
		
		 phonet = new JTextField(20);
		phonet.setLocation(180, 350);
		phonet.setSize(200, 30);
		a_joinPage.add(phonet);
		
		JLabel email = new JLabel("이메일");
		email.setLocation(50, 390);
		email.setSize(150, 50);
		email.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		email.setForeground(Color.WHITE);
		a_joinPage.add(email);
		
		emailt = new JTextField(20);
		emailt.setLocation(180, 400);
		emailt.setSize(200, 30);
		a_joinPage.add(emailt);
		
		JButton join = new JButton(new ImageIcon("images/join11.PNG"));
		ImageIcon join2 = new ImageIcon("images/join22.PNG");
		join.setBorderPainted(false);
		join.setFocusPainted(false);
		join.setContentAreaFilled(false);
		join.setRolloverIcon(join2);
		
		
		join.setLocation(350, 470);
		join.setSize(110, 45);
		join.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent T) {	
            	
            	A_Member a_Member = new A_Member();
        		String ids= idt.getText();
            	String pwds = pwdt.getText();
            	String pwds1 = pwdt1.getText();
            	String names = namet.getText();
            	String genders = gendert.getText();
            	String phones = phonet.getText();
            	String emails = emailt.getText();
            	
            	if(ids.equals("")){
            		JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
    			}else if(pwds.equals("")){
    				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
    			}else if(pwds1.equals("")){
        			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
    			}else if(!(pwds.equals(pwds1))) {
    				
    				JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다. 확인해주세요");
        			
    			}else if(names.equals("")){
    				JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
    			}else if(genders.equals("")){
    				JOptionPane.showMessageDialog(null, "성별을 입력해주세요");
    			}else if(phones.equals("")){
    				JOptionPane.showMessageDialog(null, "연락처를 입력해주세요");
    			
    			}else if(!integerOrNot(phones)){
    				JOptionPane.showMessageDialog(null, "전화번호는 '-'빼고 입력해주세요");
    			}else if(!(phones.substring(0,2).equals("01") && (phones.length() ==10 || phones.length() ==11))){
    				JOptionPane.showMessageDialog(null, "잘못된 전화번호를 입력하셨습니다.");
    			
    			
    			}else if(emails.equals("")){
    				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요");
    			
				
				}else {
            	
        			a_Member.setId(ids);
        			a_Member.setPwd(pwds);
        			a_Member.setPwd1(pwds1);
        			a_Member.setName(names);
        			a_Member.setGender(genders);
        			a_Member.setPhone(phones);
        			a_Member.setEmail(emails);
					
            		
            		a_mm.joinMember(a_Member);
            		JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!!");
            		a_joinPage.dispose();
				}
				
            	
					
            }
         });
		
		a_joinPage.add(join);
		
		JButton cancel = new JButton(new ImageIcon("images/cancelbtn11.png"));
		ImageIcon cancel2 = new ImageIcon("images/cancelbtn22.png");
		cancel.setBorderPainted(false);
		cancel.setFocusPainted(false);
		cancel.setContentAreaFilled(false);
		cancel.setRolloverIcon(cancel2);
		
		
		
		
		cancel.setLocation(250, 470);
		cancel.setSize(100, 45);
		cancel.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	a_joinPage.dispose();
               
            }
         });
		
		a_joinPage.add(cancel);
		

		a_joinPage.setResizable(false); 
		a_joinPage.setVisible(true);
		
	}
	
	 public Dialog getA_JoinPage() {
	      return a_joinPage;
	   }

	 public boolean integerOrNot(String strData){ // 입력값이 숫자인지 문자인지 판별 : 
			char[] charData = strData.toCharArray();
			boolean check=true;
			while(check){
				for(int i=0; i<charData.length; i++){		
					if(!Character.isDigit(charData[i])){
							check = !check;
							break;
					}
				}
				break;	
			}return check;
		}
	
}
