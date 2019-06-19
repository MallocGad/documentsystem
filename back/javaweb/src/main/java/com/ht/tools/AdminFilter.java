package com.ht.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(filterName = "adminFilter",urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
	
	@Override
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		

		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		 //是否需要登录
		//获取请求路径

		String url=req.getServletPath();
		if(ifNeedLogin(url)) {
			HttpSession session = req.getSession();
			//获取请求类型ajax不需要登录

			String requestType=req.getHeader("X-Requested-With");
			if(session!=null && session.getAttribute("user")!=null) {
				String id = (String) session.getAttribute("id");
				String type=(String) session.getAttribute("type");
				
				if(!"admin".equals(type)) {
					res.sendRedirect("/erro");
					return;
				}else
					chain.doFilter(request, response);
			}
			else if("XMLHttprequest".equals(requestType)) {
				response.getWriter().write("你还没有登录!");
				return;
				//是否是部门
			}else {
				res.sendRedirect("/loginpage");
				return;
			}
			
		}
		chain.doFilter(request, response);
	}
	
	private boolean ifNeedLogin(String url) {
		//不需要登录验证的url
		String[] paths=new String[] {
				"/login","/"
		};
		//判断是否需要验证
		for (String p : paths) {
			if(p.equals(url))
				return false;
		}
		return true;
	}

}


