package com.spring.jaeho.memberservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberRemoveInterceptor extends HandlerInterceptorAdapter {
	// preHandler�޼ҵ�� Controller ������ ���ͼ���.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//������ ��������
		HttpSession session = request.getSession(false);
		if (session != null) { //���ǿ� ���� �ִٸ�
			Object obj = session.getAttribute("userId");
			if (obj != null) { //obj�� ���� �ִٸ�
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/"); //���ǿ� ���� ���ٸ� Main ���� .
		return false;
	}

}
