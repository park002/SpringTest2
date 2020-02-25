package com.spring.jaeho.dto;

import oracle.sql.TIMESTAMP;

//`r_no` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '��۹�ȣ',
//`b_no` INT NOT NULL COMMENT '�Խñ۹�ȣ',
//`b_detail` TEXT NULL COMMENT '��۳���',
//`b_writer` VARCHAR(45) NOT NULL COMMENT '����ۼ���',
//`b_date` TIMESTAMP NOT NULL COMMENT '��� �ۼ���',
//`b_edit` TIMESTAMP NOT NULL COMMENT '��� ������',
//`b_secret_reply` CHAR(1) COMMENT '��� ���迩��',
public class ReplyDTO {
	private int r_no; // ��� ��ȣ
	private int b_no; // �Խñ� ��ȣ
	private String replytext;// ��� ����
	private String b_writer; // ����ۼ���
	private TIMESTAMP b_date; // ����ۼ���
	private TIMESTAMP b_edit; // ��� ������
//private String b_secret_reply;// ��� ���� ����
//	private String write; // �Խñ� �ۼ���.db�߰� ��������

	
	public int getR_no() {
		return r_no;
	}

	public String getReplytext() {
		return replytext;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}



	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public TIMESTAMP getB_date() {
		return b_date;
	}

	public void setB_date(TIMESTAMP b_date) {
		this.b_date = b_date;
	}

	public TIMESTAMP getB_edit() {
		return b_edit;
	}

	public void setB_edit(TIMESTAMP b_edit) {
		this.b_edit = b_edit;
	}

	@Override
	public String toString() {
		return "ReplyDTO [r_no=" + r_no + ", b_no=" + b_no + ", replytext=" + replytext + ", b_writer=" + b_writer
				+ ", b_date=" + b_date + ", b_edit=" + b_edit + "]";
	}


}
