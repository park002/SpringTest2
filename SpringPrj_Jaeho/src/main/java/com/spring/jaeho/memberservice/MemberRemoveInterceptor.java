package com.spring.jaeho.memberservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberRemoveInterceptor extends HandlerInterceptorAdapter {
	// preHandler메소드는 Controller 시작전 인터셉터.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//무상태 프로토콜
		HttpSession session = request.getSession(false);
		if (session != null) { //세션에 값이 있다면
			Object obj = session.getAttribute("userId");
			if (obj != null) { //obj에 값이 있다면
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/"); //세션에 값이 없다면 Main 으로 .
		return false;
	}

}
