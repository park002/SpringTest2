package com.spring.jaeho.page;

public class Pagination {
	//오라클 페이징처리 공부 2020- 02- 17
	// static 으로 정의 된 것은 자바는 메모리 할당을 한번만하고 그 메모리 주소만을 바라본다 .
	public static final int PAGE_SCALE = 10;// 페이지당 게시물 수 (상수) b_no =>1 2 3 4 5 6 7 8 9 10
	public static final int BLOCK_SCALE = 10; // 블럭 당 페이지 수 //1 2 3 4 5 6 7 8 9 10 // 11 12 13 14 15 16 17 18 19 20
	private int curPage;// 현재 페이지
	private int prevPage; // 이전 페이지
	private int nextPage; // 다음 페이지
	private int totPage; // 전체 페이지 갯수
	private int totBlock;// 전체 페이지 블록갯수
	private int curBlock; // 현재 페이지 블록
	private int prevBlock; // 이전 페이지 블록
	private int nextBlock;// 다음 페이지 블록
	private int pageBegin; // #{start}
	private int pageEnd; // #{end}
	// [이전] blockBegin ->41 42 43 44 45 46 47 48 49 50 [다음]
	private int blockBegin; // 현재 페이지 블록의 시작번호
	// [이전] 41 42 43 44 45 46 47 48 49 50 <- blockEnd [다음]
	private int blockEnd; // 현재 페이지 블록의 끝번호

	public Pagination(int count, int curPage) { // 생성자 레코드갯수,현재 페이지번호
       curBlock=1; //현재 페이지 블록번호
       this.curPage = curPage; //현재 페이지 설정
       setTotPage(count); //전체 페이지 개수 계산 
       
	}
	public void setBlockRange() {
		//현재 페이지가  몇번째 페이지 블록에 속하는지 계산  BLOCK_SCALE= 10
		//static double ceil(double a) 전달받은 실수보다 큰 정수 중 가장 작은 정수를 반환
		curBlock =(int)(Math.ceil( (curPage-1) / BLOCK_SCALE) +1); //현재 페이지가 1일 경우 curBlock=1; //현재 페이지가 11일 경우 curBlock=2;
		
		//현재 페이지블록의 시작 , 끝 번호 계산 
		blockBegin = (curBlock-1) * BLOCK_SCALE +1; //curBlock=1 일때 blockBegin=1 //curBlock=2 일때 blockBegin=11;
		
		//페이지블록의 끝번호
		blockEnd = blockBegin + BLOCK_SCALE-1; //blockBegin 1일때 blockEnd=10 // blockBegin 2일때 blockEnd=20 //blockBegin 3일 때 blockEnd=30
		
		//마지막 블록이 범위를 초과하지 않도록 계산 
		if(blockEnd>totPage) blockEnd = totPage;
		
		//이전을 눌렀을 때 이동할 페이지 번호
		prevPage = (curPage == 1) ? 1:(curBlock-1) * BLOCK_SCALE; //ex)현재p11 F => (2-1) *10 => prevPage==10
		
		//다음을 눌렀을 때 이동할 페이지 번호 
		nextPage= curBlock>totBlock?  (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1; //만약 현재블럭이 2일경우 ,총페이지블럭 4일경우 2>4 F 11 12 13 14 15 16 17 18 19 20  -> 21 된다
		
		//마지막 페이지가 범위를 초과하지 않도록 처리 
		if(nextPage>= totPage) nextPage =totPage;
		
	}
	 public void setPageRange() {
		//where rn BETWEEN #{start} AND #{end}
		
		//시작번호 = (현재페이지-1) * 페이지당 게시물수 +1 				//curPage 2일 경우 pageBegin= 11
		pageBegin = (curPage-1) * PAGE_SCALE+1;
		
		//끝번호 =시작번호+페이지당 게시물수 -1							//마찬가지로 현재페이지 curPage 2일경우 pageEnd= 20
		pageEnd = pageBegin+PAGE_SCALE-1;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int count) {  //전체 페이지 개수 계산   count== 레코드 개수 ex)35개. 35/10 =>3.5 ceil인해서  totPage=4; 
		//Math.ceil(실수) 올림 처리 
		totPage =(int) Math.ceil(count*1.0/PAGE_SCALE);
	}

	public int getTotBlock() {
		return totBlock;
	}
	
    //페이지 블록의  갯수 계산 (총 100페이지라면  10개의 블록)
	public void setTotBlock(int totBlock) {
	     //전체페이지 갯수 / 10
		// 91/10 =>9.1 =>10개
		totBlock= (int)Math.ceil(totPage / BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public static int getPageScale() {
		return PAGE_SCALE;
	}

	public static int getBlockScale() {
		return BLOCK_SCALE;
	}

}
