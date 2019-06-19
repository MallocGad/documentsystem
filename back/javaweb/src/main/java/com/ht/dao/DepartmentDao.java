package com.ht.dao;

import java.util.List;
import java.util.Map;

/**
 * 定义部门表的查找接口
 * @author ht032
 *2019/4/25
 */
public interface DepartmentDao {
	public List<Map<String,Object>> selectAllDepartment();
	public void addDepartMent(String name, String phone, String officer, String remarks,String pass) throws Exception;
	public void deletDepartMentById(String id);
	public Map<String, Object> SelectDepartmentById(String id);
	public void setIsdelete(String id);
	void editDepartmentById(String id, String name, String phone, String officer,String pass, String remarks);
	public Long selectCountByName(String name);
	public Map<String,Object> getDepartmentByName(String name);
	/**
	 * 0和1两个状态
	 * @param isdelete
	 * @return
	 */
	public List<Map<String,Object>> selectByIsDelete(String isdelete);
}
