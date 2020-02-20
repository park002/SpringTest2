package com.spring.jaeho.page;

public class PageMaker {
	private int contentNum = 10;// 한 페이지에 몇개 표시할지
	private int totalCount;// 전체 게시물 개수
	private int curPageNum;// 현재 페이지 번호
	private int startPage = 1;// 현재 페이지 블록의 시작 페이지
	private int endPage = 5;// 현재 페이지 블록의 마지막 페이지
	private boolean prev = false;// 이전 페이지로 가는 화살표
	private boolean next;// 다음 페이지로 가는 화살표
	private int currentblock;// 현재 페이지블록
	private int lastblock;// 마지막 페이지 블록

	public void prevnext(int pagenum) { //(이전버튼)
		if (pagenum > 0 && pagenum < 6) {
			setPrev(false);
			setNext(true);
		}
		else if(getLastblock() == getCurrentblock()){
			setPrev(true);
			setNext(false);
		}
		else {
			setPrev(true);
			setNext(true);
		}
	}

	// 전체 페이지수를 계산하는 메소드
	// 125/10 =>12.5
	// 13페이지
	// 50/10 => 0
	// 5페이지
	// ex)전체게시글수125/ 한페이지에 표시할 게시글 개수 10 12.5 나머지 0 보다 크기 때문에 1을 더해주면 전체 페이지 13
	public int CalcPage(int totalCount, int contentNum) { // 전체게시글개수,한 페이지에 몇개를 표시할지
		int totalpage = totalCount / contentNum;
		if (totalCount % contentNum > 0) {
			totalpage++;
		}
		return totalpage;
	}


	public int getContentNum() {
		return contentNum;
	}

	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int currentblock) { // 한블럭당 5페이지가 보이게 설정 (시작페이지)

		this.startPage = (currentblock * 5) - 4;
		// 1// 1 2 3 4 5
		// 2// 6 7 8 9 10
		// 3// 11 12 13 14 15
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int getlastblock, int getcurrentblock) { // (마지막 페이지)
		if (getlastblock == getcurrentblock) {
			this.endPage = CalcPage(getTotalCount(), getContentNum()); // 전체 페이지수를 넣어준다.
		} else {
			this.endPage = getStartPage() + 4;
		}
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCurrentblock() {
		return currentblock;
	}

	public void setCurrentblock(int pagenum) { // (현재 페이지 블록)은 페이지 를 통해서 구할 수 있다 ==>pagenum
		// 5로 나눈이유는 한 페이지당 5개씩 보여질 것 이기 때문이다.
		// 페이지 /페이지 그룹 안의 페이지 개수
		// 페이지 를 통해서 구한다 .
		// 현재 페이지 가 1이라치면
		// 0.2>0 => currentblock++;
		// 0.2 자바에서는 (int) 형변수로 선언 했을시 자바는 0으로 인식한다 .
		// 0+1=1 현재블럭 1번인걸 알 수 있다
		// 만약 3p일 경우 3/5 =>0.6 =>0+1; => 1
		// 만약 페이지가 8일경우
		// 8/5 => 1.6 => 1.6>0(true) => (int 형변환)1 => 1+1=>2
		this.currentblock = pagenum / 5;
		if (pagenum % 5 > 0) {
			this.currentblock++;
		}
	}

	public int getLastblock() {
		return lastblock;
	}

	public void setLastblock(int totalcount) { // (마지막 블록)
		// 페이지당 보여줄 리스트 10 , 한페이지 블록당 보여질 5개 1이면 1 2 3 4 5
		// 10 * 5 =>50
		// 전체글 125개 있다 가정 .
		// 125/50 => 2.5 ==> 2.5>0(true) ==>3.
		this.lastblock = totalcount / (5 * this.contentNum);
		if (totalcount % (5 * this.contentNum) > 0) {
			this.lastblock++;
		}
	}

}
