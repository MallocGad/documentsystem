 package com.ht.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
/**
 * 实现文档的增删改查
 * @author ht032
 *2019/4/15
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Override
	public void addDocument(String name, String depart_id,String readclass, String date,String path, String remarks) {
		// TODO Auto-generated method stub
		String sql="insert into document values(null,?,?,?,?,1,?,?)";
		jdbc.update(sql,name,depart_id,readclass,date,path,remarks);
	}

	@Override
	public void deleteDocumentById(String id) {
		// TODO Auto-generated method stub
		String sql="delete from document where id=?";
		jdbc.update(sql,id);
	}

	@Override
	public void editDocumentById(String id, String name, String remarks) {
		// TODO Auto-generated method stub
		String sql="update document set name=?,remarks=? where id=?";
		jdbc.update(sql,name,remarks,id);
		
	}

	@Override
	public List<Map<String, Object>> selectAllDocument() {
		// TODO Auto-generated method stub
		String sql="select * from document";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;
	}

	@Override
	public  List<Map<String, Object>> selectDocumentByDepatId(String id) {
		// TODO Auto-generated method stub
		String sql="select * from document where depart_id =?";
		List<Map<String, Object>> list = jdbc.queryForList(sql,id);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectDocumentByIsDelete(String isdelete) {
		// TODO Auto-generated method stub
		String sql="select * from document where `delete` = ?";
		List<Map<String,Object>> list=null;
		list=jdbc.queryForList(sql,isdelete);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectDocumentByReadclass(String readClass) {
		// TODO Auto-generated method stub
		String sql="select * from document where readclass = ?";
		List<Map<String,Object>> list=null;
		list=jdbc.queryForList(sql,readClass);
		return list;
	}

	
	//获取数据库当前最大的id值
	@Override
	public String selectMaxId() {
		// TODO Auto-generated method stub
		String sql="select max(id) as id from document";
		Map<String, Object> map = jdbc.queryForMap(sql);
		return ""+map.get("id");
	}

	@Override
	public Map<String, Object> selectByDocumentId(String id) {
		// TODO Auto-generated method stub
		String sql="select * from document where id=?";
		Map<String, Object> map = jdbc.queryForMap(sql,id);
		return map;
	}

	@Override
	public List<Map<String, Object>> selectPage(String page, String size, String oderby) {
		// TODO Auto-generated method stub
		int i_page=Integer.parseInt(page);
		int i_size=Integer.parseInt(size);
		String sql1="select count(*) as max from document";
		Map<String, Object> map = jdbc.queryForMap(sql1);
		String sql="select * from document order by ? DESC limit ";
		sql=sql+(i_page-1)*i_size+" , "+size;
		List<Map<String,Object>> documents=jdbc.queryForList(sql,oderby);
		documents.add(map);
		return documents;
	}

	
	

}
