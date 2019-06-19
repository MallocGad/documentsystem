package com.ht.controller;

import java.awt.dnd.DragGestureListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ht.service.DepartmentService;
import com.ht.service.DocumentService;
import com.ht.tools.Result;

/**
 * 页面控制
 * @author ht032
 *2019/4/25
 */
@Controller
public class ViewController {
	@Autowired
	private DocumentService document;
	@Autowired
	DepartmentService department;
	String admin="admin";
	String adminpass="123456";
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "login";
	}
	@RequestMapping("/api/login")
	@ResponseBody
	public HashMap<String, Object> login(HttpServletRequest req,Map<String, Object> map ) {
		Result result=new Result();
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		//管理员还是普通用户
		String type = req.getParameter("type");

		if("".equals(name)||"".equals(pass)||"".equals(type)||null==type||null==name||null==pass) {
			result.addCode(-1);
			result.addMsg("输入不能为空");
			return result.getResult();
		}
		if(type.equals("1")) {
			if(!department.isExist(name)) {
				result.addCode(-1);
				result.addMsg("用户名不存在");
				return result.getResult();
			}
			HashMap<String, Object> departs = department.getDepartment(name).getResult();
			Map<String, Object> depart = (Map<String, Object>) departs.get("result");
			System.out.println(depart);
			int l_id=(int) depart.get("id");
			String id = Integer.toString(l_id);
			String d_name = (String) depart.get("name");
			String d_pass = (String) depart.get("pass");
			if(!d_name.equals(name)||!d_pass.equals(pass)) {
				result.addCode(-1);
				result.addMsg("部门不存在或密码错误");
				return result.getResult();
			}
			HttpSession session = req.getSession();
			session.setAttribute("type", "depart");
			session.setAttribute("id", id);
			session.setAttribute("name", d_name);
			session.setMaxInactiveInterval(20);
			result.addCode(0);
			result.addMsg("成功");
			HashMap<String, Object> map1=new HashMap<String, Object>();
			map1.put("name",name );
			result.addValue(map1);
			return result.getResult();
			
		}else {
			if(!name.equals(admin)||!pass.equals(adminpass)) {
				result.addCode(-1);
				result.addMsg("管理员不存在或密码错误");
				return result.getResult();
			}
			HttpSession session = req.getSession();
			session.setAttribute("type", "admin");
			session.setAttribute("name", name);
			session.setMaxInactiveInterval(20);
			result.addCode(0);
			result.addMsg("成功");
			return result.getResult();
		}
	}
}
