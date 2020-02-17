package com.spring.jaeho.page;

public class Criteria {
	private int page; //���� ������ ��ȣ  1
	private int perPageNum; //�� �������� ������ �Խñ� ����  10

	public int getPageStart() { //Ư����������  �Խñ�  ���۹�ȣ ,�Խñ� ���� �� ��ȣ 
		return (this.page - 1) * perPageNum;
	}

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}
	
	//�������� �������� ���� �ʰ� ����. ������ �Ǹ� 1�������� ��Ÿ����.
	public void setPage(int page) { //�� page�� ������ ���  ������ �߻�, �׷��� 1�� �д�. ���� ��page��  0���� Ŭ��� ���������� �� ���� ���� .
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}
 //������ ���������� ������ �Խù� ������ 10���� �����Ѵ� .  �������� ������ �Խñ� ���� ������ �ʰ� �����ߴ�
	public void setPerPageNum(int pageCount) { //cnt ==���������� ������ �Խù� ���� ��� �������� . 
		int cnt = this.perPageNum;
		if (pageCount != cnt) { //���� , ������ ������ != perPageNum(���������� ������ �Խù� ����)���  ���� ��������� .  
			this.perPageNum = cnt;
		} else { // paegeCount�� 10�� ���. 10��������
			this.perPageNum = pageCount;
		}
	}

}
