package com.spring.jaeho.page;

public class Pagination {
	//����Ŭ ����¡ó�� ���� 2020- 02- 17
	// static ���� ���� �� ���� �ڹٴ� �޸� �Ҵ��� �ѹ����ϰ� �� �޸� �ּҸ��� �ٶ󺻴� .
	public static final int PAGE_SCALE = 10;// �������� �Խù� �� (���) b_no =>1 2 3 4 5 6 7 8 9 10
	public static final int BLOCK_SCALE = 10; // �� �� ������ �� //1 2 3 4 5 6 7 8 9 10 // 11 12 13 14 15 16 17 18 19 20
	private int curPage;// ���� ������
	private int prevPage; // ���� ������
	private int nextPage; // ���� ������
	private int totPage; // ��ü ������ ����
	private int totBlock;// ��ü ������ ��ϰ���
	private int curBlock; // ���� ������ ���
	private int prevBlock; // ���� ������ ���
	private int nextBlock;// ���� ������ ���
	private int pageBegin; // #{start}
	private int pageEnd; // #{end}
	// [����] blockBegin ->41 42 43 44 45 46 47 48 49 50 [����]
	private int blockBegin; // ���� ������ ����� ���۹�ȣ
	// [����] 41 42 43 44 45 46 47 48 49 50 <- blockEnd [����]
	private int blockEnd; // ���� ������ ����� ����ȣ

	public Pagination(int count, int curPage) { // ������ ���ڵ尹��,���� ��������ȣ
       curBlock=1; //���� ������ ��Ϲ�ȣ
       this.curPage = curPage; //���� ������ ����
       setTotPage(count); //��ü ������ ���� ��� 
       
	}
	public void setBlockRange() {
		//���� ��������  ���° ������ ��Ͽ� ���ϴ��� ���  BLOCK_SCALE= 10
		//static double ceil(double a) ���޹��� �Ǽ����� ū ���� �� ���� ���� ������ ��ȯ
		curBlock =(int)(Math.ceil( (curPage-1) / BLOCK_SCALE) +1); //���� �������� 1�� ��� curBlock=1; //���� �������� 11�� ��� curBlock=2;
		
		//���� ����������� ���� , �� ��ȣ ��� 
		blockBegin = (curBlock-1) * BLOCK_SCALE +1; //curBlock=1 �϶� blockBegin=1 //curBlock=2 �϶� blockBegin=11;
		
		//����������� ����ȣ
		blockEnd = blockBegin + BLOCK_SCALE-1; //blockBegin 1�϶� blockEnd=10 // blockBegin 2�϶� blockEnd=20 //blockBegin 3�� �� blockEnd=30
		
		//������ ����� ������ �ʰ����� �ʵ��� ��� 
		if(blockEnd>totPage) blockEnd = totPage;
		
		//������ ������ �� �̵��� ������ ��ȣ
		prevPage = (curPage == 1) ? 1:(curBlock-1) * BLOCK_SCALE; //ex)����p11 F => (2-1) *10 => prevPage==10
		
		//������ ������ �� �̵��� ������ ��ȣ 
		nextPage= curBlock>totBlock?  (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1; //���� ������� 2�ϰ�� ,���������� 4�ϰ�� 2>4 F 11 12 13 14 15 16 17 18 19 20  -> 21 �ȴ�
		
		//������ �������� ������ �ʰ����� �ʵ��� ó�� 
		if(nextPage>= totPage) nextPage =totPage;
		
	}
	 public void setPageRange() {
		//where rn BETWEEN #{start} AND #{end}
		
		//���۹�ȣ = (����������-1) * �������� �Խù��� +1 				//curPage 2�� ��� pageBegin= 11
		pageBegin = (curPage-1) * PAGE_SCALE+1;
		
		//����ȣ =���۹�ȣ+�������� �Խù��� -1							//���������� ���������� curPage 2�ϰ�� pageEnd= 20
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

	public void setTotPage(int count) {  //��ü ������ ���� ���   count== ���ڵ� ���� ex)35��. 35/10 =>3.5 ceil���ؼ�  totPage=4; 
		//Math.ceil(�Ǽ�) �ø� ó�� 
		totPage =(int) Math.ceil(count*1.0/PAGE_SCALE);
	}

	public int getTotBlock() {
		return totBlock;
	}
	
    //������ �����  ���� ��� (�� 100���������  10���� ���)
	public void setTotBlock(int totBlock) {
	     //��ü������ ���� / 10
		// 91/10 =>9.1 =>10��
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
