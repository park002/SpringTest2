package com.spring.jaeho.dto;

import oracle.sql.TIMESTAMP;
//CREATE TABLE `reply` (
//		  `r_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '´ñ±Û¹øÈ£',
//		  `b_no` int(11) NOT NULL COMMENT '°Ô½Ã±Û¹øÈ£',
//		  `replytext` text COMMENT '´ñ±Û³»¿ë',
//		  `b_writer` varchar(45) NOT NULL COMMENT '´ñ±ÛÀÛ¼ºÀÚ',
//		  `b_date` timestamp NULL DEFAULT NULL COMMENT '´ñ±Û ÀÛ¼ºÀÏ',
//		  `b_edit` timestamp NULL DEFAULT NULL COMMENT '´ñ±Û ¼öÁ¤ÀÏ',
//		  `b_secret_reply` char(1) DEFAULT NULL COMMENT '´ñ±Û ¼û±è¿©ºÎ',
//		  PRIMARY KEY (`r_no`),
//		  KEY `reply_board_b_no_fk` (`b_no`),
//		  CONSTRAINT `reply_board_b_no_fk` FOREIGN KEY (`b_no`) REFERENCES `board` (`b_no`)
//		) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

public class ReplyDTO {
	private int r_no;
	private int b_no;
	private String replytext;
	private String replyer;
	private TIMESTAMP b_date;
	private TIMESTAMP b_edit;

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

	public String getReplytext() {
		return replytext;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}


	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
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
		return "ReplyDTO [r_no=" + r_no + ", b_no=" + b_no + ", replytext=" + replytext + ", replyer=" + replyer
				+ ", b_date=" + b_date + ", b_edit=" + b_edit + "]";
	}

	

}
