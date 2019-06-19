package com.ht.javaweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ht.service.DocumentService;
import com.ht.tools.Result;

/**
 * 测试单元
 * @author ht032
 *测试文档DocumentService中的方法
 */

//
//
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {
	@Autowired
	private DocumentService service;
	
	@Test
	public void test() {
		
		Result result = null;
		
		//测试添加查询
//		service.addDocument("测试添加文档", "1", "0", "这是一个测试添加");
//		result = service.getAllDocument();
//		System.out.println(result.getResult());
		
		//测试更改与单列查询
//		service.editDocumentById("3", "更改测试文档", "这是一哥更改测试");
//		result=service.getDocumentById("3");
//		System.out.println(result.getResult());
		
		//测试删除
//		service.deleteDocumentById("2");
//		result=service.getAllDocument();
//		System.out.println(result.getResult());
		
		//测试查询2
//		result = service.getDocumentsDelete();
//		result = service.getDocumentsNoDelete();
//		System.out.println(result.getResult());
		
		//测试查询3
//		result = service.getDocumentByReadClass("0");
//		System.out.println(result.getResult());

		Result ds = service.getDocumentPage("1", "2","id");
		System.out.println("输出");
		System.out.println(ds);
	}
}
