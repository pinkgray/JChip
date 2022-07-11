package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.dao.A_MemberDao;
import model.vo.A_Member;
import view.A_LoginPage;
import view.A_MainPage;

import view.ChangePanel;
import view.MainFrame;

public class A_MemberManager {
	private MainFrame mf;
	private A_MemberDao md = new A_MemberDao();
	private A_LoginPage lp;
	
	public void joinMember(A_Member a_Member) {
		
		//파일에 기록된 리스트 조회
		ArrayList<A_Member> list = md.readMemberList();
		
		//
		if(list==null) {
			
			list = new ArrayList<A_Member>();
		}
		//리스트에 member 객체 추가
		list.add(a_Member);
		
		//리스트를 파일에 기록 후 결과값 정수로 리턴
		int result = md.writeMemberList(list);
		
		
	}
	
	
	public void loginMember(String id, String pwd, A_LoginPage lp) {
		this.lp = lp;
		//전체 멤버를 조회
		ArrayList<A_Member> list = md.readMemberList();
		
		if(list != null) {
			for(int i = 0 ; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
		}else {
			JOptionPane.showMessageDialog(null, "아이디/비밀번호를 입력해주세요");
			
		}
		
		if(list != null) {
			if( id.equals(" ")&& (pwd.equals(" "))) {
				JOptionPane.showMessageDialog(null, "아이디/비밀번호를 입력해주세요");
				return;
			
			}else{

				for(int i =0; i<list.size(); i++) {

					//(민)
					A_Member user = (A_Member)list.get(i);
					//
					
					if(user.getId().equals(id) && user.getPwd().equals(pwd)) {
						
						//(민)유저정보 같이 넘겨줌
						lp.goToMainPage(lp, user);
						//
						return;
						
						
						
					}else if(id.equals(" ")&& (pwd.equals(" "))) {
						JOptionPane.showMessageDialog(null, "아이디/비밀번호를 입력해주세요");
						return;
					}
				}
				
				
				for(int i =0; i<list.size(); i++) {
					if(list.get(i).getId() != id && list.get(i).getPwd()!= pwd) {

						JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요"); 
						return;


					}else if(list.get(i).getId() == " " && list.get(i).getPwd()== " "){
						JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요");
						return;
					}
				}
			}
			
			
		}
		
	}
	
	public void idcheck(String id) {
		ArrayList<A_Member> list = md.readMemberList();
		if(list==null) {
			if(id != "") {
			JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디 입니다.");
			list = new ArrayList<A_Member>();
			}else if(id==""){
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
				list = new ArrayList<A_Member>();
			}
		}
		
		for(int i =0; i<list.size(); i++) {
			if(list.get(i).getId().equals(id)) {
				JOptionPane.showMessageDialog(null, "사용중인 아이디 입니다.");
				return;
			}
			
		}
		for(int i =0; i<list.size(); i++) {
			if(list.get(i).getId() != id){
			
			JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디 입니다.");
				return;
			
		}
		}
	}
   
	public void findId(String name, String phone, String email) {
		
		ArrayList<A_Member> list = md.readMemberList();
		
		if(list==null) {
			JOptionPane.showMessageDialog(null, "사용자 정보를 확인해주세요");
			list = new ArrayList<A_Member>();
		}
		
		
		for(int i =0; i<list.size(); i++) {
			
			if(list.get(i).getName().equals(name) 
					&& list.get(i).getPhone().equals(phone) 
					&& list.get(i).getEmail().equals(email)) {
				
				JOptionPane.showMessageDialog(null, name+"님의 아이디는" +list.get(i).getId()+ "입니다");
				return;
			}
		}
			
			
			JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
				
	
		}
	public void findPwd(String id, String phone, String email) {
		ArrayList<A_Member> list = md.readMemberList();

		if(list==null) {
			JOptionPane.showMessageDialog(null, "사용자 정보를 확인해주세요");
			list = new ArrayList<A_Member>();

		}else {

			for(int i =0; i<list.size(); i++) {

				if(list.get(i).getId().equals(id) 
						&& list.get(i).getPhone().equals(phone) 
						&& list.get(i).getEmail().equals(email)) {

					JOptionPane.showMessageDialog(null, list.get(i).getName()+"님의 비밀번호는" +list.get(i).getPwd()+ "입니다");
					return;

				}

			}
			JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
		}	


	}
	
	


	//(민)
	public A_Member findMember(String id) {
		ArrayList<A_Member> list = md.readMemberList();
		boolean isInvitable = false;
		A_Member member = null;
		for(int i = 0; i < list.size(); i++) {
			member = (A_Member)list.get(i);
			if (member.getId().equals(id)){
				JOptionPane.showMessageDialog(null, "초대 가능한 멤버입니다.");
				isInvitable = true;
				return member;
			}
		}

		if(!isInvitable) {
			JOptionPane.showMessageDialog(null, "입력하신 정보와 일치하는 회원이 없습니다!");
		}

		return null;
	}
	public A_Member changeDate(A_Member user,String pwd, String email, String phone) {
		ArrayList<A_Member> list = md.readMemberList();
		
		System.out.println(user);
		System.out.println(pwd);
		System.out.println(email);
		System.out.println(phone);
		
		int result=0;
		
		for(int i = 0 ; i < list.size(); i++) {
			if(user.getId().equals(list.get(i).getId())) {
				
				System.out.println(list.get(i));
				list.get(i).setPwd(pwd);
				list.get(i).setPwd1(pwd);
				list.get(i).setEmail(email);
				list.get(i).setPhone(phone);
				
				result=md.writeMemberList(list);
				break;
			}
		}
		
		
		
		return user;
	}
	
	//(지)
	public ArrayList<String> findMemberName(ArrayList<String> memberlist) {
		ArrayList<String> findmembername = new ArrayList<String>();
		ArrayList<A_Member> list = md.readMemberList();
		ArrayList<String> findmember = memberlist;
		A_Member member = null;



		for (int j = 0 ; j < list.size() ; j++) {
			member = (A_Member)list.get(j);

			for (int i =0 ; i < findmember.size() ; i++) {
				if (findmember.get(i).equals(member.getId())) {
					findmembername.add(member.getName());
				}
			}
		}

		if (findmembername != null) {
			for (int i = 0 ; i < findmembername.size() ; i++) {
				System.out.println(findmembername.get(i));
			}

			return findmembername;
		}



		return null;
	}


}



