package com.spring.jaeho;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.dto.ReplyDTO;
import com.spring.jaeho.page.ReplyPager;
import com.spring.jaeho.service.ReplyService;

@RestController
@RequestMapping("/reply/")
public class ReplyController {

	@Autowired
	ReplyService service;

//
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	public void insertReply(ReplyDTO dto, HttpSession session) {// Ŀ�ǵ尴ü
//		String userId = (String) session.getAttribute("userId");
//		dto.setReplyer(userId);
//		// System.out.println("����ۼ��� ===================>"+dto.getReplyer());
//		service.insertReply(dto);
//	}

	// RestConroller ������� json�� �����Ͽ� ����Է�.
	// ResponseEntity : ������ + http status code 404,500 ���� �� �����ڰ� ������ �����ϰ���� �����Ϳ� �Բ�
	// ������ �� �־ ������ ��� ����.
	// ResponseBody : ��ü�� json���� ��ȯ�ϰڴ�.
	// RequestBody :json�� ��ü�� �ްڴ�.
	@RequestMapping(value = "/insertRest", method = RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyDTO dto, HttpSession session) {
		ResponseEntity<String> entity = null;

		try {
			// ���ǿ� ����� ȸ�����̵� ����ۼ��ڿ� ���� .
			String userId = (String) session.getAttribute("userId");
			dto.setReplyer(userId);
			// ����Է�(����) �޼ҵ� ȣ��
		
			service.insertReply(dto);
			
	
			// ����Է��� �����ϸ� �����޽��� ����
			entity = new ResponseEntity<String>("success", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// ����Է��� �����ϸ� ���и޽��� ����
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// HTTP status �޽��� ����.
		return entity;
	}

	// json���� ��ȯ�Ͽ� ��ϻ���
	//��۸���Ʈ
	// PathVariable: url�� ��õ� ������ �޾ƿ´�.
	@RequestMapping(value = "/list/{b_no}/{curPage}", method = RequestMethod.GET)
	public ModelAndView replyList(@PathVariable("b_no") int b_no, @PathVariable int curPage, ModelAndView mav,
			HttpSession session) {
		// ����¡ó�� ��۰��� ���ϴ� �޼ҵ� �ʿ� .
		int count = service.countReply(b_no);

		ReplyPager replyPager = new ReplyPager(count, curPage);
		// ���� �������� ������ ���۹�ȣ
		int start = replyPager.getPageBegin(); // 0 0�����
		int end = replyPager.getPageScale(); // 5 5���� �����ְڴ�.
		List<ReplyDTO> list = service.listReply(b_no, start, end, session);
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+list.get(0).getB_date());
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		mav.setViewName("board/replyList");
		return mav; // board/replyList.jsp�� ������

	}

//��� �󼼺���
	@RequestMapping(value = "/detail/{r_no}", method = RequestMethod.GET)
	public ModelAndView replyModify(@PathVariable("r_no") int r_no, ModelAndView mav) {
		ReplyDTO dto = service.replyDetail(r_no);
		mav.addObject("dto", dto);
		mav.setViewName("/reply/replyDetail");
		return mav;
	}

	// ��ۼ���
	@RequestMapping(value = "/update/{r_no}", method = RequestMethod.PATCH)
	public ResponseEntity<String> replyModify(@PathVariable("r_no") int r_no, @RequestBody ReplyDTO dto) {
		ResponseEntity<String> entity = null;
		try {
			dto.setR_no(r_no);
			service.replyModify(dto);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ��ۻ���
	@RequestMapping(value = "/delete/{r_no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> replydelete(@PathVariable("r_no") int r_no) {
		ResponseEntity<String> entity = null;
		try {
			service.replydelete(r_no);
			entity =new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
//	static {
//		
//		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
//		
//		list.getB_no(); //1
//		row.getB_no(); //2
//		
//		list.get(0).getB_no();
//		
//		for(ReplyDTO row : list)
//			row.getB_no(); //3
//		
//	}

//	@RequestMapping(value = "/listReply", method = RequestMethod.GET)
//	public ModelAndView listReply(@RequestParam("b_no") int b_no) {
//		List<ReplyDTO> list = service.listReply(b_no);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", list);
//		mav.setViewName("/reply/replyList");
//		return mav;
//	}
//
//	@RequestMapping(value = "/listJson")
//	public List<ReplyDTO> listJson(@RequestParam("b_no") int b_no) {
//		List<ReplyDTO> list = service.listReply(b_no);
//	// System.out.println(list);
//		return list;
//	}

}
