package board;

import java.sql.Timestamp;//날짜와 시간을 설정할때 사용하는 클래스

public class BoardDTO {

	private int notice_number;//게시물번호
	//--눈에 보이는 상태(입력)
	//                    작성자     글제목   이메일   글내용    암호(->글쓰기->본인 =>글수정,글삭제할때)
	private String notice_title,notice_contents;
	private Timestamp notice_date;//작성날짜->sysdate<->now()(Mysql의 내장함수)
	//답변형
	private int ref;//글 그룹번호(=게시물 번호)

	// --------------------------------------------------------------------------------------------------------------
	
	public int getNotice_number() {
		return notice_number;
	}
	public void setNotice_number(int notice_number) {
		this.notice_number = notice_number;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_contents() {
		return notice_contents;
	}
	public void setNotice_contents(String notice_contents) {
		this.notice_contents = notice_contents;
	}
	public Timestamp getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Timestamp notice_date) {
		this.notice_date = notice_date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}	
// --------------------------------------------------------------------------------------------------------------
	

}