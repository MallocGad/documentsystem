package com.ht.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.service.DepartmentService;
import com.ht.service.DepartmentServiceImpl;
import com.ht.tools.Result;

/**
 * 实现对部门的访问，返回主要为数据
 * @author ht032
 *2019/4/25
 */
@RestController
//@RestController//相当于@controller和responsebody一起用了   如果想返回静态页面或者thymeleaf的文件用ModelAndView
public class DepartmentController {
//	@RequestMapping("/getdepartments")
	@RequestMapping("/api/getdepartments")
//	@ResponseBody//加了这个只能返回json等数据
	public String getDepString() {
		
		return "index.html";
//		return "index"//只能是thymeleaf配置的模板html
//		return "redirect:index.html"//静态文件html
		
	}
	@Autowired
	DepartmentService department;
	
	//获取所有的部门
	@RequestMapping("/api/department/getall")
	public HashMap<String, Object> getAllDepartment() {
		Result result = department.getDepartments();
		return result.getResult();
	}
	
	//通过id获取部门
	@RequestMapping("/api/department/get/{id}")
	public Result getDepartment(@PathVariable String id) {
		Result result = department.getDepartmentById(id);
		return result;
	}
	
	//增加部门
	@RequestMapping("/api/department/add")
	public void addDepartment(HttpServletRequest request) {
		String name = request.getParameter("name");
		String depart_id = request.getParameter("depart_id");
		String readclass = request.getParameter("readclass");//可访问等级
//		request.getParameter("path")
		String remarks = request.getParameter("remarks");
		
	}
	
	//删除
	@RequestMapping("/api/department/delete/{id}")
	public void deleteDepartment(@PathVariable String id) {
		//逻辑删除
		department.logicalDeleteDepartment(id);
	}
	
	//删除
	@RequestMapping("/api/department/remove/{id}")
	public void removeDepartment(@PathVariable String id) {
		//逻辑删除
		department.physicDeleteDepartment(id);
	}
	
	
}
