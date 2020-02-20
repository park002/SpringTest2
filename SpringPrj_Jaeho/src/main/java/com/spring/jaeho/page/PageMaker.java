package com.spring.jaeho.page;

public class PageMaker {
	private int contentNum = 10;// �� �������� � ǥ������
	private int totalCount;// ��ü �Խù� ����
	private int curPageNum;// ���� ������ ��ȣ
	private int startPage = 1;// ���� ������ ����� ���� ������
	private int endPage = 5;// ���� ������ ����� ������ ������
	private boolean prev = false;// ���� �������� ���� ȭ��ǥ
	private boolean next;// ���� �������� ���� ȭ��ǥ
	private int currentblock;// ���� ���������
	private int lastblock;// ������ ������ ���

	public void prevnext(int pagenum) { //(������ư)
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

	// ��ü ���������� ����ϴ� �޼ҵ�
	// 125/10 =>12.5
	// 13������
	// 50/10 => 0
	// 5������
	// ex)��ü�Խñۼ�125/ ���������� ǥ���� �Խñ� ���� 10 12.5 ������ 0 ���� ũ�� ������ 1�� �����ָ� ��ü ������ 13
	public int CalcPage(int totalCount, int contentNum) { // ��ü�Խñ۰���,�� �������� ��� ǥ������
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

	public void setStartPage(int currentblock) { // �Ѻ��� 5�������� ���̰� ���� (����������)

		this.startPage = (currentblock * 5) - 4;
		// 1// 1 2 3 4 5
		// 2// 6 7 8 9 10
		// 3// 11 12 13 14 15
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int getlastblock, int getcurrentblock) { // (������ ������)
		if (getlastblock == getcurrentblock) {
			this.endPage = CalcPage(getTotalCount(), getContentNum()); // ��ü ���������� �־��ش�.
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

	public void setCurrentblock(int pagenum) { // (���� ������ ���)�� ������ �� ���ؼ� ���� �� �ִ� ==>pagenum
		// 5�� ���������� �� �������� 5���� ������ �� �̱� �����̴�.
		// ������ /������ �׷� ���� ������ ����
		// ������ �� ���ؼ� ���Ѵ� .
		// ���� ������ �� 1�̶�ġ��
		// 0.2>0 => currentblock++;
		// 0.2 �ڹٿ����� (int) �������� ���� ������ �ڹٴ� 0���� �ν��Ѵ� .
		// 0+1=1 ����� 1���ΰ� �� �� �ִ�
		// ���� 3p�� ��� 3/5 =>0.6 =>0+1; => 1
		// ���� �������� 8�ϰ��
		// 8/5 => 1.6 => 1.6>0(true) => (int ����ȯ)1 => 1+1=>2
		this.currentblock = pagenum / 5;
		if (pagenum % 5 > 0) {
			this.currentblock++;
		}
	}

	public int getLastblock() {
		return lastblock;
	}

	public void setLastblock(int totalcount) { // (������ ���)
		// �������� ������ ����Ʈ 10 , �������� ��ϴ� ������ 5�� 1�̸� 1 2 3 4 5
		// 10 * 5 =>50
		// ��ü�� 125�� �ִ� ���� .
		// 125/50 => 2.5 ==> 2.5>0(true) ==>3.
		this.lastblock = totalcount / (5 * this.contentNum);
		if (totalcount % (5 * this.contentNum) > 0) {
			this.lastblock++;
		}
	}

}
