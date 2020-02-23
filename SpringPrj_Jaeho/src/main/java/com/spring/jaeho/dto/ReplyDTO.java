package com.spring.jaeho.dto;

import oracle.sql.TIMESTAMP;

//`r_no` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '댓글번호',
//`b_no` INT NOT NULL COMMENT '게시글번호',
//`b_detail` TEXT NULL COMMENT '댓글내용',
//`b_writer` VARCHAR(45) NOT NULL COMMENT '댓글작성자',
//`b_date` TIMESTAMP NOT NULL COMMENT '댓글 작성일',
//`b_edit` TIMESTAMP NOT NULL COMMENT '댓글 수정일',
//`b_secret_reply` CHAR(1) COMMENT '댓글 숨김여부',
public class ReplyDTO {
	private int r_no; // 댓글 번호
	private int b_no; // 게시글 번호
	private String b_detail;// 댓글 내용
	private String b_writer; // 댓글작성자
	private TIMESTAMP b_date; // 댓글작성일
	private TIMESTAMP b_edit; // 댓글 수정일
	private String b_secret_reply;// 댓글 숨김 유무
	private String write; // 게시글 작성자.db추가 아직안함

	public int getR_no() {
		return r_no;
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

	public String getB_detail() {
		return b_detail;
	}

	public void setB_detail(String b_detail) {
		this.b_detail = b_detail;
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

	public String getB_secret_reply() {
		return b_secret_reply;
	}

	public void setB_secret_reply(String b_secret_reply) {
		this.b_secret_reply = b_secret_reply;
	}

	public String getWrite() {
		return write;
	}

	public void setWrite(String write) {
		this.write = write;
	}

	@Override
	public String toString() {
		return "ReplyDTO [r_no=" + r_no + ", b_no=" + b_no + ", b_detail=" + b_detail + ", b_writer=" + b_writer
				+ ", b_date=" + b_date + ", b_edit=" + b_edit + ", b_secret_reply=" + b_secret_reply + ", write="
				+ write + "]";
	}

}
