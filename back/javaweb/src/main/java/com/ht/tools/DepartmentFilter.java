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

/**
 * @deprecated 通过session实现登录验证
 * @author ht032
 * @since 2019/2/28
 * @deprecated 配置过滤器方式https://blog.csdn.net/weixin_37891479/article/details/79527641
 */
@WebFilter(filterName = "departFilter",urlPatterns = {"/api/department/*"})
public class DepartmentFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		 //是否需要登录
		String url = req.getRequestURI();
		if(ifNeedLogin(url)) {
			HttpSession session = req.getSession();
			//获取请求类型ajax不需要登录
			String requestType=req.getHeader("X-Requested-With");
			System.out.println("fiter"+(String)session.getAttribute("name"));
			System.out.println("fiter"+(String)session.getAttribute("id"));
			if(session!=null && session.getAttribute("name")!=null &&session.getAttribute("id")!=null) {
				String id = (String) session.getAttribute("id");
				String type=(String) session.getAttribute("type");
				chain.doFilter(request, response);
			}
			else if("XMLHttprequest".equals(requestType)) {
				response.getWriter().write("你还没有登录!");
				return;
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
				"/api/login","/fileuploadpage","/api/department/getall"
//				,"/api/department/fileupload"
		};
//		//获取请求路径
		System.out.println("path"+url);
		//判断是否需要验证
		for (String p : paths) {
			if(p.equals(url))
				return false;
		}
		return true;
	}

}
