package com.peeinn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("----------postHandle");
		Object loginID = modelAndView.getModel().get("loginId");
		Object errorMsg = modelAndView.getModel().get("error");
		
		if(loginID != null) {
			//session 영역에 Auth키 만들어서 userid 값 넣음
			request.getSession().setAttribute("Auth", loginID);
			System.out.println("로그인 완료");
			response.sendRedirect(request.getContextPath()); //home으로 이동
		} else if(errorMsg != null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
		}
	}  

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return super.preHandle(request, response, handler);
	}


}// LoginInterceptor
