package com.ht.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ht.dao.DocumentDao;
import com.ht.dao.DocumentDaoImpl;
import com.ht.tools.Result;
import com.ht.tools.getDate;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao document;
	private static String realPath="/home/ht/document/" ; //文件的保存目录/home/ht/document/
	private Result result=null;
	//事务开始
	@Override
	public Result addDocument(String depart_id, String readclass, String remarks,MultipartFile file) {
		// TODO Auto-generated method stub
		result=new Result();
		String path=null;
		String filename=null;
		File save_file=null;
		String date=null;
		try {
			if(file.isEmpty()) {
				result.addMsg("-1");
				result.addValue("上传文件不能为空！");
				return result;
			}
			
			//获取文件名字
			filename = file.getOriginalFilename();
			int index=filename.lastIndexOf(".");
			String extendname=filename.substring(index);
			
			
			//文件真实保存名字，防止重名等
			path=filename.substring(0, index)+"_"+document.selectMaxId()+extendname;
			date=getDate.Date();
		
			//文件保存
			save_file=new File(realPath+path);
			if(!save_file.getParentFile().exists()) {
				save_file.getParentFile().mkdir();
			}
			file.transferTo(save_file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.addCode(-1);
			result.addMsg("保存文件出错：IllegalStateException！");
			return result;
		}catch (IOException e) {//这里为什么会运行两次呢？而且第二次一定报错，上传路径eclipse配置的路径
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.addCode(-1);
			result.addMsg("保存文件出错：IOException！");
			System.out.println("第一次");
			return null;
		}
		//文件保存过后信息添加到数据库
		document.addDocument(filename, depart_id, readclass, date, path, remarks);
		result.addCode(0);
		result.addMsg("文件上传成功");
		System.out.println("第二次");
		return result;
	}

	@Override
	public void deleteDocumentById(String id) {
		// TODO Auto-generated method stub
		document.deleteDocumentById(id);
	}

	@Override
	//修改需要验证身份信息。
	public Result editDocumentById(String id, String name, String remarks) {
		// TODO Auto-generated method stub
		result=new Result();
		if(name==null || name=="") {
		  result.addCode(-1);
			result.addMsg("用户名不能为空");
		}
		document.editDocumentById(id, name, remarks);
		return result;
	}

	
	//展示文档不验证身份，那就下载时验证
	@Override
	public Result getDocumentById(String id) {
		// TODO Auto-generated method stub
		result=new Result();
		List<Map<String, Object>> doc = document.selectDocumentByDepatId(id);
		result.addCode(0);
    result.addMsg("成功");

		result.addValue(doc);
		return result;
	}

	@Override
	//此方法是管理员使用，验证是否为管理员
	public Result getAllDocument() {
		// TODO Auto-generated method stub
		result=new Result();
		List<Map<String, Object>> documents = document.selectAllDocument();
		result.addCode(0);
    result.addMsg("成功");

		result.addValue(documents);
		return result;
	}

	@Override
	public Result getDocumentsNoDelete() {
		// TODO Auto-generated method stub
		Result result=new Result();
		List<Map<String, Object>> departs = document.selectDocumentByIsDelete("0");
		result.addCode(0);
    result.addMsg("成功");

		result.addValue(departs);
		return result;
	}

	@Override
	public Result getDocumentsDelete() {
		// TODO Auto-generated method stub
		Result result=new Result();
		List<Map<String, Object>> departs = document.selectDocumentByIsDelete("1");
		result.addCode(0);
		result.addValue(departs);
		return result;
	}

	@Override
	public Result getDocumentByReadClass(String readclass) {
		// TODO Auto-generated method stub
		result=new Result();
		List<Map<String, Object>> documents = document.selectDocumentByReadclass(readclass);
		result.addCode(0);
		result.addValue(documents);
		return result;
	}

	//处理文件下载，后期考虑添加下载次数等功能
	@Override
	public Result downloadDocument(String file_id, HttpServletResponse response) {
		// TODO Auto-generated method stub
		result=new Result();
		Map<String, Object> map = document.selectByDocumentId(file_id);
		String realname =null;
		String filename=null;
		if(null!=map) {
			 realname=(String) map.get("path");
			 filename=(String) map.get("name");
			 File file =new File(realPath+realname);
			 if(file.exists()) {
				
				 //解决中文乱码问题比较全面的方式
//				 String agent = request.getHeader("User-Agent");
//					// 根据不同的客户端进行不同的编码
//					String filenameEncoder = "";
//					if (agent.contains("MSIE")) {
//						// IE浏览器
//						filenameEncoder = URLEncoder.encode(filename, "utf-8");
//						filenameEncoder = filenameEncoder.replace("+", " ");
//					} else if (agent.contains("Firefox")) {
//						// 火狐浏览器
//						BASE64Encoder base64Encoder = new BASE64Encoder();
//						filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
//					} else {
//						// 其它浏览器
//						filenameEncoder = URLEncoder.encode(filename, "utf-8");
//					}
				// 要下载的这个文件的类型-------客户端通过文件的MIME类型去区分类型
//					response.setContentType(this.getServletContext().getMimeType(filename));
//					// 告诉客户端该文件不是直接解析，而是以附件形式打开（下载）
//					response.setHeader("Content-Disposition", "attachment;filename=" + filenameEncoder);

			
				 //不全面解决中文乱码
				 try {
					filename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 response.setContentType("application/octet-stream");//二进制流，不知道下载文件类型
				 response.setHeader("Content-Disposition", "attachment;filename="+filename);//设置文件名
				 byte[] buffer=new byte[1024];
				 FileInputStream fileinstram=null;//输入流
				 BufferedInputStream bufinstream=null;//缓冲流
				 try {
					 fileinstram=new FileInputStream(file);//文件与输入流关联
					 bufinstream=new BufferedInputStream(fileinstram);//输入流与缓冲流关联
					 ServletOutputStream os = response.getOutputStream();
					 
					 int i=bufinstream.read(buffer);
					 while(i!=-1) {
						 os.write(buffer, 0, i);
						 i=bufinstream.read(buffer);
					 }
				  result.addCode(0);
					 result.addMsg("下载成功!");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.addCode(-1);
					result.addMsg("下载失败，文件读取错误！");
				}
				 return result;
			 }
			 
		}
		result.addCode(-1);
		result.addMsg("文件不存在!");
		return result;
	}

	@Override
	public Result getDocumentPage(String page, String size,String oderby) {
		// TODO Auto-generated method stub
		result=new Result();
		if(null==oderby||""==oderby)
		  oderby="id";
		if(null==page||null==size||"".equals(page)||"".equals(size)) {
		      result.addCode(-1);
		      result.addMsg("失败，输入请求页有误");
		      return result;
		}
		List<Map<String, Object>> docus = document.selectPage(page, size,oderby);
    result.addMsg("成功");
    result.addCode(0);
		result.addValue(docus);
		return result;
	}

	
}
