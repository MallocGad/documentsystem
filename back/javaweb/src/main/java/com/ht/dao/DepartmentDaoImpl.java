package com.ht.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
/**
 * 实现对部门的增删查改
 * @author ht032
 *2019/4/25
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Override
	public void addDepartMent(String name,String phone,String officer,String remarks,String pass) throws Exception {
		// TODO Auto-generated method stub
		//默认没有删除为0
		//事务一致性
		Connection connection = jdbc.getDataSource().getConnection();
		connection.setAutoCommit(false);
		String sql="insert into department values(null,?,?,?,?,0,?)";
		try {
			jdbc.update(sql,name,phone,officer,remarks,pass);
			connection.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			e.printStackTrace();
			throw new Exception("数据库访问出错！"+e.getMessage());
		}
	}

	@Override
	public void deletDepartMentById(String id) {
		// TODO Auto-generated method stub
		String sql="delete from department where id =?";
		jdbc.update(sql,id);
	}

	@Override
	public void editDepartmentById(String id,String name,String phone,String officer,String remarks,String pass) {
		// TODO Auto-generated method stub
		String sql="update department set name=?,phone=?,officer=?,remarks=?,pass=? where id=?";
		jdbc.update(sql,name,phone,officer,remarks,pass,id);
	}

	@Override
	public List<Map<String, Object>> selectAllDepartment() {
		// TODO Auto-generated method stub
		String sql="select * from department";
		List<Map<String,Object>> list=null;
		list=jdbc.queryForList(sql);
		return list;
	}

	@Override
	public Map<String, Object> SelectDepartmentById(String id) {
		// TODO Auto-generated method stub
		String sql="select * from department where id =?";
		Map<String,Object> depat=null;
		depat=jdbc.queryForMap(sql,id);
		return depat;
	}

	@Override
	public void setIsdelete(String id) {
		// TODO Auto-generated method stub
		String sql="update department set isdelete=1 where id=?";
		jdbc.update(sql,id);
	}

	@Override
	public Long selectCountByName(String name) {
		// TODO Auto-generated method stub
		String sql="select count(*) as nums from department  where name=?";
		Map<String, Object> queryForMap = jdbc.queryForMap(sql,name);
		return (Long) queryForMap.get("nums");
	}

	@Override
	public List<Map<String, Object>> selectByIsDelete(String isdelete) {
		// TODO Auto-generated method stub
		String sql="select * from department where isdelete = ?";
		List<Map<String,Object>> list=null;
		list=jdbc.queryForList(sql,isdelete);
		return list;
	}

	@Override
	public Map<String, Object> getDepartmentByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from department where name = ?";
		Map<String, Object> department = jdbc.queryForMap(sql,name);
		return department;
	}

	
}
