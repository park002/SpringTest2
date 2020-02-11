package com.javalec.spring_mybatis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.spring_mybatis.dto.ContentDto;

public class ContentDao implements IDao{

	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) { //ContentDao ��������� ��������������(�����̳�)�� �������  template ������ü�ڵ����� 
		this.template = template;
	}
	
	public ContentDao() { //������ ��� 
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public ArrayList<ContentDto> listDao() {
		String query = "select * from board order by mId desc";
		ArrayList<ContentDto> dtos = (ArrayList<ContentDto>) 
				template.query(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
		return dtos;
	}
	
	
//	    <insert id="insertBoard" parameterType="BoardDto">    
//	        insert into tbl_board(seq,name,email,homepage,title,content,password,count,regdate,pos,depth)
//	        values((select *from (select max(seq)+1 from tbl_board) next),#{name},#{email},#{homepage},#{title},#{content},#{password},0,curdate(),0,0)
//	    </insert>

//	try {
//		con = pool.getConnection();
//		sql = "select max(notice_number) from notice"; // �ִ밪+1=���� ������ �Խù���ȣ
//		pstmt = con.prepareStatement(sql);
//		rs = pstmt.executeQuery();
//		if (rs.next()) {// �����ִ� ����� �ִٸ� ->rs.last()->rs.getRow();(X)
//			number = rs.getInt(1) + 1; // ������=rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)=>�ʵ��X�� ����� �� ���� ��쿡 ���
//		} else {// ���� ���̺� �����Ͱ� �Ѱ��� ���� ���
//			number = 1;
//		}
//
//		// 12��->num,reg_date,reacount(����)->default,
//		// �ۼ���¥->sysdate,now()<-mysql(?��ſ�)
//		sql = "insert into notice(notice_title,notice_contents,notice_date,ref) values(?,?,?,?)";
//		pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, article.getNotice_title());// �������� Setter Method�� �޸𸮿� ����
//		pstmt.setString(2, article.getNotice_contents());
//		pstmt.setTimestamp(3, article.getNotice_date());
//		pstmt.setInt(4, ref);// pstmt.setInt(6, article.getRef());(X)

	
	@Override
	public void writeDao(final String mWriter, final String mContent) {
		System.out.println("writeDao()");

		this.template.update(new PreparedStatementCreator() {
			//	PreparedStatement createPreparedStatement(Connection con) throws SQLException;
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				//(select * from (select max(seq)+1 from board)
   //insert into tabe_a(col1, col2) values(1, 2);
				//select max(notice_number) from notice
				//        insert into tbl_board(seq,name,email,homepage,title,content,password,count,regdate,pos,depth)
		        //values((select *from (select max(seq)+1 from tbl_board) next),
				String query = "insert into board (mWriter, mContent) values (?, ?)";
					//	+ "values (board_seq.nextval, ?, ?)\";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, mWriter);
				pstmt.setString(2, mContent);
				return pstmt;
			}
		});
	}
	
	@Override
	public ContentDto viewDao(String strID) {
		System.out.println("viewDao()");
		
		String query = "select * from board where mId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
	}
	
	@Override
	public void deleteDao(final String bId) { //1 
		System.out.println("deleteDao()");
		String query = "delete from board where mId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			//	void setValues(PreparedStatement ps) throws SQLException;
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
		
	}

}
