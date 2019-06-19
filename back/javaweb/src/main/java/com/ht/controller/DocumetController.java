package com.ht.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ht.dao.DocumentDao;
import com.ht.service.DocumentService;
import com.ht.tools.Result;

/**
 * 实现对文档的访问，返回主要为数据
 * @author ht032
 *2019/4/25
 */

@RestController

public class DocumetController {
	@Autowired
	DocumentService service;
	//	@RequestMapping("/adddepartment")
	@RequestMapping("/api/document/getall")
	public HashMap<String, Object> getAll() {
		Result result = service.getAllDocument();
		return result.getResult();
	}
	
	@RequestMapping("/api/document/getpage")
	public HashMap<String, Object> getPage(HttpServletRequest req) {
	  String page = req.getParameter("page");
	  String size = req.getParameter("size");
	  Result docs = service.getDocumentPage(page, size, null);
	  return docs.getResult();
	}
	
}
