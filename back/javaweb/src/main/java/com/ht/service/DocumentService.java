package com.ht.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.ht.tools.Result;

public interface DocumentService {
	public void deleteDocumentById(String id);
	public Result getDocumentById(String id);
	public Result getAllDocument();
	public Result editDocumentById(String id, String name, String remarks);
	public Result getDocumentsNoDelete();
	public Result getDocumentsDelete();
	public Result getDocumentByReadClass(String readclass);
	public Result addDocument( String depart_id, String readclass, String remarks, MultipartFile file);
	Result downloadDocument(String id, HttpServletResponse response);
	public Result getDocumentPage(String page,String size,String oderby);
}
