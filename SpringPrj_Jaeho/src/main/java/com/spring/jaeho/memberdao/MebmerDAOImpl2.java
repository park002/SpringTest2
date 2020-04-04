//package com.spring.jaeho.memberdao;
//import org.apache.ibatis.session.SqlSession;
//
//import org.springframework.stereotype.Repository;
//import com.spring.jaeho.memberdto.MemberDTO2;
//
//
//public class MebmerDAOImpl2 implements MemberDAO2 {
//
//	
//	SqlSession sqlSession;
//
//	private static String namespace = "com.spring.jaeho.mybatis.mapper11111";
//
//	@Override
//	public void insertMember(MemberDTO2 dto) {
//		sqlSession.insert(namespace + ".insertMember", dto);
//		System.out.println(dto.isM_userEmailChecked()); // false
//	}
//
//	@Override
//	public boolean setUserEmailChecked(String code) {
//		sqlSession.update(namespace + ".setUserEmailChecked", code);
//		return true;
//	}
//
//	@Override
//	public boolean login(MemberDTO2 dto) {
//		String logincheck = sqlSession.selectOne(namespace + ".login", dto);
//		return (logincheck == null) ? false : true;
//	}
//	@Override
//	public int getUserEmailChecked(MemberDTO2 dto) {
//		Integer EmailCheck = sqlSession.selectOne(namespace + ".getUserEmailChecked", dto);
//		System.out.println("EmailCheck!!!!!!!!=>"+EmailCheck);
//		return (EmailCheck == 0) ? 0 : 1;
//	}
//	@Override
//	public MemberDTO2 viewMember(MemberDTO2 dto) {
//		return sqlSession.selectOne(namespace+".viewMember",dto);
//	}
//	
//	@Override
//	public boolean selectId(MemberDTO2 dto) {
//		  String selectId = sqlSession.selectOne(namespace + ".selectId", dto);
//		return (selectId==null) ? false:true ; 
//		
//	}
//	@Override
//	public String SearchID(MemberDTO2 dto) {
//		return sqlSession.selectOne(namespace+".SearchID",dto);
//	}
//	@Override
//	public String SearchPW(MemberDTO2 dto) {
//		return sqlSession.selectOne(namespace+".SearchPW", dto);
//	}
//	@Override
//	public String selectPW(MemberDTO2 dto) {
//		// TODO Auto-generated method stub
//		return sqlSession.selectOne(namespace+".selectPW",dto);
//	}
//@Override
//public void updatePW(MemberDTO2 dto) {
//	// TODO Auto-generated method stub
//	sqlSession.update(namespace+".updatePW",dto);
//	
//}
//@Override
//public void Remove(String m_name) {
//	// TODO Auto-generated method stub
//	sqlSession.delete(namespace+".Remove",m_name);
//	
//	
//}
//
//}
