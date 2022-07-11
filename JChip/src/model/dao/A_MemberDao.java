package model.dao;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.vo.A_Member;
import view.A_JoinPage;
import view.A_LoginPage;
import view.A_MainPage;

import view.ChangePanel;
import view.MainFrame;

public class A_MemberDao {
	
	public A_MemberDao() {}
	
	//파일에서 리스트 읽어오기
	public ArrayList<A_Member> readMemberList(){
		ObjectInputStream ois = null;
		ArrayList<A_Member> list = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("memberList.dat"));
			
			list=(ArrayList<A_Member>)ois.readObject();
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("파일이 없습니다.");
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("파일이 없습니다.");
				}
			}
		}
		
		return list;
		
	}
	
	//파일에서 리스트 기록하기
	public int writeMemberList(ArrayList<A_Member> list) {
		ObjectOutputStream oos = null;
		
		int result = 0;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("memberList.dat"));
			
			oos.writeObject(list);
			
			result++;
		
		}catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			
			try {
				oos.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	
	
}




