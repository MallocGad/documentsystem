package com.ht.javaweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.ht.dao.DepartmentDao;
import com.ht.dao.DepartmentDaoImpl;
import com.ht.service.DepartmentService;
import com.ht.service.DepartmentServiceImpl;
import com.ht.tools.Result;

/**
 * 	测试department中数据库操作的方法
 * 	@author ht032
 *	2019年5月15日17:13:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class DepartmentServiceTest {
	@Autowired
	private DepartmentService service;
	@Test
	public void Test1() {
		Result departments=null;
//			departments= service.getDepartments();
//			System.out.println(departments.getResult());	
			Result result = service.addDepartment("部门1","17761230832","陈超","123","第一次添加");
//			System.out.println(result.getResult());
//		service.editDepartmentById("3", "测试修改", "4444444", "测试者", "修改测试记录");
//		Result result = departments = service.getDepartmentById("99");
//		System.out.println(result.getResult());
//		service.logicalDeleteDepartment("3");
//		result = departments = service.getDepartmentById("3");
//		System.out.println(result.getResult());
		
//		service.physicDeleteDepartment("3");
//		departments= service.getDepartments();
//		System.out.println(departments.getResult());
//		departments=service.getDepartmentNoDelete();
//		departments=service.getDepartmentDelete();
//		System.out.println(departments.getResult());
	}
}
