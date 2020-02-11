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
	public void setTemplate(JdbcTemplate template) { //ContentDao 명시적으로 스프링설정파일(컨테이너)에 적어놨음  template 의존객체자동주입 
		this.template = template;
	}
	
	public ContentDao() { //생성자 명시 
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
//		sql = "select max(notice_number) from notice"; // 최대값+1=실제 저장할 게시물번호
//		pstmt = con.prepareStatement(sql);
//		rs = pstmt.executeQuery();
//		if (rs.next()) {// 보여주는 결과가 있다면 ->rs.last()->rs.getRow();(X)
//			number = rs.getInt(1) + 1; // 변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X을 사용할 수 없는 경우에 사용
//		} else {// 현재 테이블에 데이터가 한개라도 없는 경우
//			number = 1;
//		}
//
//		// 12개->num,reg_date,reacount(생략)->default,
//		// 작성날짜->sysdate,now()<-mysql(?대신에)
//		sql = "insert into notice(notice_title,notice_contents,notice_date,ref) values(?,?,?,?)";
//		pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, article.getNotice_title());// 웹에서는 Setter Method를 메모리에 저장
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
