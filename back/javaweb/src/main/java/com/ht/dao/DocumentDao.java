package com.ht.dao;

import java.util.List;
import java.util.Map;

/**
 * 定义文档表查找接口
 * @author ht032
 *2019/4/25
 */
public interface DocumentDao {
	public void deleteDocumentById(String id);
	public void editDocumentById(String id,String name,String remarks);
	public List<Map<String,Object>> selectAllDocument();
	public List<Map<String, Object>> selectDocumentByDepatId(String id);
	void addDocument(String name, String depart_id, String readclass, String date, String path, String remarks);
	public List<Map<String,Object>> selectDocumentByIsDelete(String isdelete);
	public List<Map<String,Object>> selectDocumentByReadclass(String readClass);
	public String selectMaxId();
	public List<Map<String,Object>> selectPage(String page,String size,String oderby);
	public Map<String,Object> selectByDocumentId(String id);
}
