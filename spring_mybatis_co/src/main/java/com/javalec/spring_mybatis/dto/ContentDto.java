package com.javalec.spring_mybatis.dto;

public class ContentDto {

	private int mId; //�Խñ۹�ȣ  pk autoincrease 
	private String mWriter; //�ۼ���
	private String mContent; //���� 

	public ContentDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ContentDto(int mId, String mWriter, String mContent) {
		this.mId = mId;
		this.mWriter = mWriter;
		this.mContent = mContent;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmWriter() {
		return mWriter;
	}

	public void setmWriter(String mWriter) {
		this.mWriter = mWriter;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

}
