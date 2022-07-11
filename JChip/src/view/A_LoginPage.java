package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.A_MemberManager;
import model.dao.A_MemberDao;
import model.vo.A_Member;



	public class A_LoginPage extends JPanel{
		private MainFrame mf;
		private A_LoginPage lp;
		private JTextField idt;
		private JTextField pwdt;
		private A_MemberDao memberDao = new A_MemberDao();
		private A_MemberManager a_mm = new A_MemberManager();
		
		
		ImageIcon icon = new ImageIcon("images/bg.png");
		Image img = icon.getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		
		public A_LoginPage(MainFrame mf) {
			this.mf= mf;
		
			this.lp = this;
			
			this.setSize(1024, 768);
			this.setLayout(new BorderLayout());

			
			
			this.setLayout(new BorderLayout());
	        
	        //여기서부터
	        JPanel panel = new JPanel();
	        panel.setOpaque(false);
	        panel.setPreferredSize(new Dimension(1024,120));
	        this.add(panel, BorderLayout.NORTH);
	        
	        JPanel panel2 = new JPanel();
	        panel2.setOpaque(false);
	        panel2.setPreferredSize(new Dimension(50,768));
	        this.add(panel2, BorderLayout.WEST);
	        
	        JPanel panel3 = new JPanel();
	        panel3.setOpaque(false);
	        panel3.setPreferredSize(new Dimension(50,768));
	        this.add(panel3, BorderLayout.EAST);
	        
	        
	        JPanel panel4 = new JPanel();
	        panel4.setOpaque(false);
	        panel4.setPreferredSize(new Dimension(1024,80));
	        this.add(panel4, BorderLayout.SOUTH);
	        //여기까지는 쓸모없는 패널로 그냥 mainPanel 위치 찹아주는 거입니다.
	        
	        JPanel mainPanel = new JPanel();
	        mainPanel.setPreferredSize(new Dimension(500,500));
	       // mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
	        mainPanel.setLayout(new BorderLayout());
	       mainPanel.setOpaque(false);
	        
	        JLabel logoimage = new JLabel(new ImageIcon("images/logo2.PNG"));
	        logoimage.setPreferredSize(new Dimension(100,300));

	        mainPanel.add(logoimage,BorderLayout.NORTH);

	        JPanel SubPanel = new JPanel();
	        //SubPanel.setLayout(null);
	        SubPanel.setPreferredSize(new Dimension(100,300));
	        SubPanel.setLayout(new BorderLayout());
	        SubPanel.setOpaque(false);
	        
	        JPanel LoginPanel =new JPanel();
	        LoginPanel.setPreferredSize(new Dimension(300,100));
	        LoginPanel.setLayout(new FlowLayout());
	        LoginPanel.setOpaque(false);
	        
	        
	        JPanel TextFieldPanel =new JPanel();
	        TextFieldPanel.setLayout(new BoxLayout(TextFieldPanel,BoxLayout.Y_AXIS));
	        TextFieldPanel.setPreferredSize(new Dimension(300,60));
	        
	        JPanel idPanel = new JPanel();
	        idPanel.setLayout(new FlowLayout());
	        idPanel.setOpaque(false);
	        idPanel.setPreferredSize(new Dimension(50,80));
	       
	        
	        JLabel id = new JLabel("ID     ");
			//id.setLocation(360,350);
			id.setSize(50, 50);


			JTextField idt = new JTextField(20);
			//idt.setLocation(410, 350);
			//idt.setSize(200, 40);

			idPanel.add(id);
			idPanel.add(idt);
			
			
			JPanel PwdPanel = new JPanel();
			PwdPanel.setLayout(new FlowLayout());
			PwdPanel.setOpaque(false);
			PwdPanel.setPreferredSize(new Dimension(50,80));

			JLabel password = new JLabel("PWD");
			//password.setLocation(360, 400);
			//password.setSize(150, 50);

			
			TextField pwdt = new TextField(28);
			//pwdt.setLocation(410, 400);
			//pwdt.setSize(20, 40);
			pwdt.setEchoChar('●');
			

			PwdPanel.add(password);
			PwdPanel.add(pwdt);
			
			TextFieldPanel.add(idPanel);
			TextFieldPanel.add(PwdPanel);
			TextFieldPanel.setOpaque(false);
			
			LoginPanel.add(TextFieldPanel);
			
			JButton login_btn = new JButton(new ImageIcon("images/login2.PNG"));
			ImageIcon login_btn2 = new ImageIcon("images/login1.PNG");
			//login_btn.setLocation(620, 350);
			login_btn.setPreferredSize(new Dimension(80,60));
			login_btn.setBorderPainted(false);
			login_btn.setFocusPainted(false);
			login_btn.setContentAreaFilled(false);
			login_btn.setRolloverIcon(login_btn2);
			login_btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
						
					
						a_mm.loginMember(idt.getText(), pwdt.getText() ,lp);
					

				}
			});
			
			//login_btn.addActionListener(this)
			
			LoginPanel.add(login_btn);
			
			JPanel Buttons_panel = new JPanel();
			Buttons_panel.setLayout(new BoxLayout(Buttons_panel,BoxLayout.Y_AXIS));
			Buttons_panel.setOpaque(false);
			Buttons_panel.setPreferredSize(new Dimension(50,5));

			JPanel buttonpanel = new JPanel();
			buttonpanel.setLayout(new FlowLayout());
			buttonpanel.setOpaque(false);
			buttonpanel.setPreferredSize(new Dimension(50,80));
			

//			JButton joinus = new JButton("아직 회원이 아니신가요?");
			
			JButton joinus = new JButton(new ImageIcon("images/join1.PNG"));
			ImageIcon joinus2 = new ImageIcon("images/join2.PNG");
			joinus.setLocation(350, 440);
			joinus.setSize(180,30);
			joinus.addActionListener(new Joinevent());
			joinus.setBorderPainted(false);
			joinus.setFocusPainted(false);
			joinus.setContentAreaFilled(false);
			joinus.setRolloverIcon(joinus2);
			//joinus.addMouseListener(new MyMouseAdapter1());
			
			


			JButton find = new JButton(new ImageIcon("images/userSerch1.PNG"));
			ImageIcon find2 = new ImageIcon("images/userSerch2.PNG");
			find.setLocation(620, 440);
			find.setSize(180,30);
			//find.addMouseListener(new MyMouseAdapter2());
			find.addActionListener(new Findevent());
			find.setBorderPainted(false);
			find.setFocusPainted(false);
			find.setContentAreaFilled(false);
			find.setRolloverIcon(find2);
			buttonpanel.add(joinus);
			buttonpanel.add(find);
			
			
			Buttons_panel.add(buttonpanel); 
			
			
			SubPanel.add(LoginPanel,BorderLayout.NORTH);
			
			SubPanel.add(Buttons_panel,BorderLayout.CENTER);
			
	        
	        mainPanel.add(SubPanel,BorderLayout.CENTER);
	        
	        this.add(mainPanel, BorderLayout.CENTER);
			
			mf.add(this);
			
		
		}
		class Findevent implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//JFrame mf = new JFrame();
				new A_FindIdPwd(mf).getA_FindIdPwd().setVisible(true);
			}
		}
		class Joinevent implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JFrame mf = new JFrame();
			new A_JoinPage(mf).getA_JoinPage().setVisible(true);
		}
		
		
	}
	  
		//(민)
		  public void goToMainPage(A_LoginPage lp, A_Member user) {
				this.lp = lp;
					//(민)mainpage로 멤버정보 넘겨줌
					ChangePanel.changePanel(mf, lp ,new A_MainPage(mf, user));
				}
		   
	  
	   
}
