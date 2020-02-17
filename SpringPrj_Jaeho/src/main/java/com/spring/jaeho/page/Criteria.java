package com.spring.jaeho.page;

public class Criteria {
	private int page; //현재 페이지 번호  1
	private int perPageNum; //한 페이지당 보여줄 게시글 개수  10

	public int getPageStart() { //특정페이지의  게시글  시작번호 ,게시글 시작 행 번호 
		return (this.page - 1) * perPageNum;
	}

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}
	
	//페이지가 음수값이 되지 않게 설정. 음수가 되면 1페이지를 나타낸다.
	public void setPage(int page) { //현 page가 음수일 경우  오류가 발생, 그래서 1로 둔다. 만약 현page가  0보다 클경우 전역변수에 그 값을 대입 .
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}
 //무조건 한페이지당 보여줄 게시물 개수를 10개로 지정한다 .  페이지당 보여줄 게시글 수가 변하지 않게 설정했다
	public void setPerPageNum(int pageCount) { //cnt ==한페이지당 보여줄 게시물 개수 라고 생각하자 . 
		int cnt = this.perPageNum;
		if (pageCount != cnt) { //만약 , 페이지 개수가 != perPageNum(한페이지당 보여줄 게시물 개수)라면  같게 만들어주자 .  
			this.perPageNum = cnt;
		} else { // paegeCount가 10개 라면. 10개로지정
			this.perPageNum = pageCount;
		}
	}

}
