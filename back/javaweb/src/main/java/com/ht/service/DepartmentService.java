package com.ht.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ht.tools.Result;

public interface DepartmentService {
	
	/**
	 * 增加一个部门，名字不可以重复
	 */
	public Result addDepartment(String name,String phone,String officer,String pass,String remarks) ;
	
	/**
	 * 通过部门id获得部门信息
	 * @param id
	 * @return 返回一个数据
	 */
	public Result getDepartmentById(String id);
	
	
	public Result getDepartmentNoDelete();
	public Result getDepartmentDelete();
	public boolean isExist(String name);
	public Result getDepartment(String name);
	/**
	 * 获取所有部门信息
	 * @return 返回部门信息的列表
	 * 
	 */
	public Result getDepartments();
	
	/**
	 * 修改部门信息
	 * @param id
	 * @param name
	 * @param phone
	 * @param officer
	 * @param remarks
	 */
	void editDepartmentById(String id, String name, String phone, String officer,String pass, String remarks);
	
	//物理删除部门信息
	public void physicDeleteDepartment(String id);
	
	//逻辑删除部门信息
	public void logicalDeleteDepartment(String id);
	
	

}
