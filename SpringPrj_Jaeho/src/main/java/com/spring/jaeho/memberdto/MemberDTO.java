package com.spring.jaeho.memberdto;

/*`m_id` VARCHAR(30) NOT NULL COMMENT '회원ID',
`m_name`VARCHAR(10) NOT NULL COMMENT'회원이름',
`m_zip1` VARCHAR(30) NOT NULL COMMENT '우편번호',
`m_zip2` VARCHAR(70) NOT NULL COMMENT '기본주소',
`m_zip3` VARCHAR(70) NOT NULL COMMENT '상세주소',
`m_password` VARCHAR(30) NOT NULL COMMENT '비밀번호',
`m_userEmail` VARCHAR(100) NOT NULL COMMENT '이메일',
`m_userEmailHash` VARCHAR(100) NULL COMMENT '이메일HASH값',
`m_userEmailChecked`  TINYINT(1) NULL COMMENT '이메일 인증 여부',
`m_tel` VARCHAR(20) NULL COMMENT '전화번호',
PRIMARY KEY (`m_id`));
*/
public class MemberDTO {
	private String m_id;
	private String m_name;
	private String m_zip1;
	private String m_zip2;
	private String m_zip3;
	private String m_password;
	private String m_userEmail;
	private String m_userEmailHash;
	private boolean m_userEmailChecked;
	private String m_tel;

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_zip1() {
		return m_zip1;
	}

	public void setM_zip1(String m_zip1) {
		this.m_zip1 = m_zip1;
	}

	public String getM_zip2() {
		return m_zip2;
	}

	public void setM_zip2(String m_zip2) {
		this.m_zip2 = m_zip2;
	}

	public String getM_zip3() {
		return m_zip3;
	}

	public void setM_zip3(String m_zip3) {
		this.m_zip3 = m_zip3;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_userEmail() {
		return m_userEmail;
	}

	public void setM_userEmail(String m_userEmail) {
		this.m_userEmail = m_userEmail;
	}

	public String getM_userEmailHash() {
		return m_userEmailHash;
	}

	public void setM_userEmailHash(String m_userEmailHash) {
		this.m_userEmailHash = m_userEmailHash;
	}

	public boolean isM_userEmailChecked() {
		return m_userEmailChecked;
	}

	public void setM_userEmailChecked(boolean m_userEmailChecked) {
		this.m_userEmailChecked = m_userEmailChecked;
	}

	public String getM_tel() {
		return m_tel;
	}

	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}

	@Override
	public String toString() {
		return "MemberDTO [m_id=" + m_id + ", m_name=" + m_name + ", m_zip1=" + m_zip1 + ", m_zip2=" + m_zip2
				+ ", m_zip3=" + m_zip3 + ", m_password=" + m_password + ", m_userEmail=" + m_userEmail
				+ ", m_userEmailHash=" + m_userEmailHash + ", m_userEmailChecked=" + m_userEmailChecked + ", m_tel="
				+ m_tel + "]";
	}

}
