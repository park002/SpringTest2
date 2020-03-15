package board;

//DBConnectionMgr(DB접속,관리), BoardDTO(매개변수,반환형,데이터 담는 역할)
//DB사용
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List을 사용
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Reservation.DBConnectionMgr;

public class BoardDAO { // MemberDAO

	private DBConnectionMgr pool = null;// 1.연결할객체 선언
	// 공통
	private Connection con = null;
	private PreparedStatement pstmt = null;// ?
	private ResultSet rs = null;// select
	private String sql = "";// 실행시킬 SQL구문 저장

	// 2.생성자를 통해서 연결->의존성
	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}// 생성자

	// 1.페이징 처리를 위한 전체레코드수를 구해와야 한다.=>데이터를 출력
	// select count(*) from board; =>반환값 O ,where 조건식X ->매개변수가 X
	public int getArticleCount() { // getMemberCount()
		int x = 0;// 총 레코드갯수를 저장

		try {
			con = pool.getConnection();// 커넥션풀에서 한개 빌려오는작업
			System.out.println("con=>" + con);// 디버깅코드
			sql = "select count(*) from notice"; // select count(*) from member;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				x = rs.getInt(1); // 변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X을 사용할 수 없는 경우에 사용
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);// 연결객체 및 다른 객체 반납
		}
		return x;
	}
   //-----------------------------------------------------------------------------------
	// (1)게시판의 레코드수를 검색어에 따른 메서드작성(검색분야,검색어)

	public int getArticleSearchCount(String search, String searchtext) { // getMemberCount()
		int x = 0;// 총 레코드갯수를 저장

		try {
			con = pool.getConnection();// 커넥션풀에서 한개 빌려오는작업
			System.out.println("con=>" + con);// 디버깅코드
			// ------------------------------------------------------------------------
			if (search == null || search == "") { // 검색분야 선택X (검색어를 입력하지 않은경우)
				sql = "select count(*) from notice"; // select count(*) from member;
			} else { // 검색분야(제목,작성자,제목+본문)
				if (search.equals("subject_content")) { // 제목+본문
					sql = "select count(*) from notice where notice_title like '%" + searchtext
							+ "%' or notice_contents like '%" + searchtext + "%' ";
				}
			}
			/* System.out.println("getArticleSearchCount 검색sql=>"+sql); */
			// -------------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				x = rs.getInt(1); // 변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X을 사용할 수 없는 경우에 사용
			}
		} catch (Exception e) {
			/* System.out.println("getArticleSearchCount() 메서드 에러유발"+e); */
		} finally {
			pool.freeConnection(con, pstmt, rs);// 연결객체 및 다른 객체 반납
		}
		return x;
	}

	// -----------------------------------------------------------------------------------
	// 2. 글목록보기

	public List getArticles(int start, int end) {// getMemberList(int start,int end)

		List articleList = null;// ArrayList articleList=null;

		try {
			con = pool.getConnection();

			sql = "select * from notice order by notice_number desc limit ?,?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 생성하라
				do {
					BoardDTO article = new BoardDTO();// MemberDTO~
					article.setNotice_number(rs.getInt("notice_number"));
					article.setNotice_title(rs.getString("notice_title"));
					article.setNotice_contents(rs.getString("notice_contents"));
					article.setNotice_date(rs.getTimestamp("notice_date"));
					article.setRef(rs.getInt("ref"));

					// 추가
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			/* System.out.println("getArticles() 메서드 에러유발"+e); */
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	//------------------------------------------------------------------------
	
	//(2)검색어에 따른 레코드의 범위지정에 대한 메서드
	
	public List getBoardArticles(int start, int end, String search, String searchtext) {

		List articleList = null;// ArrayList articleList=null;

		try {
			con = pool.getConnection();
			// ---------------------------------------------------------------------------
			if (search == null || search == "") {
				sql = "select * from notice order by notice_number desc limit ?,?";// 1,10
			} else { // 제목+본문
				if (search.equals("subject_content")) { // 제목+본문
					sql = "select * from notice where notice_title like '%" + searchtext
							+ "%' or notice_contents like '%" + searchtext + "%' order by notice_number desc limit ?,?";
				}
			}
			/* System.out.println("getBoardArticles()의 sql=>"+sql); */
			// -----------------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 생성하라
				do {
					BoardDTO article = new BoardDTO();// MemberDTO~
					article.setNotice_number(rs.getInt("notice_number"));
					article.setNotice_title(rs.getString("notice_title"));
					article.setNotice_contents(rs.getString("notice_contents"));// 글내용
					article.setNotice_date(rs.getTimestamp("notice_date"));// 오늘날짜->코딩 ->now()
					article.setRef(rs.getInt("ref"));// 그룹번호->신규글과 답변글 묶어주는 역할

					// 추가
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticles() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// ---(3)페이징 처리계산 정리해주는 메서드(ListAction)-------------------------------------
	// 1) 화면에 보여주는 페이지번호 2) 화면에 출력할 레코드갯수
	// Hashtable => 페이징 처리에 관련된 처리결과를 저장한 변수들을 하나의 객체에 담아서 -> ListAction
	public Hashtable pageList(String pageNum, int count) {

		// 1.페이징 처리결과를 저장할 hashtable객체를 선언
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();
		// ListAction에서의 복잡한 페이징처리를 대신 처리
		int pageSize = 10;// numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
		int blockSize = 3;// pagePerBlock->블럭당 보여주는 페이지수 10

		// 게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력
		if (pageNum == null) {
			pageNum = "1";// default(무조건 1페이지는 선택하지 않아도 보여줘야 하기때문에),가장 최근의 글
		}
		int currentPage = Integer.parseInt(pageNum);// "1"->1 현재페이지(=nowPage)
		// 메서드 호출->시작 레코드번호
		// (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21)
		int startRow = (currentPage - 1) * pageSize + 1; // 시작 레코드 번호
		int endRow = currentPage * pageSize;// 1*10=10,2*10=20,3*10=30 ->마지막 레코드번호
		int number = 0;// beginPerPage->페이지별로 시작하는 맨 처음에 나오는 게시물번호
		System.out.println("현재 레코드수(count)=>" + count);
		// 122-(1-1)*10=122,122-(2-1)*10=112
		number = count - (currentPage - 1) * pageSize;
		System.out.println("페이지별 number=>" + number);

		// 총페이지수,시작,종료페이지 계산
		// 122/10=12.2+1=>12.2+1.0=13.2=13페이지
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 블럭당 페이지수 계산->10->10배수,3->3배수
		int startPage = 0;// 1,2,3,,,,10 [다음 블럭 10]->11,12,,,,20
		if (currentPage % blockSize != 0) {// 1~9,11~19,21~29
			startPage = currentPage / blockSize * blockSize + 1;
		} else { // 10%10=0,(10,20,30)
					// ((10/10)-1)*10+1
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}
		int endPage = startPage + blockSize - 1;// 1+10-1=10
		System.out.println("startPage=" + startPage + ",endPage=" + endPage);
		if (endPage > pageCount)
			endPage = pageCount;// 마지막페이지=총페이지수
		// 페이징처리에 대한 계산결과->Hashtable,HashMap->ListAction전달->메모리에 저장->list.jsp
		pgList.put("pageSize", pageSize);// <->pgList.get(키명)("pageSize")
		pgList.put("blockSize", blockSize);
		pgList.put("currentPage", currentPage);
		pgList.put("startRow", startRow);
		pgList.put("endRow", endRow);
		pgList.put("count", count);
		pgList.put("number", number);
		pgList.put("startPage", startPage);
		pgList.put("endPage", endPage);
		pgList.put("pageCount", pageCount);

		return pgList;
	}

   //--------------------------------------------------------------------------
   // ------ 글쓰기 ----------------
	// insert into board values(?,?,,,)
	public void insertArticle(BoardDTO article) { // ~(MemberDTO mem)

		// 1.article=>신규글인지 답변글인지 확인
		int num = article.getNotice_number();// 0(신규글) 0아닌경우(답변글)
		int ref = article.getRef();
		// 테이블에 입력할 게시물번호를 저장할 변수
		int number = 0;// 데이터를 저장하기위한 게시물번호
		System.out.println("insertArticle 메서드의 내부 num=>" + num);// 0신규글

		try {
			con = pool.getConnection();
			sql = "select max(notice_number) from notice"; // 최대값+1=실제 저장할 게시물번호
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면 ->rs.last()->rs.getRow();(X)
				number = rs.getInt(1) + 1; // 변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X을 사용할 수 없는 경우에 사용
			} else {// 현재 테이블에 데이터가 한개라도 없는 경우
				number = 1;
			}

			// 12개->num,reg_date,reacount(생략)->default,
			// 작성날짜->sysdate,now()<-mysql(?대신에)
			sql = "insert into notice(notice_title,notice_contents,notice_date,ref) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getNotice_title());// 웹에서는 Setter Method를 메모리에 저장
			pstmt.setString(2, article.getNotice_contents());
			pstmt.setTimestamp(3, article.getNotice_date());
			pstmt.setInt(4, ref);// pstmt.setInt(6, article.getRef());(X)
			// --------------------------------------------------------

			int insert = pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>" + insert);// 1 or 0실패
		} catch (Exception e) {
			System.out.println("insertArticle() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	// 글 상세보기
	public BoardDTO getArticle(int num) {

		BoardDTO article = null;// ArrayList articleList=null;
		try {
			con = pool.getConnection();

			sql = "select * from notice where notice_number=?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				// article=new BoardDTO();//MemberDTO~
				article = makeArticleFromResult();// 반환형을 얻어와서 처리

				article.setNotice_number(rs.getInt("notice_number"));
				article.setNotice_title(rs.getString("notice_title"));
				article.setNotice_contents(rs.getString("notice_contents"));// 글내용
				article.setNotice_date(rs.getTimestamp("notice_date"));// 오늘날짜->코딩 ->now()
				article.setRef(rs.getInt("ref"));// 그룹번호->신규글과 답변글 묶어주는 역할

			}
		} catch (Exception e) {
			System.out.println("getArticle() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// -----------중복된 레코드 한개를 담을 수 있는 메서드를 따로 만들어서 처리------------
	private BoardDTO makeArticleFromResult() throws Exception {
		BoardDTO article = new BoardDTO();
		article.setNotice_number(rs.getInt("notice_number"));
		article.setNotice_title(rs.getString("notice_title"));
		article.setNotice_contents(rs.getString("notice_contents"));// 글내용
		article.setNotice_date(rs.getTimestamp("notice_date"));// 오늘날짜->코딩 ->now()
		article.setRef(rs.getInt("ref"));// 그룹번호->신규글과 답변글 묶어주는 역할

		return article;
	}

	//-----------------------------------------------------------------------------
	// 1) 게시물 수정.
	public BoardDTO updateGetArticle(int num) {
		BoardDTO article = null;// ArrayList articleList=null;
		try {
			con = pool.getConnection();

			sql = "select * from notice where notice_number=?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			// 글목록보기
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				article = makeArticleFromResult();

				article = new BoardDTO();// MemberDTO~
				article.setNotice_number(rs.getInt("notice_number"));
				article.setNotice_title(rs.getString("notice_title"));
				article.setNotice_contents(rs.getString("notice_contents"));// 글내용
				article.setNotice_date(rs.getTimestamp("notice_date"));// 오늘날짜->코딩 ->now()
				article.setRef(rs.getInt("ref"));// 그룹번호->신규글과 답변글 묶어주는 역할
			}
		} catch (Exception e) {
			System.out.println("updateGetArticle() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	// 글 수정
	public int updateArticle(BoardDTO article) {

		int x = -1;

		try {
			con = pool.getConnection();
			sql = "select * from notice where notice_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNotice_number());
			rs = pstmt.executeQuery();

			sql = "update notice set notice_title=?,notice_contents=? where notice_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getNotice_title());
			pstmt.setString(2, article.getNotice_contents());
			pstmt.setInt(3, article.getNotice_number());
			int update = pstmt.executeUpdate();
			System.out.println("게시판의 글수정 성공유무(update)=>" + update);// 0:실패 1:성공

		} catch (Exception e) {
			System.out.println("updateArticle() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
// ---------------------------------------------------------------------------------------------------------------------------------------
	
	// 글 삭제
	
	public int deleteArticle(int num) {

		int x = -1;

		try {
			con = pool.getConnection();
			sql = "delete from notice where notice_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int delete = pstmt.executeUpdate();

			System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);// 0:실패 1:성공

		} catch (Exception e) {
			System.out.println("deleteArticle() 메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
}