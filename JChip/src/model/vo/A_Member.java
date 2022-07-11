package model.vo;

import java.io.Serializable;
import java.util.Date;

public class A_Member implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6949282956683719323L;
	
	
	private String id;
	private String pwd;
	private String pwd1;
	private String name;
	private String gender;
	private String phone;
	private String email;
	
	public A_Member() {}

	
	public A_Member(String id, String pwd, String pwd1, String name, String gender, String phone, String email
	) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.pwd1 = pwd1;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		
	}
	

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public String getPwd1() {
		return pwd1;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	
	
	@Override
	public String toString() {
		return "A_Member [id=" + id + ", pwd=" + pwd + ", pwd1=" + pwd1 + ", name=" + name + ", gender=" + gender
				+ ", phone=" + phone + ", email=" + email + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}





